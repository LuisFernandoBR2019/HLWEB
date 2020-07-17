package com.ptc.helplife.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import com.ptc.helplife.Entity.Campanha;
import com.ptc.helplife.Entity.TipoSanguineo;
import com.ptc.helplife.Entity.Usuario;
import com.ptc.helplife.Entity.UsuarioComum;
import com.ptc.helplife.Entity.UsuarioHemocentro;
import com.ptc.helplife.config.ConnectionConfig;

@Repository
public class CampanhaDAO implements BaseImplementDAO<Campanha> {

	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	private Connection conn;

	// Critérios de busca
	public static final String CRITERION_USUARIO_ID_EQ = "1";
	public static final String CRITERION_STATUS_EQ = "2";
	public static final String CRITERION_TIPO_SANGUINEO_IN = "3";
	public static final String CRITERION_NOME = "4";
	public static final String CRITERION_DATAINICIO_BE = "5";
	public static final String CRITERION_DATAINICIO_LE = "6";
	public static final String CRITERION_DATAFINAL_BE = "7";
	public static final String CRITERION_DATAFINAL_LE = "8";

	@Override
	public void create(Campanha campanha) {
		try {
			String sql = "INSERT INTO campanha(descricao, nome, datainicio, datafim, status, usuario_id, hemocentro_id) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id;";
			conn = ConnectionConfig.getConnection();// abre conexão banco
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);// recebo comando que erá dado no banco
			int i = 0;
			if (campanha.getDescricao() == null) {// teste das variaveis para evitar not exception
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, campanha.getDescricao());
			}
			if (campanha.getNome() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, campanha.getNome());
			}
			if (campanha.getDataInicio() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, campanha.getDataInicio());
			}
			if (campanha.getDataFinal() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, campanha.getDataFinal());
			}
			if (campanha.getStatus() == null) {
				ps.setNull(++i, Types.INTEGER);
			} else {
				ps.setInt(++i, campanha.getStatus().getId());
			}
			if (campanha.getUsuario() == null || campanha.getUsuario().getId() == null) {

				ps.setNull(++i, Types.BIGINT);
			} else {
				ps.setLong(++i, campanha.getUsuario().getId());
			}
			if (campanha.getHemocentro() == null || campanha.getHemocentro().getId() == null) {
				ps.setNull(++i, Types.BIGINT);
			} else {
				ps.setLong(++i, campanha.getHemocentro().getId());
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				campanha.setId(rs.getLong("id"));
			}
			rs.close();
			ps.close();
			for (TipoSanguineo tipoSanguineo : campanha.getTipoSanguineoList()) {
				String sqlTipoSanguineo = "INSERT INTO tiposanguineo_campanha(tiposanguineo_id, campanha_id) VALUES (?, ?);";
				PreparedStatement psTipoSanguineo = conn.prepareStatement(sqlTipoSanguineo);
				psTipoSanguineo.setLong(1, tipoSanguineo.getId());
				psTipoSanguineo.setLong(2, campanha.getId());
				psTipoSanguineo.execute();// adiciona
				psTipoSanguineo.close();// fecha conexão
			}
			conn.commit();// usado somente onde vou fazer alteração no banco//em consultas não é usado.
			conn.close();

		} catch (SQLException e) {

			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}

	@Override
	public void update(Campanha campanha) {
		try {
			String sql = "UPDATE campanha SET descricao=?, nome=?, datainicio=?, datafim=?, status=?, usuario_id=?, hemocentro_id=? WHERE id = ?";
			conn = ConnectionConfig.getConnection();// abre conexão banco
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			int i = 0;
			if (campanha.getDescricao() == null) {// teste das variaveis para evitar not exception
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, campanha.getDescricao());
			}
			if (campanha.getNome() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, campanha.getNome());
			}
			if (campanha.getDataInicio() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, campanha.getDataInicio());
			}
			if (campanha.getDataFinal() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, campanha.getDataFinal());
			}
			if (campanha.getStatus() == null) {
				ps.setNull(++i, Types.INTEGER);
			} else {
				ps.setInt(++i, campanha.getStatus().getId());
			}
			if (campanha.getUsuario() == null || campanha.getUsuario().getId() == null) {
				ps.setNull(++i, Types.BIGINT);
			} else {
				ps.setLong(++i, campanha.getUsuario().getId());
			}
			if (campanha.getHemocentro() == null || campanha.getHemocentro().getId() == null) {
				ps.setNull(++i, Types.BIGINT);
			} else {
				ps.setLong(++i, campanha.getHemocentro().getId());
			}
			ps.setLong(++i, campanha.getId());
			ps.executeUpdate();
			ps.close();

			String sqlCampanha = "DELETE FROM tiposanguineo_campanha where campanha_id =?";// deleto todos do mesmo id.
			PreparedStatement updateCampanha = conn.prepareStatement(sqlCampanha);
			updateCampanha.setLong(1, campanha.getId());
			updateCampanha.execute();
			updateCampanha.close();
			for (TipoSanguineo tipoSanguineo : campanha.getTipoSanguineoList()) {
				String sqlTipoSanguineo = "INSERT INTO tiposanguineo_campanha(tiposanguineo_id, campanha_id) VALUES (?, ?);";
				PreparedStatement psTipoSanguineo = conn.prepareStatement(sqlTipoSanguineo);
				psTipoSanguineo.setLong(1, tipoSanguineo.getId());
				psTipoSanguineo.setLong(2, campanha.getId());
				psTipoSanguineo.execute();// adiciona
				psTipoSanguineo.close();// fecha conexão
			}
			conn.commit();// usado somente onde vou fazer alteração no banco//em consultas não é usado.
			conn.close();
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}

	@Override
	public Campanha readById(long id) {
		Campanha campanha = null;
		PreparedStatement ps;
		conn = ConnectionConfig.getConnection();// abre conexão banco
		try {
			String sql = "select C.*,\n" + "U.nome as usuario_nome,\n" + "U.endereco as usuario_endereco,\n"
					+ "U.telefone as usuario_telefone,\n" + "U.email as usuario_email,\n"
					+ "U.senha as usuario_senha,\n" + "U.estado as usuario_estado,\n" + "U.cidade as usuario_cidade,\n"
					+ "U.cep as usuario_cep,\n" + "U.status as usuario_status,\n"
					+ "U.tiposanguineo_id as usuario_tiposanguineo_id,\n" 
					+ "U.sexo as usuario_sexo,\n" + "U.datanascimento as usuario_datanascimento,\n"
					+ "U.tipo as usuario_tipo,\n" + "TP.tiposangue as usuario_tiposanguineo_tiposanguineo,\n"
					+ "H.nome as hemocentro_nome,\n" + "H.endereco as hemocentro_endereco,\n"
					+ "H.telefone as hemocentro_telefone,\n" + "H.email as hemocentro_email,\n"
					+ "H.senha as hemocentro_senha,\n" + "H.estado as hemocentro_estado,\n"
					+ "H.cidade as hemocentro_cidade,\n" + "H.cep as hemocentro_cep,\n"
					+ "ts.id as tiposanguineo_campanha_id, " + "ts.tiposangue as tiposanguineo_campanha_tiposangue\n"
					+ "from campanha C\n" + "join usuario U on U.id = C.usuario_id \n"
					+ "left join tiposanguineo TP on TP.id = U.tiposanguineo_id\n"
					+ "join usuario H on H.id = C.hemocentro_id \n"
					+ "left join tiposanguineo_campanha tc on tc.campanha_id = c.id\n"
					+ "left join tiposanguineo ts on ts.id = tc.tiposanguineo_id\n" + "where C.id = ?";

			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			List<TipoSanguineo> tipoSanguineo = null;
			while (rs.next()) {
				if (campanha == null) {
					campanha = new Campanha();
					campanha.setId(rs.getLong("id"));
					campanha.setDescricao(rs.getString("descricao"));
					campanha.setNome(rs.getString("nome"));
					campanha.setDataInicio(rs.getString("datainicio"));
					campanha.setDataFinal(rs.getString("datafim"));
					campanha.setStatus(Campanha.StatusCampanha.values()[rs.getInt("status")]);

					int tipoUsuario = rs.getInt("usuario_tipo");
					Usuario usuario = null;
					if (tipoUsuario == 0) {
						usuario = new UsuarioComum();
						// setar somente os atributos do UsuarioComum

						((UsuarioComum) usuario).setSexo(rs.getString("usuario_sexo"));
						((UsuarioComum) usuario).setDataNascimento(rs.getString("usuario_datanascimento"));

					} else {
						usuario = new UsuarioHemocentro();

					}
					// Setar o que for comum para todos os tipos de usuário
					usuario.setId(rs.getLong("usuario_id"));
					usuario.setNome(rs.getString("usuario_nome"));
					usuario.setEndereco(rs.getString("usuario_endereco"));
					usuario.setTelefone(rs.getString("usuario_telefone"));
					usuario.setEmail(rs.getString("usuario_email"));
					usuario.setSenha(rs.getString("usuario_senha"));
					usuario.setEstado(rs.getString("usuario_estado"));
					usuario.setCidade(rs.getString("usuario_cidade"));
					usuario.setCep(rs.getString("usuario_cep"));
					usuario.setStatus(Usuario.StatusUsuario.values()[rs.getInt("status")]);

					campanha.setUsuario(usuario);

					// seto todos os detalhes do hemocentro vinculado com a campanha
					UsuarioHemocentro hemocentro = new UsuarioHemocentro();

					hemocentro.setId(rs.getLong("hemocentro_id"));
					hemocentro.setNome(rs.getString("hemocentro_nome"));
					hemocentro.setEndereco(rs.getString("hemocentro_endereco"));
					hemocentro.setTelefone(rs.getString("hemocentro_telefone"));
					hemocentro.setEmail(rs.getString("hemocentro_email"));
					hemocentro.setSenha(rs.getString("hemocentro_senha"));
					hemocentro.setEstado(rs.getString("hemocentro_estado"));
					hemocentro.setCidade(rs.getString("hemocentro_cidade"));
					hemocentro.setCep(rs.getString("hemocentro_cep"));

					campanha.setHemocentro(hemocentro);
					if (tipoSanguineo == null) {
						tipoSanguineo = new ArrayList<>();
					}
				}

				TipoSanguineo tipoSanguineoCampanha = new TipoSanguineo();
				tipoSanguineoCampanha.setId(rs.getLong("tiposanguineo_campanha_id"));
				tipoSanguineoCampanha.setTipoSangue(rs.getString("tiposanguineo_campanha_tiposangue"));
				tipoSanguineo.add(tipoSanguineoCampanha);
				campanha.setTipoSanguineoList(tipoSanguineo);

			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException ex) {
			}
		}
		return campanha;
	}

	@Override
	public List<Campanha> readByCriteria(Map<String, Object> criteria) {
		// Se eu chamar metodo e não passar argumentos ele me retorna read all;
		List<Campanha> campanhaList = new ArrayList<Campanha>();
		String sql = "select C.*,\n" + "U.nome as usuario_nome,\n" + "U.endereco as usuario_endereco,\n"
				+ "U.telefone as usuario_telefone,\n" + "U.email as usuario_email,\n" + "U.senha as usuario_senha,\n"
				+ "U.estado as usuario_estado,\n" + "U.cidade as usuario_cidade,\n"
				+ "U.cep as usuario_cep,\n" + "U.status as usuario_status,\n"
				+ "U.tiposanguineo_id as usuario_tiposanguineo_id,\n" 
				+ "U.sexo as usuario_sexo,\n" + "U.datanascimento as usuario_datanascimento,\n"
				+ "U.tipo as usuario_tipo,\n" + "TP.tiposangue as usuario_tiposanguineo_tiposanguineo,\n"
				+ "H.nome as hemocentro_nome,\n" + "H.endereco as hemocentro_endereco,\n"
				+ "H.telefone as hemocentro_telefone,\n" + "H.email as hemocentro_email,\n"
				+ "H.senha as hemocentro_senha,\n" 
				+ "H.estado as hemocentro_estado,\n" + "H.cidade as hemocentro_cidade,\n" + "H.cep as hemocentro_cep,\n"
				+ "ts.id as tiposanguineo_campanha_id, ts.tiposangue as tiposanguineo_campanha_tiposangue\n"
				+ "from campanha C\n" + "join usuario U on U.id = C.usuario_id \n"
				+ "left join tiposanguineo TP on TP.id = U.tiposanguineo_id\n"
				+ "join usuario H on H.id = C.hemocentro_id \n"
				+ "left join tiposanguineo_campanha tc on tc.campanha_id = c.id\n"
				+ "left join tiposanguineo ts on ts.id = tc.tiposanguineo_id\n" + "where 1=1 ";
		Statement s;
		Long criterionUsuario_id = (Long) criteria.get(CRITERION_USUARIO_ID_EQ);
		if (criterionUsuario_id != null && criterionUsuario_id > 0) {
			sql += " AND C.usuario_id = " + criterionUsuario_id + " ";
		}
		Integer criterionStatus = (Integer) criteria.get(CRITERION_STATUS_EQ);
		if (criterionStatus != null) {
			sql += " AND C.status = " + criterionStatus + " ";
		}
		String criterionTipoSanguineo_in = (String) criteria.get(CRITERION_TIPO_SANGUINEO_IN);
		if (criterionTipoSanguineo_in != null && !criterionTipoSanguineo_in.isEmpty()) {
			sql += " AND U.tiposanguineo_id  = '" + criterionTipoSanguineo_in + "' ";
		}
		String criterionName = (String) criteria.get(CRITERION_NOME);
		if (criterionName != null && !criterionName.isEmpty()) {
			sql += " AND C.nome = '" + criterionName + "' ";
		}
		// _BE maior ou igual
		String criterionDataInicio_BE = (String) criteria.get(CRITERION_DATAINICIO_BE);
		if (criterionDataInicio_BE != null && !criterionDataInicio_BE.isEmpty()) {
			sql += " AND C.dataInicio = '" + criterionDataInicio_BE + "' ";
		}
		// _LE menor ou igual
		String criterionDataInicio_LE = (String) criteria.get(CRITERION_DATAINICIO_LE);
		if (criterionDataInicio_LE != null && !criterionDataInicio_LE.isEmpty()) {
			sql += " AND C.dataInicio = '" + criterionDataInicio_LE + "' ";
		}
		String criterionDataFinal_BE = (String) criteria.get(CRITERION_DATAFINAL_BE);
		if (criterionDataFinal_BE != null && !criterionDataFinal_BE.isEmpty()) {
			sql += " AND C.dataFinal = '" + criterionDataFinal_BE + "' ";
		}
		String criterionDataFinal_LE = (String) criteria.get(CRITERION_DATAFINAL_LE);
		if (criterionDataFinal_LE != null && !criterionDataFinal_LE.isEmpty()) {
			sql += " AND C.dataFinal = '" + criterionDataFinal_LE + "' ";
		}

		sql += " order by C.id asc";
		try {
			conn = ConnectionConfig.getConnection();
			conn.setAutoCommit(false);
			s = conn.createStatement();

			ResultSet rs = s.executeQuery(sql);
			Campanha campanha = null;
			Long campanhaId = 0L;
			List<TipoSanguineo> tipoSanguineo = null;
			while (rs.next()) {
				
				Long aux = rs.getLong("id");
				if (!Objects.equals(campanhaId, aux)) {
					tipoSanguineo = null;
					campanhaId = aux;
					campanha = new Campanha();
					campanha.setId(aux);
					campanha.setDescricao(rs.getString("descricao"));
					campanha.setNome(rs.getString("nome"));
					campanha.setDataInicio(rs.getString("datainicio"));
					campanha.setDataFinal(rs.getString("datafim"));
					campanha.setStatus(Campanha.StatusCampanha.values()[rs.getInt("status")]);

					int tipoUsuario = rs.getInt("usuario_tipo");
					Usuario usuario;
					if (tipoUsuario == 0) {
						usuario = new UsuarioComum();
						// setar somente os atributos do UsuarioComum
						((UsuarioComum) usuario).setSexo(rs.getString("usuario_sexo"));
						((UsuarioComum) usuario).setDataNascimento(rs.getString("usuario_datanascimento"));

					} else {
						usuario = new UsuarioHemocentro();
						// setar somente os atributos do UsuarioHemocentro(usuario)

					}
					// Setar o que for comum para todos os tipos de usuário
					usuario.setId(rs.getLong("usuario_id"));
					usuario.setNome(rs.getString("usuario_nome"));
					usuario.setEndereco(rs.getString("usuario_endereco"));
					usuario.setTelefone(rs.getString("usuario_telefone"));
					usuario.setEmail(rs.getString("usuario_email"));
					usuario.setSenha(rs.getString("usuario_senha"));
					usuario.setEstado(rs.getString("usuario_estado"));
					usuario.setCidade(rs.getString("usuario_cidade"));
					usuario.setCep(rs.getString("usuario_cep"));
					usuario.setStatus(Usuario.StatusUsuario.values()[rs.getInt("status")]);

					campanha.setUsuario(usuario);

					// seto todos os detalhes do hemocentro vinculado com a campanha
					UsuarioHemocentro hemocentro = new UsuarioHemocentro();

					hemocentro.setId(rs.getLong("hemocentro_id"));
					hemocentro.setNome(rs.getString("hemocentro_nome"));
					hemocentro.setEndereco(rs.getString("hemocentro_endereco"));
					hemocentro.setTelefone(rs.getString("hemocentro_telefone"));
					hemocentro.setEmail(rs.getString("hemocentro_email"));
					hemocentro.setSenha(rs.getString("hemocentro_senha"));
					hemocentro.setEstado(rs.getString("hemocentro_estado"));
					hemocentro.setCidade(rs.getString("hemocentro_cidade"));
					hemocentro.setCep(rs.getString("hemocentro_cep"));

					campanha.setHemocentro(hemocentro);

					campanhaList.add(campanha);
					if (tipoSanguineo == null) {
						tipoSanguineo = new ArrayList<>();
					}
				}
				TipoSanguineo tipoSanguineoCampanha = new TipoSanguineo();
				tipoSanguineoCampanha.setId(rs.getLong("tiposanguineo_campanha_id"));
				tipoSanguineoCampanha.setTipoSangue(rs.getString("tiposanguineo_campanha_tiposangue"));	
				tipoSanguineo.add(tipoSanguineoCampanha);
				campanha.setTipoSanguineoList(tipoSanguineo);

			}
			rs.close();
			s.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException ex) {
			}
		}
		return campanhaList;
	}

	@Override
	public void delete(Campanha campanha) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

}
