package presentacion.factoria;

import presentacion.IGUI;
import presentacion.controlador.Eventos;
import presentacion.departamento.*;

public class FactoriaPresentacion extends FactoriaAbstractaPresentacion{
	public IGUI createVista(int id) {
		switch(id) {
		case Eventos.ALTA_DEPARTAMENTO:{
			return new VistaAniadirDepartamento(); break;}
		case Eventos.BAJA_DEPARTAMENTO:{
			return new VistaEliminarDepartamento(); break;}
		case Eventos.BUSCAR_DEPARTAMENTO:{
			return new VistaBuscarDepartamento(); break;}
		case Eventos.MODIFICAR_DEPARTAMENTO:{
			return new VistaModificarDepartamento(); break;}
		case Eventos.LISTAR_DEPARTAMENTO:{
			return new VistaListarDepartamento(); break;}
		}
	}
}
