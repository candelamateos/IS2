package presentacion.factoria;

import presentacion.IGUI;
import presentacion.departamento.*;

public class FactoriaPresentacion extends FactoriaAbstractaPresentacion{
	public IGUI createVista(int id) {
		switch(id) {
		case Evento.ALTA_DEPARTAMENTO:{
			return new VistaAniadirDepartamento(); break;}
		case Evento.BAJA_DEPARTAMENTO:{
			return new VistaEliminarDepartamento(); break;}
		case Evento.BUSCAR_DEPARTAMENTO:{
			return new VistaBuscarDepartamento(); break;}
		case Evento.MODIFICAR_DEPARTAMENTO:{
			return new VistaModificarDepartamento(); break;}
		case Evento.LISTAR_DEPARTAMENTO:{
			return new VistaListarDepartamento(); break;}
		}
	}
}
