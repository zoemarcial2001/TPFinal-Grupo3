package ar.edu.unju.edm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
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
import ar.edu.unju.edm.service.IFotografiaService;
import ar.edu.unju.edm.service.IPoIService;
import ar.edu.unju.edm.service.ITuristaService;

@Controller
public class PoIController{

	@Autowired
	@Qualifier("impsql")
	IPoIService poIService;
	
	@Autowired
	ITuristaService turistaService;
	
	@Autowired
	IFotografiaService fotografiaService;
	
	PoI poiPrueba;
	
	@GetMapping("/poI/cargar")
	public String cargarPoI(Model model) {
		model.addAttribute("unPoI", poIService.crearPoI());
		return("poI");
	}
	
	@PostMapping(value="/poI/guardar", consumes = "multipart/form-data")
	public String guardarNuevoPoI(@Valid @ModelAttribute("unPoI") PoI nuevoPoI, @RequestParam("file") MultipartFile file, @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3, BindingResult resultado, Model model) throws IOException {
		//Fotografia nuevaFotografia = new Fotografia();
		
		if(resultado.hasErrors()) {
			model.addAttribute("unPoI", nuevoPoI);
			model.addAttribute("poIs", poIService.obtenerTodosPoIs());
			return "poI";
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
					/*
					for(int i = 0; i < files.length; i++) {
						MultipartFile file = files[i];
						System.out.println(files.length);
						byte[] content = file.getBytes();
						nuevaFotografia = fotografiaService.crearFotografia();
						String base64 = Base64.getEncoder().encodeToString(content);
						nuevaFotografia.setImagen(base64);
						nuevaFotografia.setPoI(nuevoPoI);
						//fotografiaService.guardarFotografia(nuevaFotografia);
						nuevoPoI.getFotografias().add(nuevaFotografia);
					}
					*/
					
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
		//List<PoI>listaPoIs = poIService.obtenerTodosPoIs();
		//listaPoIs = listaPoIs.stream().sorted((p1,p2)->p1.getNombrePoI().compareTo(p2.getNombrePoI())).collect(Collectors.toList());
		model.addAttribute("poIs", poIService.obtenerTodosPoIs()); 
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
	
	@GetMapping("/poI/ver/{codigoPoI}")
	public String verPoI( @PathVariable(name="codigoPoI") int codigoPoI, Model model) {
		try {
			model.addAttribute("unPoI", poIService.encontrarUnPoI(codigoPoI));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
		}
		
		return("verpoi");
	}
	

}
