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
        <title>Crear Producto</title>
    </head>
    <body>
        <h1>Crear Producto</h1>
        <br>
        <form method="post">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" name="nombre" id="nombreProducto" class="form-control"
                   <c:if test="${error != ''}">value="${nombre}" </c:if>
               required 
            >
            <br>
            <label for="familia" class="form-label">Familia</label>
            <input type="text" name="familia" value="${familia}" id="familiaProducto" class="form-control"
                  <c:if test="${error != ''}">value="${familia}" </c:if>  
                   required      
            >
            <br>
            <label for="categoria" class="form-label">Categoria</label>
            <input type="text" name="categoria" value="${categoria}" id="categoriaProducto" class="form-control"
                    <c:if test="${error != ''}">value="${categoria}" </c:if>
                   required
            >
            <label for="marca" class="form-label">Marca</label>
            <input type="text" name="marca" value="${marca}" id="marcaProducto" class="form-control"
                  
                    <c:if test="${error != ''}">value="${marca}" </c:if>
              required
            >
            <br>
            <label for="stock" class="form-label">Stock</label>
            <input type="number" name="stock" min="0" value="${stock}" id="stockProducto" class="form-control"
                    <c:if test="${error != ''}">value="${stock}" </c:if>
                   required>
            <br>
            <label for="precio" class="form-label">Precio</label>
            <input type="number" step="0.01" min="0.00" name="precio" value="${precio}" id="precioProducto"
                    <c:if test="${error != ''}">value="${precio}" </c:if>
                   required>
            <br>
            <div id="botones">
            <input type="submit" value="Crear nuevo producto" id="crearProducto" class="btn btn-primary">
            <a class="btn btn-secondary" id="volver" href="../empleado/MenuProductos">Volver</a>
            </div>
        </form>
        <c:if test="${! empty error}">
            <div class="error">${error}</div>
        </c:if>
             <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>

