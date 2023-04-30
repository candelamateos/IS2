package integracion.trabajador;

import java.util.List;

import negocio.trabajador.TTrabajador;

public interface DaoTrabajador {
	
	public int createTrabajador(TTrabajador trabajo);
	
	public boolean updateTrabajador(TTrabajador trabajo);
	
	public boolean deleteTrabajador(int id);
	
	public TTrabajador readTrabajador(int id);
	
	public List<TTrabajador> readAllTrabajador(TTrabajador trabajo);
}
