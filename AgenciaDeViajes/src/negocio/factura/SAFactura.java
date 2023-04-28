package negocio.factura;

import java.util.List;

public interface SAFactura {
	
	public int createFactura(TFactura Factura);
	
	public TFactura readFactura(TFactura Factura);
	
	public List<TFactura> readAllFactura(TFactura Factura);
}
