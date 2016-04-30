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

import entities.CustomActividad;
import entities.CustomAlquiler;
import entities.CustomCliente;
import entities.CustomPropiedad;

public class ClientManager {

	private static ClientManager	instance;

	public static int				SUCCESS_CODE		= 201;
	public static int				EDIT_SUCCESS_CODE	= 204;
	public static int				ERROR_CODE			= 205;
	public static int				ALREADY_EXIST_CODE	= 206;

	private static String			URL					= "http://localhost:8080/PropiedadesServidorREST_MYBATIS/";

	private DefaultClientConfig		config;
	private Client					client;
	private WebResource				service;

	private ClientManager() {
		config = new DefaultClientConfig();
		client = Client.create(config);
		service = client.resource(UriBuilder.fromUri(URL).build());
	}

	// CLIENTES
	public CustomCliente getClienteById(final int id) {
		CustomCliente client = service.path("rest").path("clientes").path(Integer.toString(id)).get(CustomCliente.class);
		return client;
	}

	public ArrayList<CustomCliente> getAllClientes() {
		CustomCliente[] array = null;
		try {
			array = service.path("rest").path("clientes").get(CustomCliente[].class);
			return new ArrayList<CustomCliente>(Arrays.asList(array));
		} catch (Exception e) {
			return null;
		}
	}

	public boolean saveCliente(final CustomCliente client) {
		try {
			ClientResponse response = service.path("rest").path("clientes").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, client);
			return response.getStatus() == SUCCESS_CODE ? true : false;
		} catch (UniformInterfaceException e) {
			ClientResponse r = e.getResponse();
			System.out.println("clients.{id}.DEL.status: " + r.getStatus());
			System.out.println("clients.{id}.DEL.entity: " + r.getEntity(String.class));
			return false;
		}
	}

	public boolean editCliente(final CustomCliente client) {
		ClientResponse response = service.path("rest").path("clientes").path(Integer.toString(client.getDni())).type(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class, client);
		return response.getStatus() == EDIT_SUCCESS_CODE ? true : false;
	}

	public boolean deleteCliente(final int id) {
		try {
			service.path("rest").path("clientes").path(Integer.toString(id)).delete();
			return true;
		} catch (UniformInterfaceException e) {
			ClientResponse r = e.getResponse();
			System.out.println("clients.{id}.DEL.status: " + r.getStatus());
			System.out.println("clients.{id}.DEL.entity: " + r.getEntity(String.class));
			return false;
		}
	}

	// ALQUILERES
	public ArrayList<CustomAlquiler> getAlquileresByDniCliente(final int dni) {
		CustomAlquiler[] array = service.path("rest").path("alquileres").path("cliente").path(Integer.toString(dni)).get(CustomAlquiler[].class);
		return new ArrayList<CustomAlquiler>(Arrays.asList(array));
	}

	public boolean saveAlquiler(final CustomAlquiler alquiler) {
		try {
			ClientResponse response = service.path("rest").path("alquileres").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, alquiler);
			return response.getStatus() == SUCCESS_CODE ? true : false;
		} catch (UniformInterfaceException e) {
			ClientResponse r = e.getResponse();
			System.out.println("alquileres.{id}.DEL.status: " + r.getStatus());
			System.out.println("alquileres.{id}.DEL.entity: " + r.getEntity(String.class));
			return false;
		}
	}

	public boolean editAlquiler(final CustomAlquiler alquiler) {
		ClientResponse response = service.path("rest").path("alquileres").path("alquiler").path(Integer.toString(alquiler.getIdAlquiler()))
				.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, alquiler);
		return response.getStatus() == EDIT_SUCCESS_CODE ? true : false;
	}

	public boolean deleteAlquiler(final int id) {
		try {
			service.path("rest").path("alquileres").path("alquiler").path(Integer.toString(id)).delete();
			return true;
		} catch (UniformInterfaceException e) {
			ClientResponse r = e.getResponse();
			System.out.println("alquileres.{id}.DEL.status: " + r.getStatus());
			System.out.println("alquileres.{id}.DEL.entity: " + r.getEntity(String.class));
			return false;
		}
	}

	// ACTIVIDADES
	public ArrayList<CustomActividad> getAllActividades() {
		CustomActividad[] array = service.path("rest").path("actividades").get(CustomActividad[].class);
		return new ArrayList<CustomActividad>(Arrays.asList(array));
	}

	public ArrayList<CustomActividad> getActividadesByIdPropiedad(final int idPropiedad) {
		CustomActividad[] array = service.path("rest").path("actividades").path(Integer.toString(idPropiedad)).get(CustomActividad[].class);
		return new ArrayList<CustomActividad>(Arrays.asList(array));
	}

	// PROPIEDADES
	public ArrayList<CustomPropiedad> getAllPropiedades() {
		CustomPropiedad[] array = service.path("rest").path("propiedades").get(CustomPropiedad[].class);
		return new ArrayList<CustomPropiedad>(Arrays.asList(array));
	}

	public CustomPropiedad getPropiedadById(final int idPropiedad) {
		CustomPropiedad propiedad = service.path("rest").path("propiedades").path(Integer.toString(idPropiedad)).get(CustomPropiedad.class);
		return propiedad;
	}

	public static ClientManager getInstance() {
		if (instance == null) {
			instance = new ClientManager();
		}
		return instance;
	}
}