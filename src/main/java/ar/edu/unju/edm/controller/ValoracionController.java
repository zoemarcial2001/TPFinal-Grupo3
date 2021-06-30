package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.model.Turistas_PoIs;
import ar.edu.unju.edm.service.IPoIService;
import ar.edu.unju.edm.service.ITuristaService;
import ar.edu.unju.edm.service.ITurista_PoIsService;

@Controller
public class ValoracionController {
	
	@Autowired
	IPoIService poiService;
	
	@Autowired
	ITuristaService turistaService;
	
	@Autowired
	ITurista_PoIsService valoracionService;
	
	@GetMapping("/valoracion/mostrar/{codigoPoI}")
	public String cargarValoracion(Model model,@PathVariable(name="codigoPoI") Integer codigo) {
		Turistas_PoIs valoracionNueva = valoracionService.crearValoracion();
		try {
			
			Authentication auth = SecurityContextHolder
		            .getContext()
		            .getAuthentication();
		    UserDetails userDetail = (UserDetails) auth.getPrincipal();

			Turista logueado = turistaService.buscarUnTurista(userDetail.getUsername());
			
			valoracionNueva.setPoI(poiService.encontrarUnPoI(codigo));
			
			model.addAttribute("unaValoracion", valoracionNueva);
		    model.addAttribute("valoraciones", valoracionService.obtenerTodasValoracionesUnPois(valoracionNueva.getPoI()));
		    model.addAttribute("mivaloracion", valoracionService.obtenerTodasValoracionesUnTurista(logueado));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return ("valoracion");
	}
	
	@PostMapping("/valoracion/guardar/{codigo}")
	public String guardarNuevoValoracion( @ModelAttribute("unaValoracion") Turistas_PoIs nuevaValoracion,@PathVariable(name="codigo") Integer codigo, Model model) {		
		
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    
	    try {
	    	PoI poiEncontrado = poiService.encontrarUnPoI(codigo);
	    	nuevaValoracion.setPoI(poiEncontrado);
			Turista turistaEncontrado = turistaService.buscarUnTurista(userDetail.getUsername());
			if (turistaEncontrado != null) {
				
					if (nuevaValoracion.getComentario().isEmpty()) {
						nuevaValoracion.setComentario(null);
					}
					else {
						turistaEncontrado.setPuntos(turistaEncontrado.getPuntos() + 5);
					}
					
					if(nuevaValoracion.getValoracion() != 0) {
						turistaEncontrado.setPuntos(turistaEncontrado.getPuntos() + 8);
					}
					
					turistaService.guardarTurista(turistaEncontrado);
					nuevaValoracion.setTurista(turistaEncontrado);
					valoracionService.guardarValoracion(nuevaValoracion);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return "redirect:/valoracion/mostrar/{codigo}";
	}
	
	
	@GetMapping("/valoracion/editar/{idTuristas_PoIs}")
	public String editarValoracion(Model model, @PathVariable(name="idTuristas_PoIs") int id) throws Exception {
		try {
			Authentication auth = SecurityContextHolder
		            .getContext()
		            .getAuthentication();
		    UserDetails userDetail = (UserDetails) auth.getPrincipal();

			Turista logueado = turistaService.buscarUnTurista(userDetail.getUsername());
			
			Turistas_PoIs valoracionEncontrada = valoracionService.encontrarUnaValoracion(id);
			PoI poivalorado = valoracionEncontrada.getPoI();
			
			model.addAttribute("unaValoracion", valoracionEncontrada);
			model.addAttribute("valoraciones", valoracionService.obtenerTodasValoracionesUnPois(poivalorado));
			
			model.addAttribute("mivaloracion" , valoracionService.obtenerTodasValoracionesUnTurista(logueado));
			
		    model.addAttribute("editMode", "true");
			
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unaValoracion", valoracionService.crearValoracion());
			model.addAttribute("editMode", "false");
		}
		
		return("valoracion");
		
	}
	
	@PostMapping("/valoracion/modificar/{codigo}")
	public String modificarValoracion(@ModelAttribute("unaValoracion") Turistas_PoIs valoracionModificada, @PathVariable(name="codigo") Integer codigo, Model model) {
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
		return "redirect:/valoracion/mostrar/{codigo}";
	}
	
	
	
	@GetMapping("/valoracion/eliminarValoracion/{idTuristas_PoIs}")
	public String eliminarValoracion(Model model, @PathVariable(name="idTuristas_PoIs") int id) throws Exception {
		int codigo = valoracionService.encontrarUnaValoracion(id).getPoI().getCodigoPoI();
		String redireccion = "redirect:/valoracion/mostrar/" + codigo;
		
		try {	
			valoracionService.eliminarValoracion(id);
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return redireccion;
	}
	
	
	
	//metodos para usuario root
	
	@GetMapping("/valoracion/vistaRoot")
	public String verValoraciones (Model model) {
		model.addAttribute("valoracion", valoracionService.obtenerTodasValoraciones());
		return ("valoracionesComentariosRoot");
	}
	
	@GetMapping("/valoracion/eliminarValoracionRoot/{idTuristas_PoIs}")
	public String eliminarValoracionRoot(Model model, @PathVariable(name="idTuristas_PoIs") int id) {		
		try {	
			valoracionService.eliminarValoracion(id);
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/valoracion/vistaRoot";
	}
	
}
