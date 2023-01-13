package org.iesalandalus.programacion.clientesempresa.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private Consola() {}
	
	public static void mostrarMenu() {
		System.out.println("MENU");
		System.out.println("----");
		System.out.println("0 - BORRAR CLIENTE");
		System.out.println("1 - BUSCAR CLIENTE");
		System.out.println("2 - INSERTAR CLIENTE");
		System.out.println("3 - MOSTRAR CLIENTES");
		System.out.println("4 - MOSTRAR CLIENTES FECHA");
		System.out.println("5 - SALIR");
	}
	
	public static Opcion elegirOpcion() {
		Opcion opt = null; 
		int opcion = 0;
		Opcion[] arr = opt.values();
		
		mostrarMenu(); 
		
		do {
			
			System.out.println("Elija una opcion [1-6]:");
			opcion = Entrada.entero();
		} while (opcion < 0 || opcion > 5);
		
		return opt.values()[opcion];
	}
	
	public static Cliente leerCliente() {
		Cliente cliente = null;
		String nombre, dni, correo, telefono, fecha;
		
		System.out.println("Introduzca el nombre");
		nombre = Entrada.cadena();
		System.out.println("Introduzca el dni");
		dni = Entrada.cadena();
		System.out.println("Introduzca el correo");
		correo = Entrada.cadena();
		System.out.println("Introduzca el teléfono");
		telefono = Entrada.cadena();
		System.out.println("Introduzca la fecha en formato dd/mm/yyyy");
		fecha = Entrada.cadena();
		
		try {
			cliente = new Cliente(nombre,dni,correo,telefono,LocalDate.parse(fecha,DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return cliente;
	}
	
	public static Cliente leerClienteDni() {
		String dni;
		Cliente buscado = null;
		System.out.println("Introduzca el DNI del cliente a buscar");

		dni = Entrada.cadena();
		try {
			buscado = new Cliente("sfd",dni,"a@a.a","123456789",LocalDate.parse("01/01/1111",DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		return buscado;
	}
	
	
	public static LocalDate leerFechaNacimiento() {
		LocalDate fecha = null;
		boolean fechaValida = false;
		String fechaStr;
		
			System.out.println("Introduzca una fecha en formato dd/MM/yyyy");
			fechaStr = Entrada.cadena();
			try {
				fecha = LocalDate.parse(fechaStr,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				fechaValida = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				fechaValida = false;
			}
		if (!fechaValida)
			leerFechaNacimiento();
		
		return fecha;
		
	}
	
	

}
