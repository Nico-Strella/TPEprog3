package tpe2;

public class NodoCamino{
	private String genero;
	private Integer cantBusquedas;
	public NodoCamino (String genero,Integer peso) {
		this.genero = genero;
		this.cantBusquedas = peso;
	}
	
	public Integer getCantBusquedas() {
		return cantBusquedas;
	}
	
	public String getGenero() {
		return genero;
	}
}
