package ar.edu.unju.edm.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import ar.edu.unju.edm.model.Fotografia;
import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.IPoIService;
import ar.edu.unju.edm.service.ITuristaService;

@Controller
public class PoIController{

	@Autowired
	@Qualifier("impsql")
	IPoIService poIService;
	
	@Autowired
	ITuristaService turistaService;
	
	@GetMapping("/poI/cargar")
	public String cargarPoI(Model model) {
		model.addAttribute("unPoI", poIService.crearPoI());
		model.addAttribute("poIs", poIService.obtenerTodosPoIs());
		return("poI");
	}
	
	@PostMapping(value="/poI/guardar", consumes = "multipart/form-data")
	public String guardarNuevoPoI(@Valid @ModelAttribute("unPoI") PoI nuevoPoI, @RequestParam("file") MultipartFile file, BindingResult resultado, Model model) throws IOException {
		
		if(resultado.hasErrors()) {
			model.addAttribute("unPoI", nuevoPoI);
			model.addAttribute("poIs", poIService.obtenerTodosPoIs());
			return "poI";
		}
		else {
			
			//Fotografia nuevaFotografia = new Fotografia();
			//nuevaFotografia.setPoI(nuevoPoI);
			//byte[] content = file.getBytes();
			//String base64 = Base64.getEncoder().encodeToString(content);
			//nuevaFotografia.setImagen(base64);
			Authentication auth = SecurityContextHolder
		            .getContext()
		            .getAuthentication();
		    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		    try {
				Turista turistaEncontrado = turistaService.buscarUnTurista(userDetail.getUsername());
				if (turistaEncontrado != null) {
					nuevoPoI.setTuristaAutor(turistaEncontrado);
					poIService.guardarPoI(nuevoPoI);
					System.out.println(poIService.obtenerTodosPoIs());
					model.addAttribute("poIs", poIService.obtenerTodosPoIs().size());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		    return "redirect:/poI/mostrar";
			
		}
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
	
	@GetMapping("/poI/mostrar" )
	public String mostrarPoI(Model model) {
		List<PoI>listaPoIs = poIService.obtenerTodosPoIs();
		listaPoIs = listaPoIs.stream().sorted((p1,p2)->p1.getNombrePoI().compareTo(p2.getNombrePoI())).collect(Collectors.toList());
		model.addAttribute("poIs", listaPoIs); 
		return("pois");
	}
	
	
	@GetMapping(value= "/poI/cargarFotografias", consumes = "multipart/form-data" ) 
		public String guardarFotografia (@RequestParam("file") MultipartFile file, @ModelAttribute("unaFotografia") Fotografia nuevaFotografia, Model model) throws IOException {
		byte[] content = file.getBytes();
		String base64 = Base64.getEncoder().encodeToString(content);
		nuevaFotografia.setImagen(base64);
		return("fotografias");
	}

}
