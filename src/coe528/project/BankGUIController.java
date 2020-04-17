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
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 *
 * @author John Cabuguason
 */
public class BankGUIController implements Initializable {
    
    private Label label;
    @FXML
    private Button login;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField userName;
    @FXML
    private Button managerLogin;
    @FXML
    private Label errorLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    @FXML
    private void getPasswordInput(ActionEvent event) {
    }
    
    @FXML
    private void CustomerSignInButton(ActionEvent event) throws IOException{
        
        Bank bank = Bank.getInstance();
        
        Customer instance = bank.getCustomer(userName.getText(), password.getText());
        
        //implement login instead of this later
        if(instance != null){
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CustomerPage.fxml"));
            Parent customerPage = loader.load();    
            
            Scene customerPageScene = new Scene(customerPage);
            
            //access the controller
            CustomerPageController controller = loader.getController();
            controller.setCustomerData(instance);
            
            Stage home_stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            home_stage.setScene(customerPageScene);
            home_stage.show();
        }
        else
            errorLabel.setText("Please check your Credentials");
            
    }

    @FXML
    private void ManagerSignInButton(ActionEvent event) throws IOException{
        
        Manager instance = new Manager(userName.getText(),password.getText());   
       
        if(instance.login()){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManagerPage.fxml"));
            Parent managerPage = loader.load();
            
            Scene managerPageScene = new Scene(managerPage);
            
            //access the controller
            ManagerPageController controller = loader.getController();
            controller.setManagerData(instance);
            
            Stage home_stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            home_stage.setScene(managerPageScene);
            home_stage.show();
        }
        else
            errorLabel.setText("Please check your Credentials");
    }
    
}
