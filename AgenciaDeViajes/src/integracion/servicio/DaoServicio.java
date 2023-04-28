package integracion.servicio;

import java.util.List;

import negocio.servicio.TServicio;


public interface DaoServicio {
	public int createServicio(TServicio servicio);
	
	public int deleteServicio(TServicio servicio);
	
	public int updateServicio(TServicio servicio);
	
	public TServicio readServicio(int id);
	
	public List<TServicio> readAllServicio();
}
