package com.example.tfts3.Controller;

import org.springframework.stereotype.Controller;

import com.example.tfts3.DAO.IMarcaDAO;
import com.example.tfts3.Models.Producto;
import com.example.tfts3.Services.IProductoService;
import com.example.tfts3.Services.IUploadFileService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;


@Controller
@SessionAttributes("producto")
@RequestMapping("/productos")
public class ProductoController {
    private final IProductoService productoRepository;
    private final IMarcaDAO marcaRepository;
    @Autowired
	private IUploadFileService uploadFileService;

    @Autowired
    public ProductoController(IProductoService productoRepository, IMarcaDAO marcaRepository) {
        this.productoRepository = productoRepository;
        this.marcaRepository = marcaRepository;
    }

    // Mostrar el formulario para crear un nuevo producto
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("titulo", "Formulario del Producto");
        model.addAttribute("producto", new Producto());
        model.addAttribute("marcas", marcaRepository.findAll());
        return "Producto/Register";
    }

    // Procesar el formulario para crear un nuevo producto
    @PostMapping("/crear")
    public String crearProducto(@Valid @ModelAttribute("producto") Producto producto, BindingResult bindingResult, Model model, SessionStatus status,@RequestParam("file") MultipartFile file) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("titulo", "Formulario del Producto");
            //return "Producto/Register";
        }
        if(producto.getId()!=null){
            Producto productoa = productoRepository.findOne(producto.getId());
            if(file != null && !file.isEmpty()){
                String fileName = uploadFileService.copy(file);
                producto.setFoto(fileName);
            }else if(file == null && productoa.getFoto()!=null){//Actualizar            
                producto.setFoto(productoa.getFoto());
            }
        }else{
            if(file != null && !file.isEmpty()){
                String fileName = uploadFileService.copy(file);
                producto.setFoto(fileName);
            }else{
                producto.setFoto("912e47e4-f3b8-41c0-be84-9518b67fac96_descargar.png");
                
            }
        }
        //Producto productoa = productoRepository.findOne(producto.getId());
        //Creado por primera vez o actualizar
        
        
        productoRepository.save(producto);
        status.setComplete();
        return "redirect:/productos/aindex";
    }

    // Mostrar la lista de productos
    @GetMapping
    public String mostrarListaProductos(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        return "Producto/ClienteIndex";
    }
    // Mostrar la lista de productos Admin
    @GetMapping("/aindex")
    public String mostrarListaProductosA(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        return "Producto/Index";
    }

    // // Mostrar la lista de productos
    // @GetMapping
    // public String mostrarListaProductos(Model model) {
    //     model.addAttribute("productos", productoRepository.findAll());
    //     return "Producto/ClienteIndex";
    // }

    // Mostrar el formulario para editar un producto existente
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Producto producto = productoRepository.findOne(id);
        model.addAttribute("producto", producto);
        model.addAttribute("marcas", marcaRepository.findAll());
        return "Producto/Register";
    }

    //Details
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Producto producto = productoRepository.findOne(id);
        model.addAttribute("producto", producto);
        return "Producto/Details";
    }

    // Procesar el formulario para editar un producto existente
    // @PostMapping("/editar/{id}")
    // public String actualizarProducto(@PathVariable Long id, @Valid @ModelAttribute("producto") Producto productoActualizado, BindingResult bindingResult, Model model,SessionStatus status,@RequestParam("file") MultipartFile file) throws IOException {
    //     if (bindingResult.hasErrors()) {
    //         model.addAttribute("marcas", marcaRepository.findAll());
    //         return "editar-producto";
    //     }
    //     Producto producto = productoRepository.findOne(id);
    //     if(file != null && !file.isEmpty()){
    //         String fileName = uploadFileService.copy(file);
    //         producto.setFoto(fileName);
    //     }else{
    //         producto.setFoto("912e47e4-f3b8-41c0-be84-9518b67fac96_descargar.png");
            
    //     }

    //     producto.setNombre(productoActualizado.getNombre());
    //     producto.setCosto(productoActualizado.getCosto());
    //     producto.setMarca(productoActualizado.getMarca());
    //     // producto.setFoto(productoActualizado);

    //     productoRepository.save(producto);
    //     status.setComplete();
    //     return "redirect:/productos/aindex";
    // }

    // Eliminar un producto existente
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        //Producto producto = productoRepository.findOne(id);
        productoRepository.delete(id);
        return "redirect:/productos";
    }
}


// @Controller
// public class ProductoController {
//     @GetMapping("/Productosc")
//     public ModelAndView pindex() {
//         ModelAndView modelAndView = new ModelAndView("Producto/ClienteIndex");
//         return modelAndView;
//     }
// }
