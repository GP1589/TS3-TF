package com.example.tfts3.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tfts3.Models.DetalleFactura;
import com.example.tfts3.Models.DetalleFacturaId;

@Repository
public interface IDetalleFacturaDAO extends JpaRepository<DetalleFactura,DetalleFacturaId>{
    
}
