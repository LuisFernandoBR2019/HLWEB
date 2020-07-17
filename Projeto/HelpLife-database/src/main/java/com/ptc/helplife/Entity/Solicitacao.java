package com.ptc.helplife.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Solicitacao {

	public enum StatusSolicitacao {
        STATUS_ATIVA(0),
        STATUS_INATIVA(1);

        private final Integer id;

        private StatusSolicitacao(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return this.id;
        }

    };
    private Long id;
    private String dataHora;
    private String descricao;
    private StatusSolicitacao status;
    private List<TipoSanguineo> tipoSanguineoList;
    private UsuarioComum usuarioComum;
    private UsuarioHemocentro hemocentro;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }

    public List<TipoSanguineo> getTipoSanguineoList() {
        return tipoSanguineoList;
    }

    public void setTipoSanguineoList(List<TipoSanguineo> tipoSanguineoList) {
        this.tipoSanguineoList = tipoSanguineoList;
    }

    public UsuarioComum getUsuarioComum() {
        return usuarioComum;
    }

    public void setUsuarioComum(UsuarioComum usuarioComum) {
        this.usuarioComum = usuarioComum;
    }

    public UsuarioHemocentro getHemocentro() {
        return hemocentro;
    }

    public void setHemocentro(UsuarioHemocentro hemocentro) {
        this.hemocentro = hemocentro;
    }

    

    @Override
    public String toString() {
        return "Solicitacao{" + "id=" + id + ", dataHora=" + dataHora + ", descricao=" + descricao + ", status=" + status + ", tipoSanguineoList=" + tipoSanguineoList + ", usuarioComum=" + usuarioComum + ", hemocentro=" + hemocentro + + '}';
    }
}
