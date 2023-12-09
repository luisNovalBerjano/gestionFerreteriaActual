let validar = false;
let validarNombreCompleto = /^[A-Z].*/;
let validarNickName = "";
let validarTelefono = /^[6-9]\d{8}$/;
let validarDireccion = "";
let validarCodigoPostal = /^\d{5}$/;
let validarMail = /^[\w.-]+@[a-zA-Z\d.-]+\.[a-zA-Z]{2,}$/;
let validado = true;
var nombreCompleto = document.getElementById("nombreCompleto");
var nick = document.getElementById("nickName");
var telefono = document.getElementById("telefono");
var direccion = document.getElementById("direccion");
var codigoPostal = document.getElementById("codigoPostal");
var mail = document.getElementById("mail");
var crearUsuario = document.getElementById("crearUsuario");

let validarNombreCompletoValido = false;
let validarNickNameValido = false;
let validarTelefonoValido = false;
let valdiarDireccionValido = false;
let validarCodigoPostalValido = false;
let validarMailValido = false;

function calcularLetraDNI(numero) {
    var letras = 'TRWAGMYFPDXBNJZSQVHLCKE';
    var letra = letras.charAt(numero % 23);
    return letra;
}


crearUsuario.addEventListener("click", () => {

    let textoAlert = "";

    if (!validarNombreCompleto.test(nombreCompleto.value)) {
        nombreCompleto.classList.remove('is-valid');
        nombreCompleto.classList.add("is-invalid");
        textoAlert += "El nombre de usuario no es valido,debe empezar por mayuscula";
        validarNombreCompletoValido = false;

    } else {
        nombreCompleto.classList.remove('is-invalid');
        nombreCompleto.classList.add("is-valid");
        validarNombreCompletoValido = true;
    }

    if (!validarNickName.test(nick.value)) {
        nick.classList.remove('is-valid');
        nick.classList.add("is-invalid");
        textoAlert += "\nEl nick no es valido, debe empezar por mayuscula";
        validarNickNameValido = false;

    } else {
        nick.classList.remove('is-invalid');
        nick.classList.add("is-valid");
        validarNickNameValido = true;
    }
    if (!validarTelefono.test(telefono.value)) {
        telefono.classList.remove('is-valid');
        telefono.classList.add("is-invalid");
        textoAlert += "\nEl telefono no es valido,debe empezar con un dígito en el rango de 6 a 9, seguido por exactamente ocho dígitos adicionales. ";
        validarTelefonoValido = false;

    } else {
        telefono.classList.remove('is-invalid');
        telefono.classList.add("is-valid");
        validarTelefonoValido = true;
    }

    if (!validarDireccion.test(direccion.value)) {
        direccion.classList.remove('is-valid');
        direccion.classList.add("is-invalid");
        textoAlert += "\nLa dirección no es valida, debe empezar con una letra mayuscula";
        valdiarDireccionValido = false;

    } else {
        direccion.classList.remove('is-invalid');
        direccion.classList.add("is-valid");
        valdiarDireccionValido = true;
    }
    if (!validarCodigoPostal.test(codigoPostal.value)) {
        codigoPostal.classList.remove('is-valid');
        codigoPostal.classList.add("is-invalid");
        textoAlert += "\nEl codigo postal no es valido, debe ser de 5 digitos";
        validarCodigoPostalValido = false;

    } else {
        codigoPostal.classList.remove('is-invalid');
        codigoPostal.classList.add("is-valid");
        validarCodigoPostalValido = true;
    }
    if (!validarMail.test(mail.value)) {
        mail.classList.remove('is-valid');
        mail.classList.add("is-invalid");
        textoAlert += "\nEl email no es valido, debe ser de este formato ejemplo@dominio.com";
        validarMailValido = false;

    } else {
        mail.classList.remove('is-invalid');
        mail.classList.add("is-valid");
        validarMailValido = true;
    }
    
    
    
    validado =  validarNombreCompletoValido && validarNickNameValido && validarTelefonoValido && valdiarDireccionValido && validarCodigoPostalValido
    && validarMailValido;

    if (!validado) {
        event.preventDefault();
        alert(textoAlert);
    }



});

