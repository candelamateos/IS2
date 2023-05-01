package negocio.factura;

import java.util.List;

import integracion.cliente.DaoCliente;
import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.factura.DaoFactura;
import integracion.trabajador.DaoTrabajador;
import negocio.cliente.TCliente;
import negocio.trabajador.TTrabajador;

public class SAFacturaImp implements SAFactura{

	@Override
	public int abrirVenta(TFactura Factura) {
		int id = -1;
		DaoFactura d = FactoriaAbstractaIntegracion.getInstancia().crearDaoFactura();
		DaoCliente c = FactoriaAbstractaIntegracion.getInstancia().crearDaoCliente();
		DaoTrabajador t = FactoriaAbstractaIntegracion.getInstancia().crearDaoTrabajador();
		int idVendedor = Factura.getIdVendedor();
		int idCliente = Factura.getIdCliente();
//		TTrabajador trabajador = t.readTrabajador(idVendedor);
		TCliente cliente = c.readCliente(idCliente);
		if(/*trabajador != null && trabajador.isActivo() && trabajador.getTipo().equals("vendedor") &&*/ cliente != null && cliente.getActivo()) {
			return d.abrirFactura(Factura);
		}
		else return -1;
	}

	@Override
	public boolean cerrarVenta(int id) {
		boolean res = false;
		DaoFactura d = FactoriaAbstractaIntegracion.getInstancia().crearDaoFactura();
		TFactura factura = d.readFactura(id);
		if(factura != null && factura.isActivo() && factura.isAbierta() && factura.getCoste() > 0) {
			res = d.cerrarFactura(id);
		}
		
		return res;
	}

	@Override
	public boolean modificarFactura(TFactura factura) {
		boolean res = false;
		DaoFactura d = FactoriaAbstractaIntegracion.getInstancia().crearDaoFactura();
		DaoCliente c = FactoriaAbstractaIntegracion.getInstancia().crearDaoCliente();
		DaoTrabajador t = FactoriaAbstractaIntegracion.getInstancia().crearDaoTrabajador();
		int idVendedor = factura.getIdVendedor();
		int idCliente = factura.getIdCliente();
//		TTrabajador trabajador = t.readTrabajador(idVendedor);
		TCliente cliente = c.readCliente(idCliente);
		if(/*trabajador != null && trabajador.isActivo() && trabajador.getTipo().equals("vendedor") &&*/ cliente != null && cliente.getActivo()) {
			res = d.updateFactura(factura);
		}
		return res;
	}

	@Override
	public TFactura readFactura(int id) {
		DaoFactura d = FactoriaAbstractaIntegracion.getInstancia().crearDaoFactura();
		return d.readFactura(id);
	}

	@Override
	public List<TFactura> readAllFactura() {
		// TODO Auto-generated method stub
		return null;
	}

}
