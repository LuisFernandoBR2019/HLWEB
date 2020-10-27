package com.ptc.helplife.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ptc.helplife.Entity.TipoSanguineo;
import com.ptc.helplife.Entity.Usuario;
import com.ptc.helplife.Entity.UsuarioComum;
import com.ptc.helplife.Entity.UsuarioHemocentro;
import com.ptc.helplife.config.ConnectionConfig;

public class UsuarioDAO implements BaseImplementDAO<Usuario> {

	// Critérios de busca
	public static final String CRITERION_TIPO_SANGUINEO_IN = "1";
	public static final String CRITERION_NOME = "2";
	public static final String CRITERION_SENHA = "3";
	public static final String CRITERION_EMAIL = "4";
	public static final String CRITERION_USUARIO_COMUM = "5";
	public static final String CRITERION_USUARIO_HEMOCENTRO = "6";


	private Connection conn;

	@Override
	public void create(Usuario usuario) {
		try {
			String sql = "INSERT INTO usuario(nome, endereco, telefone, email, senha, estado, cidade, cep, tiposanguineo_id, sexo, tipo,status, datanascimento)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?) RETURNING id;";
			conn = ConnectionConfig.getConnection();// abre conexão banco// abre conexão banco
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);// recebo comando que erá dado no banco
			int i = 0;
			if (usuario.getNome() == null) {// teste das variaveis para evitar not exception
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, usuario.getNome());
			}
			if (usuario.getEndereco() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, usuario.getEndereco());
			}
			if (usuario.getTelefone() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, usuario.getTelefone());
			}
			if (usuario.getEmail() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, usuario.getEmail());
			}
			if (usuario.getSenha() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, usuario.getSenha());
			}
			if (usuario.getEstado() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, usuario.getEstado());
			}
			if (usuario.getCidade() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, usuario.getCidade());
			}
			if (usuario.getCep() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, usuario.getCep());
			}
			if (usuario instanceof UsuarioComum) {
				
				
				if (((UsuarioComum) usuario).getTipoSanguineo().getId() == null) {
					ps.setNull(++i, Types.BIGINT);
				} else {
					ps.setLong(++i, ((UsuarioComum) usuario).getTipoSanguineo().getId());
				}
				
				if (((UsuarioComum) usuario).getSexo() == null) {
					ps.setNull(++i, Types.VARCHAR);
				} else {
					ps.setString(++i, ((UsuarioComum) usuario).getSexo());
				}
				ps.setInt(++i, 0);// tipo usuario
				ps.setInt(++i, 0);// tipo status
				if (((UsuarioComum) usuario).getDataNascimento() == null) {
					ps.setNull(++i, Types.VARCHAR);
				} else {
					ps.setString(++i, ((UsuarioComum) usuario).getDataNascimento());
				}

			} else {
				// TODO: Hemocentro
				ps.setNull(++i, Types.BIGINT); // tipo sanguineo
				ps.setNull(++i, Types.VARCHAR);// sexo
				ps.setInt(++i, 1);// tipo usuario
				ps.setInt(++i, 0);// tipo status
				ps.setNull(++i, Types.VARCHAR);// tipo data nascimento

			}

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				usuario.setId(rs.getLong("id"));
			}
			rs.close();
			ps.close();
			conn.commit();// usado somente onde vou fazer alteração no banco//em consultas não é usado.
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
	}

	@Override
	public void update(Usuario usuario) {
		try {
			String sql = "UPDATE usuario\n"
					+ "	SET nome=?, endereco=?, telefone=?, email=?, senha=?, estado=?, cidade=?, cep=?, tiposanguineo_id=?, tipo=?, status=?, sexo=?, datanascimento=?, codigo_recuperar_senha = ?\n"
					+ "	WHERE id = ?;";
			conn = ConnectionConfig.getConnection();// abre conexão banco// abre conexão banco
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			int i = 0;
			if (usuario.getNome() == null) {// teste das variaveis para evitar not exception
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, usuario.getNome());
			}
			if (usuario.getEndereco() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, usuario.getEndereco());
			}
			if (usuario.getTelefone() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, usuario.getTelefone());
			}
			if (usuario.getEmail() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, usuario.getEmail());
			}
			if (usuario.getSenha() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, usuario.getSenha());
			}
			if (usuario.getEstado() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, usuario.getEstado());
			}
			if (usuario.getCidade() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, usuario.getCidade());
			}
			if (usuario.getCep() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, usuario.getCep());
			}
			if (usuario instanceof UsuarioComum) {
			
				if (((UsuarioComum) usuario).getTipoSanguineo().getId() == null) {
					ps.setNull(++i, Types.BIGINT);
				} else {
					ps.setLong(++i, ((UsuarioComum) usuario).getTipoSanguineo().getId());
				}
				ps.setInt(++i, 0);// tipo usuario
				ps.setInt(++i, 0);// tipo status

				if (((UsuarioComum) usuario).getSexo() == null) {
					ps.setNull(++i, Types.VARCHAR);
				} else {
					ps.setString(++i, ((UsuarioComum) usuario).getSexo());
				}
				if (((UsuarioComum) usuario).getDataNascimento() == null) {
					ps.setNull(++i, Types.DATE);
				} else {
					ps.setString(++i, ((UsuarioComum) usuario).getDataNascimento());
				}

			} else {
				// TODO: Hemocentro	
				ps.setNull(++i, Types.BIGINT); // tipo sanguineo
				ps.setInt(++i, 1);// tipo usuario
				ps.setInt(++i, 0);// tipo status
				ps.setNull(++i, Types.VARCHAR);// sexo
				ps.setNull(++i, Types.VARCHAR);// tipo data nascimento

			}
			if (usuario.getCodigo() == null) {
				ps.setNull(++i, Types.VARCHAR);
			} else {
				ps.setString(++i, usuario.getCodigo());
			}
			ps.setLong(++i, usuario.getId());
			ps.executeUpdate();
			ps.close();
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
	public Usuario readById(long id) {
		Usuario usuario = null;
		PreparedStatement ps;
		conn = ConnectionConfig.getConnection();// abre conexão banco
		try {
			String sql = "select  U.* ,TS.tiposangue as tiposanguineo_tiposangue from usuario U Left  join tiposanguineo TS on TS.id = U.tiposanguineo_id where U.id = ?;";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int tipoUsuario = rs.getInt("tipo");
				if (tipoUsuario == 0) {
					usuario = new UsuarioComum();
					// setar somente os atributos do UsuarioComum
					((UsuarioComum) usuario).setSexo(rs.getString("sexo"));
					((UsuarioComum) usuario).setDataNascimento(rs.getString("datanascimento"));
					TipoSanguineo tipoSanguineo = new TipoSanguineo();
					tipoSanguineo.setId(rs.getLong("tiposanguineo_id"));
					tipoSanguineo.setTipoSangue(rs.getString("tiposanguineo_tiposangue"));
					((UsuarioComum) usuario).setTipoSanguineo(tipoSanguineo);
				} else {
					usuario = new UsuarioHemocentro();
					// setar somente os atributos do UsuarioHemocentro(usuario)
				}
				// setar atributos em comum para usuario
				usuario.setId(rs.getLong("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEstado(rs.getString("estado"));
				usuario.setCidade(rs.getString("cidade"));
				usuario.setCep(rs.getString("cep"));
				usuario.setStatus(Usuario.StatusUsuario.values()[rs.getInt("status")]);
				usuario.setTipo(rs.getInt("tipo"));
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
		return usuario;
	}

	@Override
	public List<Usuario> readByCriteria(Map<String, Object> criteria) {
		List<Usuario> usuarioList = new ArrayList<Usuario>();
		String sql = "select  U.* ,TS.tiposangue as tiposanguineo_tiposangue from usuario U Left  join tiposanguineo TS on TS.id = U.tiposanguineo_id where 1 = 1";
		Statement s;
		String criterionTipoSanguineo = (String) criteria.get(CRITERION_TIPO_SANGUINEO_IN);
		if (criterionTipoSanguineo != null && !criterionTipoSanguineo.isEmpty()) {
			sql += " AND U.tiposanguineo_id  = '" + criterionTipoSanguineo + "' ";
		}
		String criterionName = (String) criteria.get(CRITERION_NOME);
		if (criterionName != null && !criterionName.isEmpty()) {
			sql += " AND U.nome = '" + criterionName + "' ";
		}
		String criterionSenha = (String) criteria.get(CRITERION_SENHA);
		if (criterionSenha != null && !criterionSenha.isEmpty()) {
			sql += " AND U.senha  = '" + criterionSenha + "' ";
		}
		String criterionEmail = (String) criteria.get(CRITERION_EMAIL);
		if (criterionEmail != null && !criterionEmail.isEmpty()) {
			sql += " AND U.Email  = '" + criterionEmail + "' ";
		}
		Integer criterionUsuarioComum = (Integer) criteria.get(CRITERION_USUARIO_COMUM);
		if (criterionUsuarioComum != null) {
			sql += " AND U.tipo = 0 ";
		}
		Integer criterionUsuarioHemocentro = (Integer) criteria.get(CRITERION_USUARIO_HEMOCENTRO);
		if (criterionUsuarioHemocentro != null) {
			sql += " AND U.tipo = 1 ";
		}

		sql += " order by U.id asc";
		try {
			conn = ConnectionConfig.getConnection();// abre conexão banco
			conn.setAutoCommit(false);
			s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			Usuario usuario = null;
			while (rs.next()) {
				int tipoUsuario = rs.getInt("tipo");
				if (tipoUsuario == 0) {
					usuario = new UsuarioComum();
					// setar somente os atributos do UsuarioComum
					((UsuarioComum) usuario).setSexo(rs.getString("sexo"));
					((UsuarioComum) usuario).setDataNascimento(rs.getString("datanascimento"));
					TipoSanguineo tipoSanguineo = new TipoSanguineo();
					tipoSanguineo.setId(rs.getLong("tiposanguineo_id"));
					tipoSanguineo.setTipoSangue(rs.getString("tiposanguineo_tiposangue"));
					((UsuarioComum) usuario).setTipoSanguineo(tipoSanguineo);
				} else {
					usuario = new UsuarioHemocentro();
					// setar somente os atributos do UsuarioHemocentro(usuario)
				}
				// setar atributos em comum para usuario
				usuario.setId(rs.getLong("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setEstado(rs.getString("estado"));
				usuario.setCidade(rs.getString("cidade"));
				usuario.setCep(rs.getString("cep"));
				usuario.setCodigo(rs.getString("codigo_recuperar_senha"));
				usuario.setStatus(Usuario.StatusUsuario.values()[rs.getInt("status")]);
				usuario.setTipo(rs.getInt("tipo"));
				usuarioList.add(usuario);
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

		return usuarioList;
	}

	@Override
	public void delete(Usuario usuario) {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	public Usuario readByUserNameAndPass(String user, String pass) {

		Usuario usuario = null;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionConfig.getConnection();// abre conexão banco

			stmt = conn.prepareStatement("SELECT * FROM usuario WHERE email = ?");
			stmt.setString(1, user);

			rs = stmt.executeQuery();

			if (rs.next()) {
//                user = new User();
//                user.setId(rs.getLong("id"));
//                user.setUser(rs.getString("_user"));
//                user.setPass(rs.getString("pass"));
//                user.setName(rs.getString("_name"));
//                user.setEmail(rs.getString("email"));
//                user.setLevel(ELevel.valueOf(rs.getString("_level")));
			}

		} catch (SQLException ex) {
		} finally {

			try {
				if (!rs.isClosed()) {
					rs.close();
				}

			} catch (SQLException ex) {
			}

			try {
				if (!stmt.isClosed()) {
					stmt.close();
				}
			} catch (SQLException ex) {
			}

			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException ex) {
			}
		}

		return usuario;
	}

	// Logar
	public Usuario login(String email, String senha) {
		Map<String, Object> criteria = new HashMap<>();
		criteria.put(UsuarioDAO.CRITERION_EMAIL, email);
		criteria.put(UsuarioDAO.CRITERION_SENHA, senha);

		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> usuarioList = dao.readByCriteria(criteria);

		if (usuarioList.size() == 1) {
			return usuarioList.get(0);
		} else {
			return null;
		}
	}
	// Recuparer Senha

	public Usuario recuperarSenha(String email) {
		// TODO: implementar método
		Map<String, Object> criteria = new HashMap<>();
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> usuarioList = dao.readByCriteria(criteria);
		if (usuarioList.size() == 1) {
			return usuarioList.get(0);
		} else {
			return null;
		}
	}

	
}
