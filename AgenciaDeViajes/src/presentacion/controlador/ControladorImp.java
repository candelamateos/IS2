package presentacion.controlador;

import negocio.cliente.SACliente;
import negocio.cliente.TCliente;
import negocio.departamento.SADepartamento;
import negocio.departamento.TDepartamento;
import negocio.factoria.FactoriaAbstractaNegocio;
import negocio.factura.SAFactura;
import negocio.factura.TFactura;
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
		case(Eventos.ABRIR_VENTA):{
			TFactura tFactura = (TFactura) datos;
			SAFactura saFactura = FactoriaAbstractaNegocio.getInstancia().crearSAFactura();
			int res = saFactura.abrirVenta(tFactura);
			if(res != -1) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ABRIR_VENTA_OK,res);
			}
			else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_ABRIR_VENTA_ERROR,res);
			}
		break;}
		case(Eventos.CERRAR_VENTA):{
			int id = (int) datos;
			SAFactura saFactura = FactoriaAbstractaNegocio.getInstancia().crearSAFactura();
			boolean res = saFactura.cerrarVenta(id);
			if(res) {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_CERRAR_VENTA_OK,res);
			}
			else {
				FactoriaAbstractaPresentacion.getInstance().createVista(evento).actualizar(Eventos.RES_CERRAR_VENTA_ERROR,res);
			}
		break;}
			
			
		//Servicio
		
		//Trabajador
		
		//Viaje
		
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
		break;}
		}
	}
}
