if (window.console) {
  console.log("Welcome to your Play application's JavaScript!");
}

document.addEventListener('DOMContentLoaded', createWebsocket, false);

//var that = this;
var socket;
//var dataToReceive = new DataToReceive();
//var addElem = new AddElements();

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
//    if(data.hasOwnProperty("error")){
//        errorStatus = true;
//    }
//    if(data.hasOwnProperty("result")){
//        resultStatus = true;
//    }
//    if(data.hasOwnProperty("id")){
//        idStatus = true;
//    }
//        if(errorStatus == false && resultStatus == true && idStatus == true){
//            switch (data.id) {
//            case "0001":
//                console.log("| <--- | startConfiguration");
//                dataToReceive.setAreas(data.result);
//                addElem.addAreas(data.result);
//                that.getClassOfComponents(data.result[0].nickName);
//                break;
//            case "0002":
//                console.log("| <--- | getClassOfComponents");
//                console.log(data.result);
//                addElem.addClassesOfComponents(data.result);
//                break;
//
//            default:
//                break;
//            }
//        }
}

startConfiguration = function(){
        var startConf = {
            "jsonrpc": "2.0",
            "method": "startConfiguration",
            "params": [],
            "id": "0001"
        }
        console.log("| ---> | startConfiguration");
//    		waitForSocketConnection(socket, function() {
            socket.send(JSON.stringify(startConf));
//    		});
    }

function open() {
        console.log("Websocket is open");
        startConfiguration();
    }

function close() {
    console.log("close");
    socket.close();
}

$('#login').click(function(e) {
    alert("hallo")
    console.log($('#username').val());
    console.log($('#password').val());
});