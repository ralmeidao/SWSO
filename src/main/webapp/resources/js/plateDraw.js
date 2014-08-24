function plateDraw(canvasID, colors, colors2) {

	var canvas = document.getElementById(canvasID);
	var ctx = canvas.getContext("2d");

	// define the donut
	var cX = Math.floor(canvas.width / 2);
	var cY = Math.floor(canvas.height / 2);
	var radius = Math.min(cX, cY) * .75;

	// the datapoints
	var data = [];
	data.push(25);
	data.push(25);
	data.push(25);
	data.push(25);

	// colors to use for each datapoint
	// var colors=[];
	// colors.push("purple");
	// colors.push("green");
	// colors.push("cyan");
	// colors.push("gold");
	colors.push("white");
	colors.push("black");

	// track the accumulated arcs drawn so far
	var totalArc = 0;

	// draw a wedge
	function drawWedge2(percent, color) {
		// calc size of our wedge in radians
		var WedgeInRadians = percent / 100 * 360 * Math.PI / 180;

		// draw the wedge
		ctx.save();
		ctx.beginPath();
		ctx.moveTo(cX, cY);
		ctx.arc(cX, cY, radius, totalArc, totalArc + WedgeInRadians, false);
		ctx.closePath();
		ctx.fillStyle = color;
		ctx.fill();
		ctx.restore();
		// sum the size of all wedges so far
		// We will begin our next wedge at this sum
		totalArc += WedgeInRadians;
	}

	function drawWedge(percent, color) {
		// calc size of our wedge in radians
		var WedgeInRadians = percent / 100 * 360 * Math.PI / 180;

		// draw the wedge
		ctx.save();
		ctx.beginPath();
		ctx.moveTo(cX, cY);
		ctx.arc(cX, cY, radius * .60, totalArc, totalArc + WedgeInRadians,
				false);
		ctx.closePath();
		ctx.fillStyle = color;
		ctx.fill();
		ctx.restore();
		// sum the size of all wedges so far
		// We will begin our next wedge at this sum
		totalArc += WedgeInRadians;
	}

	function drawRadius(a, b, c, d) {
		ctx.beginPath();
		ctx.moveTo(a, b);
		ctx.fillStyle = colors[5];
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
		ctx.fillStyle = colors[5];
		ctx.stroke();
		ctx.fill();
		ctx.restore();
	}

	// draw the donut one wedge at a time
	function drawDonut() {
		drawHoop(radius);
		for (var i = 0; i < data.length; i++) {
			drawWedge2(data[i], colors2[i]);
		}
		// cut out an inner-circle == donut
		ctx.beginPath();
		ctx.moveTo(cX, cY);
		ctx.fillStyle = colors[4];
		ctx.arc(cX, cY, radius * .60, 0, 2 * Math.PI, false);
		ctx.fill();

		drawHoop(radius * .60);
		for (var i = 0; i < data.length; i++) {
			drawWedge(data[i], colors[i]);
		}

		var delta = radius * .30;
		drawHoop(delta);

		// cut out an inner-circle == donut
		ctx.beginPath();
		ctx.moveTo(cX, cY);
		ctx.fillStyle = colors[4];
		ctx.arc(cX, cY, radius * .30, 0, 2 * Math.PI, false);
		ctx.fill();

		drawRadius(cX + delta, cY, cX + radius, cY);
		drawRadius(cX, cY + delta, cX, cY + radius);
		drawRadius(cX - delta, cY, cX - radius, cY);
		drawRadius(cX, cY - delta, cX, cY - radius);
	}

	// draw the donut
	drawDonut();

} // end $(function(){});
