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

public class ApplicationService2 implements Initializable{
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
	 private TableView<Employee> table2;

	 @FXML
	 private TableColumn<Employee, Integer> colmatricule;
	 @FXML
	 private TableColumn<Employee, String> colnom;
	 @FXML
	 private TableColumn<Employee, String> colemail;
	 @FXML
	 private TableColumn<Employee, Double> colrecrutement;
	 @FXML
	 private TableColumn<Employee, Double> colsalaire;
	 @FXML
	 private TableColumn<Employee, Double> colhsupp;
	 @FXML
	 private TableColumn<Employee, Double> colphsupp;

	    


		
			    public ObservableList<Employee> getEmployeeList(){
			    	ObservableList<Employee> employeeList = FXCollections.observableArrayList();
			        String query = "SELECT * FROM Employee e, Salarie s where e.Matricule=s.Matricule";
			        ResultSet rs;
			        
			        try{
						Statement stmt = connexion.getConx().createStatement();
			            rs = stmt.executeQuery(query);
			            Employee employee;
			            while(rs.next()){
			                employee = new Employee(rs.getInt("matricule"), rs.getString("nom"), rs.getString("email"), rs.getDouble("recrutement"),rs.getDouble("salaire"),rs.getDouble("HSupp"),rs.getDouble("PHsupp"));
			                employeeList.add(employee);
			            }
			                
			        }catch(Exception ex){
			            ex.printStackTrace();
			        }
					return employeeList;

			    }




		
	
	public static void main (String Args[]) {
		ApplicationService2 AS = new ApplicationService2();
		//AS.createEntreprise(new Entreprise("Entrp1"));

		}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Employee> listEmployee = getEmployeeList();

		colmatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
		colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colrecrutement.setCellValueFactory(new PropertyValueFactory<>("recrutement"));
		colsalaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
		colhsupp.setCellValueFactory(new PropertyValueFactory<>("HSupp"));
		colphsupp.setCellValueFactory(new PropertyValueFactory<>("PHsupp"));

        table2.setItems(listEmployee);


		
	}
}
