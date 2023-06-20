package com.example.tfts3.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.tfts3.DAO.ProductoDAO;
import com.example.tfts3.Models.Cliente;
import com.example.tfts3.Models.DetalleFactura;
import com.example.tfts3.Models.DetalleFacturaId;
import com.example.tfts3.Models.Facturas;
import com.example.tfts3.Models.Producto;
import com.example.tfts3.Services.IClienteService;
import com.example.tfts3.Services.IDetalleFacturaService;
import com.example.tfts3.Services.IFacturasService;
import com.example.tfts3.Services.IProductoService;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/facturas")
public class FacturaController {

    private final IFacturasService facturaRepository;
    private final IProductoService productoRepository;
    private final IDetalleFacturaService detalleFacturaService;
    ProductoDAO DAO= new ProductoDAO();
    @Autowired
	private IClienteService clienteService;

    @Autowired
    public FacturaController(IFacturasService facturaRepository, IProductoService productoRepository,IDetalleFacturaService detalleFacturaService) {
        this.facturaRepository = facturaRepository;
        this.productoRepository = productoRepository;
        this.detalleFacturaService=detalleFacturaService;
    }

    @GetMapping
    public String mostrarListaFacturas(Model model) {
        List<Facturas> facturas = facturaRepository.findAll();
        model.addAttribute("facturas", facturas);
        return "lista-facturas";
    }

    // @GetMapping("/crear")
    // public String mostrarFormularioCrear(Model model) {
    //     Facturas factura = new Facturas();
    //     model.addAttribute("factura", factura);
    //     model.addAttribute("productos", productoRepository.findAll());
    //     return "crear-factura";
    // }

    //@PostMapping("/procesar")
    // @GetMapping("/procesar")
    // public String crearFactura() {        
    //     Facturas factura = new Facturas();
    //     Long id=7L;
    //     Cliente cliente=clienteService.findOne(id);        
    //     factura.setCliente(cliente);
    //     factura.setId(UUID.randomUUID().toString());    
    //     facturaRepository.save(factura);        
    //     ArrayList<Producto> lista = DAO.getList();
    //     //DetalleFactura df=new DetalleFactura();
    //     for (Producto row : lista) {
    //         DetalleFactura df=new DetalleFactura();
    //         DetalleFacturaId df_id=new DetalleFacturaId(factura.getId(),row.getId());
    //         df.setId(df_id);
    //         df.setPrecio_unitario(row.getCosto());
    //         df.setCantidad(1);
    //         detalleFacturaService.save(df); 
    //     }
    //     DAO.deleteAll();
    //     return "redirect:/productos";
    // }
    @GetMapping("/procesar")
    public String crearFactura() {
        Facturas factura = new Facturas();
        Long id = 1L;
        Cliente cliente = clienteService.findOne(id);
        factura.setCliente(cliente);
        factura.setId(UUID.randomUUID().toString());
        facturaRepository.save(factura);
        ArrayList<Producto> lista = DAO.getList();
        for (Producto producto : lista) {
            DetalleFacturaId detalleFacturaId = new DetalleFacturaId(factura.getId(), producto.getId());
            DetalleFactura detalleFactura = new DetalleFactura();
            detalleFactura.setId(detalleFacturaId);
            detalleFactura.setFactura(factura);
            detalleFactura.setProducto(producto);
            detalleFactura.setCantidad(1);
            detalleFactura.setPrecio_unitario(producto.getCosto());
            detalleFacturaService.save(detalleFactura); 
        }
        DAO.deleteAll();
        return "redirect:/productos";
    }


    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable String id, Model model) {
        Facturas factura = facturaRepository.findOne(id);
        model.addAttribute("factura", factura);
        model.addAttribute("productos", productoRepository.findAll());
        return "editar-factura";
    }

    @PostMapping("/editar/{id}")
    public String actualizarFactura(@PathVariable String id, @Valid @ModelAttribute("factura") Facturas facturaActualizada, BindingResult bindingResult, Model model) {
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
    public String eliminarFactura(@PathVariable String id) {
        //Facturas factura = facturaRepository.findOne(id);
        facturaRepository.delete(id);
        return "redirect:/facturas";
    }
    //LOCAL ARRAYLIST FUNCTIONS

    @RequestMapping("/basket/{id}")
    public ModelAndView addProductBasket(@PathVariable("id") Long id) {
        Producto producto = productoRepository.findOne(id);
        DAO.addObj(producto);
        return new ModelAndView("redirect:/facturas/basket");
    }

    @GetMapping("/basket")
    public ModelAndView Listar() {        
        ModelAndView modelAndView = new ModelAndView("Producto/Basket");
        modelAndView.addObject("productos", DAO.getList());
        modelAndView.addObject("titulo", "Carrito de Compras");
        return modelAndView;
    }


}

