package negocio.factura;

import java.util.List;

public interface SAFactura {
	
	public boolean createFactura(TFactura Factura);
	
	public TFactura readFactura(int id);
	
	public List<TFactura> readAllFactura();
}
