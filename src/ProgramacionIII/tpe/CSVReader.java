package ProgramacionIII.tpe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import ProgramacionIII.util.Timer;
import tpe.ColeccionLibros;
import tpe.Contador;
import tpe.Indice;
import tpe.Libro;

public class CSVReader {

    public static void main(String[] args) {
        String csvFile = "dataset4.csv";
        String line = "";
        String cvsSplitBy = ",";
        boolean firstRegistro = true;
        
        ColeccionLibros coleccion = new ColeccionLibros();
        Indice index = new Indice();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {   
        	Timer t = new Timer();
        	t.start();
            while ((line = br.readLine()) != null) {

                String[] items = line.split(cvsSplitBy);
       
                String[] generos = items[3].split(" ");
                
                
                // ------- CREAR LIBRO -------
                if(!firstRegistro) {
                	Libro libro = new Libro(items[0], items[1], items[2], generos);
                	 
                	// ------- Aï¿½ADIR LIBRO A LISTA DE LIBROS -------
                	coleccion.addLibro(libro);
                	 
                	// ------- CREAR INDICE ------- 
                	String[] g = libro.getGeneros();
                	for(int i = 0; i < g.length; i++) {
                		index.insert(g[i], libro);
                	}
                }
                firstRegistro = false;
            }
            
            System.out.println("Se tardo "+t.stop()+" milisegundos en generar las estructuras.\n");
            // ------ OBTENER LISTA LIBROS PERTENECIENTE A GENEROS -------
            System.out.println("Ingrese la categoria que desea buscar libros: ");
	    	Scanner s = new Scanner(System.in);
	    	String cat = s.nextLine();
	    	s.close();
	    	
	    	LinkedList<Libro> librosXgenero = index.buscar(cat);
	    	
	    	// ------- TEST NODOS VISITADOS -------
	    	System.out.println("\nSe visitaron "+Contador.getContador()+" nodos antes de encontrar el genero buscado.");
	    	Contador.resetContador();
	    	// ------- FIN TEST -------
	    	if(librosXgenero != null) {
	    		CSVWritter.writeFile(librosXgenero);
	    	}else {
	    		System.out.println("Lo sentimos, el género ingresado no existe. El programa se cerrará.");
	    	}

        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}
