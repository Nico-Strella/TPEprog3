package tpe;

import java.util.ArrayList;
import java.util.Comparator;

public class Genero{
	private String nombreGenero;
	private ArrayList<Libro> indiceLibros;
	
	public Genero(String genero) {
		nombreGenero = genero;
		indiceLibros = new ArrayList<>();
	}
	
	public void addLibro(Libro libro) {
		indiceLibros.add(libro);
	}
	
	public ArrayList<Libro> getLibros(){
		return indiceLibros;
	}
	
	public String getNombreGenero() {
		return nombreGenero;
	}	
}
