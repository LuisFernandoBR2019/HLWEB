package com.ptc.helplife.Entity;

public class AlterarSenha {

	private String email;
	private String senha;
	private String re_senha;
	private String codigo;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getRe_senha() {
		return re_senha;
	}
	public void setRe_senha(String re_senha) {
		this.re_senha = re_senha;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	@Override
	public String toString() {
		return "AlterarSenha [email=" + email + ", senha=" + senha + ", re_senha=" + re_senha + ", codigo=" + codigo
				+ "]";
	}
	
	
}
