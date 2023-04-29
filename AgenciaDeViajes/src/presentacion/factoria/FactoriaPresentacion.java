package presentacion.factoria;

import presentacion.IGUI;
import presentacion.cliente.VistaClientes;
import presentacion.controlador.Eventos;
import presentacion.departamento.*;

public class FactoriaPresentacion extends FactoriaAbstractaPresentacion{
	public IGUI createVista(int id) {
		switch(id) {
		case Eventos.CLIENTES:{
			return new VistaClientes();}
		
		
		
		
		case Eventos.ALTA_DEPARTAMENTO:{
			return new VistaAniadirDepartamento(); }
		case Eventos.BAJA_DEPARTAMENTO:{
			return new VistaEliminarDepartamento(); }
		case Eventos.BUSCAR_DEPARTAMENTO:{
			return new VistaBuscarDepartamento(); }
		case Eventos.MODIFICAR_DEPARTAMENTO:{
			return new VistaModificarDepartamento(); }
		case Eventos.LISTAR_DEPARTAMENTO:{
			return new VistaListarDepartamento(); }
		}
		return null;
	}
}
