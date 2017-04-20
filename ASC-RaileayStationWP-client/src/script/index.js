/**
 * Created by nolesuk on 23-Mar-17.
 */

$("document").ready(function () {
    addStationsInDataList($("#stationsDataList"));
    setTodayDate($("#date_departure"));
    setTodayDate($("#date_arrival"));
    $("#choosePlaces").load("choose_place.html #choosePlaces");
    $("#registration").load("registration.html #registration");
    updateAuthorizationForm();
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

$("#login").on("change paste keyup", function () {
    setEnterBtnEnable();
});

$("#password").on("change paste keyup", function () {
    setEnterBtnEnable();
});

$("#enter").click(function () {
    var authenticationPromise = authentication($("#login").val(), $("#password").val());
    authenticationPromise.then(function () {
        updateAuthorizationForm();
    })
});


$("#showRegistrationDialog").click(function () {
    $('#registration').modal();
});

$("#logout").click(function () {
    logout();
    updateAuthorizationForm();
});

$("#station_to").click(function () {
    $(this).val('');
});

$("#station_from").click(function () {
    $(this).val('');
});


function searchTrains() {
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
}

$("#searchTrains").click(function () {
    searchTrains();
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

function updateAuthorizationForm() {
    var authorize = isAuthorize();
    authorize.then(function (data) {
        if (data) {
            $("#authorization_form").hide();
            $("#login_message").text("Вы вошли под логином: " + data.login);
            var href;
            if (data.login == "admin") {
                href = 'administration.html';
            } else {
                href = 'cabinet.html';
            }
            $("#cabinet").attr('href', href);
            $("#logout_form").show();
        } else {
            $("#authorization_form").show();
            $("#logout_form").hide();
        }
    });
}

$("#cabinet").click(function () {
    //setTickets();
});

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
                var departureDate = dateToString(new Date(departureSchedule.departureDate));
                var arrivalDate = dateToString(new Date(arrivalSchedule.arrivalDate));
                var newStation = "<tr>" +
                    "<td>" + train.id + "</td>" +
                    "<td>" + train.name + "</td>" +
                    "<td>" + departureStation + "</td>" +
                    "<td>" + arrivalStation + "</td>" +
                    "<td>" + departureDate + "</td>" +
                    "<td>" + arrivalDate + "</td>" +
                    "<td>" +
                    "<table><tbody>" +
                    "<tr><td>Купе:</td><td>" +
                    "<a " + setHrefData(train.id, train.name, COUP_CARRIAGE, coups, arrivalDate, departureDate) + ">" + coups.length + "</a>" +
                    "</td></tr>" +
                    "<tr><td>Плацкарт:</td><td>" +
                    "<a " + setHrefData(train.id, train.name, RESERVED_SEATS_CARRIAGE, reservedSeats, arrivalDate, departureDate) + ">" + reservedSeats.length + "</a>" +
                    "</td></tr>" +
                    "<tr><td>Сидячие:</td><td>" +
                    "<a " + setHrefData(train.id, train.name, SEAT_PLACES_CARRIAGE, seatPlaces, arrivalDate, departureDate) + ">" + seatPlaces.length + "</a>" +
                    "</td></tr>" +
                    "<tr><td>Общий:</td><td>" +
                    "<a " + setHrefData(train.id, train.name, COMMON_CARRIAGE, common, arrivalDate, departureDate) + ">" + common.length + "</a>" +
                    "</td></tr>" +
                    "</tbody></table>" +
                    "</td>" +
                    "</tr>";
                $("#trains").append(newStation);
            });
    }));
}

function setHrefData(trainId, trainName, carriageType, places, arrivalDate, departureDate) {
    return "class=\"freePlaces\" data-train-id=\"" + trainId + "\" " +
        "data-train-name=\"" + trainName + "\" " +
        "data-carriage-type=\"" + carriageType + "\" " +
        "data-arrivalDate=\"" + arrivalDate + "\" " +
        "data-departureDate=\"" + departureDate + "\" " +
        "data-free-places='" + JSON.stringify(places) + "'";
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

function setEnterBtnEnable() {
    var loginLength = $("#login").val().length;
    var passwordLength = $("#password").val().length;
    var isValidate = passwordLength >= 4 && loginLength >= 4;
    $("#enter").prop('disabled', !isValidate);
}




