package com.example.tfts3.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tfts3.DAO.IDetalleFacturaDAO;
import com.example.tfts3.Models.DetalleFactura;
import com.example.tfts3.Models.DetalleFacturaId;

@Service
public class DetalleFacturaService implements IDetalleFacturaService{
    @Autowired
    private IDetalleFacturaDAO detalleFacturaDAO;

    @Override
    public void delete(DetalleFacturaId id) {
        // TODO Auto-generated method stub
        detalleFacturaDAO.deleteById(id);
        
    }

    @Override
    public List<DetalleFactura> findAll() {
        // TODO Auto-generated method stub
        return detalleFacturaDAO.findAll();
    }

    @Override
    public DetalleFactura findOne(DetalleFacturaId id) {
        // TODO Auto-generated method stub
        return detalleFacturaDAO.findById(id).orElseThrow(null);
    }

    @Override
    public void save(DetalleFactura detalleFactura) {
        // TODO Auto-generated method stub
        detalleFacturaDAO.save(detalleFactura);
    }    
}
