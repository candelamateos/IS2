package negocio.servicio;

public class TAlojamiento extends TServicio {
	
	private String regimen;
	private int estrellas;
	
	public TAlojamiento(String nombre, int numPlazas, int precio, String regimen, int estrellas) {
		super(nombre, numPlazas, precio, "alojamiento");
		this.regimen = regimen;
		this.estrellas = estrellas;
	}

	public String getRegimen() {
		return regimen;
	}

	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}

	public int getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}
	
	
	

}
