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


function newQuestion() {
    location.href = "forum/new_question";
}


function login() {
    location.href = "/login";
}

