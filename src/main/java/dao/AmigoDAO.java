package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Amigo;

public class AmigoDAO extends BaseDAO {

    public ArrayList<Amigo> minhaLista = new ArrayList<>();

    public ArrayList<Amigo> getMinhaLista() {
        // Limpa a lista para adicionar novos amigos
        minhaLista.clear();

        try {
            // Cria uma instrução para executar consultas SQL
            Statement stmt = getConnection().createStatement();

            // Executa uma consulta SQL para selecionar todos os registros da tabela tb_amigo
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_amigo");
            while (res.next()) {
                // Obtém os dados do amigo a partir do resultado da consulta
                int id = res.getInt("id");
                String nome = res.getString("nome");
                String telefone = res.getString("telefone");

                // Cria um objeto Amigo com os dados obtidos e adiciona à lista
                Amigo objeto = new Amigo(id, nome, telefone);
                minhaLista.add(objeto);
            }

            // Fecha a instrução após o uso
            stmt.close();
        } catch (SQLException ex) {
            // Em caso de exceção, imprime o erro
            ex.printStackTrace();
        }
        // Retorna a lista de amigos
        return minhaLista;
    }

    /*
    * Insere um amigo ao banco de dados
     */
    public boolean insertAmigoBD(Amigo objeto) {
        // Define a instrução SQL para inserir um novo amigo na tabela
        String sql = "INSERT INTO tb_amigo(id,nome,telefone) VALUES(?,?,?)";
        try {
            // Prepara a instrução SQL para a execução
            PreparedStatement stmt = getConnection().prepareStatement(sql);

            // Define os valores dos parâmetros na instrução SQL
            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setString(3, objeto.getTelefone());

            // Executa a instrução SQL
            stmt.execute();

            // Fecha a instrução após o uso
            stmt.close();

            // Retorna true para indicar sucesso na inserção
            return true;
        } catch (SQLException erro) {
            // Em caso de exceção, imprime o erro e lança uma exceção
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }

    /*
    * Deleta um amigo do banco de dados
     */
    public boolean deleteAmigoBD(int id) {
        try {
            // Cria uma instrução para executar uma operação SQL de exclusão
            Statement stmt = getConnection().createStatement();

            // Executa uma instrução SQL para excluir o amigo com o ID fornecido
            stmt.executeUpdate("DELETE FROM tb_amigo WHERE id =" + id);

            // Fecha a instrução após o uso
            stmt.close();
        } catch (SQLException erro) {
            // Em caso de exceção, imprime o erro
            System.out.println("erro: " + erro);
        }
        // Retorna true para indicar sucesso na exclusão
        return true;
    }

    /*
    * Atualiza toda vez que as informações do "amigo"  
    * é alterado no banco de dados
     */
    public boolean updateAmigoBD(Amigo objeto) {
        // Define a instrução SQL para atualizar as informações de um amigo na tabela
        String sql = "UPDATE tb_amigo set nome = ? , telefone = ? WHERE id = ?";
        try {
            // Prepara a instrução SQL para a execução
            PreparedStatement stmt = getConnection().prepareStatement(sql);

            // Define os valores dos parâmetros na instrução SQL
            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getTelefone());
            stmt.setInt(3, objeto.getId());

            // Executa a instrução SQL
            stmt.execute();

            // Fecha a instrução após o uso
            stmt.close();

            // Retorna true para indicar sucesso na atualização
            return true;
        } catch (SQLException erro) {
            // Em caso de exceção, imprime o erro e lança uma exceção
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }
    
    public Amigo carregaAmigo(int id) {
        Amigo amigo = null;
        try {
            PreparedStatement stmt = getConnection().prepareStatement("SELECT * FROM tb_amigo WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                String nome = res.getString("nome");
                String telefone = res.getString("telefone");

                amigo = new Amigo(id, nome, telefone);
            }

            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return amigo;
    }

    public int maiorID() {
        // Variável para armazenar o maior ID encontrado na tabela
        int maiorID = 0;
        try {
            // Cria uma instrução para executar uma consulta SQL
            Statement stmt = getConnection().createStatement();

            // Executa uma consulta SQL para obter o maior ID presente na tabela
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_amigo");
            res.next();
            maiorID = res.getInt("id");

            // Fecha a instrução após o uso
            stmt.close();
        } catch (SQLException ex) {
            // Em caso de exceção, imprime o erro
            System.out.println("erro: " + ex);
        }

        // Retorna o maior ID encontrado
        return maiorID;
    }
}
