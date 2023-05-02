package negocio.trabajador;

import java.util.List;

import integracion.departamento.DaoDepartamento;
import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.servicio.DaoServicio;
import integracion.trabajador.DaoTrabajador;
import negocio.departamento.TDepartamento;
import negocio.servicio.TActividad;
import negocio.servicio.TAlojamiento;
import negocio.servicio.TServicio;
import negocio.servicio.TTransporte;

public class SATrabajadorImp implements SATrabajador {

	public SATrabajadorImp() {

	}
	
	private boolean comprobarTrabajador(TTrabajador trabajador) {
		DaoDepartamento dao = FactoriaAbstractaIntegracion.getInstancia().crearDaoDepartamento();
		TDepartamento departamento = dao.readDepartamento(trabajador.getIdDepart());
		return
				trabajador.getNombre() != null
				&& trabajador.getNombre()	!= ""
				&& trabajador.getSueldo() >= 0
				&& trabajador.getIdDepart() >= 0
				&& departamento != null
		;
	}

	private boolean comprobarVendedor(TVendedor vendedor) {
		return
				vendedor.getIdJefe() >= 0;
	}

	@Override
	public int createTrabajador(TTrabajador trabajador) {
		DaoTrabajador dao = FactoriaAbstractaIntegracion.getInstancia().crearDaoTrabajador();
		DaoDepartamento d = FactoriaAbstractaIntegracion.getInstancia().crearDaoDepartamento();
		TDepartamento departamento = d.readDepartamento(trabajador.getIdDepart());
		
		if(!comprobarTrabajador(trabajador)) return -1;
		
		switch(trabajador.getTipo()) {
		case "vendedor":{
			if(comprobarVendedor((TVendedor) trabajador)) {
				departamento.setNumEmpleados(departamento.getNumEmpleados() + 1);
				return dao.createTrabajador(trabajador);
			}
			break;
		}
		
		case "jefe":{
			if(comprobarTrabajador((TJefe) trabajador)) {
				departamento.setNumEmpleados(departamento.getNumEmpleados() + 1);
				return dao.createTrabajador(trabajador);
			}
			break;
		}
		
		default: throw new IllegalArgumentException("tipo no valido");
		}
		
		return -1;
	}

	@Override
	public boolean updateTrabajador(TTrabajador trabajador) {
		DaoTrabajador dao = FactoriaAbstractaIntegracion.getInstancia().crearDaoTrabajador();
		if(trabajador ==  null) return false;
		if(dao.readTrabajador(trabajador.getId()) == null) return false;
		return dao.updateTrabajador(trabajador);
	}

	@Override
	public boolean deleteTrabajador(int id) {
		DaoServicio dao = FactoriaAbstractaIntegracion.getInstancia().crearDaoServicio();
		return dao.deleteServicio(id);
	}

	@Override
	public TTrabajador readTrabajador(int id) {
		DaoTrabajador dao = FactoriaAbstractaIntegracion.getInstancia().crearDaoTrabajador();
		
		if (id == -1) return null;
		
		return dao.readTrabajador(id);
	}

	@Override
	public List<TTrabajador> readAllTrabajador() {
		DaoTrabajador d = FactoriaAbstractaIntegracion.getInstancia().crearDaoTrabajador();
		return d.readAllTrabajador();
	}
}
