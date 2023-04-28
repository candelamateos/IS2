package integracion.servicio;

import java.util.List;

import org.json.JSONObject;

import negocio.servicio.TServicio;

public class DaoServicioImp implements DaoServicio {
	
	private static final String ARCHIVO_AlOJAMIENTOS = "servicios_alojamientos.json";
	private static final String ARCHIVO_TRANSPORTES = "servicios_transportes.json";
	private static final String ARCHIVO_ACTIVIDADES = "servicios_actividades.json";

	@Override
	public int createServicio(TServicio servicio) {
		
		return 0;
		
	}

	@Override
	public boolean deleteServicio(int id) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean updateServicio(TServicio servicio) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public TServicio readServicio(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TServicio> readAllServicio() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
