
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

    let checkIsSuccess = false;

    let lowerCaseLetters = /[a-z]/g;
    let letter = document.getElementById("letter");

    if (password.match(lowerCaseLetters)) {
        letter.classList.remove("invalid");
        letter.classList.add("valid");
    } else {
        letter.classList.remove("valid");
        letter.classList.add("invalid");
        checkIsSuccess = false;
    }

    let upperCaseLetters = /[A-Z]/g;
    if (!password.match(upperCaseLetters)) {
        checkIsSuccess = false;
    }

    let numbers = /[0-9]/g;
    if (!password.match(numbers)) {
        checkIsSuccess = false;
    }

    let minimumCharacter = 8;
    if (!password.length >= minimumCharacter) {
        checkIsSuccess = false;
    }

    return checkIsSuccess;
}
