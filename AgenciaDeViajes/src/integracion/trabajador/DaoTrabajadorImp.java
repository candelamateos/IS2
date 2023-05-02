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

import integracion.departamento.DaoDepartamento;
import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.servicio.DaoServicio;
import integracion.servicio.DaoServicioImp;
import negocio.departamento.TDepartamento;
import negocio.servicio.TActividad;
import negocio.servicio.TAlojamiento;
import negocio.servicio.TServicio;
import negocio.servicio.TTransporte;
import negocio.trabajador.TJefe;
import negocio.trabajador.TTrabajador;
import negocio.trabajador.TVendedor;

public class DaoTrabajadorImp implements DaoTrabajador {

	private static final String ARCHIVO = "trabajadores.json";

	@Override
	public int createTrabajador(TTrabajador trabajador) {

		int id = -1;

		String tipo = trabajador.getTipo();

		JSONObject data = loadData();

		id = data.getInt("proximo id");

		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("nombre", trabajador.getNombre());
		json.put("sueldo", trabajador.getSueldo());
		json.put("idDepart", trabajador.getIdDepart());
		json.put("activo", true);

		switch (tipo) {

		case "vendedor": {
			json.put("idJefe", ((TVendedor) trabajador).getIdJefe());
			JSONArray vendedores = data.getJSONArray("vendedores");
			vendedores.put(json);
			break;
		}

		case "jefe": {
			JSONArray jefes = data.getJSONArray("jefes");
			jefes.put(json);
			break;
		}

		default:
			throw new IllegalArgumentException("tipo no valido");
		}

		data.put("proximo id", id + 1);

		if (!saveData(data)) {
			return -1;
		}

		return id;
	}

	@Override
	public boolean updateTrabajador(TTrabajador trabajador) {
		int id = trabajador.getId();
		JSONObject data = loadData();
		JSONArray vendedores = data.getJSONArray("vendedores");
		JSONArray jefes = data.getJSONArray("jefes");

		if (id < 0 || id >= data.getInt("proximo id"))
			return false;

		JSONObject json = null;
		JSONObject nuevo = new JSONObject();

		nuevo.put("id", id);
		nuevo.put("nombre", trabajador.getNombre());
		nuevo.put("sueldo", trabajador.getSueldo());
		nuevo.put("idDepart", trabajador.getIdDepart());
		nuevo.put("activo", true);

		for (int i = 0; i < vendedores.length() && json == null; ++i)
			if (vendedores.getJSONObject(i).getInt("id") == id) {
				json = vendedores.getJSONObject(i);
				if (!trabajador.getTipo().equals("vendedor"))
					return false;
				nuevo.put("idJefe", ((TVendedor) trabajador).getIdJefe());
				vendedores.put(i, nuevo);
			}

		for (int i = 0; i < jefes.length() && json == null; ++i)
			if (jefes.getJSONObject(i).getInt("id") == id) {
				if (!trabajador.getTipo().equals("jefe"))
					return false;
				json = jefes.getJSONObject(i);
				jefes.put(i, nuevo);
			}

		if (json == null)
			return false;
		
		DaoDepartamento d = FactoriaAbstractaIntegracion.getInstancia().crearDaoDepartamento();
		TDepartamento departamento = d.readDepartamento(trabajador.getIdDepart());
		departamento.setNumEmpleados(departamento.getNumEmpleados() - 1);
		
		DaoDepartamento f = FactoriaAbstractaIntegracion.getInstancia().crearDaoDepartamento();
		TDepartamento departamento2 = f.readDepartamento(nuevo.getInt("idDepart"));
		departamento2.setNumEmpleados(departamento2.getNumEmpleados() + 1);
		

		return saveData(data);
	}

	@Override
	public boolean deleteTrabajador(int id) {
		JSONObject data = loadData();
		JSONArray vendedores = data.getJSONArray("vendedores");
		JSONArray jefes = data.getJSONArray("jefes");

		if (id >= 0 && id < data.getInt("proximo id")) {
			JSONObject json = null;
			for (int i = 0; i < vendedores.length() && json == null; ++i)
				if (vendedores.getJSONObject(i).getInt("id") == id)
					json = vendedores.getJSONObject(i);
			for (int i = 0; i < jefes.length() && json == null; ++i)
				if (jefes.getJSONObject(i).getInt("id") == id)
					json = jefes.getJSONObject(i);

			if (!json.getBoolean("activo")) {
				return false;
			}
			json.put("activo", false);

			return saveData(data);
		}
		return false;
	}

	@Override
	public TTrabajador readTrabajador(int id) {
		JSONObject data = loadData();
		JSONArray vendedores = data.getJSONArray("vendedores");
		JSONArray jefes = data.getJSONArray("jefes");

		JSONObject json = null;
		TTrabajador transfer = null;
		if (id >= 0 && id < data.getInt("proximo id")) {
			for (int i = 0; i < vendedores.length() && json == null; ++i)
				if (vendedores.getJSONObject(i).getInt("id") == id) {
					json = vendedores.getJSONObject(i);
					transfer = new TVendedor(json.getString("nombre"), json.getInt("sueldo"), json.getInt("idDepart"),
							json.getInt("idJefe"));
					transfer.setId(json.getInt("id"));
				}

			for (int i = 0; i < jefes.length() && json == null; ++i)
				if (jefes.getJSONObject(i).getInt("id") == id) {
					json = jefes.getJSONObject(i);
					transfer = new TJefe(json.getString("nombre"), json.getInt("sueldo"), json.getInt("idDepart"));
					transfer.setId(json.getInt("id"));
				}

			if (!json.getBoolean("activo")) {
				return null;
			}
		}

		return transfer;
	}

	@Override
	public List<TTrabajador> readAllTrabajador() {
		List<TTrabajador> lista = new ArrayList<>();
		JSONObject data = loadData();
		JSONArray vendedores = data.getJSONArray("vendedores");
		JSONArray jefes = data.getJSONArray("jefes");

		for (int i = 0; i < vendedores.length(); ++i) {
			JSONObject json = vendedores.getJSONObject(i);
			if (json.getBoolean("activo")) {
				TTrabajador transfer = new TVendedor(json.getString("nombre"), json.getInt("sueldo"),
						json.getInt("idDepart"), json.getInt("idJefe")

				);
				transfer.setId(json.getInt("id"));
				lista.add(transfer);
			}
		}

		for (int i = 0; i < jefes.length(); ++i) {
			JSONObject json = jefes.getJSONObject(i);
			if (json.getBoolean("activo")) {
				TTrabajador transfer = new TJefe(json.getString("nombre"), json.getInt("sueldo"),
						json.getInt("idDepart"));
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
			jsonInput.put("vendedores", new JSONArray());
			jsonInput.put("jefes", new JSONArray());
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
		DaoTrabajador d = new DaoTrabajadorImp();
		TVendedor vendedor1 = new TVendedor("Fernando", 600, 6, 5);
		TJefe jefe = new TJefe("Candelarie", 1500, 5);
		TVendedor vendedor2 = new TVendedor("Jaimixu", 550, 11, 4);
		
		d.createTrabajador(vendedor1);
		d.createTrabajador(vendedor2);
		d.createTrabajador(jefe);
//		
	
//		d.deleteServicio(100);
//		d.deleteServicio(0);
		
//		karts.setId(puenting.getId());
//		d.updateServicio(karts);
//		
//		riu.setId(karts.getId());
//		d.updateServicio(riu);
		
		
		
	
	}
	

}
