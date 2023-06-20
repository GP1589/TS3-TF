package com.example.tfts3.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tfts3.DAO.IMarcaDAO;
import com.example.tfts3.Models.Marca;

@Service
public class MarcaService implements IMarcaService {
    @Autowired
    private IMarcaDAO marcaDAO;

    @Override
    @Transactional
    public void delete(Long id) {
        // TODO Auto-generated method stub
        marcaDAO.deleteById(id);
        
    }

    @Override
    @Transactional(readOnly = true)
    public List<Marca> findMarcas() {
        // TODO Auto-generated method stub
        return marcaDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Marca findOne(Long id) {
        // TODO Auto-generated method stub
        return marcaDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Marca marca) {
        // TODO Auto-generated method stub
        marcaDAO.save(marca);
        
    }
    
    
    
}



    // @Override
    // @Transactional(readOnly = true)
    // public List<Marca> findMarcas(){
    //     return marcaDAO.findMarcas();
    // }

    // @Override
    // @Transactional
    // public void save(Marca marca){
    //     marcaDAO.save(marca);
    // }

    // @Override
    // @Transactional(readOnly = true)
    // public Marca findOne(Long id){
    //     return marcaDAO.findOne(id);
    // }

    // @Override
    // @Transactional
    // public void delete(Long id){
    //     marcaDAO.delete(id);
    // }