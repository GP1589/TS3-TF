package com.example.tfts3.DAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tfts3.Models.Producto;

@Repository
public interface IProductoDAO extends JpaRepository<Producto, Long> {
    
}
