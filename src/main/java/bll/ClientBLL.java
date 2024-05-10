package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.Validator;
import dao.AbstractDAO;
import dao.ClientDAO;
import model.Client;



/**
 * Clasa ClientBLL
 * Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * Apr 03, 2017
 */
public class ClientBLL {

	private List<Validator<Client>> validators;
	private ClientDAO clientDAO;

	public ClientBLL() {
		validators = new ArrayList<Validator<Client>>();
		clientDAO = new ClientDAO();
	}

	public Client findClientById(int id) {
		AbstractDAO abstractDAO=new AbstractDAO();
		Client st = (Client) abstractDAO.findById(id,Client.class);
		if (st == null) {
			throw new NoSuchElementException("The student with id =" + id + " was not found!");
		}
		return st;
	}

	public Client updateClient(Client client, int id) throws IllegalAccessException {
		Client client1 = clientDAO.update(client, id);
		if (client1 == null) {
			throw new NoSuchElementException("Could not update the client!");
		}
		return client1;
	}

	public Client deleteClient(Client client, int id) throws IllegalAccessException {
		Client client1 = clientDAO.delete(client, id);
		if (client1 != null) {
			throw new NoSuchElementException("Could not delete the client!");
		}
		return client1;
	}

//	public void deleteClient1(int id) throws IllegalAccessException {
//		clientDAO.delete1(id);
//	}

}
