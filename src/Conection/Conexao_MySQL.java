/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel
 */
public class Conexao_MySQL {
    
    
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3308/mercadinho";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConection(){//metodo pra conectar
    
        try {
            
            Class.forName(DRIVER);
            
            
            return DriverManager.getConnection(URL,USER,PASS);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao_MySQL.class.getName()).log(Level.SEVERE, null, ex);
         
            throw  new RuntimeException("Erro ao Conectar :", ex);
        }
    }
    
    public static void closeConection(Connection conexao){
        
        
            try {
                
                if (conexao != null){// se houver conexao feche-a.
                conexao.close();
            }
            
            } catch (SQLException ex) {
                Logger.getLogger(Conexao_MySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    public static void closeConectio (Connection conexao,PreparedStatement stat ){
        
        closeConection(conexao);
        
        try {
        if (stat != null){
                stat.close();
            }
        
        } catch (SQLException ex) {
                Logger.getLogger(Conexao_MySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    public static void closeConection(Connection conexao, PreparedStatement stat, ResultSet rs){
        closeConectio(conexao, stat);
        
           
            try {
                 if (rs != null){
                    rs.close();
                 }
                 
            } catch (SQLException ex) {
                Logger.getLogger(Conexao_MySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
         
    }

    
        
        
    
    
    

