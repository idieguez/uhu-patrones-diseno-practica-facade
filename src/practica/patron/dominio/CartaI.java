package practica.patron.dominio;

import java.util.List;




public interface CartaI {
	
	public boolean addPizza(Pizza pizza);
	public boolean removePizza(Pizza pizza);
	public List<Pizza> getPizzas();
	public String toString();
	
}
