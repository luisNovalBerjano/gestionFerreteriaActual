<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- Incluye la hoja de estilos de Bootstrap 5 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" type="text/css" href="./css/login.css">
    
    <title>Gestión Ferretería</title>
    
   
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="login-box"> <!-- Caja del formulario -->
            <img src="./imagenes/logoLargo.png" alt="Logo LEDH">
            <form method="post" class="mt-4">
                <div class="mb-3">
                    <label for="mail" class="form-label">Email</label>
                    <input type="email" id="mail" name="mail" class="form-control" value="${mail}" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña</label>
                    <input type="password" id="password" name="password" class="form-control" value="${password}" required>
                </div>
                <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
            </form>

            <c:if test="${not empty error}">
                <div class="alert alert-danger mt-3">
                    ${error}
                </div>
            </c:if>
        </div>
    </div>
</body>
</html>
