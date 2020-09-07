package com.ptc.helplife.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ptc.helplife.DAO.BaseImplementDAO;
import com.ptc.helplife.DAO.CampanhaDAO;
import com.ptc.helplife.DAO.TipoSanguineoDAO;
import com.ptc.helplife.DAO.UsuarioDAO;
import com.ptc.helplife.Entity.AlterarSenha;
import com.ptc.helplife.Entity.Campanha;
import com.ptc.helplife.Entity.TipoSanguineo;
import com.ptc.helplife.Entity.Usuario;
import com.ptc.helplife.Entity.UsuarioComum;
import com.ptc.helplife.Entity.UsuarioHemocentro;
import com.ptc.helplife.service.Criptografar;

import locator.ServiceLocatorMail;

@Controller
public class UsuarioController {

	public static Usuario usuarioRecuperaSenha;

	@GetMapping("service/cadastro/cadastroUsuario")
	public String goCadastroUsuario(Model model) {
		String msg = "HELP LIFE - SISTEMA  GERENCIADOR DE DOACAO SANGUINEA";
		model.addAttribute("msg", msg);

		TipoSanguineoDAO tipoSanguineoDao = new TipoSanguineoDAO();
		List<TipoSanguineo> tipoSanguineoList = tipoSanguineoDao.readAll();
		model.addAttribute("tipoSanguineoList", tipoSanguineoList);
		return "service/cadastro/cadastroUsuario";
	}

	@PostMapping("service/cadastro/cadastroUsuario")
	public ModelAndView CadastroUsuario(UsuarioComum usuario) {
		ModelAndView mv = null;

		// validação
		Map<String, String> errors = new LinkedHashMap<>();
		String nome = usuario.getNome();// compara se nome é nulo ou vazio
		if (nome == null || nome.isEmpty()) {
			errors.put("nome", "Campo obrigatório.");
		}

		String endereco = usuario.getEndereco();
		if (endereco == null || endereco.isEmpty()) {
			errors.put("endereco", "Campo obrigatório.");
		}
		// pode ser que remova
		String telefone = usuario.getTelefone();// valida telefone;
		if (telefone != null && telefone.length() < 9) {
			errors.put("telefone", "Tamanho mínimo de 9 caracteres.");
		}
		// pode ser que remova
		String email = usuario.getEmail();
		if (email != null && email.length() < 9) {
			errors.put("email", "Digite um email válido.");
		}

		UsuarioDAO dao = new UsuarioDAO();
		Map<String, Object> criteria = new HashMap<>();

		criteria.put(UsuarioDAO.CRITERION_EMAIL, email);
		List<Usuario> user = new ArrayList<>();
		user = dao.readByCriteria(criteria);
		// Parei aqui !!!!!!!!!!!!!!!!
		if (!user.isEmpty() && user != null) {
			if (email.equals(user.get(0).getEmail())) {
				errors.put("email", "Email já está em uso!");
			}
		}

		String senha = usuario.getSenha();
		if (senha != null && senha.length() < 6) {
			errors.put("senha", "Tamanho minimo de 06 caracteres.");
		}
		String md5DeRamos = "uforasteiro";
		senha += md5DeRamos;
		usuario.setSenha(Criptografar.criptografar(senha));

		String estado = usuario.getEstado();
		if (estado != null && estado.length() < 2) {
			errors.put("estado", "Tamanho minimo de 02 caracteres.");
		}

		String cidade = usuario.getCidade();
		if (cidade != null && cidade.length() < 4) {
			errors.put("cidade", "Tamanho minimo de 04 caracteres.");
		}

		TipoSanguineo tipoSanguineo = usuario.getTipoSanguineo();
		if (tipoSanguineo == null || tipoSanguineo.getId() == 0) {
			errors.put("tipoSanguineo", "Campo obrigatório.");
		}

		String dataNascimento = usuario.getDataNascimento();
		if (dataNascimento == null) {
			errors.put("dataNascimento", "Campo Obrigatório.");
		} else {
			// validação da data formato
		}

		String tipoSexo = usuario.getSexo();

		if (tipoSexo == null || tipoSexo.equals("0")) {
			errors.put("sexo", "Campo Obrigatório.");
		}

		if (errors.isEmpty()) {

			dao.create(usuario);
			mv = new ModelAndView("redirect:/service/cadastro/feedbackUsuario");
			return mv;

		} else {
			TipoSanguineoDAO tipoSanguineoDao = new TipoSanguineoDAO();
			mv = new ModelAndView("service/cadastro/cadastroUsuario");
			List<TipoSanguineo> tipoSanguineoList = tipoSanguineoDao.readAll();
			mv.addObject("tipoSanguineoList", tipoSanguineoList);
			mv.addObject("erro", errors);
			mv.addObject("usuario", usuario);
			return mv;

		}

	}

	@GetMapping("service/cadastro/feedbackUsuario")
	public ModelAndView feedBackUsuario(Model model) throws ParseException {
		ModelAndView mv;
		mv = new ModelAndView("service/cadastro/feedbackUsuario");
		return mv;
	}

	@GetMapping("service/editar/feedbackUsuario")
	public ModelAndView feedbackUsuarioEdit(Model model, HttpSession session) throws ParseException {
		ModelAndView mv = null;
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		Boolean isHemocentro = usuarioLogado instanceof UsuarioHemocentro;
		if (usuarioLogado != null) {

			mv = new ModelAndView("service/editar/feedbackUsuario");

			mv.addObject("isHemocentro", isHemocentro);

			mv.addObject("usuarioLogado", usuarioLogado);

			return mv;
		} else {
			return mv = new ModelAndView("redirect:/menu/login");
		}

	}

	@GetMapping("service/editar/feedbackHemocentro")
	public ModelAndView feedbackHemocentroEdit(Model model, HttpSession session) throws ParseException {
		ModelAndView mv = null;
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		Boolean isHemocentro = usuarioLogado instanceof UsuarioHemocentro;
		if (usuarioLogado != null) {

			mv = new ModelAndView("service/editar/feedbackHemocentro");

			mv.addObject("isHemocentro", isHemocentro);

			mv.addObject("usuarioLogado", usuarioLogado);

			return mv;
		} else {
			return mv = new ModelAndView("redirect:/menu/login");
		}

	}

	@GetMapping("service/editar/editarUsuario")
	public String goCadastroUsuario(Model model, HttpSession session) throws ParseException {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		String msg = "HELP LIFE - SISTEMA  GERENCIADOR DE DOACAO SANGUINEA";
		UsuarioComum usuarioLog = (UsuarioComum) usuarioLogado;
		if (usuarioLog != null) {
			TipoSanguineoDAO tipoSanguineoDao = new TipoSanguineoDAO();
			List<TipoSanguineo> tipoSanguineoList = tipoSanguineoDao.readAll();
			model.addAttribute("tipoSanguineoList", tipoSanguineoList);
			// Pega os dados atualizados do colaborador
			Map<String, Object> criteria = new HashMap<>();

			criteria.put(UsuarioDAO.CRITERION_EMAIL, usuarioLogado.getEmail());
			criteria.put(UsuarioDAO.CRITERION_SENHA, usuarioLogado.getSenha());

			UsuarioDAO dao = new UsuarioDAO();
			List<Usuario> usuarioList = dao.readByCriteria(criteria);

			if (usuarioList.size() == 1) {
				Usuario usuarioLogadoAtualizado = usuarioList.get(0);
				model.addAttribute("usuarioLogado", usuarioLogadoAtualizado);
			}
			return "service/editar/editarUsuario";
		} else {
			return "redirect:/menu/login";
		}
	}

	@PostMapping("service/editar/editarUsuario")
	public ModelAndView UpdateUsuario(UsuarioComum usuario, HttpSession session) {
		ModelAndView mv = null;
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		UsuarioComum usuarioLog = (UsuarioComum) usuarioLogado;
		if (usuarioLog != null) {
			TipoSanguineoDAO tipoSanguineoDao = new TipoSanguineoDAO();
			// validação
			Map<String, String> errors = new LinkedHashMap<>();
			String nome = usuario.getNome();// compara se nome é nulo ou vazio
			if (nome == null || nome.isEmpty()) {
				errors.put("nome", "Campo obrigatório.");
			}

			String endereco = usuario.getEndereco();
			if (endereco == null || endereco.isEmpty()) {
				errors.put("endereco", "Campo obrigatório.");
			}
			// pode ser que remova
			String telefone = usuario.getTelefone();// valida telefone;
			if (telefone != null && telefone.length() < 8) {
				errors.put("telefone", "Tamanho mínimo de 8 caracteres.");
			}
			// pode ser que remova
			String email = usuario.getEmail();
			if (email != null && email.length() < 8) {
				errors.put("email", "Digite um email válido.");
			}
			UsuarioDAO dao = new UsuarioDAO();
			Map<String, Object> criteria = new HashMap<>();

			criteria.put(UsuarioDAO.CRITERION_EMAIL, email);
			List<Usuario> user = new ArrayList<>();
			user = dao.readByCriteria(criteria);
			// Parei aqui !!!!!!!!!!!!!!!!
			if (!user.isEmpty() && user != null) {
				if (email.equals(user.get(0).getEmail())) {
					if (usuarioLogado.getId() != user.get(0).getId()) {
						errors.put("email", "Email já está em uso!");
					}

				}
			}
			
			usuario.setSenha(user.get(0).getSenha());
			// Seta a mesma senha do usuário
			
			
			/*
			String senha = usuario.getSenha();
			if (senha != null && senha.length() < 6) {
				errors.put("senha", "Tamanho minimo de 06 caracteres.");
			}
			String md5DeRamos = "uforasteiro";
			senha += md5DeRamos;
			usuario.setSenha(Criptografar.criptografar(senha));
			*/
			String estado = usuario.getEstado();
			if (estado != null && estado.length() < 2) {
				errors.put("estado", "Tamanho minimo de 02 caracteres.");
			}

			String cidade = usuario.getCidade();
			if (cidade != null && cidade.length() < 4) {
				errors.put("cidade", "Tamanho minimo de 04 caracteres.");
			}

			String cep = usuario.getCep();
			if (cep != null && cep.length() < 7) {
				errors.put("cep", "Tamanho minimo de 07 caracteres.");
			}

			TipoSanguineo tipoSanguineo = usuario.getTipoSanguineo();
			if (tipoSanguineo == null || tipoSanguineo.getId() == 0) {
				errors.put("tipoSanguineo", "Campo obrigatório.");
			}

			String dataNascimento = usuario.getDataNascimento();
			if (dataNascimento == null) {
				errors.put("dataNascimento", "Campo Obrigatório.");
			} else {
				// validação da data formato
			}

			String tipoSexo = usuario.getSexo();
			if (tipoSexo == null || tipoSexo.equals("0")) {
				errors.put("sexo", "Campo Obrigatório.");
			}
			if (errors.isEmpty()) {
				Boolean isHemocentro = usuarioLogado instanceof UsuarioHemocentro;
				dao.update(usuario);
				mv = new ModelAndView("redirect:/service/editar/feedbackUsuario");
				mv.addObject("usuarioLogado", usuario);
				mv.addObject("isHemocentro", isHemocentro);
				List<TipoSanguineo> tipoSanguineoList = tipoSanguineoDao.readAll();
				mv.addObject("tipoSanguineoList", tipoSanguineoList);
				return mv;
			} else {
				mv = new ModelAndView("service/editar/editarUsuario");
				List<TipoSanguineo> tipoSanguineoList = tipoSanguineoDao.readAll();
				mv.addObject("tipoSanguineoList", tipoSanguineoList);
				mv.addObject("erro", errors);
				return mv;
			}
		} else {
			mv = new ModelAndView("redirect:/menu/login");
			return mv;
		}

	}

	@GetMapping("service/cadastro/cadastroHemocentro")
	public String goCadastroHemocentro(Model model) {
		return "service/cadastro/cadastroHemocentro";

	}

	@PostMapping("service/cadastro/cadastroHemocentro")
	public ModelAndView CadastroHemocentro(UsuarioHemocentro hemocentro) {
		ModelAndView mv = null;

		// validação
		Map<String, String> errors = new LinkedHashMap<>();
		String nome = hemocentro.getNome();// compara se nome é nulo ou vazio
		if (nome == null || nome.isEmpty()) {
			errors.put("nome", "Campo obrigatório.");
		}

		String endereco = hemocentro.getEndereco();
		if (endereco == null || endereco.isEmpty()) {
			errors.put("endereco", "Campo obrigatório.");
		}

		String telefone = hemocentro.getTelefone();// valida telefone;
		if (telefone != null && telefone.length() < 8) {
			errors.put("telefone", "Tamanho mínimo de 8 caracteres.");
		}

		String email = hemocentro.getEmail();
		if (email != null && email.length() < 8) {
			errors.put("email", "Digite um email válido.");
		}

		UsuarioDAO dao = new UsuarioDAO();
		Map<String, Object> criteria = new HashMap<>();

		criteria.put(UsuarioDAO.CRITERION_EMAIL, email);
		List<Usuario> user = new ArrayList<>();
		user = dao.readByCriteria(criteria);
		// Parei aqui !!!!!!!!!!!!!!!!
		if (!user.isEmpty() && user != null) {
			if (email.equals(user.get(0).getEmail())) {
				errors.put("email", "Email já está em uso!");
			}
		}

		String senha = hemocentro.getSenha();
		if (senha != null && senha.length() < 6) {
			errors.put("senha", "Tamanho minimo de 06 caracteres.");
		}
		String md5DeRamos = "uforasteiro";
		senha += md5DeRamos;
		hemocentro.setSenha(Criptografar.criptografar(senha));

		String estado = hemocentro.getEstado();
		if (estado != null && estado.length() < 2) {
			errors.put("estado", "Tamanho minimo de 02 caracteres.");
		}

		String cidade = hemocentro.getCidade();
		if (cidade != null && cidade.length() < 4) {
			errors.put("cidade", "Tamanho minimo de 04 caracteres.");
		}

		if (errors.isEmpty()) {
			dao.create(hemocentro);
			mv = new ModelAndView("redirect:/service/cadastro/feedbackUsuario");
			return mv;

		} else {
			mv = new ModelAndView("service/cadastro/cadastroHemocentro");
			mv.addObject("erro", errors);
			mv.addObject("hemocentro", hemocentro);
			return mv;

		}
	}

	@GetMapping("service/editar/editarHemocentro")
	public String goCadastroHemocentro(Model model, HttpSession session) {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		Map<String, Object> criteria = new HashMap<>();

		criteria.put(UsuarioDAO.CRITERION_EMAIL,usuarioLogado.getEmail());
		List<Usuario> usuarioList = dao.readByCriteria(criteria);

		if (usuarioList.size() == 1) {
			Usuario usuarioLogadoAtualizado = usuarioList.get(0);
			model.addAttribute("usuarioLogado", usuarioLogadoAtualizado);
		}
		
		if (usuarioLogado != null) {
			return "service/editar/editarHemocentro";
		} else {
			return "redirect:menu/login";
		}
	}

	@PostMapping("service/editar/editarHemocentro")
	public ModelAndView UpdateUsuario(UsuarioHemocentro hemocentro, HttpSession session) throws ParseException {
		ModelAndView mv = null;
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		if (usuarioLogado != null) {

			// validação
			Map<String, String> errors = new LinkedHashMap<>();
			String nome = hemocentro.getNome();// compara se nome é nulo ou vazio
			if (nome == null || nome.isEmpty()) {
				errors.put("nome", "Campo obrigatório.");
			}

			String endereco = hemocentro.getEndereco();
			if (endereco == null || endereco.isEmpty()) {
				errors.put("endereco", "Campo obrigatório.");
			}

			String telefone = hemocentro.getTelefone();// valida telefone;
			if (telefone != null && telefone.length() < 8) {
				errors.put("telefone", "Tamanho mínimo de 8 caracteres.");
			}
			/*
			String senha = hemocentro.getSenha();
			if (senha != null && senha.length() < 6) {
				errors.put("senha", "Tamanho minimo de 06 caracteres.");
			}
			String md5DeRamos = "uforasteiro";
			senha += md5DeRamos;
			hemocentro.setSenha(Criptografar.criptografar(senha));
			*/
			
			String estado = hemocentro.getEstado();
			if (estado != null && estado.length() < 2) {
				errors.put("estado", "Tamanho minimo de 02 caracteres.");
			}

			String cidade = hemocentro.getCidade();
			if (cidade != null && cidade.length() < 4) {
				errors.put("cidade", "Tamanho minimo de 04 caracteres.");
			}
			String cep = hemocentro.getCep();
			if (cep != null && cep.length() < 7) {
				errors.put("cep", "Tamanho minimo de 07 caracteres.");
			}

			String email = hemocentro.getEmail();
			if (email != null && email.length() < 8) {
				errors.put("email", "Digite um email válido.");
			}
			UsuarioDAO dao = new UsuarioDAO();
			Map<String, Object> criteria = new HashMap<>();

			criteria.put(UsuarioDAO.CRITERION_EMAIL, email);
			List<Usuario> user = new ArrayList<>();
			user = dao.readByCriteria(criteria);
			// Parei aqui !!!!!!!!!!!!!!!!
			if (!user.isEmpty() && user != null) {
				if (email.equals(user.get(0).getEmail())) {
					if (usuarioLogado.getId() != user.get(0).getId()) {
						errors.put("email", "Email já está em uso!");
					}

				}
			}
			//Seta a mesma senah do usuário
			hemocentro.setSenha(user.get(0).getSenha());

			if (errors.isEmpty()) {
				Boolean isHemocentro = usuarioLogado instanceof UsuarioHemocentro;
				dao.update(hemocentro);
				mv = new ModelAndView("redirect:/service/editar/feedbackHemocentro");
				mv.addObject("usuarioLogado", hemocentro);
				mv.addObject("isHemocentro", isHemocentro);
				return mv;
			} else {
				mv = new ModelAndView("service/editar/editarHemocentro");
				mv.addObject("erro", errors);
				return mv;
			}

		} else {
			return mv = new ModelAndView("redirect:/menu/login");
		}
	}

	@GetMapping("menu/login")
	public String goLogin(Model model) {
		String msg = "HELP LIFE - SISTEMA  GERENCIADOR DE DOACAO SANGUINEA";
		model.addAttribute("msg", msg);

		BaseImplementDAO campanhaDao = new CampanhaDAO();
		Map<String, Object> criteria = new HashMap<>();
		criteria.put(CampanhaDAO.CRITERION_STATUS_EQ, 0);
		List<Campanha> campanhaList = campanhaDao.readByCriteria(criteria);
		model.addAttribute("campanhaList", campanhaList != null ? campanhaList : Collections.EMPTY_LIST);
		return "menu/login";
	}

	@PostMapping("menu/login")
	public ModelAndView login(String email, String senha, HttpSession session) {
		ModelAndView mv = null;
		if (senha == null || senha.isEmpty() || email == null || email.isEmpty()) {
			// Deu errado, não achou usuário no banco, ou achou mais de um
			Map<String, String> erros = new HashMap<>();
			erros.put("preenchido", "Insira um Email/Senha!");
			mv = new ModelAndView("menu/login");
			mv.addObject("erro", erros);
		} else {

			Map<String, Object> criteria = new HashMap<>();
			String md5DeRamos = "uforasteiro";
			senha += md5DeRamos;
			senha = (Criptografar.criptografar(senha));
			System.out.println(senha);
			criteria.put(UsuarioDAO.CRITERION_EMAIL, email);
			criteria.put(UsuarioDAO.CRITERION_SENHA, senha);

			UsuarioDAO dao = new UsuarioDAO();
			List<Usuario> usuarioList = dao.readByCriteria(criteria);

			if (usuarioList.size() == 1) {
				Usuario usuarioLogado = usuarioList.get(0);
				if (usuarioLogado instanceof UsuarioComum) {
					mv = new ModelAndView("redirect:/dashboards/Usuario");
				} else {
					mv = new ModelAndView("redirect:/dashboards/Hemocentro");
				}
				session.setAttribute("usuarioLogado", usuarioLogado);
			} else {
				// Deu errado, não achou usuário no banco, ou achou mais de um
				Map<String, String> erros = new HashMap<>();
				erros.put("login", "E-mail ou senha inválidos");
				mv = new ModelAndView("menu/login");
				mv.addObject("erro", erros);
			}
		}
		return mv;
	}

	@GetMapping("menu/recuperarSenha")
	public String recuperarSenha() {
		return "service/cadastro/recuperarSenhaUsuario";
	}

	@GetMapping("menu/apresentacao")
	public String apresentacao() {
		return "menu/apresentacao";
	}

	@GetMapping("menu/campanhas")
	public String campanhas() {
		return "menu/campanhas";
	}

	@GetMapping("menu/orientacoes")
	public String orientacoes() {
		return "menu/orientacoes";
	}

	@GetMapping("menu/recuperouSenha")
	public String recuperouSenha(Model model) {

		model.addAttribute("usuario", usuarioRecuperaSenha);
		return "menu/recuperouSenha";
	}

	public Usuario usuarioRecuperaSenha(Usuario usuario) {
		return usuario;
	}
	
	@GetMapping("menu/alterarSenha")
	public String alterarSenha() {
		return "service/editar/alterarSenha";
	}
	
	@PostMapping("menu/alterarSenha")
	public ModelAndView alterarSenha(AlterarSenha formulario) {
		ModelAndView mv = null;
		UsuarioDAO dao = new UsuarioDAO();
		Map<String, Object> criteria = new HashMap<>();
		criteria.put(UsuarioDAO.CRITERION_EMAIL, formulario.getEmail());
		List<Usuario> user = new ArrayList<>();
		user = dao.readByCriteria(criteria);
		// validação
		Map<String, String> errors = new LinkedHashMap<>();
		
		String email = formulario.getEmail();
		if (email == null || email.isEmpty()) {
			errors.put("email", "Campo obrigatório.");
		}
		
		String codigo = formulario.getCodigo();
		if (codigo == null || codigo.isEmpty()) {
			errors.put("codigo", "Campo obrigatório.");
		}else if (user.get(0).getCodigo() == null || user.get(0).getCodigo().isEmpty()) {
			errors.put("codigo", "Código inválido");
		}else if (!user.get(0).getCodigo().equals(codigo)) {
			errors.put("codigo", "Código inserido não é o correto");
		}
		
		String password = formulario.getSenha();
		if (password == null || password.isEmpty()) {
			errors.put("password", "Campo obrigatório.");
		}
		
		String re_password = formulario.getRe_senha();
		if (re_password == null || re_password.isEmpty()) {
			errors.put("password", "Campo obrigatório.");
		}else if (!password.equals(re_password)) {
			errors.put("password", "Senha diferente");
		}
		
		//Valida se o usuário existe
		if (user.isEmpty()) {

			errors.put("email", "Email não cadastro em nosso banco de dados!");

		}
		
		if (errors.isEmpty()) {

			if (user.size() == 1) {
				Usuario usuarioEncontrado = new Usuario();
				
				usuarioEncontrado = user.get(0);
				String md5DeRamos = "uforasteiro";
				password += md5DeRamos;
				password = (Criptografar.criptografar(password));
				usuarioEncontrado.setSenha(password);
				usuarioEncontrado.setCodigo(null);
				dao.update(usuarioEncontrado);
				
				return mv = new ModelAndView("redirect:/menu/recuperouSenha");
			} else {
				return mv = new ModelAndView("redirect:/service/editar/alterarSenha");
			}
		} else {
			mv = new ModelAndView("service/editar/alterarSenha");
			mv.addObject("erro", errors);
			return mv;
		}

	}

	@PostMapping("menu/recuperarSenha")
	public ModelAndView recuperarSenha(Usuario usuario) {
		ModelAndView mv = null;
		UsuarioDAO dao = new UsuarioDAO();
		Map<String, Object> criteria = new HashMap<>();
		criteria.put(UsuarioDAO.CRITERION_EMAIL, usuario.getEmail());
		// validação
		Map<String, String> errors = new LinkedHashMap<>();

		String email = usuario.getEmail();
		if (email == null || email.isEmpty()) {
			errors.put("email", "Campo obrigatório.");
		}
		criteria.put(UsuarioDAO.CRITERION_EMAIL, email);
		List<Usuario> user = new ArrayList<>();
		user = dao.readByCriteria(criteria);
		
		if (user.isEmpty()) {

			errors.put("email", "Email não cadastro em nosso banco de dados!");

		}

		if (errors.isEmpty()) {

			if (user.size() == 1) {
				Usuario usuarioEncontrado = user.get(0);
				usuarioRecuperaSenha = usuarioEncontrado;
				
				//Criar Código de recuperação senha
				String codigo = "";
				codigo =usuarioEncontrado.getNome().substring(2, 4) + usuarioEncontrado.getNome().substring(0, 2) + usuarioEncontrado.getTelefone().substring(0, 2) + usuarioEncontrado.getSenha().substring(2, 4);
				codigo = codigo.toUpperCase();
				String md5DeRamos = "uforasteiro";
				codigo += md5DeRamos;
				codigo = (Criptografar.criptografar(codigo));
				usuarioEncontrado.setCodigo(codigo);
				
				dao.update(usuarioEncontrado);
				String assunto = "Recuperação de Senha";
				String mensagem = "Recuperação de senha!\n" + "\n" + "Prezado(a) " + usuarioEncontrado.getNome() + "\n"
						+ "Informamos abaixo o código para recuperação de acesso em nosso sistema\n" + "\n" + "Código: "
						+ usuarioEncontrado.getCodigo() + "\n" + "\n" + "Não é necessário responder esta mensagem.";
				try {
					ServiceLocatorMail.getMailService().prepararEnvioMail(assunto, email, mensagem);
				} catch (MessagingException ex) {
					Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
				}
				return mv = new ModelAndView("redirect:/menu/alterarSenha");
			} else {
				return mv = new ModelAndView("redirect:/service/cadastro/recuperarSenhaUsuario");
			}
		} else {
			mv = new ModelAndView("service/cadastro/recuperarSenhaUsuario");
			mv.addObject("erro", errors);
			return mv;
		}
	}

	public static void buscarDoadores(String[] tipoSanguineoIds) {

		List<Usuario> usuarioList = new ArrayList<Usuario>();
		for (String tipoSanguineo : tipoSanguineoIds) {
			Map<String, Object> criteria = new HashMap<>();
			criteria.put(UsuarioDAO.CRITERION_TIPO_SANGUINEO_IN, tipoSanguineo);
			criteria.put(UsuarioDAO.CRITERION_USUARIO_COMUM, 0);
			usuarioList.addAll(new UsuarioDAO().readByCriteria(criteria));
		}

		if (usuarioList != null && !usuarioList.isEmpty()) {
			String assunto = "Campanha de doação de sangue";
			for (int i = 0; i < usuarioList.size(); i++) {
				UsuarioComum usuario = (UsuarioComum) usuarioList.get(i);

				String mensagem = "Pedido de doação sanguínea!\n" + "\n" + "Prezado(a) " + usuario.getNome() + "\n"
						+ "Informamos que neste exato momento foi criado uma Campanha de doação sanguínea, onde foi solicitado o seu tipo sanguíneo:"
						+ usuario.getTipoSanguineo().getTipoSangue() + "\n"
						+ "Pedimos encarecidamente sua colaboração para ajudar o Sangue Bom em mais essa causa.\n"
						+ "\n" + "\n" + "\n"
						+ "Indique nossos serviços a pessoas e empresas conhecidas, vamos nos unir para fazer um mundo cada vez melhor.\n"
						+ "\n" + "sanguebomfai@gmail.com\n" + "\n" + "Atenciosamente Equipe Sangue Bom.\n" + "\n"
						+ "Não é necessário responder esta mensagem.\n";

			}
		}

	}

	public static void buscarDoadoresParaSolicitacao(String[] tipoSanguineoIds) {

		List<Usuario> usuarioList = new ArrayList<Usuario>();
		for (String tipoSanguineo : tipoSanguineoIds) {
			Map<String, Object> criteria = new HashMap<>();
			criteria.put(UsuarioDAO.CRITERION_TIPO_SANGUINEO_IN, tipoSanguineo);
			List<Usuario> usuarioTipoSanguineoList = new UsuarioDAO().readByCriteria(criteria);
			if (usuarioTipoSanguineoList != null && !usuarioTipoSanguineoList.isEmpty()) {
				usuarioList.addAll(usuarioTipoSanguineoList);
			}

		}

		if (usuarioList != null && !usuarioList.isEmpty()) {
			String assunto = "Solicitação de doação de sangue";
			for (int i = 0; i < usuarioList.size(); i++) {
				UsuarioComum usuario = (UsuarioComum) usuarioList.get(i);

				String mensagem = "Solicitação de doação sanguínea!\n" + "\n" + "Prezado(a) " + usuario.getNome() + "\n"
						+ "Informamos que neste exato momento foi criado uma Solicitação de doação sanguínea, onde foi solicitado o seu tipo sanguíneo:\n"
						+ usuario.getTipoSanguineo().getTipoSangue()
						+ "Pedimos encarecidamente sua colaboração para ajudar o Sangue Bom em mais essa causa.\n"
						+ "\n" + "\n" + "\n"
						+ "Indique nossos serviços a pessoas e empresas conhecidas, vamos nos unir para fazer um mundo cada vez melhor.\n"
						+ "\n" + "sanguebomfai@gmail.com\n" + "\n" + "Atenciosamente Equipe Sangue Bom.\n" + "\n"
						+ "Não é necessário responder esta mensagem.\n";
			}
		}

	}

}
