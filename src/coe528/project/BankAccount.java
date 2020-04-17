/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;


public class BankAccount{
    
    private double balance;
    private int accountNum;
    
    public BankAccount(){
        this.balance = 100.0;
    }
    
    public BankAccount(int a ,double cash){
        this.balance = cash;
        this.accountNum = a;
    }
    
    public void deposit(double cash){
        this.balance += cash;
    }
    
    public double withdraw(double cash){
        
        if(this.balance < cash){
            System.out.println("Insufficient Balance");
            return 0.0;
        }
        else{
            this.balance -= cash;
            return cash;
        }
    }
    
    public double getBalance(){
        return this.balance;
    }

    public int getAccountNum(){
        return this.accountNum;
    }
    
    public String toString(){
        String line;
        
        line = this.accountNum+";"+this.balance;
      
        return line;
    }
    
    
    
    
}
