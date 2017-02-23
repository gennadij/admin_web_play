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
    var data = JSON.parse(message.data);
    
    console.log(JSON.stringify(data))
    
    $('#message').text("Received: " + JSON.stringify(data));
}

function open(e) {
	$('#status').text("WebSocket: opened").addClass('opened');
	
	//set NextStep 
	
	$('#StartConfig').click(function() {
		console.log("#sendEcho")
		e.preventDefault();
//		var obj = {
//			"echo" : true,
//			"toeveryone" : false
//		}
		
		var obj = {
				"dto" : "StartConfig"
		}

		 socket.send(JSON.stringify(obj))
	});

	$("#NextStep").click(function() {
		console.log("#open")
		e.preventDefault();
//		$('li').removeClass('disabled');
//		$('#open').off('click').addClass('disabled')
		
		var obj = {
				"dto" : "NextStep"
		}

		 socket.send(JSON.stringify(obj))
	});
	
	
	
}

function close(e) {
	console.log("close(e)")
	$('#status').text("WebSocket: closed").addClass('closed');
	$('#message').text("...");
}


