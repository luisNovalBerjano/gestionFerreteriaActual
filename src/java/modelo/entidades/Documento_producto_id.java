/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.entidades;

import java.io.Serializable;


/**
 *
 * @author luisn
 */
public class Documento_producto_id implements Serializable {
        
  
    private Long id_documento;
    
  
    private Long id_producto;

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

    @Override
    public int hashCode() {
       return super.hashCode();
        }

    @Override
    public boolean equals(Object obj) {
        
        return super.equals(obj);
    }
    
    
}
