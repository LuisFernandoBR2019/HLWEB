package com.ptc.helplife.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ptc.helplife.Entity.TipoSanguineo;
import com.ptc.helplife.config.ConnectionConfig;

public class TipoSanguineoDAO implements BaseImplementDAO<TipoSanguineo> {

	private Connection conn;

	public TipoSanguineo readById(long id) {
		TipoSanguineo tipoSanguineo = null;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("SELECT * FROM tiposanguineo WHERE id = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tipoSanguineo = new TipoSanguineo();
				tipoSanguineo.setId(rs.getLong("id"));
				tipoSanguineo.setTipoSangue(rs.getString("tiposangue"));
			}
			rs.close();
			ps.close();
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException ex) {
			}
		}
		return tipoSanguineo;
	}

	public List<TipoSanguineo> readAll() {
		List<TipoSanguineo> tipoSanguineoList = new ArrayList<TipoSanguineo>();
		PreparedStatement ps;
		try {
			conn = ConnectionConfig.getConnection();// abre conexão banco
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("SELECT * from tiposanguineo");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TipoSanguineo tipoSanguineo = new TipoSanguineo();
				tipoSanguineo.setId(rs.getLong("id"));
				tipoSanguineo.setTipoSangue(rs.getString("tiposangue"));

				// adicionando o objeto à lista
				tipoSanguineoList.add(tipoSanguineo);
			}
			rs.close();
			ps.close();
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException ex) {
			}
		}
		return tipoSanguineoList;
	}

	@Override
	public void create(TipoSanguineo obj) {
		
	}

	@Override
	public List<TipoSanguineo> readByCriteria(Map<String, Object> criteria) {
		return null;
	}

	@Override
	public void update(TipoSanguineo obj) {
		
	}

	@Override
	public void delete(TipoSanguineo obj) {
		
	}
	
}
