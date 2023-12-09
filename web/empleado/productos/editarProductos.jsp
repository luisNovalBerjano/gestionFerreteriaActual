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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script src="../js_productos/crear_editar_productos.js" defer></script>
        <link rel="stylesheet" type="text/css" href="../css/crear_editar_productos.css">
        <title>Editar Producto</title>
    </head>
    <body>
        <h1>Editar Producto</h1>
        <br>
        <form method="post">
            <input type="hidden" name="id_producto" value="${producto.id_producto}">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" name="nombreProducto" id="nombreProducto" class="form-control"
                   <c:if test="${error != ''}">value="${producto.nombre}" </c:if>
               required 
            >
            <br>
            <label for="familia" class="form-label">Familia</label>
            <input type="text" name="familiaProducto" value="${producto.familia}" id="familiaProducto" class="form-control"
                  <c:if test="${error != ''}">value="${producto.familia}" </c:if>  
                   required      
            >
            <br>
            <label for="categoria" class="form-label">Categoria</label>
            <input type="text" name="categoriaProducto" value="${producto.categoria}" id="categoriaProducto" class="form-control"
                    <c:if test="${error != ''}">value="${producto.categoria}" </c:if>
                   required
            >
            <label for="marca" class="form-label">Marca</label>
            <input type="text" name="marcaProducto" value="${producto.marca}" id="marcaProducto" class="form-control"
                  
                    <c:if test="${error != ''}">value="${producto.marca}" </c:if>
              required
            >
            <br>
            <label for="stock" class="form-label">Stock</label>
            <input type="number" name="stockProducto" min="0" value="${producto.stock}" id="stockProducto" class="form-control"
                    <c:if test="${error != ''}">value="${producto.stock}" </c:if>
                   required>
            <br>
            <label for="precio" class="form-label">Precio</label>
            <input type="number" step="0.01" min="0.00" name="precioProducto" value="${producto.precio}" id="precioProducto" class="form-control"
                    <c:if test="${error != ''}">value="${producto.precio}" </c:if>
                   required>
            <br>

            <input type="submit" value="Editar Producto" id="crearProducto" class="btn btn-primary">
            <a class="btn btn-secondary" id="volver" href="../empleado/MenuProductos">Volver</a>
        </form>
        <c:if test="${! empty error}">
            <div class="error">${error}</div>
        </c:if>
    </body>
</html>

