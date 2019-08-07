function newAnswer() {

    let pathName = window.location.pathname;

    console.log(pathName);

    let questionId = pathName.substr(16, pathName.length);

    console.log(questionId);

    location.href = "/forum/question/new_answer/" + questionId;

}


function login() {

    location.href = "/login";

}