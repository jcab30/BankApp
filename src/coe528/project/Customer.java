/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.util.ArrayList;

/**
 *
 * @author John Cabuguason
 */
public class Customer extends User{
    
    /**
     * Overview. : Customers are mutable objects comprised of a username,password, and
     *             bank account object. 
     */
    
    /**
     * Abstraction Function. AF(c)= an abstract Customer object D where .../
     * D username is c.userName
     * and D password is c.password
     * and D account balance is c.account.getBalance() 
     * and D account number is c.account.getAccountNum()
     * and D membership is c.level
     * and D role equala "customer"
     */
    
    /**
     * Rep Invariant. is:
     * 
     * RI(c) = true 
     * - if c.account.getBalance() >= 0
     * - c.userName does not equal b.userName for any two Customer object c and b in the bank 
     * - if c == new Customer then c.account.getBalance() == 100
     */
    
    private BankAccount account;
    private Membership level = new Silver();
    
    /**
     * Constructor for new Customer object
     * @param userName String 
     * @param password String 
     */
    public Customer(String userName,String password){
        super(userName,password,"customer");
        this.account = new BankAccount(0,100.0);
        this.updateMembership();
    }
    
    /**
     * Constructor for existing Customer object
     * @param userName String  
     * @param password String 
     * @param account BankAccount
     */
    public Customer(String userName, String password, BankAccount account){
        super(userName,password,"customer"); 
        this.account = account;
        this.updateMembership();
    }   
    
    /**<ul>
     * <li>Effects: this logs into the Bank application, returns true if this logs in
     * successfully, else returns false
     * </ul>
     */
    public boolean login(){
        Bank bank = Bank.getInstance();
        return bank.signInCustomer(super.getUsername(), super.getPassword());
    }
    
    /**<ul>
     * <li>Effects: this logs out of the Bank application and updates files stored
     * in Bank
     * </ul>
     */
    public void logout(){ 
        Bank bank = Bank.getInstance();
        bank.updateCustomerDatabase();
    }
    
    /**
     * <ul>
     * <li>Modifies: this.account.balance
     * <li>Effects: this.account.balance decreases by amount given by variable cash.Returns
     * <li>the amount specified by variable cash as double
     * </ul>
     */
    public double withdraw(double cash){
        double cashWithdrawed = this.account.withdraw(cash);
        this.updateMembership();    

        return cashWithdrawed;
    }
    
    /**<ul>
     * <li>Modifies: this.account.balance 
     * <li>Effects: this.account.balance increases by amount given by variable cash
     * </ul>
     */
    public void deposit(double cash){
        this.account.deposit(cash);
        this.updateMembership();
    }    
       
    /**<ul>
     * <li>Modifies: this.account.balance
     * <li>Effects: this.account.balance decreases by amount given by variable cash and
     * returns total purchase fee as double
     * </ul>
     */
    public double doOnlinePurchase(double cash){   
        if(cash >= 50.0)
            return this.withdraw(cash + level.getPurchaseFee() );
        else{
            System.out.println("Must be 50 dollars or more.");
            return 0;
        }
        
    }
    
    /**<ul>
     * <li>Effects: returns bankaccount number of this
     * </ul>
     */
    public BankAccount getBankAccount(){
        return this.account;
    }
  
    /**<ul>
     * <li>Effects: changes Membership level to Silver,Gold,or Platinum depending on
     * the value of this.account.balance 
     * </ul>
     */
    private void updateMembership(){     
        
        level.changeMembership(this);
    }
    
    /**<ul>
     * <li>Requires: Membership m is not null
     * <li>Modifies: this.level
     * <li>Effects: this.level equals variable m  
     * </ul>
     */
    public void setMembership(Membership m){
        this.level = m;
    }
    
    /**<ul>
     * <li>Effects: returns clone of this.level 
     * </ul>
     */
    public Membership getMembership(){
        return this.level.clone();
    }
    
    /**<ul>
     * <li>Requires: Customer c is not null
     * <li>Effects: returns true if rep invariant holds, else returns false
     * </ul>
     */
    public boolean repOK(Customer c){
        
        Bank instance = Bank.getInstance();
        ArrayList<Customer> tempList = instance.getCustomersList();
        int count = 0;
        
        //searches and finds the amount of customers with same username
        for(Customer temp: tempList){
            if(c.getUsername().equalsIgnoreCase(temp.getUsername()))
                count++;
        }
        
        if(count > 1 || c.account.getBalance() < 0)
            return false;
        else
            return true;
        
    }
    
    /**<ul>
     * <li>Effects: returns the toString() representation of the abstraction function 
     * </ul>
     */
    public String toString(){
        
        //NOTE: Customer toString output is formatted as...
        //account#;balance;username;password;role
        String line =  account+";"+super.toString();
        
        return line;
    }
    
    
}
