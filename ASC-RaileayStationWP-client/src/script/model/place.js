/**
 * Created by nolesuk on 27-Mar-17.
 */
function getFreePlacesByType(trainId, carriageType, departureDate, arrivalDate, callback) {
    var url = "http://localhost:8080/getFreePlacesByType";
    return $.ajax({
        url: url,
        method: 'POST',
        dataType: 'json',
        data: {trainId: trainId, carriageType: carriageType, departureDate: departureDate, arrivalDate: arrivalDate},
        success: callback,
        error: function (xhr) {
            errorLogging(xhr);
        }
    });
}

function bookTickets(tickets) {
    var url = "http://localhost:8080/bookTickets";
    return $.ajax({
        url: url,
        method: 'POST',
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        xhrFields: {withCredentials: true},
        data: JSON.stringify(tickets),
        success: function () {
            alert("Места успешно забронированы, подробную информацию вы можете просмотреть в личном кабинете");
        },
        error: function (xhr) {
            errorLogging(xhr);
        }
    });
}

function getTickets() {
    var url = "http://localhost:8080/getBookedTickets";
    return $.ajax({
        url: url,
        method: 'POST',
        dataType: 'json',
        xhrFields: {withCredentials: true},
        error: function (xhr) {
            errorLogging(xhr);
        }
    });
}


function cancelBookPlace(ticket, success) {
    var url = "http://localhost:8080/removeTicket";
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: ticket,
        xhrFields: {withCredentials: true},
        success: success,
        error: function (error) {
            errorLogging(error);
        }
    });
}