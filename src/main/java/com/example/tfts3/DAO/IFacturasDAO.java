package com.example.tfts3.DAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tfts3.Models.Facturas;
@Repository
public interface IFacturasDAO extends JpaRepository <Facturas,String> {
    
}
