package utilities;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import entities.Actividad;
import entities.Alquiler;
import entities.Cliente;
import entities.Propiedad;

public class ClientManager {

	private static ClientManager	instance;

	public static int				SUCCESS_CODE		= 200;
	public static int				ERROR_CODE			= 201;
	public static int				ALREADY_EXIST_CODE	= 202;

	private static String			URL					= "http://localhost:8080/PropiedadesServidorREST/";

	private DefaultClientConfig		config;
	private Client					client;
	private WebResource				service;

	private ClientManager() {
		config = new DefaultClientConfig();
		client = Client.create(config);
		service = client.resource(UriBuilder.fromUri(URL).build());
	}

	// CLIENTES
	public Cliente getClienteById(final int id) {
		ClientResponse response = service.path("rest").path("clientes").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, id);
		return response.getStatus() == SUCCESS_CODE ? response.getEntity(Cliente.class) : null;
	}

	public ArrayList<Cliente> getAllClientes() {
		Cliente[] array = service.path("rest").path("clientes").get(Cliente[].class);
		return new ArrayList<Cliente>(Arrays.asList(array));
	}

	public boolean deleteCliente(final Cliente client) {
		try {
			service.path("rest").path("clientes").path(Integer.toString(client.getDni())).delete();
			return true;
		} catch (UniformInterfaceException e) {
			ClientResponse r = e.getResponse();
			System.out.println("todos.{id}.DEL.status: " + r.getStatus());
			System.out.println("todos.{id}.DEL.entity: " + r.getEntity(String.class));
			return false;
		}
	}

	public boolean saveCliente(final Cliente client) {
		try {
			ClientResponse response = service.path("rest").path("clientes").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, client);
			return response.getStatus() == SUCCESS_CODE ? true : false;
		} catch (UniformInterfaceException e) {
			ClientResponse r = e.getResponse();
			System.out.println("todos.{id}.DEL.status: " + r.getStatus());
			System.out.println("todos.{id}.DEL.entity: " + r.getEntity(String.class));
			return false;
		}
	}

	// ALQUILERES
	public boolean saveAlquiler(final Alquiler alquiler) {
		try {
			ClientResponse response = service.path("rest").path("alquileres").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, alquiler);
			return response.getStatus() == SUCCESS_CODE ? true : false;
		} catch (UniformInterfaceException e) {
			ClientResponse r = e.getResponse();
			System.out.println("todos.{id}.DEL.status: " + r.getStatus());
			System.out.println("todos.{id}.DEL.entity: " + r.getEntity(String.class));
			return false;
		}
	}

	public boolean deleteAlquiler(final Alquiler alquiler) {
		try {
			service.path("rest").path("alquileres").path(Integer.toString(alquiler.getId())).delete();
			return true;
		} catch (UniformInterfaceException e) {
			ClientResponse r = e.getResponse();
			System.out.println("todos.{id}.DEL.status: " + r.getStatus());
			System.out.println("todos.{id}.DEL.entity: " + r.getEntity(String.class));
			return false;
		}
	}

	// PROPIEDADES
	public ArrayList<Propiedad> getAllPropiedades() {
		Propiedad[] array = service.path("rest").path("propiedades").path("todas").get(Propiedad[].class);
		return new ArrayList<Propiedad>(Arrays.asList(array));
	}

	// ACTIVIDADES
	public ArrayList<Actividad> getActividadesByPropiedad(final Propiedad propiedad) {
		Actividad[] array = service.path("rest").path("actividades").path("propiedades").path(Integer.toString(propiedad.getId()))
				.get(Actividad[].class);
		return new ArrayList<Actividad>(Arrays.asList(array));
	}

	public ArrayList<Actividad> getAllActividades() {
		Actividad[] array = service.path("rest").path("actividades").path("todas").get(Actividad[].class);
		return new ArrayList<Actividad>(Arrays.asList(array));
	}

	public static ClientManager getInstance() {
		if (instance == null) {
			instance = new ClientManager();
		}
		return instance;
	}

	public void main(String[] args) {

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
		System.out.println("\nVISUALIZAMOS TODOS LOS CLIENTES Y SUS ALQUILERES DE LA BASE DE DATOS");
		ArrayList<Cliente> clientes = ClientManager.getInstance().getAllClientes();
		for (Cliente c : clientes) {
			System.out.println(c.toString());
			for (Alquiler a : c.getAlquileres()) {
				System.out.println(" - " + a.toString());
			}
		}

		// EDITAMOS EL CLIENTE LLAMADO ENDIKA
		System.out.println("\nEDITAMOS EL CLIENTE 'ENDIKA'");
		cl1.setNombre("Endika Editado");
		if (ClientManager.getInstance().saveCliente(cl1)) {
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
		if (ClientManager.getInstance().deleteCliente(cl3)) {
			System.out.println("Cliente '" + cl3.getNombre() + "' borrado correctamente de la base de datos.");
		} else {
			System.out.println("El cliente '" + cl3.getNombre() + "' no se ha borrado de la base de datos.");
		}

	}
}