
package sygerenciamentoestoquea3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author maiqu
 */

public class funcoes {    
    
    Scanner input = new Scanner(System.in);    
    
    public void consultarProd(){
        
        Connection conn = conexaodb.conectar();

        try{
    
            // Criar o objeto Statement para enviar comandos SQL
            Statement stmt = conn.createStatement();

            // SQL de consulta
            String sql = "SELECT * FROM produtos";

            // Executar o SELECT
            ResultSet rs = stmt.executeQuery(sql);

            // Percorrer os resultados
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nome = rs.getString("nome");
                double Vr = rs.getDouble("Vr");
                int qtde = rs.getInt("qtde");
                
                System.out.println("----------------------------------------------------------------------");
                System.out.println(" / ID: " + id + " / Nome: " + nome + " / Valor: " + Vr + " / Quantidade: " + qtde + " / ");
                System.out.println("----------------------------------------------------------------------");
            }   
            
        } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
          }
    }    
 
    public void CreateProd() {
        
        Connection conn = conexaodb.conectar();
        String nome, und, controleEspecial;
        
        System.out.println("Digite o nome do produto: ");
            nome = input.nextLine(); 
        System.out.println("**********************************************************************");
        
        System.out.println("Digite sua unidade de medida: ");
            und = input.next(); 
            
    do{ System.out.println("**********************************************************************");
        System.out.println("Esse Produto é um medicamento da Portaria 344/98? [sim/nao]");
            controleEspecial = input.next();
        
        if (!controleEspecial.equalsIgnoreCase("sim") && !controleEspecial.equalsIgnoreCase("nao")){
            System.out.println("**********************************************************************");
            System.out.println("Valor inválido ! Selecione um valor [sim/nao]");
        }
        
        } while (!controleEspecial.equalsIgnoreCase("sim") && !controleEspecial.equalsIgnoreCase("nao"));
            
            try {
            // SQL de inserção com parâmetros
            String sql = "INSERT INTO produtos (nome, unidade, controlados) VALUES (?, ?, ?)";

            // Usar PreparedStatement para segurança (evita SQL Injection)
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, und);
            stmt.setString(3, controleEspecial);
            //stmt.setInt(3, 10);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Produto Criado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao Criar: " + e.getMessage());
        }
    }
    
    public void baixaDprod() {
        int options; 
        
        System.out.println("Você gostaria de: ");
        System.out.println("1- Dar baixa de produtos para paciente");
        System.out.println("2- Dar baixa de produtos para setor");
            options = input.nextInt(); 
        
        switch (options) {
            
            case 1: 
                // metodo baixa de paciente 
            break; 
                
            case 2:
                // metodo de baixa por setor 
            break;
                
        }
        
        
    }

}
    
    

