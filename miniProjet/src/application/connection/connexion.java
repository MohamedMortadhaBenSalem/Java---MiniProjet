package application.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class connexion {
	private static Connection cn = null;

	public static Connection getConx() {
		try {
			if (cn == null) {
				Class.forName("com.mysql.jdbc.Driver");
				 cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojet", "root", "");
				System.out.println("Connected");
			}

	
		} catch (Exception e) {
			System.out.println(e);
		}
		return cn;
	}

	public static void main(String args[]) {
		connexion.getConx();
	}
}
