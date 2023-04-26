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
		JSONObject json = new JSONObject();
		
		json.put("nombre", cliente.getNombre());
		json.put("activo", true);
		
		JSONObject data = loadData();
		JSONObject clientes = data.getJSONObject("clientes");
		
		id = data.getInt("proximo id");
		json.put("id", id);
		
		clientes.put(json.get("id").toString(), json);
		data.put("proximo id", id + 1);
		
		saveData(data);
		
		return id;
	}

	@Override
	public int updateCliente(TCliente cliente) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCliente(TCliente cliente) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TCliente readCliente(TCliente cliente) {
		// TODO Auto-generated method stub
		return null;
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
		try(OutputStream output = new FileOutputStream(new File(ARCHIVO))){
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
		TCliente c = new TCliente(1, "PEPE");
		DaoCliente d = new DaoClienteImp();
		d.createCliente(c);
	}

}
