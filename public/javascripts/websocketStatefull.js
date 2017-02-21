document.addEventListener('DOMContentLoaded', createWebsocket, false);

var socket;

function createWebsocket(){
    console.log("JavaScript");
    var uri ='ws://localhost:9000/websocket';
    socket = new WebSocket(uri);
    socket.onmessage = onMessage;
    socket.onopen = open;
    socket.onclose = close

}

function onMessage(message) {
//    var data = JSON.parse(message.data);
    
    $('#message').text("Received: " + JSON.stringify(e.content));
}

function open(e) {
	console.log("open(e)")
	$('#status').text("WebSocket: opened").addClass('opened');
}

function close(e) {
	console.log("close(e)")
	$('#status').text("WebSocket: closed").addClass('closed');
	$('#message').text("...");
}

$('#sendEcho').click(function(e) {
	console.log("#sendEcho")
	e.preventDefault();
	var obj = {
		"echo" : true,
		"toeveryone" : false
	}

	 socket.send(obj)
});

$("#open").click(function(e) {
	console.log("#open")
//	e.preventDefault();
//	$('li').removeClass('disabled');
//	$('#open').off('click').addClass('disabled')
});
