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

function bookTicket(ticket) {
    var url = "http://localhost:8080/bookTicket";
    return $.ajax({
        url: url,
        method: 'POST',
        dataType: 'json',
        xhrFields: {withCredentials: true},
        data: ticket,
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
        xhrFields: {withCredentials: true}
    });
}


function cancelBookPlace(ticketId) {
    var url = "http://localhost:8080/removeTicket";
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: {ticketId: ticketId},
        xhrFields: {withCredentials: true},
        success: function () {
            setTickets();
        },
        error: function (error) {
            errorLogging(error);
        }
    });
}
