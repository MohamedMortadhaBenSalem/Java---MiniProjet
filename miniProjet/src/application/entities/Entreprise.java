package application.entities;

import java.util.HashMap;

public class Entreprise {
	private String nomEntreprise;
	private HashMap<Integer,Salarie> salaries;
	public Entreprise(String nomEntreprise) {
		super();
		this.nomEntreprise = nomEntreprise;
	}
	public String getNomEntreprise() {
		return nomEntreprise;
	}
	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}
	public HashMap<Integer, Salarie> getSalaries() {
		return salaries;
	}
	public void setSalaries(HashMap<Integer, Salarie> salaries) {
		this.salaries = salaries;
	}

	
}
