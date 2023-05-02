package presentacion.controlador;

import java.util.List;

import negocio.cliente.SACliente;
import negocio.cliente.TCliente;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.factura.SAFactura;
import negocio.factura.SALineaFactura;
import negocio.factura.TFactura;
import negocio.factura.TFacturaConLineas;
import negocio.factura.TLineaFactura;
import negocio.servicio.SAServicio;
import negocio.servicio.TServicio;
import negocio.trabajador.SATrabajador;
import negocio.trabajador.TTrabajador;
import negocio.servicio.TServicio;
import negocio.viaje.SAViaje;
import negocio.viaje.TViaje;
import presentacion.factoria.FactoriaAbstractaPresentacion;

public class ControladorImp extends Controlador {

	public void accion(int evento, Object datos) {
		switch (evento) {

		// Departamento
		case (Eventos.ALTA_DEPARTAMENTO): {
			TDepartamento tDepartamento = (TDepartamento) datos;
			SADepartamento saDepartamento = FactoriaAbstractaNegocio.getInstancia().crearSADepartamento();
			int res = saDepartamento.createDepartamento(tDepartamento);
			if (res != -1) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_DEPARTAMENTO_OK,
						res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_DEPARTAMENTO_ERROR,
						res);
				break;
			}
		}
		case (Eventos.BAJA_DEPARTAMENTO): {
			int id = (int) datos;
			SADepartamento saDepartamento = FactoriaAbstractaNegocio.getInstancia().crearSADepartamento();
			boolean res = saDepartamento.deleteDepartamento(id);
			if (res) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BAJA_DEPARTAMENTO_OK,
						res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BAJA_DEPARTAMENTO_ERROR,
						res);
				break;
			}
		}
		case (Eventos.BUSCAR_DEPARTAMENTO): {
			int id = (int) datos;
			SADepartamento saDepartamento = FactoriaAbstractaNegocio.getInstancia().crearSADepartamento();
			TDepartamento res = saDepartamento.readDepartamento(id);
			if (res != null) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_DEPARTAMENTO_OK,
						res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_BUSCAR_DEPARTAMENTO_ERROR, res);
				break;
			}
		}
		case (Eventos.LISTAR_DEPARTAMENTO): {
			SADepartamento saDepartamento = FactoriaAbstractaNegocio.getInstancia().crearSADepartamento();
			List<TDepartamento> res = saDepartamento.readAllDepartamento();
			if (res != null) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_LISTAR_DEPARTAMENTO_OK,	res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_LISTAR_DEPARTAMENTO_ERROR, res);
				break;
			}
		}
		case (Eventos.MODIFICAR_DEPARTAMENTO): {
			TDepartamento departamento = (TDepartamento) datos;
			SADepartamento saDepartamento = FactoriaAbstractaNegocio.getInstancia().crearSADepartamento();
			boolean res = saDepartamento.updateDepartamento(departamento);
			if (res) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_MODIFICAR_DEPARTAMENTO_OK, res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_MODIFICAR_DEPARTAMENTO_ERROR, res);
				break;
			}

		}

		// Factura
		case (Eventos.ABRIR_VENTA): {
			TFactura factura = (TFactura) datos;
			SAFactura saFactura = FactoriaAbstractaNegocio.getInstancia().crearSAFactura();
			int res = saFactura.abrirVenta(factura);
			if (res != -1) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ABRIR_VENTA_OK,
						res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_ABRIR_VENTA_ERROR, res);
				break;
			}
		}
		case (Eventos.CERRAR_VENTA): {
			int id = (int) datos;
			SAFactura saFactura = FactoriaAbstractaNegocio.getInstancia().crearSAFactura();
			boolean res = saFactura.cerrarVenta(id);
			if (res) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_CERRAR_VENTA_OK,
						res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_CERRAR_VENTA_ERROR, res);
				break;
			}
		}
		case (Eventos.ANIADIR_VIAJE_A_FACTURA): {
			TLineaFactura linea = (TLineaFactura) datos;
			SALineaFactura saLineaFactura = FactoriaAbstractaNegocio.getInstancia().crearSALineaFactura();
			boolean res = saLineaFactura.createLineaFactura(linea);
			if (res) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_ANIADIR_VIAJE_A_FACTURA_OK, res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_ANIADIR_VIAJE_A_FACTURA_ERROR, res);
				break;
			}
		}
		case (Eventos.MODIFICAR_FACTURA): {
			TFactura factura = (TFactura) datos;
			SAFactura saFactura = FactoriaAbstractaNegocio.getInstancia().crearSAFactura();
			boolean res = saFactura.modificarFactura(factura);
			if (res) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_MODIFICAR_FACTURA_OK, res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_MODIFICAR_FACTURA_ERROR, res);
				break;
			}
		}
		case (Eventos.BUSCAR_FACTURA): {
			int id = (int) datos;
			SAFactura saFactura = FactoriaAbstractaNegocio.getInstancia().crearSAFactura();
			TFacturaConLineas res = saFactura.readFactura(id);
			if (res != null) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_BUSCAR_FACTURA_OK, res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_BUSCAR_FACTURA_ERROR, res);
				break;
			}
		}
		case (Eventos.LISTAR_FACTURAS): {
			SAFactura saFactura = FactoriaAbstractaNegocio.getInstancia().crearSAFactura();
			List<TFactura> res = saFactura.readAllFactura();
			if (res != null) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_LISTAR_FACTURAS_OK, res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_LISTAR_FACTURAS_ERROR, res);
				break;
			}
		}

		// Trabajador
		case (Eventos.ALTA_TRABAJADOR):{
			TTrabajador tTrabajador = (TTrabajador) datos;
			SATrabajador saTrabajador = FactoriaAbstractaNegocio.getInstancia().crearSATrabajador();
			int res = saTrabajador.createTrabajador(tTrabajador);
			if (res != -1) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_TRABAJADOR_OK,
						res);
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_ALTA_TRABAJADOR_ERROR, res);
			}
			
			break;
		}
		case (Eventos.BAJA_TRABAJADOR):{

			int id = (int) datos;
			SATrabajador saTrabajador = FactoriaAbstractaNegocio.getInstancia().crearSATrabajador();
			boolean res = saTrabajador.deleteTrabajador(id);
			if (res) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
				.actualizar(Eventos.RES_BAJA_TRABAJADOR_OK, res);
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_BAJA_TRABAJADOR_ERROR, res);
			}
			
			break;
		}
		case (Eventos.LISTAR_TRABAJADOR): {
			SATrabajador saTrabajador = FactoriaAbstractaNegocio.getInstancia().crearSATrabajador();
			List<TTrabajador> res = saTrabajador.readAllTrabajador();
			if (res != null) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_LISTAR_TRABAJADOR_OK, res);
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_LISTAR_TRABAJADOR_ERROR, res);
			}
			
			break;
		}
		case (Eventos.BUSCAR_TRABAJADOR): {
			int id = (int) datos;
			SATrabajador saTrabajador = FactoriaAbstractaNegocio.getInstancia().crearSATrabajador();
			TTrabajador res = saTrabajador.readTrabajador(id);
			if (res != null) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_BUSCAR_TRABAJADOR_OK, res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_BUSCAR_TRABAJADOR_ERROR, res);
				break;
			}
		}
		case (Eventos.MODIFICAR_TRABAJADOR): {
			TTrabajador trabajador = (TTrabajador) datos;
			SATrabajador saTrabajador = FactoriaAbstractaNegocio.getInstancia().crearSATrabajador();
			boolean res = saTrabajador.updateTrabajador(trabajador);
			if (res) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_MODIFICAR_FACTURA_OK, res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_MODIFICAR_FACTURA_ERROR, res);
				break;
			}
		}

		// Viaje
		case (Eventos.ALTA_VIAJE): {
			TViaje tViaje = (TViaje) datos;
			SAViaje saViaje = FactoriaAbstractaNegocio.getInstancia().crearSAViaje();
			int res = saViaje.createViaje(tViaje);
			if (res != -1) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_VIAJE_OK,
						res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_VIAJE_ERROR,
						res);
				break;
			}
		}
		case (Eventos.BAJA_VIAJE): {
			int id = (int) datos;
			SAViaje saViaje = FactoriaAbstractaNegocio.getInstancia().crearSAViaje();
			boolean res = saViaje.deleteViaje(id);
			if (res) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BAJA_VIAJE_OK,
						res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BAJA_VIAJE_ERROR,
						res);
				break;
			}
		}
		case (Eventos.BUSCAR_VIAJE): {
			int id = (int) datos;
			SAViaje saViaje = FactoriaAbstractaNegocio.getInstancia().crearSAViaje();
			TViaje res = saViaje.readViaje(id);
			if (res != null) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_VIAJE_OK,
						res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_BUSCAR_VIAJE_ERROR, res);
				break;
			}
		}
		case (Eventos.LISTAR_VIAJE): {
			SAViaje saViaje = FactoriaAbstractaNegocio.getInstancia().crearSAViaje();
			List<TViaje> res = saViaje.readAllViaje();
			if (res != null) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_LISTAR_VIAJE_OK,
						res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_LISTAR_VIAJE_ERROR, res);
				break;
			}
		}
		case (Eventos.MODIFICAR_VIAJE): {
			TViaje viaje = (TViaje) datos;
			SAViaje saViaje = FactoriaAbstractaNegocio.getInstancia().crearSAViaje();
			boolean res = saViaje.updateViaje(viaje);
			if (res) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_MODIFICAR_VIAJE_OK, res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_MODIFICAR_VIAJE_ERROR, res);
				break;
			}
		}

		// Clientes
		case (Eventos.ALTA_CLIENTE): {
			TCliente tCliente = (TCliente) datos;
			SACliente saCliente = FactoriaAbstractaNegocio.getInstancia().crearSACliente();
			int res = saCliente.createCliente(tCliente);
			if (res != -1) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_CLIENTE_OK,
						res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_ALTA_CLIENTE_ERROR, res);
				break;
			}
		}
		case (Eventos.BAJA_CLIENTE): {
			int id = (int) datos;
			SACliente saCliente = FactoriaAbstractaNegocio.getInstancia().crearSACliente();
			boolean res = saCliente.deleteCliente(id);
			if (res) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BAJA_CLIENTE_OK,
						res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_BAJA_CLIENTE_ERROR, res);
				break;
			}
			
		}
		case (Eventos.LISTAR_CLIENTE): {
			SACliente saCliente = FactoriaAbstractaNegocio.getInstancia().crearSACliente();
			List<TCliente> res = saCliente.readAllCliente();
			if (res != null) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_LISTAR_CLIENTE_OK, res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_LISTAR_CLIENTE_ERROR, res);
				break;
			}
			
		}
		case (Eventos.BUSCAR_CLIENTE): {
			int id = (int) datos;
			SACliente saCliente = FactoriaAbstractaNegocio.getInstancia().crearSACliente();
			TCliente res = saCliente.readCliente(id);
			if (res != null) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_BUSCAR_CLIENTE_OK, res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_BUSCAR_CLIENTE_ERROR, res);
				break;
			}
		}
		
		case (Eventos.MODIFICAR_CLIENTE): {
			TCliente cliente = (TCliente) datos;
			SACliente saCliente = FactoriaAbstractaNegocio.getInstancia().crearSACliente();
			boolean res = saCliente.updateCliente(cliente);
			if (res) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_MODIFICAR_CLIENTE_OK, res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_MODIFICAR_CLIENTE_ERROR, res);
				break;
			}
		}
		
		//Servicios
		case (Eventos.ALTA_SERVICIO): {
			TServicio tServicio = (TServicio) datos;
			SAServicio saServicio = FactoriaAbstractaNegocio.getInstancia().crearSAServicio();
			int res = saServicio.createServicio(tServicio);
			if (res != -1) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_SERVICIO_OK,
						res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_ALTA_SERVICIO_ERROR, res);
				break;
			}
			
		}

		case Eventos.BAJA_SERVICIO:{
			int id = (int) datos;
			SAServicio saServicio = FactoriaAbstractaNegocio.getInstancia().crearSAServicio();
			boolean res = saServicio.deleteServicio(id);
			if (res) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
					.actualizar(Eventos.RES_BAJA_SERVICIO_OK,			res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_BAJA_SERVICIO_ERROR, res);
				break;
			}
		}
		
		case Eventos.BUSCAR_SERVICIO:{
			int id = (int) datos;
			SAServicio saServicio = FactoriaAbstractaNegocio.getInstancia().crearSAServicio();
			TServicio res = saServicio.readServicio(id);
			if (res != null) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_BUSCAR_SERVICIO_OK, res);
				break;
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_BUSCAR_SERVICIO_ERROR, res);
				break;
			}
		}
		
		case Eventos.LISTAR_SERVICIO:{
			SAServicio saServicio = FactoriaAbstractaNegocio.getInstancia().crearSAServicio();
			List<TServicio> res = saServicio.readAllServicio();
			if (res != null) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_LISTAR_SERVICIO_OK, res);
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_LISTAR_SERVICIO_ERROR, res);
			}
			break;
		}
		
		case Eventos.MODIFICAR_SERVICIO:{
			TServicio Servicio = (TServicio) datos;
			SAServicio saServicio = FactoriaAbstractaNegocio.getInstancia().crearSAServicio();
			boolean res = saServicio.updateServicio(Servicio);
			if (res) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_MODIFICAR_SERVICIO_OK, res);
			} else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento)
						.actualizar(Eventos.RES_MODIFICAR_SERVICIO_ERROR, res);
			}
			break;
		}
		
		
		}
		
	}
}
