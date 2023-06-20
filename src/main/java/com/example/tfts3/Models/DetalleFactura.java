package com.example.tfts3.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "DetalleFactura")
public class DetalleFactura {
    @EmbeddedId
    public DetalleFacturaId id;

    @ManyToOne
    @MapsId("facturaId")
    @JoinColumn(name = "factura_id")
    private Facturas factura;

    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private int cantidad;

    private float precio_unitario;
    // Constructor, getters y setters

    // public DetalleFactura(DetalleFacturaId id, Facturas factura, Producto producto, int cantidad,
    //         float precio_unitario) {
    //     this.id = id;
    //     this.factura = factura;
    //     this.producto = producto;
    //     this.cantidad = cantidad;
    //     this.precio_unitario = precio_unitario;
    // }

    public DetalleFacturaId getId() {
        return id;
    }


    public void setId(DetalleFacturaId id) {
        this.id = id;
    }

    public Facturas getFactura() {
        return factura;
    }

    public void setFactura(Facturas factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

}


