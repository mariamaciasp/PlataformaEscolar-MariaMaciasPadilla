window.onload = function(){
	document.getElementById("pwd2").onblur = comprobarContrasena;
}

function revisarFormularioPassword(){
	var resultado = false;

	resultado =
				comprobarContrasena();

	formularioPassword.enviar.className = resultado?"btn btn-success mb-2":"btn btn-danger mb-2";

	return false; //lo tengo a false para que nunca envíe el formularioPassword, cuando esto entrara en producción, habría que poner return resultado;
}

function comprobarContrasena(){
	var pwd1 = formularioPassword.pwd1;
	var pwd2 = formularioPassword.pwd2;
	var resultado = pwd1.value!=="" && pwd2.value!=="";
	var mensaje;
	if(pwd1.value !== pwd2.value){
		resultado = false;
		mensaje = "Las contraseñas no coinciden";

	}else{
		var regex = /^(?=.*\d)(?=.*[a-záéíóúüñ]).*[A-ZÁÉÍÓÚÜÑ]/;
		resultado = pwd1.value.length>7 && regex.test(pwd1.value);
		mensaje = !resultado?"La contraseña debe tener al menos 8 caracteres, algún número, alguna mayúscula y alguna minúscula":"Contraseña correcta"
	}

	cambiarAparienciaPwd(pwd1,pwd2,resultado,mensaje);

	return resultado;
}



function cambiarAparienciaPwd(pw1,pw2,estado,mensaje){	
	if(estado){
		pw1.classList.remove("border-danger");
		pw1.classList.add("border-success");
		pw2.classList.remove("border-danger");		
		pw2.classList.add("border-success");
		pw2.parentNode.nextElementSibling.style.visibility = 'hidden';	
	}
	else{
		pw1.classList.remove("border-success");
		pw1.classList.add("border-danger");
		pw2.classList.remove("border-success");
		pw2.classList.add("border-danger");
		pw2.parentNode.nextElementSibling.innerText = mensaje;
		pw2.parentNode.nextElementSibling.style.visibility = 'visible';
	}
}