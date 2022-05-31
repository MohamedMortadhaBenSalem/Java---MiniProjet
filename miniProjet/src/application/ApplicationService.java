package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import application.connection.connexion;
import application.entities.Employee;
import application.entities.Entreprise;
import application.entities.Salarie;
import application.entities.Vendeur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn; 
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;


public class ApplicationService implements Initializable {
	@FXML
	private TextField tfentreprise;

	@FXML
	private TextField tfmatricule;
	@FXML
	private TextField tfnom;
	@FXML
	private TextField tfemail;
	@FXML
	private TextField tfrecrutement;
	@FXML
	private TextField tfsalaire;
	@FXML
	private TextField tfhsupp;
	@FXML
	private TextField tfphsupp;
	@FXML
	private TextField tfvente;
	@FXML
	private TextField tfpourcentage;
	@FXML
	private RadioButton rbemployee;
	@FXML
	private RadioButton rbvendeur;

	@FXML
	private TextField tfmin;
	@FXML
	private TextField tfmax;

	@FXML

	private TableView<Salarie> table;
	@FXML
	private TableColumn<Vendeur, Double> colvente;
	@FXML
	private TableColumn<Vendeur, Double> colpourcentage;

	@FXML
	private TableColumn<Salarie, Integer> colmatricule;
	@FXML
	private TableColumn<Salarie, String> colnom;
	@FXML
	private TableColumn<Salarie, String> colemail;
	@FXML
	private TableColumn<Salarie, Double> colrecrutement;
	@FXML
	private TableColumn<Salarie, Double> colsalaire;
	@FXML
	private TableColumn<Employee, Double> colhsupp;
	@FXML
	private TableColumn<Employee, Double> colphsupp;
	@FXML
	private Button btnInsert;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnMaxTauxVente;
	@FXML
	private Button btnSalaireCompris;
	@FXML
	private Button btnAnciennete;
	@FXML
	private Button btnSalaireFaible;
	@FXML
	private Button btnListeEmployee;
	 private Stage stage;
	 private Scene scene;
	 private Parent root;

	@FXML
	private void handleButtonAction(ActionEvent event) {

		if (event.getSource() == btnUpdate) {
			updateSalarie();
		} else if (event.getSource() == btnDelete) {
			deleteSalarie();
		} else if (event.getSource() == btnInsert) {
			createSalarie();
		}

	}
	
	public void setHSupp() {
		File inputFile = new File("C:\\fichiers\\employee.txt");
		FileReader fr;
		BufferedReader br;
		String PHSupp="";
		try {
			rbemployee.setSelected(true);
			fr =  new FileReader(inputFile);
			br = new BufferedReader(fr);
			String s;
			boolean ok = false;
			while((s=br.readLine())!=null) {
				String [] fileData = s.split(" ");
				if (fileData [0].equals("PHSupp")) {
					PHSupp=fileData[1];
				}
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
				}
		tfphsupp.setText(PHSupp);
				}

			public void setPourcentage() {
				File inputFile = new File("C:\\fichiers\\employee.txt");
				FileReader fr;
				BufferedReader br;
				String Pourcentage="";
				try {
					rbvendeur.setSelected(true);
					fr =  new FileReader(inputFile);
					br = new BufferedReader(fr);
					String s;
					boolean ok = false;
					while((s=br.readLine())!=null) {
						String [] fileData = s.split(" ");
						if (fileData [0].equals("Pourcentage")) {
							Pourcentage=fileData[1];
						}
					}
				}catch (FileNotFoundException e) {
					e.printStackTrace();
					}catch (IOException e) {
						e.printStackTrace();
						}
				tfpourcentage.setText(Pourcentage);
						}
	
	@FXML
	void handelMouseAvtion(MouseEvent event) {
		Salarie Salarie = table.getSelectionModel().getSelectedItem();
		
		tfmatricule.setText("" + Salarie.getMatricule());
		tfnom.setText(Salarie.getNom());
		tfemail.setText(Salarie.getEmail());
		tfrecrutement.setText("" + Salarie.getRecrutement());
		tfsalaire.setText("" + Salarie.getSalaire());

		

	}
	 public void switchToVueScene(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("interface2.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }
	 public void switchToVueScene1(ActionEvent event) throws IOException {
		  root = FXMLLoader.load(getClass().getResource("interface3.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
		 }

	public void createSalarie() {
		try {
			Statement stmt = connexion.getConx().createStatement();

			if (Integer.parseInt(tfrecrutement.getText()) >= 2005) {
				tfsalaire.setText("400");
			} else {
				tfsalaire.setText("280");

			}
			if (rbemployee.isSelected()) {
				String reqSalarie = "Insert into salarie values(" + tfmatricule.getText() + ",'" + tfnom.getText()
						+ "','" + tfemail.getText() + "'," + tfrecrutement.getText() + ","
						+ (Double.parseDouble(tfsalaire.getText())
								+ Double.parseDouble(tfhsupp.getText()) * Double.parseDouble(tfphsupp.getText()))
						+ "," + tfentreprise.getText() + ")";
				if (stmt.executeUpdate(reqSalarie) == 1) {
					System.out.println("Insertion a Salarie  a étè effectuer !");
					lister();
				}
				String reqEmployee = "Insert into Employee values (" + tfmatricule.getText() + "," + tfhsupp.getText()
						+ "," + tfphsupp.getText() + ");";
				if (stmt.executeUpdate(reqEmployee) == 1) {
					System.out.println("Insertion a Employee  a étè effectuer !");
				}
			}
			if (rbvendeur.isSelected()) {
				String reqSalarie = "Insert into salarie values(" + tfmatricule.getText() + ",'" + tfnom.getText()
						+ "','" + tfemail.getText() + "'," + tfrecrutement.getText() + ","
						+ (Double.parseDouble(tfsalaire.getText())
								+ Double.parseDouble(tfvente.getText()) * Double.parseDouble(tfpourcentage.getText()))
						+ "," + tfentreprise.getText() + ")";
				if (stmt.executeUpdate(reqSalarie) == 1) {
					System.out.println("Insertion a Salarie  a étè effectuer !");
					lister();
				}

				String reqVendeur = "Insert into Vendeur values (" + tfmatricule.getText() + "," + tfvente.getText()
						+ "," + tfpourcentage.getText() + ");";
				if (stmt.executeUpdate(reqVendeur) == 1) {
					System.out.println("Insertion a Vendeur  a étè effectuer !");
				}
			}

		} catch (SQLException ex) {
			System.out.println("Erreur SQL " + ex);

		}
	}

	public void deleteSalarie() {
		try {
			String req = "delete from salarie where matricule="
					+ table.getSelectionModel().getSelectedItem().getMatricule() + "";
			Statement stmt = connexion.getConx().createStatement();

			if (stmt.executeUpdate(req) == 1) {
				lister();
				System.out.println("Salarie a été supprimer");
			}
		} catch (SQLException ex) {
			System.out.println("Erreur");
		}
	}

	public void updateSalarie() {
		try {			
			Statement stmt = connexion.getConx().createStatement();
			if (rbemployee.isSelected()) {
				String reqSalarie = "UPDATE  Salarie s, Employee e SET s.Nom  = '" + tfnom.getText() + "', s.Email = '"+ tfemail.getText() + "', s.Recrutement = '" + tfrecrutement.getText() + "', s.Salaire = '"+ tfsalaire.getText() + "', e.HSupp = '" + tfhsupp.getText() + "', e.PHSupp = '"+ tfphsupp.getText() + "' WHERE s.Matricule=e.Matricule and s.Matricule = " + tfmatricule.getText()+ "";
				if (stmt.executeUpdate(reqSalarie) == 1) {
					System.out.println("Modification a Employee  a étè effectuer !");
					lister();
				}			
			}
			if (rbvendeur.isSelected()) {
				String reqSalarie = "UPDATE  Salarie s, Vendeur v SET s.Nom  = '" + tfnom.getText() + "', s.Email = '"+ tfemail.getText() + "', s.Recrutement = '" + tfrecrutement.getText() + "', s.Salaire = '"+ tfsalaire.getText() + "', v.Vente = '" + tfvente.getText() + "', v.Pourcentage = '"+ tfpourcentage.getText() + "' WHERE s.Matricule=v.Matricule and s.Matricule = " + tfmatricule.getText()+ "";
				if (stmt.executeUpdate(reqSalarie) == 1) {
					System.out.println("Modification a Vendeur  a étè effectuer !");
					lister();
				}			
			}
		} catch (SQLException ex) {
			System.out.println("Erreur" + ex);
			
			
			
		}
	}

	public void createEntreprise(Entreprise E) {
		try {
			String req = "Insert into entreprise (nom) values ('" + E.getNomEntreprise() + "')";
			Statement stmt = connexion.getConx().createStatement();
			if (stmt.executeUpdate(req) == 1) {

				System.out.println("Entreprise" + E.getNomEntreprise() + "a été créer avec success!");

			}
		} catch (SQLException ex) {
			System.out.println("Erreur SQL");
		}
	}

	public ObservableList<Salarie> getSalarieList() {
		ObservableList<Salarie> salarieList = FXCollections.observableArrayList();
		String query = "SELECT * FROM Salarie";
		ResultSet rs;

		try {
			Statement stmt = connexion.getConx().createStatement();
			rs = stmt.executeQuery(query);
			Salarie salarie;
			while (rs.next()) {
				salarie = new Salarie(rs.getInt("matricule"), rs.getString("nom"), rs.getString("email"),
						rs.getDouble("recrutement"), rs.getDouble("salaire"));
				salarieList.add(salarie);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return salarieList;

	}

	public ObservableList<Salarie> getMaxVente() {
		ObservableList<Salarie> salarieList = FXCollections.observableArrayList();
		String query = "Select s.matricule, nom, email, recrutement, salaire, (salaire + vente * pourcentage) from salarie s ,vendeur v where v.matricule= s.matricule order by vente desc";
		ResultSet rs;

		try {
			Statement stmt = connexion.getConx().createStatement();
			rs = stmt.executeQuery(query);
			Salarie salarie;
			table.getItems().clear();
			while (rs.next()) {
				salarie = new Salarie(rs.getInt("matricule"), rs.getString("nom"), rs.getString("email"),
						rs.getDouble("recrutement"), rs.getDouble("salaire"));
				table.getItems().add(salarie);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return salarieList;

	}

	public ObservableList<Salarie> SalaireCompris() {
		ObservableList<Salarie> salarieList = FXCollections.observableArrayList();
		String query = "select * from salarie where salaire > " + tfmin.getText() + " and salaire < " + tfmax.getText()
				+ "";
		ResultSet rs;

		try {
			Statement stmt = connexion.getConx().createStatement();
			rs = stmt.executeQuery(query);
			Salarie salarie;
			table.getItems().clear();
			while (rs.next()) {
				salarie = new Salarie(rs.getInt("matricule"), rs.getString("nom"), rs.getString("email"),
						rs.getDouble("recrutement"), rs.getDouble("salaire"));
				table.getItems().add(salarie);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return salarieList;

	}

	public ObservableList<Salarie> Anciennete() {
		ObservableList<Salarie> salarieList = FXCollections.observableArrayList();
		String query = "select * from salarie order by recrutement ";
		ResultSet rs;

		try {
			Statement stmt = connexion.getConx().createStatement();
			rs = stmt.executeQuery(query);
			Salarie salarie;
			table.getItems().clear();
			while (rs.next()) {
				salarie = new Salarie(rs.getInt("matricule"), rs.getString("nom"), rs.getString("email"),
						rs.getDouble("recrutement"), rs.getDouble("salaire"));
				table.getItems().add(salarie);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return salarieList;

	}

	public ObservableList<Salarie> SalaireFaible() {
		ObservableList<Salarie> salarieList = FXCollections.observableArrayList();
		String query = "select * from salarie order by salaire  ";
		ResultSet rs;

		try {
			Statement stmt = connexion.getConx().createStatement();
			rs = stmt.executeQuery(query);
			Salarie salarie;
			table.getItems().clear();
			while (rs.next()) {
				salarie = new Salarie(rs.getInt("matricule"), rs.getString("nom"), rs.getString("email"),
						rs.getDouble("recrutement"), rs.getDouble("salaire"));
				table.getItems().add(salarie);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return salarieList;

	}
	
	
	


	public void lister() {
		ObservableList<Salarie> listSalarie = getSalarieList();

		colmatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
		colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colrecrutement.setCellValueFactory(new PropertyValueFactory<>("recrutement"));
		colsalaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));

		table.setItems(listSalarie);

	}
	
	

	public static void main(String Args[]) {
		ApplicationService AS = new ApplicationService();
		// AS.createEntreprise(new Entreprise("Entrp1"));

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lister();

	}

}
