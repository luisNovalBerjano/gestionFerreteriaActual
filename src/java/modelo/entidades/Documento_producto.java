/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entidades;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author luisn
 */


@Entity
@Table(name = "documento_producto")
@Cacheable(false) //La etiqueta cacheable hace que no se guarde en la cache de java para que las vistas se recarguen como es debido

public class Documento_producto implements Serializable {
    
    @Id
    @Column(name="id_documento")
    private Long id_documento;
    
    @Id
    @Column(name="id_producto")
    private Long id_producto;
    
    // Relación con la tabla de id_documento (clave externa)
    @ManyToOne
    @JoinColumn(name = "id_documento", insertable = false, updatable = false)
    private Documento documento;
    
     // Relación con la tabla de id_producto (clave externa)
    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;

    public Long getId_documento() {
        return id_documento;
    }

    public void setId_documento(Long id_documento) {
        this.id_documento = id_documento;
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Documento_producto{" + "id_documento=" + id_documento + ", id_producto=" + id_producto + ", documento=" + documento + ", producto=" + producto + '}';
    }
    
    
}

