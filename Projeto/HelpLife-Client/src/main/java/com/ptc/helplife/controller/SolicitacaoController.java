package com.ptc.helplife.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ptc.helplife.DAO.BaseImplementDAO;
import com.ptc.helplife.DAO.SolicitacaoDAO;
import com.ptc.helplife.DAO.TipoSanguineoDAO;
import com.ptc.helplife.DAO.UsuarioDAO;
import com.ptc.helplife.Entity.Solicitacao;
import com.ptc.helplife.Entity.TipoSanguineo;
import com.ptc.helplife.Entity.Usuario;
import com.ptc.helplife.Entity.UsuarioComum;

@Controller
public class SolicitacaoController {

	@GetMapping("service/cadastro/feedbackSolicitacaoEdit")
	public ModelAndView feedbackSolicitacaoEdit(Model model) throws ParseException {
		ModelAndView mv;
		mv = new ModelAndView("service/cadastro/feedbackSolicitacaoEdit");
		return mv;
	}

	@GetMapping("service/cadastro/feedbackSolicitacao")
	public ModelAndView feedbackSolicitacao(Model model) throws ParseException {
		ModelAndView mv;
		mv = new ModelAndView("service/cadastro/feedbackSolicitacao");
		return mv;
	}

	@GetMapping("service/listar/listarSolicitacao")
	public ModelAndView readAllAtivas(Model model, HttpSession session) {
		BaseImplementDAO solicitacaoDao = new SolicitacaoDAO();
		ModelAndView mv;
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		if (usuarioLogado != null) {
			Map<String, Object> criteria = new HashMap<>();
			criteria.put(SolicitacaoDAO.CRITERION_STATUS_EQ, 0);
			List<Solicitacao> solicitacaoList = solicitacaoDao.readByCriteria(criteria);
			model.addAttribute("solicitacaoList", solicitacaoList != null ? solicitacaoList : Collections.EMPTY_LIST);
			return mv = new ModelAndView("service/listar/listarSolicitacao");
		} else {
			return mv = new ModelAndView("redirect:/menu/login");
		}
	}

	@GetMapping("service/listar/informacaoSolicitacao/{id}")
	public ModelAndView infoReadId(@PathVariable("id") Long id, Model model, HttpSession session) {
		BaseImplementDAO solicitacaoDao = new SolicitacaoDAO();
		ModelAndView mv;
		UsuarioComum usuario = (UsuarioComum) session.getAttribute("usuarioLogado");
		if (usuario != null) {
			Solicitacao solicitacao = (Solicitacao) solicitacaoDao.readById(id);

			model.addAttribute("solicitacao", solicitacao);
			return mv = new ModelAndView("service/listar/informacaoSolicitacao");
		} else {
			return mv = new ModelAndView("redirect:/menu/login");
		}
	}

	@GetMapping("service/listar/usuario/listarSolicitacaoUsuario")
	public ModelAndView listarSolicitacoesUsuario(HttpSession session) {
		BaseImplementDAO solicitacaoDao = new SolicitacaoDAO();
		UsuarioComum usuario = (UsuarioComum) session.getAttribute("usuarioLogado");
		ModelAndView mv = new ModelAndView("service/listar/usuario/listarSolicitacaoUsuario");
		if (usuario != null) {
			Map<String, Object> criteria = new HashMap<>();
			criteria.put(SolicitacaoDAO.CRITERION_USUARIO_ID_EQ, usuario.getId());
			SolicitacaoDAO dao = new SolicitacaoDAO();

			List<Solicitacao> solicitacaoList = dao.readByCriteria(criteria);
			mv.addObject("solicitacaoList", solicitacaoList != null ? solicitacaoList : Collections.EMPTY_LIST);

			return mv;
		} else {
			return mv = new ModelAndView("redirect:/menu/login");
		}
	}

	@GetMapping("service/cadastro/cadastroSolicitacao")
	public ModelAndView gocadastroSolicitacao(Model model, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		ModelAndView mv;
		String msg = "HELP LIFE - SISTEMA  GERENCIADOR DE DOACAO SANGUINEA";
		model.addAttribute("msg", msg);
		if (usuarioLogado != null) {
			TipoSanguineoDAO tipoSanguineoDao = new TipoSanguineoDAO();
			List<TipoSanguineo> tipoSanguineoList = tipoSanguineoDao.readAll();
			model.addAttribute("tipoSanguineoList", tipoSanguineoList);

			UsuarioDAO usuarioHemocentroDao = new UsuarioDAO();
			Map<String, Object> criteria = new HashMap<>();
			criteria.put(UsuarioDAO.CRITERION_USUARIO_HEMOCENTRO, 1);
			List<Usuario> hemocentroList = usuarioHemocentroDao.readByCriteria(criteria);
			model.addAttribute("hemocentroList", hemocentroList);
			return mv = new ModelAndView("service/cadastro/cadastroSolicitacao");
		} else {
			return mv = new ModelAndView("redirect:/menu/login");
		}
	}

	@PostMapping("service/cadastro/cadastroSolicitacao")
	public ModelAndView CadastroSolicitacao(Solicitacao solicitacao, HttpSession session, String[] tipoSanguineoId) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		UsuarioComum usuarioComum = (UsuarioComum) usuarioLogado;
		ModelAndView mv = null;
		solicitacao.setUsuarioComum(usuarioComum);
		solicitacao.setStatus(Solicitacao.StatusSolicitacao.STATUS_ATIVA);
		List<TipoSanguineo> tipoSanguineoList = new ArrayList<TipoSanguineo>();
		if (usuarioLogado != null) {
			if (tipoSanguineoId != null) {
				for (String idTipoSanguineo : tipoSanguineoId) {
					TipoSanguineo tipoSanguineo = new TipoSanguineo();
					tipoSanguineo.setId(Long.parseLong(idTipoSanguineo));
					tipoSanguineoList.add(tipoSanguineo);
				}
				solicitacao.setTipoSanguineoList(tipoSanguineoList);
			}
			// validação
			Map<String, String> errors = new LinkedHashMap<>();
			String descricao = solicitacao.getDescricao();
			if (descricao == null || descricao.isEmpty()) {
				errors.put("nome", "Campo nome obrigatório.");
			}

			String dataHora = solicitacao.getDataHora();
			if (dataHora == null) {
				errors.put("dataHora", "Campo data Obrigatório.");
			} else {
				// validação da data formato
			}

			List<TipoSanguineo> tipoSanguineoList1 = solicitacao.getTipoSanguineoList();
			if (tipoSanguineoList1 == null) {
				errors.put("tipoSanguineoList", "Campo Tipo Sanguineo Obrigatótio.");
			}

			Usuario hemocentro = solicitacao.getHemocentro();
			if (hemocentro == null || hemocentro.getId() == 0) {
				errors.put("hemocentro", "Campo hemocentro Obrigatório.");
			}

			if (errors.isEmpty()) {
				SolicitacaoDAO dao = new SolicitacaoDAO();
				dao.create(solicitacao);
				UsuarioController.buscarDoadoresParaSolicitacao(tipoSanguineoId);
				mv = new ModelAndView("redirect:/service/listar/usuario/listarSolicitacaoUsuario");
				return mv;
			} else {
				mv = new ModelAndView("service/cadastro/cadastroSolicitacao");
				List<TipoSanguineo> listaSangue = new TipoSanguineoDAO().readAll();
				mv.addObject("tipoSanguineoList", listaSangue);

				UsuarioDAO usuarioHemocentroDao = new UsuarioDAO();
				Map<String, Object> criteria = new HashMap<>();
				criteria.put(UsuarioDAO.CRITERION_USUARIO_HEMOCENTRO, 1);
				List<Usuario> hemocentroList = usuarioHemocentroDao.readByCriteria(criteria);
				mv.addObject("hemocentroList", hemocentroList);
				mv.addObject("erro", errors);
				mv.addObject("solicitacao", solicitacao);
				return mv;
			}
		} else {
			return mv = new ModelAndView("redirect:/menu/login");
		}
	}

	@GetMapping("service/editar/editarSolicitacao/{id}")
	public ModelAndView goCadastroSolicitacao(@PathVariable("id") Long id, Model model, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		String msg = "SANGUE BOM - SISTEMA  GERENCIADOR DE DOACAO SANGUINEA";
		ModelAndView mv;
		if (usuarioLogado != null) {
			Solicitacao solicitacao = new SolicitacaoDAO().readById(id);
			solicitacao.setStatus(Solicitacao.StatusSolicitacao.STATUS_ATIVA);

			Map<String, Object> criteria = new HashMap<>();
			criteria.put(UsuarioDAO.CRITERION_USUARIO_HEMOCENTRO, 1);
			List<Usuario> hemocentroList = new UsuarioDAO().readByCriteria(criteria);

			TipoSanguineoDAO tipoSanguineoDao = new TipoSanguineoDAO();
			List<TipoSanguineo> tipoSanguineoList = tipoSanguineoDao.readAll();

			model.addAttribute("tipoSanguineoList", tipoSanguineoList);
			model.addAttribute("solicitacaoTipoSanguineoList", solicitacao.getTipoSanguineoList());
			model.addAttribute("usuarioLogado", usuarioLogado);
			model.addAttribute("solicitacao", solicitacao);
			model.addAttribute("hemocentroList", hemocentroList);
			model.addAttribute("msg", msg);

			return mv = new ModelAndView("service/editar/editarSolicitacao");
		} else {
			return mv = new ModelAndView("redirect:/menu/login");
		}
	}

	@PostMapping("service/editar/editarSolicitacao/{id}")
	public ModelAndView UpdateSolicitacao(@PathVariable("id") Long id, Solicitacao solicitacao, HttpSession session,
			String[] tipoSanguineoId) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		solicitacao.setUsuarioComum((UsuarioComum) usuarioLogado);
		solicitacao.setStatus(Solicitacao.StatusSolicitacao.STATUS_ATIVA);
		List<TipoSanguineo> tipoSanguineoList = null;
		ModelAndView mv = null;
		if (usuarioLogado != null) {
			tipoSanguineoList = new ArrayList<TipoSanguineo>();
			if (tipoSanguineoId != null) {
				for (String idTipoSanguineo : tipoSanguineoId) {
					TipoSanguineo tipoSanguineo = new TipoSanguineo();
					tipoSanguineo.setId(Long.parseLong(idTipoSanguineo));
					tipoSanguineoList.add(tipoSanguineo);
				}
				solicitacao.setTipoSanguineoList(tipoSanguineoList);
			}
			// validação
			Map<String, String> errors = new LinkedHashMap<>();
			String descricao = solicitacao.getDescricao();
			if (descricao == null || descricao.isEmpty()) {
				errors.put("nome", "Campo nome obrigatório.");
			}

			String dataHora = solicitacao.getDataHora();
			if (dataHora == null) {
				errors.put("dataHora", "Campo data Obrigatório.");
			} else {
				// validação da data formato
			}

			List<TipoSanguineo> tipoSanguineoList1 = solicitacao.getTipoSanguineoList();
			if (tipoSanguineoList1 == null) {
				errors.put("tipoSanguineoList", "Campo Tipo Sanguineo Obrigatótio.");
			}

			Usuario hemocentro = solicitacao.getHemocentro();
			if (hemocentro == null || hemocentro.getId() == 0) {
				errors.put("hemocentro", "Campo hemocentro Obrigatório.");
			}

			if (errors.isEmpty()) {
				SolicitacaoDAO dao = new SolicitacaoDAO();
				dao.update(solicitacao);
				mv = new ModelAndView("redirect:/service/listar/usuario/listarSolicitacaoUsuario");
				return mv;
			} else {
				mv = new ModelAndView("redirect:/service/editar/editarSolicitacao/" + id);
				return mv;
			}
		} else {
			return mv = new ModelAndView("redirect:/menu/login");
		}
	}

	@GetMapping("service/inativar/inativarSolicitacao/{id}")
	public ModelAndView inativarSolicitacao(@PathVariable("id") Long id, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		ModelAndView mv;
		if (usuarioLogado != null) {
			SolicitacaoDAO dao = new SolicitacaoDAO();
			Solicitacao solicitacao = dao.readById(id);
			solicitacao.setStatus(Solicitacao.StatusSolicitacao.STATUS_INATIVA);
			dao.update(solicitacao);
			mv = new ModelAndView("redirect:/service/listar/usuario/listarSolicitacaoUsuario");
			return mv;
		} else {
			return mv = new ModelAndView("redirect:/menu/login");
		}
	}

	@GetMapping("service/ativar/ativarSolicitacao/{id}")
	public ModelAndView ativarSolicitacao(@PathVariable("id") Long id, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		ModelAndView mv;
		if (usuarioLogado != null) {
			SolicitacaoDAO dao = new SolicitacaoDAO();
			Solicitacao solicitacao = dao.readById(id);
			solicitacao.setStatus(Solicitacao.StatusSolicitacao.STATUS_ATIVA);
			dao.update(solicitacao);
			mv = new ModelAndView("redirect:/service/listar/usuario/listarSolicitacaoUsuario");
			return mv;
		} else {
			return mv = new ModelAndView("redirect:/menu/login");
		}
	}

}
