/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Conection.Conexao_MySQL;
import Model.Bean.Produto;
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
public class ProdutoDao {
    
    public void create(Produto p){
    
        Connection  conexao = Conexao_MySQL.getConection();
        
        PreparedStatement comando = null;
        
        try {
            
            comando = conexao.prepareStatement("INSERT INTO produto (descricao, qtd, preco) values (?,?,?)");
            
            //passando os parametros
            comando.setString(1, p.getDescricao());
            comando.setInt(2, p.getQtd());
            comando.setDouble(3, p.getPreco());
            
            comando.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo no banco com Sucesso");
                    
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null," Falha ao salvar no banco :"+ ex);
        }finally{
            Conexao_MySQL.closeConectio(conexao, comando);
        }
    
    }
    
    public List<Produto> read (){
        
        Connection conexao = Conexao_MySQL.getConection();
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        List <Produto> produtos = new ArrayList<>();
        
        try {
            comando = conexao.prepareStatement("SELECT * FROM produto");
            resultado = comando.executeQuery();
            
            while (resultado.next()){
            Produto produto = new Produto();
            
            produto.setId(resultado.getInt("id"));
            produto.setDescricao(resultado.getString("descricao"));
            produto.setQtd(resultado.getInt("qtd"));
            produto.setPreco(resultado.getDouble("preco"));
            
            produtos.add(produto);
            
            }
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Erro ao cosultar o Banco: "+ex);
        }finally{
            Conexao_MySQL.closeConection(conexao, comando, resultado);
        }
     
        return produtos;
    }
    
    public void update (Produto p){
        
        Connection conexao = Conexao_MySQL.getConection();
        PreparedStatement comando = null;
        
        try {
            
            comando = conexao.prepareStatement("UPDATE produto SET descricao = ?, qtd = ?, preco = ? WHERE id = ?");
            
            
            comando.setString(1, p.getDescricao());
            comando.setInt(2, p.getQtd());
            comando.setDouble(3, p.getPreco());
            comando.setInt(4, p.getId());
            
            comando.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado no banco com Sucesso");
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null," Falha ao Atualizar :"+ ex);
        }
    
    }
    
    public void delete(Produto p){
        
        Connection conexao = Conexao_MySQL.getConection();
        PreparedStatement comando = null;
        
        try {
            comando = conexao.prepareStatement("DELETE FROM produto WHERE id = ?");
            
            comando.setInt(1, p.getId());
            
            comando.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido do banco com Sucesso");
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null," Falha ao Excluir : "+ ex); 
        }
        
    }

    public List<Produto> search(String desc) {

        Connection conexao = Conexao_MySQL.getConection();
        PreparedStatement comando = null;
        ResultSet resultado = null;
        
        
        List <Produto> produtos = new ArrayList<>();
        
        try {
            
            comando = conexao.prepareStatement("SELECT * FROM produto WHERE descricao LIKE ?");
       
            comando.setString(1, "%"+desc+"%");
            
            resultado = comando.executeQuery();
            
            while(resultado.next()){
                Produto produto = new Produto();
                
                produto.setId(resultado.getInt("id"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setQtd(resultado.getInt("qtd"));
                produto.setPreco(resultado.getDouble("preco"));
                
                produtos.add(produto);
            }
                
            JOptionPane.showMessageDialog(null, "Pesquisa realizada com sucesso *,*");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar no banco: "+ex);
        }finally{
            Conexao_MySQL.closeConection(conexao, comando, resultado);
        }
        
        return produtos;
    }
}
