package pos_java_jdbc.pos_java_jdbc;

import org.junit.Test;

import dao.UserDAO;
import model.UserModel;

public class TesteBancoJDBC {
	
	UserDAO dao= new UserDAO();
	UserModel user = new UserModel();

	@Test
	public void testeSalvar() {
		//SingleConnection.getConnection();
		user.setNome("Moly");
		user.setEmail("molinha@teste.com");
		
		try {
			dao.salvar(user);
			System.out.println("sucesso...");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testeListar() {
		try {
			dao.listar();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testeBuscarPorID() {
		try {
			dao.buscarPorID(3L);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testeAtualizarNome() {
		try {
			UserModel user = new UserModel();
			user.setId(5L);
			user.setNome("Zé Tobias");
			
			dao.atualizarNome(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deletar() {
		try {
			dao.deletar(7L);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
