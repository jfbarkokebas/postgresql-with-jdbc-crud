package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.UserModel;

public class UserDAO {

	private Connection connection;

	public UserDAO() {
		connection = SingleConnection.getConnection();
	}
	
	public List<UserModel> listar(){
		
		List<UserModel> list = new ArrayList<UserModel>();
		
		try {
			String sql = "SELECT * FROM user_posjava";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				UserModel user = new UserModel();
				user.setId(result.getLong("id"));
				user.setNome(result.getString("nome"));
				user.setEmail(result.getString("email"));
				
				list.add(user);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(list);
		return list;
	}
                                               
	public void salvar(UserModel user) throws SQLException {


			try {
				String sql = "insert into user_posjava(nome, email)" + "values(?, ?)";
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setString(1, user.getNome());
				stmt.setString(2, user.getEmail());
				stmt.execute();
				connection.commit();

			} catch (Exception e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
	}

	public void deletar(Long id) throws SQLException {
		try {
			String sql = "delete from user_posjava where id= ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.execute();
			connection.commit();
			System.out.println("excluido com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public UserModel buscarPorID(Long id) {
		
		UserModel user = new UserModel();                 
		
		try {
			String sql = "SELECT * FROM user_posjava WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
            ResultSet result = stmt.executeQuery();
			
			while(result.next()) {
				user.setId(result.getLong("id"));
				user.setNome(result.getString("nome"));
				user.setEmail(result.getString("email"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Busca por ID: " + user);
		return user;
	}
	
	public void atualizarNome(UserModel user) {
		
		try {
			String sql = "UPDATE user_posjava set nome = ? WHERE id = ?"; 
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getNome());
			stmt.setLong(2, user.getId());
			stmt.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
