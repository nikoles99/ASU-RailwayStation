/**
 * Created by nolesuk on 07-Mar-17.
 */
var train;

$("document").ready(function () {
    addStationsInDataList($("#stationsDataList"));
    setTodayDate($("#departureDate"));
});


$("#stations").on("change", ".station", function () {
    var station = $(this).val();
    var isFindDuplicate = false;

    $("#stations .station").each(function () {
        if (this.value == station) {
            if (isFindDuplicate) {
                $(this).val("");
                alert("Станции в маршруте не должны совпадать");
                return;
            }
            isFindDuplicate = true;
        }
    });
});


$("#addStation").click(function () {
    addStation($("#nameStation").val(), function (data) {
        $("#nameStation").val('');
        addStationsInDataList($("#stationsDataList"));
    });
});


$("#deleteStation").click(function () {
    deleteStation($("#nameStation").val(), function () {
        $("#nameStation").val('');
        addStationsInDataList($("#stationsDataList"));
    })
});


$("#buildRoute").click(function () {
    var isCarriagesValidate = validateCarriages();
    var isRouteValidated = validateRoute();
    var isValidationComplete = isRouteValidated && isCarriagesValidate;
    if (isValidationComplete) {
        train = getTrain();
        var lastScheduleIndex = parseInt(train.schedules.length - 1);
        var route = $("#stations tr:eq(0) td:eq(1)").find("input").val() + "-" + $("#stations tr:eq(" + lastScheduleIndex + ") td:eq(1)").find("input").val();
        $("#trainRoute").val(route);
        var arrivalDate = train.schedules[train.schedules.length - 1].arrivalDate;
        var arrivalDateStr = dateToString(arrivalDate);
        $("#arrivalDate").val(arrivalDateStr);
    }
    $("#save").prop('disabled', !isValidationComplete);
});


$("#stations").on("click", ".removeStationFromRoute", function () {
    $(this).closest('tr').remove();
    $("#addStationToRoute").prop('disabled', false);
});


$("#nameSearch").on("change paste keyup", function () {
    var length = $("#nameSearch").val().length;
    var isDisables = length < 0;
    $("#search").prop('disabled', isDisables);
});


$("#search").click(function () {
    var station = $("#nameSearch").val();
    var split = station.split("-");
    if (split.length != 2) {
        alert("Не верно введен маршрут, Пример ввода: Минск-Гродно");
        return;
    }
    getTrainsByStations(split[0], split[1], function (data) {
        fillTrains(data);
    });
});


$("#save").click(function () {
    var train = getTrain();
    saveTrain(train);
    $(this).prop('disabled', true);
});


$("#nameStation").on("change paste keyup", function () {
    var length = $("#nameStation").val().length;
    var isDisables = length >= 4 && length < 20 ? false : true;
    $("#addStation").prop('disabled', isDisables);
    $("#deleteStation").prop('disabled', isDisables);
    $("#updateStation").prop('disabled', isDisables);
});


$("#addStationToRoute").click(function () {
    if ($('#stations tr').length > 10) {
        $("#addStationToRoute").prop('disabled', true);
    } else {
        var newStation = "<tr>" +
            "<td>Станция</td>" +
            "<td><input type=\"text\" class=\"station\" list=\"stationsDataList\"/></td>" +
            "<td>Расстояние до следующей станции(км)</td>" +
            "<td><input type=\"number\" class=\"distance\" min=\"20\" max =\"200\"></td>" +
            "<td><input class=\"removeStationFromRoute\" type=\"button\" value=\"-\"></td></tr>";
        $("#stations tr:last").after(newStation);
    }
});


function fillTrains(data) {
    $("#trains tr").remove();
    var trainsHeader = "<tr>" +
        "<td>Номер поезда</td>" +
        "<td>Маршрут</td> " +
        "<td>Время отправления</td>" +
        "<td>Вермя прибытия</td>" +
        "</tr>";
    $("#trains").append(trainsHeader);
    $.each(data, (function (index, train) {
        var arrivalDate = train.schedules[0].arrivalDate;
        var departureDate = train.schedules[train.schedules.length - 1].departureDate;
        var newStation = "<tr>" +
            "<td>" + train.id + "</td>" +
            "<td>" + train.name + "</td>" +
            "<td>" + dateToString(new Date(departureDate)) + "</td>" +
            "<td>" + dateToString(new Date(arrivalDate)) + "</td>";
        "</tr>";
        $("#trains tr:last").after(newStation);
    }));
    $("#trains").css("visibility", "visible");
}


function validateDepartureDate() {
    var departureDateText = $("#departureDate").val();
    var departureDate = strToDate(departureDateText);

    if (departureDateText == "" || departureDate < new Date($.now())) {
        alert("проверьте правильность ввода даты отправления");
        return false;
    }
    return true;
}

function getTrain() {
    var train = {name: "", carriages: [], schedules: []};
    var schedules = [];
    var trainSpeed = 60;
    var departureDateText = $("#departureDate").val();
    var departureDate = strToDate(departureDateText);

    $("#stations tr").each(function () {
        var station = this.getElementsByClassName("station")[0].value;
        var distance = this.getElementsByClassName("distance")[0].value;

        var arrivalDate = new Date(departureDate);
        arrivalDate.setMinutes(arrivalDate.getMinutes() - 10);
        var stationId = getStationId(station);
        schedules.push({stationId: stationId, arrivalDate: arrivalDate, departureDate: new Date(departureDate)});

        var allMinutes = parseInt(distance) / trainSpeed * 60;
        var hours = parseInt(allMinutes / 60);
        var minutes = allMinutes - hours * 60;
        departureDate.setMinutes(departureDate.getMinutes() + minutes);
        departureDate.setHours(departureDate.getHours() + hours);
    });
    train.schedules = schedules;
    addCarriages(train);
    train.name = $("#trainRoute").val();
    return train;
}

function addCarriages(train) {
    var number = 0;

    var commonPlacesCount = 81;
    var commonCarriagesCount = $("#countCommon").val();
    addCarriage(train, COMMON_CARRIAGE, commonCarriagesCount, number, commonPlacesCount);

    var reservedSeatPlacesCount = 54;
    var reservedSeatCount = $("#countReservedSeat").val();
    addCarriage(train, RESERVED_SEATS_CARRIAGE, reservedSeatCount, number, reservedSeatPlacesCount);

    var coupPlacesCount = 36;
    var coupsCount = $("#countCoups").val();
    addCarriage(train, COUP_CARRIAGE, coupsCount, number, coupPlacesCount);

    var seatPlacesCount = 42;
    var seatCarriageCount = $("#countSeatPlaces").val();
    addCarriage(train, SEAT_PLACES_CARRIAGE, seatCarriageCount, number, seatPlacesCount);
}


function validateCarriages() {
    var countCarriages = parseInt($("#countCarriages").val());
    if (countCarriages == "" || countCarriages < 10 || countCarriages > 30) {
        alert("countCarriages число вагонов между 10 и 30");
        return false;
    }
    var countCommon = $("#countCommon").val();
    if (countCommon == "" || countCommon < 2 || countCommon > countCarriages) {
        alert("countCommon число вагонов между 2 и 10");
        return false;
    }
    var countReservedSeat = $("#countReservedSeat").val();
    if (countReservedSeat == "" || countReservedSeat < 2 || countReservedSeat > countCarriages) {
        alert("countReservedSeat число вагонов между 10 и 30");
        return false;
    }
    var countCoups = $("#countCoups").val();
    if (countCoups == "" || countCoups < 2 || countCoups > countCarriages) {
        alert("countCoups число вагонов между 10 и 30");
        return false;
    }
    var countSeatPlaces = $("#countSeatPlaces").val();
    if (countSeatPlaces == "" || countSeatPlaces < 2 || countSeatPlaces > countCarriages) {
        alert("countSeatPlaces число вагонов между 10 и 30");
        return false;
    }
    var countAllCarriages = parseInt(countCommon) + parseInt(countReservedSeat) + parseInt(countCoups) + parseInt(countSeatPlaces);
    if (countCarriages != countAllCarriages) {
        alert("сумма вагонов не равно общему числу вагонов");
        return false;
    }
    return true;
}

function validateRoute() {
    var isValidated = true;
    if (!validateDepartureDate()) {
        isValidated = false;
    }
    $("#stations tr").each(function () {
        var station = this.getElementsByClassName("station")[0].value;

        if (station == "") {
            alert("выберите станцию");
            isValidated = false;
            return isValidated;
        }
        var distance = this.getElementsByClassName("distance")[0].value;
        if (distance == "" || distance < 20 || distance > 200) {
            alert("проверьте правильность ввода расстояния до следующей станции");
            isValidated = false;
            return isValidated;
        }

        $("#stationsDataList option").each(function () {
            if (this.value == station) {
                isValidated = true;
                return isValidated;
            }
        });
    });
    return isValidated;
}


function addCarriage(train, type, countCarriages, number, countPlaces) {
    var carriage = {carriageType: type, number: number, places: []};
    addPlaces(carriage, countPlaces);
    for (var i = 0; i < countCarriages; i++) {
        carriage.number = ++number;
        train.carriages.push(carriage);
    }
}

function addPlaces(carriage, countPlaces) {
    for (var i = 1; i <= countPlaces; i++) {
        carriage.places.push({number: i});
    }
}


function getStationId(name) {
    var stationId = 0;
    $("#stationsDataList option").each(function () {
        if (this.value == name) {
            stationId = parseInt($(this).next("span").text());
        }
    });
    return stationId;
}



