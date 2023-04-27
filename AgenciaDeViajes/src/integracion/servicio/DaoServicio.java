package integracion.servicio;

import java.util.List;

import negocio.servicio.TServicio;


public interface DaoServicio {
	public int altaServicio(TServicio servicio);
	
	public int bajaServicio(TServicio servicio);
	
	public int modificarServicio(TServicio servicio);
	
	public TServicio buscarServicio(int id);
	
	public List<TServicio> listarServicio();
}
