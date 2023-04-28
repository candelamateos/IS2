package negocio.cliente;

import java.util.List;


public interface SACliente {
	
	public int createCliente(TCliente Cliente);
	
	public int updateCliente(TCliente Cliente);
	
	public int deleteCliente(TCliente Cliente);
	
	public TCliente readCliente(TCliente Cliente);
	
	public List<TCliente> readAllCliente(TCliente Cliente);
}
