/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author John Cabuguason
 */
public class Silver extends Membership {
    
    public double getPurchaseFee(){
        return 20.0;
    }
    
    public void changeMembership(Customer context){
        
        double currentBalance = context.getBankAccount().getBalance();
        
        if(10000 <= currentBalance && currentBalance < 20000)
            context.setMembership(new Gold());
        else if(20000 <= currentBalance)
            context.setMembership(new Platinum());
        else
            context.setMembership(this);
        
    }
    public String toString(){
        return "Silver";
    }
    
}
