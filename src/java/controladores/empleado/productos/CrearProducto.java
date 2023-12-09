package controladores.empleado.productos;

import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.ProductoJpaController;
import modelo.entidades.Producto;

/**
 *
 * @author luisn
 */
@WebServlet(name = "CrearProducto", urlPatterns = {"/empleado/CrearProducto"})
public class CrearProducto extends HttpServlet {

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
        String vista = "/empleado/productos/crearProductos.jsp";

        String nombre = "";

        String marca = "";

        String familia = "";

        String categoria = "";

        Integer stock = 0;

        Double precio = 0.0;
        
        String error = "";

        

        if (request.getParameter("nombre") != null && request.getParameter("marca") != null &&  request.getParameter("familia") != null 
                &&  request.getParameter("categoria") != null &&  request.getParameter("stock") != null &&  request.getParameter("precio") != null) { 
                //Si no es null es cuando se crea el producto
                
            nombre = request.getParameter("nombre");
            marca = request.getParameter("marca");
            categoria = request.getParameter("categoria");
            familia = request.getParameter("familia");
            stock = Integer.parseInt(request.getParameter("stock"));
            precio = Double.parseDouble(request.getParameter("precio")) ;
           

           if (nombre.isEmpty()) {
                error = "El nombre no puede estar vacío";
            } else if (marca.isEmpty()) {
                error = "El producto debe de tener marca asignada";
            } else if (familia.isEmpty()) {
                error = "El producto debe pertenecer a una familia";
            } else if (categoria.isEmpty()) {
                error = "El producto debe pertenecer a alguna categoria";
            } else if (stock < 0) {
                error = "El stock no puede ser negativo";    
            } else if (precio < 0.0) {
                error = "El precio no puede ser negativo";   
            } else {
            Producto producto = new Producto();
            producto.setNombre(nombre);
            producto.setMarca(marca);
            producto.setFamilia(familia);
            producto.setCategoria(categoria);
            producto.setStock(stock);
            producto.setPrecio(precio);
            
            
            

                EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionFerreteria");
                ProductoJpaController pjc = new ProductoJpaController(emf); //Llamamos controlador JPA USUARIO
                try { //Persistimos los datos en la base de datos
                    pjc.create(producto);
                    response.sendRedirect("MenuProductos"); // 
                    return;
                } catch (RollbackException e) {
                    error = "El producto ya existe";
                }
            }
        }
        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.setAttribute("nombre", nombre);
            request.setAttribute("marca", marca);
            request.setAttribute("familia", familia);
            request.setAttribute("categoria", categoria);
            request.setAttribute("stock", stock);
            request.setAttribute("precio", precio);
           
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