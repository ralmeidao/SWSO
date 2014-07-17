var canvas;
var contexto;
var tamanhoBarra;
var percentagem;

$(function(){
	canvas = $('.confidenceBar')[0];
	contexto = canvas.getContext("2d");
	tamanhoBarra = canvas.width-40;
	percentagem = $('#percentBar').val();
	
	desenhaBarra();
	marcaPonto();
	
});

function desenhaBarra(){
	var grd = contexto.createLinearGradient(10,0,tamanhoBarra,0);
	grd.addColorStop("0","green");
	grd.addColorStop("0.5","yellow");
	grd.addColorStop("1","red");
	 
	contexto.fillStyle=grd;
	contexto.fillRect(10,30,tamanhoBarra,25);

	contexto.fillStyle='rgb(0,0,0)';
	contexto.font="bold 15px Arial";
	contexto.textAlign="start";
	contexto.fillText("Divergente",tamanhoBarra-75,48);
}

function marcaPonto(){
	var posicao = (tamanhoBarra/100)*percentagem;
	var ax = posicao;
	var bx = posicao+10;
	var cx = posicao+20;
	
	desenhaTriangulo(ax, 18, bx, 28, cx, 18, true);
	desenhaValor(posicao, percentagem);
}

function desenhaTriangulo(ax, ay, bx, by, cx, cy, solido){
	contexto.beginPath();
	contexto.moveTo(ax, ay);
	contexto.lineTo(bx, by);
	contexto.lineTo(cx, cy);
	contexto.lineTo(ax, ay);
	contexto.stroke();
	if(solido) {
		contexto.fill();
	}
	contexto.closePath();
}

function desenhaValor(posicao){
	contexto.font="bold 12px Arial";
	contexto.textAlign="start";      
	contexto.fillText(percentagem+"%",posicao,15);   
}