/**
 * Created by nikita on 18.03.17.
 */
function deleteStation(stationName, callback) {
    var url = "http://localhost:8080/deleteStation";
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'jsonp',
        data: {name: stationName},
        success: callback,
        error: function (xhr) {
            errorLogging(xhr);
        }
    });
}


function getStations(callback) {
    var url = "http://localhost:8080/getAllStations";
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'jsonp',
        success: callback,
        error: function (xhr) {
            errorLogging(xhr);
        }
    });
}

function addStation(nameStation, callback) {
    var url = "http://localhost:8080/addStation";
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'jsonp',
        data: {name: nameStation},
        success: callback,
        error: function (xhr) {
            errorLogging(xhr);
        }
    });
}


function addStationsInDataList(dataList) {
    getStations(function (data) {
        dataList.empty();
        for (var i in data) {
            $("<option/>").html(data[i].name).appendTo(dataList);
            $("<span/>").html(data[i].id).appendTo(dataList);
        }
    });
}
