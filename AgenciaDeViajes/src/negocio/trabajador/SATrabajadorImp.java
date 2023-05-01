package negocio.trabajador;

import java.util.List;

import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.trabajador.DaoTrabajador;

public class SATrabajadorImp implements SATrabajador{

	public SATrabajadorImp() {
		
	}
	
	@Override
	public int createTrabajador(TTrabajador trabajador) {
		int id = -1;
		
		// creamos el DAO del trabajador dado
		DaoTrabajador dt = FactoriaAbstractaIntegracion.getInstancia().crearDaoTrabajador();
		
		
		
		
		
		
		
		
		return 0;
	}

	@Override
	public boolean updateTrabajador(TTrabajador trabajador) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTrabajador(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TTrabajador readTrabajador(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TTrabajador> readAllTrabajador() {
		// TODO Auto-generated method stub
		return null;
	}

}
