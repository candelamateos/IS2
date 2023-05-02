package negocio.trabajador;

import java.util.Collection;

public class TTrabajador {
	
	private int id;
	private String nombre;
	private boolean activo;
	private int sueldo;
	private int idDepart;
	private String tipo;
	private int idJefe;
	
	public TTrabajador() {
	}
	
	public TTrabajador(String nombre, int sueldo, int idDepart, String tipo, int idJefe) {
		this.sueldo = sueldo;
		this.activo = true;
		this.idDepart = idDepart;
		this.tipo = tipo;
		this.idJefe = idJefe;
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
	
	public int getIdDepart() {
		return this.idDepart;
	}

	public void setIdDepart(int id) {
		this.id = id;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public int getIdJefe() {
		return idJefe;
	}

	public void setIdJefe(int idJefe) {
		this.idJefe = idJefe;
	}
}
