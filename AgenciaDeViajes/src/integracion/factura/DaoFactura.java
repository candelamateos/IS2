package integracion.factura;

import java.util.List;

import negocio.factura.TFactura;

public interface DaoFactura {

	public int createFactura(TFactura Factura);
	
	public TFactura readFactura(TFactura Factura);
	
	public List<TFactura> readAllFactura(TFactura Factura);
}
