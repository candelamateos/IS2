package integracion.cliente;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
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

		JSONObject clientes = data.getJSONObject("clientes");
		clientes.put(json.get("id").toString(), json);

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
		JSONObject clientes = data.getJSONObject("clientes");

		if (clientes.has(Integer.toString(id))) {
			JSONObject json = new JSONObject();
		}
	}

	@Override
	public boolean deleteCliente(int id) {
		boolean resultado = true;
		JSONObject data = loadData();
		JSONObject clientes = data.getJSONObject("clientes");

		if (clientes.has(Integer.toString(id))) {
			clientes.remove(Integer.toString(id));
			resultado = saveData(data);
		} else
			resultado = false;
		return resultado;
	}

	@Override
	public TCliente readCliente(int id) {
		TCliente cliente = null;

		JSONObject data = loadData();
		JSONObject clientes = data.getJSONObject("clientes");

		if (clientes.has(Integer.toString(id))) {
			JSONObject json = clientes.getJSONObject(Integer.toString(id));
			cliente = new TCliente();
			if (!json.has("id") || !json.has("nombre") || !json.has("activo")) {
				return null;
			}
			cliente.setId(json.getInt("id"));
			cliente.setNombre(json.getString("nombre"));
			cliente.setActivo(json.getBoolean("activo"));
		}
		return cliente;
	}

	@Override
	public List<TCliente> readAllCliente(TCliente cliente) {
		// TODO Auto-generated method stub
		return null;
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
			jsonInput.put("clientes", new JSONObject());
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
		TCliente c = new TCliente("ANTONIO");
		DaoCliente d = new DaoClienteImp();
		d.createCliente(c);
		d.createCliente(c);
		TCliente e = d.readCliente(3);
		if (e == null) {
			System.out.println("Error");
		} else {
			System.out.println(e.getId() + " " + e.getNombre() + " " + e.getActivo());
		}
	}

}
