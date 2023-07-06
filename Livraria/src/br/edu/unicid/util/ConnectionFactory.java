package br.edu.unicid.util;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConnectionFactory {

	// Primeiro Método:
	public static Connection getConnection() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Caminho para o nosso driver

			String login = "root";
			String senha = "";
			String url = "jdbc:mysql://localhost:3306/dblivraria";// url padrao do SQL

			return DriverManager.getConnection(url, login, senha);
		} catch (Exception erro) {
			throw new Exception(erro.getMessage());
		}
	}

	public static void main(String[] args) {
		try {
			Connection conn = ConnectionFactory.getConnection();
			JOptionPane.showMessageDialog(null, "DB Connection");
		} catch (Exception erro) {
			erro.printStackTrace();
		}
	}
}