package negocio.viaje;

import java.util.ArrayList;
import java.util.List;

import integracion.cliente.DaoCliente;
import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.factura.DaoFactura;
import integracion.trabajador.DaoTrabajador;
import integracion.viaje.DaoViaje;
import negocio.cliente.TCliente;
import negocio.trabajador.TTrabajador;

public class SAViajeImp implements SAViaje{
	
	private boolean comprobarDatos(TViaje viaje) {
		return viaje.getPrecio() > 0 && viaje.getActivo() && viaje.getNumPlazas() >= 0 && viaje.getIdActividad() > 0 && viaje.getIdAlojamiento() > 0 && viaje.getIdTransporte() > 0;
	}

	@Override
	public int createViaje(TViaje Viaje) {
		int id = -1;
		DaoViaje d = FactoriaAbstractaIntegracion.getInstancia().crearDaoViaje();
		if(Viaje != null && comprobarDatos(Viaje)) {
			id = d.createViaje(Viaje);
		}
		return id;
	}

	@Override
	public boolean updateViaje(TViaje Viaje) {
		boolean update = false;
		DaoViaje d = FactoriaAbstractaIntegracion.getInstancia().crearDaoViaje();
		TViaje tViaje = d.readViaje(Viaje.getId());
		if(tViaje != null && comprobarDatos(tViaje)) {
			update = d.updateViaje(Viaje);
		}
		return update;
	}

	@Override
	public boolean deleteViaje(int id) {
		boolean delete = false;
		DaoViaje d = FactoriaAbstractaIntegracion.getInstancia().crearDaoViaje();
		TViaje tViaje = d.readViaje(id);
		if(tViaje != null && tViaje.getActivo()) {
			delete = d.deleteViaje(id);
		}
		return delete;
	}

	@Override
	public TViaje readViaje(int id) {
		DaoViaje d = FactoriaAbstractaIntegracion.getInstancia().crearDaoViaje();
		TViaje tViaje = d.readViaje(id);
		if(tViaje != null && comprobarDatos(tViaje)) {
			return tViaje;
		}
		else return null;
	}

	@Override
	public List<TViaje> readAllViaje() {
		DaoViaje d = FactoriaAbstractaIntegracion.getInstancia().crearDaoViaje();
		List<TViaje> lista = d.readAllViaje();
		boolean datosCorrectos = true;
		if(lista != null) {
			for(TViaje viaje : lista) {
				if(viaje == null || !comprobarDatos(viaje)) datosCorrectos = false;
			}
			if(datosCorrectos) return lista;
			else return null;
		}
		else return null;
	}

}
