/*
 * Servlet Controlador EditarDepartamento.
 */
package controladores;

import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.UsuarioJpaController;
import modelo.entidades.Usuario;

/**
 *
 * @author luisn
 */
@WebServlet(name = "EditarPerfil", urlPatterns = {"/EditarPerfil"})
public class EditarPerfil extends HttpServlet {

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
            throws ServletException, IOException {
        String vista = "/editarPerfil.jsp";
        

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionFerreteria");
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        String error = "";
        
        if(request.getSession().getAttribute("usuario")!= null){
            Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
            request.getSession().setAttribute("usuario", usuario);
            
            if (request.getParameter("nombreCompleto") != null) {
            // Editando
            String nombreCompleto = request.getParameter("nombreCompleto");

          
            String rol = usuario.getRol();

            String nickName = request.getParameter("nickName");
            
            String dni = usuario.getDni();
            
            Integer codigoPostal = Integer.parseInt(request.getParameter("codigoPostal"));
            
            Integer telefono = Integer.parseInt(request.getParameter("telefono"));
            
            String mail = request.getParameter("mail");
             
            String direccion = request.getParameter("direccion");

            String pass=usuario.getPass();

            usuario.setNombreCompleto(nombreCompleto);
            usuario.setNickName(nickName);
            usuario.setCodigoPostal(codigoPostal);
            usuario.setTelefono(telefono);
            usuario.setMail(mail);
            usuario.setDireccion(direccion);
            usuario.setRol(rol);
            usuario.setDni(dni);
            

            if (nombreCompleto.isEmpty()) {
                error = "El nombre no puede estar vacío";
            } else if (rol.isEmpty()) {
                error = "El rol no puede estar vacío";
            } else if (nickName.isEmpty()) {
                error = "El nickname no puede estar vacío";
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
                try {
                    ujc.edit(usuario);
                    response.sendRedirect("./EditarPerfil");
                    return;
                } catch (Exception e) {
                    request.setAttribute("error", "Error editando el usuario");
                    request.setAttribute("usuario", usuario);
                }
            }

            if (!error.isEmpty()) {
                request.setAttribute("error", error);
                request.setAttribute("usuario", usuario);

            }
        } else { //Si hay algun error editando dejamos el usuario como estaba
            
            request.setAttribute("usuario", usuario);
        }
            
            
        }else{
            error = "El usuario no es valido"; 
            response.sendRedirect("Login");
                    return;
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
        processRequest(request, response);
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
        processRequest(request, response);
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
