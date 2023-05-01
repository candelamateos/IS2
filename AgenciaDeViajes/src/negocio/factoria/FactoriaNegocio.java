package negocio.factoria;

import negocio.cliente.SACliente;
import negocio.cliente.SAClienteImp;
import negocio.departamento.SADepartamento;
import negocio.departamento.SADepartamentoImp;
import negocio.factura.SAFactura;
import negocio.factura.SAFacturaImp;
import negocio.factura.SALineaFacturaImp;
import negocio.servicio.SAServicio;
import negocio.servicio.SAServicioImp;
import negocio.trabajador.SATrabajador;
import negocio.trabajador.SATrabajadorImp;
import negocio.viaje.SAViaje;
import negocio.viaje.SAViajeImp;

public class FactoriaNegocio extends FactoriaAbstractaNegocio {

	@Override
	public SADepartamento crearSADepartamento() {
		return new SADepartamentoImp();
	}

	@Override
	public SACliente crearSACliente() {
		return new SAClienteImp();
	}

	@Override
	public SATrabajador crearSATrabajador() {
		return new SATrabajadorImp();
	}

	@Override
	public SAFactura crearSAFactura() {
		return new SAFacturaImp();
	}

	@Override
	public SALineaFacturaImp crearSALineaFactura() {
		return new SALineaFacturaImp();
	}

	@Override
	public SAViaje crearSAViaje() {
		return new SAViajeImp();
	}

	@Override
	public SAServicio crearSAServicio() {
		return new SAServicioImp();
	}
	
}
