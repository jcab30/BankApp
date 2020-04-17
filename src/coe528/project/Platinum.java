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
public class Platinum extends Membership{
    
    public double getPurchaseFee(){
        return 0.0;
    }
    
    public void changeMembership(Customer context){
        
        double currentBalance = context.getBankAccount().getBalance();
        
        if(currentBalance < 10000.0)
            context.setMembership(new Silver());
        else if(10000 <= currentBalance && currentBalance < 20000)
            context.setMembership(new Gold());
        else
            context.setMembership(this);
    }
    
    public String toString(){
        return "Platinum";
    }
}
