package com.ptc.helplife.Entity;

public class Usuario {

	public enum StatusUsuario {
        STATUS_ATIVA(0),
        STATUS_INATIVA(1);

        private final Integer id;

        private StatusUsuario(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return this.id;
        }

    };

    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String senha;
    private String estado;
    private String cidade;
    private String cep;
    private StatusUsuario status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + ", email=" + email + ", senha=" + senha + ", estado=" + estado + ", cidade=" + cidade + ", cep=" + cep + ", status=" + status + '}';
    }
}
