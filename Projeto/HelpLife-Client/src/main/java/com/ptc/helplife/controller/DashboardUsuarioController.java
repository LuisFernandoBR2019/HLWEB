package com.ptc.helplife.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ptc.helplife.DAO.BaseImplementDAO;
import com.ptc.helplife.DAO.CampanhaDAO;
import com.ptc.helplife.DAO.SolicitacaoDAO;
import com.ptc.helplife.Entity.Campanha;
import com.ptc.helplife.Entity.Solicitacao;
import com.ptc.helplife.Entity.Usuario;

@Controller
public class DashboardUsuarioController {

	@GetMapping("dashboards/Usuario")
	public ModelAndView goDashboardUsuario(Model model, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		ModelAndView mv;
		if (usuarioLogado != null) {
		BaseImplementDAO campanhaDao = new CampanhaDAO();
		Map<String, Object> criteria = new HashMap<>();
		criteria.put(CampanhaDAO.CRITERION_STATUS_EQ, 0);
		List<Campanha> campanhaList = campanhaDao.readByCriteria(criteria);
		model.addAttribute("campanhaList", campanhaList != null ? campanhaList : Collections.EMPTY_LIST);

		BaseImplementDAO solicitacaoDao = new SolicitacaoDAO();
		Map<String, Object> criteria1 = new HashMap<>();
		criteria1.put(SolicitacaoDAO.CRITERION_STATUS_EQ, 0);
		List<Solicitacao> solicitacaoList = solicitacaoDao.readByCriteria(criteria1);
		model.addAttribute("solicitacaoList", solicitacaoList != null ? solicitacaoList : Collections.EMPTY_LIST);

		return  mv = new ModelAndView("dashboards/Usuario");
		}else {
			return  mv = new ModelAndView("redirect:/menu/login");
		}
	}

	@GetMapping("dashboards/Hemocentro")
	public ModelAndView goDashboardHemocentro(Model model, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		ModelAndView mv;
		if (usuarioLogado != null) {
		BaseImplementDAO campanhaDao = new CampanhaDAO();
		Map<String, Object> criteria = new HashMap<>();
		criteria.put(CampanhaDAO.CRITERION_STATUS_EQ, 0);
		List<Campanha> campanhaList = campanhaDao.readByCriteria(criteria);
		model.addAttribute("campanhaList", campanhaList != null ? campanhaList : Collections.EMPTY_LIST);
		return mv = new ModelAndView("dashboards/Hemocentro");
		}else {
			return  mv = new ModelAndView("redirect:/menu/login");
		}
	}

}
