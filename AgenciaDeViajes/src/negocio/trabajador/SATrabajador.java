package negocio.trabajador;

import java.util.List;

public interface SATrabajador {

	public int createServicio(TTrabajador Trabajador);
	
	public int updateServicio(TTrabajador Trabajador);
		
	public int deleteServicio(TTrabajador Trabajador);
		
	public TTrabajador readServicio(TTrabajador Trabajador);
		
	public List<TTrabajador> readAllServicio(TTrabajador Trabajador);
}
