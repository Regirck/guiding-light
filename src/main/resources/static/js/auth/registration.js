
function validatePassword() {

    let passwordI = document.getElementById("password").value;
    let passwordII = document.getElementById("confirm-password").value;

    let submission = true;

    let lowerCaseLetters = /[a-z]/g;
    let upperCaseLetters = /[A-Z]/g;
    let numbers = /[0-9]/g;
    let minimumCharacter = 8;

    if (! passwordI.match(lowerCaseLetters)) {
        alert("Password not include lower case!")
    }

    if (passwordI !== passwordII) {
        submission = false;
        alert("Password not match!")
    }

    return submission;
}