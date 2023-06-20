package com.example.tfts3.Models;

import java.io.Serializable;

import jakarta.persistence.*;

@Embeddable
public class DetalleFacturaId implements Serializable {
    private Long facturaId;
    private Long productoId;
    private static final long serialVersionUID=1L;
    // Constructor, getters and setters, equals, and hashCode

    
    public Long getFacturaId() {
        return facturaId;
    }
    public DetalleFacturaId(Long facturaId, Long productoId) {
        this.facturaId = facturaId;
        this.productoId = productoId;
    }
    public void setFacturaId(Long facturaId) {
        this.facturaId = facturaId;
    }
    public Long getProductoId() {
        return productoId;
    }
    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    
    
}
