package negocio.trabajador;

public class TVendedor extends TTrabajador{
	
	private int idJefe;
	
	public TVendedor() {
	}
	
	public TVendedor(String nombre, int sueldo) {
		super(nombre, sueldo, "Vendedor");
	}

	public int getIdJefe() {
		return idJefe;
	}

	public void setIdJefe(int idJefe) {
		this.idJefe = idJefe;
	}

}
