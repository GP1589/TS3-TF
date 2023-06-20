package com.example.tfts3.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import com.example.tfts3.Models.Cliente;
import com.example.tfts3.Services.IClienteService;
import com.example.tfts3.Services.IUploadFileService;


@Controller
@SessionAttributes("cliente")
public class ClienteController {
	@Autowired
	private IClienteService clienteService;
	@Autowired
	private IUploadFileService uploadFileService;
	@RequestMapping(value = "Clientes",method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo1","Listado de Clientes");
		model.addAttribute("clientes",clienteService.findAll());
		return "Clientes/Clientes";
	}
	
	@RequestMapping(value = "/form1")
	public String formularioCliente(Map<String, Object> model) {
		Cliente cliente=new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario del Cliente");
		return "Clientes/Register-Cliente";
	}
	
	@GetMapping(value = "/uploads/{filename}")
	public ResponseEntity<Resource> goImage(@PathVariable String filename) {
		Resource resource = null;
		try {
			resource = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() +"\"").body(resource);
	}
	
	@RequestMapping(value = "/form1/{id}")
	public String editar(@PathVariable (value = "id") Long id,
			Map<String, Object> model) {
		Cliente cliente=null;
		if(id>0) {
			cliente=clienteService.findOne(id);
		}else {
		   return "redirect:/Clientes";		   
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");		
		return "/Clientes/Register-Cliente";
	}
	
	@RequestMapping(value = "/form1", method = RequestMethod.POST)
	public String registrar(@Validated Cliente cliente, BindingResult result,
	Model model, SessionStatus status,@RequestParam("file") MultipartFile file) throws IOException {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario del Cliente");
			// return "redirect:/form1";
			
		}
		if(cliente.getId()!=null){
            Cliente clientea = clienteService.findOne(cliente.getId());
            if(file != null && !file.isEmpty()){
                String fileName = uploadFileService.copy(file);
                cliente.setFoto(fileName);
            }else if(file == null && clientea.getFoto()!=null){//Actualizar            
                cliente.setFoto(clientea.getFoto());
            }
        }else{
            if(file != null && !file.isEmpty()){
                String fileName = uploadFileService.copy(file);
                cliente.setFoto(fileName);
            }else{
                cliente.setFoto("912e47e4-f3b8-41c0-be84-9518b67fac96_descargar.png");
                
            }
        }
		// String fileName = uploadFileService.copy(file);
		// cliente.setFoto(fileName);
		clienteService.save(cliente);;
		status.setComplete();
		return "redirect:/Clientes";
	}
	
	@RequestMapping(value="/eliminar1/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if(id>0) {
			clienteService.delete(id);
		}
		return "redirect:/Clientes";
	}	
}
