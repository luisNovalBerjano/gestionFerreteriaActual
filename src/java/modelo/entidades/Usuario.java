
package modelo.entidades;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author luisn
 */
@Entity
@Table(name = "usuario")
@Cacheable(false)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long id_usuario;
    
     @Column(name="nickName")
    private String nickName;
     
     @Column(name="pass")
    private String pass;
    
    @Column(name="rol")
    private String rol;
    
    @Column(name="dni")
    private String dni;
    
    @Column(name="nombreCompleto")
    private String nombreCompleto;
    
    @Column(name="telefono")
    private Integer telefono;
    
    @Column(name="direccion")
    private String direccion;
    
    @Column(name="mail")
    private String mail;
    
    @Column(name="codigoPostal")
    private Integer codigoPostal;

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", nickName=" + nickName + ", pass=" + pass + ", rol=" + rol + ", dni=" + dni + ", nombreCompleto=" + nombreCompleto + ", telefono=" + telefono + ", direccion=" + direccion + ", mail=" + mail + ", codigoPostal=" + codigoPostal + '}';
    }
    

   


   
    
    
    
    
   
    
   
    
    

    
    
}
