package api.db;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nolesuk on 21-Feb-17.
 */
@Transactional(readOnly = true)
public interface DbUpdater {
    void run();
}
