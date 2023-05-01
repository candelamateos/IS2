package presentacion.factoria;

import presentacion.IGUI;
import presentacion.cliente.*;
import presentacion.controlador.Eventos;
import presentacion.departamento.*;
import presentacion.factura.VistaAbrirVenta;
import presentacion.factura.VistaAniadirViajeAFactura;
import presentacion.factura.VistaCerrarVenta;
import presentacion.factura.VistaFacturas;
import presentacion.servicio.VistaAnyadirServicio;
import presentacion.servicio.VistaBuscarServicio;
import presentacion.servicio.VistaEliminarServicio;
import presentacion.servicio.VistaListarServicios;
import presentacion.servicio.VistaModificarServicio;
import presentacion.servicio.VistaServicios;
import presentacion.factura.*;
import presentacion.viaje.*;

public class FactoriaPresentacion extends FactoriaAbstractaPresentacion{
	public IGUI createVista(int id) {
		switch(id) {
		
		//Departamento
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
		
		//Factura
		case Eventos.FACTURAS:{
			return new VistaFacturas();}
		case Eventos.ABRIR_VENTA:{
			return new VistaAbrirVenta();}
		case Eventos.CERRAR_VENTA:{
			return new VistaCerrarVenta();}
		case Eventos.ANIADIR_VIAJE_A_FACTURA:{
			return new VistaAniadirViajeAFactura();}
		case Eventos.MODIFICAR_FACTURA:{
			return new VistaModificarFactura();}
		case Eventos.BUSCAR_FACTURA:{
			return new VistaBuscarFactura();}
		case Eventos.LISTAR_FACTURAS:{
			return new VistaListarFacturas();}
		

		
		//Servicio
		case Eventos.SERVICIOS:{
			return new VistaServicios();}
		case Eventos.ALTA_SERVICIO:{
			return new VistaAnyadirServicio();}
		case Eventos.BAJA_SERVICIO:{
			return new VistaEliminarServicio(); }
		case Eventos.BUSCAR_SERVICIO:{
			return new VistaBuscarServicio(); }
		case Eventos.MODIFICAR_SERVICIO:{
			return new VistaModificarServicio(); }
		case Eventos.LISTAR_SERVICIO:{
			return new VistaListarServicios(); }
		
		
		//Trabajador
		
		//Viaje
		case Eventos.VIAJES:{
			return new VistaViajes(); }
		case Eventos.ALTA_VIAJE:{
			return new VistaAniadirViaje(); }
		case Eventos.BAJA_VIAJE:{
			return new VistaEliminarViaje(); }
		case Eventos.BUSCAR_VIAJE:{
			return new VistaBuscarViaje(); }
		case Eventos.MODIFICAR_VIAJE:{
			return new VistaModificarViaje(); }
		case Eventos.LISTAR_VIAJE:{
			return new VistaListarViaje(); }
		
		//Clientes
		case Eventos.CLIENTES:{
			return new VistaClientes();}
		case Eventos.ALTA_CLIENTE:{
			return new VistaAniadirCliente();}
		case Eventos.BAJA_CLIENTE:{
			return new VistaEliminarCliente(); }
		case Eventos.BUSCAR_CLIENTE:{
			return new VistaBuscarCliente(); }
		case Eventos.MODIFICAR_CLIENTE:{
			return new VistaModificarCliente(); }
		case Eventos.LISTAR_CLIENTE:{
			return new VistaListarCliente(); }
		}
		
		
		
		return null;
	}
}
