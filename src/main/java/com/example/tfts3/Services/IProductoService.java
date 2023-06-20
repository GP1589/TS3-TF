package com.example.tfts3.Services;

import java.util.List;

import com.example.tfts3.Models.Producto;

public interface IProductoService {
    public List<Producto> findAll();
    public void save(Producto producto);
    public Producto findOne(Long codigo);
    public void delete(Long codigo);
}
