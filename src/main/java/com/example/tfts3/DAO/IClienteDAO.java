package com.example.tfts3.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tfts3.Models.Cliente;
@Repository
public interface IClienteDAO  extends JpaRepository<Cliente,Long> {
	// public List<Cliente> findall();
    // public void save(Cliente cliente);
    // public Cliente findOne(Long cod);
    // public void delete(Long cod);

}
