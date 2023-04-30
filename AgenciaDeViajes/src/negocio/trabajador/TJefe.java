package negocio.trabajador;

public class TJefe extends TTrabajador{
	
	public TJefe() {
		
	}
	
	public TJefe(String nombre, int sueldo) {
		super(nombre, sueldo, "Jefe");
	}

	@Override
	public int getIdJefe() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setIdJefe(int idJefe) {
		// TODO Auto-generated method stub
		
	}
}
