package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.service.IPoIService;

@Controller
public class PoIController{

	@Autowired
	@Qualifier("impsql")
	IPoIService poIService;
	
	
	@GetMapping("/poI/mostrar")
	public String cargarPoI(Model model) {
		model.addAttribute("unPoI", poIService.crearPoI());
		model.addAttribute("poIs", poIService.obtenerTodosPoIs());
		return("poI");
	}
	
	@PostMapping("/poI/guardar")
	public String guardarNuevoPoI(@ModelAttribute("unPoI") PoI nuevoPoI, Model model) {
		poIService.guardarPoI(nuevoPoI);
		System.out.println(poIService.obtenerTodosPoIs());
		model.addAttribute("poIs", poIService.obtenerTodosPoIs().size());
		return "redirect:/poI/mostrar";
	}
	
	@GetMapping("/poI/editar/{codigoPoI}")
	public String editarPoI(Model model, @PathVariable(name="codigoPoI") int codigo) throws Exception {
		try {
			PoI poIEncontrado = poIService.encontrarUnPoI(codigo);
			model.addAttribute("unPoI", poIEncontrado);	
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unPoI", poIService.crearPoI());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("poIs", poIService.obtenerTodosPoIs());
		return("poI");
	}
	
	@PostMapping("/poI/modificar")
	public String modificarPoI(@ModelAttribute("unPoI") PoI poIModificado, Model model) {
		try {
			poIService.modificarPoI(poIModificado);
			model.addAttribute("unPoI", new PoI());				
			model.addAttribute("editMode", "false");
		} catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unPoI", poIModificado);			
			model.addAttribute("poIs", poIService.obtenerTodosPoIs());
			model.addAttribute("editMode", "true");
		}
		model.addAttribute("poIs", poIService.obtenerTodosPoIs());
		return("poI");
	}
	
	
	
	@GetMapping("/poI/eliminarPoI/{codigo}")
	public String eliminarPoI(Model model, @PathVariable(name="codigo") int codigo) {		
		try {			
			poIService.eliminarPoI(codigo);			
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/poI/mostrar";
	}
	
	
	
}
