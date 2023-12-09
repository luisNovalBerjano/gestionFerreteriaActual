<%-- 
    Document   : crearProductos.jsp
    Created on : 07-nov-2023, 19:54:58
    Author     : luisn
--%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
        <script src="../js_ventas/ventas.js" defer></script>
        <link rel="stylesheet" type="text/css" href="../css/crearVenta.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <title>Crear Venta</title>
    </head>
    <body>
        <h1 class="mt-4">Crear Venta</h1>
        <br>
        
        <form method="post" id="miFormulario">
            <label for="empleado">Empleado</label>
            <select   name="empleado" id="nombreEmpleado">
                <option value="">Seleccione un empleado</option>
                <c:forEach items="${empleados}" var="e">
                    <c:if test="${e.id_usuario==usuario.id_usuario}"> 
                        <option value="${e.id_usuario}" selected>${e.nombreCompleto} "  </option>
                    </c:if>
                    <option value="${e.id_usuario}">${e.nombreCompleto}"</option>
                </c:forEach>
            </select>
            <br>
            <label for="cliente">Cliente</label>
            <select name="cliente" id="nombreCliente">
                <option value="">Seleccione un cliente</option>
                <c:forEach items="${clientes}" var="c">
                   
                    <option value="${c.id_cliente}">${c.id_usuario.nombreCompleto}"</option>
                </c:forEach>
            </select>
            <br>
            
            
            
             <label for="tipoDocumento">Tipo de documento</label>
             <select name="tipoDocumento" id="tipoDocumento" required="">
                 <option value="0">Nota</option>
                 <option value="1">Ticket</option>
                 <option value="2">Factura</option>
             </select>
             
             <div id="productosDocumento">
                 <button id="nuevoProducto">
                    Añadir nuevo producto
                 </button>
             </div>
             <label for="precioBruto">Precio Bruto</label>
             <input type="text" name="precioBruto"    disabled id="precioBruto"  />
             <input type="hidden" id="numeroProductos" name="numeroProductos" value="0"/>
             <br>
             <input type="submit" value="crear nueva venta" id="nuevaVenta"/>
             <a class="btn btn-secondary" id="volver" href="../empleado/MenuVentas">Volver</a>
             
        </form>
        <c:if test="${! empty error}">
            <div class="error">${error}</div>
        </c:if>
             <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

