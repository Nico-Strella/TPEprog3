package tpe2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GrafoGeneros {
    
    private final Integer N; //Cantidad de nodos
    private Integer lastValue; //valor para asignarle en la matriz a la palabra del genero
    private HashMap<String, Integer>[] lista; // Creacion de la matriz
	private HashMap<String, Integer> myMap;  // Guardo el indice en la matriz con el nombre
	private ArrayList<String> Indice; //Guardo el orden en que se guardan las categorias
	
    
    public GrafoGeneros(Integer n) {
        this.N = n;
        this.lastValue = 0;
        lista = new HashMap[N];
        this.Indice = new ArrayList<String>();
        this.myMap = new HashMap<String, Integer>();
        for(int i = 0; i < N; i++) {
        	lista[i] = new HashMap<>();
        }
    }
        
    public void agregar(String c1, String c2, Integer peso){
		Integer genero1 = this.myMap.get(c1);
		Integer genero2 = this.myMap.get(c2);
		if (genero1 == null ) {
			genero1 = this.getNewValue(c1);
		}
		if (genero2 == null ) {
			this.getNewValue(c2);
		}
		
		Integer valor = lista[genero1].get(c2);
		if(valor == null) {
			lista[genero1].put(c2, peso);
		}else {
			lista[genero1].put(c2, valor+1);
		}
		
    }
    
    private Integer getNewValue(String key) {
		this.Indice.add(key);
		Integer value = lastValue;
		this.myMap.put(key, value);
		this.lastValue++;
		return value;
	}
    
    public ArrayList<NodoCamino> getCamino(String cat, int cantCaminosPedidos){
    		ArrayList<NodoCamino> hijos = new ArrayList<>();
    		ArrayList<NodoCamino> result = new ArrayList<>();
    		int indice = myMap.get(cat);
    		
    		for(Map.Entry e : lista[indice].entrySet()) {
				NodoCamino nodo = new NodoCamino((String)e.getKey(),(Integer)e.getValue());
				hijos.add(nodo);
    		}

    		Collections.sort(hijos, new Comparador().reversed());
    		int cantGeneros = hijos.size();
    		if (cantGeneros < cantCaminosPedidos ) {
    			cantCaminosPedidos = cantGeneros;
    		}
    		
    		for (int i = 0; i < cantCaminosPedidos; i++) {
    			result.add(hijos.get(i)); 
    		}
    		this.imprimirArrayList(result);
		return result;
    }
    
    public Set<String> dfs(String cat){
    	boolean[] visitado = new boolean[N];
    	Set<String> descendientes = new HashSet<>();
    	
    	dfs_visitar(myMap.get(cat), visitado, descendientes);
    	
    	return descendientes;
    }
    
    private void dfs_visitar(int v, boolean visitado[], Set<String> desc) {
    	visitado[v] = true;
    	
    	for(Map.Entry e : lista[v].entrySet()) {
    		Integer index = myMap.get(e.getKey());
    		if(visitado[index] == false) {
				dfs_visitar(index, visitado, desc);
			}
    	}
    	desc.add(Indice.get(v));
    }
    
    public Set<String> dfsCiclo() {
    	Set<String> conCiclo = new HashSet<>();
    	boolean[] visitado = new boolean[N];
		boolean hayCiclo = false;		
		
		for(int i = 0; i < Indice.size(); i++) {
			int j = 0;
			while(j < lista[i].size() && !hayCiclo) {
				hayCiclo = hayCiclo(i, visitado, conCiclo);
				if(hayCiclo) {
					conCiclo.add(Indice.get(i));
				}
				j++;
			}
    	}
		
		return conCiclo;	
	}
	
	private boolean hayCiclo(int v, boolean visitado[], Set<String> conCiclo) {
		visitado[v] = true;
		boolean hayCiclo = false;
		
		for(Map.Entry e : lista[v].entrySet()) {
			if(!hayCiclo) {
				Integer hijo = myMap.get(e.getKey()); 
				if(!visitado[hijo]) {
					hayCiclo = hayCiclo(hijo, visitado, conCiclo);
				}else {
					hayCiclo = true;
				}
			}
		}
		return hayCiclo;
	}
    
    public void imprimirArrayList(ArrayList<NodoCamino>  Arr) {
		int size = Arr.size();
		for(Integer i=0; i< size; i++){
			 System.out.print( Arr.get(i).getGenero() + " " +  Arr.get(i).getCantBusquedas() + " / "); 
		}
		System.out.println();
	}
}