package ProgramacionIII.tpe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import tpe.Libro;

public class CSVWritter {
	
	public static void writeFile(ArrayList<Libro> Libros) {
		
		BufferedWriter bw = null;
		try {
			File file = new File("salida.csv");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			
			Integer ArraySize = Libros.size();
			for(int k = 0; k < ArraySize; k++) {
	    			bw.write(Libros.get(k).getTitulo());
	    			bw.newLine();
    			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error cerrando el BufferedWriter" + ex);
			}
		}
		
	}

	

}
