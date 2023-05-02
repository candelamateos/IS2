package negocio.departamento;

import java.util.List;

import integracion.departamento.DaoDepartamento;
import integracion.factoria.FactoriaAbstractaIntegracion;

public class SADepartamentoImp implements SADepartamento {

	public SADepartamentoImp() {
	}

	private boolean comprobarDatos(TDepartamento departamento) {
		return departamento.getActivo() && departamento.getNumEmpleados() >= 0 && departamento.getNombre() != "" && departamento.getNombre() != null;
	}

	@Override
	public int createDepartamento(TDepartamento departamento) {
		int id = -1;
		DaoDepartamento d = FactoriaAbstractaIntegracion.getInstancia().crearDaoDepartamento();
		if (departamento != null && comprobarDatos(departamento)) {
			id = d.createDepartamento(departamento);
		}
		return id;
	}

	@Override
	public boolean updateDepartamento(TDepartamento departamento) {
		boolean update = false;
		DaoDepartamento d = FactoriaAbstractaIntegracion.getInstancia().crearDaoDepartamento();
		TDepartamento existe = d.readDepartamento(departamento.getId());
		if (existe != null && comprobarDatos(existe)) {
			update = d.updateDepartamento(departamento);
		}
		return update;
	}

	@Override
	public boolean deleteDepartamento(int id) {
		boolean delete = false;
		DaoDepartamento d = FactoriaAbstractaIntegracion.getInstancia().crearDaoDepartamento();
		TDepartamento existe = d.readDepartamento(id);
		if (existe != null && comprobarDatos(existe)) {
			delete = d.deleteDepartamento(id);
		}
		return delete;
	}

	@Override
	public TDepartamento readDepartamento(int id) {
		DaoDepartamento d = FactoriaAbstractaIntegracion.getInstancia().crearDaoDepartamento();
		TDepartamento existe = d.readDepartamento(id);
		if (existe != null && comprobarDatos(existe)) {
			return existe;
		}
		return null;
	}

	@Override
	public List<TDepartamento> readAllDepartamento() {
		DaoDepartamento d = FactoriaAbstractaIntegracion.getInstancia().crearDaoDepartamento();
		List<TDepartamento> lista = d.readAllDepartamento();
		boolean datosCorrectos = true;
		if (lista != null) {
			for (TDepartamento departamento : lista) {
				if (departamento == null || !comprobarDatos(departamento))
					datosCorrectos = false;
			}
			if (datosCorrectos)
				return lista;
			else
				return null;
		} else
			return null;
	}

}
