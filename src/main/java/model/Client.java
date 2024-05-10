package model;

/**
 * Clasa Client
 */
public class Client {
	private int id;
	private String nume;
	private String prenume;


	public Client() {
	}

	public Client(int id) {
		this.id=id;
	}

	public Client(int id, String nume, String prenume) {
		this.id = id;
		this.nume = nume;
		this.prenume = prenume;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String name) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nume=" + nume + ", prenume=" + prenume + "]";
	}

}
