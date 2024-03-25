package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	//private static String url = "jdbc:mysql://localhost/posjava";
	private static String url = "jdbc:postgresql://localhost/posjava";
	private static String user = "postgres";
	private static String password = "151902";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	
	private static void conectar() {
		
		try {
			if(connection == null) {
				//Class.forName("com.mysql.cj.jdbc.Driver");
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("Success Connection !!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static Connection getConnection() {
		return connection;
	}
	

}
