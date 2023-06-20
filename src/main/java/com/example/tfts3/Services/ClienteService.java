package com.example.tfts3.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tfts3.DAO.IClienteDAO;
import com.example.tfts3.Models.Cliente;

@Service
public class ClienteService implements IClienteService {
	
	@Autowired
	private IClienteDAO clienteDAO;

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clienteDAO.deleteById(id);;
		
	}
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return clienteDAO.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		// TODO Auto-generated method stub
		return clienteDAO.findById(id).orElse(null);
	}
	@Override
	@Transactional
	public void save(Cliente cliente) {
		// TODO Auto-generated method stub
		clienteDAO.save(cliente);
	}

	


}
