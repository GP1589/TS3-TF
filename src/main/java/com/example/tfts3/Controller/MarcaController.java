package com.example.tfts3.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.tfts3.Models.Marca;
import com.example.tfts3.Services.IMarcaService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@SessionAttributes("marca")
public class MarcaController {
    
    @Autowired
    private IMarcaService marcaService;

    @RequestMapping(value="Marcas", method=RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Marcas");
        model.addAttribute("marcas", marcaService.findMarcas());
        return "Marcas/Marcas";
    }

    @RequestMapping(value = "/form")
    public String formularioMarca(Map<String, Object> model){
        Marca marca=new Marca();
        model.put("titulo", "Formulario de la Marca");
        model.put("marca", marca);
        return "Marcas/Register-Marca";
    }
    
    @RequestMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model){
        Marca marca=null;
        if (id>0) {
            marca=marcaService.findOne(id);
        } else {
            return "redirect:/Marcas";
        }
        model.put("titulo", "Editar Marca");
        model.put("marca", marca);
        return "/Marcas/Register-Marca";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String registrar(@Validated Marca marca, BindingResult result, Model model, SessionStatus status){
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de la Marca");
            return "Register-Marca";
        }
        marcaService.save(marca);
        status.setComplete();
        return "redirect:/Marcas";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id){
        if (id>0) {
            marcaService.delete(id);
        }
        return "redirect:/Marcas";
    }
}
