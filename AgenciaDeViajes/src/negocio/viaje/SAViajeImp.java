package negocio.viaje;

import java.util.List;

import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.factura.DaoFactura;
import integracion.viaje.DaoViaje;
import integracion.servicio.DaoServicio;
import negocio.factura.TLineaFactura;
import negocio.servicio.*;

public class SAViajeImp implements SAViaje{
		
	private boolean comprobarDatos(TViaje viaje) {
		return viaje.getActivo() && viaje.getNumPlazas() >= 0 && viaje.getIdActividad() > 0 && viaje.getIdAlojamiento() > 0 && viaje.getIdTransporte() > 0;
	}

	@Override
	public int createViaje(TViaje Viaje) {
		int id = -1;
		DaoViaje d = FactoriaAbstractaIntegracion.getInstancia().crearDaoViaje();
		DaoServicio dServicio = FactoriaAbstractaIntegracion.getInstancia().crearDaoServicio();
		TActividad tActividad = (TActividad) dServicio.readServicio(Viaje.getIdActividad());
		TAlojamiento tAlojamiento = (TAlojamiento) dServicio.readServicio(Viaje.getIdAlojamiento());
		TTransporte tTransporte = (TTransporte) dServicio.readServicio(Viaje.getIdTransporte());
		if(Viaje != null && comprobarDatos(Viaje) && tActividad != null && tAlojamiento != null && tTransporte != null) {
			int plazasActividad = tActividad.getNumPlazas() - Viaje.getNumPlazas();
			int plazasAlojamiento = tAlojamiento.getNumPlazas() - Viaje.getNumPlazas();
			int plazasTransporte = tTransporte.getNumPlazas() - Viaje.getNumPlazas();
			if(plazasActividad >= 0 && plazasAlojamiento >= 0 && plazasTransporte >= 0) {
				Viaje.setPrecio(tActividad.getPrecio() + tAlojamiento.getPrecio() + tTransporte.getPrecio());
				id = d.createViaje(Viaje);
				if(id != -1) {
					tActividad.setNumPlazas(plazasActividad);
					tAlojamiento.setNumPlazas(plazasAlojamiento);
					tTransporte.setNumPlazas(plazasTransporte);
				}
			}
		}
		return id;
	}

	private boolean comprobarDatosActualizados(TViaje nuevo, TViaje antiguo) {
		boolean correcto = false;
		DaoServicio dServicio = FactoriaAbstractaIntegracion.getInstancia().crearDaoServicio();
		
		TActividad tActividadNuevo = (TActividad) dServicio.readServicio(nuevo.getIdActividad());
		TAlojamiento tAlojamientoNuevo = (TAlojamiento) dServicio.readServicio(nuevo.getIdAlojamiento());
		TTransporte tTransporteNuevo = (TTransporte) dServicio.readServicio(nuevo.getIdTransporte());
		
		TActividad tActividadAntiguo = (TActividad) dServicio.readServicio(antiguo.getIdActividad());
		TAlojamiento tAlojamientoAntiguo = (TAlojamiento) dServicio.readServicio(antiguo.getIdAlojamiento());
		TTransporte tTransporteAntiguo = (TTransporte) dServicio.readServicio(antiguo.getIdTransporte());
		
		int antiguasPlazasActividad = tActividadAntiguo.getNumPlazas();
		int nuevasPlazasActividad = tActividadNuevo.getNumPlazas();
		int antiguasPlazasAlojamiento = tAlojamientoAntiguo.getNumPlazas();
		
		int nuevasPlazasAlojamiento = tAlojamientoNuevo.getNumPlazas();
		int antiguasPlazasTransporte = tTransporteAntiguo.getNumPlazas();
		int nuevasPlazasTransporte = tTransporteNuevo.getNumPlazas();
		
		if(tActividadNuevo.getId() != tActividadAntiguo.getId()) { /*La actividad ha cambiado*/
			antiguasPlazasActividad += antiguo.getNumPlazas();
			nuevasPlazasActividad -= nuevo.getNumPlazas();
		}
		else if(tActividadNuevo.getId() == tActividadAntiguo.getId() && nuevo.getNumPlazas() > antiguo.getNumPlazas()){ /*Misma actividad pero mayor numero de plazas*/
			antiguasPlazasActividad = antiguasPlazasActividad - (nuevo.getNumPlazas() - antiguo.getNumPlazas());
			nuevasPlazasActividad = antiguasPlazasActividad;
		}
		else if(tActividadNuevo.getId() == tActividadAntiguo.getId() && nuevo.getNumPlazas() < antiguo.getNumPlazas()){ /*Misma actividad pero menor numero de plazas*/
			antiguasPlazasActividad = antiguasPlazasActividad + (antiguo.getNumPlazas() - nuevo.getNumPlazas());
			nuevasPlazasActividad = antiguasPlazasActividad;
		}
		
		if(tAlojamientoNuevo.getId() != tAlojamientoAntiguo.getId()) { /*El alojamiento ha cambiado*/
			antiguasPlazasAlojamiento += antiguo.getNumPlazas();
			nuevasPlazasAlojamiento -= nuevo.getNumPlazas();
		}
		else if(tAlojamientoNuevo.getId() == tAlojamientoAntiguo.getId() && nuevo.getNumPlazas() > antiguo.getNumPlazas()){ /*Mismo alojamiento pero mayor numero de plazas*/
			antiguasPlazasAlojamiento = antiguasPlazasAlojamiento - (nuevo.getNumPlazas() - antiguo.getNumPlazas());
			nuevasPlazasAlojamiento = antiguasPlazasAlojamiento;
		}
		else if(tAlojamientoNuevo.getId() == tAlojamientoAntiguo.getId() && nuevo.getNumPlazas() < antiguo.getNumPlazas()){ /*Mismo alojamiento pero menor numero de plazas*/
			antiguasPlazasAlojamiento = antiguasPlazasAlojamiento + (antiguo.getNumPlazas() - nuevo.getNumPlazas());
			nuevasPlazasAlojamiento = antiguasPlazasAlojamiento;
		}
		
		if(tTransporteNuevo.getId() != tTransporteAntiguo.getId()) { /*El transporte ha cambiado*/
			antiguasPlazasTransporte += antiguo.getNumPlazas();
			nuevasPlazasTransporte -= nuevo.getNumPlazas();
		}
		else if(tTransporteNuevo.getId() == tTransporteAntiguo.getId() && nuevo.getNumPlazas() > antiguo.getNumPlazas()){ /*Mismo transporte pero mayor numero de plazas*/
			antiguasPlazasTransporte = antiguasPlazasTransporte - (nuevo.getNumPlazas() - antiguo.getNumPlazas());
			nuevasPlazasTransporte = antiguasPlazasTransporte;
		}
		else if(tTransporteNuevo.getId() == tTransporteAntiguo.getId() && nuevo.getNumPlazas() < antiguo.getNumPlazas()){ /*Mismo transporte pero menor numero de plazas*/
			antiguasPlazasTransporte = antiguasPlazasTransporte + (antiguo.getNumPlazas() - nuevo.getNumPlazas());
			nuevasPlazasTransporte = antiguasPlazasTransporte;
		}
		
		/*Si el servicio no cambia y el numero de plazas tampoco, no hay que hacer ninguna modificación en el servicio*/
		
		if(nuevasPlazasActividad >= 0 && nuevasPlazasAlojamiento >= 0 && nuevasPlazasTransporte >= 0) {
			correcto = true;
			tActividadAntiguo.setNumPlazas(antiguasPlazasActividad);
			tAlojamientoAntiguo.setNumPlazas(antiguasPlazasAlojamiento);
			tTransporteAntiguo.setNumPlazas(antiguasPlazasTransporte);
			
			tActividadNuevo.setNumPlazas(nuevasPlazasActividad);
			tAlojamientoNuevo.setNumPlazas(nuevasPlazasAlojamiento);
			tTransporteNuevo.setNumPlazas(nuevasPlazasTransporte);
		}		
		return correcto;
	}
	
	@Override
	public boolean updateViaje(TViaje Viaje) {
		boolean update = false;
		DaoViaje d = FactoriaAbstractaIntegracion.getInstancia().crearDaoViaje();
		
		DaoFactura dFactura = FactoriaAbstractaIntegracion.getInstancia().crearDaoFactura();
		List<TLineaFactura> listaFacturas = dFactura.readAllLineaFactura();
		boolean anterior = false;
		for(TLineaFactura lf : listaFacturas) {
			if(lf.getIdViaje() == Viaje.getId()) anterior = true;	/*Si el viaje ya esta dentro de una factura, no se puede modificar ni eliminar*/
		}
		
		if(!anterior) {			
			TViaje tViaje = d.readViaje(Viaje.getId());
			if(Viaje != null && comprobarDatosActualizados(Viaje, tViaje)) {
				update = d.updateViaje(Viaje);
			}
		}	
		return update;
	}

	@Override
	public boolean deleteViaje(int id) {
		boolean delete = false;
		DaoViaje d = FactoriaAbstractaIntegracion.getInstancia().crearDaoViaje();
		
		DaoFactura dFactura = FactoriaAbstractaIntegracion.getInstancia().crearDaoFactura();
		List<TLineaFactura> listaFacturas = dFactura.readAllLineaFactura();
		boolean anterior = false;
		for(TLineaFactura lf : listaFacturas) {
			if(lf.getIdViaje() == id) anterior = true;	/*Si el viaje ya esta dentro de una factura, no se puede modificar ni eliminar*/	
		}
		if(!anterior) {
			TViaje tViaje = d.readViaje(id);
			if(tViaje != null && tViaje.getActivo()) {
				delete = d.deleteViaje(id);
			}
		}
		
		return delete;
	}

	@Override
	public TViaje readViaje(int id) {
		DaoViaje d = FactoriaAbstractaIntegracion.getInstancia().crearDaoViaje();
		return d.readViaje(id);
	}

	@Override
	public List<TViaje> readAllViaje() {
		DaoViaje d = FactoriaAbstractaIntegracion.getInstancia().crearDaoViaje();
		return d.readAllViaje();
	}

}
