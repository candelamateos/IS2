package integracion.cliente;

import java.util.List;

import org.json.JSONObject;

import negocio.cliente.TCliente;



public class DaoClienteImp implements DaoCliente {

	@Override
	public int createCliente(TCliente cliente) {
		int id = -1;
		JSONObject json = new JSONObject();
		
		json.put("nombre", cliente.getNombre());
		json.put("activo", cliente.getActivo());
		
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

}
