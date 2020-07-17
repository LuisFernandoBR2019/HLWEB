package com.ptc.helplife.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ptc.helplife.DAO.DashboardSangueDAO;

@Controller
public class DashboardController {
	
	@GetMapping("service/listar/dashboard/contarCampanha")
	public String contarCampanha(Model model) throws SQLException {
		DashboardSangueDAO contarCampanha = new DashboardSangueDAO();
		Integer contador = contarCampanha.contadorCampanha();
		model.addAttribute("contadorCampanha", contador);

		return "service/listar/dashboard/contarCampanha";

	}

}
