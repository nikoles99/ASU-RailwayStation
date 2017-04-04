/**
 * Created by nolesuk on 04-Apr-17.
 */
function getCarriageById(id, callback) {
    var url = "http://localhost:8080/getCarriageById";
    return $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: {id: id},
        success: callback,
        error: function (error) {
            errorLogging(error);
        }
    });
}
