package integracion.servicio;

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

import negocio.cliente.TCliente;
import negocio.servicio.TActividad;
import negocio.servicio.TAlojamiento;
import negocio.servicio.TTransporte;
import negocio.servicio.TServicio;

public class DaoServicioImp implements DaoServicio {
	
	private static final String ARCHIVO = "servicios.json";
	

	@Override
	public int createServicio(TServicio servicio) {
		
		int id = -1;
		
		String tipo = servicio.getTipo();
		
		JSONObject data = loadData();

		id = data.getInt("proximo id");

		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("nombre", servicio.getNombre());
		json.put("numPlazas", servicio.getNumPlazas());
		json.put("precio", servicio.getPrecio());
		json.put("activo", true);
		
		switch(tipo) {
		
		case "actividad":{
			json.put("tipoActividad", ((TActividad) servicio).getTipoActividad());
			json.put("colectivo", ((TActividad) servicio).isColectivo());
			JSONArray actividades = data.getJSONArray("actividades");
			actividades.put(json);
			break;
		}
		
		case "alojamiento":{
			json.put("regimen", ((TAlojamiento) servicio).getRegimen());
			json.put("estrellas", ((TAlojamiento) servicio).getEstrellas());
			JSONArray alojamientos = data.getJSONArray("alojamientos");
			alojamientos.put(json);
			break;
		}
		
		case "transporte":{
			json.put("tipoTransporte", ((TTransporte) servicio).getTipoTransporte());
			json.put("comida", ((TTransporte) servicio).isComida());
			JSONArray transportes = data.getJSONArray("transportes");
			transportes.put(json);
			break;
		}
		
		default: throw new IllegalArgumentException("tipo no valido");
		}
		
		data.put("proximo id", id + 1);

		if (!saveData(data)) {
			return -1;
		}

		return id;
		
	}

	@Override
	public boolean deleteServicio(int id) {
		JSONObject data = loadData();
		JSONArray actividades = data.getJSONArray("actividades");
		JSONArray transportes = data.getJSONArray("transportes");
		JSONArray alojamientos = data.getJSONArray("alojamientos");

		if ( id >= 0 && id < data.getInt("proximo id")) {
			JSONObject json = null;
			for(int i = 0; i < actividades.length() && json == null; ++i)
				if(actividades.getJSONObject(i).getInt("id") == id)
					json = actividades.getJSONObject(i);
			for(int i = 0; i < transportes.length() && json == null; ++i)
				if(transportes.getJSONObject(i).getInt("id") == id)
					json = transportes.getJSONObject(i);
			for(int i = 0; i < alojamientos.length() && json == null; ++i)
				if(alojamientos.getJSONObject(i).getInt("id") == id)
					json = alojamientos.getJSONObject(i);

			if(!json.getBoolean("activo")) {
				return false;
			}
			json.put("activo", false);
			
			return saveData(data);
		}
		return false;
	}

	@Override
	public boolean updateServicio(TServicio servicio) {
		int id = servicio.getId();
		JSONObject data = loadData();
		JSONArray actividades = data.getJSONArray("actividades");
		JSONArray transportes = data.getJSONArray("transportes");
		JSONArray alojamientos = data.getJSONArray("alojamientos");

		if ( id < 0 || id >= data.getInt("proximo id")) return false;
		
		JSONObject json = null;
		JSONObject nuevo = new JSONObject();
		
		nuevo.put("id", id);
		nuevo.put("nombre", servicio.getNombre());
		nuevo.put("numPlazas", servicio.getNumPlazas());
		nuevo.put("precio", servicio.getPrecio());
		nuevo.put("activo", true);
		
		for(int i = 0; i < actividades.length() && json == null; ++i)
			if(actividades.getJSONObject(i).getInt("id") == id) {
				json = actividades.getJSONObject(i);
				if(!servicio.getTipo().equals("actividad")) return false;
				nuevo.put("tipoActividad", ((TActividad) servicio).getTipoActividad());
				nuevo.put("colectivo", ((TActividad) servicio).isColectivo());
				actividades.put(i, nuevo);
			}
		
		for(int i = 0; i < transportes.length() && json == null; ++i)
			if(transportes.getJSONObject(i).getInt("id") == id) {
				if(!servicio.getTipo().equals("transporte")) return false;
				json = transportes.getJSONObject(i);
				nuevo.put("tipoTransporte", ((TTransporte) servicio).getTipoTransporte());
				nuevo.put("comida", ((TTransporte) servicio).isComida());
				transportes.put(i, nuevo);
			}
		
		for(int i = 0; i < alojamientos.length() && json == null; ++i)
			if(alojamientos.getJSONObject(i).getInt("id") == id) {
				if(!servicio.getTipo().equals("alojamiento")) return false;
				json = alojamientos.getJSONObject(i);
				nuevo.put("regimen", ((TAlojamiento) servicio).getRegimen());
				nuevo.put("estrellas", ((TAlojamiento) servicio).getEstrellas());
				alojamientos.put(i, nuevo);
			}
		
		if(json == null) return false;
		
		return saveData(data);

	}

	@Override
	public TServicio readServicio(int id) {
		JSONObject data = loadData();
		JSONArray actividades = data.getJSONArray("actividades");
		JSONArray transportes = data.getJSONArray("transportes");
		JSONArray alojamientos = data.getJSONArray("alojamientos");
		
		JSONObject json = null;
		TServicio transfer = null;
		if ( id >= 0 && id < data.getInt("proximo id")) {
			for(int i = 0; i < actividades.length() && json == null; ++i)
				if(actividades.getJSONObject(i).getInt("id") == id) {
					json = actividades.getJSONObject(i);
					transfer = new TActividad(
							json.getString("nombre"),
							json.getInt("numPlazas"),
							json.getInt("precio"),
							json.getString("tipoActividad"),
							json.getBoolean("colectivo")
						);
					transfer.setId(json.getInt("id"));
				}
			
			for(int i = 0; i < transportes.length() && json == null; ++i)
				if(transportes.getJSONObject(i).getInt("id") == id) {
					json = transportes.getJSONObject(i);
					transfer = new TTransporte(
							json.getString("nombre"),
							json.getInt("numPlazas"),
							json.getInt("precio"),
							json.getString("tipoTransporte"),
							json.getBoolean("comida")
						);
					transfer.setId(json.getInt("id"));
				}
			
			for(int i = 0; i < alojamientos.length() && json == null; ++i)
				if(alojamientos.getJSONObject(i).getInt("id") == id) {
					json = alojamientos.getJSONObject(i);
					transfer = new TAlojamiento(
							json.getString("nombre"),
							json.getInt("numPlazas"),
							json.getInt("precio"),
							json.getString("regimen"),
							json.getInt("estrellas")
						);
					transfer.setId(json.getInt("id"));
				}

			if(!json.getBoolean("activo")) {
				return null;
			}
		}
		
		return transfer;
	}

	@Override
	public List<TServicio> readAllServicio() {
		List<TServicio> lista = new ArrayList<>();
		JSONObject data = loadData();
		JSONArray actividades = data.getJSONArray("actividades");
		JSONArray transportes = data.getJSONArray("transportes");
		JSONArray alojamientos = data.getJSONArray("alojamientos");
		for(int i = 0; i < actividades.length(); ++i) {
			JSONObject json = actividades.getJSONObject(i);
			if(json.getBoolean("activo")) {
				TServicio transfer = new TActividad(
						json.getString("nombre"),
						json.getInt("numPlazas"),
						json.getInt("precio"),
						json.getString("tipoActividad"),
						json.getBoolean("colectivo")
					);
				transfer.setId(json.getInt("id"));
				lista.add(transfer);
			}
		}
		
		for(int i = 0; i < transportes.length(); ++i) {
			JSONObject json = transportes.getJSONObject(i);
			if(json.getBoolean("activo")) {
				TServicio transfer = new TTransporte(
						json.getString("nombre"),
						json.getInt("numPlazas"),
						json.getInt("precio"),
						json.getString("tipoTransporte"),
						json.getBoolean("comida")
					);
				transfer.setId(json.getInt("id"));
				lista.add(transfer);
			}
		}
		for(int i = 0; i < alojamientos.length(); ++i) {
			JSONObject json = alojamientos.getJSONObject(i);
			if(json.getBoolean("activo")) {
				TServicio transfer = new TAlojamiento(
						json.getString("nombre"),
						json.getInt("numPlazas"),
						json.getInt("precio"),
						json.getString("regimen"),
						json.getInt("estrellas")
					);
				transfer.setId(json.getInt("id"));
				lista.add(transfer);
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
			jsonInput.put("actividades", new JSONArray());
			jsonInput.put("alojamientos", new JSONArray());
			jsonInput.put("transportes", new JSONArray());
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
	
	public static void main(String[] args) {
		DaoServicio d = new DaoServicioImp();
		TActividad puenting = new TActividad("puenting", 10, 100, "de riesgo", false);
		TTransporte madrid_sevilla = new TTransporte("bus madrid - sevilla", 60, 15, "bus", false);
		TAlojamiento riu = new TAlojamiento("Hotel Riu", 5, 70, "todo incluido", 5);
		TActividad karts = new TActividad("karts", 20, 50, "familiar", true);

		d.createServicio(puenting);
		d.createServicio(madrid_sevilla);
		d.createServicio(riu);
		d.createServicio(karts);
//		
		d.deleteServicio(0);
//		d.deleteServicio(100);
//		d.deleteServicio(0);
		
//		karts.setId(puenting.getId());
//		d.updateServicio(karts);
//		
//		riu.setId(karts.getId());
//		d.updateServicio(riu);
		
		d.readAllServicio();
		
	
	}
	
	

}
