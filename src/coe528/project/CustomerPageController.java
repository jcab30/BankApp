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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author John Cabuguason
 */
public class CustomerPageController implements Initializable {

    @FXML
    private Label usernameLabel;
    @FXML
    private Button depositButton;
    @FXML
    private Button withdrawButton;
    @FXML
    private Button purchaseButton;
    @FXML
    private TextField depositAmount;
    @FXML
    private TextField withdrawAmount;
    @FXML
    private TextField purchasePrice;
    @FXML
    private Label customerErrorLabel;
    @FXML
    private ProgressBar balanceAmount;
    @FXML
    private Label accountNumLabel;
    @FXML
    private Label membershipLabel;
    @FXML
    private Label balanceLabel;
    @FXML
    private Button logoutButton;
        
    private Customer currentCustomer;
    @FXML
    private Button repOKButton;
    @FXML
    private Label abstractionLabel;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    public void setCustomerData(Customer c){
        currentCustomer = c;
        usernameLabel.setText("Username: "+this.currentCustomer.getUsername());
        accountNumLabel.setText("Account #: "+this.currentCustomer.getBankAccount().getAccountNum());
        balanceLabel.setText("Account Balance: "+this.currentCustomer.getBankAccount().getBalance());
        membershipLabel.setText("Membership Level: "+this.currentCustomer.getMembership());
        balanceAmount.setProgress(0.00001*this.currentCustomer.getBankAccount().getBalance());
        
    }

    @FXML
    private void depositMoney(ActionEvent event) {
        
        customerErrorLabel.setText("");
        try{
            double d = Double.parseDouble(depositAmount.getText());     
            currentCustomer.deposit(d);
        }
        catch(NumberFormatException e){
            customerErrorLabel.setText("Enter a valid deposit amount");
        }
        
        this.setCustomerData(currentCustomer);
    }

    @FXML
    private void withdrawMoney(ActionEvent event) {
        customerErrorLabel.setText("");
        try{
            double w = Double.parseDouble(withdrawAmount.getText());     
            currentCustomer.withdraw(w);
        }
        catch(NumberFormatException e){
            customerErrorLabel.setText("Enter a valid withdrawal amount");
        }
        
        this.setCustomerData(currentCustomer);
    }

    @FXML
    private void purchaseOnline(ActionEvent event) {
        
        customerErrorLabel.setText("");
        try{
            double p = Double.parseDouble(purchasePrice.getText());     
            currentCustomer.doOnlinePurchase(p);
        }
        catch(NumberFormatException e){
            customerErrorLabel.setText("Enter a valid purchase price");
        }
        
        this.setCustomerData(currentCustomer);
    }

    @FXML
    private void logoutCustomer(ActionEvent event) throws IOException{
        currentCustomer.logout();
        
        FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("BankGUI.fxml"));
            Parent mainGUI = loader.load();
            
            Scene mainGUI_scene = new Scene(mainGUI);
            
            Stage home_stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            home_stage.setScene(mainGUI_scene);
            home_stage.show();
    }

    @FXML
    private void repOKMethod(ActionEvent event) {
        abstractionLabel.setText(""+currentCustomer.repOK(currentCustomer));
    }

    @FXML
    private void abFunctionMethod(ActionEvent event) {
       abstractionLabel.setText(currentCustomer.toString());
    }

   
 }
    

