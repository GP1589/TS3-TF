package com.example.tfts3.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.tfts3.Models.Marca;

@Repository
public interface IMarcaDAO extends JpaRepository<Marca, Long>{
    // public List<Marca> findMarcas();
    // public void save(Marca marca);
    // public Marca findOne(Long id);
    // public void delete(Long id);
}
