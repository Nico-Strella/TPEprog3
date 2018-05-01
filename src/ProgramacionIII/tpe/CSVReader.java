package ProgramacionIII.tpe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import tpe.ColeccionLibros;
import tpe.Genero;
import tpe.Indice;
import tpe.Indice2;
import tpe.Libro;

public class CSVReader {

    public static void main(String[] args) {
        String csvFile = "dataset1.csv";
        String line = "";
        String cvsSplitBy = ",";
        boolean firstRegistro = true;
        
        ColeccionLibros coleccion = new ColeccionLibros();
        //Indice index = new Indice();
        Indice2 index = new Indice2();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                String[] items = line.split(cvsSplitBy);
       
                String[] generos = items[3].split(" ");
                
                
                // ------- CREAR LIBRO -------
                if(!firstRegistro) {                	
                	Libro libro = new Libro(items[0], items[1], items[2], generos);
                	 
                	// ------- A�ADIR LIBRO A LISTA DE LIBROS -------
                	coleccion.addLibro(libro);
                	 
                	// ------- CREAR INDICE ------- 
                	String[] g = libro.getGeneros();
                	for(int i = 0; i < g.length; i++) {
                		//index.insert(g[i], libro);
                		index.addGenero(g[i], libro);
                	}         
                	
                	
                }
                firstRegistro = false;               
            }
            
            // ------ OBTENER LISTA LIBROS PERTENECIENTE A GENEROS -------            
            //LinkedList<Libro> librosXgenero = index.buscar("moda");
            ArrayList<Genero> listaGeneros = index.getListaGeneros();
	    		for(int j = 0; j < listaGeneros.size(); j++) {
	    			System.out.println(listaGeneros.get(j).getNombreGenero());
	    		}
	    		
	    		int x = index.buscarGenero("cine");
	    		ArrayList<Libro> l = listaGeneros.get(x).getLibros();
	    		CSVWritter.writeFile(l);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    
}
