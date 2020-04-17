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
public abstract class  Membership implements Cloneable{
    
    public abstract double getPurchaseFee();
    
    public abstract void changeMembership(Customer context);
    
    public Membership clone(){
        try{
            return (Membership)super.clone();
        }
        catch(CloneNotSupportedException e){
            e.getMessage();
        }
        
        return new Silver();
    }
}
