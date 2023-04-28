package negocio.viaje;

import java.util.List;


public interface SAViaje {
	
	public boolean createViaje(TViaje Viaje);
	
	public boolean updateViaje(TViaje Viaje);
		
	public boolean deleteViaje(int id);
		
	public TViaje readViaje(int id);
		
	public List<TViaje> readAllViaje();
}
