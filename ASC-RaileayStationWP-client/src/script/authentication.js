/**
 * Created by nolesuk on 13-Apr-17.
 */
var NOT_FOUND_USER = "No entity found for query; nested exception is javax.persistence.NoResultException: No entity found for query";
function authentication(login, password) {
    var url = "http://localhost:8080/authentication";
    return $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: {login: login, password: password},
        xhrFields: {withCredentials: true},
        error: function (error) {
            var message = error.responseJSON.message;
            if (NOT_FOUND_USER === message) {
                $('#errorMessage').text("Пользователь c логином \"" + login + "\" не найден");
                $('#registration').modal();
            }
        }
    });
}

function registration(user) {
    var url = "http://localhost:8080/registration";
    return $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: user,
        xhrFields: {
            withCredentials: true
        },
        success: function () {
            alert("Регистрация пройдена успешно, зайдите под своим логином и паролем");
            $('#registration').modal('toggle');
            clearForm();
        },
        error: function (data) {
            var message = data.responseJSON.message;
            if(message.indexOf("unique_constrain_login")!=-1){
                alert("пользователь под таким логином уже зарегистрирован");
            }
        }
    });
}

function logout() {
    var url = "http://localhost:8080/logout";
    return $.ajax({
        url: url,
        xhrFields: {withCredentials: true},
        success: function () {
            alert("Р");
        },
        error: function (data) {
            errorLogging(data);
        }
    });
}

function isAuthorize() {
    var url = "http://localhost:8080/isAuthenticated";
    return $.ajax({
        url: url,
        type: 'POST',
        xhrFields: {withCredentials: true}
    });
}
