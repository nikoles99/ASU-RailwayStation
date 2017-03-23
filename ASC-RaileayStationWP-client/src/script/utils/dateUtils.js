/**
 * Created by nolesuk on 23-Mar-17.
 */
function dateToString(date) {
    var day = correctDate(date.getDate());
    var year = date.getFullYear();
    var month = correctDate(date.getMonth() + 1);
    var hours = correctDate(date.getHours());
    var minutes = correctDate(date.getMinutes());
    return year + "-" + month + "-" + day + "T" + hours + ":" + minutes;
}

function strToDate(dateStr) {
    var date = new Date(dateStr + "+03:00");
    return date;
}


function correctDate(number) {
    if (number < 10 && number >= 0) {
        number = "0" + number;
    }
    return number;
}

function setTodayDate(widget) {
    var date = new Date($.now());
    date.setDate(date.getDate() + 1);
    var departureDate = dateToString(date);
    widget.val(departureDate);
}
