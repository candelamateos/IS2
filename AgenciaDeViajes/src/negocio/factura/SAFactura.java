package negocio.factura;

import java.util.List;

public interface SAFactura {
	
	public int abrirVenta(TFactura Factura);
	
	public boolean cerrarVenta(int id);
	
	public boolean modificarFactura(TFactura factura);
	
	public TFactura readFactura(int id);
	
	public List<TFactura> readAllFactura();
}
