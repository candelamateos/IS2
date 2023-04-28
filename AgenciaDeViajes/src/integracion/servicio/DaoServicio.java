package integracion.servicio;

import java.util.List;

import negocio.servicio.TServicio;


public interface DaoServicio {
	public int createServicio(TServicio servicio);
	
	public boolean deleteServicio(int id);
	
	public boolean updateServicio(TServicio servicio);
	
	public TServicio readServicio(int id);
	
	public List<TServicio> readAllServicio();
}
