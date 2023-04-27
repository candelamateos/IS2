package integracion.cliente;

import java.util.List;

import negocio.cliente.TCliente;

public interface DaoCliente {
	
public int createCliente(TCliente cliente);
	
	public int updateCliente(TCliente cliente);
	
	public boolean deleteCliente(int id);
	
	public TCliente readCliente(TCliente cliente);
	
	public List<TCliente> readAllCliente(TCliente cliente);
	
	
}
