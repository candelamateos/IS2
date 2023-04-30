package integracion.factura;

import java.util.List;

import negocio.factura.TFactura;
import negocio.factura.TLineaFactura;

public interface DaoFactura {

	public int abrirFactura(TFactura Factura);
	
	public int addViaje(TLineaFactura lfactura);
	
	public boolean updateFactura(TFactura Factura);
	
	public boolean cerrarFactura(int id);
	
	public TFactura readFactura(int id);
	
	public List<TFactura> readAllFactura();
}
