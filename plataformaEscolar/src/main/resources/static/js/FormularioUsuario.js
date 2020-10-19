window.onload = function(){
	document.getElementById("email").onblur = comprobarCorreo;
	document.getElementById("pwd2").onblur = comprobarContrasena;
}

function revisarFormulario(){
	var resultado = false;

	resultado = comprobarCorreo()&&
				comprobarContrasena();
	
	formulario.enviar.className = resultado?"btn btn-success mb-2":"btn btn-danger mb-2";

	return true; //lo tengo a false para que nunca envíe el formulario, cuando esto entrara en producción, habría que poner return resultado;
}



function comprobarCorreo(){
	var correo = formulario.email;
	var resultado = correo.value!=="";

	//la comprobación consiste en que haya 1 arroba y que después de ella al menos haya un punto
	//también puede buscarse un regex que lo compruebe
	if(resultado){
		var partesCorreo = correo.value.split('@');
		resultado = partesCorreo.length==2;
		if(resultado){
			var partesDominio = partesCorreo[1].split('.');
			resultado = partesDominio.length>1;
		}			
	}
	
	cambiarApariencia(correo,resultado);
	
	return resultado;
}

function comprobarContrasena(){
	var pwd1 = formulario.pwd1;
	var pwd2 = formulario.pwd2;
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


function cambiarApariencia(campo, estado){	
	if(estado){
		campo.classList.remove("border-danger");
		campo.classList.add("border-success");
		campo.parentNode.nextElementSibling.style.visibility = 'hidden';

	}
	else{
		campo.classList.remove("border-success");
		campo.classList.add("border-danger");
		campo.parentNode.nextElementSibling.style.visibility = 'visible';
	}
		
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