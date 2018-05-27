package tpe2;

public class NodoCamino{
	private String genero;
	private Integer cantBusquedas;
	public NodoCamino (String genero,Integer cantBusquedas) {
		this.genero = genero;
		this.cantBusquedas = cantBusquedas;
	}
	
	public Integer getCantBusquedas() {
		return cantBusquedas;
	}
	
	public String getGenero() {
		return genero;
	}
}
