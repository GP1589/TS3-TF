package com.example.tfts3.Services;

import java.util.List;

import com.example.tfts3.Models.Marca;

public interface IMarcaService {
    public List<Marca> findMarcas();
    public void save(Marca marca);
    public Marca findOne(Long id);
    public void delete(Long id);
}
