package application.entities;

public class Vendeur extends Salarie{
	private double Vente;
	private double Pourcentage;
	public Vendeur(int matricule, String nom, String email, double recrutement, double salaire, double vente,
			double pourcentage) {
		super(matricule, nom, email, recrutement, salaire);
		Vente = vente;
		Pourcentage = pourcentage;
	}
	public double getVente() {
		return Vente;
	}
	public void setVente(double vente) {
		Vente = vente;
	}
	public double getPourcentage() {
		return Pourcentage;
	}
	public void setPourcentage(double pourcentage) {
		Pourcentage = pourcentage;
	}
	

}
