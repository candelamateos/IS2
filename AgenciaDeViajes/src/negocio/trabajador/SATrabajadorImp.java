package negocio.trabajador;

import java.util.List;

import integracion.departamento.DaoDepartamento;
import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.trabajador.DaoTrabajador;

public class SATrabajadorImp implements SATrabajador {

	public SATrabajadorImp() {

	}

	@Override
	public int createTrabajador(TTrabajador trabajador) {
		int id = -1;

		DaoDepartamento departamento = FactoriaAbstractaIntegracion.getInstancia().crearDaoDepartamento();
		DaoTrabajador d = FactoriaAbstractaIntegracion.getInstancia().crearDaoTrabajador();

		if (trabajador.getNombre() == null || trabajador.getNombre().equals("") || trabajador.getSueldo() == 0
				|| departamento.readDepartamento(trabajador.getId()) == null || trabajador.getTipo().equals("")) {
			return -1;
		} else {
			if (trabajador.getTipo().equals("jefe")) {
				return d.createTrabajador(trabajador);
			} else {
				if (((TVendedor) trabajador).getIdJefe() == 0) {
					return -1;
				} else
					return d.createTrabajador(trabajador);
			}
		}
	}

	@Override
	public boolean updateTrabajador(TTrabajador trabajador) {
		boolean update = false;

		DaoTrabajador d = FactoriaAbstractaIntegracion.getInstancia().crearDaoTrabajador();
		DaoDepartamento departamento = FactoriaAbstractaIntegracion.getInstancia().crearDaoDepartamento();

		if (trabajador.getId() == -1 || trabajador.getNombre() == null || trabajador.getNombre().equals("")
				|| trabajador.getSueldo() == 0 || departamento.readDepartamento(trabajador.getId()) == null
				|| trabajador.getTipo().equals("")) {

			TTrabajador trab_buscado = d.readTrabajador(trabajador.getId());
			if (trab_buscado != null) {
				update = d.updateTrabajador(trabajador);
			}
		}
		return update;
	}

	@Override
	public boolean deleteTrabajador(int id) {
		DaoTrabajador d = FactoriaAbstractaIntegracion.getInstancia().crearDaoTrabajador();

		if (id != -1) {
			// compruebo, en caso de ser jefe, que no tiene ningún vendedor asociado
			if (d.readTrabajador(id).getTipo().equals("jefe")) {
				List<TTrabajador> lista = d.readAllTrabajador();
				boolean encontrado = false;
				for (TTrabajador t : lista) {
					if (t.getTipo().equals("vendedor") && ((TVendedor) t).getIdJefe() == id) {
						encontrado = true;
					}
				}

				if (encontrado) {
					return false;
				} else {
					return d.deleteTrabajador(id);
				}
				
			}else {
				return d.deleteTrabajador(id);
			}

		} else {
			return false;
		}
	}

	@Override
	public TTrabajador readTrabajador(int id) {
		DaoTrabajador d = FactoriaAbstractaIntegracion.getInstancia().crearDaoTrabajador();
		TTrabajador trabajador = null;

		if (id != -1) {
			trabajador = d.readTrabajador(id);
		}

		return trabajador;
	}

	@Override
	public List<TTrabajador> readAllTrabajador() {
		DaoTrabajador d = FactoriaAbstractaIntegracion.getInstancia().crearDaoTrabajador();
		return d.readAllTrabajador();
	}
}
