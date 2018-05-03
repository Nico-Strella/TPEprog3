package tpe;

public class Contador {
	private static int contador = 0;
	public static void aumentarContador() {
		contador++;
	}
	
	public static int getContador() {
		return contador;
	}
	
	public static void resetContador() {
		contador = 0;
	}
}
