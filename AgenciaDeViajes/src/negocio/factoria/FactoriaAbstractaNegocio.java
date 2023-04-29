package negocio.factoria;

import negocio.cliente.SACliente;
import negocio.departamento.SADepartamento;
import negocio.factura.SAFactura;
import negocio.servicio.SAServicio;
import negocio.trabajador.SATrabajador;
import negocio.viaje.SAViaje;

public abstract class FactoriaAbstractaNegocio {
	private static FactoriaAbstractaNegocio instancia = null;
	
	public static FactoriaAbstractaNegocio getInstancia() {
		if(instancia == null) instancia = new FactoriaNegocio();
		return instancia;
	}
	
	public abstract SADepartamento crearSADepartamento();
	public abstract SACliente crearSACliente();
	public abstract SATrabajador crearSATrabajador();
	public abstract SAFactura crearSAFactura();
	public abstract SAViaje crearSAViaje();
	public abstract SAServicio crearSAServicio();
}
