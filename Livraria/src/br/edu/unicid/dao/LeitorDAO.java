package br.edu.unicid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.unicid.model.Leitor;
import br.edu.unicid.util.ConnectionFactory;

public class LeitorDAO {

	private Leitor leitor;
	private Connection conn; // conecta com o banco de dados
	private PreparedStatement ps; // permite executar querys
	private ResultSet rs; // tabela

	public LeitorDAO() throws Exception {
		try {
			conn = ConnectionFactory.getConnection();

		} catch (Exception erro) {
			throw new Exception("Erro " + erro.getMessage());
		}
	}

	public void salvar(Leitor leitor) throws Exception {
		try {
			String sql = "INSERT INTO tb_Leitor(codLeitor, nomeLeitor, tipoLeitor)" + "values (?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, leitor.getCodLeitor());
			ps.setString(2, leitor.getNomeLeitor());
			ps.setString(3, leitor.getTipoLeitor());
			ps.executeUpdate();// executa a querry(comando SQL) no banco de dados
		} catch (Exception erro) {
			throw new Exception("Erro " + erro.getMessage());
		}
	}

	public void alterar(Leitor leitor) throws Exception {
		try {
			String sql = "UPDATE tb_leitor SET nomeLeitor=?, tipoLeitor=? "// Sempre no UPDATE tem que ter um WHERE se
																			// não apaga o BD todo
					+ "WHERE codLeitor=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, leitor.getNomeLeitor());
			ps.setString(2, leitor.getTipoLeitor());
			ps.setInt(3, leitor.getCodLeitor());
			ps.executeUpdate();
		} catch (Exception erro) {
			throw new Exception("Erro " + erro.getMessage());
		}
	}

	public void excluir(int codLeitor) throws Exception {
		try {
			String sql = "DELETE  FROM tb_leitor WHERE codLeitor=?";// Sempre no DELETE tem que ter um WHERE se não
																	// apaga o BD todo
			ps = conn.prepareStatement(sql);
			ps.setInt(1, codLeitor);
			ps.executeUpdate();
		} catch (Exception erro) {
			throw new Exception("Erro " + erro.getMessage());
		}
	}

	public List<Leitor> listarTodos() throws Exception {
		try {
			List<Leitor> leitores = new ArrayList<Leitor>();

			String sql = "SELECT * FROM tb_leitor";

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				leitor = new Leitor();
				leitor.setCodLeitor(rs.getInt(1));
				leitor.setNomeLeitor(rs.getString(2));
				leitor.setTipoLeitor(rs.getString(3));

				leitores.add(leitor);
			}
			return leitores;
		} catch (Exception erro) {
			throw new Exception("Erro " + erro.getMessage());
		}
	}

	public Leitor consultar(int codLeitor) throws Exception {

		try {
			Leitor leitor = new Leitor();

			String sql = "SELECT * FROM tb_leitor WHERE codLeitor=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, codLeitor);
			rs = ps.executeQuery();

			if (rs.next()) {
				String nomeLeitor = rs.getString("nomeLeitor");
				String tipoLeitor = rs.getString("tipoLeitor");
				leitor = new Leitor(codLeitor, nomeLeitor, tipoLeitor);
			}
			return leitor;
		} catch (Exception erro) {
			throw new Exception("Erro " + erro.getMessage());
		}
	}
}