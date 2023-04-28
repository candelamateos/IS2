package integracion.viaje;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

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
	public boolean deleteViaje(int id) {
		return false;
	}

	@Override
	public boolean updateViaje(TViaje viaje) {
		return false;
	}

	@Override
	public TViaje readViaje(int id) {
		return null;
	}

	@Override
	public List<TViaje> readAllViaje() {
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

}
