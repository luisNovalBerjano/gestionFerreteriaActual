class Producto {
    constructor(id, nombre, precio, stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }
}

const body = document.body;
var contenedor = document.getElementById("productosDocumento");
var productos = [];
var preciosParciales = [];
var contadorProductos = 1;
var productosSeleccionados = [];
var contadorClicks=0;   
var precioBruto = document.getElementById("precioBruto");
precioBruto.value=0;

let selectEmpleadoValido=false;
let selectClienteValido=false;

async function recuperarProductos() {
    var nuevoContenedor = document.createElement("div");
    nuevoContenedor.setAttribute("id", "productosDocumento" + contadorProductos);
    let select = document.createElement("select");
    select.setAttribute("name", "selectProductos" + contadorProductos);
    select.setAttribute("id", "selectProductos" + contadorProductos);
    select.setAttribute("required", "required");
    if(select.value===""){
        document.getElementById("nuevoProducto").disabled=true;
    
    }

    const response = await fetch(`../Rest/obtenerProductos`);
    const data = await response.json();

    select.innerHTML = "";
    const optionPred = document.createElement('option');
    optionPred.text = "Seleccione un producto";
    
    select.add(optionPred);

    data.forEach(producto => {
       
        if (!productosSeleccionados.includes(producto.id_producto)) {
            const option = document.createElement('option');
            option.text = producto.nombre;
            option.value = producto.id_producto;
            select.add(option);

            const nuevoProducto = new Producto(
                producto.id_producto,
                producto.nombre,
                producto.precio,
                producto.stock
            );
            productos.push(nuevoProducto);
        }
    });

    nuevoContenedor.appendChild(select);
    contenedor.appendChild(nuevoContenedor);
    contadorProductos++;
    
}

document.getElementById("nuevoProducto").addEventListener("click", (event) => {
     document.getElementById("nuevoProducto").disabled=true;
    
    event.preventDefault();
    recuperarProductos();
});

var productoEncontrado = null;
var indiceContenedor = 0;
var contadorContendor = [null, 0];

contenedor.addEventListener("change", (event) => {
    const target = event.target;

    
        
    if (target.tagName === 'SELECT' && target.id.startsWith("selectProductos") && target.value!=="") {
            document.getElementById("nuevoProducto").disabled=false;
            cambiarIds();
         
        const contenedorPadre = target.closest("div"); //El div que contiene el select
        indiceContenedor = Array.from(contenedor.children).indexOf(contenedorPadre);

        if (!contadorContendor[indiceContenedor]) {
            contadorContendor.push(0);
        }

        const productoSeleccionadoId = parseInt(target.value);
        productoEncontrado = productos.find(producto => producto.id === productoSeleccionadoId);

        const stockDisponible = contenedorPadre.querySelector("#stockDisponible" + indiceContenedor);
        const precioUnitario = contenedorPadre.querySelector("#precioUnitario" + indiceContenedor);
        const inputCantidad = contenedorPadre.querySelector("#inputCantidad" + indiceContenedor);

        if (productoEncontrado && contadorContendor[indiceContenedor] === 0) {
            let stockDisponible = document.createElement("span");
            stockDisponible.setAttribute("id", "stockDisponible" + indiceContenedor);

            let precioUnitario = document.createElement("span");
            precioUnitario.setAttribute("id", "precioUnitario" + indiceContenedor);

            let inputCantidad = document.createElement("input");
            inputCantidad.setAttribute("type", "number");
            inputCantidad.setAttribute("id", "inputCantidad" + indiceContenedor);
            inputCantidad.setAttribute("required", "required");
            inputCantidad.setAttribute("min", "0");
            inputCantidad.setAttribute("max", productoEncontrado.stock.toString());
            inputCantidad.setAttribute("value", "");
            inputCantidad.setAttribute("required", "required");
            
            stockDisponible.innerHTML = "Stock disponible: " + productoEncontrado.stock + " Uds  ||  ";
            precioUnitario.innerHTML = "Precio unitario: " + productoEncontrado.precio + "?";

            contenedorPadre.appendChild(stockDisponible);
            contenedorPadre.appendChild(precioUnitario);
            contenedorPadre.appendChild(inputCantidad);

            let btnEliminar = document.createElement("button");
            btnEliminar.innerText = "Eliminar";
            btnEliminar.addEventListener("click", function () {
                const select = contenedorPadre.querySelector("select");
                const productoId = parseInt(select.value);

                const index = productosSeleccionados.indexOf(productoId);
                if (index !== -1) {
                    productosSeleccionados.splice(index, 1);
                }
                
                contenedor.removeChild(contenedorPadre);
                contadorContendor.splice(indiceContenedor, 1);

                preciosParciales = preciosParciales.filter(item => item.id !== "inputCantidad" + indiceContenedor);

                
                let precioFinal = 0;

                for (let i = 0; i < preciosParciales.length; i++) {
                    precioFinal += parseFloat(preciosParciales[i].total);
                }

                precioBruto.value = parseFloat(precioFinal.toFixed(2));
                console.log(precioBruto);
            });
            contenedorPadre.appendChild((btnEliminar));
            contadorContendor[indiceContenedor]++;
            productosSeleccionados.push(productoEncontrado.id);
            target.disabled = true;
        }
        
        let productosUnicos = [...new Set(productos)];
        
       
    if (todosEnSeleccionados(productosUnicos, productosSeleccionados)) {
        document.getElementById("nuevoProducto").disabled=true;
        alert("No se pueden añadir mas productos.");
}
        
    }else

    if (target.tagName === 'INPUT' && target.id.startsWith("inputCantidad") && productoEncontrado) {
        inputCantidad = document.getElementById("inputCantidad" + indiceContenedor);
        var selectedProduct = {
            id: inputCantidad.id,
            cantidad: inputCantidad.value,
            precio: productoEncontrado.precio,
            total: inputCantidad.value * productoEncontrado.precio
        };

        const encontrado = preciosParciales.find(item => item.id === selectedProduct.id);

        if (encontrado) {
            const indice = preciosParciales.indexOf(encontrado);
            preciosParciales[indice] = selectedProduct;
        } else {
            preciosParciales.push(selectedProduct);
        }

        
        let precioFinal = 0;

        for (let i = 0; i < preciosParciales.length; i++) {
            precioFinal += parseFloat(preciosParciales[i].total);
        }
        
        precioBruto.value = parseFloat(precioFinal.toFixed(2));
        console.log(precioBruto);
    }else{
       event.stopPropagation();
    }
});

function cambiarIds() {
    // Expresión regular para buscar divs cuyos IDs comiencen con "productosDocumento" seguido de uno o más dígitos
    const regexId = /^productosDocumento\d+$/;
    const regexStock = /^stockDisponible\d+$/;
    const regexPrecioUnitario = /^precioUnitario\d+$/;
    const regexInputCantidad = /^inputCantidad\d+$/;
    const regexSelect = /^selectProductos\d+$/;
    
    
    // Seleccionar todos los elementos del documento
    const elementos = document.querySelectorAll('div');

    // Inicializar contador
    let contador = 0;

    // Iterar sobre los elementos y contar aquellos cuyo ID cumpla con el patrón de la expresión regular
    elementos.forEach(elemento => {
        if (elemento.id && regexId.test(elemento.id)) {
            contador++;
            elemento.setAttribute("id", "productosDocumento" + contador);
            const todosLosHijos = elemento.children;

            for (let i = 0; i < todosLosHijos.length; i++) {
                if (todosLosHijos[i].id && regexStock.test(todosLosHijos[i].id)) {
                    todosLosHijos[i].setAttribute("id", "stockDisponible" + contador);
                    todosLosHijos[i].setAttribute("name", "stockDisponible" + contador);
                    
                }
                if (todosLosHijos[i].id && regexInputCantidad.test(todosLosHijos[i].id)) {
                    todosLosHijos[i].setAttribute("id", "inputCantidad" + contador);
                    todosLosHijos[i].setAttribute("name", "inputCantidad" + contador);
                }
                if (todosLosHijos[i].id && regexPrecioUnitario.test(todosLosHijos[i].id)) {
                    todosLosHijos[i].setAttribute("id", "precioUnitario" + contador);
                    todosLosHijos[i].setAttribute("name", "precioUnitario" + contador);
                }
                if(todosLosHijos[i].id && regexSelect.test(todosLosHijos[i].id) ){
                    todosLosHijos[i].setAttribute("id", "selectProductos" + contador);
                    todosLosHijos[i].setAttribute("name", "selectProductos" + contador);
                }
            }
        }
        document.getElementById("numeroProductos").value=contador;
    });
}

// Evento para eliminar un producto y actualizar el precio total
contenedor.addEventListener("click", (event) => {
    if (event.target.tagName === 'BUTTON' && event.target.innerText === 'Eliminar') {
        const contenedorPadre = event.target.parentNode;
        const select = contenedorPadre.querySelector("select");
        const productoId = parseInt(select.value);

        const index = productosSeleccionados.indexOf(productoId);
        if (index !== -1) {
            productosSeleccionados.splice(index, 1);
        }

        
        cambiarIds();
        contadorContendor.splice(indiceContenedor, 1);

        preciosParciales = preciosParciales.filter(item => item.id !== "inputCantidad" + indiceContenedor);

        
        let precioFinal = 0;

        for (let i = 0; i < preciosParciales.length; i++) {
            precioFinal += parseFloat(preciosParciales[i].total);
        }
        
        precioBruto.value = parseFloat(precioFinal.toFixed(2));
        console.log(precioBruto);
    }
    
    
    
});

function todosEnSeleccionados(productos, productosSeleccionados) {
    
    return productos.every(producto => productosSeleccionados.includes(producto.id));
    }

// Obtener el formulario
const formulario = document.getElementById('miFormulario');

// Agregar un evento al submit del formulario





const nuevaVenta=document.getElementById("nuevaVenta");

nuevaVenta.addEventListener("click",()=>{
    textoAlert="";
    var validarStockProducto = /^\d+$/;
    var validarSelects=/^\d+$/;
  
    var selectProductosValidos=true;
    var cantidadesValidas=true;
    var formularioValido=false;
    var empleadoValido=false;
    var clienteValido=false;
    var documentoValido=false;
    
    var selectNombreEmpleado=document.getElementById("nombreEmpleado");
    var selectNombreCliente=document.getElementById("nombreCliente");
    var selectTipoDocumento=document.getElementById("tipoDocumento");
    
    if(document.getElementById("numeroProductos").value>=1){
        
    
    
    for (let i=1; i<=document.getElementById("numeroProductos").value;i++){
        
        if(!validarSelects.test(document.getElementById("selectProductos"+i).value)){
            selectProductosValidos=false;
            
            if(i===1){
                textoAlert+="El producto "+i+" no es valido";
                
            }else{
                textoAlert+="\nEl producto "+i+" no es valido";
            }
            
        }
        
        if(!validarStockProducto.test(document.getElementById("inputCantidad"+i).value)){
            cantidadesValidas=false;
            if(i===1){
                textoAlert+="La cantidad del producto "+i+" no es valida";
                
            }else{
                textoAlert+="\nLa cantidad del producto "+i+" no es valida";
            }
        
            
        }
    }
    
    if(!validarSelects.test(selectNombreCliente.value)){
        clienteValido=false;
        textoAlert+="\nEl cliente seleccionado no es valido";
    }else{
        clienteValido=true;
    }
    if(!validarSelects.test(selectNombreEmpleado.value)){
        empleadoValido=false;
           textoAlert+="\nEl empleado seleccionado no es valido";
    }else{
        empleadoValido=true;
    }
    if(!validarSelects.test(selectTipoDocumento.value)){
        documentoValido=false;
           textoAlert+="\nEl documento seleccionado no es valido";
    }else{
        documentoValido=true;
    }

formularioValido=clienteValido&&documentoValido&&empleadoValido&&selectProductosValidos&&cantidadesValidas;

if(!formularioValido){
    event.preventDefault();
        alert(textoAlert);
}
    }else{
        textoAlert="Por favor seleccione al menos un producto para la venta";
        alert(textoAlert);
        event.preventDefault();
    }
});

formulario.addEventListener('submit', function(event) {
    // Obtener todos los elementos del formulario
    const elementosFormulario = formulario.elements;

    // Recorrer cada elemento del formulario
    for (let i = 0; i < elementosFormulario.length; i++) {
        // Verificar si el elemento está deshabilitado
        if (elementosFormulario[i].disabled) {
            // Si está deshabilitado, quitar la propiedad disabled
            elementosFormulario[i].removeAttribute('disabled');
        }
    }
   
});
