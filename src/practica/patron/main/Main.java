package practica.patron.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import practica.patron.dominio.Cliente;
import practica.patron.dominio.Empleado;
import practica.patron.dominio.Carta;
import practica.patron.dominio.Pedido;
import practica.patron.dominio.Pizza;
import practica.patron.dominio.UsuarioA;
import practica.patron.fachada.PizzaWebFacade;
import practica.patron.utiles.RolUsuario;
import practica.patron.utiles.TipoCliente;




public class Main {
	
	private static final Logger logger = Logger.getLogger(Main.class.getName());
    
    private static final String COLOR_RESET = "\u001B[0m";
    private static final String COLOR_ROJO = "\u001B[31m";
    private static final String COLOR_SUBR_ROJO = "\u001B[41m";

    private static final String ADMIN_USUARIO = "admin";
    private static final String ADMIN_CONTRASENA = "admin";
    
    private static PizzaWebFacade pizzaWeb = new PizzaWebFacade();
    private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    
    
    
    
    
	
	
	
	
	public static void main(String[] args) {
		
		// Atributos.
		boolean cargaInicialOk = false;
		
		
		// Carga inicial.
		cargaInicialOk = cargaInicial();
		
		if (!cargaInicialOk) {
			logger.severe("Ha ocurrido un error durante el proceso de carga inicial de los datos. Se cancela la ejecución del programa.");
			return;
		}
		
		
		// Ejecución del programa.
		ejecucionPrograma();
        
    }
	
	
	
	
	
	
	
	
	
    
	private static boolean cargaInicial() {
		
		boolean resultado = false;
		
		
		try {
			
			// Empleados.
			Empleado empleado1 = new Empleado("pj.martinez", "pj.martinez", "Pedro José Martínez Salvador", "28159746F", RolUsuario.EMPLEADO, 2200.0, "ES66 2100 0418 40 1234567891");
			Empleado empleado2 = new Empleado("d.perez", "d.perez", "David Pérez Tellez", "96746982T", RolUsuario.EMPLEADO, 1800.0, "ES60 0049 1500 05 1234567892");
			
			
			// Clientes.
			Cliente cliente1 = new Cliente("d.velazquez", "d.velazquez", "Diego Velázquez", "28468209Q", RolUsuario.CLIENTE, TipoCliente.PARTICULAR);
			Cliente cliente2 = new Cliente("s.ramon", "s.ramon", "Santiago Ramón y Cajal", "44782009P", RolUsuario.CLIENTE, TipoCliente.PARTICULAR);
			Cliente cliente3 = new Cliente("j.cierva", "j.cierva", "Juan de la Cierva y Codorníu", "77523694B", RolUsuario.CLIENTE, TipoCliente.PARTICULAR);
			Cliente cliente4 = new Cliente("s.ochoa", "s.ochoa", "Severo Ochoa de Albornoz", "28128550C", RolUsuario.CLIENTE, TipoCliente.EMPRESA);
			
			
			// Usuarios.
			List<UsuarioA> usuarios = new ArrayList<>();
			usuarios.add(empleado1);
			usuarios.add(empleado2);
			usuarios.add(cliente1);
			usuarios.add(cliente2);
			usuarios.add(cliente3);
			usuarios.add(cliente4);
			
			
			// Pizzas.
			Pizza pizza1 = new Pizza("Margarita", List.of("tomate", "mozzarella", "orégano"), 8.0);
			Pizza pizza2 = new Pizza("Caprichosa", List.of("tomate", "mozzarella", "jamón york", "champiñones", "pimiento", "orégano"), 11.50);
			Pizza pizza3 = new Pizza("Genovesa", List.of("tomate", "mozzarella", "champiñones", "cebolla", "atún", "orégano"), 11.50);
			Pizza pizza4 = new Pizza("4 Estaciones", List.of("tomate", "mozzarella", "jamón york", "champiñones", "aceitunas", "orégano"), 11.50);
			Pizza pizza5 = new Pizza("Florentina", List.of("tomate", "mozzarella", "bacon", "champiñones", "pollo", "orégano"), 12.50);
			Pizza pizza6 = new Pizza("Auténtica", List.of("tomate", "mozzarella", "gorgonzola", "jamón serrano", "huevo", "orégano"), 12.50);
			Pizza pizza7 = new Pizza("Siciliana", List.of("tomate", "mozzarella", "alcaparras", "aceitunas", "anchoas", "orégano"), 12.50);
			Pizza pizza8 = new Pizza("Paesana", List.of("tomate", "mozzarella", "champiñones", "cebolla", "pollo", "orégano"), 12.50);
			
			
			// Carta.
			Carta carta = new Carta();
			carta.addPizza(pizza1);
			carta.addPizza(pizza2);
			carta.addPizza(pizza3);
			carta.addPizza(pizza4);
			carta.addPizza(pizza5);
			carta.addPizza(pizza6);
			carta.addPizza(pizza7);
			carta.addPizza(pizza8);
			
			
			// Pedidos.
			Pedido pedido1 = new Pedido(List.of(pizza2, pizza3), 23.00,
					new GregorianCalendar() {{ setTime(formato.parse("30/04/2025 14:00")); }},
					new GregorianCalendar() {{ setTime(formato.parse("30/04/2025 14:30")); }},
					cliente1);
			
			Pedido pedido2 = new Pedido(List.of(pizza1, pizza8), 20.50,
					new GregorianCalendar() {{ setTime(formato.parse("30/04/2025 14:15")); }},
					new GregorianCalendar() {{ setTime(formato.parse("30/04/2025 14:45")); }},
					cliente2);

			Pedido pedido3 = new Pedido(List.of(pizza4, pizza5), 24.00,
					new GregorianCalendar() {{ setTime(formato.parse("01/05/2025 14:05")); }},
					new GregorianCalendar() {{ setTime(formato.parse("01/05/2025 14:35")); }},
					cliente1);

			Pedido pedido4 = new Pedido(List.of(pizza6, pizza7), 25.00,
					new GregorianCalendar() {{ setTime(formato.parse("01/05/2025 21:00")); }},
					new GregorianCalendar() {{ setTime(formato.parse("01/05/2025 21:30")); }},
					cliente3);
			
			List<Pedido> pedidos = new ArrayList<>();
			pedidos.add(pedido1);
			pedidos.add(pedido2);
			pedidos.add(pedido3);
			pedidos.add(pedido4);
			
			
			// Carga inicial.
			resultado = pizzaWeb.cargaInicial(ADMIN_USUARIO, ADMIN_CONTRASENA, usuarios, carta, pedidos);
			
		} catch(Exception e) {
			resultado = false;
		}
		
		
		return resultado;
		
	}
	
	
	
	
	
	
	
	
	
	
	private static void ejecucionPrograma() {

        Scanner scanner = new Scanner(System.in);
        int opcionMenu = 1;																						// 1: menú login; 2: menú empleado; 3: menú cliente.
        int opcionSeleccion;
        int opcionMix;
        RolUsuario rol = null;
        
		System.out.println("");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("--  PIZZERIA LA TRADIZIONALE                                                                                          --");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        
        System.out.println("");
        System.out.println("Bienvenido a 'La Tradizionale', su pizzería italiana.");
        System.out.println("Espere mientras conectamos con el servidor...");
        LockSupport.parkNanos(2_000_000_000L);
        
        System.out.println("");
        System.out.println(COLOR_SUBR_ROJO + "[Esta es una aplicación de uso académico.]" + COLOR_RESET);
        System.out.println(COLOR_SUBR_ROJO + "[Para conocer las credenciales de usuario para las pruebas, consulte la documentación.]" + COLOR_RESET);
        System.out.println("");
        
        do {
        	
        	
        	
        	
        	// Mostrar menú.
        	switch (opcionMenu) {
            
	            // 1. De login.
	            case 1:
	            	System.out.println("Seleccione una opción del menú:");
	            	System.out.println("");
	            	System.out.println("1. Iniciar sesión.");
	            	System.out.println("2. Registrarse como cliente.");
	            	System.out.println("");
	                System.out.println("0. Salir.");
	                System.out.println("");
	            	break;
	            	
            	// 2. De empleado.
	            case 2:
	            	System.out.println("Seleccione una opción del menú:");
	            	System.out.println("");
	            	System.out.println("1. Visualizar la carta.");
	            	System.out.println("2. Añadir pizza a la carta.");
	            	System.out.println("3. Quitar pizza de la carta.");
	            	System.out.println("");
	            	System.out.println("9. Cerrar sesión.");
	                System.out.println("");
	            	break;
	            	
            	// 3. De cliente.
	            case 3:
	            	System.out.println("Seleccione una opción del menú:");
	            	System.out.println("");
	            	System.out.println("1. Visualizar la carta.");
	            	System.out.println("2. Iniciar un nuevo pedido.");
	            	System.out.println("3. Visualizar historial de pedidos.");
	            	System.out.println("");
	            	System.out.println("9. Cerrar sesión.");
	                System.out.println("");
	            	break;
            	
        	}
        	
        	System.out.print("Selección: ");
        	opcionSeleccion = scanner.nextInt();
            scanner.nextLine();																					// Limpiar el buffer.
            System.out.println("");
        	
        	opcionMix = Integer.parseInt(opcionMenu + "" + opcionSeleccion);
        	
        	
        	
        	
        	// Mostrar siguiente nivel del menú.
            switch (opcionMix) {
                
            	
                // 11 (Menú Login): Iniciar sesión.
                case 11:
                    System.out.println("Iniciar sesión.");
                    System.out.println("");
                    System.out.println("Introduzca sus credenciales de acceso.");
                    System.out.print  ("· Nombre de usuario: ");
                    String nombreUsuarioLogin = scanner.nextLine();
                    System.out.print  ("· Contraseña: ");
                    String contrasenaLogin = scanner.nextLine();
                    System.out.println("");
                    
                    boolean sesionIniciada = pizzaWeb.iniciarSesion(nombreUsuarioLogin, contrasenaLogin);
                    
                    if (sesionIniciada) {
                    	rol = pizzaWeb.obtenerRolDelUsuario();
                        if (rol == RolUsuario.EMPLEADO) opcionMenu = 2;
                        else if (rol == RolUsuario.CLIENTE) opcionMenu = 3;
                        
                        System.out.println("Ha iniciado sesión correctamente. Se ha identificado como " + rol.toString().toLowerCase() + ".");
                        System.out.println("");
                        
                    } else {
                    	System.out.println("Sus credenciales no son válidas.");
                        System.out.println("");
                    }
                    
                    break;
                    
                    
                // 12 (Menú Login): Registrarse como cliente.
                case 12:
                	System.out.println("Registrarse como cliente.");
                    System.out.println("");
                    System.out.println("Introduzca sus datos.");
                    System.out.print  ("· Nombre de usuario: ");
                    String nombreUsuarioRegistro = scanner.nextLine();
                    System.out.print  ("· Contraseña: ");
                    String contrasenaRegistro = scanner.nextLine();
                    System.out.print  ("· Nombre completo: ");
                    String nombreCompletoRegistro = scanner.nextLine();
                    System.out.print  ("· DNI: ");
                    String dniRegistro = scanner.nextLine();
                    System.out.print  ("· ¿Es un cliente particular (1) o empresa (2)? ");
                    int tipoClienteIntRegistro = scanner.nextInt();
                    scanner.nextLine();																			// Limpiar el buffer.
                    System.out.println("");
                    
                    TipoCliente tipoClienteRegistro = null;
                    if (tipoClienteIntRegistro == 1) tipoClienteRegistro = TipoCliente.PARTICULAR;
                    else if (tipoClienteIntRegistro == 2) tipoClienteRegistro = TipoCliente.EMPRESA;
                    
                    boolean clienteRegistrado = pizzaWeb.registrarCliente(nombreUsuarioRegistro, contrasenaRegistro, nombreCompletoRegistro, dniRegistro, tipoClienteRegistro);
                    
                    if (clienteRegistrado) {
                        System.out.println("El cliente ha sido creado correctamente. Puede proceder a iniciar sesión.");
                        System.out.println("");
                        
                    } else {
                    	System.out.println("El cliente no ha podido ser creado correctamente.");
                        System.out.println("");
                    }
                    
                	break;
                	
                	
            	// 21 (Menú Empleado) / 31 (Menú Cliente): Visualizar la carta.
                case 21:
                case 31:
                	List<Pizza> cartaVisualizar = pizzaWeb.obtenerPizzasDeLaCarta();
                	int iVisualizar = 1;
                	
                	System.out.println("Visualizar la carta.");
                    System.out.println("");
                    
                    for (Pizza pizza : cartaVisualizar) {
                    	System.out.println("· (" + iVisualizar + ") Pizza " + pizza.getNombre() + " (" + pizza.getPrecio() + " EUR): " + String.join(", ", pizza.getIngredientes()) + ".");
                    	iVisualizar++;
                    }
                    
                    System.out.println("");
                    
                	break;
                	
                	
            	// 22 (Menú Empleado): Añadir pizza a la carta.
                case 22:
                	System.out.println("Añadir pizza a la carta.");
                    System.out.println("");
                    System.out.println("Introduzca la información de la nueva pizza.");
                    System.out.print  ("· Nombre: ");
                    String nombrePizza = scanner.nextLine();
                    System.out.print  ("· ¿Cuántos ingredientes tendrá? ");
                    int numIngredientesPizza = scanner.nextInt();
                    scanner.nextLine();																			// Limpiar el buffer.
                    
                    List<String> ingredientesPizza = new ArrayList<>();
                    for (int iIngredientesPizza = 1; iIngredientesPizza <= numIngredientesPizza; iIngredientesPizza++) {
                    	System.out.print  ("· Ingrediente " + iIngredientesPizza + ": ");
                    	String ingredientePizza = scanner.nextLine().trim().toLowerCase();
                    	ingredientesPizza.add(ingredientePizza);
                    }
                    
                    System.out.print  ("· Precio: ");
                    double precioPizza = scanner.nextDouble();
                    scanner.nextLine();																			// Limpiar el buffer.
                    System.out.println("");
                    
                    Pizza pizza = new Pizza(nombrePizza, ingredientesPizza, precioPizza);
                    boolean pizzaAnadida = pizzaWeb.anadirPizzaALaCarta(pizza);
                    
                    if (pizzaAnadida) {
                        System.out.println("La pizza ha sido añadida correctamente a la carta.");
                        System.out.println("");
                        
                    } else {
                    	System.out.println("La pizza no ha podido ser añadida a la carta.");
                        System.out.println("");
                    }
                    
                	break;
                	
                	
            	// 23 (Menú Empleado): Eliminar pizza de la carta.
                case 23:
                	List<Pizza> cartaVisualizarEliminar = pizzaWeb.obtenerPizzasDeLaCarta();
                	int iVisualizarEliminar = 1;
                	
                	System.out.println("Quitar pizza de la carta.");
                    System.out.println("");
                    
                    for (Pizza pizzaEliminar : cartaVisualizarEliminar) {
                    	System.out.println("· (" + iVisualizarEliminar + ") Pizza " + pizzaEliminar.getNombre() + " (" + pizzaEliminar.getPrecio() + " EUR): " + String.join(", ", pizzaEliminar.getIngredientes()) + ".");
                    	iVisualizarEliminar++;
                    }
                    
                    System.out.println("");
                    System.out.print  ("· Seleccione qué pizza desea quitar de la carta: ");
                    int pizzaEliminarInt = scanner.nextInt();
                    scanner.nextLine();																			// Limpiar el buffer.
                    System.out.println("");
                    
                    boolean pizzaEliminada = pizzaWeb.eliminarPizzaDeLaCarta(cartaVisualizarEliminar.get(pizzaEliminarInt - 1));
                    
                    if (pizzaEliminada) {
                        System.out.println("La pizza ha sido quitada correctamente de la carta.");
                        System.out.println("");
                        
                    } else {
                    	System.out.println("La pizza no ha podido ser quitada de la carta.");
                        System.out.println("");
                    }
                    
                	break;
                	
                	
            	// 32 (Menú Cliente): Iniciar un nuevo pedido.
                case 32:
                	
                	List<Pizza> cartaVisualizarPedido = pizzaWeb.obtenerPizzasDeLaCarta();
                	int iVisualizarPedido = 1;
                	
                	System.out.println("Iniciar un nuevo pedido.");
                    System.out.println("");
                    
                    for (Pizza pizzaPedido : cartaVisualizarPedido) {
                    	System.out.println("· (" + iVisualizarPedido + ") Pizza " + pizzaPedido.getNombre() + " (" + pizzaPedido.getPrecio() + " EUR): " + String.join(", ", pizzaPedido.getIngredientes()) + ".");
                    	iVisualizarPedido++;
                    }
                    
                    System.out.println("");
                    System.out.print  ("· ¿Cuántas pizzas va a añadir? ");
                    int numPizzaPedido = scanner.nextInt();
                    scanner.nextLine();																			// Limpiar el buffer.
                    System.out.println("");
                    
                    List<Pizza> pizzasPedido = new ArrayList<>();
                    for (int iPizzasPedido = 1; iPizzasPedido <= numPizzaPedido; iPizzasPedido++) {
                    	System.out.print  ("· Pizza " + iPizzasPedido + ": ");
                    	int pizzaSeleccionada = scanner.nextInt();
                        scanner.nextLine();																		// Limpiar el buffer.
                    	pizzasPedido.add(cartaVisualizarPedido.get(pizzaSeleccionada - 1));
                    }
                    
                    System.out.println("");
                    System.out.println("Estamos realizando el pedido. Por favor, espere...");
                    LockSupport.parkNanos(2_000_000_000L);
                    
                    Pedido pedidoRealizado = pizzaWeb.realizarPedido(pizzasPedido);
                    
                    if (pedidoRealizado != null) {
                        double importePedido = pedidoRealizado.getImporte();
                        String fechaEstimadaPedido = formato.format(pedidoRealizado.getFechaEstimacion().getTime());
                    	System.out.println("El pedido se ha realizado correctamente. El importe total es de " + importePedido + " EUR. Podrá recoger su pedido a las " + fechaEstimadaPedido + ".");
                        System.out.println("");
                        
                    } else {
                    	System.out.println("El pedido no se ha realizado correctamente.");
                        System.out.println("");
                    }
                    
                	break;
                	
                	
            	// 33 (Menú Cliente): Visualizar historial de pedidos.
                case 33:
                	List<Pedido> historialPedidosCliente = pizzaWeb.obtenerHistorialDePedidosDelCliente();
                	
                	System.out.println("Visualizar historial de pedidos.");
                    System.out.println("");
                    
                    for (Pedido pedidoCliente : historialPedidosCliente) {
                    	List<Pizza> pizzasPedidoH = pedidoCliente.getPizzas();
                    	double importePedidoH = pedidoCliente.getImporte();
                        String fechaPedidoH = formato.format(pedidoCliente.getFechaPedido().getTime());
                    	System.out.println("· Pedido del " + fechaPedidoH + " (" + importePedidoH + " EUR): " + pizzasPedidoH.stream().map(Pizza::getNombre).collect(Collectors.joining(", ")) + ".");
                    }
                    
                    System.out.println("");
                    
                	break;
                	
                	
                // 29 (Menú Empleado) / 39 (Menú Cliente): Cerrar sesión.
                case 29:
                case 39:
                	System.out.println("Cerrando sesión...");
                    LockSupport.parkNanos(2_000_000_000L);
                    pizzaWeb.cerrarSesion();
                    
                    System.out.println("Sesión cerrada.");
                    System.out.println("");
                    
                    opcionMenu = 1;
                    rol = null;
                    
                	break;
                    
                	
                // 10 (Menú Login) / 20 (Menú Empleado) / 30 (Menú Cliente): Salir.
                case 10:
                case 20:
                case 30:
                    System.out.println("Gracias por visitar 'La Tradizionale'.");
                    LockSupport.parkNanos(2_000_000_000L);
                    System.out.println("");
                    
                    opcionMenu = 1;
                    rol = null;
                    
                    break;
                    
                    
                default:
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
                    System.out.println("");
                    
                    
            }
            
        } while (opcionMix != 10 && opcionMix != 20 && opcionMix != 30);
        
        scanner.close();
		
	}
	
	
}
