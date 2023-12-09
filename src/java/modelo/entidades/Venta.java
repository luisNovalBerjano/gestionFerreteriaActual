
package modelo.entidades;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author luisn
 */
@Entity
@Table(name = "venta")
@Cacheable(false)
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_venta")
    private Long id_venta;
     
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente id_cliente;
     
     @ManyToOne
     @JoinColumn(name="id_usuario")
    private Usuario id_usuario;
   
     @Column(name="precio_bruto")
    private Double precio_bruto;

    public Long getId_venta() {
        return id_venta;
    }

    public void setId_venta(Long id_venta) {
        this.id_venta = id_venta;
    }

    public Cliente getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Cliente id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Double getPrecio_bruto() {
        return precio_bruto;
    }

    public void setPrecio_bruto(Double precio_bruto) {
        this.precio_bruto = precio_bruto;
    }

    @Override
    public String toString() {
        return "venta{" + "id_venta=" + id_venta + ", id_cliente=" + id_cliente + ", id_usuario=" + id_usuario + ", precio_bruto=" + precio_bruto + '}';
    }
     
    
     
}
