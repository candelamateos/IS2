package negocio.cliente;

import java.util.List;


public interface SACliente {
	
	public int createCliente(TCliente Cliente);
	
	public boolean updateCliente(TCliente Cliente);
	
	public boolean deleteCliente(int id);
	
	public TCliente readCliente(int id);
	
	public List<TCliente> readAllCliente();
}
