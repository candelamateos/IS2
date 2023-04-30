package negocio.factura;

public class TFactura {
	
	private int id;
	private int coste;
	private int idCliente;
	private int idVendedor;
	private boolean abierta;
	private boolean activo;
	
	public TFactura(int idCliente, int idVendedor) {
		this.idCliente = idCliente;
		this.idVendedor = idVendedor;
		abierta = true;
		activo = true;
		coste = 0;
	}

	public TFactura() {
		
	}

	public boolean isAbierta() {
		return abierta;
	}

	public void setAbierta(boolean abierta) {
		this.abierta = abierta;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
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

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}
}
