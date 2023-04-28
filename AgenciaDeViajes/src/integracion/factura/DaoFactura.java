package integracion.factura;

import java.util.List;

import negocio.factura.TFactura;

public interface DaoFactura {

	public boolean createFactura(TFactura Factura);
	
	public TFactura readFactura(int id);
	
	public List<TFactura> readAllFactura();
}
