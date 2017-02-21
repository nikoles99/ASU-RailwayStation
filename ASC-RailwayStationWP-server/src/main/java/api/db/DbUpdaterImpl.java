package api.db;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.*;
import java.sql.*;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Component
public class DbUpdaterImpl implements DbUpdater {

    private final Logger LOGGER = Logger.getLogger(getClass());

    private final String PATH = "/patches/";
    private final String PATCH_PREFIX = "patch_";
    private final String PATCH_POSTFIX = ".sql";
    private final String LAST_PATCH_SELECT_QUERY = "SELECT * FROM sys_config WHERE name = 'last_patch'";
    private final String LAST_PATCH_UPDATE_QUERY = "UPDATE sys_config SET val = ? WHERE name = 'last_patch'";

    private Connection connection;

    @Autowired
    private DataSource dataSource;

    public DbUpdaterImpl() {
    }

    @Override
    @PostConstruct
    public void run() {
        System.out.println("Executing patches...");
        try {
            connection = dataSource.getConnection();
            int last = 0;
            try {
                last = getCurrentPatchNo();
            } catch (SQLException e) {
                String message = e.getMessage();
                if (message.contains("relation \"sys_config\" does not exist")) {
                    InputStream input = getCurrentPatch(last);
                    if (input != null) {
                        runPatch(last, input);
                        last++;
                    }
                }
            }
            for (int i = last; i < 100; i++) {
                InputStream input = getCurrentPatch(i);
                if (input != null) {
                    runPatch(i, input);

                } else {
                    break;
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } catch (IOException e) {
            LOGGER.error(e);
        } finally {
            close(connection);
        }
    }


    private void runPatch(int i, InputStream input) throws UnsupportedEncodingException, IOException, SQLException {
        String patchNo = String.format("%05d", i);
        String fullName = PATH + PATCH_PREFIX + patchNo + PATCH_POSTFIX;
        BufferedReader in = new BufferedReader(new InputStreamReader(input, "UTF8"));

        String queries;
        StringBuilder sb = new StringBuilder();
        while ((queries = in.readLine()) != null) {
            sb.append(queries);
            sb.append(" ");
            System.out.println(queries);
        }
        Statement stmt = connection.createStatement();
        stmt.execute(sb.toString());
        stmt.close();
        saveLastPatch(i);
    }

    private void saveLastPatch(int i) throws SQLException {
        PreparedStatement prepareStatement = connection.prepareStatement(LAST_PATCH_UPDATE_QUERY);
        prepareStatement.setString(1, String.valueOf(i));
        prepareStatement.execute();
    }

    private InputStream getCurrentPatch(int i) {
        String patchNo = String.format("%05d", i);
        String fullName = PATH + PATCH_PREFIX + patchNo + PATCH_POSTFIX;
        InputStream input = this.getClass().getResourceAsStream(fullName);
        return input;
    }

    private int getCurrentPatchNo() throws SQLException {
        Statement createStatement = connection.createStatement();
        ResultSet result = createStatement.executeQuery(LAST_PATCH_SELECT_QUERY);
        result.next();
        String lastPatch = result.getString("val");
        int lastPatchNo = Integer.parseInt(lastPatch);
        int i = lastPatchNo + 1;
        createStatement.close();
        return i;
    }

    private void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

}
