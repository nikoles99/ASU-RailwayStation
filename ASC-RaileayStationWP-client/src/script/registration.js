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


/*$("#login").click(function () {
 setEnterBtnEnable();
 });*/
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

function setRegisterBtnEnable() {
    var firstName = $('#first_name').val().length;
    var lastName = $('#last_name').val().length;
    var phone = $('#phone').val().length;
    var email = $('#email').val().length;
    var login = $('#login').val().length;
    var password = $('#password').val().length;
    var confirmPassword = $('#password_confirmation').val().length;
    var isValidate = firstName >= 4 && lastName >= 4 && phone == 13 && email >= 4 && login >= 4 && password >= 4 && confirmPassword >= 4;
    $("#register").prop('disabled', !isValidate);
}

$(document).on("click", "#register", function () {
    var isValidate = validateRegistrationForm();
    if (isValidate) {
        var user = {
            login: $('#login').val(),
            password: $('#password').val(),
            name: firstName = $('#first_name').val(),
            lastName: $('#last_name').val(),
            email: $('#email').val()
        };
        registration(user);
    }
});
