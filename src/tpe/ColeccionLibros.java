package tpe;

import java.util.LinkedList;

public class ColeccionLibros {
	private LinkedList<Libro> coleccion;
	
	// ------- CONSTRUCTOR -------
	
	public ColeccionLibros() {
		coleccion = new LinkedList<>();
	}
	
	public void addLibro(Libro libro) {
		coleccion.add(libro);
	}
	
	public LinkedList<Libro> getColeccion(){
		return coleccion;
	}
	
	
}
