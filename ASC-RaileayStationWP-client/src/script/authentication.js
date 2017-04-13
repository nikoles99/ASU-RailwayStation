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
        success: function () {

        },
        error: function (error) {
            var message = error.responseJSON.message;
            if (NOT_FOUND_USER === message) {
                $('#errorMessage').text("Пользователь c логином \"" + login + "\" не найден");
                $('#registration').modal();
            }
        }
    });
}

function registration(user, success, error) {
    var url = "http://localhost:8080/registration";
    return $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: user,
        success: success,
        error: error
    });
}

function logout(success) {
    var url = "http://localhost:8080/logout";
    return $.ajax({
        url: url,
        type: 'POST',
        success: success
    });
}
