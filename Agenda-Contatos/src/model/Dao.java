package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Dao {

	/* MOdolu de conexao */

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?userTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "";

	private Connection conectar() {

		Connection conexao = null;

		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, user, password);
			return conexao;
		} catch (Exception e) {

			System.out.println(e + "erro na coxeão com o banco");
			return null;
		}

	}
	// * CRUD Creat

	public void inserirContato(ContatoBeans contato) {

		String sql = "insert into contato (nome,fone,email) values(?,?,?)";

		try {

			// conectar ao banco
			Connection conexao = conectar();
			PreparedStatement pst = conexao.prepareStatement(sql);

			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());

			pst.executeUpdate();
			pst.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// CRUD Read
	public ArrayList<ContatoBeans> listarContatos() {

		try {

			ArrayList<ContatoBeans> contatos = new ArrayList<>();

			String sql = "select *from contato order by nome";
			Connection conexao = conectar();
			PreparedStatement pst = conexao.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);

				// inserindo dados no arraylist
				contatos.add(new ContatoBeans(id, nome, fone, email));
			}
			conexao.close();
			return contatos;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	//// CRUD Update == editar e salvar

	public void selecionarContato(ContatoBeans contato) {

		String sql = "select * from contato where idcont= ?";

		try {
			Connection conexao = conectar();
			PreparedStatement pst = conexao.prepareStatement(sql);
			pst.setString(1, contato.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				contato.setId(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));

			}
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

// CRUD Updat
	public void alterarContato(ContatoBeans contato) {
		String sql = "update contato set nome=?,fone=?,email=?  where idcont =?";
		try {
			Connection conexao = conectar();
			PreparedStatement pst = conexao.prepareStatement(sql);
			
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getId());
			// ResultSet rs = pst.executeQuery();
			pst.executeUpdate();
			conexao.close();

		} catch (Exception e) {
			System.out.println("[erro]"+e);
		}
	}
	public void deletarContato(ContatoBeans contato) {
		
		String sql = "delete from contato where idcont  =?";
		try {
			Connection conexao = conectar();
			PreparedStatement pst = conexao.prepareStatement(sql);
			pst.setString(1, contato.getId());
			pst.executeUpdate();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
