let validar = true;
let validarNombreProducto = "";
let validarFamiliaProducto = "";
let validarCategoriaProducto = "";
let validarMarcaProducto = "";
let validarStockProducto = /^(?:[1-9]\d{0,2}|999)$/;
let validarPrecioProducto = /^\d+(\.\d{1,2})?$/;
let validado = true;
let nombreProductoValido = false;
let familiaProductoValido = false;
let categoriaProductoValido = false;
let marcaProductoValido = false;
let stockProductoValido = false;
let precioProductoValido = false;


const nombreProducto = document.getElementById("nombreProducto");
const familiaProducto = document.getElementById("familiaProducto");
const categoriaProducto = document.getElementById("categoriaProducto");
const marcaProducto = document.getElementById("marcaProducto");
const stockProducto = document.getElementById("stockProducto");
const precioProducto = document.getElementById("precioProducto");
const crearProducto = document.getElementById("crearProducto");

crearProducto.addEventListener("click",()=>{
   let textoAlert="";
    //TODO:HACER PARA TODOS LOS INPUTS
    if(!validarNombreProducto.test(nombreProducto.value)){
       nombreProducto.classList.remove('is-valid');
        nombreProducto.classList.add("is-invalid");
        textoAlert+="El nombre del producto no es valido,";
        nombreProductoValido=false;
    }else{
        nombreProducto.classList.remove("is-invalid");
        nombreProducto.classList.add("is-valid");
        nombreProductoValido = true;
    }
    
     if(!validarFamiliaProducto.test(familiaProducto.value)){
       familiaProducto.classList.remove('is-valid');
        familiaProducto.classList.add("is-invalid");
        textoAlert+="La familia del producto no es valido,";
        familiaProductoValido=false;
    }else{
        familiaProducto.classList.remove("is-invalid");
        familiaProducto.classList.add("is-valid");
        familiaProductoValido = true;
    }
    
     if( !validarCategoriaProducto.test(categoriaProducto.value )){
        categoriaProducto.classList.remove('is-valid');
        categoriaProducto.classList.add("is-invalid");
        textoAlert+="\nLa categoria del producto no es valida";
        categoriaProductoValido=false;
        
    }else{
        categoriaProducto.classList.remove('is-invalid');
        categoriaProducto.classList.add("is-valid");
        categoriaProductoValido=true;
    }
    
     if( !validarMarcaProducto.test(marcaProducto.value )){
        marcaProducto.classList.remove('is-valid');
        marcaProducto.classList.add("is-invalid");
        textoAlert+="\nLa marca del producto no es valida";
        marcaProductoValido=false;
        
    }else{
        marcaProducto.classList.remove('is-invalid');
        marcaProducto.classList.add("is-valid");
        marcaProductoValido=true;
    }
     if( !validarStockProducto.test(stockProducto.value )){
        stockProducto.classList.remove('is-valid');
        stockProducto.classList.add("is-invalid");
        textoAlert+="\nEl stock del producto no es valida";
        stockProductoValido=false;
        
    }else{
        stockProducto.classList.remove('is-invalid');
        stockProducto.classList.add("is-valid");
        stockProductoValido=true;
    }
     if( !validarPrecioProducto.test(precioProducto.value )){
        precioProducto.classList.remove('is-valid');
        precioProducto.classList.add("is-invalid");
        textoAlert+="\nEl precio del producto no es valido";
        precioProductoValido=false;
        
    }else{
        precioProducto.classList.remove('is-invalid');
        precioProducto.classList.add("is-valid");
        precioProductoValido=true;
    }

    
    validado = nombreProductoValido && familiaProductoValido && categoriaProductoValido && marcaProductoValido && stockProductoValido && precioProductoValido;

    if(!validado){
        alert(textoAlert);
        event.preventDefault();
    }
});



