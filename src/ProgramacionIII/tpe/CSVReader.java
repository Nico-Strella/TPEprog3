package ProgramacionIII.tpe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import ProgramacionIII.util.Timer;
import tpe2.GrafoGeneros;

public class CSVReader {

    public static void main(String[] args) {
        String csvFile = "dataset6.csv";
        String line = "";
        String cvsSplitBy = ",";
        HashMap<String,Integer> Hash = new HashMap<>();
        boolean isFirst = true;
        
        GrafoGeneros Grafo = new GrafoGeneros(40); 

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {   
	        	Timer t = new Timer();
	        	t.start();
            while ((line = br.readLine()) != null) {
            	if (!isFirst) {
            		 String[] items = line.split(cvsSplitBy);
                     
                 	int size = items.length;
                 	Hash.put(items[0],1);
                 	
                 	// ------- AGREGO LA BUSQUEDA AL GRAFO -------
                 for(int i = 0; i<size-1;i++) {
                 		Grafo.agregar(items[i], items[i+1], 1);
                 		
                 }  
            	}
            	isFirst = false;
            }
            
            System.out.println("1. Ingrese el genero que desea ver el historial de busqueda: ");
         	Scanner s = new Scanner(System.in);
         	String cat = s.nextLine();
         	
         	System.out.println("1. Ingrese la cantidad : ");

         	Integer cant = s.nextInt();
         	s.close();
            Grafo.getCamino(cat,cant);
            System.out.println("\n");
            
            Set<String> resultado = Grafo.dfs(cat);
            
            for(String str : resultado) {
            	System.out.println(str);
            }
            
           //Ciclos
            GrafoGeneros GrafoCiclo = Grafo.DFS_Ciclo(cat);
            Grafo.imprimirCamino();
            
		   

        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}
