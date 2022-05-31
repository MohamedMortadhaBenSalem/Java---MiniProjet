package application.entities;

public class Employee extends Salarie {
	private double HSupp;
	private double PHsupp;
	public Employee(int matricule, String nom, String email, double recrutement, double salaire, double hsupp, double phsupp) {
		super(matricule, nom, email, recrutement, salaire);
		HSupp = hsupp;
		PHsupp = phsupp;

	}
	public double getHSupp() {
		return HSupp;
	}
	public void setHSupp(double hSupp) {
		HSupp = hSupp;
	}
	public double getPHsupp() {
		return PHsupp;
	}
	public void setPHsupp(double pHsupp) {
		PHsupp = pHsupp;
	}

}
