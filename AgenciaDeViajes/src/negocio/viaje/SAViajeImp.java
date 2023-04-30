package negocio.viaje;

import java.util.List;

import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.viaje.DaoViaje;

public class SAViajeImp implements SAViaje{

	@Override
	public int createViaje(TViaje Viaje) {
		int id = -1;
		DaoViaje d = FactoriaAbstractaIntegracion.getInstancia().crearDaoViaje();
		
		if(Viaje != null) {
			TViaje existe = d.readViaje(Viaje.getId());
			if(existe == null) {
				id = d.createViaje(Viaje);
			}
		}
		return id;
	}

	@Override
	public boolean updateViaje(TViaje Viaje) {
		boolean update = false;
		DaoViaje d = FactoriaAbstractaIntegracion.getInstancia().crearDaoViaje();
		
		if(Viaje != null) {
			TViaje existe = d.readViaje(Viaje.getId());
			if(existe != null) {
				update = d.updateViaje(Viaje);
			}
		}
		return update;
	}

	@Override
	public boolean deleteViaje(int id) {
		boolean delete = false;
		DaoViaje d = FactoriaAbstractaIntegracion.getInstancia().crearDaoViaje();
		
		if(id != -1) {
			TViaje existe = d.readViaje(id);
			if(existe != null) {
				delete = d.deleteViaje(id);
			}
		}
		return delete;
	}

	@Override
	public TViaje readViaje(int id) {
		TViaje viaje = null;
		DaoViaje d = FactoriaAbstractaIntegracion.getInstancia().crearDaoViaje();
		
		if(id != -1) {
			viaje = d.readViaje(id);
		}
		return viaje;
	}

	@Override
	public List<TViaje> readAllViaje() {
		DaoViaje d = FactoriaAbstractaIntegracion.getInstancia().crearDaoViaje();
		return d.readAllViaje();
	}

}
