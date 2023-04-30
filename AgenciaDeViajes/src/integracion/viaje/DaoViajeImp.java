package integracion.viaje;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.viaje.TViaje;

public class DaoViajeImp implements DaoViaje{
	
	private static final String ARCHIVO = "viajes.json";

	@Override
	public int createViaje(TViaje viaje) {
		int id = -1;
		JSONObject data = loadData();

		id = data.getInt("proximo id");

		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("precio", viaje.getPrecio());
		json.put("numPlazas", viaje.getNumPlazas());
		json.put("idActividad", viaje.getIdActividad());
		json.put("idAlojamiento", viaje.getIdAlojamiento());
		json.put("idTransporte", viaje.getIdTransporte());
		json.put("activo", true);

		JSONArray viajes = data.getJSONArray("viajes");
		viajes.put(json.getInt("id"), json);

		data.put("proximo id", id + 1);

		if (!saveData(data)) {
			return -1;
		}

		return id;
	}

	@Override
	public boolean deleteViaje(int id) {
		boolean resultado = false;
		JSONObject data = loadData();
		JSONArray viajes = data.getJSONArray("viajes");

		if (id < data.getInt("proximo id")) {
			JSONObject json = viajes.getJSONObject(id);
			
			if (!json.has("id") || !json.has("precio") || !json.has("numPlazas") || !json.has("idActividad") || !json.has("idAlojamiento") || !json.has("idTransporte") || !json.has("activo")) {
				return false;
			}
			if(!json.getBoolean("activo")) {
				return false;
			}
			json.put("activo", false);
			
			return saveData(data);
		}
		return resultado;
	}

	@Override
	public boolean updateViaje(TViaje viaje) {
		int id = viaje.getId();
		
		JSONObject data = loadData();
		JSONArray viajes = data.getJSONArray("viajes");
		
		if (id < data.getInt("proximo id")) {
			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("precio", viaje.getPrecio());
			json.put("numPlazas", viaje.getNumPlazas());
			json.put("idActividad", viaje.getIdActividad());
			json.put("idAlojamiento", viaje.getIdAlojamiento());
			json.put("idTransporte", viaje.getIdTransporte());
			json.put("activo", viaje.getActivo());
			
			viajes.put(json.getInt("id"), json);
		}
		
		return saveData(data);
	}

	@Override
	public TViaje readViaje(int id) {
		TViaje viaje = null;

		JSONObject data = loadData();
		JSONArray viajes = data.getJSONArray("viajes");

		if (id < data.getInt("proximo id") ) {
			JSONObject json = viajes.getJSONObject(id);
			viaje = new TViaje();
			if (!json.has("id") || !json.has("precio") || !json.has("numPlazas") || !json.has("idActividad") || !json.has("idAlojamiento") || !json.has("idTransporte") || !json.has("activo")) {
				return null;
			}
			if(!json.getBoolean("activo")) {
				return null;
			}
			viaje.setId(json.getInt("id"));
			viaje.setPrecio(json.getInt("precio"));
			viaje.setNumPlazas(json.getInt("numPlazas"));
			viaje.setIdActividad(json.getInt("idActividad"));
			viaje.setIdAlojamiento(json.getInt("idAlojamiento"));
			viaje.setIdTransporte(json.getInt("idTransporte"));
			viaje.setActivo(json.getBoolean("activo"));
		}
		return viaje;
	}

	@Override
	public List<TViaje> readAllViaje() {
		List<TViaje> lista = new ArrayList<>();
		
		JSONObject data = loadData();
		JSONArray viajes = data.getJSONArray("viajes");
		
		for(int i = 0; i < data.getInt("proximo id"); i++) {
			JSONObject json = viajes.getJSONObject(i);
			if (!json.has("id") || !json.has("precio") || !json.has("numPlazas") || !json.has("idActividad") || !json.has("idAlojamiento") || !json.has("idTransporte") || !json.has("activo")) {
				return null;
			}
			if(json.getBoolean("activo")) {
				TViaje viaje = new TViaje();
				viaje.setId(json.getInt("id"));
				viaje.setPrecio(json.getInt("precio"));
				viaje.setNumPlazas(json.getInt("numPlazas"));
				viaje.setIdActividad(json.getInt("idActividad"));
				viaje.setIdAlojamiento(json.getInt("idAlojamiento"));
				viaje.setIdTransporte(json.getInt("idTransporte"));
				viaje.setActivo(json.getBoolean("activo"));
				
				lista.add(viaje);
			}
		}		
		return lista;
	}
	
	private JSONObject loadData() {
		InputStream input;
		JSONObject jsonInput;
		try {
			input = new FileInputStream(new File(ARCHIVO));
			jsonInput = new JSONObject(new JSONTokener(input));
		} catch (FileNotFoundException e) {
			jsonInput = new JSONObject();
			jsonInput.put("proximo id", 0);
			jsonInput.put("viajes", new JSONArray());
		}
		return jsonInput;
	}

	private boolean saveData(JSONObject data) {
		try (OutputStream output = new FileOutputStream(new File(ARCHIVO))) {
			PrintStream print = new PrintStream(output);
			print.print(data);

		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}

}
