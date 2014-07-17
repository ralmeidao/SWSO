function somenteNumero(e) {
	var tecla = e.charCode;
	if (tecla == undefined) { // Validação para o IE
		tecla = e.keyCode;
	}
	tecla = String.fromCharCode(tecla);
	if (e.which == 8 || e.which == 0 || /^\-?([0-9]+|Infinity)$/.test(tecla)) {
		return;
	} else {
		e.keyCode = 0;
		e.charCode = 0;
		e.returnValue = false;
		return false;
	}
}

function limitarTextArea(limitField, limitNum) {
  limitField.value = limitField.value.replace(new RegExp("\r", "g"), " ").replace(new RegExp("\n", "g"), " ");
  if (limitField.value.length > limitNum) {
    limitField.value = limitField.value.substring(0, limitNum);
  }
}

function formatarCpf(){
	
	var valorCpf = $("input[id*='Cpf']").val();
	valorCpf = valorCpf.replace(/\-/g,"").replace(/\./g,'');
	
	if (valorCpf != "" && valorCpf != undefined && !/^\-?([0-9]+|Infinity)$/.test(valorCpf)){
		$("input[id*='Cpf']").val("");
	} else if (valorCpf != null && valorCpf != "") {
		
		exp = /\-|\.|\/|\(|\)| /g;
		
		valorCpf = valorCpf.replace( exp, "" );
		
		valorCpf = valorCpf.substring(0,3)+"." +valorCpf.substring(3,6)+"."+valorCpf.substring(6,9)+"-"+valorCpf.substring(9,11);
		
		$("input[id*='Cpf']").val(valorCpf);
	}
}


/*
 * function matuser(e){
 * 
 * var tecla = e.charCode;
 * 
 * 
 * 
 * if (tecla == undefined) { // Validação para o IE
 * 
 * tecla = e.keyCode;
 *  }
 * 
 * tecla = String.fromCharCode(tecla);
 *  // if (e.which == 8 || e.which == 0 || /^\-?([0-9]+|Infinity)$/.test(tecla)) {
 * if (e.which == 8 || e.which == 0 || /^\-?([0-9]+|Infinity))$/.test(tecla)) {
 * 
 * 
 * return;
 *  } else {
 * 
 * e.keyCode = 0;
 * 
 * e.charCode = 0;
 * 
 * e.returnValue = false;
 * 
 * return false;
 *  }
 *  }
 */

/**  
 * Função Principal 
 * @param w - O elemento que será aplicado (normalmente this).
 * @param e - O evento para capturar a tecla e cancelar o backspace.
 * @param m - A máscara a ser aplicada.
 * @param r - Se a máscara deve ser aplicada da direita para a esquerda. Veja Exemplos.
 * @param a - 
 * @returns null  
 */
function maskIt(w,e,m,r,a){
    
    // Cancela se o evento for Backspace
    if (!e){
 	   e = window.event;
    }
    if (e.keyCode){
 	   code = e.keyCode;
    } else if (e.which) {
 	   code = e.which;
    }
    
    // Variáveis da função
    var txt  = (!r) ? w.value.replace(/[^\d]+/gi,'') : w.value.replace(/[^\d]+/gi,'').reverse();
    var mask = (!r) ? m : m.reverse();
    var pre  = (a ) ? a.pre : "";
    var pos  = (a ) ? a.pos : "";
    var ret  = "";

    if(code == 9 || code == 8 || txt.length == mask.replace(/[^#]+/g,'').length) return false;

    // Loop na máscara para aplicar os caracteres
    for(var x=0,y=0, z=mask.length;x<z && y<txt.length;){
            if(mask.charAt(x)!='#'){
                    ret += mask.charAt(x); x++;
            } else{
                    ret += txt.charAt(y); y++; x++;
            }
    }
    
    // Retorno da função
    ret = (!r) ? ret : ret.reverse();      
    w.value = pre+ret+pos;
}

PrimeFaces.locales['pt'] = {

	    closeText: 'Fechar',

	    prevText: 'Anterior',

	    nextText: 'Próximo',

	    currentText: 'Começo',

	    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],

	    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun', 'Jul','Ago','Set','Out','Nov','Dez'],

	    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],

	    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb'],

	    dayNamesMin: ['D','S','T','Q','Q','S','S'],

	    weekHeader: 'Semana',

	    firstDay: 1,

	    isRTL: false,

	    showMonthAfterYear: false,

	    yearSuffix: '',

	    timeOnlyTitle: 'Só Horas',

	    timeText: 'Tempo',

	    hourText: 'Hora',

	    minuteText: 'Minuto',

	    secondText: 'Segundo',

	    ampm: false,

	    month: 'Mês',

	    week: 'Semana',

	    day: 'Dia',

	    allDayText : 'Todo Dia'

	};



//Novo método para o objeto 'String'
String.prototype.reverse = function(){
       return this.split('').reverse().join('');
};



function somenteLetras(e) {
	var tecla = e.charCode;
	if (tecla == undefined) { // Validação para o IE
		tecla = e.keyCode;
	}
	tecla = String.fromCharCode(tecla);
	if (e.which == 8 || e.which == 0
			|| /^\-?([a-z A-Z]+|Infinity)$/.test(tecla)) {
		return;
	} else {
		e.keyCode = 0;
		e.charCode = 0;
		e.returnValue = false;
		return false;
	}
}

function somenteLetrasEnumeros(e) {
	var tecla = e.charCode;
	if (tecla == undefined) { // Validação para o IE
		tecla = e.keyCode;
	}
	tecla = String.fromCharCode(tecla);
	if (e.which == 8 || e.which == 0
			|| /^\-?([a-z A-Z 0-9]+|Infinity)$/.test(tecla)) {
		return;
	} else {
		e.keyCode = 0;
		e.charCode = 0;
		e.returnValue = false;
		return false;
	}
}

$(function() {
	$("#dropIdentif").hide();
	$("#btnInfos").mouseover(function() {
		$("#dropIdentif").show();
	}).mouseout(function() {
		$("#dropIdentif").hide();
	});

	// $('.zebra tbody tr:odd').addClass('impar');
	// $('.zebra tbody tr:even').addClass('par');

	$('.zebra tbody tr').hover(function() {
		$(this).addClass('destacar');
	}, function() {
		$(this).removeClass('destacar');
	});
});
	

$(document).ready(function(){
	formatarDatas();
	$(".carregando").hide();
	$.unblockUI();
	adicionaRemoveClassBtnSelected();
	
	// Foco no Menu
	$(".menuLink").focus(function() {
		$(this).addClass('link-menu-selected');
	});
	
	// Perda de foco do menu
	$(".menuLink").blur(function() {
        $(this).removeClass('link-menu-selected');
	});
	
});

function showStatus() {
	$("#idLoad").show();
	$.blockUI({message: null});
}
 
function hideStatus() {
	$("#idLoad").hide();
	$.unblockUI();
}

function pad (n, len, padding){
	var sign = '', s = n;

	if (typeof n === 'number'){
		sign = n < 0 ? '-' : '';
		s = Math.abs (n).toString ();
	}

	if ((len -= s.length) > 0){
		s = Array (len + 1).join (padding || '0') + s;
	}
	return sign + s;
}

$(window).bind('beforeunload',function(){
	$("input[id*='Data']").mask('99/99/9999');
	$(".carregando").show();
	$.blockUI({message: null});
});

function foceClick(id){
	jQuery(PrimeFaces.escapeClientId(id)).click();
}

/**
 * Metodo limapa os campos da sessao que possui as datas inicio e fim 
 * 
 * @returns {Boolean}
 */
function limparSessaoDatas(){
	$("input[id='mainFormCadastro:idDataExpedicao_input']").val("");
	$("input[id='mainFormCadastro:idDataNascimento_input']").val("");
	return false;
}

/**
 * Metodo responsavel por formatar datas
 */
function formatarDatas(){
//	$("input[id*='Data']").mask('99/99/9999');
	
	$( "input[id*='Data']" ).each(function() {
		 valorData =  $( this ).val( ); 
		 if(valorData != null && valorData!= "" ){
			 valorData = valorData.replace("/","").replace("/","");
			 valorData = valorData.substring(0,2) +"/" +  valorData.substring(2,4) + "/" + valorData.substring(4,8);
			 $( this ).val(valorData);
		 }
	 });
}

function guardarIdComponenteController(element){
	if(element != null){
		var idComponente = element.id;
		//Função definida pelo p:remoteCommand e esta implementada no ContextoController
		guardarIdComponente([{name: 'idComponente', value: idComponente}]); 
	}else{
		return null;
	}
}


function giveFocus(elementId){
	var element = document.getElementById(elementId);
	if(element != null){
		element.focus();
	}
}

function adicionaRemoveClassBtnSelected() {
	// Foco dos botões
	$(".buttonSelected").focus(function() {
		$(this).addClass('btnSelected');
	});
	
	// Perda de foco dos botões
	$(".buttonSelected").blur(function() {
        $(this).removeClass('btnSelected');
	});	
	
	// Click dos botões
	$(".buttonSelected").click(function() {
	        $(this).removeClass('btnSelected');
	});
}

function noCopy(event) {
	var tecla = String.fromCharCode(event.keyCode).toLowerCase();
	if (event.ctrlKey && (tecla == "c" || tecla == "v")) {
		window.event ? event.returnValue = false : event.preventDefault();
		return false;
	}
}

function overSomenteNumeros(element) {
	var valor = $(element).val();
	if (valor != "" && valor != undefined && !/^\-?([0-9]+|Infinity)$/.test(valor)) {
		$(element).val("");
	} else {
		return;
	}
}


