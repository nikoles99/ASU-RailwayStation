/**
 * Created by nikita on 18.03.17.
 */
function deleteStation(stationName, callback) {
    var url = "http://localhost:8080/deleteStation";
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: {name: stationName},
        xhrFields: {withCredentials: true},
        success: callback,
        error: function (error) {
            errorLogging(error);
        }
    });
}

function getStationByName(name, callback) {
    var url = "http://localhost:8080/getStationByName";
    return $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: {name: name},
        success: callback,
        error: function (error) {
            errorLogging(error);
        }
    });
}

function getStations(callback) {
    var url = "http://localhost:8080/getAllStations";
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        success: callback,
        error: function (error) {
            errorLogging(error);
        }
    });
}

function addStation(nameStation, callback) {
    var url = "http://localhost:8080/addStation";
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: {name: nameStation},
        xhrFields: {withCredentials: true},
        success: callback,
        error: function (error) {
            errorLogging(error);
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

function getStationById(id, callback) {
    var url = "http://localhost:8080/getStationById";
    return $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: {id: id},
        success: callback,
        error: function (error) {
            errorLogging(error);
        }
    });
}
