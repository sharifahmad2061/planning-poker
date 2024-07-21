let new_round_form = document.getElementById('new_round_form');
let current_round_form = document.getElementById('current_round_form');
let completed_round_list = document.getElementById('past-rounds');

websocket_connection = new WebSocket('ws://localhost:8080/ws');

websocket_connection.onmessage = function (event) {
    let message = JSON.parse(event.data);
    console.log(message);
}

new_round_form.addEventListener('submit', function (event) {
    event.preventDefault();
    let formData = new FormData(new_round_form);
    websocket_connection.send(JSON.stringify(formData));
});