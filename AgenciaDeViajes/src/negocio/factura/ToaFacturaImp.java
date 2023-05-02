package negocio.factura;

import java.util.Iterator;
import java.util.List;

import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.factura.DaoFactura;

public class ToaFacturaImp implements ToaFactura {

	@Override
	public TFacturaConLineas readFactura(int id) {
		TFacturaConLineas facturaConLineas = null;
		
		DaoFactura f = FactoriaAbstractaIntegracion.getInstancia().crearDaoFactura();
		TFactura factura = f.readFactura(id);
		List<TLineaFactura> lineas = f.readAllLineaFactura();
		if(factura != null) {
			Iterator<TLineaFactura> it = lineas.iterator();
			while(it.hasNext()) {
				TLineaFactura linea = it.next();
				if(linea.getIdFactura() != factura.getId()) it.remove();
			}
			
			facturaConLineas = new TFacturaConLineas(factura, lineas);
		}
		return facturaConLineas;
	}

}
