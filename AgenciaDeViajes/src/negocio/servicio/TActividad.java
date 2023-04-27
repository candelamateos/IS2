package negocio.servicio;

public class TActividad extends TServicio{



	protected String tipo;
	protected boolean colectivo;
	
	public TActividad(int id, String nombre, int numPlazas, int precio, String tipo, boolean colectivo) {
		super(id, nombre, numPlazas, precio);
		this.tipo = tipo;
		this.colectivo = colectivo;
	}
	
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isColectivo() {
		return colectivo;
	}

	public void setColectivo(boolean colectivo) {
		this.colectivo = colectivo;
	}
}
