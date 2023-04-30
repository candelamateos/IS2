package presentacion.controlador;

import java.util.List;

import negocio.cliente.SACliente;
import negocio.cliente.TCliente;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.viaje.SAViaje;
import negocio.viaje.TViaje;
import presentacion.factoria.FactoriaAbstractaPresentacion;

public class ControladorImp extends Controlador {
	public void accion(int evento, Object datos) {
		switch (evento) {
		
		//Departamento
		case (Eventos.ALTA_DEPARTAMENTO): {
			TDepartamento tDepartamento = (TDepartamento) datos;
			SADepartamento saDepartamento = FactoriaAbstractaNegocio.getInstancia().crearSADepartamento();
//			int res = saDepartamento.createDepartamento(tDepartamento);
			// TODO segun el valor de res, se actualiza la vista de una manera u otra.
			// Si todo ok el aspecto es este(falta el else)
//			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_DEPARTAMENTO_OK,res);
			// ...
			// break;} }
		}
		
		//Factura
		
		//Servicio
		
		//Trabajador
		
		//Viaje
		case (Eventos.ALTA_VIAJE): {
			TViaje tViaje = (TViaje) datos;
			SAViaje saViaje = FactoriaAbstractaNegocio.getInstancia().crearSAViaje();
			int res = saViaje.createViaje(tViaje);
			if(res != -1) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_VIAJE_OK,res);
				break;
			}
			else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_VIAJE_ERROR,res);
				break;
			}
		}
		case (Eventos.BAJA_VIAJE): {
			int id = (int) datos;
			SAViaje saViaje = FactoriaAbstractaNegocio.getInstancia().crearSAViaje();
			boolean res = saViaje.deleteViaje(id);
			if(res) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BAJA_VIAJE_OK,res);
				break;
			}
			else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BAJA_VIAJE_ERROR,res);
				break;
			}
		}
		case (Eventos.BUSCAR_VIAJE): {
			int id = (int) datos;
			SAViaje saViaje = FactoriaAbstractaNegocio.getInstancia().crearSAViaje();
			TViaje res = saViaje.readViaje(id);
			if(res != null) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_VIAJE_OK,res);
				break;
			}
			else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BUSCAR_VIAJE_ERROR,res);
				break;
			}
		}
		case (Eventos.LISTAR_VIAJE): {
			SAViaje saViaje = FactoriaAbstractaNegocio.getInstancia().crearSAViaje();
			List<TViaje> res = saViaje.readAllViaje();
			if(res != null) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_LISTAR_VIAJE_OK,res);
				break;
			}
			else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_LISTAR_VIAJE_ERROR,res);
				break;
			}
		}
		case (Eventos.MODIFICAR_VIAJE): {
			int id = (int) datos;
			SAViaje saViaje = FactoriaAbstractaNegocio.getInstancia().crearSAViaje();
			boolean res = saViaje.updateViaje(saViaje.readViaje(id));
			if(res) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MODIFICAR_VIAJE_OK,res);
				break;
			}
			else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_MODIFICAR_VIAJE_ERROR,res);
				break;
			}
		}
		
		//Clientes
		case(Eventos.ALTA_CLIENTE): {
			TCliente tCliente = (TCliente) datos;
			SACliente saCliente = FactoriaAbstractaNegocio.getInstancia().crearSACliente();
			int res = saCliente.createCliente(tCliente);
			if(res != -1) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_CLIENTE_OK,res);
			}
			else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ALTA_CLIENTE_ERROR,res);
			}
		}
		case(Eventos.BAJA_CLIENTE): {
			int id = (int) datos;
			SACliente saCliente = FactoriaAbstractaNegocio.getInstancia().crearSACliente();
			boolean res = saCliente.deleteCliente(id);
			if(res) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BAJA_CLIENTE_OK,res);
			}
			else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BAJA_CLIENTE_ERROR,res);
			}
		}
		case(Eventos.LISTAR_CLIENTE): {
			SACliente saCliente = FactoriaAbstractaNegocio.getInstancia().crearSACliente();
			List<TCliente> res = saCliente.readAllCliente();
			if(res != null) {
			FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_LISTAR_CLIENTE_OK,res);
			}
			else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_BAJA_CLIENTE_ERROR,res);
			}
		}
		}
	}
}
