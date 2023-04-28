package integracion.viaje;

import java.util.List;

import negocio.viaje.TViaje;

public interface DaoViaje {
	
	public boolean createViaje(TViaje viaje);
	
	public boolean deleteViaje(int id);
	
	public boolean updateViaje(TViaje viaje);
	
	public TViaje readViaje(int id);
	
	public List<TViaje> readAllViaje();
}
