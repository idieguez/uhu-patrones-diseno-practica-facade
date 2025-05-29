package practica.patron.fachada;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import practica.patron.dominio.Cliente;
import practica.patron.dominio.Carta;
import practica.patron.dominio.Pedido;
import practica.patron.dominio.Pizza;
import practica.patron.dominio.UsuarioA;
import practica.patron.utiles.RolUsuario;
import practica.patron.utiles.TipoCliente;




public class PizzaWebFacade {
	
	
	/* ****************************************************************************************************
	 * 
	 * ATRIBUTOS.
	 * 
	 * ****************************************************************************************************/
	
	private static final Logger logger = Logger.getLogger(PizzaWebFacade.class.getName());
	
	protected boolean cargaInicialRealizada;
	protected UsuarioA usuarioIdentificado;
	protected List<UsuarioA> usuarios;
	protected Carta carta;
	protected List<Pedido> pedidos;
	
	
	
	
	
	
	
	
	
	
	/* ****************************************************************************************************
	 * 
	 * CONSTRUCTOR.
	 * 
	 * ****************************************************************************************************/
	
	public PizzaWebFacade() {
		
		cargaInicialRealizada = false;
		usuarioIdentificado = null;
		usuarios = new ArrayList<>();
		carta = new Carta();
		pedidos = new ArrayList<>();
		
	}
	
	
	
	
	
	
	
	
	
	
	/* ****************************************************************************************************
	 * 
	 * ACCIONES DEL ADMINISTRADOR.
	 * 
	 * ****************************************************************************************************/
	
	public boolean cargaInicial(String nombreUsuario, String contrasena, List<UsuarioA> usuarios, Carta carta, List<Pedido> pedidos) {
		
		// Comprobación inicial.
		if (cargaInicialRealizada) { logger.warning("La carga inicial ya fue realizada con anterioridad y sólo se permite una vez."); return false; }
		if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) { logger.warning("El usuario es obligatorio."); return false; }
		if (contrasena == null || contrasena.trim().isEmpty()) { logger.warning("La contraseña es obligatoria."); return false; }
		if (usuarios == null || usuarios.size() == 0) { logger.warning("La carga inicial debe incluir los usuarios del sistema."); return false; }
		if (carta == null || carta.getPizzas().size() == 0) { logger.warning("La carga inicial debe incluir un menú."); return false; }
		
		
		// Comprobación de las credenciales.
		if (!nombreUsuario.equals("admin") || !contrasena.equals("admin")) {
			logger.severe("Las credenciales del usuario administrador no son correctas.");
			return false;
		}
		
		
		// Carga inicial.
		this.usuarios = usuarios;
		this.carta = carta;
		this.pedidos = pedidos;
		this.cargaInicialRealizada = true;
		
		
		// Fin.
		return this.cargaInicialRealizada;
		
	}
	
	
	
	
	
	
	
	
	
	
	/* ****************************************************************************************************
	 * 
	 * ACCIONES DE LOS USUARIOS, TANTO EMPLEADOS COMO CLIENTES.
	 * 
	 * ****************************************************************************************************/
	
	public boolean iniciarSesion(String nombreUsuario, String contrasena) {
		
		// Comprobación inicial.
		if (usuarioIdentificado != null) { logger.warning("Ya hay un usuario identificado. Por favor, cierre sesión antes de volver a iniciar sesión."); return false; }
		if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) { logger.warning("El usuario no está definido."); return false; }
		if (contrasena == null || contrasena.trim().isEmpty()) { logger.warning("La contraseña no está definida."); return false; }
		
		
		// Atributos.
		boolean resultado = false;
		
		
		// Comprobación de las credenciales.
		for (UsuarioA usuario : usuarios) {
			if (usuario.getNombre().equals(nombreUsuario)) {
				if (usuario.getContrasena().equals(contrasena)) {
					usuarioIdentificado = usuario;
					resultado = true;
					break;
				}
			}
		}
		
		
		// Fin.
		return resultado;
		
	}
	
	
	
	
	public boolean cerrarSesion() {
		
		// Comprobación inicial.
		if (usuarioIdentificado == null) { logger.warning("No hay ningún usuario identificado."); return false; }
		
		
		// Cierre de la sesión.
		usuarioIdentificado = null;
		
		
		// Fin.
		return true;
		
	}
	
	
	
	
	public List<Pizza> obtenerPizzasDeLaCarta() {
		return carta.getPizzas();
	}
	
	
	
	
	public RolUsuario obtenerRolDelUsuario() {
		
		// Comprobación inicial.
		if (usuarioIdentificado == null) { logger.warning("No hay ningún usuario identificado."); return null; }
		
		
		// Obtención del rol del usuario.
		return usuarioIdentificado.getRol();
		
	}
	
	
	
	
	
	
	
	
	
	
	/* ****************************************************************************************************
	 * 
	 * ACCIONES DE LOS EMPLEADOS.
	 * 
	 * ****************************************************************************************************/
	
	public boolean anadirPizzaALaCarta(Pizza pizza) {
		
		// Comprobación inicial.
		if (usuarioIdentificado == null) { logger.warning("Esta acción está sólo permitida para empleados."); return false; }
		if (usuarioIdentificado.getRol() != RolUsuario.EMPLEADO) { logger.warning("Esta acción está sólo permitida para empleados."); return false; }
		if (pizza == null) { logger.warning(""); return false; }
		
		
		// Atributos.
		boolean resultado;
		
		
		// Añadición de la pizza.
		resultado = carta.addPizza(pizza);
		
		
		// Fin.
		return resultado;
		
	}
	
	
	
	
	public boolean eliminarPizzaDeLaCarta(Pizza pizza) {
		
		// Comprobación inicial.
		if (usuarioIdentificado == null) { logger.warning("Esta acción está sólo permitida para empleados."); return false; }
		if (usuarioIdentificado.getRol() != RolUsuario.EMPLEADO) { logger.warning("Esta acción está sólo permitida para empleados."); return false; }
		if (pizza == null) { logger.warning("La pizza no está definida."); return false; }
		
		
		// Atributos.
		boolean resultado;
		
		
		// Eliminación de la pizza.
		resultado = carta.removePizza(pizza);
		
		
		// Fin.
		return resultado;
		
	}
	
	
	
	
	
	
	
	
	
	
	/* ****************************************************************************************************
	 * 
	 * ACCIONES DE LOS CLIENTES.
	 * 
	 * ****************************************************************************************************/
	
	public boolean registrarCliente(String nombre, String contrasena, String nombreCompleto, String dni, TipoCliente tipoCliente) {
		
		// Comprobación inicial.
		if (usuarioIdentificado != null) { logger.warning("Ya hay un usuario identificado. Por favor, cierre sesión antes de registrar un nuevo cliente."); return false; }
		if (nombre == null || nombre.trim().isEmpty()) { logger.warning("El nombre de usuario no está definido."); return false; }
		if (contrasena == null || contrasena.trim().isEmpty()) { logger.warning("La contraseña no está definida."); return false; }
		if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) { logger.warning("El nombre completo no está definido."); return false; }
		if (dni == null || dni.trim().isEmpty()) { logger.warning("El DNI no está definido."); return false; }
		if (tipoCliente == null) { logger.warning("El tipo de cliente no está definido."); return false; }
		
		
		// Atributos.
		boolean resultado = false;
		
		
		// Registro del cliente.
		UsuarioA cliente = new Cliente(nombre, contrasena, nombreCompleto, dni, RolUsuario.CLIENTE, tipoCliente);
		resultado = usuarios.add(cliente);
		
		
		// Fin.
		return resultado;
		
	}
	
	
	
	
	public Pedido realizarPedido(List<Pizza> pizzas) {
		
		// Comprobación inicial.
		if (usuarioIdentificado == null) { logger.warning("Esta acción está sólo permitida para clientes."); return null; }
		if (usuarioIdentificado.getRol() != RolUsuario.CLIENTE) { logger.warning("Esta acción está sólo permitida para clientes."); return null; }
		if (pizzas == null || pizzas.size() == 0) { logger.warning("El listado de pizzas está vacío."); return null; }
		
		
		// Atributos.
		Pedido pedido = new Pedido();
		Cliente cliente = null;
		
		
		// Realización del pedido.
		try {
			if (usuarioIdentificado instanceof Cliente) {
				cliente = (Cliente) usuarioIdentificado;
				
			} else {
				logger.warning("Esta acción está sólo permitida para clientes.");
				return null;
			}

			pedido.setCliente(cliente);
			pedido.setPizzas(pizzas);
			double importe = pedido.calcularImporte();
			boolean tramitado = pedido.tramitar();
			
			if (importe > 0 && tramitado)
				pedidos.add(pedido);
			else
				pedido = null;
			
		} catch (Exception e) {
			pedido = null;
		}
		
		
		// Fin.
		return pedido;
		
	}
	
	
	
	
	public List<Pedido> obtenerHistorialDePedidos() {
		
		// Comprobación inicial.
		if (usuarioIdentificado == null) { logger.warning("Esta acción está sólo permitida para empleados."); return null; }
		if (usuarioIdentificado.getRol() != RolUsuario.EMPLEADO) { logger.warning("Esta acción está sólo permitida para empleados."); return null; }
		
		
		// Obtención del historial de pedidos.
		return pedidos;
		
	}
	
	
	
	
	public List<Pedido> obtenerHistorialDePedidosDelCliente() {
		
		// Comprobación inicial.
		if (usuarioIdentificado == null) { logger.warning("Esta acción está sólo permitida para clientes."); return null; }
		if (usuarioIdentificado.getRol() != RolUsuario.CLIENTE) { logger.warning("Esta acción está sólo permitida para clientes."); return null; }
		
		
		// Atributos.
		List<Pedido> pedidosCliente = new ArrayList<>();
		
		
		// Obtención del historial de pedidos del cliente.
		for (Pedido pedido : pedidos) {
			if (pedido.getCliente().getId().equals(usuarioIdentificado.getId())) {
				pedidosCliente.add(pedido);
			}
		}
		
		
		// Fin.
		return pedidosCliente;
		
	}
	
	
}
