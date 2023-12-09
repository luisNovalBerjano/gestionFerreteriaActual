
package controladores;
/**
 *
 * @author luisn
 */
import encriptado.Encriptado;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
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
import modelo.entidades.Cliente;
import modelo.entidades.Usuario;

/**
 *
 * @author luisn
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
        String vista = "/login.jsp";
        // Si hemos recibido los datos del formulario
        
        if (request.getParameter("mail") != null && 
                request.getParameter("password") != null) {
            String mail = request.getParameter("mail");
            String password = Encriptado.encriptaContraseña(request.getParameter("password"));
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestionFerreteria");
            
            
            UsuarioJpaController ujc = new UsuarioJpaController(emf);
            List<Usuario> usuarios = ujc.findUsuarioEntities();
            for (Usuario u : usuarios) {
                if (u.getMail().equals(mail) && u.getPass().equals(password)) {
                   
                    if(u.getRol().equals("cliente")){
                        ClienteJpaController cjc= new ClienteJpaController(emf);
                        List<Cliente>clientes=new ArrayList<>();
                        clientes=cjc.findClienteEntities();
                        for(int i=0;i<clientes.size();i++){
                            if(u.getId_usuario()==clientes.get(i).getId_usuario().getId_usuario()&& clientes.get(i).getEsta_activo()==false ){
                                response.sendRedirect("UsuarioInactivo");
                                return;
                               
                            }
                        }
                    }
                    request.getSession().setAttribute("usuario", u);
                    response.sendRedirect("Inicio");
                    return;
                }
            }
            String error = "Usuario o contraseña incorrectos";
            password=request.getParameter("password");
            request.setAttribute("error", error);
            request.setAttribute("mail", mail);
            request.setAttribute("password", password);
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
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
