package practica.patron.utiles;




public class Utiles {
	
	public static String textoATipoOracion(String texto) {
		
		if (texto == null || texto.isEmpty()) return texto;
		
		return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
		
	}
	
}
