if (window.console) {
  console.log("Welcome to your Play application's JavaScript!");


}
document.addEventListener('DOMContentLoaded', createWebsocket, false);

var socket;

function createWebsocket(){
    console.log("JavaScript");
    var uri ='ws://localhost:9000/admin';
    socket = new WebSocket(uri);
    socket.onmessage = onMessage;
    socket.onopen = open;
    socket.onclose = close

}

function onMessage(message) {
    var data = JSON.parse(message.data);
//    var errorStatus = false;
//    var resultStatus= false;
//    var idStatus = false;
    console.log("Receive: " + message.data);

    if(data.test == "test") {
        console.log("get message")
        for(var i = 0; i < 5; i++){
            var iteratee = {
                "count": i
            }
            socket.send(JSON.stringify(iteratee))
        }
    }
}


function open() {
    console.log("Websocket is open");
    startConfiguration();
}

function close() {
console.log("close");
socket.close();
}