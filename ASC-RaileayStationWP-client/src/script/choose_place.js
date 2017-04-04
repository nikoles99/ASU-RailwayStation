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
    $("#trainName").text(trainName);
    var departureStation = getUrlParameter("departureStation");
    var arrivalStation = getUrlParameter("arrivalStation");
    $("#route").text(departureStation + " - " + arrivalStation);
    var carriageType = getUrlParameter("carriageType");
    $("#carriage_type").text(carriageType);
    var trainId = getUrlParameter("trainId");
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

$('#adults_checkbox').change(function () {
    setPassengersBlockVisibility();
});
$('#children_checkbox').change(function () {
    setPassengersBlockVisibility();
});

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
        if (carriageId != "" && carriageId === place.carriageId) {
            continue;
        }
        var placesColumn = getPlacesByCarriageId(places, place.carriageId);
        var tr = "<tr>" +
            "<td>" + place.carriageId + "</td>" +
            "<td>" + placesColumn + "</td>" +
            "</tr>";
        carriageId = place.carriageId;
        $("#free_places").append(tr);
    }
}
