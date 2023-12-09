package controladores.empleado.ventas;

import controladores.empleado.productos.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import modelo.dao.DocumentoJpaController;
import modelo.dao.Documento_productoJpaController;
import modelo.dao.ProductoJpaController;
import modelo.dao.UsuarioJpaController;
import modelo.dao.VentaJpaController;
import modelo.entidades.Cliente;
import modelo.entidades.Documento;
import modelo.entidades.Documento_producto;
import modelo.entidades.Producto;
import modelo.entidades.Usuario;
import modelo.entidades.Venta;


/**
 *
 * @author luisn
 */
@WebServlet(name = "CrearVenta", urlPatterns = {"/empleado/CrearVenta"})
public class CrearVenta extends HttpServlet {

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
        String vista = "/empleado/ventas/crearVentas.jsp";
        String error="";
        Long id_cliente=0L;
        Long id_empleado=0L;
        Double precioBruto=0.0;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionFerreteria");
        ClienteJpaController cjc = new ClienteJpaController(emf); //Llamamos controlador JPA USUARIO
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        DocumentoJpaController djc= new DocumentoJpaController(emf);
        ProductoJpaController pjc=new ProductoJpaController(emf);
        Documento_productoJpaController dpjc=new Documento_productoJpaController(emf);
        Boolean nota = false;
        Boolean ticket  = false;
        Boolean abono = false;
        Boolean factura = false;
        Date fecha = new Date();
        Integer iva = null;
        Integer numeroProdcuctos = null;
        Double precioTotal = 0.00;  
        String empleadoR=request.getParameter("empleado");
        String cliente=request.getParameter("cliente");
        String precio_bruto=request.getParameter("precioBruto");
        String tipo_documentos=request.getParameter("tipoDocumento");
        String numeroProductos=request.getParameter("numeroProductos");
        
        System.out.println(request.toString());
        
        if (request.getParameter("empleado") != null && request.getParameter("cliente") != null &&  request.getParameter("precioBruto") != null &&
        request.getParameter("tipoDocumento") != null &&  request.getParameter("numeroProductos") != null ){ 
                //Si no es null es cuando se crea el producto
                
                
            id_cliente = Long.valueOf(request.getParameter("cliente")) ;
            id_empleado = Long.valueOf(request.getParameter("empleado"));
            precioBruto = Double.parseDouble(request.getParameter("precioBruto"));
            numeroProdcuctos=Integer.parseInt(request.getParameter("numeroProductos"));
            switch(request.getParameter("tipoDocumento")){
                case "0": {
                    nota = true;
                    ticket = false;
                    abono = false;
                    factura = false;
                    
                }
                 case "1": {
                    nota = false;
                    ticket = true;
                    abono = false;
                    factura = false;
                    
                }
                  case "2": {
                    nota = false;
                    ticket = false;
                    abono = true;
                    factura = false;
                    
                }
                   case "3": {
                    nota = false;
                    ticket = false;
                    abono = false;
                    factura = true;
                    
                }
            }
            if(factura==true){
                iva=21;
            }else{
                iva=0;
            }
            
             if (precioBruto < 0.0) {
                error = "El precio no puede ser negativo";   
            } else {
            
             VentaJpaController   vjc=new VentaJpaController(emf);
            Cliente c= cjc.findCliente(id_cliente);
            Usuario u= ujc.findUsuario(id_empleado);
            
            Venta v= new Venta();
            v.setId_cliente(c);
            v.setId_usuario(u);
            v.setPrecio_bruto(precioBruto);
            

                try { //Persistimos los datos en la base de datos
                    vjc.create(v);
                   precioTotal=((precioBruto*iva)/100)+precioBruto;
                    Documento d=new Documento();
                    d.setEs_abono(abono);
                    d.setEs_factura(factura);
                    d.setEs_nota_entrega(nota);
                    d.setEs_ticket(ticket);
                    d.setFecha(fecha);
                    d.setId_venta(v);
                    d.setIva(iva);
                    d.setPrecio_total(precioTotal);
                    djc.create(d);
                    d.getId_documento();
                    String inputProducto="selectProductos";
                    for(int i=1;i<=numeroProdcuctos;i++ ){
                      Long idProducto=Long.valueOf(request.getParameter(inputProducto+i));
                      
                      Documento_producto dp=new Documento_producto();
                      dp.setId_documento(d.getId_documento());
                      dp.setId_producto(idProducto);
                      dpjc.create(dp);
                      
                    }
                    
                    response.sendRedirect("MenuVentas"); // 
                } catch (RollbackException e) {
                    error = "El error es"+ e.getMessage();
                }
            }
        
             
             
             
             
        
        
        }else{
            
            List clientes= cjc.findClienteEntities();
                List empleados= new ArrayList<>();
        
        for(Usuario empleado :ujc.findUsuarioEntities()  ){
            if(empleado.getRol().equals("empleado")){
                empleados.add(empleado);
        }
        
        }
        
        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.setAttribute("cliente", id_cliente);
            request.setAttribute("empleado", id_empleado);
            request.setAttribute("precio_bruto", precioBruto);
            
           
        }
        
        
            
        request.setAttribute("empleados", empleados);
        request.setAttribute("clientes", clientes);
        getServletContext().getRequestDispatcher(vista).forward(request, response);
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