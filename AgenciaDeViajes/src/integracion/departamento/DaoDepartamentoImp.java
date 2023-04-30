package integracion.departamento;

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

import negocio.departamento.TDepartamento;

public class DaoDepartamentoImp implements DaoDepartamento {

	private static final String ARCHIVO = "departamento.json";

	@Override
	public int createDepartamento(TDepartamento departamento) {
		int id = -1;
		JSONObject data = loadData();

		id = data.getInt("proximo id");

		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("nombre", departamento.getNombre());
		json.put("numPlazas", departamento.getNumEmpleados());
		json.put("activo", true);

		JSONArray departamentos = data.getJSONArray("departamentos");
		departamentos.put(json.getInt("id"), json);

		data.put("proximo id", id + 1);

		if (!saveData(data)) {
			return -1;
		}

		return id;
	}

	@Override
	public boolean deleteDepartamento(int id) {
		boolean resultado = false;
		JSONObject data = loadData();
		JSONArray departamentos = data.getJSONArray("departamentos");

		if (id < data.getInt("proximo id")) {
			JSONObject json = departamentos.getJSONObject(id);

			if (!json.has("id") || !json.has("nombre") || !json.has("numEmpleados") || !json.has("activo")) {
				return false;
			}
			if (!json.getBoolean("activo")) {
				return false;
			}
			json.put("activo", false);
			return saveData(data);
		}
		return resultado;
	}

	@Override
	public boolean updateDepartamento(TDepartamento departamento) {
		int id = departamento.getId();

		JSONObject data = loadData();
		JSONArray departamentos = data.getJSONArray("departamentos");

		if (id < data.getInt("proximo id")) {
			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("nombre", departamento.getNombre());
			json.put("numEmpleados", departamento.getNumEmpleados());
			json.put("activo", departamento.getActivo());

			departamentos.put(json.getInt("id"), json);
		}

		return saveData(data);
	}

	@Override
	public TDepartamento readDepartamento(int id) {
		TDepartamento departamento = null;

		JSONObject data = loadData();
		JSONArray departamentos = data.getJSONArray("departamentos");

		if (id < data.getInt("proximo id")) {
			JSONObject json = departamentos.getJSONObject(id);
			departamento = new TDepartamento();
			if (!json.has("id") || !json.has("nombre") || !json.has("numEmpleados") || !json.has("activo")) {
				return null;
			}
			if (!json.getBoolean("activo")) {
				return null;
			}
			departamento.setId(json.getInt("id"));
			departamento.setNombre(json.getString("nombre"));
			departamento.setNumEmpleados(json.getInt("numEmpleados"));
			departamento.setActivo(json.getBoolean("activo"));
		}
		return departamento;
	}

	@Override
	public List<TDepartamento> readAllDepartamento() {
		List<TDepartamento> lista = new ArrayList<>();

		JSONObject data = loadData();
		JSONArray departamentos = data.getJSONArray("departamentos");

		for (int i = 0; i < data.getInt("proximo id"); i++) {
			JSONObject json = departamentos.getJSONObject(i);
			if (!json.has("id") || !json.has("precio") || !json.has("numEmpleados") || !json.has("activo")) {
				return null;
			}
			if (json.getBoolean("activo")) {
				TDepartamento departamento = new TDepartamento();
				departamento.setId(json.getInt("id"));
				departamento.setNombre(json.getString("nombre"));
				departamento.setNumEmpleados(json.getInt("numEmpleados"));
				departamento.setActivo(json.getBoolean("activo"));

				lista.add(departamento);
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
			jsonInput.put("departamentos", new JSONArray());
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
