/*
 * Servlet Controlador MenuDepartamentos.
 */
package controladores.empleado.ventas;

import controladores.empleado.productos.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.VentaJpaController;
import modelo.entidades.Usuario;



/**
 *
 * @author luisn
 */
@WebServlet(name = "MenuVentas", urlPatterns = {"/empleado/MenuVentas"})
public class MenuVentas extends HttpServlet {

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
        String vista = "/empleado/ventas/menuVentas.jsp";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionFerreteria"); 
        VentaJpaController djc = new VentaJpaController(emf);
        Usuario u = (Usuario)request.getSession().getAttribute("usuario");
            request.getSession().setAttribute("usuario", u);
        request.setAttribute("ventas", djc.findVentaEntities()); //Mandamos las ventas a la vista
        
        getServletContext().getRequestDispatcher(vista).forward(request, response);
        
    }

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
