/*
 * Servlet Controlador EditarDepartamento.
 */
package controladores.empleado.productos;

import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
@WebServlet(name = "EditarProducto", urlPatterns = {"/empleado/EditarProducto"})
public class EditarProducto extends HttpServlet {

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
        String vista = "/empleado/productos/editarProductos.jsp";
        Long id = Long.valueOf(request.getParameter("id_producto"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionFerreteria");
        ProductoJpaController ujc = new ProductoJpaController(emf);
        Producto producto = ujc.findProducto(id);
        String error = "";

        if (request.getParameter("nombreProducto") != null && request.getParameter("categoriaProducto") != null && request.getParameter("familiaProducto")!= null 
                && request.getParameter("marcaProducto")!= null && request.getParameter("stockProducto") != null && request.getParameter("precioProducto")!= null ) {
            // Editando{
            // Editando
            String nombreProducto= request.getParameter("nombreProducto");
            
            String familiaProducto = request.getParameter("familiaProducto");

            String categoriaProducto = request.getParameter("categoriaProducto");

            String marcaProducto = request.getParameter("marcaProducto");
            
            Integer stockProducto = Integer.parseInt(request.getParameter("stockProducto"));
            
            Double precioProducto = Double.parseDouble(request.getParameter("precioProducto"));
            
            producto.setNombre(nombreProducto);
            producto.setFamilia(familiaProducto);
            producto.setCategoria(categoriaProducto);
            producto.setFamilia(familiaProducto);
            producto.setMarca(marcaProducto);
            producto.setStock(stockProducto);
            producto.setPrecio(precioProducto);
            

            if (nombreProducto.isEmpty()) {
                error = "El nombre del producto no puede estar vacio";
            } else if (familiaProducto.isEmpty()) {
                error = "Debe asignar una familia al producto";
            } else if (categoriaProducto.isEmpty()) {
                error = "Debe asignar una categoria al producto";
            } else if (marcaProducto.isEmpty()) {
                error = "Debe asignar una marca al producto";
            } else if (stockProducto < 0) {
                error = "El producto necesita tener un stock";
            } else if (precioProducto < 0.00) {
                error = "Debe asignar un precio al producto";
            } else {
                try {
                    ujc.edit(producto);
                    response.sendRedirect("MenuProductos");
                    return;
                } catch (Exception e) {
                    request.setAttribute("error", "Error editando el producto");
                    request.setAttribute("producto", producto);
                }
            }

            if (!error.isEmpty()) {
                request.setAttribute("error", error);
                request.setAttribute("producto", producto);

            }
        } else { //Si hay algun error editando dejamos el producto como estaba
            
            request.setAttribute("producto", producto);
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
