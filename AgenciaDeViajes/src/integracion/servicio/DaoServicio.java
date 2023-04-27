package integracion.servicio;

import java.util.List;


public interface DaoServicio {
	public int altaServicio(TServicio servicio);
	
	public int bajaServicio(TServicio servicio);
	
	public int modificarServicio(TServicio servicio);
	
	public TServicio buscarServicio(ID id);
	
	public List<TServicio> listarServicio();
}
