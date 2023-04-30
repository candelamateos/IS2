package negocio.cliente;

import java.util.List;

import integracion.cliente.DaoCliente;
import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.viaje.DaoViaje;
import negocio.viaje.TViaje;

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
		boolean update = false;
		DaoCliente d = FactoriaAbstractaIntegracion.getInstancia().crearDaoCliente();
		
		if(Cliente!= null) {
			TCliente existe = d.readCliente(Cliente.getId());
			if(existe != null) {
				update = d.updateCliente(Cliente);
			}
		}
		return update;
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
		TCliente cliente = null;
		DaoCliente d = FactoriaAbstractaIntegracion.getInstancia().crearDaoCliente();
		
		if(id != -1) {
			cliente = d.readCliente(id);
		}
		return cliente;
	}

	@Override
	public List<TCliente> readAllCliente() {
		DaoCliente d = FactoriaAbstractaIntegracion.getInstancia().crearDaoCliente();
		return d.readAllCliente();
	}

}
