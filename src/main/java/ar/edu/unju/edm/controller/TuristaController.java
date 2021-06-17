package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.ITuristaService;

@Controller
public class TuristaController {
	
	@Autowired
	@Qualifier("impsql")
	ITuristaService turistaService;
	
	@GetMapping("/registrar")
	public String cargarTurista1(Model model) {
		model.addAttribute("unTurista", turistaService.crearTurista());
	    return ("registroturista");
	}
	
	@PostMapping("/registrar/guardar")
	public String guardarNuevoTurista1(@ModelAttribute("unTurista") Turista nuevoTurista, Model model) {
		System.out.println(" turistaaaaaaaa" + nuevoTurista.getEmail());
		turistaService.guardarTurista(nuevoTurista);
		
		return "redirect:/login";
	}
	
	@GetMapping("/turista/mostrar")
	public String cargarTurista(Model model) {
		model.addAttribute("unTurista", turistaService.crearTurista());
	    model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
	    return ("turista1");
	}
	
	@PostMapping("/turista/guardar")
	public String guardarNuevoTurista( @ModelAttribute("unTurista") Turista nuevoTurista, Model model) {		
		turistaService.guardarTurista(nuevoTurista);
		System.out.println(turistaService.obtenerTodosTuristas());
		model.addAttribute("turistas", turistaService.obtenerTodosTuristas().size());
		return "redirect:/turista/mostrar";
	}
	
	@GetMapping("/turista/editar/{id}")
	public String editarTurista(Model model, @PathVariable(name="id") int id) throws Exception {
		try {
			Turista turistaEncontrado = turistaService.encontrarUnTurista(id);
		    model.addAttribute("unTurista", turistaEncontrado);	
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unTurista", turistaService.crearTurista());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
		return("turista1");
	}
	
	@PostMapping("/turista/modificar")
	public String modificarTurista(@ModelAttribute("unTurista") Turista turistaModificado, Model model) {
		try {
			turistaService.modificarTurista(turistaModificado);
			model.addAttribute("unTurista", new Turista());
			model.addAttribute("editMode", "false");
		} catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unTurista", turistaModificado);			
			model.addAttribute("turistas", turistaService.obtenerTodosTuristas());			
			model.addAttribute("editMode", "true");
		}
		model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
		return("turista1");
	}
	
	
	
	@GetMapping("/turista/eliminarTurista/{id}")
	public String eliminarTurista(Model model, @PathVariable(name="id") int id) {		
		try {			
			turistaService.eliminarTurista(id);	
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/turista/mostrar";
	}

	
	
	@GetMapping("/turista/prueba")
	public String cargarPerfil(Model model) {
	    return ("perfiltuista");
	}
	
}
