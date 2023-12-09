

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        
         <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../css/menus.css">
        
        
        <title>Usuarios</title>
    </head>
    <body>
        <header>

            <c:if test="${usuario.rol == 'admin'}">
                <nav class="navbar navbar-expand-lg " style="background-color: #3D55A2">
                    <div class="container" >
                        <a class="navbar-brand" href=".././Inicio">
                            <img src="../imagenes/logo.png" alt="Logo LEDH">
                        </a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse text-white"  id="navbarNav">
                            <ul class="navbar-nav ml-auto">
                                <li class="nav-item">
                                    <a class="nav-link text-white" href="./../admin/MenuUsuarios">Usuarios</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-white" href="MenuClientes">Clientes</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-white" href="./admin/MenuFacturacion">Facturación</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-white" href="./../empleado/MenuProductos">Productos</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-white" href="./../empleado/MenuVentas">Ventas</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link text-white" href="../CerrarSesion">Cerrar Sesion</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </c:if>
            <c:if test="${usuario.rol == 'empleado'}">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container">
                        <a class="navbar-brand" href="./Inicio">LEDH (Imagen)

                        </a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNav">
                            <ul class="navbar-nav ml-auto">
                                <li class="nav-item">
                                    <a class="nav-link" href="./empleado/productos/MenuProductos">Productos</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="./empleado/MenuVentas">Ventas</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="../CerrarSesion">Cerrar Sesion</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </c:if>
            <c:if test="${usuario.rol == 'cliente'}">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container">
                        <a class="navbar-brand" href="./Inicio">LEDH (Imagen)
                        </a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNav">
                            <ul class="navbar-nav ml-auto">
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Mis documentos</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="../CerrarSesion">Cerrar Sesion</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </c:if>
        </header>
        <div class="container" id="margen">
            <h1 class="mt-4">Gestion De Clientes</h1>

            <table class="table mt-4">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>NickName</th>
                        <th>DNI</th>
                        <th>Direccion</th>c
                        <th>Codigo Postal</th>
                        <th>Correo</th>
                        <th>Activar/Desactivar</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${usuarios}" var="u">
                        <c:forEach items="${clientes}" var="c">
                            <c:if test="${u.id_usuario == c.id_usuario.id_usuario}"> 
                                
                            
                        <tr>
                            <td><c:out value="${u.nombreCompleto}"/></td>
                            <td><c:out value="${u.nickName}"/></td>
                            <td><c:out value="${u.dni}"/></td>
                            <td><c:out value="${u.direccion}"/></td>
                            <td><c:out value="${u.codigoPostal}"/></td>
                            <td><c:out value="${u.mail}"/></td>
                            <td>
                                <form action="DesactivarCliente" method="post">
                                    <input type="hidden" name="id_cliente" value="${c.id_cliente}">
                                    <button type="submit" class="btn btn-primary"> <c:if test="${c.esta_activo==true}">Desactivar</c:if> <c:if test="${c.esta_activo==false}">Activar</c:if></button>
                                </form>
                            </td>
                            
                        </tr>
                        </c:if>
                        </c:forEach>
                    </c:forEach>
                </tbody>
            </table>


            <a class="btn btn-secondary mb-4" id="margen"  href=".././Inicio">Volver</a>
        </div>
         <footer
                class="text-center text-lg-start text-white"
                >

            <!-- Grid container -->
            <div class="container p-4 pb-0">
                <hr class="my-1">
                <!-- Section: Links -->
                <section class="">
                    <!--Grid row-->
                    <div class="row">
                        <!-- Grid column -->
                        <div class="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
                            <h6 class="text-uppercase mb-4 font-weight-bold">
                                La Electrica Del Hogar
                            </h6>
                            <p>
                                Material de iluminación & soluciones eléctricas, encuentra tus materiales y solicita presupuesto para cualquier aviso.
                            </p>
                        </div>
                        <!-- Grid column -->

                        <div class="col-md-4 mx-auto text-center pt-5">
                            <img src="../imagenes/footer.png" alt="Logo LEDH">

                            <!-- Social Media Links -->
                            <div class="pt-2">
                            <a href="https://www.facebook.com/profile.php?id=100063674918309" class="btn btn-outline-light btn-floating m-1 text-white" role="button">
                                <i class="fab fa-facebook-f"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-facebook" viewBox="0 0 16 16">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-facebook" viewBox="0 0 16 16">
                                    <path d="M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951z"/>
                                    </svg>
                                </i>
                            </a>
                            <a href="https://maps.app.goo.gl/9u7fvcLCPhkLYGxJ8"
                               class="btn btn-outline-light btn-floating m-1 text-white" role="button">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-geo-alt" viewBox="0 0 16 16">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-map" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M15.817.113A.5.5 0 0 1 16 .5v14a.5.5 0 0 1-.402.49l-5 1a.502.502 0 0 1-.196 0L5.5 15.01l-4.902.98A.5.5 0 0 1 0 15.5v-14a.5.5 0 0 1 .402-.49l5-1a.5.5 0 0 1 .196 0L10.5.99l4.902-.98a.5.5 0 0 1 .415.103zM10 1.91l-4-.8v12.98l4 .8V1.91zm1 12.98 4-.8V1.11l-4 .8v12.98zm-6-.8V1.11l-4 .8v12.98l4-.8z"/>
                                </svg>
                                </svg>
                            </a>
                            <a href="https://instagram.com/laelectricadelhogar?igshid=NzZlODBkYWE4Ng==" class="btn btn-outline-light btn-floating m-1 text-white" role="button">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-instagram" viewBox="0 0 16 16">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-instagram" viewBox="0 0 16 16">
                                <path d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.917 3.917 0 0 0-1.417.923A3.927 3.927 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.916 3.916 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.926 3.926 0 0 0-.923-1.417A3.911 3.911 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0h.003zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599.28.28.453.546.598.92.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.47 2.47 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.478 2.478 0 0 1-.92-.598 2.48 2.48 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233 0-2.136.008-2.388.046-3.231.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92.28-.28.546-.453.92-.598.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045v.002zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92zm-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217zm0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334z"/>
                                </svg>
                                </svg>
                            </a>
                               </div>
                        </div>

                        <!-- Grid column -->
                        <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mt-3">
                            <h6 class="text-uppercase mb-4 font-weight-bold">Contacto</h6>
                            <p><i class="fas fa-home mr-3"></i>Plaza Del Aljarafe Nº8 Bajo IZQ, CP 41005 (Sevilla)</p>
                            <p><i class="fas fa-envelope mr-3"></i> oficial.ledh@gmail.com</p>
                            <p><i class="fas fa-phone mr-3"></i><a class="text-white" href=tel:+34601043298>Servicio de Whatsapp</a> <a href="https://wa.me/34669709800"
                                                                                                                                            class="btn btn-outline-light btn-floating m-1"
                                                                                                                                            class="text-white"
                                                                                                                                            role="button"
                                                                                                                                            ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-whatsapp" viewBox="0 0 16 16">
                                    <path d="M13.601 2.326A7.854 7.854 0 0 0 7.994 0C3.627 0 .068 3.558.064 7.926c0 1.399.366 2.76 1.057 3.965L0 16l4.204-1.102a7.933 7.933 0 0 0 3.79.965h.004c4.368 0 7.926-3.558 7.93-7.93A7.898 7.898 0 0 0 13.6 2.326zM7.994 14.521a6.573 6.573 0 0 1-3.356-.92l-.24-.144-2.494.654.666-2.433-.156-.251a6.56 6.56 0 0 1-1.007-3.505c0-3.626 2.957-6.584 6.591-6.584a6.56 6.56 0 0 1 4.66 1.931 6.557 6.557 0 0 1 1.928 4.66c-.004 3.639-2.961 6.592-6.592 6.592zm3.615-4.934c-.197-.099-1.17-.578-1.353-.646-.182-.065-.315-.099-.445.099-.133.197-.513.646-.627.775-.114.133-.232.148-.43.05-.197-.1-.836-.308-1.592-.985-.59-.525-.985-1.175-1.103-1.372-.114-.198-.011-.304.088-.403.087-.088.197-.232.296-.346.1-.114.133-.198.198-.33.065-.134.034-.248-.015-.347-.05-.099-.445-1.076-.612-1.47-.16-.389-.323-.335-.445-.34-.114-.007-.247-.007-.38-.007a.729.729 0 0 0-.529.247c-.182.198-.691.677-.691 1.654 0 .977.71 1.916.81 2.049.098.133 1.394 2.132 3.383 2.992.47.205.84.326 1.129.418.475.152.904.129 1.246.08.38-.058 1.171-.48 1.338-.943.164-.464.164-.86.114-.943-.049-.084-.182-.133-.38-.232z"/>
                                    </svg>
                                </a> </p>

                            <p><i class="fas fa-print mr-3"></i> <a href="8VR5+GX Bollullos de la Mitación"></a></p>
                        </div>
                        <hr class="my-3 mb-4">
                        <!-- Grid column -->
                    </div>
                    <!--Grid row-->
                </section>
                <!-- Section: Links -->

                

            </div>
        </footer>

        
    </body>
</html>
