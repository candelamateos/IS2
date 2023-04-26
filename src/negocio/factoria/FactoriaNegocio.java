package negocio.factoria;

import negocio.departamento.SADepartamento;
import negocio.departamento.SADepartamentoImp;

public class FactoriaNegocio extends FactoriaAbstractaNegocio {

	@Override
	public SADepartamento crearSADepartamento() {
		return new SADepartamentoImp();
	}

	@Override
	public SADepartamento crearSACliente() {
		return new SAClienteImp();
	}

	@Override
	public SADepartamento crearSATrabajador() {
		return new SATrabajadorImp();
	}

	@Override
	public SADepartamento crearSAFactura() {
		return new SAFacturaImp();
	}

	@Override
	public SADepartamento crearSAViaje() {
		return new SAViajeImp();
	}

	@Override
	public SADepartamento crearSAServicio() {
		return new SAServicioImp();
	}
	
}
