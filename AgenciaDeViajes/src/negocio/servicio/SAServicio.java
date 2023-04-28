package negocio.servicio;

import java.util.List;

public interface SAServicio {
	
	public boolean createServicio(TServicio Servicio);
		
	public boolean updateServicio(TServicio Servicio);
		
	public boolean deleteServicio(int id);
		
	public TServicio readServicio(int id);
		
	public List<TServicio> readAllServicio();
}
