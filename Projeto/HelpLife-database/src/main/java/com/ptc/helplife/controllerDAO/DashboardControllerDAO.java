package com.ptc.helplife.controllerDAO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptc.helplife.service.DashboardService;

@RestController // indica classe controle;
@RequestMapping("/api/v1/helplife")
@CrossOrigin(origins = "*")
public class DashboardControllerDAO {
	
	private DashboardService service = new DashboardService();

	@GetMapping("/dashboard/alimentaGraficoTabela")
	public Map<String,Integer> alimentaGraficoTabela() throws SQLException {
		Map<String,Integer> mapaSangue = new HashMap<>();
		mapaSangue = service.mapaSangue();
		return mapaSangue;
	}
	
	@GetMapping("/dashboard/contadorCampanha")
	public Integer contarCampanha() throws SQLException {
		Integer contador = 0;
		contador = service.graficoDashboardCampanha();
		return contador;
	}
	
	@GetMapping("/dashboard/contadorSolicitacao")
	public Integer contarSolicitacao() throws SQLException {
		Integer contador = 0;
		contador = service.graficoDashboardSolicitacao();
		
		return contador;
	}
	
}
