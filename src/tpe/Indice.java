package tpe;

import java.util.LinkedList;

public class Indice{
	
	private class Genero{
		private Indice hi;
		private Indice hd;
		private String nombreGenero;
		private LinkedList<Libro> indiceLibros;
		
		private Genero() {
			nombreGenero = null;
			hi = null;
			hd = null;
			indiceLibros = new LinkedList<>();
		}
	}
	private Genero root;
	
	public Indice() {
		root = new Genero();
	}
	
	// ------- METODOS ARBOL -------
	
	public Genero getRoot() {
		return root;
	}
	
	public Indice getHijoIzq() {
		return root.hi;
	}
	
	public Indice getHijoDer() {
		return root.hd;
	}
	
	public boolean isEmpty() {
		return (root.nombreGenero == null);
	}
	
	private int comparar(String genero1, String genero2) {
		return genero1.compareTo(genero2);
	}
	
	public void insert(String genero, Libro libro) {
		if (isEmpty()) {
			Genero nuevo = new Genero();
            nuevo.nombreGenero = genero;
            nuevo.hd = new Indice();
            nuevo.hi = new Indice();
            nuevo.indiceLibros.add(libro);
            root = nuevo;
        }
        else {
            if (comparar(genero, root.nombreGenero) < 0) {
            	(root.hi).insert(genero, libro);
            }else if(comparar(genero, root.nombreGenero) > 0) {
            	(root.hd).insert(genero, libro);
            }else {
            	root.indiceLibros.add(libro);
            }
        }

	}
	
	public LinkedList<Libro> buscar(String genero){
        LinkedList<Libro> listaLibros = null;
        if (!isEmpty()) {
        	//cuento por cuantos nodos pase antes de encontrar el genero deseado
        	Contador.aumentarContador();;
        	
            if (genero.equals(root.nombreGenero)) {
            	return root.indiceLibros;
            }
            else {
                if (comparar(genero,root.nombreGenero) < 0) {
                	listaLibros = root.hi.buscar(genero);
                }
                else {
                	listaLibros = root.hd.buscar(genero);
                }
            }
        }
        return listaLibros;
    } 
    
    public boolean hasElem(String genero){
        if (!isEmpty()) {
        	if (genero.equals(root.nombreGenero)) {
                return true;
        	}else {
        		if (comparar(genero, root.nombreGenero)< 0) {
        			return root.hi.hasElem(genero);
        		}else {
        			return root.hd.hasElem(genero);
        		}
        	}
        }
        return false;
    }
     
 
    public void inOrder(){
        if (!isEmpty()) {
            root.hi.inOrder();
            System.out.print( root.nombreGenero + "\n"  );
            root.hd.inOrder();
        }
    }   
}
