package practica.patron.dominio;

import java.util.ArrayList;
import java.util.List;




public class Carta implements CartaI {
	
	protected List<Pizza> pizzas;
	protected final int maxPizzas = 10;
	
	
	public Carta() {
		this.pizzas = new ArrayList<>();
	}
	
	@Override
	public boolean addPizza(Pizza pizzaNueva) {
		
		boolean resultado = true;
		boolean yaExiste = false;
		
		try {
			// Buscar si ya existe una pizza con el mismo nombre.
			for (Pizza pizzaAux : this.pizzas) {
				if (pizzaAux.getNombre().toLowerCase().equals(pizzaNueva.getNombre().toLowerCase()))
					yaExiste = true;
			}
			
			// Si no existe una pizza con el mismo nombre y no se ha llegado ya al límite de pizzas del menú, se añade.
			if (!yaExiste && this.pizzas.size() < maxPizzas) {
				this.pizzas.add(pizzaNueva);
				
			} else {
				resultado = false;
			}
			
		} catch (Exception e) {
			resultado = false;
		}
		
		return resultado;
		
	}
	
	@Override
	public boolean removePizza(Pizza pizzaEliminar) {
		return this.pizzas.remove(pizzaEliminar);
	}
	
	public boolean removePizzaById(String idPizzaEliminar) {
		
		boolean resultado = false;
		
		try {
			
			for (Pizza pizzaAux : this.pizzas) {
				if (pizzaAux.getId().toLowerCase().equals(idPizzaEliminar)) {
					this.pizzas.remove(pizzaAux);
					resultado = true;
					break;
				}
			}
			
		} catch (Exception e) {
			resultado = false;
		}
		
		return resultado;
		
	}
	
	public boolean removePizzaByName(String namePizzaEliminar) {
		
		boolean resultado = false;
		
		try {
			
			for (Pizza pizzaAux : this.pizzas) {
				if (pizzaAux.getNombre().toLowerCase().equals(namePizzaEliminar)) {
					this.pizzas.remove(pizzaAux);
					resultado = true;
					break;
				}
			}
			
		} catch (Exception e) {
			resultado = false;
		}
		
		return resultado;
		
	}
	
	@Override
	public List<Pizza> getPizzas() {
		return this.pizzas;
	}
	
	@Override
	public String toString() {
		
		int i = 0;
		String texto = null;
		
		texto = "Menú: " + this.pizzas.size() + " pizzas.";
		
		for (Pizza pizza : this.pizzas) {
			i++;
			texto = texto + "\n" + "Pizza " + i + " (" + pizza.getNombre() + "): " + String.join(", ", pizza.getIngredientes()) + ".";
		}
		
		return texto;
		
	}
	
}
