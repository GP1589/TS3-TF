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
        ArrayList<Producto> lista = DAO.getList();
        double totalProducto=0;
        double monto=0;
        Facturas factura = new Facturas();
        Long id = 1L;
        Cliente cliente = clienteService.findOne(id);
        factura.setCliente(cliente);
        factura.setId(UUID.randomUUID().toString());        
        facturaRepository.save(factura);        
        for (Producto producto : lista) {
            DetalleFacturaId detalleFacturaId = new DetalleFacturaId(factura.getId(), producto.getId());
            DetalleFactura detalleFactura = new DetalleFactura();
            detalleFactura.setId(detalleFacturaId);
            detalleFactura.setFactura(factura);
            detalleFactura.setProducto(producto);
            detalleFactura.setCantidad(1);
            detalleFactura.setPrecio_unitario(producto.getCosto());
            totalProducto=detalleFactura.getPrecio_unitario()*detalleFactura.getCantidad();
            monto+=totalProducto;
            detalleFacturaService.save(detalleFactura); 
        }
        factura.setMonto(monto);
        facturaRepository.save(factura);  
        DAO.deleteAll();
        return "redirect:/productos";
    }
    // @PostMapping("/procesar")
    // public String crearFactura(@ModelAttribute("productos") List<Producto> productos) {
    //     double totalProducto=0;
    //     double monto=0;
    //     Facturas factura = new Facturas();
    //     Long id = 1L;
    //     Cliente cliente = clienteService.findOne(id);
    //     factura.setCliente(cliente);
    //     factura.setId(UUID.randomUUID().toString());        
    //     facturaRepository.save(factura);
    //     for (Producto producto : productos) {
    //         Producto productoi= productoRepository.findOne(producto.getId());
    //         DetalleFacturaId detalleFacturaId = new DetalleFacturaId(factura.getId(), producto.getId());
    //         DetalleFactura detalleFactura = new DetalleFactura();
    //         detalleFactura.setId(detalleFacturaId);
    //         detalleFactura.setFactura(factura);
    //         detalleFactura.setProducto(productoi);
    //         detalleFactura.setCantidad(producto.getPcantidad());
    //         detalleFactura.setPrecio_unitario(productoi.getCosto());
    //         totalProducto=detalleFactura.getPrecio_unitario()*detalleFactura.getCantidad();
    //         monto+=totalProducto;
    //         detalleFacturaService.save(detalleFactura);
    //     };
    //     factura.setMonto(monto);
    //     facturaRepository.save(factura);  
    //     DAO.deleteAll();
    //     return "redirect:/productos";
    // }
    // @PostMapping("/procesar")
    // public String crearFactura(@ModelAttribute("productos") List<Producto> productos) {
    //     double totalProducto = 0;
    //     double monto = 0;
    //     Facturas factura = new Facturas();
    //     Long id = 1L;
    //     Cliente cliente = clienteService.findOne(id);
    //     factura.setCliente(cliente);
    //     factura.setId(UUID.randomUUID().toString());
    //     facturaRepository.save(factura);
    //     for (Producto producto : productos) {
    //         Producto productoi = productoRepository.findOne(producto.getId());
    //         DetalleFacturaId detalleFacturaId = new DetalleFacturaId(factura.getId(), producto.getId());
    //         DetalleFactura detalleFactura = new DetalleFactura();
    //         detalleFactura.setId(detalleFacturaId);
    //         detalleFactura.setFactura(factura);
    //         detalleFactura.setProducto(productoi);
    //         detalleFactura.setCantidad(producto.getPcantidad());
    //         detalleFactura.setPrecio_unitario(productoi.getCosto());
    //         totalProducto = detalleFactura.getPrecio_unitario() * detalleFactura.getCantidad();
    //         monto += totalProducto;
    //         detalleFacturaService.save(detalleFactura);
    //     }
    //     factura.setMonto(monto);
    //     facturaRepository.save(factura);
    //     DAO.deleteAll();
    //     return "redirect:/productos";
    // }


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
    public ModelAndView Listar(Model model) {        
        ModelAndView modelAndView = new ModelAndView("Producto/Basket");
        List<Producto> lista=DAO.getList();
        double subtotal=0;
        double total=0;
        for (Producto producto : lista) {
            subtotal+=producto.getCosto();
        };
        total=subtotal+10;
        modelAndView.addObject("productos", DAO.getList());
        modelAndView.addObject("subtotal", subtotal);
        modelAndView.addObject("total",total);
        modelAndView.addObject("titulo", "Carrito de Compras");
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.POST)
    public ModelAndView deleteFromBasket(@PathVariable("id") Long id) {
        Producto producto = DAO.findById(id);
        DAO.delete(producto);
        return new ModelAndView("redirect:/facturas/basket");
    }

}

