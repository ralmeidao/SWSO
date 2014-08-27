function movesDraw(canvasID, arrayMoves) {

	var canvas = document.getElementById(canvasID);
	var ctx = canvas.getContext("2d");
	
	var distancia = canvas.width/4;

	for (var int = 0; int < arrayMoves.length; int++) {
		move = arrayMoves[int];
		drawMove(int, move[0], move[1]);
	}

	function drawMove(i, de, para) {
		var ax = (distancia/2) + (de * distancia);
		var ay = 5 + (i*20);
		drawPoint(ax, ay);
		
		var bx = (distancia/2) + (para * distancia);
		var by = 5 + ((i+1) * 20);
		drawPoint(bx, by);
		
		drawLine(ax, ay, bx, by);
	}

	
	function drawLine(ax, ay, bx, by) {
		ctx.beginPath();
		ctx.moveTo(ax, ay);
		ctx.fillStyle = "black";
		ctx.lineTo(bx, by);
		ctx.stroke();
		ctx.fill();
	}
	
	function drawPoint(cx, cy){
		ctx.beginPath();
		ctx.moveTo(cx, cy);
		ctx.fillStyle = "black";
		ctx.arc(cx, cy, 5, 0, 2 * Math.PI, false);
		ctx.fill();
	}
}
