package tpe;

import java.util.Comparator;

public class GeneroComparator implements Comparator<Genero>{
	@Override
	public int compare(Genero genero1, Genero genero2) {
		return genero1.getNombreGenero().compareTo(genero2.getNombreGenero());
	}
}
