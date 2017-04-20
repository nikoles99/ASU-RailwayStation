/**
 * Created by nikita on 18.03.17.
 */
function errorLogging(xhr) {
    var error = xhr.responseJSON;
    if (error != null) {
        error = error.message;
    } else {
        error = xhr.statusText;
    }
    alert(error);
    console.log("code: " + xhr.code);
}