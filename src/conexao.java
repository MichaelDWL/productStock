

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {

    public static Connection conectar() {
        Connection conn = null;

        try {
            
            String url = "jdbc:mysql://localhost:3306/estoque_db";
            String usuario = "root";
            String senha = "";

            conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conectado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }

        return conn;
    }
}