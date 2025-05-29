/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygerenciamentoestoquea3;
import java.sql.*;

/**
 *
 * @author Michael_DWL
 */
public class conexaodb {
    
    public static Connection conectar() {
        
        Connection conn = null;

        try {
            
            String url = "jdbc:mysql://localhost:3306/";
            String usuario = "root";
            String senha = "";

            // conecta ao banco de dados
            conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conectado com sucesso!");
            
            // 2. Cria o banco caso não exista
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS estoque");
            System.out.println("Banco verificado/criado com sucesso!");

            // 3. Conecta ao banco estoque
            conn = DriverManager.getConnection(url + "estoque", usuario, senha);

            // 4. Cria a tabela caso não exista
            
            // Criação da tabela produtos
            String sqlProdutos = "CREATE TABLE IF NOT EXISTS produtos (" +
                    "ID INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "nome VARCHAR(30) NOT NULL UNIQUE, " +
                    "unidade VARCHAR(10) NOT NULL, " +
                    "lote VARCHAR(10), " +
                    "Dtvalidade VARCHAR(10), " +
                    "qtde INT(11), " +
                    "Vr DECIMAL(10,2), " +
                    "controlados ENUM('sim', 'nao')" +
                    ");";
            stmt.executeUpdate(sqlProdutos);

            // Criação da tabela usuarios
            String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios (" +
                    "ID_user INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "nome VARCHAR(30) NOT NULL, " +
                    "login VARCHAR(15) NOT NULL UNIQUE, " +
                    "senha VARCHAR(10) NOT NULL, " +
                    "cargo_user VARCHAR(15) NOT NULL" +
                    ");";
            stmt.executeUpdate(sqlUsuarios);

            // Criação da tabela nota_fiscal
            String sqlNotaFiscal = "CREATE TABLE IF NOT EXISTS nota_fiscal (" +
                    "ID_NF INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "num_nfe INT(11) NOT NULL, " +
                    "data_emissao DATE NOT NULL, " +
                    "VrTotal DECIMAL(10,2)" +
                    ");";
            stmt.executeUpdate(sqlNotaFiscal);

            // Criação da tabela nota_prod
            String sqlNotaProd = "CREATE TABLE IF NOT EXISTS nota_prod (" +
                    "ID_nXp INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "nota_id INT(11), " +
                    "prod_id INT(11), " +
                    "qtde_ent INT(11), " +
                    "FOREIGN KEY (nota_id) REFERENCES nota_fiscal(ID_NF), " +
                    "FOREIGN KEY (prod_id) REFERENCES produtos(ID)" +
                    ");";
            stmt.executeUpdate(sqlNotaProd);
            
            // Verifica se o usuário ADMIN já existe em caso de primeiro login 
            String sqlCheckAdmin = "SELECT COUNT(*) FROM usuarios WHERE login = 'ADMIN'";
            ResultSet rs = stmt.executeQuery(sqlCheckAdmin);
            rs.next();
            int count = rs.getInt(1);

            if (count == 0) {
                String sqlInsertAdmin = "INSERT INTO usuarios (nome, login, senha, cargo_user) " +
                        "VALUES ('ADMIN', 'ADMIN', '0000', 'Admin')";
                stmt.executeUpdate(sqlInsertAdmin);
                System.out.println("Usuário ADMIN criado com sucesso!");
            } else {
                System.out.println("Usuário ADMIN já existe.");
            }

            System.out.println("Tabelas verificadas/criadas com sucesso!");
            
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }

        return conn;
        
        
        
        
        
        
        
    }
}
