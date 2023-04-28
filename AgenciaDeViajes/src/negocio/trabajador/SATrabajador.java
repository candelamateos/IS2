package negocio.trabajador;

import java.util.List;

public interface SATrabajador {

	public boolean createServicio(TTrabajador Trabajador);
	
	public boolean updateServicio(TTrabajador Trabajador);
		
	public boolean deleteServicio(int id);
		
	public TTrabajador readServicio(int id);
		
	public List<TTrabajador> readAllServicio();
}
