package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe para obter uma conexão com o banco de dados.
 */
public class BaseDAO {

    // Método para obter uma conexão com o banco de dados.
    public Connection getConnection() {
        Connection connection = null;

        try {
            // Carrega o driver JDBC.
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            // Configuração da conexão.
            String server = "localhost";
            String database = "db_ferramentas";
            String url = "jdbc:mysql://" + server + ":3306/" + database
                    + "?useTimezone=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
            String user = "root";
            String password = "1234";

            // Conecta ao banco de dados.
            connection = DriverManager.getConnection(url, user, password);

            // Testa se a conexão foi bem-sucedida.
            if (connection != null) {
                System.out.println("Conectado com sucesso.");
            } else {
                System.out.println("Falha ao conectar.");
            }

            return connection;

        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado: " + e.getMessage());
            return null;

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }
}
