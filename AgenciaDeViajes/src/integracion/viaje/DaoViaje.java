package integracion.viaje;

import java.util.List;

import negocio.viaje.TViaje;

public interface DaoViaje {
	
	public int createViaje(TViaje viaje);
	
	public int deleteViaje(TViaje viaje);
	
	public int updateViaje(TViaje viaje);
	
	public TViaje readViaje(int id);
	
	public List<TViaje> readAllViaje();
}
