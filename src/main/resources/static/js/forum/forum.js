function newQuestion() {
    location.href = "/new_question";
}


function login() {
    location.href = "/login";
}

$.getJSON('/forum/questions', function (question) {

    for (let i = 0; i < question.length; i++) {
        let htmlQuestions = document.getElementById("questions");
        console.log(htmlQuestions);

        console.log(question[i]);

        let link = 'https://guiding-light.herokuapp.com/forum/question/' + question[i]['id'];
        htmlQuestions.innerHTML +=
            '<a href=' + link + '" class="list-group-item list-group-item-action list-group-item-dark">' +
                question[i]['title'] +
            '</a>';

    }
});
