package utilities;

import java.util.ArrayList;

import entities.Actividad;
import entities.Alquiler;
import entities.Cliente;
import entities.Propiedad;

public class Test {

	public static void main(String[] args) {

		// CLIENTES

		// INSERTAR CLIENTES
		System.out.println("INSERTAMOS TRES CLIENTES DE PRUEBA");
		Cliente cl1 = new Cliente(78952922, "Endika", "Salgueiro", "email@email.com", "Calle falsa 1234", 48012, 123456789);
		Cliente cl2 = new Cliente(12457845, "Jordan", "Aranda", "email@email.com", "Calle falsa 1234", 48012, 123456789);
		Cliente cl3 = new Cliente(12345678, "Prueba", "Prueba", "email@email.com", "Calle falsa 1234", 48012, 123456789);
		if (ClientManager.getInstance().saveCliente(cl1)) {
			System.out.println("Insertado correctamente");
		} else {
			System.out.println("Ya existe");
		}
		if (ClientManager.getInstance().saveCliente(cl2)) {
			System.out.println("Insertado correctamente");
		} else {
			System.out.println("Ya existe");
		}
		if (ClientManager.getInstance().saveCliente(cl3)) {
			System.out.println("Insertado correctamente");
		} else {
			System.out.println("Ya existe");
		}

		// OBTENEMOS TODOS LOS CLIENTES DE LA BASE DE DATOS Y LOS MOSTRAMOS
		System.out.println("\nVISUALIZAMOS TODOS LOS CLIENTES DE LA BASE DE DATOS");
		ArrayList<Cliente> clientes = ClientManager.getInstance().getAllClientes();
		for (Cliente c : clientes) {
			System.out.println(c.toString());
		}

		// EDITAMOS EL CLIENTE LLAMADO ENDIKA
		System.out.println("\nEDITAMOS EL CLIENTE 'ENDIKA'");
		cl1.setNombre("Endika Editado");
		if (ClientManager.getInstance().editCliente(cl1)) {
			System.out.println("Cliente editado.");
		} else {
			System.out.println("No se ha podido editar el cliente");
		}

		// OBTENEMOS EL CLIENTE EDITADO Y LO MOSTRAMOS
		System.out.println("\nMOSTRAMOS LOS DATOS DEL CLIENTE 'ENDIKA' TRAS LA EDICION");
		cl1 = ClientManager.getInstance().getClienteById(cl1.getDni());
		System.out.println(cl1.toString());

		// BORRAMOS EL CLIENTE 3 (EL DE PRUEBA) Y SUS ALQUILERES
		System.out.println("\nELIMINAMOS EL CLIENTE DE PRUEBA");
		if (ClientManager.getInstance().deleteCliente(cl3.getDni())) {
			System.out.println("Cliente '" + cl3.getNombre() + "' borrado correctamente de la base de datos.");
		} else {
			System.out.println("El cliente '" + cl3.getNombre() + "' no se ha borrado de la base de datos.");
		}

		// ACTIVIDADES

		// OBTENEMOS TODAS LAS ACTIVIDADES DE LA BASE DE DATOS Y LAS MOSTRAMOS
		System.out.println("\nVISUALIZAMOS TODAS LAS ACTIVIDADES DE LA BASE DE DATOS");
		ArrayList<Actividad> actividades = ClientManager.getInstance().getAllActividades();
		for (Actividad a : actividades) {
			System.out.println(a.toString());
		}

		// OBTENEMOS TODAS LAS ACTIVIDADES DE LA PROPIEDAD CON ID 1
		System.out.println("\nVISUALIZAMOS TODAS LAS ACTIVIDADES DE LA PROPIEDAD CON ID 1");
		ArrayList<Actividad> actividadesPropiedad = ClientManager.getInstance().getActividadesByIdPropiedad(1);
		for (Actividad a : actividadesPropiedad) {
			System.out.println(a.toString());
		}

		// PROPIEDADES

		// OBTENEMOS TODAS LAS PROPIEDADES Y LAS MOSTRAMOS
		System.out.println("\nVISUALIZAMOS TODAS LAS PROPIEDADES DE LA BASE DE DATOS");
		ArrayList<Propiedad> propiedades = ClientManager.getInstance().getAllPropiedades();
		for (Propiedad p : propiedades) {
			System.out.println(p.toString());
		}

		// OBTENEMOS LA PROPIEDAD CON ID 1 Y LA MOSTRAMOS
		System.out.println("\nVISUALIZAMOS LA PROPIEDAD CON ID 1");
		Propiedad propiedad = ClientManager.getInstance().getPropiedadById(1);
		System.out.println(propiedad.toString());

		// ALQUILERES

		// INSERTAR ALQUILERES
		/*
		System.out.println("\nINSERTAMOS TRES ALQUILERES DE PRUEBA");
		java.sql.Date date = new java.sql.Date(new java.util.Date(2016, 01, 01).getDate());
		Alquiler alq1 = new Alquiler(cl1, new Actividad(1, "Jugar a futbol"), new Propiedad(1, "Polideportivo Karrantza"), date, date, 500);
		Alquiler alq2 = new Alquiler(cl2, new Actividad(1, "Jugar a futbol"), new Propiedad(1, "Polideportivo Karrantza"), date, date, 700);
		Alquiler alq3 = new Alquiler(cl1, new Actividad(2, "Jugar a baloncesto"), new Propiedad(2, "Cancha de baloncesto Karrantza"), date, date,
				450);

		if (ClientManager.getInstance().saveAlquiler(alq1)) {
			System.out.println("Insertado correctamente");
		} else {
			System.out.println("Ya existe");
		}
		if (ClientManager.getInstance().saveAlquiler(alq2)) {
			System.out.println("Insertado correctamente");
		} else {
			System.out.println("Ya existe");
		}
		if (ClientManager.getInstance().saveAlquiler(alq3)) {
			System.out.println("Insertado correctamente");
		} else {
			System.out.println("Ya existe");
		}
		*/
		// OBTENEMOS TODOS LOS ALQUILERES DEL CLIENTE 1 Y LOS MOSTRAMOS
		System.out.println("\nVISUALIZAMOS TODOS LOS ALQUILERES DEL CLIENTE 1 DE LA BASE DE DATOS");
		ArrayList<Alquiler> alquileres = ClientManager.getInstance().getAlquileresByDniCliente(cl1.getDni());
		for (Alquiler a : alquileres) {
			System.out.println(a.toString());
		}
		
		
		// EDITAMOS EL PRIMER ALQUILER DEL CLIENTE 1
		System.out.println("\nEDITAMOS EL PRIMER ALQUILER DEL CLIENTE 1");
		Alquiler alquilerEditado = alquileres.get(0);
		alquilerEditado.setPrecio(9999);
		System.out.println("Alquiler: " + alquilerEditado.toString());
		if (ClientManager.getInstance().editAlquiler(alquilerEditado)) {
			System.out.println("Alquiler editado.");
		} else {
			System.out.println("No se ha podido editar el alquiler");
		}

		// BORRAMOS EL SEGUNDO ALQUILER DEL CLIENTE 1
		System.out.println("\nELIMINAMOS EL SEGUNDO ALQUILER DEL CLIENTE 1");
		if (ClientManager.getInstance().deleteCliente(alquileres.get(1).getIdAlquiler())) {
			System.out.println("Alquiler '" + alquileres.get(1).getIdAlquiler() + "' borrado correctamente de la base de datos.");
		} else {
			System.out.println("El alquiler '" + alquileres.get(1).getIdAlquiler() + "' no se ha borrado de la base de datos.");
		}
	}
}
