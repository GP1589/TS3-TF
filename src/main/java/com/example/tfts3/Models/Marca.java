package com.example.tfts3.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "marcas")
public class Marca implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String numrep;
    
    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos = new ArrayList<>();

    // Constructor, getters y setters

    // public void addProducto(Producto producto) {
    //     productos.add(producto);
    //     producto.setMarca(this);
    // }

    // public void removeProducto(Producto producto) {
    //     productos.remove(producto);
    //     producto.setMarca(null);
    // }

    private static final long serialVersionUID=1L;

    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumrep() {
        return this.numrep;
    }

    public void setNumrep(String numrep) {
        this.numrep = numrep;
    }
    public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
