function newQuestion() {
    location.href = "/new_question";
}


function login() {
    location.href = "/login";
}

$.ajax('/forum/questions', {
    statusCode: {
        200: function () {
            fillQuestion();
        },
        204: function () {
            alert('No content');
            // TODO alert modal
        }
    }
});

function fillQuestion() {
    $.getJSON('/forum/questions', function (question) {
        for (let i = 0; i < question.length; i++) {

            let htmlQuestions = document.getElementById("questions");


            htmlQuestions.innerHTML +=
                `
                <li id="` + question[i]['id'] + `" class="list-group-item list-group-item-dark">
                    <div>
                        ` + question[i]['title'] + `
                    </div>
                    <div>
                        ` + question[i]['content'] + `
                    </div>
                </li>
                `

        }
        searchQuestion(question);
    });
}

function searchQuestion(questionJSON) {
    let questions = document.querySelectorAll('section > div > ul > li');

    for (let i = 0; i < questions.length; i++) {

        questions[i].addEventListener("click", function () {

            for (let j = 0; j < questionJSON.length; j++) {
                if (questions[i].id == questionJSON[i]['id']) {
                    openQuestion(questionJSON[i]);
                    break;
                }
            }
        });
    }
}

function openQuestion(question) {
    console.log(question);
}