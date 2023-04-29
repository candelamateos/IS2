package integracion.factoria;

import integracion.cliente.DaoCliente;
import integracion.cliente.DaoClienteImp;
import integracion.departamento.DaoDepartamento;
import integracion.departamento.DaoDepartamentoImp;
import integracion.factura.DaoFactura;
import integracion.factura.DaoFacturaImp;
import integracion.servicio.DaoServicio;
import integracion.servicio.DaoServicioImp;
import integracion.trabajador.DaoTrabajador;
import integracion.trabajador.DaoTrabajadorImp;
import integracion.viaje.DaoViaje;
import integracion.viaje.DaoViajeImp;

public class FactoriaIntegracion extends FactoriaAbstractaIntegracion {

	@Override
	public DaoDepartamento crearDaoDepartamento() {
		return new DaoDepartamentoImp();
	}

	@Override
	public DaoCliente crearDaoCliente() {
		return new DaoClienteImp();
	}

	@Override
	public DaoTrabajador crearDaoTrabajador() {
		return new DaoTrabajadorImp();
	}

	@Override
	public DaoFactura crearDaoFactura() {
		return new DaoFacturaImp();
	}

	@Override
	public DaoViaje crearDaoViaje() {
		return new DaoViajeImp();
	}

	@Override
	public DaoServicio crearDaoServicio() {
		return new DaoServicioImp();
	}

}
