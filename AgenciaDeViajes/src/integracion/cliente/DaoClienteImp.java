package integracion.cliente;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
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
		
		// TODO Auto-generated method stub
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
			jsonInput.put("Ultimo id", 0);
			jsonInput.put("Clientes", new JSONArray());
		}
		
		return jsonInput;
	}

}
