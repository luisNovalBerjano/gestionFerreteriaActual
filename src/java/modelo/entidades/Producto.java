/*
 * Clase Empleado.
 * Entidad JPA
 */
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
@Table(name = "producto")
@Cacheable(false) //La etiqueta cacheable hace que no se guarde en la cache de java para que las vistas se recarguen como es debido
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long id_producto;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="stock")
    private Integer stock;
    
    @Column(name="precio")
    private Double precio;
    
    @Column(name="familia")
    private String familia;
    
    @Column(name="categoria")
    private String categoria;
    
    @Column(name="marca")
    private String marca;

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "producto{" + "id_producto=" + id_producto + ", nombre=" + nombre + ", stock=" + stock + ", precio=" + precio + ", familia=" + familia + ", categoria=" + categoria + ", marca=" + marca + '}';
    }
    
    
    
}
