package com.ptc.helplife.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ptc.helplife.DAO.BaseImplementDAO;
import com.ptc.helplife.DAO.CampanhaDAO;
import com.ptc.helplife.Entity.Campanha;

public class MainController {

	@GetMapping("/")
	public String index(Model model) {

		BaseImplementDAO campanhaDao = new CampanhaDAO();
		Map<String, Object> criteria = new HashMap<>();
		criteria.put(CampanhaDAO.CRITERION_STATUS_EQ, 0);
		List<Campanha> campanhaList = campanhaDao.readByCriteria(criteria);
		System.out.println("Campanha:" + campanhaList);
		model.addAttribute("campanhaList", campanhaList != null ? campanhaList : Collections.EMPTY_LIST);

		return "index";

	}

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv;
		mv = new ModelAndView("/index");
		BaseImplementDAO campanhaDao = new CampanhaDAO();
		Map<String, Object> criteria = new HashMap<>();
		criteria.put(CampanhaDAO.CRITERION_STATUS_EQ, 0);
		List<Campanha> campanhaList = campanhaDao.readByCriteria(criteria);
		System.out.println("Campanha:" + campanhaList);
		mv.addObject("campanhaList", campanhaList != null ? campanhaList : Collections.EMPTY_LIST);
		return mv;
	}

	@PostMapping("/")
	public ModelAndView index(String email, String senha, HttpSession session) {
		ModelAndView mv;
		if (!email.isEmpty()) {
			try {
				URL url = new URL("http://localhost:9035/api/v1/helplife/campanha/");
				// String data = HttpRequestService.sendRequest(url, null, "GET");
				// System.out.println(data);
				mv = new ModelAndView("redirect:/login");

				return mv;
			} catch (MalformedURLException ex) {
				Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return new ModelAndView("redirect:/");
	}
}
