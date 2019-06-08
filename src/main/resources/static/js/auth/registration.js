
function validatePassword() {

    let passwordI = document.getElementById("password").value;
    let passwordII = document.getElementById("confirm-password").value;

    let passwordIsStrong = containmentCheck(passwordI);

    if (passwordIsStrong === false) {
        $("#password-not-valid").modal("show");
        return false;
    }

    if (passwordI !== passwordII) {
        alert("Password not match!");
        return  false;
    }

    return true;
}

function containmentCheck(password) {

    let lowerCaseLetters = /[a-z]/g;
    if (!password.match(lowerCaseLetters)) {
        return false;
    }

    let upperCaseLetters = /[A-Z]/g;
    if (!password.match(upperCaseLetters)) {
        return false;
    }

    let numbers = /[0-9]/g;
    if (!password.match(numbers)) {
        return false;
    }

    let minimumCharacter = 8;
    if (!password.length >= minimumCharacter) {
        return false;
    }

    return true;
}
