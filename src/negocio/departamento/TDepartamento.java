package negocio.departamento;

public class TDepartamento {
	private int id;
	private String nombre;
	private int numEmpleados;
	
	public TDepartamento() {
	}
	
	public TDepartamento(String nombre) {
		this.nombre=nombre;
		this.numEmpleados=0;
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
	public int getNumEmpleados() {
		return numEmpleados;
	}
	public void setNumEmpleados(int numEmpleados) {
		this.numEmpleados = numEmpleados;
	}
}
