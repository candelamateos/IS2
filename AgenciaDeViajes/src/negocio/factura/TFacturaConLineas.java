package negocio.factura;

import java.util.List;

public class TFacturaConLineas {
	
	public TFacturaConLineas(TFactura factura, List<TLineaFactura> lineas) {
		super();
		this.factura = factura;
		this.lineas = lineas;
	}
	private TFactura factura;
	private List<TLineaFactura> lineas;
	public TFactura getFactura() {
		return factura;
	}
	public void setFactura(TFactura factura) {
		this.factura = factura;
	}
	public List<TLineaFactura> getLineas() {
		return lineas;
	}
	public void setLineas(List<TLineaFactura> lineas) {
		this.lineas = lineas;
	}
}
