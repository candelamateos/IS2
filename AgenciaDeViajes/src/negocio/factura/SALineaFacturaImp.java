package negocio.factura;

import integracion.factoria.FactoriaAbstractaIntegracion;
import integracion.factura.DaoFactura;
import integracion.viaje.DaoViaje;
import negocio.viaje.TViaje;

public class SALineaFacturaImp implements SALineaFactura {

	@Override
	public boolean createLineaFactura(TLineaFactura linea) {
		boolean res = false;
		int idFactura = linea.getIdFactura();
		int idViaje = linea.getIdViaje();
		int plazas = linea.getPlazasVendidas();
		DaoFactura f = FactoriaAbstractaIntegracion.getInstancia().crearDaoFactura();
		DaoViaje v = FactoriaAbstractaIntegracion.getInstancia().crearDaoViaje();
		
		TFactura factura = f.readFactura(idFactura);
		TViaje viaje = v.readViaje(idViaje);
		if(factura != null && factura.isActivo() && factura.isAbierta() && viaje != null && viaje.getActivo() && viaje.getNumPlazas() > plazas) {
			linea.setCoste(plazas * viaje.getPrecio());
			viaje.setNumPlazas(viaje.getNumPlazas() - plazas);
			factura.setCoste(factura.getCoste() + linea.getCoste());
			if(f.addViaje(linea) != -1 ) res = f.updateFactura(factura);
			if(res) res = v.updateViaje(viaje);
		}
		
		return res;
	}

}
