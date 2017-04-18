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
    $("#tickets").append(header);
    var ticketsPromise = getTickets();
    ticketsPromise.then(function (tickets) {
        for (var i = 0; i < tickets.length; i++) {
            var placeNumber = tickets[i].placeNumber;
            var carriageType = tickets[i].carriageType;
            var carriageNumber = tickets[i].carriageNumber;
            var arrivalDate = tickets[i].arrivalDate;
            var departureDate = tickets[i].departureDate;
            var arrivalStation = tickets[i].arrivalStation;
            var departureStation = tickets[i].departureStation;
            var id = place.carriageId;
            if (carriageId != "" && carriageId === id) {
                continue;
            }
            var placesColumn = getPlacesByCarriageId(places, id);
            var carriagePromise = getCarriageById(id);
            carriagePromise.then(function (carriage) {
                var tr = "<tr>" +
                    "<td>" + carriage.number + "</td>" +
                    "<td>" + placesColumn + "</td>" +
                    "</tr>";
                $("#free_places").append(tr);
            });
            carriageId = id;
        }
    });
}