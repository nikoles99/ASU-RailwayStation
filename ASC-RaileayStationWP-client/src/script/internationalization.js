/**
 * Created by nolesuk on 22-Feb-17.
 */
var appLanguage = "rus";

function makeInternationalization() {
    $.ajax({
        url: 'languages/' + appLanguage + '.json',
        dataType: 'json',
        success: function (response) {
            $.each(response, function (key, value) {
                var element = document.getElementById(key);
                if (element != null) {
                    element.value = value;
                    element.innerHTML = value;
                }
            });
        }
    });
}
$("document ").ready(function () {
    makeInternationalization();
});
function setLanguage(language){
    appLanguage = language;
    makeInternationalization();
}
