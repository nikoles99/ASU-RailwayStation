/**
 * Created by nikita on 03.04.17.
 */
const ADULTS = "adults";
const CHILDREN_WITH_NO_SEATS = "children_with_no_seats";
const CHILDREN_WITH_SEATS = "children_with_seats";
var countPassenger = 3;
var chosenPlaces = [];

function fillForm() {
    chosenPlaces = [];
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

$(document).on("click", "#continue", function () {
    $("#last_name").val(data.lastName);
});

function isPlaceContains(place) {
    for (var i = 0; i <= chosenPlaces.length; i++) {
        var chosenPlace = chosenPlaces[i];
        if (place == chosenPlace) {
            return true;
        }
    }
    return false;
}

function remove(place) {
    for (var i = 0; i <= chosenPlaces.length; i++) {
        var chosenPlace = chosenPlaces[i];
        if (place == chosenPlace) {
            chosenPlaces.splice(i, 1);
        }
    }

}

$(document).on("click", ".btn.btn-default.btn-xs.place", function () {
    var place = $(this).val();
    var placeContain = isPlaceContains(place);
    if (placeContain) {
        $(this).css("background-color", "");
        remove(place);
    } else {
        if (chosenPlaces.length >= countPassenger) {
            alert("максимальное количесто выбранных мест (3)")
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
            var placeUI = "<input class=\"btn btn-default btn-xs place\" type='button' value='" + place.number + "' data='" + carriageId + "'/>";
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
        var place = places[i];
        var id = place.carriageId;
        if (carriageId != "" && carriageId === id) {
            continue;
        }
        var placesColumn = getPlacesByCarriageId(places, id);
        var carriagePromise = getCarriageById(id);
        carriagePromise.then(function (carriage) {
            var tr = "<tr>" +
                "<td>" + carriage.number + "</td>" +
                "<td>" + placesColumn + "</td>" +
                "</tr>";
            $("#free_places").append(tr);
        });
        carriageId = id;
    }
}

