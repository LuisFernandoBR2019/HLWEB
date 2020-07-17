package com.ptc.helplife.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Campanha {

	public enum StatusCampanha {
        STATUS_ATIVA(0),
        STATUS_INATIVA(1);

        private final Integer id;

        private StatusCampanha(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return this.id;
        }

    };

    private Long id;
    private String descricao;
    private String nome;
    private List<TipoSanguineo> tipoSanguineoList;
    private String dataInicio;
    private String dataFinal;
    private StatusCampanha status;
    private Usuario usuario;
    private UsuarioHemocentro hemocentro;

  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

   

    public List<TipoSanguineo> getTipoSanguineoList() {
        return tipoSanguineoList;
    }

    public void setTipoSanguineoList(List<TipoSanguineo> tipoSanguineoList) {
        this.tipoSanguineoList = tipoSanguineoList;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public StatusCampanha getStatus() {
        return status;
    }

    public void setStatus(StatusCampanha status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = (Usuario) usuario;
    }

    public UsuarioHemocentro getHemocentro() {
        return hemocentro;
    }

    public void setHemocentro(UsuarioHemocentro hemocentro) {
        this.hemocentro = hemocentro;
    }

    @Override
    public String toString() {
        return "Campanha{" + "id=" + id + ", descricao=" + descricao + ", nome=" + nome + ", tipoSanguineoList=" + tipoSanguineoList + ", dataInicio=" + dataInicio + ", dataFinal=" + dataFinal + ", status=" + status + ", usuario=" + usuario + ", hemocentro=" + hemocentro + '}';
    }
}
