package com.example.tfts3.Services;

import java.util.List;
import com.example.tfts3.Models.Cliente;

public interface IClienteService {
	public List<Cliente> findAll();
    public void save(Cliente cliente);
    public Cliente findOne(Long id);
    public void delete(Long id);


    
}
