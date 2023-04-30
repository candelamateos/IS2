package negocio.departamento;

import java.util.List;

import integracion.departamento.DaoDepartamento;
import integracion.factoria.FactoriaAbstractaIntegracion;

public class SADepartamentoImp implements SADepartamento {
	
	public SADepartamentoImp() {
	}

	@Override
	public int createDepartamento(TDepartamento departamento) {
		int id = -1;
		DaoDepartamento d = FactoriaAbstractaIntegracion.getInstancia().crearDaoDepartamento();
		
		if(departamento != null) {
			TDepartamento existe = d.readDepartamento(departamento.getId());
			if(existe == null) {
				id = d.createDepartamento(departamento);
			}
		}
		return id;
	}

	@Override
	public boolean updateDepartamento(TDepartamento departamento) {
		boolean update = false;
		DaoDepartamento d = FactoriaAbstractaIntegracion.getInstancia().crearDaoDepartamento();
		
		if(departamento != null) {
			TDepartamento existe = d.readDepartamento(departamento.getId());
			if(existe != null) {
				update = d.updateDepartamento(departamento);
			}
		}
		return update;
	}

	@Override
	public boolean deleteDepartamento(int id) {
		boolean delete = false;
		DaoDepartamento d = FactoriaAbstractaIntegracion.getInstancia().crearDaoDepartamento();
		
		if(id != -1) {
			TDepartamento existe = d.readDepartamento(id);
			if(existe != null) {
				delete = d.deleteDepartamento(id);
			}
		}
		return delete;
	}

	@Override
	public TDepartamento readDepartamento(int id) {
		TDepartamento departamento = null;
		DaoDepartamento d = FactoriaAbstractaIntegracion.getInstancia().crearDaoDepartamento();
		
		if(id != -1) {
			departamento = d.readDepartamento(id);
		}
		return departamento;
	}

	@Override
	public List<TDepartamento> readAllDepartamento() {
		DaoDepartamento d = FactoriaAbstractaIntegracion.getInstancia().crearDaoDepartamento();
		return d.readAllDepartamento();
	}

}
