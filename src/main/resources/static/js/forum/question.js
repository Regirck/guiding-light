function newAnswer() {

    let pathName = window.location.pathname;

    let questionId = pathName.substr(10, pathName.length);

    location.href = "/new_answer/" + questionId;

}


function login() {

    location.href = "/login";

}