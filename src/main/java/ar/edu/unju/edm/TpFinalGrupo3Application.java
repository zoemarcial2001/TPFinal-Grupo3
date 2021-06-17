package ar.edu.unju.edm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ar.edu.unju.edm.model.Cupon;
import ar.edu.unju.edm.model.Turista;
import ar.edu.unju.edm.service.ICuponService;
import ar.edu.unju.edm.service.ITuristaService;

@SpringBootApplication
public class TpFinalGrupo3Application implements CommandLineRunner{

	@Autowired
	@Qualifier("impsql")
	ITuristaService turistaService;
	
	@Autowired
	Turista turista;
	
	@Autowired
	ICuponService cuponService;
	
	@Autowired
	Cupon cupon;
	
	public static void main(String[] args) {
		SpringApplication.run(TpFinalGrupo3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
  /*
			turista.setNombre("karen");
		   turista.setApellido("Merlos");
		
			turista.setEmail("cora@gmail.com");
			turista.setPassword("1234");	
			
			turista.setPais("Argentina");
			turistaService.guardarTurista(turista);	
			*/
		/*
		    cupon.setValor(10);
		    cupon.setDescripcion("canjealo en la sucursal mas cercana");
		    cupon.setDescuento(50);
		    cupon.setTitulo("en compras en supermercados comodin");
		    
		    cuponService.guardarCupon(cupon);
		    */
	}
}
