package com.example.tfts3.DAO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import com.example.tfts3.Models.Producto;

public class ProductoDAO {
    ArrayList<Producto> lista=new ArrayList<>();
    public void addObj(Producto producto) {	
		lista.add(producto);
	}
    
    public ArrayList<Producto> getList(){
		return lista; 
	}

    public void delete(Producto producto){
        lista.remove(producto);
    }
}
