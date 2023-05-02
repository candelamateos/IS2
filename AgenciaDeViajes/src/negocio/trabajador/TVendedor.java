package negocio.trabajador;

public class TVendedor extends TTrabajador{
	
	int idJefe;
	
	public TVendedor() {
	}
	
	public TVendedor(String nombre, int sueldo, int idDepart, int idJefe) {
		super(nombre, sueldo, idDepart, "vendedor");
		this.idJefe = idJefe;
	}

	public int getIdJefe() {
		return idJefe;
	}

	public void setIdJefe(int idJefe) {
		this.idJefe = idJefe;
	}
}
