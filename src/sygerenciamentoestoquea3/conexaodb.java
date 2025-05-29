package sygerenciamentoestoquea3;

import java.sql.*;

public class conexaodb {

    public static Connection conectar() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String usuario = "root";
        String senha = "";

        try {
            // 1. Conecta ao servidor MySQL (sem selecionar banco)
            conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conectado com sucesso ao servidor!");

            // 2. Cria o banco de dados se não existir
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS estoque");
            System.out.println("Banco verificado/criado com sucesso!");

            // 3. Fecha a conexão anterior e conecta ao banco 'estoque'
            conn.close();
            conn = DriverManager.getConnection(url + "estoque", usuario, senha);
            System.out.println("Conectado com sucesso ao banco de dados 'estoque'!");

            // 4. Novo Statement com a nova conexão
            stmt = conn.createStatement();

            criarTabelas(stmt);
            inserirUsuarioAdmin(stmt);

            System.out.println("Tabelas verificadas/criadas com sucesso!");
        
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }

        return conn;
    }

    private static void criarTabelas(Statement stmt) throws SQLException {
        // Tabela produtos
        String sqlProdutos = "CREATE TABLE IF NOT EXISTS produtos ("
                + "ID INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                + "nome VARCHAR(30) NOT NULL UNIQUE,"
                + "unidade VARCHAR(10) NOT NULL,"
                + "lote VARCHAR(10),"
                + "Dtvalidade VARCHAR(10),"
                + "qtde INT(11),"
                + "Vr DECIMAL(10,2),"
                + "controlados ENUM('sim', 'nao')"
                + ")";

        // Tabela usuarios
        String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios ("
                + "ID_user INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                + "nome VARCHAR(30) NOT NULL,"
                + "login VARCHAR(15) NOT NULL UNIQUE,"
                + "senha VARCHAR(10) NOT NULL,"
                + "cargo_user VARCHAR(15) NOT NULL"
                + ")";
 
        // Tabela nota_fiscal
        String sqlNotaFiscal = "CREATE TABLE IF NOT EXISTS notas_fiscais ("
                + "ID_NF INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                + "num_nfe INT(11) NOT NULL,"
                + "data_emissao DATE NOT NULL,"
                + "VrTotal DECIMAL(10,2)"
                + ")";

        // Tabela nota_prod
        String sqlNotaProd = "CREATE TABLE IF NOT EXISTS nota_prod ("
                + "ID_nXp INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                + "nota_id INT(11),"
                + "prod_id INT(11),"
                + "qtde_ent INT(11),"
                + "FOREIGN KEY (nota_id) REFERENCES nota_fiscal(ID_NF),"
                + "FOREIGN KEY (prod_id) REFERENCES produtos(ID)"
                + ")";

        //Comando para executar a criação das tabelas
        stmt.executeUpdate(sqlProdutos);
        stmt.executeUpdate(sqlUsuarios);
        stmt.executeUpdate(sqlNotaFiscal);
        stmt.executeUpdate(sqlNotaProd);
    }

    private static void inserirUsuarioAdmin(Statement stmt) throws SQLException {
        String sqlCheckAdmin = "SELECT COUNT(*) FROM usuarios WHERE login = 'ADMIN'";
        ResultSet rs = stmt.executeQuery(sqlCheckAdmin);
        rs.next();
        
        // Se não existir o Usuário admin ele vai criar 
        if (rs.getInt(1) == 0) {
            String sqlInsertAdmin = "INSERT INTO usuarios (nome, login, senha, cargo_user)"
                    + " VALUES ('ADMIN', 'ADMIN', '0000', 'Admin')";
            stmt.executeUpdate(sqlInsertAdmin);
            System.out.println("Usuário ADMIN criado com sucesso!");
        } else {
            System.out.println("Usuário ADMIN já existe.");
        }
    }
}
