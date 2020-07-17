package com.ptc.helplife.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
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

import com.ptc.helplife.DAO.CampanhaDAO;
import com.ptc.helplife.DAO.TipoSanguineoDAO;
import com.ptc.helplife.DAO.UsuarioDAO;
import com.ptc.helplife.Entity.Campanha;
import com.ptc.helplife.Entity.TipoSanguineo;
import com.ptc.helplife.Entity.Usuario;
import com.ptc.helplife.Entity.UsuarioHemocentro;
import com.ptc.helplife.service.CampanhaService;

@Controller
public class CampanhaController {
	CampanhaService campanhaService;
	
	@GetMapping("service/cadastro/feedbackCampanha")
	public ModelAndView feedbackCampanha(Model model) throws ParseException {
		ModelAndView mv;
		mv = new ModelAndView("service/cadastro/feedbackCampanha");
		return mv;
	}
	
	@GetMapping("service/cadastro/feedbackCampanhaEdit")
	public ModelAndView feedbackCampanhaEdit(Model model) throws ParseException {
		ModelAndView mv;
		mv = new ModelAndView("service/cadastro/feedbackCampanhaEdit");
		return mv;
	}

	@GetMapping("service/listar/listarCampanha")
	public ModelAndView readAllCampanhasAtivas(Model model, HttpSession session) {
		campanhaService = new CampanhaService();
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		Boolean isHemocentro = usuarioLogado instanceof UsuarioHemocentro;
		model.addAttribute("isHemocentro", isHemocentro);
		ModelAndView mv;
		if (usuarioLogado != null) {
			mv = new ModelAndView("service/listar/listarCampanha");
			Map<String, Object> criteria = new HashMap<>();
			criteria.put(CampanhaDAO.CRITERION_STATUS_EQ, 0);
			List<Campanha> campanhaList = campanhaService.readByCriteria(criteria);

			model.addAttribute("campanhaList", campanhaList != null ? campanhaList : Collections.EMPTY_LIST);

			return mv;
		} else {
			mv = new ModelAndView("redirect:/menu/login");
			return mv;
		}
	}
	@GetMapping("service/listar/HemocentrolistarCampanha")
	public ModelAndView readAllCampanhasAtivasParaHemocentro(Model model, HttpSession session) {
		campanhaService = new CampanhaService();
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		Boolean isHemocentro = usuarioLogado instanceof UsuarioHemocentro;
		model.addAttribute("isHemocentro", isHemocentro);
		ModelAndView mv;
		if (usuarioLogado != null) {
			mv = new ModelAndView("service/listar/HemocentrolistarCampanha");
			Map<String, Object> criteria = new HashMap<>();
			criteria.put(CampanhaDAO.CRITERION_STATUS_EQ, 0);
			List<Campanha> campanhaList = campanhaService.readByCriteria(criteria);

			model.addAttribute("campanhaList", campanhaList != null ? campanhaList : Collections.EMPTY_LIST);

			return mv;
		} else {
			mv = new ModelAndView("redirect:/menu/login");
			return mv;
		}
	}

	@GetMapping("service/listar/informacaoCampanha/{id}")
	public ModelAndView infoReadId(@PathVariable("id") Long id, Model model, HttpSession session) {
		campanhaService = new CampanhaService();
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		Boolean isHemocentro = usuarioLogado instanceof UsuarioHemocentro;
		ModelAndView mv;
		if (usuarioLogado != null) {
			Campanha campanha = (Campanha) campanhaService.readById(id);
			mv = new ModelAndView("service/listar/informacaoCampanha");
			mv.addObject("isHemocentro", isHemocentro);
			mv.addObject("campanha", campanha);
			return mv;
		} else {
			mv = new ModelAndView("redirect:/menu/login");
			return mv;
		}
	}
	
	@GetMapping("service/listar/informacaoCampanhaHemocentro/{id}")
	public ModelAndView infoReadIdHemocentro(@PathVariable("id") Long id, Model model, HttpSession session) {
		campanhaService = new CampanhaService();
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		Boolean isHemocentro = usuarioLogado instanceof UsuarioHemocentro;
		ModelAndView mv;
		if (usuarioLogado != null) {
			Campanha campanha = (Campanha) campanhaService.readById(id);
			mv = new ModelAndView("service/listar/informacaoCampanhaHemocentro");
			mv.addObject("isHemocentro", isHemocentro);
			mv.addObject("campanha", campanha);
			return mv;
		} else {
			mv = new ModelAndView("redirect:/menu/login");
			return mv;
		}
	}

	@GetMapping("service/listar/listarCampanhaHemocentro")
	public ModelAndView listarCampanhasHemocentro(HttpSession session) {
		UsuarioHemocentro usuario = (UsuarioHemocentro) session.getAttribute("usuarioLogado");
		ModelAndView mv = new ModelAndView("service/listar/listarCampanhaHemocentro");
		if (usuario != null) {
			Map<String, Object> criteria = new HashMap<>();
			criteria.put(CampanhaDAO.CRITERION_USUARIO_ID_EQ, usuario.getId());
			campanhaService = new CampanhaService();
			List<Campanha> campanhaList = campanhaService.readByCriteria(criteria);
			mv.addObject("campanhaList", campanhaList != null ? campanhaList : Collections.EMPTY_LIST);

			return mv;
		} else {
			mv = new ModelAndView("redirect:/menu/login");
			return mv;
		}
	}

	@GetMapping("service/listar/usuario/listarCampanhaUsuario")
	public ModelAndView listarCampanhasUsuario(HttpSession session) {
		campanhaService = new CampanhaService();
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		ModelAndView mv = new ModelAndView("service/listar/usuario/listarCampanhaUsuario");
		if (usuario != null) {
			Map<String, Object> criteria = new HashMap<>();
			criteria.put(CampanhaDAO.CRITERION_USUARIO_ID_EQ, usuario.getId());
			campanhaService = new CampanhaService();

			List<Campanha> campanhaList = campanhaService.readByCriteria(criteria);
			mv.addObject("campanhaList", campanhaList != null ? campanhaList : Collections.EMPTY_LIST);
			return mv;
		} else {
			mv = new ModelAndView("redirect:/menu/login");
			return mv;
		}
	}

	@GetMapping("service/cadastro/cadastroCampanha")
	public ModelAndView goCadastroCampanha(Model model, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		String msg = "HELP LIFE - SISTEMA  GERENCIADOR DE DOACAO SANGUINEA";

		Boolean isHemocentro = usuarioLogado instanceof UsuarioHemocentro;
		ModelAndView mv;
		if (usuarioLogado != null) {
			TipoSanguineoDAO tipoSanguineoDao = new TipoSanguineoDAO();
			List<TipoSanguineo> tipoSanguineoList = tipoSanguineoDao.readAll();
			model.addAttribute("tipoSanguineoList", tipoSanguineoList);

			UsuarioDAO usuarioHemocentroDao = new UsuarioDAO();
			Map<String, Object> criteria = new HashMap<>();
			criteria.put(UsuarioDAO.CRITERION_USUARIO_HEMOCENTRO, 1);
			List<Usuario> hemocentroList = usuarioHemocentroDao.readByCriteria(criteria);
			mv = new ModelAndView("service/cadastro/cadastroCampanha");
			mv.addObject("hemocentroList", hemocentroList);
			mv.addObject("isHemocentro", isHemocentro);
			return mv;
		} else {
			mv = new ModelAndView("redirect:/menu/login");
			return mv;
		}
	}

	@GetMapping("service/cadastro/cadastroCampanhaHemocentro")
	public ModelAndView goCadastroCampanhaHemocentro(Model model, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		String msg = "HELP LIFE - SISTEMA  GERENCIADOR DE DOACAO SANGUINEA";

		Boolean isHemocentro = usuarioLogado instanceof UsuarioHemocentro;
		model.addAttribute("isHemocentro", isHemocentro);
		ModelAndView mv;
		if (usuarioLogado != null) {
			TipoSanguineoDAO tipoSanguineoDao = new TipoSanguineoDAO();
			List<TipoSanguineo> tipoSanguineoList = tipoSanguineoDao.readAll();
			model.addAttribute("tipoSanguineoList", tipoSanguineoList);

			UsuarioDAO usuarioHemocentroDao = new UsuarioDAO();
			Map<String, Object> criteria = new HashMap<>();
			criteria.put(UsuarioDAO.CRITERION_USUARIO_HEMOCENTRO, 1);
			List<Usuario> hemocentroList = usuarioHemocentroDao.readByCriteria(criteria);
			model.addAttribute("hemocentroList", hemocentroList);
			mv = new ModelAndView("service/cadastro/cadastroCampanhaHemocentro");
			return mv;
		} else {
			mv = new ModelAndView("redirect:/menu/login");
			return mv;
		}
	}

	@PostMapping("service/cadastro/cadastroCampanha")
	public ModelAndView CadastroCampanha(Campanha campanha, HttpSession session, String[] tipoSanguineoId) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		Boolean isHemocentro = usuarioLogado instanceof UsuarioHemocentro;
		ModelAndView mv = null;
		List<TipoSanguineo> tipoSanguineoList = new ArrayList<TipoSanguineo>();
		if (usuarioLogado != null) {
			if (tipoSanguineoId != null) {
				for (String idTipoSanguineo : tipoSanguineoId) {
					TipoSanguineo tipoSanguineo = new TipoSanguineo();
					tipoSanguineo.setId(Long.parseLong(idTipoSanguineo));
					tipoSanguineoList.add(tipoSanguineo);
				}
				campanha.setTipoSanguineoList(tipoSanguineoList);
			}
			// validação
			Map<String, String> errors = new LinkedHashMap<>();
			String descricao = campanha.getDescricao();
			if (descricao == null || descricao.isEmpty()) {
				errors.put("descricao", "Campo Descricao obrigatório.");
			}

			String nome = campanha.getNome();
			if (nome == null || nome.isEmpty()) {
				errors.put("nome", "Campo Nome campanha obrigatório.");
			}

			String dataInicio = campanha.getDataInicio();
			if (dataInicio == null) {
				errors.put("dataInicio", "Campo Data inicio Obrigatório.");
			}else {
				//validação da data formato
			}

			String dataFinal = campanha.getDataFinal();
			if (dataFinal == null) {
				errors.put("dataFinal", "Campo Data final Obrigatório.");
			}else {
				//validação da data formato
			}

			Usuario hemocentro = campanha.getHemocentro();
			if (hemocentro == null || hemocentro.getId() == 0) {
				errors.put("hemocentro", "Campo Hemocentro Obrigatório.");
			}

			List<TipoSanguineo> tipoSanguineoList1 = campanha.getTipoSanguineoList();
			if (tipoSanguineoList1 == null) {
				errors.put("tipoSanguineoList", "Campo Tipo Sanguineo Obrigatório.");
			}

			if (errors.isEmpty()) {

				campanha.setUsuario(usuarioLogado);
				campanha.setStatus(Campanha.StatusCampanha.STATUS_ATIVA);
				campanha.setTipoSanguineoList(tipoSanguineoList);

				campanhaService = new CampanhaService();
				campanhaService.create(campanha);
				UsuarioController.buscarDoadores(tipoSanguineoId);
				if (isHemocentro) {
					mv = new ModelAndView("redirect:/service/listar/listarCampanhaHemocentro");
					mv.addObject("isHemocentro", isHemocentro);
				} else {
					mv = new ModelAndView("redirect:/service/listar/usuario/listarCampanhaUsuario");
					mv.addObject("isHemocentro", isHemocentro);
				}
				return mv;
			} else {
				mv = new ModelAndView("service/cadastro/cadastroCampanha");
				List<TipoSanguineo> listaSangue = new TipoSanguineoDAO().readAll();
				mv.addObject("tipoSanguineoList", listaSangue);

				UsuarioDAO usuarioHemocentroDao = new UsuarioDAO();
				Map<String, Object> criteria = new HashMap<>();
				criteria.put(UsuarioDAO.CRITERION_USUARIO_HEMOCENTRO, 1);
				List<Usuario> hemocentroList = usuarioHemocentroDao.readByCriteria(criteria);
				mv.addObject("hemocentroList", hemocentroList);
				mv.addObject("erro", errors);
				mv.addObject("isHemocentro", isHemocentro);
				mv.addObject("campanha", campanha);
				return mv;
			}
		} else {
			mv = new ModelAndView("redirect:/menu/login");
			return mv;
		}
	}

	@PostMapping("service/cadastro/cadastroCampanhaHemocentro")
	public ModelAndView CadastroCampanhaHemocentro(Campanha campanha, HttpSession session, String[] tipoSanguineoId) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		Boolean isHemocentro = usuarioLogado instanceof UsuarioHemocentro;
		ModelAndView mv = null;
		List<TipoSanguineo> tipoSanguineoList = new ArrayList<TipoSanguineo>();
		if (usuarioLogado != null) {
			if (tipoSanguineoId != null) {
				for (String idTipoSanguineo : tipoSanguineoId) {
					TipoSanguineo tipoSanguineo = new TipoSanguineo();
					tipoSanguineo.setId(Long.parseLong(idTipoSanguineo));
					tipoSanguineoList.add(tipoSanguineo);
				}
				campanha.setTipoSanguineoList(tipoSanguineoList);
			}
			// validação
			Map<String, String> errors = new LinkedHashMap<>();
			String descricao = campanha.getDescricao();
			if (descricao == null || descricao.isEmpty()) {
				errors.put("descricao", "Campo Descricao obrigatório.");
			}

			String nome = campanha.getNome();
			if (nome == null || nome.isEmpty()) {
				errors.put("nome", "Campo Nome campanha obrigatório.");
			}

			String dataInicio = campanha.getDataInicio();
			if (dataInicio == null) {
				errors.put("dataInicio", "Campo Data inicio Obrigatório.");
			}else {
				//validação da data formato
			}

			String dataFinal = campanha.getDataFinal();
			if (dataFinal == null) {
				errors.put("dataFinal", "Campo Data final Obrigatório.");
			}else {
				//validação da data formato
			}

			List<TipoSanguineo> tipoSanguineoList1 = campanha.getTipoSanguineoList();
			if (tipoSanguineoList1 == null) {
				errors.put("tipoSanguineoList", "Campo Tipo Sanguineo Obrigatório.");
			}

			if (errors.isEmpty()) {

				campanha.setUsuario(usuarioLogado);
				campanha.setStatus(Campanha.StatusCampanha.STATUS_ATIVA);
				campanha.setTipoSanguineoList(tipoSanguineoList);
				campanhaService = new CampanhaService();
				campanhaService.create(campanha);
				UsuarioController.buscarDoadores(tipoSanguineoId);
				
				mv = new ModelAndView("redirect:/service/listar/listarCampanhaHemocentro");
				mv.addObject("isHemocentro", isHemocentro);
				
				return mv;
			} else {
				mv = new ModelAndView("service/cadastro/cadastroCampanhaHemocentro");
				List<TipoSanguineo> listaSangue = new TipoSanguineoDAO().readAll();
				mv.addObject("tipoSanguineoList", listaSangue);

				UsuarioDAO usuarioHemocentroDao = new UsuarioDAO();
				Map<String, Object> criteria = new HashMap<>();
				criteria.put(UsuarioDAO.CRITERION_USUARIO_HEMOCENTRO, 1);
				List<Usuario> hemocentroList = usuarioHemocentroDao.readByCriteria(criteria);
				mv.addObject("hemocentroList", hemocentroList);
				mv.addObject("erro", errors);
				mv.addObject("isHemocentro", isHemocentro);
				mv.addObject("campanha", campanha);
				return mv;
			}
		} else {
			mv = new ModelAndView("redirect:/menu/login");
			return mv;
		}
	}

	@GetMapping("service/editar/editarCampanha/{id}")
	public ModelAndView goCadastroCampanha(@PathVariable("id") Long id, Model model, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		String msg = "HELP LIFE - SISTEMA  GERENCIADOR DE DOACAO SANGUINEA";
		Boolean isHemocentro = usuarioLogado instanceof UsuarioHemocentro;
		ModelAndView mv;
		if (usuarioLogado != null) {
			Campanha campanha = campanhaService.readById(id);
			campanha.setStatus(Campanha.StatusCampanha.STATUS_ATIVA);

			Map<String, Object> criteria = new HashMap<>();
			criteria.put(UsuarioDAO.CRITERION_USUARIO_HEMOCENTRO, 1);
			List<Usuario> hemocentroList = new UsuarioDAO().readByCriteria(criteria);

			TipoSanguineoDAO tipoSanguineoDao = new TipoSanguineoDAO();
			List<TipoSanguineo> tipoSanguineoList = tipoSanguineoDao.readAll();

			model.addAttribute("tipoSanguineoList", tipoSanguineoList);
			model.addAttribute("campanhaTipoSanguineoList", campanha.getTipoSanguineoList());
			model.addAttribute("isHemocentro", isHemocentro);
			model.addAttribute("usuarioLogado", usuarioLogado);
			model.addAttribute("campanha", campanha);
			model.addAttribute("hemocentroList", hemocentroList);
			model.addAttribute("msg", msg);

			mv = new ModelAndView("service/editar/editarCampanha");
			return mv;
		} else {
			mv = new ModelAndView("redirect:/menu/login");
			return mv;
		}
	}

	@PostMapping("service/editar/editarCampanha/{id}")
	public ModelAndView updateCampanha(@PathVariable("id") Long id, Campanha campanha, HttpSession session,
			String[] tipoSanguineoId) {
		List<TipoSanguineo> tipoSanguineoList = null;
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		campanha.setUsuario(usuarioLogado);
		campanha.setStatus(Campanha.StatusCampanha.STATUS_ATIVA);
		ModelAndView mv = null;
		if (usuarioLogado != null) {
			tipoSanguineoList = new ArrayList<TipoSanguineo>();
			if (tipoSanguineoId != null) {
			for (String idTipoSanguineo : tipoSanguineoId) {
				TipoSanguineo tipoSanguineo = new TipoSanguineo();
				tipoSanguineo.setId(Long.parseLong(idTipoSanguineo));
				tipoSanguineoList.add(tipoSanguineo);
			}
			}
				campanha.setTipoSanguineoList(tipoSanguineoList);
				// validação
				Map<String, String> errors = new LinkedHashMap<>();
				String descricao = campanha.getDescricao();
				if (descricao == null || descricao.isEmpty()) {
					errors.put("descricao", "Campo Descricao obrigatório.");
				}

				String nome = campanha.getNome();
				if (nome == null || nome.isEmpty()) {
					errors.put("nome", "Campo Nome campanha obrigatório.");
				}

				String dataInicio = campanha.getDataInicio();
				if (dataInicio == null || dataInicio.isEmpty()) {
					errors.put("dataInicio", "Campo Data inicio Obrigatório.");
				}else {
					//Teste de formatação de data
				}

				String dataFinal = campanha.getDataFinal();
				if (dataFinal == null || dataFinal.isEmpty()) {
					errors.put("dataFinal", "Campo Data final Obrigatório.");
				}else {
					//Teste de formatação de data
				}

				Usuario hemocentro = campanha.getHemocentro();
				if (hemocentro == null || hemocentro.getId() == 0) {
					errors.put("hemocentro", "Campo Hemocentro Obrigatório.");
				}

				List<TipoSanguineo> tipoSanguineoList1 = campanha.getTipoSanguineoList();
				if (tipoSanguineoList1 == null || tipoSanguineoList1.isEmpty()) {
					errors.put("tipoSanguineoList", "Campo Tipo Sanguineo Obrigatório.");
				}

				if (errors.isEmpty()) {
					campanha.setTipoSanguineoList(tipoSanguineoList);
					campanhaService = new CampanhaService();
					campanhaService.update(campanha);
					mv = new ModelAndView("redirect:/service/listar/usuario/listarCampanhaUsuario");
					return mv;
				} else {
					mv = new ModelAndView("redirect:/service/editar/editarCampanha/"+id);
					return mv;
				}
			}else {
				mv = new ModelAndView("redirect:/menu/login");
				return mv;
			}

	}
	@GetMapping("service/editar/editarCampanhaHemocentro/{id}")
	public ModelAndView goCadastroCampanhaHemocentro(@PathVariable("id") Long id, Model model, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		String msg = "HELP LIFE - SISTEMA  GERENCIADOR DE DOACAO SANGUINEA";
		Boolean isHemocentro = usuarioLogado instanceof UsuarioHemocentro;
		ModelAndView mv;
		if (usuarioLogado != null) {
			Campanha campanha = campanhaService.readById(id);
			campanha.setStatus(Campanha.StatusCampanha.STATUS_ATIVA);

			Map<String, Object> criteria = new HashMap<>();
			criteria.put(UsuarioDAO.CRITERION_USUARIO_HEMOCENTRO, 1);
			List<Usuario> hemocentroList = new UsuarioDAO().readByCriteria(criteria);

			TipoSanguineoDAO tipoSanguineoDao = new TipoSanguineoDAO();
			List<TipoSanguineo> tipoSanguineoList = tipoSanguineoDao.readAll();

			model.addAttribute("tipoSanguineoList", tipoSanguineoList);
			model.addAttribute("campanhaTipoSanguineoList", campanha.getTipoSanguineoList());
			model.addAttribute("isHemocentro", isHemocentro);
			model.addAttribute("usuarioLogado", usuarioLogado);
			model.addAttribute("campanha", campanha);
			model.addAttribute("hemocentroList", hemocentroList);
			model.addAttribute("msg", msg);

			mv = new ModelAndView("service/editar/editarCampanhaHemocentro");
			return mv;
		} else {
			mv = new ModelAndView("redirect:/menu/login");
			return mv;
		}
	}

	@PostMapping("service/editar/editarCampanhaHemocentro/{id}")
	public ModelAndView updateCampanhaHemocentro(@PathVariable("id") Long id, Campanha campanha, HttpSession session,
			String[] tipoSanguineoId) {
		List<TipoSanguineo> tipoSanguineoList = null;
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		campanha.setUsuario(usuarioLogado);
		campanha.setStatus(Campanha.StatusCampanha.STATUS_ATIVA);
		ModelAndView mv = null;
		if (usuarioLogado != null) {
			tipoSanguineoList = new ArrayList<TipoSanguineo>();
			if (tipoSanguineoId != null) {
			for (String idTipoSanguineo : tipoSanguineoId) {
				TipoSanguineo tipoSanguineo = new TipoSanguineo();
				tipoSanguineo.setId(Long.parseLong(idTipoSanguineo));
				tipoSanguineoList.add(tipoSanguineo);
			}
			}
				campanha.setTipoSanguineoList(tipoSanguineoList);
				// validação
				Map<String, String> errors = new LinkedHashMap<>();
				String descricao = campanha.getDescricao();
				if (descricao == null || descricao.isEmpty()) {
					errors.put("descricao", "Campo Descricao obrigatório.");
				}

				String nome = campanha.getNome();
				if (nome == null || nome.isEmpty()) {
					errors.put("nome", "Campo Nome campanha obrigatório.");
				}

				String dataInicio = campanha.getDataInicio();
				if (dataInicio == null || dataInicio.isEmpty()) {
					errors.put("dataInicio", "Campo Data inicio Obrigatório.");
				}else {
					//Teste de formatação de data
				}

				String dataFinal = campanha.getDataFinal();
				if (dataFinal == null || dataFinal.isEmpty()) {
					errors.put("dataFinal", "Campo Data final Obrigatório.");
				}else {
					//Teste de formatação de data
				}

				Usuario hemocentro = campanha.getHemocentro();
				if (hemocentro == null || hemocentro.getId() == 0) {
					errors.put("hemocentro", "Campo Hemocentro Obrigatório.");
				}

				List<TipoSanguineo> tipoSanguineoList1 = campanha.getTipoSanguineoList();
				if (tipoSanguineoList1 == null || tipoSanguineoList1.isEmpty()) {
					errors.put("tipoSanguineoList", "Campo Tipo Sanguineo Obrigatório.");
				}

				if (errors.isEmpty()) {
					campanha.setTipoSanguineoList(tipoSanguineoList);
					campanhaService = new CampanhaService();
					campanhaService.update(campanha);
					mv = new ModelAndView("redirect:/service/listar/listarCampanhaHemocentro");
					return mv;
				} else {
					mv = new ModelAndView("redirect:/service/editar/editarCampanha/"+id);
					return mv;
				}
			}else {
				mv = new ModelAndView("redirect:/menu/login");
				return mv;
			}

	}

	@GetMapping("service/inativar/inativarCampanha/{id}")
	public ModelAndView inativarCampanha(@PathVariable("id") Long id, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		ModelAndView mv = null;
		if (usuarioLogado != null) {
			campanhaService = new CampanhaService();
			Campanha campanha = campanhaService.readById(id);
			campanha.setStatus(Campanha.StatusCampanha.STATUS_INATIVA);
			campanhaService.update(campanha);
			mv = new ModelAndView("redirect:/service/listar/usuario/listarCampanhaUsuario");
			return mv;
		} else {
			mv = new ModelAndView("redirect:/menu/login");
			return mv;
		}
	}

	@GetMapping("service/ativar/ativarCampanha/{id}")
	public ModelAndView ativarCampanha(@PathVariable("id") Long id, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		ModelAndView mv = null;
		if (usuarioLogado != null) {
			campanhaService = new CampanhaService();
			Campanha campanha = campanhaService.readById(id);
			campanha.setStatus(Campanha.StatusCampanha.STATUS_ATIVA);
			campanhaService.update(campanha);
			mv = new ModelAndView("redirect:/service/listar/usuario/listarCampanhaUsuario");
			return mv;
		} else {
			mv = new ModelAndView("redirect:/menu/login");
			return mv;
		}
	}
	@GetMapping("service/inativar/inativarCampanhaHemocentro/{id}")
	public ModelAndView inativarCampanhaHemocentro(@PathVariable("id") Long id, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		ModelAndView mv = null;
		if (usuarioLogado != null) {
			campanhaService = new CampanhaService();
			Campanha campanha = campanhaService.readById(id);
			campanha.setStatus(Campanha.StatusCampanha.STATUS_INATIVA);
			campanhaService.update(campanha);
			mv = new ModelAndView("redirect:/service/listar/listarCampanhaHemocentro");
			return mv;
		} else {
			mv = new ModelAndView("redirect:/menu/login");
			return mv;
		}
	}

	@GetMapping("service/ativar/ativarCampanhaHemocentro/{id}")
	public ModelAndView ativarCampanhaHemocentro(@PathVariable("id") Long id, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		ModelAndView mv = null;
		if (usuarioLogado != null) {
			campanhaService = new CampanhaService();
			Campanha campanha = campanhaService.readById(id);
			campanha.setStatus(Campanha.StatusCampanha.STATUS_ATIVA);
			campanhaService.update(campanha);
			mv = new ModelAndView("redirect:/service/listar/listarCampanhaHemocentro");
			return mv;
		} else {
			mv = new ModelAndView("redirect:/menu/login");
			return mv;
		}
	}
}
