//MAIN
$(document).ready(function(){
	adicionaRemoveClassBtnSelectedFileUpload();
	adicionaTooltipe();
});

function adicionaRemoveClassBtnSelectedFileUpload() {
	// Foco dos botões
	$(".fileUploadFocus").focus(function() {
		$($(this)[0].parentElement.parentElement.parentElement).addClass('btnSelected');
	});

	// Perda de foco dos botões
	$(".fileUploadFocus").blur(function() {
    	$($(this)[0].parentElement.parentElement.parentElement).removeClass('btnSelected');
	});	

	// Click dos botões
	$(".fileUploadFocus").click(function() {
    	$($(this)[0].parentElement.parentElement.parentElement).removeClass('btnSelected');
	});
}

function adicionaTooltipe() {
	$($('.ui-fileupload')[0]).attr('title', 'Carregar Imagem');
}