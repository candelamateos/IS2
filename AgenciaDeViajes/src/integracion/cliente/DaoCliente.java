package integracion.cliente;

import java.util.List;

import negocio.cliente.TCliente;

public interface DaoCliente {
	
	public boolean createCliente(TCliente cliente);
	
	public boolean updateCliente(TCliente cliente);
	
	public boolean deleteCliente(int id);
	
	public TCliente readCliente(int id);
	
	public List<TCliente> readAllCliente();
	
	
}
