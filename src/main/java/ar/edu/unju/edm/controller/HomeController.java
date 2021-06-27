package ar.edu.unju.edm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.edm.model.PoI;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.IPoIService;
import ar.edu.unju.edm.service.ITuristaService;

@Controller
public class HomeController {

	@Autowired
	IPoIService poIService;
	
	@Autowired
	ITuristaService turistaService;
	
	
	@GetMapping({"/","/login","/home", "/index","/login?error=true"})
	public String cargarHome(Model model){	
	return "home";
	}
	
	@GetMapping({"/inicio"})
	public String cargarInicio(Model model){	
	
		List<PoI> todosPoIs = poIService.masValorados();

		if (todosPoIs.size() > 0 ) {
		todosPoIs = todosPoIs.size()>3?todosPoIs.subList(0, 3):todosPoIs;
	}
		
		model.addAttribute("poIs", todosPoIs.size()>0?todosPoIs:null);
		
		
		List<Turista> todosTuristas = turistaService.conMasPuntos();
		
		if (todosTuristas.size()>0) {
			todosTuristas = todosTuristas.size()>3?todosTuristas.subList(0, 3):todosTuristas;
		}
		
		model.addAttribute("turista", todosTuristas.size()>0?todosTuristas:null);
		
		
	 return "inicio2";
	}
}	
	


