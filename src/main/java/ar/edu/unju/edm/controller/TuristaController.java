package ar.edu.unju.edm.controller;

import java.io.IOException;
import java.util.Base64;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.IPoIService;
import ar.edu.unju.edm.service.ITuristaService;
@Controller
public class TuristaController {
	
	@Autowired
	ITuristaService turistaService;
	
	@Autowired
	IPoIService poiService;
	
	
	//metodos para usuario normal
	
	@GetMapping("/registrar")
	public String cargarTurista1(Model model) {
		model.addAttribute("unTurista", turistaService.crearTurista());
	    return ("registroturista");
	}
	
	@PostMapping("/registrar/guardar")
	public String guardarNuevoTurista1(@Valid @ModelAttribute("unTurista") Turista nuevoTurista,BindingResult resultado,  Model model){
      if(resultado.hasErrors()) {
        model.addAttribute("unTurista", nuevoTurista);
         return "registroturista";
      }
      else {
    	turistaService.guardarTurista(nuevoTurista);
		return "redirect:/login";
	   }
	 
	}
	

	@GetMapping("/turista/perfil")
	public String verTurista1(Model model)throws Exception {
		
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();

		Turista logueado = turistaService.buscarUnTurista(userDetail.getUsername());
		model.addAttribute("perfil", logueado);

	    return ("perfil");
	}

	@PostMapping(value="/turista/perfilGuardar",consumes = "multipart/form-data")
	public String guardarPerfil( @ModelAttribute("perfil") Turista turistaModificado,@RequestParam("foto") MultipartFile file, Model model)throws IOException {		
		
		try {
			if (!file.isEmpty() ) {
				byte[] content = file.getBytes();
				String base64 = Base64.getEncoder().encodeToString(content);
				 turistaModificado.setFotoPerfil(base64);
			}
			turistaService.modificarTurista(turistaModificado);
			model.addAttribute("perfil", new Turista());
		} catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("perfil", turistaModificado);	
		}
		
		return "redirect:/turista/perfil";
	}
	
//otros usuarios inicio
	@GetMapping("/perfil/{id}")
	public String mostrarperfil(Model model, @PathVariable(name="id") Integer id) {
		try {
			Turista turista = turistaService.encontrarUnTurista(id);
			model.addAttribute("turista", turista); 
			model.addAttribute("pois", poiService.obtenerMisPoIs(turista));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ("perfilusuarios");
	}

	
	@GetMapping("/cupones/mostrar")
    public String mostrarCupones( Model model) {
		
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    
	    try {
			Turista turista = turistaService.buscarUnTurista(userDetail.getUsername());
			model.addAttribute("turista", turista );
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return("canjear");
		
	}
	
	@PostMapping("/cupones/canjear")
	 public String canjearCupon(Model model) {
		 
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    
		try {
			    Turista turistaEnc = turistaService.buscarUnTurista(userDetail.getUsername());
			    if(turistaEnc != null) {
			    	
			    	if (turistaEnc.getPuntos()>=10) {
			    		turistaEnc.setPuntos(turistaEnc.getPuntos() - 10);
				    	turistaService.guardarTurista(turistaEnc);
					}
			    	else {
						return ("insuficiente");
					}
			    }
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/cupones/mostrar";
		 
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
		turistaService.rootGuardarTurista(nuevoTurista);
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
			turistaService.modificarTuristaRoot(turistaModificado);
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
	
}

