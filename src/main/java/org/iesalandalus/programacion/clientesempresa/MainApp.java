package org.iesalandalus.programacion.clientesempresa;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;
import org.iesalandalus.programacion.clientesempresa.vista.Consola;
import org.iesalandalus.programacion.clientesempresa.vista.Opcion;
import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {

	private static int NUM_MAX_CLIENTES = 10;
	public static Clientes clientes;
	
	private static void insertarCliente()  {
		Cliente cliente = Consola.leerCliente();
		try {
			clientes.insertar(cliente);
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void buscarCliente() {
		Cliente cli = clientes.buscar(Consola.leerClienteDni());
		if (cli == null) {
			System.out.println("\nError el cliente no existe");
		} else {
			System.out.println(cli.toString());
		}
	}
	
	private static void borrarCliente() {
		Cliente cli = clientes.buscar(Consola.leerClienteDni());
		if (cli == null) {
			System.out.println("\nError el cliente no existe");
		} else {
			try {
				clientes.borrar(cli);
				System.out.println("Cliente borrado");
			} catch (OperationNotSupportedException e) {

				System.out.println(e.getMessage());
			}
		}
	}
	
	private static void mostrarClientes() {
		Cliente[] arr = clientes.get();
		if (arr.length > 0) {
			for (int i=0; i<arr.length; i++) {
				System.out.println(arr[i].toString());
			}
		} else {
			System.out.println("No hay clientes ");
		}
	}
	
	private static void mostrarClientesFecha() {
		LocalDate fecha = Consola.leerFechaNacimiento();
		Cliente[] arr = clientes.get();
		boolean encontrado = false;
		if (arr.length > 0) {
			for (int i=0; i<arr.length; i++) {
				if (arr[i].getFechaNacimiento().equals(fecha)) {
					System.out.println(arr[i].toString());
					encontrado = true;
				}
			}
		} else {
			System.out.println("No existen clientes.");
		}
		if (!encontrado) {
			System.out.println("No hay clientes con esa fecha de nacimiento");
		}
	}
	
	private static void ejecutarOpcion(Opcion opcion) {
		
		switch (opcion) {
		case BORRAR_CLIENTE:
			borrarCliente();
			break;
		case BUSCAR_CLIENTE:
			buscarCliente();
			break;
		case INSERTAR_CLIENTE:
			insertarCliente();
			break;
		case MOSTRAR_CLIENTES:
			mostrarClientes();
			break;
		case MOSTRAR_CLIENTES_FECHA:
			mostrarClientesFecha();
			break;
		case SALIR:
			System.out.println("Adios");
			break;
		}
	}
	public static void main(String[] args) {
		clientes = new Clientes(10);
		Opcion opcion;
		do {
		 opcion = Consola.elegirOpcion();
		 ejecutarOpcion(opcion);
		} while (opcion != Opcion.SALIR);
		
	}

}