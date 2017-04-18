/**
 * Created by nikita on 18.04.17.
 */
$("document").ready(function () {
    setTickets();
});

function setTickets() {
    $("#tickets tr").remove();
    var header = " <tr>" +
        "<td>Станция отправления</td>" +
        "<td>Станция прибытия</td> " +
        "<td>Время отправления</td> " +
        "<td>Время прибытия</td>" +
        "<td>Время прибытия</td>" +
        "<td>Тип вагона</td>" +
        "<td>Номер вагона</td>" +
        "<td>Место</td>" +
        "<td></td></tr>";
    $("#tickets tbody:last-child").append(header);
    var ticketsPromise = getTickets();
    ticketsPromise.then(function (tickets) {
        for (var i = 0; i < tickets.length; i++) {
            var ticketId = tickets[i].id;
            var departureStation = tickets[i].departureStation;
            var arrivalStation = tickets[i].arrivalStation;
            var departureDate = dateToString(new Date(tickets[i].departureDate));
            var arrivalDate = dateToString(new Date(tickets[i].arrivalDate));
            var carriageType = tickets[i].carriageType;
            var carriageNumber = tickets[i].carriageNumber;
            var placeNumber = tickets[i].placeNumber;
            var tr = "<tr>" +
                "<td>" + departureStation + "</td>" +
                "<td>" + arrivalStation + "</td>" +
                "<td>" + departureDate + "</td>" +
                "<td>" + arrivalDate + "</td>" +
                "<td>" + carriageType + "</td>" +
                "<td>" + carriageNumber + "</td>" +
                "<td>" + placeNumber + "</td>" +
                "<td><button class=\"btn btn-danger btn-xs cancelBooking\" data-ticker-id=\""+ticketId+"\" type=\"button\">Отменить бронирование</button></td>" +
                "</tr>";
            $("#tickets tbody:last-child").append(tr);
        }
    });
}

$(document).on("click", ".btn.btn-danger.btn-xs.cancelBooking", function () {
    var tickerId = $(this).data("tickerId");
    cancelBookPlace(tickerId);
});