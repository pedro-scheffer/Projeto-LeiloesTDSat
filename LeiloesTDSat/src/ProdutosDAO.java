/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        conn = new conectaDAO().connectDB();
        
        try {
            String sql = ("INSERT INTO produtos (nome,valor,status) VALUES (?,?,?)");
            PreparedStatement st = this.conn.prepareStatement(sql);
            st.setString(1, produto.getNome());
            st.setInt(2, produto.getValor());
            st.setString(3, produto.getStatus());
            st.execute();
            
            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar o cadastro do produto" + erro + "Erro");
        }
    }
    
    public List<ProdutosDTO> listarProdutos(){
        conn = new conectaDAO().connectDB();
        String sql = "SELECT*FROM produtos";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<ProdutosDTO> listaProdutos = new ArrayList<>();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                listaProdutos.add(produto);
            }
            return listaProdutos;

        } catch (Exception ex) {
            System.out.println("ERRO: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", 1);
            return null;
        }
    }
    
    //Altera o status do produto para vendido
    public void venderProduto (int id) {
        conn = new conectaDAO().connectDB();
        
        try {
            String sql = "UPDATE produtos SET status=? where ID=?";
            
            PreparedStatement st = this.conn.prepareStatement(sql);
            st.setString(1, "Vendido");
            st.setInt(2,id);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso.", "Sucesso", 1);
        } catch (Exception erro) {
            System.out.println("ERRO: " + erro.getMessage());
            JOptionPane.showMessageDialog(null, erro.getMessage(), "ERRO", 1);
        }
    }
    
    //Lista produtos vendidos
    public List<ProdutosDTO> listarProdutosVendidos()   {
        conn = new conectaDAO().connectDB();
        

        try {
            String sql = "SELECT * FROM produtos where status='Vendido'";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<ProdutosDTO> listaProdutos = new ArrayList<>();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();

                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                listaProdutos.add(produto);
            }
            return listaProdutos;

        } catch (Exception ex) {
            System.out.println("ERRO: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO", 1);
            return null;
        }
    }
        
}

