function validateRegistrationForm() {
    var email = $('#email_registration').val();
    if (email.indexOf('@') == -1) {
        alert("Email введен не верно");
        return false;
    }
    var password = $('#password_registration').val();
    var confirmPassword = $('#password_confirmation_registration').val();
    if (password != confirmPassword) {
        alert("Пароли не совпадают");
        return false;
    }
    return true;
}

$(document).on("change paste keyup", "#first_name_registration", function () {
    setRegisterBtnEnable();
});
$(document).on("change paste keyup", "#last_name_registration", function () {
    setRegisterBtnEnable();
});
$(document).on("change paste keyup", "#phone_registration", function () {
    setRegisterBtnEnable();
});
$(document).on("change paste keyup", "#email_registration", function () {
    setRegisterBtnEnable();
});
$(document).on("change paste keyup", "#login_registration", function () {
    setRegisterBtnEnable();
});
$(document).on("change paste keyup", "#password_registration", function () {
    setRegisterBtnEnable();
});
$(document).on("change paste keyup", "#password_confirmation_registration", function () {
    setRegisterBtnEnable();
});

function clearForm() {
    $('#first_name_registration').val("");
    $('#last_name_registration').val("");
    $('#phone_registration').val("");
    $('#email_registration').val("");
    $('#login_registration').val("");
    $('#password_registration').val("");
    $('#password_confirmation_registration').val("");
}

function setRegisterBtnEnable() {
    var firstName = $('#first_name_registration').val().length;
    var lastName = $('#last_name_registration').val().length;
    var phone = $('#phone_registration').val().length;
    var email = $('#email_registration').val().length;
    var login = $('#login_registration').val().length;
    var password = $('#password_registration').val().length;
    var confirmPassword = $('#password_confirmation_registration').val().length;
    var isValidate = firstName >= 4 && lastName >= 4 && phone == 13 && email >= 4 && login >= 4 && password >= 4 && confirmPassword >= 4;
    $("#register").prop('disabled', !isValidate);
}

$(document).on("click", "#register", function () {
    var isValidate = validateRegistrationForm();
    if (isValidate) {
        var user = {
            login: $('#login_registration').val(),
            password: $('#password_registration').val(),
            name:  $('#first_name_registration').val(),
            lastName: $('#last_name_registration').val(),
            email: $('#email_registration').val()
        };
        registration(user);
    }
});
