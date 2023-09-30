package vo;

public class CiudadanoRestVO {

	private String nombre;
	private String edad;
	private String genero;
	private Long id;
	
	public CiudadanoRestVO(Long id, String nombre, String edad, String genero) {
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.genero = genero;
	}

	public CiudadanoRestVO() {}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CiudadanoTesteoRest Id: " + id + "\nNombre: " + nombre + "\nEdad: " + edad + "\nGenero: " + genero;
	}
	
	
}
