package com.ptc.helplife.controllerDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ptc.helplife.DAO.UsuarioDAO;
import com.ptc.helplife.Entity.AlterarSenha;
import com.ptc.helplife.Entity.Usuario;
import com.ptc.helplife.Entity.UsuarioComum;
import com.ptc.helplife.Entity.UsuarioHemocentro;
import com.ptc.helplife.service.Criptografar;
import com.ptc.helplife.service.UsuarioService;

import locator.ServiceLocatorMail;

@RestController // indica classe controle;
@RequestMapping("/api/v1/helplife")
@CrossOrigin(origins = "*")
public class UsuarioControllerDAO {
	public static Usuario usuarioRecuperaSenha;

	private UsuarioService service = new UsuarioService();

	@GetMapping("/usuario")
	public List<Usuario> get() {
		Map<String, Object> criteria = new HashMap<>();
		List<Usuario> usuarioList = service.readByCriteria(criteria);
		System.out.println(usuarioList);
		return usuarioList;
	}

	@GetMapping("/usuario/{id}")
	public ResponseEntity getById(@PathVariable("id") Long id) {
		Usuario usuario = service.readById(id);
		HttpHeaders httpHeaders = new HttpHeaders();
		if (usuario != null) {
			return new ResponseEntity<>(usuario, httpHeaders, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(usuario, httpHeaders, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/hemocentro")
	public ResponseEntity post(@RequestBody UsuarioHemocentro usuario) {
		Map<String, String> errors = service.validate(usuario);
		HttpHeaders httpHeaders = new HttpHeaders();
		if (errors.isEmpty()) {
			for (int i = 0; i < 4; i++) {

				usuario = (UsuarioHemocentro) service.validaDadosAndroid(usuario);
			}
			String md5DeRamos = "uforasteiro";
			String senha = usuario.getSenha();
			usuario.setSenha(senha+md5DeRamos);
			usuario.setSenha(Criptografar.criptografar(usuario.getSenha()));
			service.create(usuario);
			httpHeaders.add("Location", "/usuario/" + usuario.getId());
			return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(errors, httpHeaders, HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PostMapping("/usuariocomum")
	public ResponseEntity post(@RequestBody UsuarioComum usuario) {
		Map<String, String> errors = service.validate(usuario);
		String emailMobile = null;
		int contaEmail = usuario.getEmail().length();
		HttpHeaders httpHeaders = new HttpHeaders();
		if (errors.isEmpty()) {
			for (int i = 0; i < 4; i++) {

				usuario = (UsuarioComum) service.validaDadosAndroid(usuario);

			}
			String md5DeRamos = "uforasteiro";
			String senha = usuario.getSenha();
			usuario.setSenha(senha+md5DeRamos);
			usuario.setSenha(Criptografar.criptografar(usuario.getSenha()));
			service.create(usuario);
			httpHeaders.add("Location", "/usuario/" + usuario.getId());
			return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(errors, httpHeaders, HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/usuariocomum/{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody UsuarioComum usuario) {
		usuario.setId(id);
		Map<String, String> errors = service.validate(usuario);
		HttpHeaders httpHeaders = new HttpHeaders();
		if (errors.isEmpty()) {
			for (int i = 0; i < 4; i++) {

				usuario = (UsuarioComum) service.validaDadosAndroid(usuario);

			}
			service.update(usuario);
			return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(errors, httpHeaders, HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/hemocentro/{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody UsuarioHemocentro usuario) {
		usuario.setId(id);
		Map<String, String> errors = service.validate(usuario);
		HttpHeaders httpHeaders = new HttpHeaders();
		if (errors.isEmpty()) {
			for (int i = 0; i < 4; i++) {

				usuario = (UsuarioHemocentro) service.validaDadosAndroid(usuario);

			}
			service.update(usuario);
			return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(errors, httpHeaders, HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@DeleteMapping("/usuario/{id}")
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

	@PostMapping("/login")
	public Usuario efetuarLogin(@RequestBody Usuario usuario) {
		String emailMobile = null;
		int contaEmail = usuario.getEmail().length();
		String senhaMobile = null;
		int contaSenha = usuario.getSenha().length();
		if (usuario.getSenha() == null || usuario.getSenha().isEmpty() || usuario.getEmail() == null
				|| usuario.getEmail().isEmpty()) {
			return new Usuario();
		} else {

			for (int i = 0; i < 4; i++) {
				if (usuario.getEmail().contains("\t")) {

					emailMobile = usuario.getEmail().substring(0, (contaEmail - 1));
					usuario.setEmail(emailMobile);
				}
				if (usuario.getSenha().contains("\t")) {
					senhaMobile = usuario.getSenha().substring(0, (contaSenha - 1));
					usuario.setSenha(senhaMobile);
				}
			}
		}

		Map<String, Object> criteria = new HashMap<>();
		String md5DeRamos = "uforasteiro";
		String senha = usuario.getSenha();
		usuario.setSenha(senha+md5DeRamos);
		usuario.setSenha(Criptografar.criptografar(usuario.getSenha()));
		criteria.put(UsuarioDAO.CRITERION_EMAIL, usuario.getEmail());
		criteria.put(UsuarioDAO.CRITERION_SENHA, usuario.getSenha());

		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> usuarioList = dao.readByCriteria(criteria);

		if (usuarioList.isEmpty() || usuarioList == null) {
			return new Usuario();
		}
		usuario = usuarioList.get(0);

		return usuario;
	}

	@PostMapping("/recuperarSenha")
	public String recuperarSenha(@RequestBody Usuario usuario) {
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
				String assunto = "Recuperação de Senha";
				String mensagem = "Recuperação de senha!\n" + "\n" + "Prezado(a) " + usuarioEncontrado.getNome() + "\n"
						+ "Informamos abaixo a sua senha de acesso em nosso sistema\n" + "\n" + "Senha: "
						+ usuarioEncontrado.getSenha() + "\n" + "\n" + "Não é necessário responder esta mensagem.";
				try {
					ServiceLocatorMail.getMailService().prepararEnvioMail(assunto, email, mensagem);
				} catch (MessagingException ex) {
					Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
			return "sucesso";
		} else {

			return "falhou";

		}
	
	}
	
	@PostMapping("/alterarSenha")
	public String alterarSenha(@RequestBody AlterarSenha formulario) {
	
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
		

		//Valida se o usuário existe
		if (user.isEmpty()) {

			errors.put("email", "Email não cadastro em nosso banco de dados!");

		}
		
		String codigo = formulario.getCodigo();
		if (codigo == null || codigo.isEmpty() && errors.isEmpty()) {
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
				
				return "Ok";
			} else {
				return "Ok";
			}
		} else {
			return "Falhou";
		}

	}

}
