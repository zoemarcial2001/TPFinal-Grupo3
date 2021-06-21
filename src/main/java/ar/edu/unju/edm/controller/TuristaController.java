package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	
	//metodos para usuario normal
	
	@GetMapping("/registrar")
	public String cargarTurista1(Model model) {
		model.addAttribute("unTurista", turistaService.crearTurista());
	    return ("registroturista");
	}
	
	@PostMapping("/registrar/guardar")
	public String guardarNuevoTurista1(@ModelAttribute("unTurista") Turista nuevoTurista, Model model) {
		Turista turistaEncontrado;
		try {
			turistaEncontrado = turistaService.buscarUnTurista(nuevoTurista.getEmail());
			if(turistaEncontrado == null) {
				turistaService.guardarTurista(nuevoTurista);
			}
			else {
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/login";
	}
	
	
	
	@GetMapping("/turista/perfil")
	public String verTurista1(Model model)throws Exception {
		Turista logueado = turistaService.encontrarUnTurista(SecurityContextHolder.getInitializeCount());
	if (logueado == null) {
		return ("redirect:/abc");
	}else {
		model.addAttribute("perfil", logueado);
		
	    return ("perfil");
	}
 // System.out.println(logueado + "jsdhg");
	}
		
	@PostMapping("/turista/perfilGuardar")
	public String guardarPerfil( @ModelAttribute("unTurista") Turista turistaModificado, Model model) {		
			try {
				turistaService.modificarTurista(turistaModificado);
				model.addAttribute("unTurista", new Turista());
			} catch (Exception e) {
				model.addAttribute("formUsuarioErrorMessage",e.getMessage());
				model.addAttribute("unTurista", turistaModificado);			
				model.addAttribute("turistas", turistaService.obtenerTodosTuristas());			
			}
			return("redirect:/turista/perfil");
		}
	
	
	
	
	//metodos para usuario root
	
	@GetMapping("/turista/mostrar")
	public String cargarTurista(Model model) {
		model.addAttribute("unTurista", turistaService.crearTurista());
	    model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
	    return ("turista1");
	}
	
	@PostMapping("/turista/guardar")
	public String guardarNuevoTurista( @ModelAttribute("unTurista") Turista nuevoTurista, Model model) {	
		
		Turista turistaEncontrado;
		try {
			turistaEncontrado = turistaService.buscarUnTurista(nuevoTurista.getEmail());
			if(turistaEncontrado == null) {
				turistaService.guardarTurista(nuevoTurista);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("turistas", turistaService.obtenerTodosTuristas());
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
	
	@GetMapping ("/cancelar/carga")
	public String cancelar() {
		return "redirect:/turista/mostrar";
	}

}
