package integracion.factoria;

import integracion.cliente.DaoCliente;
import integracion.departamento.DaoDepartamento;
import integracion.factura.DaoFactura;
import integracion.servicio.DaoServicio;
import integracion.trabajador.DaoTrabajador;
import integracion.viaje.DaoViaje;

public abstract class FactoriaAbstractaIntegracion {
	
private static FactoriaAbstractaIntegracion instancia = null;
	
	public static FactoriaAbstractaIntegracion getInstancia() {
		if(instancia == null) instancia = new FactoriaIntegracion();
		return instancia;
	}
	
	public abstract DaoDepartamento crearDaoDepartamento();
	public abstract DaoCliente crearDaoCliente();
	public abstract DaoTrabajador crearDaoTrabajador();
	public abstract DaoFactura crearDaoFactura();
	public abstract DaoViaje crearDaoViaje();
	public abstract DaoServicio crearDaoServicio();
	
}
