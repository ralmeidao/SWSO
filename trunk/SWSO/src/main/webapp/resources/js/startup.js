$(document).on("keydown", function(event) {
	var tecla = event.keyCode || event.witch;
		if (tecla == 13) {
			jQuery(PrimeFaces.escapeClientId("mainForm:btnEntrar")).click();
		}
	}
);