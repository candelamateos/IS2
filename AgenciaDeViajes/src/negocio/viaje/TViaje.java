package negocio.viaje;

public class TViaje {

	private int id;
	private int precio;
	private int numPlazas;
	private int idActividad;
	private int idAlojamiento;
	private int idTransporte;
	private boolean activo;

	public TViaje() {
	}
	
	public TViaje(int precio, int numPlazas, int idActividad, int idAlojamiento, int idTransporte) {
		this.precio = precio;
		this.numPlazas = numPlazas;
		this.idActividad = idActividad;
		this.idAlojamiento = idAlojamiento;
		this.idTransporte = idTransporte;
		this.activo = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getNumPlazas() {
		return numPlazas;
	}

	public void setNumPlazas(int numPlazas) {
		this.numPlazas = numPlazas;
	}

	public int getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(int idActividad) {
		this.idActividad = idActividad;
	}

	public int getIdAlojamiento() {
		return idAlojamiento;
	}

	public void setIdAlojamiento(int idAlojamiento) {
		this.idAlojamiento = idAlojamiento;
	}

	public int getIdTransporte() {
		return idTransporte;
	}

	public void setIdTransporte(int idTransporte) {
		this.idTransporte = idTransporte;
	}
		

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
