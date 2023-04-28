package negocio.servicio;

public class TTransporte extends TServicio{

	protected String tipoTransporte;
	protected boolean comida;
	
	public TTransporte(String nombre, int numPlazas, int precio, String tipoTransporte, boolean comida) {
		super(nombre, numPlazas, precio, "Transporte");
		this.tipoTransporte = tipoTransporte;
		this.comida = comida;
	}

	public String getTipoTransporte() {
		return tipoTransporte;
	}

	public void setTipoTransporte(String tipoTransporte) {
		this.tipoTransporte = tipoTransporte;
	}

	public boolean isComida() {
		return comida;
	}

	public void setComida(boolean comida) {
		this.comida = comida;
	}
	
	
	
}
