function plateDraw(canvasID, arrayColors) {

	var canvas = document.getElementById(canvasID);
	var ctx = canvas.getContext("2d");

	// define the donut
	var cX = Math.floor(canvas.width / 2);
	var cY = Math.floor(canvas.height / 2);
	var radius = Math.min(cX, cY) * .9;
	var qtdTrilhas = arrayColors.length;
	var colors = [];
	var qtdSectors = 0;
	var tamanhoSetor = 100;
	
	for (var int = 0; int < arrayColors.length; int++) {
		colors = arrayColors[int];
		qtdSectors = colors.length;
		tamanhoSetor = 100/qtdSectors;
		
		// draw the donut
		drawDonut(tamanhoSetor, colors, getFator(qtdTrilhas, int), getFator(qtdTrilhas, int+1));
	}

	function getFator(qtdTrilhas, int) {
		return (1 - (0.9/(qtdTrilhas+1)*(int)));
	}


	// draw the donut one wedge at a time
	function drawDonut(tamanhoSetor, colors, fator1, fator2) {
		
		drawHoop(radius * fator1);

		// track the accumulated arcs drawn so far
		var totalArc = 0;
		
		// calc size of our wedge in radians
		var wedgeInRadians = tamanhoSetor / 100 * 360 * Math.PI / 180;
		
		var delta1 =  radius * fator1;
		var delta2 =  radius * fator2;
		
		for (var i = 0; i < colors.length; i++) {
			drawWedge(tamanhoSetor, colors[i], totalArc, wedgeInRadians, delta1);
			
			if(i != 0) {
				drawRadius(cX + (delta1 * Math.cos(i*wedgeInRadians)) , cY+ (delta1 * Math.sin(i*wedgeInRadians)), cX + (delta2 * Math.cos(i*wedgeInRadians)), cY + (delta2 * Math.sin(i*wedgeInRadians)));
			} 			
			if(i + 1 == colors.length) {
				drawRadius(cX + (delta1 * Math.cos(0*wedgeInRadians)) , cY+ (delta1 * Math.sin(0*wedgeInRadians)), cX + (delta2 * Math.cos(0*wedgeInRadians)), cY + (delta2 * Math.sin(0*wedgeInRadians)));	
			}
			
			// sum the size of all wedges so far
			// We will begin our next wedge at this sum
			totalArc += wedgeInRadians;
		}

		drawHoop(delta2);
		drawArcWhite(delta2);
	}

	
	function drawWedge(percent, color, totalArc, wedgeInRadians, a) {
		// draw the wedge
		ctx.save();
		ctx.beginPath();
		ctx.moveTo(cX, cY);
		ctx.arc(cX, cY, a, totalArc, totalArc + wedgeInRadians,	false);
		ctx.closePath();
		ctx.fillStyle = color;
		ctx.fill();
		ctx.restore();
	}

	function drawArcWhite(a){
		ctx.beginPath();
		ctx.moveTo(cX, cY);
		ctx.fillStyle = "white";
		ctx.arc(cX, cY, a, 0, 2 * Math.PI, false);
		ctx.fill();
	}

	function drawRadius(a, b, c, d) {
		ctx.beginPath();
		ctx.moveTo(a, b);
		ctx.fillStyle = "black";
		ctx.lineTo(c, d);
		ctx.stroke();
		ctx.fill();
	}

	function drawHoop(a) {
		ctx.save();
		ctx.beginPath();
		ctx.moveTo(cX, cY);
		ctx.arc(cX, cY, a + 1, 0, 2 * Math.PI, false);
		ctx.closePath();
		ctx.fillStyle = "black";
		ctx.stroke();
		ctx.fill();
		ctx.restore();
	}

}
