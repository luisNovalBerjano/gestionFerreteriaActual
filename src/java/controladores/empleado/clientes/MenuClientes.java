/*
 * Servlet Controlador MenuDepartamentos.
 */
package controladores.empleado.clientes;

import controladores.admin.usuario.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
@WebServlet(name = "MenuClientes", urlPatterns = {"/empleado/MenuClientes"})
public class MenuClientes extends HttpServlet {

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
        String vista = "/empleado/clientes/menuClientes.jsp";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionFerreteria"); 
        UsuarioJpaController djc = new UsuarioJpaController(emf);
        ClienteJpaController cjc= new ClienteJpaController(emf);
        List<Usuario>usuarios=new ArrayList<>();
        List<Cliente>clientes=new ArrayList<>();
        clientes=cjc.findClienteEntities();
        usuarios=djc.findUsuarioEntities();
        List<Usuario>usuariosFiltrado=new ArrayList<>();
        
        
        for(int i=0;i<usuarios.size();i++){
        
       if(usuarios.get(i).getRol().equals("cliente")){
            usuariosFiltrado.add(usuarios.get(i));
        }
       
        
       
        }
         request.setAttribute("clientes", clientes); //Mandamos los usuarios a la vista
        request.setAttribute("usuarios", usuariosFiltrado);
        
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
