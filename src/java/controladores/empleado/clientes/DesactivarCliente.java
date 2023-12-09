/*
 * Servlet Controlador CrearDepartamento.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores.empleado.clientes;


import controladores.admin.usuario.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.ClienteJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Cliente;

/**
 *
 * @author luisn
 */
@WebServlet(name = "DesactivarCliente", urlPatterns = {"/empleado/DesactivarCliente"})
public class DesactivarCliente extends HttpServlet {

    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(request.getParameter("id_cliente")!=null){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionFerreteria");
            ClienteJpaController cjc= new ClienteJpaController(emf);
            Long idCliente=Long.valueOf(request.getParameter("id_cliente"));
            Cliente c= cjc.findCliente(idCliente);
            if(c.getEsta_activo()==true){
                c.setEsta_activo(false);
            }else{
                c.setEsta_activo(true);
            }
            try{
                cjc.edit(c);
                response.sendRedirect("./MenuClientes");
            }catch(Exception e){
                
            }
            
        }
        
        

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
