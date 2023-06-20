package com.example.tfts3.Services;

import java.util.List;

import com.example.tfts3.Models.Facturas;

public interface IFacturasService {
    public List<Facturas> findAll();
    public void save(Facturas facturas);
    public Facturas findOne(Long codigo);
    public void delete(Long codigo);
}
