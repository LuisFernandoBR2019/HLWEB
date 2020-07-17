package com.ptc.helplife.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.ptc.helplife.Entity.Solicitacao;
import com.ptc.helplife.Entity.TipoSanguineo;
import com.ptc.helplife.Entity.Usuario;
import com.ptc.helplife.Entity.UsuarioComum;
import com.ptc.helplife.Entity.UsuarioHemocentro;
import com.ptc.helplife.config.ConnectionConfig;

public class SolicitacaoDAO implements BaseImplementDAO<Solicitacao> {

	private Connection conn;

	// Critérios de busca
	public static final String CRITERION_USUARIO_ID_EQ = "1";
	public static final String CRITERION_STATUS_EQ = "2";
	public static final String CRITERION_TIPO_SANGUINEO_IN = "3";
	public static final String CRITERION_NOME = "4";

	@Override
	public void create(Solicitacao solicitacao) {

		try {
			String sql = "INSERT INTO solicitacao(datahora, descricao, status, usuario_id, hemocentro_id)VALUES (?, ?, ?, ?, ?) RETURNING id;";
			conn = ConnectionConfig.getConnection();// abre conexão banco
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			int i = 0;
			if (solicitacao.getDataHora() == null) {// teste das variaveis para evitar not exception
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, solicitacao.getDataHora());
			}
			if (solicitacao.getDescricao() == null) {// teste das variaveis para evitar not exception
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, solicitacao.getDescricao());
			}
			if (solicitacao.getStatus() == null) {// teste das variaveis para evitar not exception
				ps.setNull(++i, Types.INTEGER);
			} else {
				ps.setInt(++i, solicitacao.getStatus().getId());
			}
			if (solicitacao.getUsuarioComum() == null || solicitacao.getUsuarioComum().getId() == null) {
				ps.setNull(++i, Types.BIGINT);
			} else {
				ps.setLong(++i, solicitacao.getUsuarioComum().getId());
			}
			if (solicitacao.getHemocentro() == null || solicitacao.getHemocentro().getId() == null) {
				ps.setNull(++i, Types.BIGINT);
			} else {
				ps.setLong(++i, solicitacao.getHemocentro().getId());
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				solicitacao.setId(rs.getLong("id"));
			}
			rs.close();
			ps.close();
			// Já sei id eu adiciono na lista tipo sanguineo e solicitacao (relação N X N );
//                if (solicitacao.getId() != null) {
			for (TipoSanguineo tipoSanguineo : solicitacao.getTipoSanguineoList()) {
				String sqlTipoSanguineo = "INSERT INTO tiposanguineo_solicitacao(tiposanguineo_id, solicitacao_id) VALUES (?, ?);";

				PreparedStatement psTipoSanguineo = conn.prepareStatement(sqlTipoSanguineo);
				psTipoSanguineo.setLong(1, tipoSanguineo.getId());
				psTipoSanguineo.setLong(2, solicitacao.getId());
				psTipoSanguineo.execute();// adiciona
				psTipoSanguineo.close();// fecha conexão
//                }
//                }

			}

			conn.commit();// usado somente onde vou fazer alteração no banco//em consultas não é usado.
			conn.close();
		} catch (SQLException e) {
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException ex) {

			}
		}
	}

	@Override
	public void update(Solicitacao solicitacao) {
		try {
			String sql = "UPDATE solicitacao SET datahora=?, descricao=?, status=?, usuario_id=?, hemocentro_id=? WHERE id = ?";
			conn = ConnectionConfig.getConnection();// abre conexão banco
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			int i = 0;
			if (solicitacao.getDataHora() == null) {// teste das variaveis para evitar not exception
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, solicitacao.getDataHora());
			}
			if (solicitacao.getDescricao() == null) {// teste das variaveis para evitar not exception
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, solicitacao.getDescricao());
			}
			if (solicitacao.getStatus() == null) {// teste das variaveis para evitar not exception
				ps.setNull(++i, Types.INTEGER);
			} else {
				ps.setInt(++i, solicitacao.getStatus().getId());
			}
			if (solicitacao.getUsuarioComum() == null || solicitacao.getUsuarioComum().getId() == null) {
				ps.setNull(++i, Types.BIGINT);
			} else {
				ps.setLong(++i, solicitacao.getUsuarioComum().getId());
			}
			if (solicitacao.getHemocentro() == null || solicitacao.getHemocentro().getId() == null) {
				ps.setNull(++i, Types.BIGINT);
			} else {
				ps.setLong(++i, solicitacao.getHemocentro().getId());
			}
			ps.setLong(++i, solicitacao.getId());
			ps.executeUpdate();
			ps.close();

			String sqlSolicitacao = "DELETE FROM tiposanguineo_solicitacao where solicitacao_id =?";// deleto todos do
																									// mesmo id.
			PreparedStatement updateSolicitacao = conn.prepareStatement(sqlSolicitacao);
			updateSolicitacao.setLong(1, solicitacao.getId());
			updateSolicitacao.execute();
			updateSolicitacao.close();
			for (TipoSanguineo tipoSanguineo : solicitacao.getTipoSanguineoList()) {
				String sqlTipoSanguineo = "INSERT INTO tiposanguineo_solicitacao(tiposanguineo_id, solicitacao_id) VALUES (?, ?);";
				PreparedStatement psTipoSanguineo = conn.prepareStatement(sqlTipoSanguineo);
				psTipoSanguineo.setLong(1, tipoSanguineo.getId());
				psTipoSanguineo.setLong(2, solicitacao.getId());
				psTipoSanguineo.execute();// adiciona
				psTipoSanguineo.close();// fecha conexão
			}
			conn.commit();// usado somente onde vou fazer alteração no banco//em consultas não é usado.
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public Solicitacao readById(long id) {
		Solicitacao solicitacao = null;
		PreparedStatement ps;
		conn = ConnectionConfig.getConnection();// abre conexão banco
		try {
			String sql = "select S.*,\n" + "U.nome as usuario_nome,\n" + "U.endereco as usuario_endereco,\n"
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
					+ "T.id as tiposanguineo_solicitacao_id, T.tiposangue as tiposanguineo_solicitacao_tiposangue,\n"
					+ "D.id as doacao_solicitacao_id, D.datahora as doacao_solicitacao_datahora, D.status as doacao_solicitacao_status\n"
					+ "from solicitacao S\n" + "join usuario U on U.id = S.usuario_id \n"
					+ "left join tiposanguineo TP on TP.id = U.tiposanguineo_id\n"
					+ "join usuario H on H.id = S.hemocentro_id \n"
					+ "left join tiposanguineo_solicitacao TL on TL.solicitacao_id = S.id\n"
					+ "left join tiposanguineo T on T.id = TL.tiposanguineo_id\n"
					+ "left join doacao D ON D.solicitacao_id = S.id\n" + "where S.id = ?";

			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			List<TipoSanguineo> tipoSanguineo = null;
			while (rs.next()) {
				if (solicitacao == null) {
					tipoSanguineo = null;
					solicitacao = new Solicitacao();
					solicitacao.setId(rs.getLong("id"));
					solicitacao.setDataHora(rs.getString("datahora"));
					solicitacao.setDescricao(rs.getString("descricao"));
					solicitacao.setStatus(Solicitacao.StatusSolicitacao.values()[rs.getInt("status")]);

					Usuario usuario = null;

					usuario = new UsuarioComum();
					// setar somente os atributos do UsuarioComum
					((UsuarioComum) usuario).setSexo(rs.getString("usuario_sexo"));
					((UsuarioComum) usuario).setDataNascimento(rs.getString("usuario_datanascimento"));

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

					solicitacao.setUsuarioComum((UsuarioComum) usuario);

					// seto todos os detalhes do hemocentro vinculado com a SOLICITACAO
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

					solicitacao.setHemocentro(hemocentro);
					if (tipoSanguineo == null) {
						tipoSanguineo = new ArrayList<>();
					}
					
				}

				TipoSanguineo tipoSanguineoSolicitacao = new TipoSanguineo();
				tipoSanguineoSolicitacao.setId(rs.getLong("tiposanguineo_solicitacao_id"));
				tipoSanguineoSolicitacao.setTipoSangue(rs.getString("tiposanguineo_solicitacao_tiposangue"));
				tipoSanguineo.add(tipoSanguineoSolicitacao);
				solicitacao.setTipoSanguineoList(tipoSanguineo);

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
		return solicitacao;

	}

	@Override
	public List<Solicitacao> readByCriteria(Map<String, Object> criteria) {
		List<Solicitacao> solicitacaoList = new ArrayList<Solicitacao>();
		String sql = "select S.*,\n" + "U.nome as usuario_nome,\n" + "U.endereco as usuario_endereco,\n"
				+ "U.telefone as usuario_telefone,\n" + "U.email as usuario_email,\n" + "U.senha as usuario_senha,\n"
				+ "U.estado as usuario_estado,\n" + "U.cidade as usuario_cidade,\n" + "U.cep as usuario_cep,\n"
				+ "U.status as usuario_status,\n" 
				+ "U.tiposanguineo_id as usuario_tiposanguineo_id,\n"
				+ "U.sexo as usuario_sexo,\n" + "U.datanascimento as usuario_datanascimento,\n"
				+ "U.tipo as usuario_tipo,\n" + "TP.tiposangue as usuario_tiposanguineo_tiposanguineo,\n"
				+ "H.nome as hemocentro_nome,\n" + "H.endereco as hemocentro_endereco,\n"
				+ "H.telefone as hemocentro_telefone,\n" + "H.email as hemocentro_email,\n"
				+ "H.senha as hemocentro_senha,\n" + "H.estado as hemocentro_estado,\n"
				+ "H.cidade as hemocentro_cidade,\n" + "H.cep as hemocentro_cep,\n"
				+ "TS.id as tiposanguineo_solicitacao_id, TS.tiposangue as tiposanguineo_solicitacao_tiposangue,\n"
				+ "D.id as doacao_solicitacao_id, D.datahora as doacao_solicitacao_datahora, D.status as doacao_solicitacao_status\n"
				+ "from solicitacao S\n" + "join usuario U on U.id = S.usuario_id \n"
				+ "left join tiposanguineo TP on TP.id = U.tiposanguineo_id\n"
				+ "join usuario H on H.id = S.hemocentro_id \n"
				+ "left join tiposanguineo_solicitacao TL on TL.solicitacao_id = S.id\n"
				+ "left join tiposanguineo TS on TS.id = TL.tiposanguineo_id\n"
				+ "left join doacao D ON D.solicitacao_id = S.id\n" + "where 1 = 1";
		Statement s;
		Long criterionUsuario_id = (Long) criteria.get(CRITERION_USUARIO_ID_EQ);
		if (criterionUsuario_id != null && criterionUsuario_id > 0) {
			sql += " AND S.usuario_id = " + criterionUsuario_id + " ";
		}
		Integer criterionStatus = (Integer) criteria.get(CRITERION_STATUS_EQ);
		if (criterionStatus != null) {
			sql += " AND S.status = " + criterionStatus + " ";
		}
		String criterionTipoSanguineo_in = (String) criteria.get(CRITERION_TIPO_SANGUINEO_IN);
		if (criterionTipoSanguineo_in != null && !criterionTipoSanguineo_in.isEmpty()) {
			sql += " AND U.tiposanguineo_id  = '" + criterionTipoSanguineo_in + "' ";
		}
		String criterionName = (String) criteria.get(CRITERION_NOME);
		if (criterionName != null && !criterionName.isEmpty()) {
			sql += " AND S.nome = '" + criterionName + "' ";
		}
		sql += " order by S.id asc";
		try {
			conn = ConnectionConfig.getConnection();
			conn.setAutoCommit(false);
			s = conn.createStatement();

			ResultSet rs = s.executeQuery(sql);
			Solicitacao solicitacao = null;
			Long solicitacaoId = 0L;
			List<TipoSanguineo> tipoSanguineo = null;
			while (rs.next()) {
				Long aux = rs.getLong("id");
				if (!Objects.equals(solicitacaoId, aux)) {
					tipoSanguineo = null;
					solicitacaoId = aux;
					solicitacao = new Solicitacao();
					solicitacao.setId(aux);
					solicitacao.setDataHora(rs.getString("datahora"));
					solicitacao.setDescricao(rs.getString("descricao"));
					solicitacao.setStatus(Solicitacao.StatusSolicitacao.values()[rs.getInt("status")]);

					Usuario usuario;
					usuario = new UsuarioComum();
					// setar somente os atributos do UsuarioComum
					((UsuarioComum) usuario).setSexo(rs.getString("usuario_sexo"));
					((UsuarioComum) usuario).setDataNascimento(rs.getString("usuario_datanascimento"));

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

					solicitacao.setUsuarioComum((UsuarioComum) usuario);

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

					solicitacao.setHemocentro(hemocentro);

					solicitacaoList.add(solicitacao);
					
					if (tipoSanguineo == null) {
						tipoSanguineo = new ArrayList<>();
					}
				}

				TipoSanguineo tipoSanguineoSolicitacao = new TipoSanguineo();
				tipoSanguineoSolicitacao.setId(rs.getLong("tiposanguineo_solicitacao_id"));
				tipoSanguineoSolicitacao.setTipoSangue(rs.getString("tiposanguineo_solicitacao_tiposangue"));
				
				tipoSanguineo.add(tipoSanguineoSolicitacao);
				solicitacao.setTipoSanguineoList(tipoSanguineo);


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
		return solicitacaoList;

	}

	@Override
	public void delete(Solicitacao solicitacao) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}
}
