<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="../js_usuarios/crear.js" defer></script>
     <link rel="stylesheet" type="text/css" href="../css/crear_editar_usuario.css">

    <title>Crear usuario</title>
</head>
<body class="bg-light">
    <div class="container">
        <h1 class="mt-4 mb-4">Crear usuario</h1>
        <form method="post" id="crearUsuarioForm">
            <div class="mb-3">
                <label for="nombreCompleto" class="form-label">Nombre</label>
                <input type="text" name="nombreCompleto" id="nombreCompleto" class="form-control" value="${nombreCompleto}" required>
            </div>
            <div class="mb-3">
                <label for="nickName" class="form-label">Nick Name</label>
                <input type="text" name="nickName" id="nickName" class="form-control" value="${nickName}" required>
            </div>
            <div class="mb-3">
                <label for="pass" class="form-label">Contraseña</label>
                <input type="password" name="pass" id="pass" class="form-control" value="${pass}" required>
            </div>
            <div class="mb-3">
                <label for="rol" class="form-label">Rol</label>
                <select name="rol" id="rol" class="form-select" required> 
                    <option value="admin">Administrador</option>
                    <option value="empleado">Empleado</option>
                    <option value="cliente">Cliente</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="dni" class="form-label">DNI</label>
                <input type="text" name="dni" id="dni" class="form-control" value="${dni}" required>
            </div>
            <div class="mb-3">
                <label for="telefono" class="form-label">Teléfono</label>
                <input type="number" min="600000000" max="999999999" name="telefono" id="telefono" class="form-control" value="${telefono}" required>
            </div>
            <div class="mb-3">
                <label for="direccion" class="form-label">Dirección</label>
                <input type="text" name="direccion" id="direccion" class="form-control" value="${direccion}" required>
            </div>
            <div class="mb-3">
                <label for="codigoPostal" class="form-label">Código Postal</label>
                <input type="number" min="10000" max="99999" name="codigoPostal" id="codigoPostal" class="form-control" value="${codigoPostal}" required>
            </div>
            <div class="mb-3">
                <label for="mail" class="form-label">Email</label>
                <input type="email" name="mail" id="mail" class="form-control" value="${mail}" required>
            </div>

            <button type="submit" id="crearUsuario" class="btn btn-primary">Crear nuevo usuario</button>
            <a class="btn btn-secondary" id="volver" href="../admin/MenuUsuarios">Volver</a>
        </form>
            
        
        <c:if test="${!empty error}">
            <div class="alert alert-danger mt-3">${error}</div>
        </c:if>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
