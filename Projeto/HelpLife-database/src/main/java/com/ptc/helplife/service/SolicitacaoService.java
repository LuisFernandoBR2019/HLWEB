package com.ptc.helplife.service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ptc.helplife.DAO.SolicitacaoDAO;
import com.ptc.helplife.Entity.Solicitacao;
import com.ptc.helplife.Entity.TipoSanguineo;

public class SolicitacaoService {

    public List<Solicitacao> readByCriteria(Map<String, Object> criteria) {
        SolicitacaoDAO dao = new SolicitacaoDAO();
        return dao.readByCriteria(criteria);
    }

    public Solicitacao readById(Long id) {
        SolicitacaoDAO dao = new SolicitacaoDAO();
        return dao.readById(id);
    }

    public void create(Solicitacao solicitacao) {
        SolicitacaoDAO dao = new SolicitacaoDAO();
        dao.create(solicitacao);
    }

    public void update(Solicitacao solicitacao) {
        SolicitacaoDAO dao = new SolicitacaoDAO();
        dao.update(solicitacao);
    }

    public Solicitacao merge(Map<String, String> data) {
        return null;
    }

    public void delete(Long id) {
    }

    public Map<String, String> validate(Solicitacao solicitacao) {
        Map<String, String> errors = new LinkedHashMap<>();

        String dataHora = solicitacao.getDataHora();
        if (dataHora == null) {
            errors.put("dataHora", "Campo obrigatorio.");
        }
//        String descricao = solicitacao.getDescricao();
//        if (descricao == null || descricao.isEmpty() && descricao.length() < 20) {
//            errors.put("descricao", "Não pode ser vazia e Tamanho minimo de 20 caracteres.");
//        }
        return errors;
    }

    public List<Solicitacao> getSolicitacoesAtivas() {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put(SolicitacaoDAO.CRITERION_STATUS_EQ, Solicitacao.StatusSolicitacao.STATUS_ATIVA.getId());

        SolicitacaoDAO dao = new SolicitacaoDAO();
        List<Solicitacao> solicitacaoList = dao.readByCriteria(criteria);

        return solicitacaoList;
    }

    public List<Solicitacao> getSolicitacoesAtivasPorTipoSanguineo(TipoSanguineo tipoSanguineo) {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put(SolicitacaoDAO.CRITERION_STATUS_EQ, Solicitacao.StatusSolicitacao.STATUS_ATIVA.getId());
        criteria.put(SolicitacaoDAO.CRITERION_TIPO_SANGUINEO_IN, tipoSanguineo.getId());

        SolicitacaoDAO dao = new SolicitacaoDAO();
        List<Solicitacao> solicitacaoList = dao.readByCriteria(criteria);

        return solicitacaoList;
    }
}
