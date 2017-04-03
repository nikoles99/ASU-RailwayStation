/**
 * Created by nikita on 03.04.17.
 */
const ADULTS = "adults";
const CHILDREN_WITH_NO_SEATS = "children_with_no_seats";
const CHILDREN_WITH_SEATS = "children_with_seats";
var countPassenger = 3;

$(document).ready(function () {
    $('#passengers').hide();
    $('#places').hide();
    var trainName = getUrlParameter("trainName");
    $("#trainName").text(trainName);
    var departureStation = getUrlParameter("departureStation");
    var arrivalStation = getUrlParameter("arrivalStation");
    $("#route").text(departureStation + " - " + arrivalStation);
    var carriageType = getUrlParameter("carriageType");
    $("#carriage_type").text(carriageType);
    var trainId = getUrlParameter("trainId");
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
    setPlacesBlockVisibility();
});
$('#children_checkbox').change(function () {
    setPassengersBlockVisibility();
    setPlacesBlockVisibility();
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

function setPlacesBlockVisibility() {
    var isAdultsChecked = $('#adults_checkbox').is(':checked');
    var isChildrenChecked = $('#children_checkbox').is(':checked');
    if (isAdultsChecked || isChildrenChecked) {
        $('#places').slideDown();
    }
    else {
        $('#places').slideUp();
    }
}

