window.addEventListener("load", () => {
    const new_round_form = document.getElementById("new-round-form");
// let current_round_form = document.getElementById("current-round-form");
// let completed_round_list = document.getElementById("past-rounds");

    websocket_connection = new WebSocket('ws://localhost:8080/ws');

    websocket_connection.onmessage = function (event) {
        console.log(event.data);
    }

    new_round_form.addEventListener("submit", function (event) {
        event.preventDefault();
        const formElements = event.target.elements;
        let formDataObj = {};
        for (let i = 0; i < formElements.length; i++) {
            const input = formElements[i];
            if (input.name) { // Ensure the element has a 'name' attribute
                formDataObj[input.name] = input.value;
            }
        }
        console.log(formDataObj);
        websocket_connection.send(JSON.stringify(formDataObj));
    });
});
