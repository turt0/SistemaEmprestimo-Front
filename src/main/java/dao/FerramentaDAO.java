package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Ferramenta;

/**
 * Classe DAO responsável pelas operações da ferramenta no banco.
 */
public class FerramentaDAO extends BaseDAO {

    public ArrayList<Ferramenta> minhaLista = new ArrayList<Ferramenta>();

    /**
     * Retorna a lista de ferramentas cadastradas.
     */
    public ArrayList<Ferramenta> getMinhaLista() {
        // Limpa a lista para adicionar novas ferramentas.
        minhaLista.clear();
        try {
            Statement stmt = this.getConnection().createStatement();

            // Faz uma consulta para selecionar todos os registros da tabela tb_ferramentas.
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_ferramentas ORDER BY id");
            while (res.next()) {

                int id = res.getInt("id");
                String nome = res.getString("nome");
                String marca = res.getString("marca");
                double custo = res.getDouble("custo");

                Ferramenta objeto = new Ferramenta(id, nome, marca, custo);
                minhaLista.add(objeto);
            }

            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return minhaLista;
    }

    /**
     * Cadastra uma ferramenta.
     */
    public boolean insertFerramentaBD(Ferramenta objeto) {
        String sql = "INSERT INTO tb_ferramentas(id,nome,marca,custo) VALUES(?,?,?,?)";
        try {
            // Define os valores dos parâmetros para o banco de dados.
            PreparedStatement stmt = this.getConnection().prepareStatement(sql);
            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setString(3, objeto.getMarca());
            stmt.setDouble(4, objeto.getCusto());

            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLException erro) {
            // Apresenta erro caso ocorra.
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Carrega uma ferramenta pelo ID.
     */
    public Ferramenta carregaFerramenta(int id) {

        Ferramenta objeto = null;
        try {
            PreparedStatement stmt = this.getConnection().prepareStatement("SELECT * FROM tb_ferramentas WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                objeto = new Ferramenta(
                        res.getInt("id"),
                        res.getString("nome"),
                        res.getString("marca"),
                        res.getDouble("custo")
                );
            }

            stmt.close();
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        return objeto;
    }

    /**
     * Altera uma ferramenta.
     */
    public boolean updateFerramentaBD(Ferramenta objeto) {
        String sql = "UPDATE tb_ferramentas set nome = ? , marca = ? , custo = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.getConnection().prepareStatement(sql);
            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getMarca());
            stmt.setDouble(3, objeto.getCusto());
            stmt.setInt(4, objeto.getId());

            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Deleta uma ferramenta.
     */
    public boolean deleteFerramentaBD(int id) {
        try {
            PreparedStatement stmt = this.getConnection().prepareStatement("DELETE FROM tb_ferramentas WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException erro) {
            System.out.println("erro: " + erro);
        }
        return true;
    }

    /**
     * Retorna o maior ID cadastrado na tabela.
     */
    public int maiorID() {
        int maiorID = 0;
        try {
            Statement stmt = this.getConnection().createStatement();
            ResultSet res = stmt.executeQuery("SELECT COALESCE(MAX(id), 0) id FROM tb_ferramentas");
            res.next();
            maiorID = res.getInt("id");
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("erro: " + ex);
        }

        return maiorID;
    }

    /**
     * Retorna o custo total das ferramentas cadastradas.
     */
    public double getTotalGasto() {
        double total = 0;
        try {
            Statement stmt = this.getConnection().createStatement();
            ResultSet res = stmt.executeQuery("SELECT COALESCE(SUM(custo), 0) total FROM tb_ferramentas");
            res.next();
            total = res.getDouble("total");
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("erro: " + ex);
        }

        return total;
    }
}
