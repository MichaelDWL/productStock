
package sygerenciamentoestoquea3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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

    public void invProd() { 
        
        boolean busca = true; 
        int codProd, quantidadeAtual,newQtde;
        String newLote, newDt;
        String name; 
        
        // Conectar com banco de dados 
        Connection conn = conexaodb.conectar();
        
        System.out.println("Qual produto você deseja inventariar ?");
        System.out.println("**********************************************************************");
            consultarProd(); // imprime a lista de produtos com os códigos 
        System.out.println("**********************************************************************");
        System.out.print("Digite o código do produto: ");   
        
        do{    codProd = input.nextInt();
            System.out.println("**********************************************************************");
            try {
                // Seleciona um produto dentro da tabela produto onde o ID é igual a...
                String sql = "select * from produtos where ID = ?";
                
                // Coloca a sintaxe de conectar/consultar na variavel stmt  
                PreparedStatement stmt = conn.prepareStatement(sql);
                
                // Substitui a interrogação que existe na string sql 
                stmt.setInt(1, codProd); 

                // Executa a consulta e coloca o retorno dela em "Resultado"
                ResultSet resultado = stmt.executeQuery();

                // Se tiver retornado algum resultado 
                if (resultado.next()) {
                    
                    System.out.println("Código encontrado no banco de dados.");
                    busca = true;
                    
                    // Pega a quantidade e o nome do produto no banco 
                    quantidadeAtual = resultado.getInt("qtde");
                    name = resultado.getString("nome");
                    
                    System.out.println("Quantidade atual de: "+ name +" é de " + quantidadeAtual);
                    System.out.println("ALERTA ! Você irá substituir a quantidade atual do produto em estoque");
                    System.out.println("**********************************************************************");
                    System.out.println("Qual a quantidade real de " + name + " existe no estoque fisico ?");
                    System.out.println("**********************************************************************");
                        newQtde = input.nextInt(); 
                    System.out.println("**********************************************************************");
                    System.out.println("Qual seu lote?");
                    System.out.println("**********************************************************************");
                        newLote = input.next(); 
                    System.out.println("**********************************************************************");
                        System.out.println("Qual a data de validade?");
                    System.out.println("**********************************************************************");
                        newDt = input.next();                        
                    System.out.println("**********************************************************************");
                    
                    // Coloca na variavel o codigo para fazer o update no banco de dados
                    String sqlUpdate = "UPDATE produtos set qtde = ?, lote = ?, Dtvalidade = ? where ID = ?"; 
                    
                    // Comando para fazer o update 
                    PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate); 
                    
                    // Substitui na String sqlUpdate os valores com interrogação 
                    stmtUpdate.setInt(1, newQtde);
                    stmtUpdate.setString(2, newLote);
                    stmtUpdate.setString(3, newDt);
                    stmtUpdate.setInt(4, codProd);
                    
                    // Pega quantas linhas foram afetadas 
                    int linhasAfetadas = stmtUpdate.executeUpdate();
                    
                    // Se existir linhas afetadas 
                    if (linhasAfetadas > 0) {
                        System.out.println("Dados do produto atualizados com sucesso!");
                        System.out.println("Novos dados para o produto: Qtde: " + newQtde + "/ lote: " + newLote + "/ Validade: " +newDt);
                        System.out.println("**********************************************************************");
                    } 
                    
                    else {
                        System.out.println("Erro ao atualizar os dados.");
                        System.out.println("**********************************************************************");
                        busca = false; 
                    }
                }
                
                // se não encontrar o código ele vai pedir novamente pra digitar o código
                else {
                    System.out.println("Código NÃO encontrado, selecione um produto cadastrado");
                    busca = false;
                    System.out.println("Digite novamente o código do produto");
                    System.out.println("**********************************************************************");
                }
            }  
            // se ocorrer algum erro, vai mostrar a pilha de execução do erro 
            catch (SQLException e) {
                e.printStackTrace();
                System.out.println("**********************************************************************");
            }
   
        } while(busca==false);        
    }
        
        
        
        
    }
    
    

