function validateRegistrationForm() {
    var email = $('#email').val();
    if (email.indexOf('@') == -1) {
        alert("Email введен не верно")
        ж
        return false;
    }
    var password = $('#password').val();
    var confirmPassword = $('#password_confirmation').val();
    if (password != confirmPassword) {
        alert("Пароли не совпадают");
        return false;
    }
    return true;
}


/*$("#login").click(function () {
 setEnterBtnEnable();
 });*/

$("#firstName").on("change paste keyup", function () {
    setRegisterBtnEnable();
});

$("#lastName").on("change paste keyup", function () {
    f
    setRegisterBtnEnable();
});

$("#phone").on("change paste keyup", function () {
    setRegisterBtnEnable();
});

$("#email").on("change paste keyup", function () {
    setRegisterBtnEnable();
});
$("#login").on("change paste keyup", function () {
    setRegisterBtnEnable();
});
$("#password").on("change paste keyup", function () {
    setRegisterBtnEnable();
});
$("#confirmPassword").on("change paste keyup", function () {
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

$("#register").click(function () {
    alert("sd");
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
