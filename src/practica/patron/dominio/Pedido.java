package practica.patron.dominio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;




public class Pedido implements PedidoI {
	
	protected String id;
	protected List<Pizza> pizzas;
	protected double importe;
	protected Calendar fechaPedido;
	protected Calendar fechaEstimacion;
	protected Cliente cliente;
	
	
	public Pedido() {
		this.id = UUID.randomUUID().toString();
		this.pizzas = new ArrayList<>();
		this.importe = 0;
		this.fechaPedido = null;
		this.fechaEstimacion = null;
		this.cliente = null;
	}
	
	public Pedido(List<Pizza> pizzas, double importe, Calendar fechaPedido, Calendar fechaEstimacion, Cliente cliente) {
		this.id = UUID.randomUUID().toString();
		this.pizzas = new ArrayList<>(pizzas);
		this.importe = importe;
		this.fechaPedido = fechaPedido;
		this.fechaEstimacion = fechaEstimacion;
		this.cliente = cliente;
	}
	
	public String getId() {
		return id;
	}
	
	public List<Pizza> getPizzas() {
		return pizzas;
	}
	
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = new ArrayList<>(pizzas);
	}
	
	public double getImporte() {
		return importe;
	}
	
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	public Calendar getFechaPedido() {
		return fechaPedido;
	}
	
	public void setFechaPedido(Calendar fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	
	public Calendar getFechaEstimacion() {
		return fechaEstimacion;
	}
	
	public void setFechaEstimacion(Calendar fechaEstimacion) {
		this.fechaEstimacion = fechaEstimacion;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public boolean addPizza(Pizza pizza) {
		
		boolean resultado = true;
		
		try {
			this.pizzas.add(pizza);
			this.importe = importe + pizza.precio;
			
		} catch (Exception e) {
			resultado = false;
		}
		
		return resultado;
		
	}
	
	public boolean tramitar() {
		
		boolean resultado = true;
		
		try {
			calcularImporte();
			
			this.fechaPedido = Calendar.getInstance();
			this.fechaEstimacion = Calendar.getInstance();
			this.fechaEstimacion.add(Calendar.MINUTE, 30);
			
		} catch (Exception e) {
			resultado = false;
		}
		
		return resultado;
		
	}
	
	public double calcularImporte() {
		
		this.importe = 0;
		
		try {
			if (this.pizzas != null && this.pizzas.size() > 0) {
				for (Pizza pizza : this.pizzas) {
					this.importe = this.importe + pizza.getPrecio();
				}
			}
			
		} catch (Exception e) {
			this.importe = 0;
		}
		
		return this.importe;
		
	}
	
	public String toString() {
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		return "Pedido " + id + ". Cliente: " + cliente.getNombreCompleto() + " (" + cliente.getNombre() + "). " +
				"Pizzas: " + pizzas.stream().map(Pizza::getNombre).collect(Collectors.joining(", ")) + ". Importe: " + importe + " EUR. " +
				"F. Pedido: " + formato.format(fechaPedido.getTime()) + ". F. Estimada: " + formato.format(fechaEstimacion.getTime()) + ".";
		
	}
	
}
