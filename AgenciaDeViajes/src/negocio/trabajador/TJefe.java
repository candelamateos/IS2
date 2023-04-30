package negocio.trabajador;

public class TJefe extends TTrabajador{
	
	public TJefe() {
		
	}
	
	public TJefe(String nombre, int sueldo) {
		super(nombre, sueldo, "Jefe");
	}

}
