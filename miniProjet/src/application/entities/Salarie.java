package application.entities;

public class Salarie {
	
	private int Matricule;
	private String Nom;
	private String Email;
	private double Recrutement;
	private double Salaire;
	public Salarie(int matricule, String nom, String email, double recrutement, double salaire) {
		super();
		Matricule = matricule;
		Nom = nom;
		Email = email;
		Recrutement = recrutement;
		Salaire = salaire;
	}
	public int getMatricule() {
		return Matricule;
	}
	public void setMatricule(int matricule) {
		Matricule = matricule;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public double getRecrutement() {
		return Recrutement;
	}
	public void setRecrutement(double recrutement) {
		Recrutement = recrutement;
	}
	public double getSalaire() {
		return Salaire;
	}
	public void setSalaire(double salaire) {
		Salaire = salaire;
	}



}
