package tpe;

public class Libro {
	private String titulo;
	private String autor;
	private String paginas;
	String[] generos;
	
	// --------- CONSTRUCTOR ---------
		
	public Libro(String t, String a, String p, String[] g) {
		titulo = t;
		autor = a;
		paginas = p;
		generos = g;
	}
	
	// --------- GET ---------
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public String getPaginas() {
		return paginas;
	}
	
	public String[] getGeneros(){
		return generos;
	}	
}
