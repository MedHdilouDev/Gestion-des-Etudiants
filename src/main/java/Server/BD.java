package Server;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.net.*;
import java.sql.*;

public class BD {
    public static BD obj ;
    private static Connection con ;
    
    public  BD(){
    try{
        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dbEtudiant", "root", "hdilou2005@");
        
    }catch(Exception e){
        e.printStackTrace();
    }
    }
    
    
    public static Connection getInstance(){
        if(obj == null){
            obj = new BD();
        }
        return con;
    }

    
    
    
    
}
