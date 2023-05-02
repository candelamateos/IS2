package negocio.trabajador;

public class TVendedor extends TTrabajador{
	
	public TVendedor() {
	}
	
	public TVendedor(String nombre, int sueldo, int idDepart, String tipo, int idJefe) {
		super(nombre, sueldo, idDepart, "Vendedor", idJefe);
	}
}
