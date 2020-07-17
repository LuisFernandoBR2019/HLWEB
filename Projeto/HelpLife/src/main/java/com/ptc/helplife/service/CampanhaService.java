package com.ptc.helplife.service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ptc.helplife.DAO.CampanhaDAO;
import com.ptc.helplife.Entity.Campanha;
import com.ptc.helplife.Entity.TipoSanguineo;

public class CampanhaService {

    public List<Campanha> readByCriteria(Map<String, Object> criteria) {
        CampanhaDAO dao = new CampanhaDAO();
        return dao.readByCriteria(criteria);
    }

    public Campanha readById(Long id) {
        CampanhaDAO dao = new CampanhaDAO();
        return dao.readById(id);
    }

    public void create(Campanha campanha) {
        CampanhaDAO dao = new CampanhaDAO();
        dao.create(campanha);
    }

    public void update(Campanha campanha) {
        CampanhaDAO dao = new CampanhaDAO();
        dao.update(campanha);
    }

    public Campanha merge(Map<String, String> data) {
        return null;
    }

    public void delete(Long id) {
       
    }

    public Map<String, String> validate(Campanha campanha) {
        Map<String, String> errors = new LinkedHashMap<>();

        String nome = campanha.getNome();//compara se nome é nulo ou vazio
        if (nome == null || nome.isEmpty()) {
            errors.put("nome", "Campo obrigatorio.");
        }
//        String descricao = campanha.getDescricao();//valida descricao;
//        if (descricao == null || descricao.isEmpty() && descricao.length() < 20) {
//            errors.put("descricao", "Não pode ser vazia e Tamanho minimo de 20 caracteres.");
//        }
        String dataInicio = campanha.getDataInicio();//valida dataInicio;
        if (dataInicio == null) {
            errors.put("dataInicio", "Não pode ser vazia e Tamanho minimo de 10 caracteres.(xx/xx/xxxxx)");
        }
        String dataFim = campanha.getDataFinal();//valida dataFim;
        if (dataFim == null) {
            errors.put("dataFim", "Não pode ser vazia e Tamanho minimo de 10 caracteres.(xx/xx/xxxxx)");
        }
        return errors;
    }

    public List<Campanha> getCampanhasAtivas() {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put(CampanhaDAO.CRITERION_STATUS_EQ, Campanha.StatusCampanha.STATUS_ATIVA.getId());

        CampanhaDAO dao = new CampanhaDAO();
        List<Campanha> campanhaList = dao.readByCriteria(criteria);

        return campanhaList;
    }

    public List<Campanha> getCampanhasAtivasPorTipoSanguineo(TipoSanguineo tipoSanguineo) {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put(CampanhaDAO.CRITERION_STATUS_EQ, Campanha.StatusCampanha.STATUS_ATIVA.getId());
        criteria.put(CampanhaDAO.CRITERION_TIPO_SANGUINEO_IN, tipoSanguineo.getId());

        CampanhaDAO dao = new CampanhaDAO();
        List<Campanha> campanhaList = dao.readByCriteria(criteria);

        return campanhaList;
    }
}
