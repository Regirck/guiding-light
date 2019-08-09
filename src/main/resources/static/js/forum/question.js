function createModalWithUserPersonalData() {

    let btn = document.querySelector("#modal-button");

    btn.addEventListener("click", function () {
        callUser();
        openModal();
    });

    function callUser() {
        let userId = document.querySelector("#user-id").innerHTML;
        console.log(userId);
        $.getJSON('/profile/get/' + userId, function(response) {
            console.log(response);
        });
    }

    function openModal() {
        $("#user-modal").modal("show");
    }
}

createModalWithUserPersonalData();

function newAnswer() {

    let pathName = window.location.pathname;

    let questionId = pathName.substr(16, pathName.length);

    location.href = "/forum/question/new_answer/" + questionId;
}


function login() {

    location.href = "/login";

}