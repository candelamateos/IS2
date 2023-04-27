package negocio.servicio;

public class TTransporte extends TServicio{

	protected String tipo;
	protected boolean comida;
	
	public TTransporte(int id, String nombre, int numPlazas, int precio, String tipo, boolean comida) {
		super(id, nombre, numPlazas, precio);
		this.tipo = tipo;
		this.comida = comida;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isComida() {
		return comida;
	}

	public void setComida(boolean comida) {
		this.comida = comida;
	}
	
	
	
}
