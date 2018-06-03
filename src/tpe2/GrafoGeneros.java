package tpe2;

import tpe.Contador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GrafoGeneros {

	private final Integer N; //Cantidad de nodos
	private Integer lastValue; //valor para asignarle en la matriz a la palabra del genero

	private ArrayList<HashMap<String, Integer>> lista;
	private HashMap<String, Integer> myMap;  // Guardo el indice en la matriz con el nombre
	private ArrayList<String> Indice; //Guardo el orden en que se guardan las categorias

	private static final String noVisitado  = "Blanco";
	private static final String Visitado = "Amarillo";
	private static final String VisitadoPlus = "Negro";


	public GrafoGeneros(Integer n) {
		this.N = n;
		this.lastValue = 0;
		lista = new ArrayList<>();
		this.Indice = new ArrayList<String>();
		this.myMap = new HashMap<String, Integer>();
	}

	public void agregar(String c1, String c2, Integer peso) {

		Integer desdeGenero = this.myMap.get(c1);
		Integer hastaGenero = this.myMap.get(c2);

		if (desdeGenero == null) {
			desdeGenero = this.getNewValue(c1);
			lista.add(desdeGenero,new HashMap<>());
		}
		if (hastaGenero == null) {
			hastaGenero = this.getNewValue(c2);
			lista.add(hastaGenero,new HashMap<>());
		}

		Integer valor = lista.get(desdeGenero).get(c2);

		if (valor == null) {
			lista.get(desdeGenero).put(c2, peso);
		} else {
			lista.get(desdeGenero).put(c2, valor + 1);
		}

	}

	private Integer getNewValue(String key) {
		this.Indice.add(key);
		Integer value = lastValue;
		this.myMap.put(key, value);
		this.lastValue++;
		return value;
	}

	public ArrayList<NodoCamino> getCamino(String cat, int cantCaminosPedidos) {
		ArrayList<NodoCamino> hijos = new ArrayList<>();
		ArrayList<NodoCamino> result = new ArrayList<>();
		int indice = myMap.get(cat);

		for (Map.Entry e : lista.get(indice).entrySet()) {
			NodoCamino nodo = new NodoCamino((String) e.getKey(), (Integer) e.getValue());
			hijos.add(nodo);
		}

		Collections.sort(hijos, new Comparador().reversed());
		int cantGeneros = hijos.size();
		if (cantGeneros < cantCaminosPedidos) {
			cantCaminosPedidos = cantGeneros;
		}

		for (int i = 0; i < cantCaminosPedidos; i++) {
			result.add(hijos.get(i));
		}
		//this.imprimirArrayList(result);
		return result;
	}

	public Set<String> dfs(String cat) {
		boolean[] visitado = new boolean[N];
		Set<String> descendientes = new HashSet<>();
		System.out.println("El contador tiene un valor inicial de: "+Contador.getContador());
		Contador.resetContador();
		dfs_visitar(myMap.get(cat), visitado, descendientes);

		return descendientes;
	}

	private void dfs_visitar(int v, boolean visitado[], Set<String> desc) {
		Contador.aumentarContador();
		visitado[v] = true;

		for (Map.Entry e : lista.get(v).entrySet()) {
			Integer index = myMap.get(e.getKey());
			if (visitado[index] == false) {
				dfs_visitar(index, visitado, desc);
			}
		}
		desc.add(Indice.get(v));
	}

	private void imprimirArrayList(ArrayList<NodoCamino> Arr) {
		int size = Arr.size();
		for (Integer i = 0; i < size; i++) {
			System.out.print(Arr.get(i).getGenero() + " " + Arr.get(i).getCantBusquedas() + " / ");
		}
		System.out.println();
	}

	/*
		PARA CADA vértice v HACER
		estado[v] = BLANCO
		padre[v] = NULO
		adyacentes ← Vecinos[v]
		MIENTRAS i<adyacentes.size Y NO hayCiclo
		SI estado[adyacentes[i]] = BLANCO ENTONCES
		hayCiclo = Hay_Ciclo(v)
	*/
	public GrafoGeneros DFS_Ciclo(String cat) {
		String[] estadoNodo = new String[this.Indice.size()];
		Integer[] padre = new Integer[this.Indice.size()];
		int size = estadoNodo.length;
		for (int i = 0; i < size; i++) {
			estadoNodo[i] = this.noVisitado;
			padre[i] = null;
		}

		int nodo = myMap.get(cat);
		boolean hayCiclo = false;
		for (Map.Entry e : lista.get(nodo).entrySet()) {
			if (!hayCiclo) {
				Integer adyacente = myMap.get(e.getKey());
				if (estadoNodo[adyacente] == this.noVisitado) {
					hayCiclo = this.hayCiclo(nodo, padre, estadoNodo);
				}
			}
		}

		return armarCamino(padre,hayCiclo,nodo);

	}

	private GrafoGeneros armarCamino(Integer[] padre, boolean hayCiclo,int nodoInicio) {
		GrafoGeneros G = null;
		if (hayCiclo){
			G = new GrafoGeneros(this.N);
			int size = padre.length;
			int i = nodoInicio;
			do {
				String desde = Indice.get(padre[i]);
				String hasta = Indice.get(i);
				G.agregar(desde,hasta,1);
				i = padre[i];
			} while (i != nodoInicio);
		}
		return G;
	}

	private boolean hayCiclo(int nodo, Integer[] padre, String[] estadoNodo) {
		estadoNodo[nodo] = this.Visitado;
		boolean hayCiclo = false;
		for (Map.Entry e : lista.get(nodo).entrySet()) {
			Integer adyacente = myMap.get(e.getKey());
			if (!hayCiclo) {
				if (estadoNodo[adyacente] == this.noVisitado) {
					hayCiclo = this.hayCiclo(adyacente, padre, estadoNodo);
				} else {
					if (estadoNodo[nodo] == this.VisitadoPlus) {
						hayCiclo = true;
					}
				}
			}
			padre[adyacente] = nodo;
		}
		estadoNodo[nodo] = this.VisitadoPlus;
		return hayCiclo;
	}

	public void imprimirCamino() {

	}
}