package com.ptc.helplife.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ptc.helplife.DAO.UsuarioDAO;
import com.ptc.helplife.Entity.Usuario;

public class UsuarioService {

	public List<Usuario> readByCriteria(Map<String, Object> criteria) {
		UsuarioDAO dao = new UsuarioDAO();
		return dao.readByCriteria(criteria);
	}

	public Usuario readById(Long id) {
		UsuarioDAO dao = new UsuarioDAO();
		return dao.readById(id);
	}

	public void create(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO();
		dao.create(usuario);
	}

	public void update(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO();
		dao.update(usuario);
	}

	public Usuario merge(Map<String, String> data) {
		return null;
	}

	public void delete(Long id) {

	}

	// valida usuario
	public Map<String, String> validate(Usuario usuario) {
		Map<String, String> errors = new LinkedHashMap<>();

		String nome = usuario.getNome();// compara se nome é nulo ou vazio
		if (nome == null || nome.isEmpty()) {
			errors.put("nome", "Campo obrigatorio.");
		}

		String telefone = usuario.getTelefone();// valida telefone;
		if (telefone != null && telefone.length() < 8) {
			errors.put("telefone", "Tamanho minimo de 08 caracteres.");
		}
		String email = usuario.getEmail();// valida telefone;
		if (email != null && email.length() < 6) {
			errors.put("email", "Tamanho minimo de 10 caracteres.");
		}
		String senha = usuario.getSenha();// valida telefone;
		if (senha != null && senha.length() < 6) {
			errors.put("senha", "Tamanho minimo de 06 caracteres.");
		}

		return errors;
	}
	// Valida Android \t

	public Usuario validaDadosAndroid(Usuario usuario) {

		if (usuario.getEmail() != null) {
			if (usuario.getEmail().contains("\t")) {
				int contadorDados = usuario.getEmail().length();
				;
				String dadoMobile;
				dadoMobile = usuario.getEmail().substring(0, (contadorDados - 1));
				usuario.setEmail(dadoMobile);
			}
		}

		if (usuario.getEndereco() != null) {
			if (usuario.getEndereco().contains("\t")) {
				int contadorDados = usuario.getEndereco().length();
				;
				String dadoMobile;
				dadoMobile = usuario.getEndereco().substring(0, (contadorDados - 1));
				usuario.setEndereco(dadoMobile);
			}
		}

		if (usuario.getCep() != null) {
			if (usuario.getCep().contains("\t")) {
				int contadorDados = usuario.getCep().length();
				;
				String dadoMobile;
				dadoMobile = usuario.getCep().substring(0, (contadorDados - 1));
				usuario.setCep(dadoMobile);
			}

		}

		if (usuario.getCidade() != null) {
			if (usuario.getCidade().contains("\t")) {
				int contadorDados = usuario.getCidade().length();
				;
				String dadoMobile;
				dadoMobile = usuario.getCidade().substring(0, (contadorDados - 1));
				usuario.setCidade(dadoMobile);
			}
		}

		if (usuario.getEstado() != null) {
			if (usuario.getEstado().contains("\t")) {
				int contadorDados = usuario.getEstado().length();
				;
				String dadoMobile;
				dadoMobile = usuario.getEstado().substring(0, (contadorDados - 1));
				usuario.setEstado(dadoMobile);
			}
		}

		if (usuario.getNome() != null) {
			if (usuario.getNome().contains("\t")) {
				int contadorDados = usuario.getNome().length();
				;
				String dadoMobile;
				dadoMobile = usuario.getNome().substring(0, (contadorDados - 1));
				usuario.setNome(dadoMobile);
			}
		}

		if (usuario.getTelefone() != null) {
			if (usuario.getTelefone().contains("\t")) {
				int contadorDados = usuario.getTelefone().length();
				;
				String dadoMobile;
				dadoMobile = usuario.getTelefone().substring(0, (contadorDados - 1));
				usuario.setTelefone(dadoMobile);
			}
		}

		if (usuario.getSenha() != null) {
			if (usuario.getSenha().contains("\t")) {
				int contadorDados = usuario.getSenha().length();
				;
				String dadoMobile;
				dadoMobile = usuario.getSenha().substring(0, (contadorDados - 1));
				usuario.setSenha(dadoMobile);
			}
		}

		return usuario;
	}

	// Logar
	public Usuario login(String email, String senha) {
		Map<String, Object> criteria = new HashMap<>();
		criteria.put(UsuarioDAO.CRITERION_EMAIL, email);
		String md5DeRamos = "uforasteiro";
		senha += md5DeRamos;
		senha = (Criptografar.criptografar(senha));
		criteria.put(UsuarioDAO.CRITERION_SENHA, senha);

		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> usuarioList = dao.readByCriteria(criteria);

		if (usuarioList.size() == 1) {
			return usuarioList.get(0);
		} else {
			return null;
		}
	}
	// Recuparer Senha

	public void recuperarSenha(String email) {
		// TODO: implementar método
	}
}
