package ProgramacionIII.tpe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import tpe.ColeccionLibros;
import tpe.Indice;
import tpe.Libro;

public class CSVReader {

    public static void main(String[] args) {
        String csvFile = "dataset1.csv";
        String line = "";
        String cvsSplitBy = ",";
        boolean firstRegistro = true;
        
        ColeccionLibros coleccion = new ColeccionLibros();
        Indice index = new Indice();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] items = line.split(cvsSplitBy);
       
                String[] generos = items[3].split(" ");
                
                
                // ------- CREAR LIBRO -------
                if(!firstRegistro) {                	
                	Libro libro = new Libro(items[0], items[1], items[2], generos);
                	 
                	// ------- AÑADIR LIBRO A LISTA DE LIBROS -------
                	coleccion.addLibro(libro);
                	 
                	// ------- CREAR INDICE ------- 
                	String[] g = libro.getGeneros();
                	for(int i = 0; i < g.length; i++) {
                		index.insert(g[i], libro);
                	}
                }
                firstRegistro = false;               
            }
            
            LinkedList<Libro> test = index.buscar("fotografia");
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    
}
