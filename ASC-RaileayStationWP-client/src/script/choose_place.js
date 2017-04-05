/**
 * Created by nikita on 03.04.17.
 */
const ADULTS = "adults";
const CHILDREN_WITH_NO_SEATS = "children_with_no_seats";
const CHILDREN_WITH_SEATS = "children_with_seats";
var countPassenger = 3;

$(document).ready(function () {
    $('#passengers').hide();
    var trainName = getUrlParameter("trainName");
    var trainId = getUrlParameter("trainId");
    $("#trainName").text(trainId + " " + trainName);
    var departureStation = getUrlParameter("departureStation");
    var arrivalStation = getUrlParameter("arrivalStation");
    $("#route").text(departureStation + " - " + arrivalStation);
    var carriageType = getUrlParameter("carriageType");
    $("#carriage_type").text(carriageType);

    var arrivalDate = getUrlParameter("arrivalDate");
    $("#arrivalDate").text(arrivalDate);
    var departureDate = getUrlParameter("departureDate");
    $("#departureDate").text(departureDate);
    setPlaces();

});
function fillPassengerCount(select) {
    select.children().remove();
    for (var i = 0; i <= countPassenger; i++) {
        select.append($("<option/>", {
            value: i,
            text: i,
        }));
    }
}

function setPassengersBlockVisibility() {
    var isAdultsChecked = $('#adults_checkbox').is(':checked');
    var isChildrenChecked = $('#children_checkbox').is(':checked');
    if (isAdultsChecked || isChildrenChecked) {
        $('#passengers').slideDown();
    }
    else {
        $('#passengers').slideUp();
    }
}

function getPlacesByCarriageId(places, carriageId) {
    var str1 = "";
    for (var i = 0; i < places.length; i++) {
        var place = places[i];
        if (carriageId === place.carriageId) {
            var placeUI = "<input type='button' value='" + place.number + "' data='" + carriageId + "'/>";
            str1 = str1.concat(placeUI);
        }
    }
    return str1;
}

function setPlaces() {
    var places = JSON.parse(getUrlParameter("places"));
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
