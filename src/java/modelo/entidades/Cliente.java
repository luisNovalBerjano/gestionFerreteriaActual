
package modelo.entidades;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 *
 * @author luisn
 */
@Entity
@Table(name = "cliente")
@Cacheable(false)

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id_cliente;
    
    @OneToOne 
    @JoinColumn(name = "id_usuario")
    private Usuario id_usuario;
    
     @Column(name = "esta_activo")
    private Boolean esta_activo;

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Boolean getEsta_activo() {
        return esta_activo;
    }

    public void setEsta_activo(Boolean esta_activo) {
        this.esta_activo = esta_activo;
    }

    @Override
    public String toString() {
        return "cliente{" + "id_cliente=" + id_cliente + ", id_usuario=" + id_usuario + ", esta_activo=" + esta_activo + '}';
    }
    
    
}
