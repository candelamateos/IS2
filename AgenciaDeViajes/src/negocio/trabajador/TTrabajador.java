package negocio.trabajador;

import java.util.Collection;

public abstract class TTrabajador {
	
	private int id;
	private String nombre;
	private boolean activo;
	private int sueldo;
	private String tipo;
	
	public TTrabajador() {
	}
	
	public TTrabajador(String nombre, int sueldo, String tipo) {
		this.nombre = nombre;
		this.sueldo = sueldo;
		this.activo = true;
		this.tipo = tipo;
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

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public abstract int getIdJefe();
	
	public abstract void setIdJefe(int idJefe);
}
