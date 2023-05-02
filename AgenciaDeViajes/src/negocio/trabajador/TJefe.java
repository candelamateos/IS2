package negocio.trabajador;

public class TJefe extends TTrabajador{
	
	public TJefe() {
		
	}
	
	public TJefe(String nombre, int sueldo, int idDepart, String tipo, int idJefe) {
		super(nombre, sueldo, idDepart, "Jefe", 0);
	}
}
