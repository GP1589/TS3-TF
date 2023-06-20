package com.example.tfts3.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tfts3.DAO.IProductoDAO;
import com.example.tfts3.Models.Producto;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private IProductoDAO productoDAO ;

    @Override
    public void delete(Long codigo) {
        // TODO Auto-generated method stub
        productoDAO.deleteById(codigo);
        
    }

    @Override
    public List<Producto> findAll() {
        // TODO Auto-generated method stub
        return productoDAO.findAll();
    }

    @Override
    public Producto findOne(Long codigo) {
        // TODO Auto-generated method stub
        return productoDAO.findById(codigo).orElse(null);
    }

    @Override
    public void save(Producto producto) {
        // TODO Auto-generated method stub
        productoDAO.save(producto);
        
    }

    


    
}
