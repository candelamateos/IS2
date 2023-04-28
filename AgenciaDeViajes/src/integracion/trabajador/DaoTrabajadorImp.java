package integracion.trabajador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;

import negocio.trabajador.TTrabajador;

public class DaoTrabajadorImp implements DaoTrabajador{
	
	private static final String ARCHIVO = "trabajadores.json";

	@Override
	public int createTrabajador(TTrabajador trabajo) {
		
		return 0;
	}

	@Override
	public int updateTrabajador(TTrabajador trabajo) {
		return 0;
	}

	@Override
	public boolean deleteTrabajador(int id) {
		return false;
	}

	@Override
	public TTrabajador readTrabajador(int id) {
		return null;
	}

	@Override
	public List<TTrabajador> readAllTrabajador(TTrabajador trabajo) {
		return null;
	}
	
	private JSONObject loadData() {
		InputStream input;
		JSONObject jsonInput;
		
		try {
			input = new FileInputStream(new File(ARCHIVO));
			jsonInput = new JSONObject(new JSONTokener(input));
		}catch (FileNotFoundException e){
			jsonInput = new JSONObject();
			jsonInput.put()
			
		}
	}

}
