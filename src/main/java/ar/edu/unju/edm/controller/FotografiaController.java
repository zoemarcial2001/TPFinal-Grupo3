package ar.edu.unju.edm.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ar.edu.unju.edm.model.Fotografia;
import ar.edu.unju.edm.service.IFotografiaService;
import ar.edu.unju.edm.service.IPoIService;

@Controller
public class FotografiaController {
	
	@Autowired
	IFotografiaService fotografiaService;
	
	@Autowired
	IPoIService poIService;
	

	@GetMapping ("/foto/cargar/{codigoPoI}")
	public String cargarFotografias(@PathVariable(name="codigoPoI") int codigo, Model model) {
		model.addAttribute("unaFotografia", fotografiaService.crearFotografia());
		model.addAttribute("fotografias", fotografiaService.obtenerTodasFotografias());
		return("fotografias");
	}
	
	@GetMapping(value="/foto/guardar/", consumes = "multipart/form-data" ) 
	public String cargarFotografia (@RequestParam("file") MultipartFile file, @ModelAttribute("unaFotografia") Fotografia nuevaFotografia, Model model) throws IOException {
		
		try {
			byte[] content = file.getBytes();
			String base64 = Base64.getEncoder().encodeToString(content);
			nuevaFotografia.setImagen(base64);
			nuevaFotografia.setPoI(poIService.encontrarUnPoI(codigo));
			fotografiaService.guardarFotografia(nuevaFotografia);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	return("fotografias");
   }
	
}
