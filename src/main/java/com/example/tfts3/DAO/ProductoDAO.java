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

    public void deleteAll(){
        lista.clear();
    }
    public Producto findById(Long id){
        Producto pbid = null;
        Iterator<Producto> it = lista.iterator();
        do {
            if (!it.hasNext()) {
                break;
            }
            Producto ae = it.next();
            if (ae.getId()==id) {
                pbid = ae;
                break;
            }
        } while (true);
        return pbid;
    }
    
}
