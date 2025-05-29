package practica.patron.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;




public class Pizza implements PizzaI {
	
	protected String id;
	protected String nombre;
	protected List<String> ingredientes;
	protected double precio;
	
	
	public Pizza(String nombre, List<String> ingredientes, double precio) {
		this.id = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.ingredientes = new ArrayList<String>(ingredientes);
		this.precio = precio;
	}
	
	public String getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<String> getIngredientes() {
		return new ArrayList<String>(ingredientes);
	}
	
	public void setIngredientes(List<String> ingredientes) {
		this.ingredientes = new ArrayList<String>(ingredientes);
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String toString() {
		return nombre + " (" + precio + " EUR): " + String.join(", ", ingredientes) + ".";
	}
	
}
