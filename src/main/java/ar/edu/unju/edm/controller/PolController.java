package ar.edu.unju.edm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Pol;
import ar.edu.unju.edm.service.IPolService;

@Controller
public class PolController {

	@Autowired
	@Qualifier("impsql")
	IPolService polService;
	
	
	@GetMapping("/pol/mostrar")
	public String cargarPol(Model model) {
		model.addAttribute("unPol", polService.crearPol());
		model.addAttribute("pols", polService.obtenerTodosPols());
		return("pol");
	}
	
	@PostMapping("/pol/guardar")
	public String guardarNuevoPol(@Valid @ModelAttribute("unPol") Pol nuevoPol, BindingResult resultado, Model model) {		
		
		if(resultado.hasErrors()) {
			model.addAttribute("unCliente", nuevoPol);
			model.addAttribute("clientes", polService.obtenerTodosPols());
			return "pol";
		}
		else {
			polService.guardarPol(nuevoPol);		
			return "redirect:/pol/mostrar";
		}
	}
	
	@GetMapping("/pol/editar/{codPol}")
	public String editarPol(Model model, @PathVariable(name="codPol") int codigo) throws Exception {
		try {
			Pol polEncontrado = polService.encontrarUnPol(codigo);
			model.addAttribute("unProducto", polEncontrado);	
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unProducto", polService.crearPol());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("pols", polService.obtenerTodosPols());
		return("pol");
	}
	
	@PostMapping("/pol/modificar")
	public String modificarPol(@ModelAttribute("unPol") Pol polModificado, Model model) {
		try {
			polService.modificarPol(polModificado);
			model.addAttribute("unPol", new Pol());				
			model.addAttribute("editMode", "false");
		} catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unProducto", polModificado);			
			model.addAttribute("productos", polService.obtenerTodosPols());
			model.addAttribute("editMode", "true");
		}
		model.addAttribute("pols", polService.obtenerTodosPols());
		return("pol");
	}
	
	
	
	@GetMapping("/pol/eliminarPol/{codPol}")
	public String eliminarCliente(Model model, @PathVariable(name="codPol") int codigo) {		
		try {			
			polService.eliminarPol(codigo);			
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/pol/mostrar";
	}
	
	
	
}
