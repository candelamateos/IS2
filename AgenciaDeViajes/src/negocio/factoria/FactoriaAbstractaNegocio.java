package negocio.factoria;

import negocio.departamento.SADepartamento;

public abstract class FactoriaAbstractaNegocio {
	private static FactoriaAbstractaNegocio instancia = null;
	
	public static FactoriaAbstractaNegocio getInstancia() {
		if(instancia == null) instancia = new FactoriaNegocio();
		return instancia;
	}
	
	public abstract SADepartamento crearSADepartamento();
	public abstract SADepartamento crearSACliente();
	public abstract SADepartamento crearSATrabajador();
	public abstract SADepartamento crearSAFactura();
	public abstract SADepartamento crearSAViaje();
	public abstract SADepartamento crearSAServicio();
}
