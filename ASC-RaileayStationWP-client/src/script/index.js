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
        var arrivalStation = $("#station_to").val();
        var departureStation = $("#station_from").val();
        $.when(
            getSchedule(train.schedules, departureStation),
            getSchedule(train.schedules, arrivalStation))
            .then(function (departureSchedule, arrivalSchedule) {
                var newStation = "<tr>" +
                    "<td>" + train.id + "</td>" +
                    "<td>" + train.name + "</td>" +
                    "<td>" + departureStation + "</td>" +
                    "<td>" + arrivalStation + "</td>" +
                    "<td>" + dateToString(new Date(departureSchedule.departureDate)) + "</td>" +
                    "<td>" + dateToString(new Date(arrivalSchedule.arrivalDate)) + "</td>";
                "</tr>";
                $("#trains tr:last").after(newStation);
            });
    }));
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


