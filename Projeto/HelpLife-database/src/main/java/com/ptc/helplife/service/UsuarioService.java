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
	
	
	    //valida usuario
	    public Map<String, String> validate(Usuario usuario) {
	        Map<String, String> errors = new LinkedHashMap<>();

	        String nome = usuario.getNome();//compara se nome é nulo ou vazio
	        if (nome == null || nome.isEmpty()) {
	            errors.put("nome", "Campo obrigatorio.");
	        }

	        String telefone = usuario.getTelefone();//valida telefone;
	        if (telefone != null && telefone.length() < 11) {
	            errors.put("telefone", "Tamanho minimo de 11 caracteres.");
	        }
	        String email = usuario.getEmail();//valida telefone;
	        if (email != null && email.length() < 20) {
	            errors.put("email", "Tamanho minimo de 20 caracteres.");
	        }
	        String senha = usuario.getSenha();//valida telefone;
	        if (senha != null && senha.length() < 8) {
	            errors.put("senha", "Tamanho minimo de 08 caracteres.");
	        }

	        return errors;
	    }

	//Logar
	    public Usuario login(String email, String senha) {
	        Map<String, Object> criteria = new HashMap<>();
	        criteria.put(UsuarioDAO.CRITERION_EMAIL, email);
	        criteria.put(UsuarioDAO.CRITERION_SENHA, senha);

	        UsuarioDAO dao = new UsuarioDAO();
	        List<Usuario> usuarioList = dao.readByCriteria(criteria);

	        if (usuarioList.size() == 1) {
	            return usuarioList.get(0);
	        } else {
	            return null;
	        }
	    }
	//Recuparer Senha

	    public void recuperarSenha(String email) {
	        //TODO: implementar método
	    }
}
