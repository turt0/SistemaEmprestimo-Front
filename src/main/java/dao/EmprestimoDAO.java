package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import modelo.Amigo;
import modelo.Emprestimo;
import modelo.Ferramenta;

/**
 * Classe DAO responsável pelas operações do empréstimo no banco.
 */
public class EmprestimoDAO extends BaseDAO {

    /*
     * Método para obter a lista de todos os empréstimos.
     */
    public List<Emprestimo> getMinhaLista() {
        List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
        String sql = "SELECT e.id, e.dt_emprestimo, e.dt_devolucao_prevista, e.dt_devolucao_real, "
                + "a.id id_amigo, a.nome nome_amigo, a.telefone telefone_amigo, "
                + "f.id id_ferramenta, f.nome nome_ferramenta, f.marca, f.custo "
                + "FROM tb_emprestimos e "
                + "INNER JOIN tb_amigo a ON a.id = e.id_amigo "
                + "INNER JOIN tb_ferramentas f ON f.id = e.id_ferramenta "
                + "ORDER BY e.id";

        try {
            PreparedStatement pstmt = this.getConnection().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // Monta o objeto amigo a partir do resultado da consulta.
                Amigo amigo = new Amigo(
                        rs.getInt("id_amigo"),
                        rs.getString("nome_amigo"),
                        rs.getString("telefone_amigo")
                );

                // Monta o objeto ferramenta a partir do resultado da consulta.
                Ferramenta ferramenta = new Ferramenta(
                        rs.getInt("id_ferramenta"),
                        rs.getString("nome_ferramenta"),
                        rs.getString("marca"),
                        rs.getDouble("custo")
                );

                // Monta o objeto empréstimo e adiciona na lista.
                Emprestimo emprestimo = new Emprestimo(
                        rs.getInt("id"),
                        rs.getDate("dt_emprestimo"),
                        rs.getDate("dt_devolucao_prevista"),
                        rs.getDate("dt_devolucao_real"),
                        amigo,
                        ferramenta
                );
                emprestimos.add(emprestimo);
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return emprestimos;
    }

    /*
     * Método para inserir um novo empréstimo no banco de dados.
     */
    public boolean insertEmprestimoBD(Emprestimo emprestimo) {
        String sql = "INSERT INTO tb_emprestimos (id_amigo, id_ferramenta, dt_emprestimo, dt_devolucao_prevista, dt_devolucao_real) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = this.getConnection().prepareStatement(sql);

            pstmt.setInt(1, emprestimo.getAmigo().getId());
            pstmt.setInt(2, emprestimo.getFerramenta().getId());
            pstmt.setDate(3, new java.sql.Date(emprestimo.getDtEmprestimo().getTime()));
            pstmt.setDate(4, new java.sql.Date(emprestimo.getDtDevolucaoPrevista().getTime()));

            // Deixa a devolução real nula no momento do cadastro.
            if (emprestimo.getDtDevolucaoReal() == null) {
                pstmt.setNull(5, java.sql.Types.DATE);
            } else {
                pstmt.setDate(5, new java.sql.Date(emprestimo.getDtDevolucaoReal().getTime()));
            }

            int affectedRows = pstmt.executeUpdate();
            pstmt.close();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /*
     * Método para deletar um empréstimo do banco de dados pelo ID.
     */
    public boolean deleteEmprestimoBD(int id) {
        String sql = "DELETE FROM tb_emprestimos WHERE id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException erro) {
            erro.printStackTrace();
            throw new RuntimeException(erro);
        }
    }

    /*
     * Método para registrar a devolução de um empréstimo.
     */
    public boolean registrarDevolucaoBD(int id) {
        String sql = "UPDATE tb_emprestimos SET dt_devolucao_real = CURRENT_DATE WHERE id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException erro) {
            erro.printStackTrace();
            throw new RuntimeException(erro);
        }
    }

    /*
     * Método para obter o maior ID de empréstimo no banco de dados.
     */
    public int maiorID() {
        String sql = "SELECT COALESCE(MAX(id), 0) id FROM tb_emprestimos";
        int maiorID = 0;
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                maiorID = rs.getInt("id");
            }
            stmt.close();
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
        }
        return maiorID;
    }

    /*
     * Método para atualizar um empréstimo existente.
     */
    public boolean updateEmprestimoBD(Emprestimo emprestimo) {
        String sql = "UPDATE tb_emprestimos SET dt_emprestimo = ?, dt_devolucao_prevista = ?, dt_devolucao_real = ?, id_amigo = ?, id_ferramenta = ? WHERE id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(emprestimo.getDtEmprestimo().getTime()));
            stmt.setDate(2, new java.sql.Date(emprestimo.getDtDevolucaoPrevista().getTime()));

            // Atualiza a devolução real conforme o valor do objeto.
            if (emprestimo.getDtDevolucaoReal() == null) {
                stmt.setNull(3, java.sql.Types.DATE);
            } else {
                stmt.setDate(3, new java.sql.Date(emprestimo.getDtDevolucaoReal().getTime()));
            }

            stmt.setInt(4, emprestimo.getAmigo().getId());
            stmt.setInt(5, emprestimo.getFerramenta().getId());
            stmt.setInt(6, emprestimo.getId());

            stmt.executeUpdate();
            stmt.close();
            return true;
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
    }

    /*
     * Método para gerar o resumo do relatório de empréstimos.
     */
    public String gerarResumoRelatorio() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Emprestimo> lista = this.getMinhaLista();

        sb.append("=== RELATÓRIO DE EMPRÉSTIMOS ===\n\n");
        sb.append("Todos os empréstimos:\n");

        if (lista.isEmpty()) {
            sb.append("Nenhum empréstimo cadastrado.\n\n");
        } else {
            for (Emprestimo e : lista) {
                sb.append("ID ").append(e.getId())
                        .append(" | Amigo: ").append(e.getAmigo().getNome())
                        .append(" | Ferramenta: ").append(e.getFerramenta().getNome())
                        .append(" | Empréstimo: ").append(sdf.format(e.getDtEmprestimo()))
                        .append(" | Prev. devolução: ").append(sdf.format(e.getDtDevolucaoPrevista()))
                        .append(" | Status: ").append(e.isAtivo() ? "ATIVO" : "DEVOLVIDO")
                        .append("\n");
            }
            sb.append("\n");
        }

        // Consulta o amigo com maior quantidade de empréstimos.
        String sqlMaior = "SELECT a.nome, COUNT(*) qtd FROM tb_emprestimos e "
                + "INNER JOIN tb_amigo a ON a.id = e.id_amigo "
                + "GROUP BY a.id, a.nome ORDER BY qtd DESC, a.nome LIMIT 1";

        // Consulta os amigos com empréstimos ainda não devolvidos.
        String sqlPendentes = "SELECT DISTINCT a.nome FROM tb_emprestimos e "
                + "INNER JOIN tb_amigo a ON a.id = e.id_amigo "
                + "WHERE e.dt_devolucao_real IS NULL ORDER BY a.nome";

        try {
            PreparedStatement stmtMaior = getConnection().prepareStatement(sqlMaior);
            ResultSet rsMaior = stmtMaior.executeQuery();
            PreparedStatement stmtPend = getConnection().prepareStatement(sqlPendentes);
            ResultSet rsPend = stmtPend.executeQuery();

            sb.append("Quem fez mais empréstimos:\n");
            if (rsMaior.next()) {
                sb.append(rsMaior.getString("nome"))
                        .append(" (")
                        .append(rsMaior.getInt("qtd"))
                        .append(" empréstimos)\n\n");
            } else {
                sb.append("Sem dados.\n\n");
            }

            sb.append("Quem ainda não devolveu:\n");
            boolean temPendencia = false;
            while (rsPend.next()) {
                temPendencia = true;
                sb.append("- ").append(rsPend.getString("nome")).append("\n");
            }
            if (!temPendencia) {
                sb.append("Ninguém.\n");
            }

            stmtMaior.close();
            stmtPend.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return sb.toString();
    }
}
