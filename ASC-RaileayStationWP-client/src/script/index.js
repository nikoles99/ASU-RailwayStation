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
        "<td>Маршрут</td> " +
        "<td>Станция отправления</td> " +
        "<td>Станция прибытия</td> " +
        "<td>Время отправления</td>" +
        "<td>Вермя прибытия</td>" +
        "<td>Вагоны</td>" +
        "</tr>";
    $("#trains").append(trainsHeader);
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
                    "<table>" +
                    "<tr><td>Купе:</td><td><a href=\"authorization.html\">" + coups + "</a></td></tr>" +
                    "<tr><td>Плацкарт:</td><td><a href=\"authorization.html\">" + reservedSeats + "</a></td></tr>" +
                    "<tr><td>Сидячие:</td><td><a href=\"authorization.html\">" + seatPlaces + "</a></td></tr>" +
                    "<tr><td>Общий:</td><td><a href=\"authorization.html\">" + common + "</a></td></tr>" +
                    "</table>" +
                    "</td></tr>";
                $("#trains tr:last").after(newStation);
            });
    }));
}

function getFreePlaces(trainId, carriageType) {
    var departureDate = strToDate($("#date_departure").val());
    var arrivalDate = strToDate($("#date_arrival").val());
    var freePlacesPromise = getFreePlacesByType(trainId, carriageType, departureDate, arrivalDate);
    return freePlacesPromise.then(function (places) {
        return places.length;
    })
}

function getSchedule(schedules, stationName) {
    var stationPromise = getStationByName(stationName);
    return stationPromise.then(function (station) {
        for (var i = 0; i < schedules.length; i++) {
            var stationId = schedules[i].stationId;
            if (stationId == station.id) {
                return schedules[i];
            }
        }
        throw new Error('Illegal State: schedule not found');
    });
}


