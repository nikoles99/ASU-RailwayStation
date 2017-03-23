/**
 * Created by nolesuk on 23-Mar-17.
 */

$("document").ready(function () {
    addStationsInDataList($("#stationsDataList"));
    setTodayDate($("#date_departure"));
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
$("#searchTrains").click(function () {
    var arrivalStation = $("#station_to").val();
    var departureStation = $("#station_from").val();
    var arrivalDate = strToDate($("#date_arrival").val());
    var departureDate = strToDate($("#date_departure").val());
    var isValidate = validate(departureStation, departureDate, arrivalStation, arrivalDate);
    if (isValidate) {
        getTrainsByParams(departureStation, departureDate, arrivalStation, arrivalDate, function (data) {
            showTrains(data);
        });
    }
});

function showTrains(data) {
    alert(data);
}

