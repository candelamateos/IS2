package negocio.servicio;

public class TActividad extends TServicio{

	private String tipoActividad;
	private boolean colectivo;
	
	public TActividad(String nombre, int numPlazas, int precio, String tipoActividad, boolean colectivo) {
		super(nombre, numPlazas, precio, "Actividad");
		this.tipoActividad = tipoActividad;
		this.colectivo = colectivo;
	}
	
	
	public String getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(String tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public boolean isColectivo() {
		return colectivo;
	}

	public void setColectivo(boolean colectivo) {
		this.colectivo = colectivo;
	}
}
