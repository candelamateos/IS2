package integracion.trabajador;

import java.util.List;

import negocio.trabajador.TTrabajador;

public class DaoTrabajadorImp implements DaoTrabajador{

	@Override
	public int createTrabajador(TTrabajador trabajo) {
		return 0;
	}

	@Override
	public int updateTrabajador(TTrabajador trabajo) {
		return 0;
	}

	@Override
	public boolean deleteTrabajador(int id) {
		return false;
	}

	@Override
	public TTrabajador readTrabajador(int id) {
		return null;
	}

	@Override
	public List<TTrabajador> readAllTrabajador(TTrabajador trabajo) {
		return null;
	}

}
