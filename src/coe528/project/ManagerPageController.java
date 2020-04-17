/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.Global.instance;

/**
 * FXML Controller class
 *
 * @author John Cabuguason
 */
public class ManagerPageController implements Initializable {

    @FXML
    private ListView<Customer> listCustomers;
    @FXML
    private TextField userNameAdd;
    @FXML
    private TextField passwordAdd;
    @FXML
    private Button addCustomer;
    @FXML
    private Label errorLabel2;
    @FXML
    private TextField userNameRemove;
    @FXML
    private TextField passwordRemove;
    @FXML
    private Button removeCustomer;
    @FXML
    private Label errorLabel1;
    @FXML
    private Label managerLabel;

    private Manager current;
    @FXML
    private Button logoutButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setManagerData(Manager m){
        this.current= m;
        managerLabel.setText("Manager: "+this.current.getUsername());
        this.updateList();
        errorLabel1.setText("");
        errorLabel2.setText("");
    }
    
    public void updateList(){
        listCustomers.getItems().clear();
        listCustomers.getItems().addAll(Bank.customers);
    }

    @FXML
    private void AddCustomer(ActionEvent event) {
        
        errorLabel2.setText("");
        
        if(userNameAdd.getText().equals("") || passwordAdd.getText().equals("")){
            errorLabel2.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
            errorLabel2.setText("Enter input for customer info");
        }
        else{
            Customer toAdd = new Customer(userNameAdd.getText(),passwordAdd.getText());
            
            if(this.current.addCustomer(toAdd)){
                errorLabel2.setStyle("-fx-text-fill: green; -fx-font-size: 12px;");
                errorLabel2.setText("Successfully added!");
            }
            else{
                errorLabel2.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
                errorLabel2.setText("Username already exists!");
            }   
            
        }
        this.updateList();
    }
    
    @FXML
    private void RemoveCustomer(ActionEvent event) {
        
        errorLabel1.setText("");
        Bank instance = Bank.getInstance();
        
        if(userNameRemove.getText().equalsIgnoreCase("customers"))
            return;
        if(userNameRemove.getText().equals("") || passwordRemove.getText().equals("")){
            errorLabel1.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
            errorLabel1.setText("Enter input for customer info");
        }
        else if(instance.getCustomer(userNameRemove.getText(), passwordRemove.getText()) != null){
      
            Customer toDelete = instance.getCustomer(userNameRemove.getText(),passwordRemove.getText());
            this.current.deleteCustomer(toDelete);
            errorLabel1.setStyle("-fx-text-fill: green; -fx-font-size: 12px;");
            errorLabel1.setText("Successfully deleted!");
        }
        else{
            errorLabel1.setText("Unsuccesful deletion.");
        }
        this.updateList();
        
    }

    @FXML
    private void logoutManager(ActionEvent event) throws IOException{
        
        current.logout();
        
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("BankGUI.fxml"));
            Parent mainGUI = loader.load();
            
            Scene mainGUI_scene = new Scene(mainGUI);
            
            Stage home_stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            home_stage.setScene(mainGUI_scene);
            home_stage.show();     
         
    
    }
    
}
