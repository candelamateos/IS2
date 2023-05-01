package negocio.servicio;

import java.util.List;

import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.servicio.DaoServicio;

public class SAServicioImp implements SAServicio{

	@Override
	public int createServicio(TServicio servicio) {
		DaoServicio dao = FactoriaAbstractaIntegracion.getInstancia().crearDaoServicio();
		if(!comprobarComun(servicio)) return -1;
		
		switch(servicio.getTipo()) {
		case "actividad":{
			if(comprobarActividad((TActividad) servicio)) return dao.createServicio(servicio);
			break;
		}
		
		case "transporte":{
			if(comprobarTransporte((TTransporte) servicio)) return dao.createServicio(servicio);
			break;
		}
		
		case "alojamiento":{
			if(comprobarAlojamiento((TAlojamiento) servicio)) return dao.createServicio(servicio);
			break;
		}
		
		default: throw new IllegalArgumentException("tipo no valido");
		}
		
		return -1;
	}

	private boolean comprobarComun(TServicio servicio) {
		return
				servicio.getNombre() != null
				&& servicio.getNombre()	!= ""
				&& servicio.getNumPlazas() >= 0
				&& servicio.getPrecio() >= 0
		;
		
	}

	private boolean comprobarAlojamiento(TAlojamiento servicio) {
		return
				servicio.getEstrellas() > 0
				&& servicio.getEstrellas() <= 5
				&& servicio.getRegimen() != null
				&& servicio.getRegimen() != ""
		;
	}

	private boolean comprobarTransporte(TTransporte servicio) {
		return
				servicio.getTipoTransporte() != null
				&& servicio.getTipoTransporte() != ""
		;
	}

	private boolean comprobarActividad(TActividad servicio) {
		return
				servicio.getTipoActividad() != null
				&& servicio.getTipoActividad() != ""
		;
	}

	@Override
	public boolean updateServicio(TServicio servicio) {
		DaoServicio dao = FactoriaAbstractaIntegracion.getInstancia().crearDaoServicio();
		if(servicio ==  null) return false;
		if(dao.readServicio(servicio.getId()) == null) return false;
		return dao.updateServicio(servicio);
	}

	@Override
	public boolean deleteServicio(int id) {
		DaoServicio dao = FactoriaAbstractaIntegracion.getInstancia().crearDaoServicio();
		return dao.deleteServicio(id);
	}

	@Override
	public TServicio readServicio(int id) {
		DaoServicio dao = FactoriaAbstractaIntegracion.getInstancia().crearDaoServicio();
		return dao.readServicio(id);
	}

	@Override
	public List<TServicio> readAllServicio() {
		DaoServicio dao = FactoriaAbstractaIntegracion.getInstancia().crearDaoServicio();
		return dao.readAllServicio();
	}

	

}
