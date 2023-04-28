package negocio.servicio;

public class TServicio {
	protected int id;
	protected String nombre;
	protected int numPlazas;
	protected int precio;
	protected String tipo;
	protected boolean activo;
	
	
	public TServicio(String nombre, int numPlazas, int precio, String tipo) {
		this.nombre = nombre;
		this.numPlazas = numPlazas;
		this.precio = precio;
		this.tipo = tipo;
		this.activo = true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumPlazas() {
		return numPlazas;
	}
	public void setNumPlazas(int numPlazas) {
		this.numPlazas = numPlazas;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
