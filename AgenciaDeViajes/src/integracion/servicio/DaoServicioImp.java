package integracion.servicio;

import java.util.List;

import org.json.JSONObject;

import negocio.servicio.TServicio;

public class DaoServicioImp implements DaoServicio {
	
	private static final String ARCHIVO = "clientes.json";

	@Override
	public int altaServicio(TServicio servicio) {
		int id = -1;
		JSONObject json = new JSONObject();
		
		json.put("nombre", cliente.getNombre());
		json.put("activo", true);
		
		JSONObject data = loadData();
		JSONObject clientes = data.getJSONObject("clientes");
		
		id = data.getInt("proximo id");
		json.put("id", id);
		
		clientes.put(json.get("id").toString(), json);
		data.put("proximo id", id + 1);
		
		saveData(data);
		
		return id;
	}

	@Override
	public int bajaServicio(TServicio servicio) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modificarServicio(TServicio servicio) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TServicio buscarServicio(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TServicio> listarServicio() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
