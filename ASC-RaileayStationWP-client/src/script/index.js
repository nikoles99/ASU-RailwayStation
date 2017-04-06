/**
 * Created by nolesuk on 23-Mar-17.
 */

$("document").ready(function () {
    addStationsInDataList($("#stationsDataList"));
    setTodayDate($("#date_departure"));
    setTodayDate($("#date_arrival"));
});


function validate(departureStation, departureDate, arrivalStation, arrivalDate) {
    if (departureStation == "" || arrivalStation == "") {
        alert("Выберите станцию");
        return false;
    }
    if (departureStation == arrivalStation) {
        alert("Станции не должны совпадать");
        return false;
    }
    if (departureDate >= arrivalDate) {
        alert("Даты введены не верно");
        return false;
    }
    return true;
}


$("#station_to").click(function () {
    $(this).val('');
});

$("#station_from").click(function () {
    $(this).val('');
});


$("#searchTrains").click(function () {
    var arrivalStation = $("#station_to").val();
    var departureStation = $("#station_from").val();
    var arrivalDate = strToDate($("#date_arrival").val());
    var departureDate = strToDate($("#date_departure").val());
    var isValidate = validate(departureStation, departureDate, arrivalStation, arrivalDate);
    if (isValidate) {
        getTrainsByParams(departureStation, departureDate, arrivalStation, arrivalDate, function (trains) {
            fillTrains(departureStation, arrivalStation, trains);
        });
    }
});


function appendTrainsTableHeader() {
    var trainsHeader = "<tr>" +
        "<td>Номер поезда</td>" +
        "<th>Маршрут</th> " +
        "<th>Станция отправления</th> " +
        "<th>Станция прибытия</th> " +
        "<th>Время отправления</th>" +
        "<th>Вермя прибытия</th>" +
        "<th>Вагоны</th>" +
        "</tr>";
    $("#trains tbody:last-child").append(trainsHeader);
}

function fillTrains(departureStation, arrivalStation, trains) {
    $("#trains tr").remove();
    appendTrainsTableHeader();
    $("#trains").css("visibility", "visible");

    $.each(trains, (function (index, train) {
        $.when(
            getSchedule(train.schedules, departureStation),
            getSchedule(train.schedules, arrivalStation),
            getFreePlaces(train.id, "COUP"),
            getFreePlaces(train.id, "RESERVED_SEAT"),
            getFreePlaces(train.id, "SEAT_PLACE"),
            getFreePlaces(train.id, "COMMON"))
            .then(function (departureSchedule, arrivalSchedule, coups, reservedSeats, seatPlaces, common) {
                var newStation = "<tr>" +
                    "<td>" + train.id + "</td>" +
                    "<td>" + train.name + "</td>" +
                    "<td>" + departureStation + "</td>" +
                    "<td>" + arrivalStation + "</td>" +
                    "<td>" + dateToString(new Date(departureSchedule.departureDate)) + "</td>" +
                    "<td>" + dateToString(new Date(arrivalSchedule.arrivalDate)) + "</td>" +
                    "<td>" +
                    "<table><tbody>" +
                    "<tr><td>Купе:</td><td>" +
                    "<a href=" + getChoosePlaceUrl(train.id, train.name, COUP_CARRIAGE, coups) + ">" + coups.length + "</a>" +
                    "</td></tr>" +
                    "<tr><td>Плацкарт:</td><td>" +
                    "<a href=" + getChoosePlaceUrl(train.id, train.name, RESERVED_SEATS_CARRIAGE, coups) + ">" + reservedSeats.length + "</a>" +
                    "</td></tr>" +
                    "<tr><td>Сидячие:</td><td>" +
                    "<a href=" + getChoosePlaceUrl(train.id, train.name, SEAT_PLACES_CARRIAGE, seatPlaces) + ">" + seatPlaces.length + "</a>" +
                    "</td></tr>" +
                    "<tr><td>Общий:</td><td>" +
                    "<a href=" + getChoosePlaceUrl(train.id, train.name,COMMON_CARRIAGE, common) + ">" + common.length + "</a>" +
                    "</td></tr>" +
                    "</tbody></table>" +
                    "</td>" +
                    "</tr>";
                $("#trains").append(newStation);
            });
    }));
}

function getChoosePlaceUrl(trainId, trainName, carriageType, places) {
    var arrivalStation = $("#station_to").val();
    var departureStation = $("#station_from").val();
    var arrivalDate = $("#date_arrival").val();
    var departureDate = $("#date_departure").val();
    return "javascript:window.location.href='choose_place.html?" +
        "trainId=" + trainId +
        "&trainName=" + trainName +
        "&departureStation=" + departureStation +
        "&arrivalStation=" + arrivalStation +
        "&arrivalDate=" + arrivalDate +
        "&departureDate=" + departureDate +
        "&places=" + JSON.stringify(places) +
        "&carriageType=" + carriageType + "'";
}
function getFreePlaces(trainId, carriageType) {
    var departureDate = strToDate($("#date_departure").val());
    var arrivalDate = strToDate($("#date_arrival").val());
    var freePlacesPromise = getFreePlacesByType(trainId, carriageType, departureDate, arrivalDate);
    return freePlacesPromise.then(function (places) {
        return places;
    })
}

function getSchedule(schedules, stationName) {
    var stationPromise = getStationByName(stationName);
    return stationPromise.then(function (station) {
        for (var i = 0; i < schedules.length; i++) {
            var stationId = schedules[i].stationId;
            if (stationId == station[0].id) {
                return schedules[i];
            }
        }
        throw new Error('Illegal State: schedule not found');
    });
}


