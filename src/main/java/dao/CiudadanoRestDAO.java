package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import conexion.Conexion;
import vo.CiudadanoRestVO;
 
@SuppressWarnings("unused")
public class CiudadanoRestDAO {

	private PreparedStatement PS = null;
	
	public static CiudadanoRestVO GetCiudadanoByIdDAO(Long id) {
		
		CiudadanoRestVO CiudadanoBD = new CiudadanoRestVO();
		
		try {
			
			Connection conexion = Conexion.ConectarBD();
			PreparedStatement PS = conexion.prepareStatement("SELECT idciudadano_rest, nombre, edad, genero FROM ciudadano_rest where idciudadano_rest = ?");
			PS.setLong(1, id);
			ResultSet CiudadanoSet = PS.executeQuery();
			
			if(CiudadanoSet.next()) {
			
			CiudadanoBD.setId(CiudadanoSet.getLong("idciudadano_rest"));
			CiudadanoBD.setNombre(CiudadanoSet.getString("nombre")); 
			CiudadanoBD.setEdad(CiudadanoSet.getString("edad"));
			CiudadanoBD.setGenero(CiudadanoSet.getString("genero"));
			System.out.println("Usuario Solicitado: \n" + CiudadanoBD.toString());
			
			} else {
				CiudadanoBD = null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return CiudadanoBD;
	}

	public static boolean AddCiudadano(CiudadanoRestVO ciudadano) {
		
		Boolean resultado = false;
		Connection conexion = Conexion.ConectarBD();
		
		try {
			
			PreparedStatement PS = conexion.prepareStatement("INSERT INTO ciudadano_rest (nombre, edad, genero) values (?,?,?)");
			PS.setString(1, ciudadano.getNombre());
			PS.setString(2, ciudadano.getEdad());
			PS.setString(3, ciudadano.getGenero());
			
		if (PS.executeUpdate() == 1) {
			resultado = true;
			System.out.println("Usuario Ingresado");
		} else {
			resultado = false;
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}

	public static ArrayList<CiudadanoRestVO> RequestCiudadanos() {
		
		ArrayList<CiudadanoRestVO> ListaCiudadanos = new ArrayList<>();
		
		try {
			
			
			Connection conexion = Conexion.ConectarBD();
			java.sql.Statement Statement = conexion.createStatement();
			ResultSet CiudadanoSet = Statement.executeQuery("SELECT * FROM ciudadano_rest");
			
			while (CiudadanoSet.next()) {
				
				CiudadanoRestVO Ciudadano = new CiudadanoRestVO();
				Ciudadano.setId(CiudadanoSet.getLong("idciudadano_rest"));
				Ciudadano.setNombre(CiudadanoSet.getString("nombre")); 
				Ciudadano.setEdad(CiudadanoSet.getString("edad"));
				Ciudadano.setGenero(CiudadanoSet.getString("genero"));
				
				ListaCiudadanos.add(Ciudadano);
				
			}
			
			Iterator<CiudadanoRestVO> IteratorCiudadano = ListaCiudadanos.iterator();
			
			while (IteratorCiudadano.hasNext()) {
				CiudadanoRestVO ciudadanoRestVO = (CiudadanoRestVO) IteratorCiudadano.next();
				System.out.println(ciudadanoRestVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ListaCiudadanos;
		
		
	}

	public static Boolean DeleteCiudadanoById(Long id) {
		
		Boolean resultado = false;
		
		try {
			Connection conexion = Conexion.ConectarBD();
			PreparedStatement PS = conexion.prepareStatement("DELETE FROM ciudadano_rest where idciudadano_rest = ?");
			PS.setLong(1, id);
			if (PS.executeUpdate() == 1) {
				System.out.println("DATOS DE CIUDADANO CON ID: " + id + " ELIMINADO");
				return resultado = true;
			} else {
				return resultado = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
		
		
		
	}
}
