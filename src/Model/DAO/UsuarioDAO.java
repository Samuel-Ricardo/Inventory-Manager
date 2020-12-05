/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Conection.Conexao_MySQL;
import Model.Bean.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel
 */
public class UsuarioDAO {
    
    public boolean checkLogin(String login, String senha){
    
        Connection conexao = Conexao_MySQL.getConection();
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Usuario usuario = new Usuario();
        boolean check = false;
        
        try {
            
            comando = conexao.prepareStatement("SELECT * FROM usuario WHERE login = ? and Senha = ?");
            comando.setString(1, login);
            comando.setString(2, senha);
            resultado = comando.executeQuery();
            
            if (resultado.next()){

                check = true;
           
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao entrar em contato com o Banco: "+ex);
        }finally{
            Conexao_MySQL.closeConection(conexao, comando, resultado);
        }
        
        return check;
    }
    
}
