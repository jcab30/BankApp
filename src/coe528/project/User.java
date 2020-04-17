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
public abstract class User {
    
    private String userName;
    private String password;
    private String role;
    
    public User(String userName, String password,String role){
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
//    
//    //check credentials of the user 
//    // if no user give error 
//    public abstract void login();
//    
//    // logout if user wants to exit program
//    public abstract void logout();
    
    public String getUsername(){
        return this.userName;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public String getRole(){
        return this.role;
    }
    
    public String toString(){
        String line =this.userName+";"+this.password+";" +this.role;
        return line;
    }
    
   
}
