package negocio.servicio;

import java.util.List;

public interface SAServicio {
	
	public int createServicio(TServicio Servicio);
		
	public int updateServicio(TServicio Servicio);
		
	public int deleteServicio(TServicio Servicio);
		
	public TServicio readServicio(TServicio Servicio);
		
	public List<TServicio> readAllServicio(TServicio Servicio);
}
