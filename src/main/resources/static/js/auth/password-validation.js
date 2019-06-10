function passwordValidation() {

    let passwordIsValid = true;

    let passwordI = document.getElementById("password").value;
    let passwordII = document.getElementById("confirm-password").value;


    let passwordIsStrong = containmentCheck(passwordI);
    if (passwordIsStrong === false) {
        $("#password-not-valid").modal("show");
        passwordIsValid = false;
    }

    let notMatchAlert = document.getElementById("password-not-match");
    if (passwordI === passwordII) {
        notMatchAlert.classList.remove("password-not-match-display-block");
        notMatchAlert.classList.add("password-not-match-display-none");
    } else {
        notMatchAlert.classList.remove("password-not-match-display-none");
        notMatchAlert.classList.add("password-not-match-display-block");
        passwordIsValid = false;
    }

    return passwordIsValid;
}

function containmentCheck(password) {

    let checkIsSuccess = true;

    letterCheck();
    capitalCheck();
    numberCheck();
    lengthCheck();

    return checkIsSuccess;

    function letterCheck() {
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
    }

    function capitalCheck() {
        let upperCaseLetters = /[A-Z]/g;
        let capital = document.getElementById("capital");
        if (password.match(upperCaseLetters)) {
            capital.classList.remove("invalid");
            capital.classList.add("valid");
        } else {
            capital.classList.remove("valid");
            capital.classList.add("invalid");
            checkIsSuccess = false;
        }
    }

    function numberCheck() {
        let numbers = /[0-9]/g;
        let number = document.getElementById("number");
        if (password.match(numbers)) {
            number.classList.remove("invalid");
            number.classList.add("valid");
        } else {
            number.classList.remove("valid");
            number.classList.add("invalid");
            checkIsSuccess = false;
        }
    }

    function lengthCheck() {
        let minimumCharacter = 8;
        let length = document.getElementById("length");

        if (password.length >= minimumCharacter) {
            length.classList.remove("invalid");
            length.classList.add("valid");
        } else {
            length.classList.remove("valid");
            length.classList.add("invalid");
            checkIsSuccess = false;
        }
    }
}
