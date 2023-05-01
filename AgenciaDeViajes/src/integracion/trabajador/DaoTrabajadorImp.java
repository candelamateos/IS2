package integracion.trabajador;

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

import negocio.trabajador.TTrabajador;
import negocio.trabajador.TVendedor;

public class DaoTrabajadorImp implements DaoTrabajador{
	
	private static final String ARCHIVO = "trabajadores.json";

	@Override
	public int createTrabajador(TTrabajador trabajador) {
		int id = -1;
		JSONObject data = loadData();
		
		id = data.getInt("proximo id");
		
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("nombre", trabajador.getNombre());
		json.put("activo", true);
		json.put("sueldo", trabajador.getSueldo());
		json.put("idDepart", trabajador.getIdDepart());
		json.put("tipo", trabajador.getTipo());
		
		if (trabajador.getTipo().equals("vendedor")) {
			TVendedor aux = (TVendedor) trabajador;
			json.put("idJefe", aux.getIdJefe());
		}
		
		JSONArray trabajadores = data.getJSONArray("trabajadores");
		trabajadores.put(json.getInt("id"), json);
		
		data.put("proximo id", id + 1);
		
		if (!saveData(data))
			return -1;
		
		return id;
	}

	@Override
	public boolean updateTrabajador(TTrabajador trabajador) {
		int id = trabajador.getId();
		
		JSONObject data = loadData();
		JSONArray trabajadores = data.getJSONArray("trabajadores");
		
		if (id >= 0 && id < data.getInt("proximo id")) {
			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("nombre", trabajador.getNombre());
			json.put("activo", true);
			json.put("sueldo", trabajador.getSueldo());
			json.put("tipo", trabajador.getTipo());
			json.put("idDepart", trabajador.getIdDepart());
			
			if (trabajador.getTipo().equals("vendedor")) {
				TVendedor aux = (TVendedor) trabajador;
				json.put("idJefe", aux.getIdJefe());
			}
			
			trabajadores.put(json.getInt("id"), json);
		}
		
		return saveData(data);
	}

	@Override
	public boolean deleteTrabajador(int id) {
		JSONObject data = loadData();
		JSONArray trabajadores = data.getJSONArray("trabajadores");

		if (id >= 0 && id < data.getInt("proximo id")) {
			JSONObject json = trabajadores.getJSONObject(id);
			
			if (!json.has("id") || !json.has("nombre") || !json.has("activo")
					|| !json.has("sueldo") || !json.has("tipo") || !json.has("idDepart")) {
				return false;
			}else {
				if (json.getString("tipo").equals("vendedor") && !json.has("idJefe")) {
					return false;
				}
			}
			
			if(!json.getBoolean("activo")) {
				return false;
			}
			json.put("activo", false);
			
			return saveData(data);
		}
		return false;
	}

	@Override
	public TTrabajador readTrabajador(int id) {
		TTrabajador transfer = null;
		
		JSONObject data = loadData();
		JSONArray trabajadores = data.getJSONArray("trabajadores");
		
		if (id >= 0 && id < data.getInt("proximo id")) {
			JSONObject json = data.getJSONObject("id");
			transfer = new TTrabajador();
			
			if (!json.has("id") || !json.has("nombre") || !json.has("activo")
					|| !json.has("sueldo") || !json.has("tipo")) {
				return null;
			}
			else {
				if (json.getString("tipo").equals("jefe")) {
					transfer.setId(json.getInt("id"));
					transfer.setNombre(json.getString("nombre"));
					transfer.setActivo(json.getBoolean("activo"));
					transfer.setSueldo(json.getInt("sueldo"));
					transfer.setTipo(json.getString("tipo"));
				}
				else {
					if (!json.has("idJefe")) {
						return null;
					}
					else {
						transfer.setId(json.getInt("id"));
						transfer.setNombre(json.getString("nombre"));
						transfer.setActivo(json.getBoolean("activo"));
						transfer.setSueldo(json.getInt("sueldo"));
						transfer.setTipo(json.getString("tipo"));
						((TVendedor) transfer).setIdJefe(json.getInt("idJefe"));
					}
				}
			}
		}
		
		return transfer;
	}

	@Override
	public List<TTrabajador> readAllTrabajador() {
		List<TTrabajador> lista = new ArrayList<>();
		
		JSONObject data = loadData();
		JSONArray trabajadores = data.getJSONArray("trabajadores");
		
		for (int i = 0; i < data.getInt("proximo id"); i++) {
			TTrabajador transfer = readTrabajador(trabajadores.getJSONObject(i).getInt("id"));
			lista.add(transfer);
		}
		
		return lista;
	}
	
	private JSONObject loadData() {
		InputStream input;
		JSONObject jsonInput;
		try {
			input = new FileInputStream(new File(ARCHIVO));
			jsonInput = new JSONObject(new JSONTokener(input));
		}catch (FileNotFoundException e){
			jsonInput = new JSONObject();
			jsonInput.put("proximo id", 0);
			jsonInput.put("trabajadores", new JSONArray());
		}
		
		return jsonInput;
	}
	
	private boolean saveData (JSONObject data) {
		try (OutputStream output = new FileOutputStream(new File (ARCHIVO))){
			PrintStream print = new PrintStream(output);
			print.print(data);
		}catch (IOException e) {
			return false;
		}

		return true;
	}

}
