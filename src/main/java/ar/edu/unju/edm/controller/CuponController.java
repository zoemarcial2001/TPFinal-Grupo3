package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Cupon;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.ICuponService;
import ar.edu.unju.edm.service.ITuristaService;

@Controller
public class CuponController {

	@Autowired
	ITuristaService turistaService;
	
	@Autowired
	ICuponService cuponService;
	
	@GetMapping("/cupones/mostrar")
    public String mostrarCupones(Model model) {
		
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
	
	@GetMapping("/cupones/cargar")
	  public String cargarCupon(Model model) {
			model.addAttribute("unCupon", cuponService.crearCupon());
			return("cupones");
		}
	
    }

  