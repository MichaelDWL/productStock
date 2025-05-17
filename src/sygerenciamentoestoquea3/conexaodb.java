/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygerenciamentoestoquea3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author maiqu
 */
public class conexaodb {
    public static Connection conectar() {
        
        Connection conn = null;

        try {
            
            String url = "jdbc:mysql://localhost:3306/estoque";
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
