/**
 * Created by nolesuk on 07-Mar-17.
 */
var reservedSeatPlacesCount = 54;
var coupPlacesCount = 36;
var commonPlacesCount = 81;
var seatPlacesCount = 42;
var stationsArray = [];
var trainSpeed = 60;

var carriageNumber = 0;

var train;

function refreshTrain() {
    train = {
        name: "",
        carriages: [],
        schedules: []
    }
}

$("#save").click(function () {
    if (train != null) {
        var url = "http://localhost:8080/addNewRoute";
        console.log(JSON.stringify(train));
        $.ajax({
            url: url,
            method: 'POST',
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(train),
            success: function (data) {
                $("#nameStation").val('');
                getStationsFromServer();
            },
            error: function (xhr) {
                errorLogging(xhr);
            }
        });
    }
});

$("document").ready(function () {
    getStationsFromServer();
    var date = new Date($.now());
    date.setDate(date.getDate() + 1);
    var departureDate = dateToString(date);
    $("#departureDate").val(departureDate);

});


$("#stations").on("click", ".removeStationFromRoute", function () {
    $(this).closest('tr').remove();
    $("#addStationToRoute").prop('disabled', false);
});
$("#nameSearch").on("change paste keyup", function () {
    var length = $("#nameSearch").val().length;
    var isDisables = length >= 0 ? false : true;
    $("#search").prop('disabled', isDisables);
});

$("#search").click(function () {
    var station = $("#nameSearch").val();
    var split = station.split("-");
    if (split.length != 2) {
        alert("Не верно введен маршрут, Пример ввода: Минск-Гродно");
        return;
    }
    getTrainsByStationName(split[0], split[1]);
});

function getTrainsByStationName(departureStation, arrivalStation) {
    var url = "http://localhost:8080/getTrainsByRoute";
    var ajax = $.ajax({
        url: url,
        method: 'POST',
        dataType: 'json',
        data: {departureStation: departureStation, arrivalStation: arrivalStation},
        success: function (data) {
            fillTrains(data);
        },
        error: function (xhr) {
            errorLogging(xhr);
        }
    });
    return ajax;
}

function fillTrains(data) {
   $("#trains tr").remove();
    var trainsHeader = "<tr>" +
        "<td>Номер поезда</td>" +
        "<td>Маршрут</td> " +
        "<td>Время отправления</td>" +
        "<td>Вермя прибытия</td>" +
        "</tr>"
    $("#trains").append(trainsHeader);
    $.each(data, (function (index, train) {
        var arrivalDate = train.schedules[0].arrivalDate;
        var departureDate = train.schedules[train.schedules.length - 1].arrivalDate;
        var newStation = "<tr>" +
            "<td>" + train.id + "</td>" +
            "<td>" + train.name + "</td>" +
            "<td>" + dateToString(new Date(departureDate)) + "</td>" +
            "<td>" + dateToString(new Date(arrivalDate)) + "</td>"
        "</tr>";
        $("#trains tr:last").after(newStation);
    }));
    $("#trains").css("visibility", "visible");
}

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

$("#buildRoute").click(function () {
    refreshTrain();
    var isRouteValidated = validateRoute();
    var isCarriagesValidate = validateCarriages();

    var isValidationComplete = isRouteValidated && isCarriagesValidate;
    var lastIndex = parseInt(train.schedules.length - 1);
    var route = $("#stations tr:eq(0) td:eq(1)").find("input").val() + " - " + $("#stations tr:eq(" + lastIndex + ") td:eq(1)").find("input").val();
    if (isValidationComplete) {
        train.name = route;
        $("#trainRoute").val(route);
        var arrivalDate = train.schedules[train.schedules.length - 1].arrivalDate;
        var date = dateToString(arrivalDate);
        $("#arrivalDate").val(date);
    }
    $("#save").prop('disabled', !isValidationComplete);
});

function dateToString(date) {
    var day = correctDate(date.getDate());
    var year = date.getFullYear();
    var month = correctDate(date.getMonth() + 1);
    var hours = correctDate(date.getHours());
    var minutes = correctDate(date.getMinutes());
    return year + "-" + month + "-" + day + "T" + hours + ":" + minutes;
}

function correctDate(number) {
    if (number < 10 && number >= 0) {
        number = "0" + number;
    }
    return number;
}

function validateRoute() {
    var departureDateText = $("#departureDate").val();
    var departureDate = new Date(departureDateText+"+03:00");
    if (departureDateText == "" || departureDate < new Date($.now())) {
        alert("проверьте правильность ввода даты отправления");
        return false;
    }
    stationsArray = [];
    $("#stations tr").each(function () {
        var isStationValidate = false;
        var station = this.getElementsByClassName("station")[0].value;
        var distance = this.getElementsByClassName("distance")[0].value;

        $("#stationsDataList option").each(function () {
            if (this.value == station) {
                isStationValidate = true;
            }
        });
        if (station == "") {
            alert("выберите станцию");
            return false;
        }

        var arrivalDate = new Date(departureDate);
        arrivalDate.setMinutes(arrivalDate.getMinutes() - 10);

        if (isStationValidate) {
            var stationId = getStationId(station);
            stationsArray.push({stationId: stationId, arrivalDate: arrivalDate, departureDate: new Date(departureDate)});
        }
        else {
            return !isStationValidate;
        }

        if (distance == "" || distance < 1 || countReservedSeat > 200) {
            alert("проверьте правильность ввода расстояния до следующей станции");
            return false;
        } else {
            var allMinutes = parseInt(distance) / trainSpeed * 60;
            var hours = parseInt(allMinutes / 60);
            var minutes = allMinutes - hours * 60;
            departureDate.setMinutes(departureDate.getMinutes() + minutes);
            departureDate.setHours(departureDate.getHours() + hours);
        }

    });
    train.schedules = stationsArray;
    return true;
}


function validateCarriages() {
    var countCarriages = parseInt($("#countCarriages").val());
    if (countCarriages == "" || countCarriages < 10 || countCarriages > 30) {
        alert("countCarriages число вагонов между 10 и 30");
        return false;
    }

    var commonCarriages = $("#commonCarriages").val();
    if (commonCarriages == "" || commonCarriages < 2 || commonCarriages > countCarriages) {
        alert("commonCarriages число вагонов между 2 и 10");
        return false;
    }

    addCarriage("common", commonCarriages, commonPlacesCount);

    var countReservedSeat = $("#countReservedSeat").val();
    if (countReservedSeat == "" || countReservedSeat < 2 || countReservedSeat > countCarriages) {
        alert("countReservedSeat число вагонов между 10 и 30");
        return false;
    }

    addCarriage("reservedSeat", countReservedSeat, reservedSeatPlacesCount);

    var countCoups = $("#countCoups").val();
    if (countCoups == "" || countCoups < 2 || countCoups > countCarriages) {
        alert("countCoups число вагонов между 10 и 30");
        return false;
    }

    addCarriage("coup", countCoups, coupPlacesCount);

    var countSeatPlaces = $("#countSeatPlaces").val();
    if (countSeatPlaces == "" || countSeatPlaces < 2 || countSeatPlaces > countCarriages) {
        alert("countSeatPlaces число вагонов между 10 и 30");
        return false;
    }

    addCarriage("seatPlaces", countSeatPlaces, seatPlacesCount);

    var countAllCarriages = parseInt(commonCarriages) + parseInt(countReservedSeat) + parseInt(countCoups) + parseInt(countSeatPlaces);
    if (countCarriages != countAllCarriages) {
        alert("сумма вагонов не равно общему числу вагонов");
        return false;
    }
    return true;
}

function addCarriage(type, countCarriages) {
    for (var i = 0; i < countCarriages; i++) {
        train.carriages.push({carriageType: type, number: ++carriageNumber});
    }
}


$("#addStationToRoute").click(function () {
    if ($('#stations tr').length > 10) {
        $("#addStationToRoute").prop('disabled', true);
    } else {
        var newStation = "<tr>" +
            "<td>Станция</td>" +
            "<td><input type=\"text\" class=\"station\" list=\"stationsDataList\"/></td>" +
            "<td>Расстояние до следующей станции(км)</td>" +
            "<td><input type=\"number\" class=\"distance\" min=\"1\" max =\"200\"></td>" +
            "<td><input class=\"removeStationFromRoute\" type=\"button\" value=\"-\"></td></tr>";
        $("#stations tr:last").after(newStation);
    }
});


function setDisableRouteDiv(isDisable) {
    $("#route:not(:last-child) :input").prop('disabled', isDisable);
}


$("#nameStation").on("change paste keyup", function () {
    var length = $("#nameStation").val().length;
    var isDisables = length >= 4 && length < 20 ? false : true;
    $("#addStation").prop('disabled', isDisables);
    $("#deleteStation").prop('disabled', isDisables);
    $("#updateStation").prop('disabled', isDisables);
});


$("#addStation").click(function () {
    var url = "http://localhost:8080/addStation";
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'jsonp',
        data: {name: $("#nameStation").val()},
        success: function (data) {
            $("#nameStation").val('');
            getStationsFromServer();
        },
        error: function (xhr) {
            errorLogging(xhr);
        }
    });
});

function errorLogging(xhr) {
    var text = xhr.code;
    console.log("readyState: " + xhr.readyState);
    console.log("responseText: " + xhr.responseText);
    console.log("status: " + xhr.status);
    console.log("text status: " + textStatus);
    console.log("error: " + err);
}


$("#deleteStation").click(function () {
    var url = "http://localhost:8080/deleteStation";
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'jsonp',
        data: {name: $("#nameStation").val()},
        success: function (data) {
            $("#nameStation").val('');
            getStationsFromServer();
        },
        error: function (xhr) {
            errorLogging(xhr);
        }
    });
});

function getStationsFromServer() {
    var url = "http://localhost:8080/getAllStations";
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'jsonp',
        success: function (data) {
            $("#stationsDataList").empty();
            for (var i in data) {
                $("<option/>").html(data[i].name).appendTo("#stationsDataList");
                $("<span/>").html(data[i].id).appendTo("#stationsDataList");
            }
        },
        error: function (xhr) {
            errorLogging(xhr);
        }
    });

}

function getStationId(name) {
    var stationId = 0;
    $("#stationsDataList option").each(function () {
        if (this.value == name) {
            stationId = parseInt($(this).next("span").text());
            return;
        }
    });
    return stationId;
}
