/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.util.ArrayList;
import java.util.*;
import java.io.*;
/**
 *
 * @author John Cabuguason
 */
public class Bank {
    
    //*IMPORTANT*//
    
    // VARIABLES USED FOR STORAGE AND DYNAMIC COMPUTER USE IMPLEMENTATION
    private static File customerfile;
    private static File managerfile;
    
    // CHANGE THIS TO WHATEVER DESTINATION YOU WANT TO MAKE THE CUSTOMER STORAGE FILES IN
    private static String fileDestination;
    //arraylists for session use
    public static ArrayList<Customer> customers = new ArrayList <Customer>();
    public static ArrayList<Manager> managers = new ArrayList <Manager>();
    
    public Bank(){
        
        /** IMPORTANT NOTE.
        * Implementation of a DYNAMIC STORAGE DATABASE of CUSTOMER and MANAGEAR
        */
        this.fileDestination = "C:\\Users\\John Cabuguason\\Documents\\NetBeansProjects\\BankApp\\src\\coe528\\project";
        this.customerfile = new File(this.fileDestination+"\\customers.txt");
        this.managerfile = new File(this.fileDestination+"\\managers.txt");
        
        try{
            
            
            /** IMPORTANT NOTE.
             * Creates file for manager database --> although the project document says
             * one manager for the bank I implemented the ability to add multiple managers
             * in the bank for future proof
             */
            
            customerfile.createNewFile();
            
            if(managerfile.createNewFile()){
                FileWriter fWriter = new FileWriter(managerfile,false);
                fWriter.write("admin;admin;manager");
                fWriter.close();
            }
        }
        catch(IOException e){}
        
        Scanner scanF;
        String [] data;
        
        //load customer database
        try{  
  
            //set scanner to read customer file
            scanF = new Scanner(customerfile);

            while(scanF.hasNextLine()){

                // take next line in file as input
                String temp = scanF.nextLine();
                
                // splits data based on ';' delimiter
                data = temp.split(";"); 
                
                //places all customers in file -> to arraylist for session use
                customers.add(new Customer(data[2],data[3],new BankAccount(Integer.parseInt(data[0]),Double.parseDouble(data[1])) ) ) ;
            }
            
            //set scanner to read manager file
            scanF = new Scanner(managerfile);
            
            while(scanF.hasNextLine()){

                // take next line in file as input
                String temp = scanF.nextLine();
                
                // splits data based on ';' delimiter
                data = temp.split(";"); 
                
                //places managers in file -> to arraylist for session use
                managers.add(new Manager(data[0],data[1]));
            }
            
            scanF.close();
        }
        catch(FileNotFoundException e){
            e.getMessage();
        }
        
    }
    
    private static Bank bank = new Bank();
    
    public static Bank getInstance(){
       if(bank == null)
           bank = new Bank();
       
       return bank;
    }
 
    //Effects: validates and signs manager into the database
    public boolean signInManager(String userName,String password){
        boolean signedIn = false;
        for(Manager m: managers){
            if(m.getUsername().equalsIgnoreCase(userName) && m.getPassword().equals(password)){
                signedIn = true;
            }
        }
        
        if(signedIn)
            System.out.println("Verification Complete");
        else
            System.out.println("Verification Incomplete");
        
        return signedIn;
    }
    
    //CHANGE THIS LATER TO THE SAME AS SIGNINMANAGER METHOD
    public boolean signInCustomer(String userName,String password){
        
        boolean signedIn = false;
        for(Customer c: customers){
            if(c.getUsername().equalsIgnoreCase(userName) && c.getPassword().equals(password))
                signedIn = true;
        }
        
        if(signedIn)
            System.out.println("Verification Complete");
        else
            System.out.println("Verification Incomplete");
        
        return signedIn;
    
    }
    
    public Customer getCustomer(String userName,String password){
        
        for(Customer c: customers){
            if(c.getUsername().equalsIgnoreCase(userName) && c.getPassword().equals(password))
                return c;
        }
        
        return null;
    }
    
    //clones arraylist of customers and returns it
    public ArrayList<Customer> getCustomersList(){
        ArrayList<Customer> shallowList = new ArrayList<Customer>();
        
    
        for(int i = 0;i < this.customers.size(); i++){
            Customer temp = new Customer(this.customers.get(i).getUsername(),this.customers.get(i).getPassword());
            shallowList.add(temp);
        }
        
        return shallowList;
    }
    
    /**
     * Method Name: update
     * Description: updates the bank data file of customers
     */
    public void updateCustomerDatabase(){
        FileWriter fWriter;
        
        this.customerfile = new File(this.fileDestination+"\\customers.txt");
        
        try{
            fWriter = new FileWriter(this.customerfile,false);
            
            for(Customer c: customers){
                fWriter.write(c+"\n");
            }
            
            fWriter.close();
            
            
            for(Customer c: customers){
                
                 this.customerfile = new File(this.fileDestination+"\\"+c.getUsername()+".txt");
                 
                fWriter = new FileWriter(customerfile,false);
                
                fWriter.write(c+"\n");
                
                fWriter.close();
            }
           
           
            
        }
        catch(IOException e){
            e.getMessage();
        }  
        
    }    
      
    
}