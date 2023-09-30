package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	private static final String Controlador = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost/testeorest";
	private static final String usuario = "root";
	private static final String clave = "";
	
	static Connection conexion = null;
	
	public static Connection ConectarBD() {
		try {
			Class.forName(Controlador);
			conexion = DriverManager.getConnection(url, usuario, clave);
			System.out.println("Conectado");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR CONECTAR BASE DE DATOS");
		}
		return conexion;
	};
	
	
	
	
}
