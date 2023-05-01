package integracion.factura;

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

import negocio.factura.TFactura;
import negocio.factura.TLineaFactura;

public class DaoFacturaImp implements DaoFactura {

	private static final String ARCHIVO = "facturas.json";

	@Override
	public int abrirFactura(TFactura factura) {
		int id = -1;

		JSONObject data = loadData();
		JSONArray facturas = data.getJSONArray("facturas");
		id = data.getInt("proximo id factura");

		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("coste", 0);
		json.put("idVendedor", factura.getIdVendedor());
		json.put("idCliente", factura.getIdCliente());
		json.put("abierta", true);
		json.put("activo", true);

		facturas.put(json);
		data.put("proximo id factura", id + 1);

		if (!saveData(data)) {
			return -1;
		}

		return id;
	}

	@Override
	public TFactura readFactura(int id) {
		TFactura f = null;

		JSONObject data = loadData();
		JSONArray facturas = data.getJSONArray("facturas");

		if (id < data.getInt("proximo id factura")) {
			JSONObject json = facturas.getJSONObject(id);
			if (!json.has("id") || !json.has("coste") || !json.has("idCliente") || !json.has("idVendedor")
					|| !json.has("abierta") || !json.has("activo")) {
				return null;
			}
			if (!json.getBoolean("activo")) {
				return null;
			}
			f = new TFactura();
			f.setId(json.getInt("id"));
			f.setCoste(json.getInt("coste"));
			f.setIdCliente(json.getInt("idCliente"));
			f.setIdVendedor(json.getInt("idVendedor"));
			f.setAbierta(json.getBoolean("abierta"));
			f.setActivo(json.getBoolean("activo"));
		}
		return f;
	}

	@Override
	public List<TFactura> readAllFactura() {
		List<TFactura> lista = new ArrayList<>();

		JSONObject data = loadData();
		JSONArray facturas = data.getJSONArray("facturas");

		for (int i = 0; i < data.getInt("proximo id factura"); i++) {
			JSONObject json = facturas.getJSONObject(i);
			if (!json.has("id") || !json.has("coste") || !json.has("idCliente") || !json.has("idVendedor")
					|| !json.has("abierta") || !json.has("activo")) {
				return null;
			}
			if (json.getBoolean("activo")) {
				TFactura f = new TFactura();
				f.setId(json.getInt("id"));
				f.setCoste(json.getInt("coste"));
				f.setIdCliente(json.getInt("idCliente"));
				f.setIdVendedor(json.getInt("idVendedor"));
				f.setAbierta(json.getBoolean("abierta"));
				f.setActivo(json.getBoolean("activo"));

				lista.add(f);
			}
		}
		return lista;
	}

	@Override
	public List<TLineaFactura> readAllLineaFactura() {
		List<TLineaFactura> lista = new ArrayList<>();

		JSONObject data = loadData();
		JSONArray facturas = data.getJSONArray("lineasFactura");

		for (int i = 0; i < data.getInt("proximo id lineaFactura"); i++) {
			JSONObject json = facturas.getJSONObject(i);
			if (!json.has("id") || !json.has("coste") || !json.has("plazasVendidas") || !json.has("idFactura")
					|| !json.has("idViaje") || !json.has("activo")) {
				return null;
			}
			if (json.getBoolean("activo")) {
				TLineaFactura f = new TLineaFactura();
				f.setId(json.getInt("id"));
				f.setCoste(json.getInt("coste"));
				f.setPlazasVendidas(json.getInt("plazasVendidas"));
				f.setIdFactura(json.getInt("idFactura"));
				f.setIdViaje(json.getInt("idViaje"));
				f.setActivo(json.getBoolean("activo"));

				lista.add(f);
			}
		}
		return lista;
	}

	@Override
	public int addViaje(TLineaFactura lfactura) {
		int id = -1;

		JSONObject data = loadData();
		JSONArray lineas = data.getJSONArray("lineasFactura");
		id = data.getInt("proximo id lineaFactura");

		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("coste", lfactura.getCoste());
		json.put("plazasVendidas", lfactura.getPlazasVendidas());
		json.put("idFactura", lfactura.getIdFactura());
		json.put("idViaje", lfactura.getIdViaje());
		json.put("activo", true);

		lineas.put(json);
		data.put("proximo id lineaFactura", id + 1);

		if (!saveData(data)) {
			return -1;
		}

		return id;
	}

	@Override
	public boolean updateFactura(TFactura factura) {
		int id = factura.getId();

		JSONObject data = loadData();
		JSONArray facturas = data.getJSONArray("facturas");

		if (id < data.getInt("proximo id factura")) {
			JSONObject json = new JSONObject();
			json.put("id", id);
			json.put("coste", factura.getCoste());
			json.put("idVendedor", factura.getIdVendedor());
			json.put("idCliente", factura.getIdCliente());
			json.put("abierta", factura.isAbierta());
			json.put("activo", factura.isActivo());

			facturas.put(json.getInt("id"), json);
			
			return saveData(data);
		}
		return false;
	}

	@Override
	public boolean cerrarFactura(int id) {
		JSONObject data = loadData();
		JSONArray facturas = data.getJSONArray("facturas");
		
		if(id < data.getInt("proximo id factura")) {
			JSONObject json = facturas.getJSONObject(id);
			json.put("abierta", false);
			
			return saveData(data);
		}
		return false;
	}

	private JSONObject loadData() {
		InputStream input;
		JSONObject jsonInput;
		try {
			input = new FileInputStream(new File(ARCHIVO));
			jsonInput = new JSONObject(new JSONTokener(input));
		} catch (FileNotFoundException e) {
			jsonInput = new JSONObject();
			jsonInput.put("proximo id factura", 0);
			jsonInput.put("facturas", new JSONArray());
			jsonInput.put("proximo id lineaFactura", 0);
			jsonInput.put("lineasFactura", new JSONArray());
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
		DaoFactura d= new DaoFacturaImp();
		d.readAllLineaFactura();
	}
}
