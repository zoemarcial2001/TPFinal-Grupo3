package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Turistas_PoIs;
import ar.edu.unju.edm.service.ITurista_PoIsService;

@Controller
public class ValoracionController {
	
	@Autowired
	@Qualifier("implementacionsql")
	ITurista_PoIsService valoracionService;
	
	@GetMapping("/valoracion/mostrar")
	public String cargarValoracion(Model model) {
		model.addAttribute("unaValoracion", valoracionService.crearValoracion());
	    model.addAttribute("valoraciones", valoracionService.obtenerTodasValoraciones());
	    
	    return ("valoracion");
	}
	
	@PostMapping("/valoracion/guardar")
	public String guardarNuevoValoracion( @ModelAttribute("unaValoracion") Turistas_PoIs nuevaValoracion, Model model) {		
		valoracionService.guardarValoracion(nuevaValoracion);
		System.out.println(valoracionService.obtenerTodasValoraciones());
		model.addAttribute("valoraciones", valoracionService.obtenerTodasValoraciones().size());
		return "redirect:/valoracion/mostrar";
		
	}
	
	@GetMapping("/valoracion/editar/{id}")
	public String editarValoracion(Model model, @PathVariable(name="id") int id) throws Exception {
		try {
			Turistas_PoIs valoracionEncontrada = valoracionService.encontrarUnaValoracion(id);
			model.addAttribute("unaValoracion", valoracionEncontrada);
		    model.addAttribute("editMode", "true");
			
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unaValoracion", valoracionService.crearValoracion());
			model.addAttribute("editMode", "false");
		}
		
		model.addAttribute("valoraciones" , valoracionService.obtenerTodasValoraciones());
		return("valoracion");
		
	}
	
	@PostMapping("/valoracion/modificar")
	public String modificarValoracion(@ModelAttribute("unaValoracion") Turistas_PoIs valoracionModificada, Model model) {
		try {
			valoracionService.modificarValoracion(valoracionModificada);
			model.addAttribute("unaValoracion", new Turistas_PoIs());
			model.addAttribute("editMode", "false");
			
		} catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unaValoracion", valoracionModificada);
			model.addAttribute("valoraciones", valoracionService.obtenerTodasValoraciones());
			model.addAttribute("editMode", "true");
		}
		model.addAttribute("valoraciones", valoracionService.obtenerTodasValoraciones());
		return("valoracion");
	}
	
	
	
	@GetMapping("/valoracion/eliminarValoracion/{id}")
	public String eliminarValoracion(Model model, @PathVariable(name="id") int id) {		
		try {	
			valoracionService.eliminarValoracion(id);
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/valoracion/mostrar";
	}

}
