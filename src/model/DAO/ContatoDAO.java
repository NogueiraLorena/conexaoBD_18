package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Contato;
import util.Conexao;

public class ContatoDAO {

	public static Contato inserir(String nome,String email,String mensagem) {
		Contato contato = null;
		
		Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/18_conexaobd?useTimezone=true&serverTimezone=UTC",
										"com.mysql.cj.jdbc.Driver",
										"root",
										"alunolab");
		Connection con = conexao.obterConexao();
		
		String sql = "insert into contato(nome,email,mensagem) values(?,?,?)";
		
		try {
			PreparedStatement comando = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, nome);
			comando.setString(2, email);
			comando.setString(3, mensagem);
			
			if(comando.executeUpdate()>0) {
				ResultSet rs = comando.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					contato = new Contato(id, nome, email, mensagem);
				}
				rs.close();
			}
			comando.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("Erro ao inserir no Banco de Dados.");
			System.out.println("Verifique sua instrução SQL.");
			System.out.println("Mensagem "+e.getMessage());
		}
		
		return contato;
	}
}
	
	public static Contato[] buscarTodos(){
		 Contato[] contatos = null;
		 try {
		 // Crição do select
		 String sql = "Select * from contato";
		 // Obter a conexão com o banco de dados
		 Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/18_conexaobd?useTimezone=true&serverTimezone=UTC",
					"com.mysql.cj.jdbc.Driver",
					"root",
					"alunolab");
		Statement comando = conexao.createStatement();
		 /* ResultSet - Classe java que monta em memória uma matriz
		 * com a resposta das linhas do banco de dados
		 */
		 ResultSet rs = comando.executeQuery(sql);
		 // vetor de objetos
		 contatos = new Contato[10];	
		
		 /* Passagem de linha de dados do ResultSet para o vetor de objetos
		  * (uma linha de dados da matriz do ResultSet é copiada para
		  * um objeto no vetor contatos) 
		  */
		 int i = 0;
		 while (rs.next()) {
		  contatos[i++] = new Contato(
		  rs.getInt("id"),
		  rs.getString("nome"),
		  rs.getString("email"),
		  rs.getString("mensagem"));
		 }
		 }
		 
}