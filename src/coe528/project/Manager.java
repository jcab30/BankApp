/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;
import java.io.*;
import java.util.*;


/**
 *
 * @author John Cabuguason
 */
public class Manager extends User{
    
    private File file;
    
    // constructor
    public Manager(String userName, String password){
        super(userName,password,"manager");
    }
    
    //Effects: logs the manager in to the system
    public boolean login(){  
        Bank bank = Bank.getInstance();
        return bank.signInManager(super.getUsername(), super.getPassword());
    }
    
    //Effects: logs manager out of the system
    public void logout(){
        Bank bank = Bank.getInstance();
        bank.updateCustomerDatabase();
        
        //implement code to end session
    }
    
    //Effects: adds customer to the bank database 
    public boolean addCustomer(Customer c) {
        Bank bank = Bank.getInstance();
        boolean existsAlready = false;
        
        for(int i = 0; i < Bank.customers.size(); i++ )
            if(Bank.customers.get(i).getUsername().equals(c.getUsername()))
                existsAlready = true;
        
        if(existsAlready)
            return false;
        else{
             bank.customers.add(c);
             return true;
        }
            
   }
    
    //Effects: deletes customer from the bank database
    public void deleteCustomer(Customer c){
        Bank bank = Bank.getInstance();
        int i = 0;
        
        for(i = 0; i < bank.customers.size();i++){
            if(c.getBankAccount().getAccountNum() == bank.customers.get(i).getBankAccount().getAccountNum()
            &&c.getUsername().equalsIgnoreCase(bank.customers.get(i).getUsername())
            &&c.getPassword().equalsIgnoreCase(bank.customers.get(i).getPassword())){
                
                bank.customers.remove(i);
                
                File customerfile = new File("C:\\Users\\John Cabuguason"
                    + "\\Documents\\NetBeansProjects\\BankApp\\src"
                    + "\\coe528\\project\\"+c.getUsername()+".txt");
                
                if(customerfile.exists())
                    customerfile.delete();
   
                break;
            }
        }
        
        
        if(i > bank.customers.size())
            System.out.println("ERROR: Customer not found");   
        else{
            for(Customer cus : bank.customers)      
                System.out.println(cus);
            bank.updateCustomerDatabase(); 
        } 
    } 
      
    @Override
    public String toString(){
        return super.toString();
    }
    
   
    

   
    
    
}
