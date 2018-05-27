package tpe2;

import java.util.Comparator;

import tpe2.NodoCamino;

public class Comparador implements Comparator<NodoCamino> {
	

	@Override
	public int compare(NodoCamino o1, NodoCamino o2) {
		return o1.getCantBusquedas().compareTo(o2.getCantBusquedas());
	}
}
