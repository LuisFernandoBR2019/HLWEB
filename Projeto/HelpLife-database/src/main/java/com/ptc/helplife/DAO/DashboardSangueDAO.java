package com.ptc.helplife.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ptc.helplife.config.ConnectionConfig;

@Repository
public class DashboardSangueDAO implements BaseImplementDAO<Object> {
	private Connection conn;

	@Override
	public void create(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object readById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> readByCriteria(Map<String, Object> criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub

	}

	public Integer contadorCampanha() throws SQLException {
		conn = ConnectionConfig.getConnection();// abre conexão banco
		Integer contador = 0;
		String sql = "select count(*) from campanha where status = 0 ;";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			contador = rs.getInt("count");
		}
		rs.close();
		ps.close();
		conn.close();
		return contador;
	}

	public Integer contadorSolicitacao() throws SQLException {
		conn = ConnectionConfig.getConnection();// abre conexão banco
		Integer contador = 0;
		String sql = "select count(*) from solicitacao where status = 0;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			contador = rs.getInt("count");
		}
		rs.close();
		ps.close();
		conn.close();
		return contador;
	}
	
	public Map<String,Integer> mapaSangue () throws SQLException{
		Map<String,Integer> mapaSangue = new HashMap<>();
		conn = ConnectionConfig.getConnection();// abre conexão banco
		String sql = "select TP.tiposangue,count(*)  as quantidade from usuario as U\r\n" + 
				"inner join tiposanguineo as TP on TP.id = U.tiposanguineo_id\r\n" + 
				"group by TP.tiposangue order by tiposangue;";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			mapaSangue.put(rs.getString("tiposangue"), rs.getInt("quantidade"));
		}
		rs.close();
		ps.close();
		conn.close();
		return mapaSangue;
	}

}
