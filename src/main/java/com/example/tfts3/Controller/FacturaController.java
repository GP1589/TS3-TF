package com.example.tfts3.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.tfts3.Models.Facturas;
import com.example.tfts3.Services.IFacturasService;
import com.example.tfts3.Services.IProductoService;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/facturas")
public class FacturaController {

    private final IFacturasService facturaRepository;
    private final IProductoService productoRepository;

    @Autowired
    public FacturaController(IFacturasService facturaRepository, IProductoService productoRepository) {
        this.facturaRepository = facturaRepository;
        this.productoRepository = productoRepository;
    }

    @GetMapping
    public String mostrarListaFacturas(Model model) {
        List<Facturas> facturas = facturaRepository.findAll();
        model.addAttribute("facturas", facturas);
        return "lista-facturas";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        Facturas factura = new Facturas();
        model.addAttribute("factura", factura);
        model.addAttribute("productos", productoRepository.findAll());
        return "crear-factura";
    }

    @PostMapping("/crear")
    public String crearFactura(@Valid @ModelAttribute("factura") Facturas factura, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("productos", productoRepository.findAll());
            return "crear-factura";
        }
        facturaRepository.save(factura);
        return "redirect:/facturas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Facturas factura = facturaRepository.findOne(id);
        model.addAttribute("factura", factura);
        model.addAttribute("productos", productoRepository.findAll());
        return "editar-factura";
    }

    @PostMapping("/editar/{id}")
    public String actualizarFactura(@PathVariable Long id, @Valid @ModelAttribute("factura") Facturas facturaActualizada, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("productos", productoRepository.findAll());
            return "editar-factura";
        }
        Facturas factura = facturaRepository.findOne(id);

        factura.setMonto(facturaActualizada.getMonto());
        factura.setProductos(facturaActualizada.getProductos());

        facturaRepository.save(factura);
        return "redirect:/facturas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarFactura(@PathVariable Long id) {
        //Facturas factura = facturaRepository.findOne(id);
        facturaRepository.delete(id);
        return "redirect:/facturas";
    }
}

