var COMMON_CARRIAGE = "common";
var RESERVED_SEATS_CARRIAGE = "reservedSeat";
var COUP_CARRIAGE = "coup";
var SEAT_PLACES_CARRIAGE = "seatPlaces";

function getTrainsByStations(departureStation, arrivalStation, callback) {
    if (departureStation == "" || arrivalStation == "") {
        alert('Маршрут введен не корректно');
        return;
    }
    var url = "http://localhost:8080/getTrainsByStations";
    $.ajax({
        url: url,
        method: 'POST',
        dataType: 'json',
        data: {departureStation: departureStation, arrivalStation: arrivalStation},
        success: callback,
        error: function (xhr) {
            errorLogging(xhr);
        }
    });
}

function getTrainsByParams(departureStation, departureDate, arrivalStation, arrivalDate, callback) {
    if (departureStation == "" || arrivalStation == "" || arrivalDate == "" || departureDate == "") {
        alert('Маршрут введен не корректно');
        return;
    }
    var url = "http://localhost:8080/getTrainsByParams";
    $.ajax({
        url: url,
        method: 'POST',
        dataType: 'json',
        data: {departureStation: departureStation, arrivalStation: arrivalStation, departureDate: departureDate, arrivalDate: arrivalDate},
        success: callback,
        error: function (xhr) {
            errorLogging(xhr);
        }
    });
}

function saveTrain(train) {
    if (train != null) {
        var url = "http://localhost:8080/addTrain";
        $.ajax({
            url: url,
            method: 'POST',
            dataType: 'json',
            xhrFields: {withCredentials: true},
            data: JSON.stringify(train),
            error: function (xhr) {
                errorLogging(xhr);
            }
        });
    }
}
