function getTrainsByStationName(departureStation, arrivalStation, callback) {
    var url = "http://localhost:8080/getTrainsByRoute";
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

function addNewTrain(train) {
    if (train != null) {
        var url = "http://localhost:8080/addNewRoute";
        console.log(JSON.stringify(train));
        $.ajax({
            url: url,
            method: 'POST',
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(train),
            error: function (xhr) {
                alert(xhr.responseText);
                errorLogging(xhr);
            }
        });
    }
}