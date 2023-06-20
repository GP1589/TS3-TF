package com.example.tfts3.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tfts3.DAO.IFacturasDAO;
import com.example.tfts3.Models.Facturas;

@Service
public class FacturasService implements IFacturasService{
    @Autowired
    private IFacturasDAO facturasDAO;

    @Override
    public void delete(String codigo) {
        // TODO Auto-generated method stub
        facturasDAO.deleteById(codigo);;
    }

    @Override
    public List<Facturas> findAll() {
        // TODO Auto-generated method stub
        return facturasDAO.findAll();
    }

    @Override
    public Facturas findOne(String codigo) {
        // TODO Auto-generated method stub
        return facturasDAO.findById(codigo).orElseThrow(null);
    }

    @Override
    public void save(Facturas facturas) {
        // TODO Auto-generated method stub
        facturasDAO.save(facturas);
        
    }
    
    
}
