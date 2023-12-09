<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="../js_usuarios/editar.js" defer></script>
    <link rel="stylesheet" type="text/css" href="../css/crear_editar_usuario.css">
    <title>Editar usuario</title>
</head>
<body class="bg-light">
    <div class="container">
        <h1 class="mt-4 mb-4">Editar usuario</h1>
        <form method="post">
            <input type="hidden" name="id_usuario" value="${usuario.id_usuario}">
            
            <div class="mb-3">
                <label for="nombreCompleto" class="form-label">Nombre</label>
                <input type="text" name="nombreCompleto" value="${usuario.nombreCompleto}" class="form-control" required id="nombreCompleto">
            </div>
            
            <div class="mb-3">
                <label for="nickName" class="form-label">Nick Name</label>
                <input type="text" name="nickName" value="${usuario.nickName}" class="form-control" required id="nickName">
            </div>
           
            
            <div class="mb-3">
                <label for="rol" class="form-label">Rol</label>
                <select name="rol" class="form-select" required id="rol"> 
                    <option value="admin" <c:if test="${usuario.rol == 'admin'}">selected</c:if>>Administrador</option>
                    <option value="empleado" <c:if test="${usuario.rol == 'empleado'}">selected</c:if>>Empleado</option>
                    <option value="cliente" <c:if test="${usuario.rol == 'cliente'}">selected</c:if>>Cliente</option>
                </select>
            </div>
            
            <div class="mb-3">
                <label for="dni" class="form-label">DNI</label>
                <input type="text" name="dni" value="${usuario.dni}" class="form-control" required id="dni">
            </div>
            
            <div class="mb-3">
                <label for="telefono" class="form-label">Teléfono</label>
                <input type="number" min="600000000" max="999999999" name="telefono" value="${usuario.telefono}" class="form-control" required id="telefono">
            </div>
            
            <div class="mb-3">
                <label for="direccion" class="form-label">Dirección</label>
                <input type="text" name="direccion" value="${usuario.direccion}" class="form-control" required id="direccion">
            </div>
            
            <div class="mb-3">
                <label for="codigoPostal" class="form-label">Código Postal</label>
                <input type="number" min="10000" max="99999" name="codigoPostal" value="${usuario.codigoPostal}" class="form-control" required id="codigoPostal">
            </div>
            
            <div class="mb-3">
                <label for="mail" class="form-label">Email</label>
                <input type="text" name="mail" value="${usuario.mail}" class="form-control" required id="mail">
            </div>

            <input type="submit" value="Editar usuario" class="btn btn-primary" id="crearUsuario">
           <a class="btn btn-secondary" id="volver" href="../admin/MenuUsuarios">Volver</a>
        </form>
            
        
        <c:if test="${! empty error}">
            <div class="alert alert-danger mt-3">${error}</div>
        </c:if>
    </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
