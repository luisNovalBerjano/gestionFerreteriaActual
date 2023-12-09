/*
 * Servlet Controlador CrearDepartamento.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.admin.usuario;

import encriptado.Encriptado;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.ClienteJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Cliente;
import modelo.entidades.Usuario;

/**
 *
 * @author luisn
 */
@WebServlet(name = "CrearUsuario", urlPatterns = {"/admin/CrearUsuario"})
public class CrearUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        String vista = "/admin/usuarios/crearUsuario.jsp";

        String nombreCompleto = "";

        String rol = "";

        String nickName = "";

        String pass = "";

        String dni = "";

        Integer codigoPostal = 0;

        Integer telefono = 0;

        String mail = "";

        String direccion = "";
        
        String error = "";

        if (request.getParameter("nombreCompleto") != null && request.getParameter("rol") != null &&  request.getParameter("nickName") != null 
                &&  request.getParameter("pass") != null &&  request.getParameter("dni") != null &&  request.getParameter("codigoPostal") != null
                &&  request.getParameter("telefono") != null && request.getParameter("mail") != null &&  request.getParameter("direccion") != null) { 
                //Si no es null es cuando se crea el usuario
                
            nombreCompleto = request.getParameter("nombreCompleto");
            rol = request.getParameter("rol");
            nickName = request.getParameter("nickName");
            pass = Encriptado.encriptaContrase�a(request.getParameter("pass"));
            dni = request.getParameter("dni");
            codigoPostal = Integer.parseInt(request.getParameter("codigoPostal"));
            telefono = Integer.parseInt(request.getParameter("telefono"));
            mail = request.getParameter("mail");
            direccion = request.getParameter("direccion");
            

           if (nombreCompleto.isEmpty()) {
                error = "El nombre no puede estar vacío";
            } else if (rol.isEmpty()) {
                error = "El rol no puede estar vacío";
            } else if (nickName.isEmpty()) {
                error = "El nickname no puede estar vacío";
            } else if (pass.isEmpty()) {
                error = "La contraseña no puede estar vacía";
            } else if (dni.isEmpty()) {
                error = "El dni no puede estar vacía";
            } else if (codigoPostal < 10000 && codigoPostal > 99999) {
                error = "El codigo postal tiene que tener 5 digitos";    
            } else if (telefono < 600000000 && telefono > 999999999) {
                error = "El telefono tiene que tener 9 digitos";   
            } else if (mail.isEmpty()) {
                error = "El mail no puede estar vacío";
            } else if (direccion.isEmpty()) {
                error = "La direccion no puede estar vacia";    
            } else {
            Usuario usuario = new Usuario();
            usuario.setNombreCompleto(nombreCompleto);
            usuario.setRol(rol);
            usuario.setNickName(nickName);
            usuario.setPass(pass);
            usuario.setDni(dni);
            usuario.setCodigoPostal(codigoPostal);
            usuario.setTelefono(telefono);
            usuario.setMail(mail);
            usuario.setDireccion(direccion);
            
            
            

                EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionFerreteria");
                UsuarioJpaController ujc = new UsuarioJpaController(emf); //Llamamos controlador JPA USUARIO
                try { //Persistimos los datos en la base de datos
                    ujc.create(usuario);
                    if(rol.equals("cliente")){
                        ClienteJpaController cjc = new ClienteJpaController(emf); 
                        Cliente cliente = new Cliente();
                        cliente.setId_usuario(usuario);
                        cliente.setEsta_activo(Boolean.TRUE);
                        cjc.create(cliente);
                        
                        
                    }
                    response.sendRedirect("MenuUsuarios"); // 
                    return;
                } catch (RollbackException e) {
                    error = "El usuario ya existe";
                }
            }
        }
        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.setAttribute("nombreCompleto", nombreCompleto);
            request.setAttribute("rol", rol);
            request.setAttribute("nickName", nickName);
            request.setAttribute("dni", dni);
            request.setAttribute("pass", pass);
            request.setAttribute("mail", mail);
            request.setAttribute("telefono", telefono);
            request.setAttribute("direccion", direccion);
            request.setAttribute("codigoPostal", codigoPostal);
        }
        getServletContext().getRequestDispatcher(vista).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CrearUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CrearUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
