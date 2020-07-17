package com.ptc.helplife.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ptc.helplife.DAO.DashboardSangueDAO;

public class DashboardService {

	DashboardSangueDAO dao = new DashboardSangueDAO();
	public Integer graficoDashboardCampanha() throws SQLException{
		return dao.contadorCampanha();	
	}
	
	public Integer graficoDashboardSolicitacao() throws SQLException{
		return dao.contadorSolicitacao();	
	}
	
	public Map<String,Integer> mapaSangue() throws SQLException{
		return dao.mapaSangue();
	}
}
