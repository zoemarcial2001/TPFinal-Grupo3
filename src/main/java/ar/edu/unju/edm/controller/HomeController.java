package ar.edu.unju.edm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	
		List<PoI> todosPoIs = poIService.obtenerTodosPoIs();
		
	if (todosPoIs.size() >= 3 ) {
		List<PoI> mas = new ArrayList<>();
		PoI prueba = poIService.masValorados().get(0);
		PoI prueba1 = poIService.masValorados().get(1);
		PoI prueba2 = poIService.masValorados().get(2);
		
		if(prueba != null){
			mas.add(prueba);
			if(prueba1 != null) {
				mas.add(prueba1);
				if(prueba2 != null) {
					mas.add(prueba2);
				}
			}
			model.addAttribute("poIs", mas);
	     }
		else {		
			model.addAttribute("poIs", poIService.obtenerTodosPoIs());
		}
		
		
		 List<Turista> todosTuristas = turistaService.obtenerTodosTuristas();
		
		 if (todosTuristas.size() >= 3) {
			 List<Turista> masPuntos = new ArrayList<>();
				Turista pruebaT = turistaService.conMasPuntos().get(0);
				Turista pruebaT1 = turistaService.conMasPuntos().get(1);
				Turista pruebaT2 = turistaService.conMasPuntos().get(2);
				
				masPuntos.add(pruebaT);
				masPuntos.add(pruebaT1);
				masPuntos.add(pruebaT2);
				
				model.addAttribute("turista", masPuntos);
		}
		 else {
				model.addAttribute("turista", turistaService.obtenerTodosTuristas());
		}
		
	 
	}
	 return "inicio2";
	}
}	
	


