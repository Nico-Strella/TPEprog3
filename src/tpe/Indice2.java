package tpe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Indice2{	
	
	private ArrayList<Genero> listaGeneros;
	
	public Indice2() {
		listaGeneros = new ArrayList<>();
	}
	
	public void addGenero(String genero, Libro libro) {
		boolean contiene = false;
		Genero g = new Genero(genero);
		GeneroComparator comparador = new GeneroComparator();
		int i = 0;
		while(i < listaGeneros.size() && !contiene) {
			if(comparador.compare(listaGeneros.get(i), g) == 0) {
				contiene = true;
			}
			i++;
		}
		
		if(!contiene) {
			g.addLibro(libro);
			listaGeneros.add(g);
		}else {
			int j = buscarGenero(genero);
			listaGeneros.get(j).addLibro(libro);
		}
		
		
		Collections.sort(listaGeneros, comparador);		
	}
	
	public int buscarGenero(String genero) {
		Genero g = new Genero(genero);
		GeneroComparator comparador = new GeneroComparator(); 
		return Collections.binarySearch(listaGeneros, g, comparador);
	}
	
	public ArrayList<Genero> getListaGeneros(){
		return listaGeneros;
	}
}
