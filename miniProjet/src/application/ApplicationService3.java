package application;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ApplicationService3 implements Initializable{
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
	 private TextField tfvente;
	 @FXML
	 private TextField tfpourcentage;
	 @FXML
	 private TableView<Vendeur> table3;

	 @FXML
	 private TableColumn<Vendeur, Integer> colmatricule;
	 @FXML
	 private TableColumn<Vendeur, String> colnom;
	 @FXML
	 private TableColumn<Vendeur, String> colemail;
	 @FXML
	 private TableColumn<Vendeur, Double> colrecrutement;
	 @FXML
	 private TableColumn<Vendeur, Double> colsalaire;
	 @FXML
	 private TableColumn<Vendeur, Double> colvente;
	 @FXML
	 private TableColumn<Vendeur, Double> colpourcentage;

	


	 

		
			    public ObservableList<Vendeur> getVendeurList(){
			    	ObservableList<Vendeur> vendeurList = FXCollections.observableArrayList();
			        String query = "SELECT * FROM Vendeur v, Salarie s where v.Matricule=s.Matricule";
			        ResultSet rs;
			        
			        try{
						Statement stmt = connexion.getConx().createStatement();
			            rs = stmt.executeQuery(query);
			            Vendeur vendeur;
			            while(rs.next()){
			                vendeur = new Vendeur(rs.getInt("matricule"), rs.getString("nom"), rs.getString("email"), rs.getDouble("recrutement"),rs.getDouble("salaire"),rs.getDouble("vente"),rs.getDouble("pourcentage"));
			                vendeurList.add(vendeur);
			            }
			                
			        }catch(Exception ex){
			            ex.printStackTrace();
			        }
					return vendeurList;

			    }




		
	
	public static void main (String Args[]) {
		ApplicationService3 AS = new ApplicationService3();

		}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Vendeur> listVendeur = getVendeurList();

		colmatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
		colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colrecrutement.setCellValueFactory(new PropertyValueFactory<>("recrutement"));
		colsalaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
		colvente.setCellValueFactory(new PropertyValueFactory<>("vente"));
		colpourcentage.setCellValueFactory(new PropertyValueFactory<>("pourcentage"));

        table3.setItems(listVendeur);


		
	}
}
