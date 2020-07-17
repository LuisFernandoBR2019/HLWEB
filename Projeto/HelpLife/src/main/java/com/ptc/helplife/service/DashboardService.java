package com.ptc.helplife.service;

import java.sql.SQLException;

import com.ptc.helplife.DAO.DashboardSangueDAO;

public class DashboardService {

	public Integer graficoDashboardCampanha() throws SQLException{
		DashboardSangueDAO dao = new DashboardSangueDAO();
		return dao.contadorCampanha();
		
	}
	
	public Integer graficoDashboardSolicitacao() throws SQLException{
		DashboardSangueDAO dao = new DashboardSangueDAO();
		return dao.contadorSolicitacao();
		
	}
	
}
