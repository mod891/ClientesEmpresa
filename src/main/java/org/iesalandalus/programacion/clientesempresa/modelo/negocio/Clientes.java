package org.iesalandalus.programacion.clientesempresa.modelo.negocio;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.apache.commons.math3.exception.NullArgumentException;
import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

public class Clientes {

	private int tamano;
	private int capacidad;
	private Cliente[] coleccionClientes;
	
	public Clientes(int capacidad) {
		
		if (capacidad <= 0) 
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
			
		this.tamano = 0;
		this.capacidad = capacidad;
		coleccionClientes = new Cliente[capacidad];
	}
	public int getTamano() {
		return tamano;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public Cliente[] get() {
		return this.copiaProfundaClientes();
	}
	
	public boolean capacidadSuperada(int indice) {
		return true;
	}
	
	public boolean tamanoSuperado(int indice) {
		return true;
	}
	
	public int buscarIndice(Cliente cliente) {
		for (int i=0; i<tamano; i++) {
			if (coleccionClientes[i].equals(cliente))
				return i;
		}
		return tamano+1;
	}
	
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		
		if (cliente == null) 
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		
		if (buscar(cliente) != null)
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese dni.");
		if (getTamano()+1 > this.getCapacidad())
			throw new OperationNotSupportedException("ERROR: No se aceptan más clientes.");
		
		coleccionClientes[getTamano()] = new Cliente(cliente);
		tamano++;
	}
	
	public Cliente buscar(Cliente cliente) {
		
		int indice = buscarIndice(cliente); 
		
		if ( indice > getTamano())
			return null;
		else
			return new Cliente(coleccionClientes[indice]);
		
	}
	
	public void desplazarUnaPosicionHaciaIzquierda(int indice) {
		
		for (int i=indice; i<this.getTamano(); i++) {
			coleccionClientes[i] = coleccionClientes[i+1];
		}
	}
	
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null)
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");

		int indice = buscarIndice(cliente); 
		
		if ( indice > getTamano())
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente como el indicado.");
		
		if (indice < getTamano()) {
			coleccionClientes[indice] = null;
			this.desplazarUnaPosicionHaciaIzquierda(indice);
			this.tamano--;
		}
	}
	
	public Cliente[] copiaProfundaClientes() {
		Cliente[] copia = new Cliente[getTamano()];
		for (int i=0; i<getTamano(); i++) {
			copia[i] = new Cliente(this.coleccionClientes[i]);
		}
		return copia;
	}
	
	public static void main(String args[]) {
		//	public Cliente(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
		Clientes cli = new Clientes(10);
		LocalDate fecha = LocalDate.parse("1991-01-08");
		Cliente cliente1 = new Cliente("abc","23305525Q","a@a.com","123123123",fecha);
		Cliente cliente2 = new Cliente("abcd","04459757B","a@a.com","111333444",fecha);

		Cliente[] clientes;
		try {
			cli.insertar(cliente1);
			cli.insertar(cliente2);
			cli.borrar(cliente1);
		
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e ) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		System.out.println("hola");
		
		clientes = cli.get();
		System.out.println(clientes.length);
		for (Cliente c : clientes) {
			System.out.println(c.toString());
		}
	
	}
	
}
