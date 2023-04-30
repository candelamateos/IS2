package negocio.cliente;

import java.util.List;

import integracion.cliente.DaoCliente;
import integracion.factoria.FactoriaAbstractaIntegracion;

public class SAClienteImp implements SACliente {

	@Override
	public int createCliente(TCliente Cliente) {
		int id = -1;
		DaoCliente d = FactoriaAbstractaIntegracion.getInstancia().crearDaoCliente();
		String nombre = Cliente.getNombre();
		if (nombre != null && !nombre.equals("")) {
			return d.createCliente(Cliente);
		} else
			return -1;
	}

	@Override
	public boolean updateCliente(TCliente Cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCliente(int id) {
		DaoCliente d = FactoriaAbstractaIntegracion.getInstancia().crearDaoCliente();
		if (id != 0) {
			return d.deleteCliente(id);
		}
		else {
			return false;
		}
	}

	@Override
	public TCliente readCliente(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TCliente> readAllCliente() {
		// TODO Auto-generated method stub
		return null;
	}

}
