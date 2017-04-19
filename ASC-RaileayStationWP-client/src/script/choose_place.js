/**
 * Created by nikita on 03.04.17.
 */
const ADULTS = "adults";
const CHILDREN_WITH_SEATS = "children_with_seats";
var countPassenger;
var ticketTemplate = {departureStation: "", departureDate: "", arrivalStation: "", arrivalDate: "", placeId: "", price: ""};
var chosenPlaces = [];

function fillForm() {
    chosenPlaces = [];
    countPassenger = 0;
    $('#places').prop('disabled', true);
    var freePlaces = $(this).closest('a');
    var trainName = freePlaces.data("trainName");
    var trainId = freePlaces.data("trainId");
    $("#trainName").text(trainId + " " + trainName);

    var arrivalStation = $("#station_to").val();
    var departureStation = $("#station_from").val();
    $("#route").text(departureStation + " - " + arrivalStation);

    var carriageType = freePlaces.data("carriageType");
    $("#carriage_type").text(carriageType);

    var arrivalDate = freePlaces.data("arrivaldate");
    $("#arrivalDate").text(arrivalDate);
    var departureDate = freePlaces.data("departuredate");
    $("#departureDate").text(departureDate);
    var freePlacesArray = freePlaces.data("freePlaces");

    ticketTemplate.trainId = trainId;
    ticketTemplate.arrivalStation = arrivalStation;
    ticketTemplate.departureStation = departureStation;
    ticketTemplate.arrivalDate = new Date(strToDate(arrivalDate));
    ticketTemplate.departureDate = new Date(strToDate(departureDate));
    setPlaces(freePlacesArray);
}
$("#trains").on("click", ".freePlaces", function () {
    var authorize = isAuthorize();
    fillForm.call(this);
    authorize.then(function (data) {
        if (data) {
            $("#last_name").val(data.lastName);
            $("#name").val(data.name);
            $('#choosePlaces').modal();
        } else {
            alert("Для того, чтобы продолжить необходимо пройти регистрацию или войти в в систему")
        }
    })

});

function book() {
    var tickets = [];
    for (var i = 0; i < chosenPlaces.length; i++) {
        var ticket = $.extend({}, ticketTemplate);
        var place = chosenPlaces[i];
        ticket.placeId = place.id;
        ticket.price = place.price;
        tickets.push(ticket);
    }
    bookTickets(tickets)
}


$(document).on("click", "#continue", function () {
    var isValidate = validateBookForm();
    if (isValidate) {
        book();
    }
});

$(document).on("change", "#childCount", function () {
    setPassengerCount();
});

function setPassengerCount() {
    var adultCount = $("#adultsCount").val();
    var childCount = $("#childCount").val();
    if (adultCount == "") {
        adultCount = 0;
    }
    if (childCount == "") {
        childCount = 0;
    }
    countPassenger = parseInt(adultCount) + parseInt(childCount);
}
$(document).on("change", "#adultsCount", function () {
    setPassengerCount();
});

function validateBookForm() {
    var adultsCount = $("#adultsCount").val();
    var childCount = $("#childCount").val();
    if (adultsCount < 1 || adultsCount > 2) {
        alert('Можно выбрать только до 2х полных билетов');
        return false;
    }
    if (childCount < 0 || childCount > 3) {
        alert('Можно выбрать только до 3х детских билетов');
        return false;
    }
    if (chosenPlaces.length != countPassenger) {
        alert('Число выбранных билетов не соответствует количеству пассажиров');
        return false;
    }
    return true;
}

function isPlaceContains(place) {
    for (var i = 0; i <= chosenPlaces.length; i++) {
        var chosenPlace = chosenPlaces[i];
        if (chosenPlace != null && place.id == chosenPlace.id) {
            return true;
        }
    }
    return false;
}

function remove(place) {
    for (var i = 0; i <= chosenPlaces.length; i++) {
        var chosenPlace = chosenPlaces[i];
        if (chosenPlace != null && place.id == chosenPlace.id) {
            chosenPlaces.splice(i, 1);
        }
    }

}

$(document).on("click", ".btn.btn-default.btn-xs.place", function () {
    if (countPassenger == 0) {
        alert("Выберите количество пассажиров");
        return;
    }
    var place = {id: "", price: ""};
    place.id = $(this).data("placeid");
    place.price = 100;
    var placeContain = isPlaceContains(place);
    if (placeContain) {
        $(this).css("background-color", "");
        remove(place);
    } else {
        if (chosenPlaces.length >= 5) {
            alert("максимальное количесто выбранных мест - (5)")
            return;
        }
        $(this).css("background-color", "#5cb85c");
        chosenPlaces.push(place);
    }
});

function getPlacesByCarriageId(places, carriageId) {
    var str1 = "";
    for (var i = 0; i < places.length; i++) {
        var place = places[i];
        if (carriageId === place.carriageId) {
            var placeUI = "<input class=\"btn btn-default btn-xs place\" type='button' value='" + place.number + "' data-placeId='" + place.id + "'/>";
            str1 = str1.concat(placeUI);
        }
    }
    return str1;
}

function setPlaces(places) {
    $("#free_places tr").remove();
    var header = "<tr><td>Номер вагона</td><td>Номера свободных мест</tr>";
    $("#free_places").append(header);
    var carriageId = "";
    for (var i = 0; i < places.length; i++) {
        var id = places[i].carriageId;
        if (carriageId != "" && carriageId === id) {
            continue;
        }
        var carriagePromise = getCarriageById(id);
        carriagePromise.then(function (carriage) {
            var placesColumn = getPlacesByCarriageId(places, carriage.id);
            var tr = "<tr>" +
                "<td>" + carriage.number + "</td>" +
                "<td>" + placesColumn + "</td>" +
                "</tr>";
            $("#free_places").append(tr);
        });
        carriageId = id;
    }
}

