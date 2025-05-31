
package sygerenciamentoestoquea3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*; 
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maiqu
 */

public class funcoes {    
    
    Scanner input = new Scanner(System.in);    
    Connection conn = conexaodb.conectar();
    boolean busca = true; 
    int codProd; 
    
    // Consultar lista de produtos 
    public void consultarProd(){
        
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
    
    // Criar produtos 
    public void CreateProd() {

        String nome, und, controleEspecial;
        
        System.out.println("Digite o nome do produto: ");
            nome = input.nextLine(); 
        System.out.println("----------------------------------------------------------------------");
        
        System.out.println("Digite sua unidade de medida: ");
            und = input.next(); 
            
    do{ System.out.println("----------------------------------------------------------------------");
        System.out.println("Esse Produto é um medicamento da Portaria 344/98? [sim/nao]");
            controleEspecial = input.next();
        
        if (!controleEspecial.equalsIgnoreCase("sim") && !controleEspecial.equalsIgnoreCase("nao")){
            System.out.println("----------------------------------------------------------------------");
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
            
            // Executa e Retorna o resultado 
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Produto Criado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao Criar: " + e.getMessage());
        }
    }
    
    // Baixa de Produtos 
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
    
    // Invetariar produto 
    public void invProd() { 
        
        int quantidadeAtual,newQtde;
        String newLote, newDt;
        String name; 
        
        // Conectar com banco de dados 
        
        System.out.println("Qual produto você deseja inventariar ?");
        System.out.println("----------------------------------------------------------------------");
        
        // verifica se existe o produto no banco de dados e coloca as informações do produto em "Resultado"
        ResultSet resultado = verificaDB();

        // Se tiver retornado algum resultado 
        try { 
            // ele vai ler os resultados
            if (resultado.next()) {

            // Pega a quantidade e o nome do produto no banco 
            
                quantidadeAtual = resultado.getInt("qtde");
                name = resultado.getString("nome");

                System.out.println("Quantidade atual de: "+ name +" é de " + quantidadeAtual);
                System.out.println("ALERTA ! Você irá substituir a quantidade atual do produto em estoque");
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Qual a quantidade real de " + name + " existe no estoque fisico ?");
                System.out.println("----------------------------------------------------------------------");
                    newQtde = input.nextInt(); 
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Qual seu lote?");
                System.out.println("----------------------------------------------------------------------");
                    newLote = input.next(); 
                System.out.println("----------------------------------------------------------------------");
                    System.out.println("Qual a data de validade?  (dd/mm/aaaa)");
                System.out.println("----------------------------------------------------------------------");
                    newDt = input.next();                        
                System.out.println("----------------------------------------------------------------------");

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
                    System.out.println("----------------------------------------------------------------------");
                } 

                else {
                    System.out.println("Erro ao atualizar os dados.");
                    System.out.println("----------------------------------------------------------------------");
                    busca = false; 
                }
                
            }
        } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("----------------------------------------------------------------------");
              }
    }    
    
    // Entrada de nota fiscal    
    public void notaFiscal() {
        
        int nFe, qtdeDB, qtdeNFE, qtdeSOMA,idNotaFiscal = -1;
        String dtEmit,name;
        Double vrTotal, vrUn;
        busca = false; 
            
            // PARTE 1 - Informações da nota fiscal 
        do{ System.out.print("Digite o numero da Nota Fiscal: ");
                nFe = input.nextInt();
            System.out.print("Digite sua data de emissão (dd/mm/yyyy): ");
                dtEmit = input.next();

            // Converte a data para ser amarzenada de forma correta no banco de dados 
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dtEmit, formatter);

            System.out.print("Digite o valor total da nota: ");
                vrTotal = input.nextDouble(); 

            // PARTE 2 - Adiciona a nota fiscal no banco 
            try {
                
                // Criar uma nota fiscal no banco 
                String sql_nfe = "insert into notas_fiscais (num_nfe, data_emissao, Vrtotal) value (?,?,?)";    
                PreparedStatement stmt = conn.prepareStatement(sql_nfe,Statement.RETURN_GENERATED_KEYS);
                
                // Substitui "?" em sql_nfe
                stmt.setInt(1, nFe);
                stmt.setDate(2, java.sql.Date.valueOf(data));
                stmt.setDouble(3, vrTotal);
                
                // Executa o Update 
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                
                // Vai ler o resultado e pegar o ID da nota fiscal
                if (rs.next()) {
                idNotaFiscal = rs.getInt(1);
                }
                if (idNotaFiscal == -1) {
                System.out.println("Erro: ID da nota fiscal não foi gerado!");
                }
                busca = true;
                
            } catch (SQLException ex) {
                System.out.println("Erro ao entrar com a nota");
                Logger.getLogger(funcoes.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("**********************************************************************");
              }
            
        } while (busca == false);     
        
            // PARTE 3 - DADOS DO PRODUTO      
        do{ System.out.println("**********************************************************************");
            System.out.println("Selecine o Produto que você deseja entrar");

            ResultSet resultado = verificaDB();

            System.out.println("**********************************************************************");
            System.out.print("Digite a quantidade do produto: ");
                qtdeNFE = input.nextInt(); 
            System.out.print("Digite seu valor unitário: ");
                vrUn = input.nextDouble(); 
            System.out.println("**********************************************************************");

            // PARTE 3 - ADICIONAR QTDE NO BANCO 
            
            busca = false; 
            
            try {   
                    if (resultado.next()){
                        name = resultado.getString("nome");
                        codProd = resultado.getInt("ID");
                        qtdeDB = resultado.getInt("qtde");
                        
                        qtdeSOMA = qtdeDB + qtdeNFE;
                    
                        // Coloca na variavel o codigo para fazer o update no banco de dados
                        String sqlUpdate = "UPDATE produtos set qtde = ?, Vr = ? where ID = ?"; 

                        // Comando para fazer o update 
                        PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate); 

                        // Substitui na String sqlUpdate os valores com interrogação 
                        stmtUpdate.setInt(1, qtdeSOMA);
                        stmtUpdate.setDouble(2, vrUn);
                        stmtUpdate.setInt(3, codProd);

                        // Pega quantas linhas foram afetadas 
                        int linhasAfetadas = stmtUpdate.executeUpdate();

                        // Se existir linhas afetadas 
                        if (linhasAfetadas > 0) {
                            System.out.println("Nota Fiscal Entrada com sucesso");
                            System.out.println("Novos dados para " +name+": Qtde: " + qtdeSOMA + " Vr: "+vrUn);
                            System.out.println("**********************************************************************");
                            busca = true; 
                        } 

                        else {
                            System.out.println("Erro ao entrar com a nota Fiscal !");
                            System.out.println("**********************************************************************");
                        }
                    
                    } else{
                        System.out.println("Nenhum resultado encontrado");
                    }

                    // PARTE 5 - Adiciona a nota e o produto na tabela comparativa 
                    String notaXprodsql = "insert into nota_prod (nota_id, prod_id, qtde_ent) value (?,?,?)"; 

                    // Comando para fazer o insert  
                    PreparedStatement stmt_nt_pr = conn.prepareStatement(notaXprodsql);
                    
                    stmt_nt_pr.setInt(1, idNotaFiscal);
                    stmt_nt_pr.setInt(2, codProd);
                    stmt_nt_pr.setInt(3, qtdeNFE);
                    
                    // Executa o Update 
                    stmt_nt_pr.executeUpdate();
                    
                } catch (SQLException ex) {
                Logger.getLogger(funcoes.class.getName()).log(Level.SEVERE, null, ex);
            }
        // Se ocorreu algum erro, recomece o processo  
        } while (busca == false);           
    }
    
    // verifica se o produto digitado existe no banco de dados 
    public ResultSet verificaDB (){
            ResultSet resultado = null; 
        
            consultarProd(); // imprime a lista de produtos com os códigos 
            System.out.println("**********************************************************************");
            System.out.print("Digite o código do produto: ");
                
        do{ codProd = input.nextInt();
            System.out.println("**********************************************************************");
            try {
                // Seleciona um produto dentro da tabela produto onde o ID é igual a...
                String sql = "select * from produtos where ID = ?";
                
                // Coloca a sintaxe de conectar/consultar na variavel stmt  
                PreparedStatement stmt = conn.prepareStatement(sql);
                
                // Substitui a interrogação que existe na string sql 
                stmt.setInt(1, codProd); 

                // Executa a consulta e coloca o retorno dela em "Resultado"
                resultado = stmt.executeQuery();
                
                // Verifica se existe algum resultado 
                if (resultado.isBeforeFirst()) {
                    
                    System.out.println("Código encontrado no banco de dados.");
                    busca = true;
                    
                }
                
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

      return resultado; 
        
    }
    
    // Relatórios 
    
    // Relátorio de Posição de estoque 
    public void pos_estoque(){
        
        StringBuilder dados = new StringBuilder();
        
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
                
                String linha = id + "," + nome + "," + Vr + "," + qtde; // armazena os dados nessa variavel 
                
                dados.append(linha).append("\n"); //Coloca na variavel "dados" a String da variavel linha 
                
                System.out.println("----------------------------------------------------------------------");
                System.out.println(" / ID: " + id + " / Nome: " + nome + " / Valor: " + Vr + " / Quantidade: " + qtde + " / ");
                System.out.println("----------------------------------------------------------------------");
            }   
            
        } catch (Exception e) {
            System.out.println("Erro ao consultar: " + e.getMessage());
        }
        
        System.out.print("Deseja salvar o relatório em CSV? (s/n): ");
            String opt = input.next();
            input.nextLine(); // tira a linha em branco que buga 

        if (opt.equalsIgnoreCase("s")) {
            System.out.print("Digite o nome do arquivo: ");
            String name_relat = input.nextLine();

            // Caminho fixo
            String pasta = "C:/Users/maiqu/OneDrive/Área de Trabalho/relatorios";

            // Cria a pasta se não existir
            File dir = new File(pasta);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Garante separador no final do caminho (bug que nao salva arquivo)
            if (!pasta.endsWith("/") && !pasta.endsWith("\\")) {
                pasta += "/";
            }

            // Monta o caminho completo
            String caminhoCompleto = pasta + name_relat;
            if (!caminhoCompleto.toLowerCase().endsWith(".csv")) {
                caminhoCompleto += ".csv";
            }

            salvarCSV(dados.toString(), caminhoCompleto);
        }

    }
    
    // Salvar relatório em CSV
    public void salvarCSV(String conteudo, String caminhoCompleto) {
        
    try (FileWriter writer = new FileWriter(caminhoCompleto)) {
        writer.write(conteudo);
        System.out.println("Relatório salvo em: " + caminhoCompleto);
    } catch (IOException e) {
        System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
    }
}
    
    
    
    // CONTROLE DO ADMIN
    
    
    
    // Cria um novo usuário 
    public void createUser(String n, String l, String s, String c){ 
        
        String name_user, login, senha, cargo_user;
        // recebe os dados de fora da função 
        name_user = n;
        login = l; 
        senha = s; 
        cargo_user = c;
        
        try {
            String sql = "INSERT into usuarios (nome,login,senha,cargo_user) value (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, name_user);
            stmt.setString(2, login);
            stmt.setString(3, senha);
            stmt.setString(4, cargo_user);
            
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Usuário Criado com sucesso!");
            }

        } catch (Exception e) {
            System.out.println("Erro ao Criar Usuário: " + e.getMessage());
        }
         
    }
    
    // Definir a senha para o valor padrão 0000 
    public void resetPassword(String Login){
        
        String login = Login;
        
        try {
            // Coloca na variavel o codigo para fazer o update no banco de dados
            String sqlUpdate = "UPDATE usuario set senha = 0000 where login = ?"; 

            // Comando para fazer o update 
            PreparedStatement stmtUpdate; 
            stmtUpdate = conn.prepareStatement(sqlUpdate);

            // Substitui na String sqlUpdate os valores com interrogação 
            stmtUpdate.setString(1, login);
            
            // Pega quantas linhas foram afetadas 
            int linhasAfetadas = stmtUpdate.executeUpdate();

            // Se existir linhas afetadas 
            if (linhasAfetadas > 0) {
                System.out.println("Senha Definida como padrão: 0000");
            } 

            else {
                System.out.println("Erro ao definir senha");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(funcoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // Verifica login e senha do usuario 
    public ResultSet verificaAcesso (String Login, String Senha){
            
            String login, senha;
            
            login = Login; 
            senha = Senha; 
            
            ResultSet resultado;    

            try {
                // Seleciona um usuario dentro da tabela usuario onde o login é igual a...
                String sql = "select * from usuarios where login = ?";
                
                // Coloca a sintaxe de conectar/consultar na variavel stmt  
                PreparedStatement stmt = conn.prepareStatement(sql);
                
                // Substitui a interrogação que existe na string sql 
                stmt.setString(1, login); 

                // Executa a consulta e coloca o retorno dela em "Resultado"
                resultado = stmt.executeQuery();
                
                // Verifica se existe algum resultado 
                if (resultado.next()) {
                
                    String senhadb = resultado.getString("senha");
                    
                    if (senha.equals(senhadb)){
                        //Volta para consultar novamente 
                        resultado.beforeFirst();
                        //Se o login e a senha tiver correto, retorna o resultado da busca
                        return resultado;
                    }
                    else { 
                        System.out.println("Senha incorreta !");
                    }
                }
                else {
                    System.out.println("login não econtrado");
                }
            }  
            // se ocorrer algum erro, vai mostrar a pilha de execução do erro 
            catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }
    
    // Verifica no banco e retorna as informações do usuario 
    public ResultSet verificaUsuario (String Login){
        
        String login = Login;
        ResultSet resultado; 
        
        try {
                // Seleciona um usuario dentro da tabela usuario onde o login é igual a...
                String sql = "select * from usuarios where login = ?";
                
                // Coloca a sintaxe de conectar/consultar na variavel stmt  
                PreparedStatement stmt = conn.prepareStatement(sql);
                
                // Substitui a interrogação que existe na string sql 
                stmt.setString(1, login); 

                // Executa a consulta e coloca o retorno dela em "Resultado"
                resultado = stmt.executeQuery();
                
                // Verifica se existe algum resultado 
                if (resultado.isBeforeFirst()) {
                    System.out.println("Login encontrado !");
                    return resultado; 
                }
                else {
                    System.out.println("Login não econtrado"); 
                }
            }  
            // se ocorrer algum erro, vai mostrar a pilha de execução do erro 
            catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }
    
    public String nomeCurto(String login) {
        String nomeCurto = null;

        try {
            // Vai pegar apenas os dois primeiros nomes do usuario 
            String sql = "SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(nome, ' ', 2), ' ', -2) AS nome_curto " +
                         "FROM usuarios WHERE login = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, login);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nomeCurto = rs.getString("nome_curto");
            }
        
        } catch (SQLException e) {
            System.out.println("Erro ao consultar nome: " + e.getMessage());
        }

        return nomeCurto;
    }


    // Altera os dados do usuário
    public void alterLogin (String Login){
        
        String login,newLogin;
        login = Login; 
        
        System.out.print("Digite o novo login: ");
            newLogin = input.nextLine(); 
        
        // Coloca na variavel o codigo para fazer o update no banco de dados
        String sqlUpdate = "UPDATE usuarios set login = ? where login = ?"; 

        // Comando para fazer o update 
        PreparedStatement stmtUpdate; 
        try {
            stmtUpdate = conn.prepareStatement(sqlUpdate);

        // Substitui na String sqlUpdate os valores com interrogação 
        stmtUpdate.setString(1, newLogin);
        stmtUpdate.setString(2, login);

        // Pega quantas linhas foram afetadas 
        int linhasAfetadas = stmtUpdate.executeUpdate();

        // Se existir linhas afetadas 
        if (linhasAfetadas > 0) {
            System.out.println("Login do usuário atualizado com sucesso!");
        } 

        else {
            System.out.println("Erro ao atualizar os dados de login."); 
        }
        } catch (SQLException ex) {
            Logger.getLogger(funcoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void alterCargo (String Login){
        
        String login,newCargo;
        login = Login; 
        
        System.out.print("Digite o novo Cargo: ");
            newCargo = input.nextLine(); 
        
        // Coloca na variavel o codigo para fazer o update no banco de dados
        String sqlUpdate = "UPDATE usuarios set cargo_user = ? where login = ?"; 

        // Comando para fazer o update 
        PreparedStatement stmtUpdate; 
        try {
            stmtUpdate = conn.prepareStatement(sqlUpdate);

        // Substitui na String sqlUpdate os valores com interrogação 
        stmtUpdate.setString(1, newCargo);
        stmtUpdate.setString(2, login);

        // Pega quantas linhas foram afetadas 
        int linhasAfetadas = stmtUpdate.executeUpdate();

        // Se existir linhas afetadas 
        if (linhasAfetadas > 0) {
            System.out.println("Cargo do usuário atualizado com sucesso!");
        } 

        else {
            System.out.println("Erro ao atualizar os dados do Cargo do usuário."); 
        }
        } catch (SQLException ex) {
            Logger.getLogger(funcoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void alterName (String Login){
        
        String login,newName;
        login = Login; 
        
        System.out.print("Digite o Nome do usuário: ");
            newName = input.nextLine(); 
        
        // Coloca na variavel o codigo para fazer o update no banco de dados
        String sqlUpdate = "UPDATE usuarios set nome = ? where login = ?"; 

        // Comando para fazer o update 
        PreparedStatement stmtUpdate; 
        try {
            stmtUpdate = conn.prepareStatement(sqlUpdate);

        // Substitui na String sqlUpdate os valores com interrogação 
        stmtUpdate.setString(1, newName);
        stmtUpdate.setString(2, login);

        // Pega quantas linhas foram afetadas 
        int linhasAfetadas = stmtUpdate.executeUpdate();

        // Se existir linhas afetadas 
        if (linhasAfetadas > 0) {
            System.out.println("Nome do usuário atualizado com sucesso!");
        } 

        else {
            System.out.println("Erro ao atualizar os dados do nome do usuário."); 
        }
        } catch (SQLException ex) {
            Logger.getLogger(funcoes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void alterSenha (String Login){
        
        String login,newSenha;
        login = Login; 
        
        System.out.print("Digite a nova Senha: ");
            newSenha = input.nextLine(); 
        
        // Coloca na variavel o codigo para fazer o update no banco de dados
        String sqlUpdate = "UPDATE usuarios set senha = ? where login = ?"; 

        // Comando para fazer o update 
        PreparedStatement stmtUpdate; 
        try {
            stmtUpdate = conn.prepareStatement(sqlUpdate);

        // Substitui na String sqlUpdate os valores com interrogação 
        stmtUpdate.setString(1, newSenha);
        stmtUpdate.setString(2, login);

        // Pega quantas linhas foram afetadas 
        int linhasAfetadas = stmtUpdate.executeUpdate();

        // Se existir linhas afetadas 
        if (linhasAfetadas > 0) {
            System.out.println("Sua senha foi atualizada com sucesso!");
        } 

        else {
            System.out.println("Erro ao atualizar sua senha."); 
        }
        } catch (SQLException ex) {
            Logger.getLogger(funcoes.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    public int optCargo(String Cargo){
    
        String cargo = Cargo; 
        int cargoNum;

        if (cargo.equalsIgnoreCase("AuxAlmoxarife")) {
            return cargoNum = 1; 
        }
        if (cargo.equalsIgnoreCase("Almoxarife")) {
            return cargoNum = 2; 
        }
        if (cargo.equalsIgnoreCase("Farmacêutico")) {
            return cargoNum = 3; 
        }
        if (cargo.equalsIgnoreCase("Admin")) {
            return cargoNum = 4; 
        }
        return 0;
    }

    
   



    
}    

    
    

