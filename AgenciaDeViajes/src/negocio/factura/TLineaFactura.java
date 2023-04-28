package negocio.factura;

public class TLineaFactura {
	
	private int id;
	private int coste;
	private int plazasVendidas;
	private int idFactura;
	private int idViaje;
	
	public TLineaFactura() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}

	public int getPlazasVendidas() {
		return plazasVendidas;
	}

	public void setPlazasVendidas(int plazasVendidas) {
		this.plazasVendidas = plazasVendidas;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public int getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(int idViaje) {
		this.idViaje = idViaje;
	}
	
	
	
}
