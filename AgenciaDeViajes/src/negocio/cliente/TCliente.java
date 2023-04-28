package negocio.cliente;

public class TCliente {
	
	private int id;

	private String nombre;

	private Boolean activo;

	public TCliente() {
		
	}
	
	public TCliente(String _nombre) {
		//EL id lo pone el DAOClienteImp
		nombre = _nombre;
		activo = true;
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

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}
