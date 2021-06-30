package ar.edu.unju.edm.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

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

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.IPoIService;
import ar.edu.unju.edm.service.ITuristaService;

@Controller
public class PoIController{

	@Autowired
	IPoIService poIService;
	
	@Autowired
	ITuristaService turistaService;

	
	@GetMapping("/poI/cargar")
	public String cargarPoI(Model model) {
		model.addAttribute("unPoI", poIService.crearPoI());
		return("agregarpoi");
	}
	
	@PostMapping(value="/poI/guardar", consumes = "multipart/form-data")
	public String guardarNuevoPoI(@Valid @ModelAttribute("unPoI") PoI nuevoPoI, BindingResult resultado, @RequestParam("foto") MultipartFile file, @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3,  Model model) throws IOException {
		
		if(resultado.hasErrors()) {
			model.addAttribute("unPoI", nuevoPoI);
			model.addAttribute("poIs", poIService.obtenerTodosPoIs());
			return "agregarpoi";
		}
		else {
			
			byte[] content = file.getBytes();
			byte[] content2 = file2.getBytes();
			byte[] content3 = file3.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			String base65 = Base64.getEncoder().encodeToString(content2);
			String base66 = Base64.getEncoder().encodeToString(content3);
			
            if(base64.equals("")) {
            	
			}
			else {
			
			    nuevoPoI.setImagen(base64);
			}
			if(base65.equals("")) {
				
			}
			else {
				nuevoPoI.setImagen2(base65);
			}
			if(base66.equals("")) {
				
			}
			else {
				nuevoPoI.setImagen3(base66);
			}
			
			
			Authentication auth = SecurityContextHolder
		            .getContext()
		            .getAuthentication();
		    UserDetails userDetail = (UserDetails) auth.getPrincipal();
		    try {
				Turista turistaEncontrado = turistaService.buscarUnTurista(userDetail.getUsername());
				if (turistaEncontrado != null) {
					
					turistaEncontrado.setPuntos(turistaEncontrado.getPuntos() + 10);
					nuevoPoI.setTuristaAutor(turistaEncontrado);
					poIService.guardarPoI(nuevoPoI);
					model.addAttribute("poIs", poIService.obtenerTodosPoIs());
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
		return("agregarpoi");
	}
	
	@PostMapping( value="/poI/modificar", consumes = "multipart/form-data")
	public String modificarPoI(@ModelAttribute("unPoI") PoI poIModificado, @RequestParam("foto") MultipartFile file, @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3, Model model) throws IOException{
		try {
			if (!file.isEmpty() ) {
				byte[] content = file.getBytes();
				String base64 = Base64.getEncoder().encodeToString(content);
				 poIModificado.setImagen(base64);
			}
			
			if (!file2.isEmpty()) {
				byte[] content2 = file2.getBytes();
				String base65 = Base64.getEncoder().encodeToString(content2);
				poIModificado.setImagen2(base65);
			}
			
			if (!file3.isEmpty()) {
				byte[] content3 = file3.getBytes();
				String base66 = Base64.getEncoder().encodeToString(content3);
				poIModificado.setImagen3(base66);
			}
			
			poIService.modificarPoI(poIModificado);
			
			model.addAttribute("unPoI", new PoI());				
		} catch (Exception e) {
			
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unPoI", poIModificado);			
		}
		model.addAttribute("poIs", poIService.obtenerTodosPoIs());
		return "redirect:/poI/mostrar/mispois";
	}
	
	@GetMapping("/poI/eliminarPoI/{codigo}")
	public String eliminarPoI(Model model, @PathVariable(name="codigo") int codigo) throws Exception {		
		
			try {		
				poIService.eliminarPoI(codigo);			
			}
			catch(Exception e){
				model.addAttribute("listErrorMessage",e.getMessage());
			}		
			return "redirect:/poI/mostrar/mispois";	
	}
	
	@GetMapping("/poI/mostrar" )
	public String mostrarPoI(Model model) {
		model.addAttribute("poIs", poIService.obtenerTodosPoIs()); 
		return("pois");
	}
	
	@GetMapping("/poI/mostrarValorados" )
	public String mostrarMasValorados(Model model) {
		
		model.addAttribute("poIs", poIService.masValorados()); 
		
		return("pois");
	}
	
	@GetMapping("/poI/mostrarAlfabeto")
	public String mostrarAlfabeticamente(Model model) {
	List<PoI>listaPoIs = poIService.obtenerTodosPoIs();
	listaPoIs = listaPoIs.stream().sorted((p1,p2)->p1.getNombrePoI().compareTo(p2.getNombrePoI())).collect(Collectors.toList());
	model.addAttribute("poIs", listaPoIs);
	return("pois");
	}
	
	@GetMapping("/poI/mostrar/mispois" )
	public String mostrarMisPoIs(Model model) {
		
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    
	    try {
			Turista turista = turistaService.buscarUnTurista(userDetail.getUsername());
			model.addAttribute("poIs", poIService.obtenerMisPoIs(turista));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return("mispois");
	}
	
	
	@GetMapping ("/cancelar")
	public String cancelar() {
		return "redirect:/poI/mostrar/mispois";
	}
	
	//metodos para usuario root
	
	@GetMapping("/poI/cargarRoot")
	public String mostrarPoIRoot(Model model) {
		model.addAttribute("poIs", poIService.obtenerTodosPoIs());
		return("poiroot");
	}
	
	@GetMapping("/poI/eliminarRoot/{codigo}")
	public String eliminarPoIRoot(Model model, @PathVariable(name="codigo") int codigo) {		
		
		try {		
				poIService.eliminarPoI(codigo);			
			}
			catch(Exception e){
				model.addAttribute("listErrorMessage",e.getMessage());
			}
		return "redirect:/poI/cargarRoot";
		
	}

}
