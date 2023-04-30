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
		
		// me piden el identificador del trabajador
		id = data.getInt("proximo id");
		
		// ingresamos los datos en un JSONObject para un trabajador
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("nombre", trabajador.getNombre());
		json.put("activo", true);
		json.put("sueldo", trabajador.getSueldo());
		json.put("tipo", trabajador.getTipo());
		
		// comprobamos si se trata de un vendedor para asociarle el atributo idJefe
		if (trabajador.getTipo().equals("vendedor")) {
			TVendedor aux = (TVendedor) trabajador;
			json.put("idJefe", aux.getIdJefe());
		}
		
		// ahora rellenamos con la informaci�n de cada trabajador de la lista
		JSONArray trabajadores = data.getJSONArray("trabajadores");
		trabajadores.put(json.getInt("id"), json);
		
		// actualizamos el valor del pr�ximo id de la lista
		data.put("proximo id", id + 1);
		
		// comprobamos que se hayan podido leer los datos
		if (!saveData(data))
			return -1;
		
		return id;
	}

	@Override
	public boolean updateTrabajador(TTrabajador trabajador) {
		int id = trabajador.getId();
		
		// creamos la lista de trabajadores
		JSONObject data = loadData();
		JSONArray trabajadores = data.getJSONArray("trabajadores");
		
		if (id < data.getInt("proximo id")) {
			// actualizamos el trabajador con la nueva info
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
			
			// metemos el json en la lista
			trabajadores.put(json.getInt("id"), json);
		}
		
		return saveData(data);
	}

	@Override
	public boolean deleteTrabajador(int id) {
		boolean resultado = false;
		JSONObject data = loadData();
		JSONArray trabajadores = data.getJSONArray("trabajadores");

		if (id < data.getInt("proximo id")) {
			JSONObject json = trabajadores.getJSONObject(id);
			
			if (!json.has("id") || !json.has("nombre") || !json.has("activo")
					|| !json.has("sueldo") || !json.has("tipo") || !json.has("idDepart")) {
				return false;
			}
			// en caso de estar todos los campos, comprobamos si est� el de idJefe
			// cuando el tipo es vendedor
			else {
				if (json.getString("tipo").equals("vendedor") && !json.has("idJefe")) {
					return false;
				}
			}
			// ya estaba eliminado
			if(!json.getBoolean("activo")) {
				return false;
			}
			json.put("activo", false);
			
			return saveData(data);
		}
		return resultado;
	}

	@Override
	public TTrabajador readTrabajador(int id) {
		TTrabajador transfer = null;
		
		// creamos el array de trabajadores
		JSONObject data = loadData();
		JSONArray trabajadores = data.getJSONArray("trabajadores");
		
		// comprobamos que el trabajador se encuentra en la lista
		if (id < data.getInt("proximo id")) {
			JSONObject json = data.getJSONObject("id");
			
			// transfer al que le pasaremos los datos
			transfer = new TTrabajador();
			
			if (!json.has("id") || !json.has("nombre") || !json.has("activo")
					|| !json.has("sueldo") || !json.has("tipo")) {
				return null;
			}
			else {
				// si es jefe le pasamos los datos con normalidad
				if (json.getString("tipo").equals("jefe")) {
					transfer.setId(json.getInt("id"));
					transfer.setNombre(json.getString("nombre"));
					transfer.setActivo(json.getBoolean("activo"));
					transfer.setSueldo(json.getInt("sueldo"));
					transfer.setTipo(json.getString("tipo"));
				}
				// si es vendedor...
				else {
					// ... miramos si tiene idJefe como atributo 
					if (!json.has("idJefe")) {
						return null;
					}
					else {
						transfer.setId(json.getInt("id"));
						transfer.setNombre(json.getString("nombre"));
						transfer.setActivo(json.getBoolean("activo"));
						transfer.setSueldo(json.getInt("sueldo"));
						transfer.setTipo(json.getString("tipo"));
						
						transfer.setIdJefe(json.getInt("idJefe"));
					}
				}
			}
		}
		
		return transfer;
	}

	@Override
	public List<TTrabajador> readAllTrabajador(TTrabajador trabajador) {
		List<TTrabajador> lista = new ArrayList<>();
		
		JSONObject data = loadData();
		JSONArray trabajadores = data.getJSONArray("trabajadores");
		
		// recorremos cada uno de los trabajadores de la lista
		// y hacemos sus correspondientes 
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
