package negocio.trabajador;

import java.util.List;

public interface SATrabajador {

	public int createTrabajador(TTrabajador Trabajador);
	
	public boolean updateTrabajador(TTrabajador Trabajador);
		
	public boolean deleteTrabajador(int id);
		
	public TTrabajador readTrabajador(int id);
		
	public List<TTrabajador> readAllTrabajador();
}
