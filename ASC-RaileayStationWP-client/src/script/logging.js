/**
 * Created by nikita on 18.03.17.
 */
function errorLogging(xhr) {
    var text = xhr.code;
    console.log("readyState: " + xhr.readyState);
    console.log("responseText: " + xhr.responseText);
    console.log("status: " + xhr.status);
    console.log("text status: " + textStatus);
    console.log("error: " + err);
}