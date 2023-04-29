package integracion.cliente;

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

public class DaoClienteImp implements DaoCliente {

	private static final String ARCHIVO = "clientes.json";

	@Override
	public int createCliente(TCliente cliente) {
		int id = -1;
		JSONObject data = loadData();

		id = data.getInt("proximo id");

		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("nombre", cliente.getNombre());
		json.put("activo", true);

		JSONArray clientes = data.getJSONArray("clientes");
		clientes.put(json.getInt("id"), json);

		data.put("proximo id", id + 1);

		if (!saveData(data)) {
			return -1;
		}

		return id;
	}

	@Override
	public boolean updateCliente(TCliente cliente) {
		int id = cliente.getId();
		
		JSONObject data = loadData();
		JSONArray clientes = data.getJSONArray("clientes");
		
		if (id < data.getInt("proximo id")) {
			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("nombre", cliente.getNombre());
			json.put("activo", cliente.getActivo());
			
			clientes.put(json.getInt("id"), json);
		}
		
		return saveData(data);
	}

	@Override
	public boolean deleteCliente(int id) {
		boolean resultado = false;
		JSONObject data = loadData();
		JSONArray clientes = data.getJSONArray("clientes");

		if (id < data.getInt("proximo id")) {
			JSONObject json = clientes.getJSONObject(id);
			
			if (!json.has("id") || !json.has("nombre") || !json.has("activo")) {
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
	public TCliente readCliente(int id) {
		TCliente cliente = null;

		JSONObject data = loadData();
		JSONArray clientes = data.getJSONArray("clientes");

		if (id < data.getInt("proximo id") ) {
			JSONObject json = clientes.getJSONObject(id);
			cliente = new TCliente();
			if (!json.has("id") || !json.has("nombre") || !json.has("activo")) {
				return null;
			}
			if(!json.getBoolean("activo")) {
				return null;
			}
			cliente.setId(json.getInt("id"));
			cliente.setNombre(json.getString("nombre"));
			cliente.setActivo(json.getBoolean("activo"));
		}
		return cliente;
	}

	@Override
	public List<TCliente> readAllCliente() {
		List<TCliente> lista = new ArrayList<>();
		
		JSONObject data = loadData();
		JSONArray clientes = data.getJSONArray("clientes");
		
		for(int i = 0; i < data.getInt("proximo id"); i++) {
			JSONObject json = clientes.getJSONObject(i);
			if (!json.has("id") || !json.has("nombre") || !json.has("activo")) {
				return null;
			}
			if(json.getBoolean("activo")) {
				TCliente cliente = new TCliente();
				cliente.setId(json.getInt("id"));
				cliente.setNombre(json.getString("nombre"));
				cliente.setActivo(json.getBoolean("activo"));
				
				lista.add(cliente);
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
			jsonInput.put("clientes", new JSONArray());
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

	//TODO eliminar esto antes de entregar, es solo para probar el codigo
	public static void main(String[] args) {
		DaoCliente d = new DaoClienteImp();
		TCliente miguel = new TCliente("Miguel");
		TCliente anto = new TCliente("Antonio");
		TCliente jose = new TCliente("Jose");
		TCliente rodr = new TCliente("Rodrigo");
		d.createCliente(miguel);
		d.createCliente(anto);
		d.createCliente(jose);
		d.createCliente(rodr);
		
		println(d.readCliente(2)); //jose
		
		jose = d.readCliente(2);
		jose.setNombre("José");
		d.updateCliente(jose);
		println(d.readCliente(2));//José con tilde
		
		d.deleteCliente(3); //Eliminamos Rodr
		println(d.readCliente(3));//error
		
		d.readAllCliente();
		for(TCliente c : d.readAllCliente()) {
			println(c);
		}
	}
	
	private static void println(TCliente f) {
		if (f == null) {
			System.out.println("Error");
		} else {
			System.out.println(f.getId() + " " + f.getNombre() + " " + f.getActivo());
		}
	}

}
