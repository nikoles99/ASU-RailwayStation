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


function fillTrains(departureStation, arrivalStation, trains) {
    $("#trains tr").remove();
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
    $.each(trains, (function (index, train) {
        var arrivalStation = $("#station_to").val();
        var departureStation = $("#station_from").val();
        var newStation = "<tr>" +
            "<td>" + train.id + "</td>" +
            "<td>" + train.name + "</td>" +
            "<td>" + departureStation + "</td>" +
            "<td>" + arrivalStation + "</td>" +
            "<td>" + dateToString(new Date(departureDate)) + "</td>" +
            "<td>" + dateToString(new Date(arrivalDate)) + "</td>";
        "</tr>";
        $("#trains tr:last").after(newStation);
    }));
    $("#trains").css("visibility", "visible");
}


function getStationIdByName(name){

}
