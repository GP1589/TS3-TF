package com.example.tfts3.Services;

import java.util.List;
import com.example.tfts3.Models.DetalleFactura;
import com.example.tfts3.Models.DetalleFacturaId;

public interface IDetalleFacturaService {
    public List<DetalleFactura> findAll();
    public void save(DetalleFactura detalleFactura);
    public DetalleFactura findOne(DetalleFacturaId id);
    public void delete(DetalleFacturaId id);
    
}
