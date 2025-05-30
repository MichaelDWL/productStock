
package sygerenciamentoestoquea3;

import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author Michael_DWL
 */
public class cargo {
    
    Scanner input = new Scanner(System.in);
    int options,moreOrclose;
    boolean buscar = true;
    
    funcoes funct = new funcoes(); 
               
    public void auxAlmoxarife(){
            
        do {
            System.out.println("1 - Realizar baixa");
            System.out.println("2 - Listar Produtos");
            System.out.println("----------------------------------------------------------------------");
                options = input.nextInt(); 
            
            if (options > 2 || options < 1){
                
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Opção inválida, por favor escolha uma opção de 1 a 2");
                System.out.println("----------------------------------------------------------------------");
                System.out.println("O que Você gostaria de fazer ?");
                System.out.println("----------------------------------------------------------------------");
            }
            
        } while (options > 2 || options < 1);
              
        switch(options){
            
            case 1:
                // Metodo de realizar baixa    
                System.out.println("Baixar produtos");
            break; 
                
            case 2: 
                System.out.println("----------------------------------------------------------------------");
                System.out.println(" - Posição de estoque Simplificado - ");
                System.out.println("----------------------------------------------------------------------");
                    funct.consultarProd();
            
                    // Continuar ou finalizar programa
                    
                    moreOrClose(); 
                    
                    if (moreOrclose == 1){
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("O que você gostaria de fazer ?");
                            auxAlmoxarife(); 
                    }
            break;
        }
    }
    
    public void almoxarife(){
        
    
        do {
            System.out.println("1 - Realizar baixa");
            System.out.println("2 - Criar Produto"); // ok 
            System.out.println("3 - Inventario de Produtos "); // ok 
            System.out.println("4 - Entrar com notas ficais"); // ok 
            System.out.println("5 - Relatórios"); 
            System.out.println("----------------------------------------------------------------------");
                options = input.nextInt(); 
            System.out.println("----------------------------------------------------------------------");    
            if (options < 0 || options > 5)
                System.out.println("Opção inválida, por favor escolha uma opção de 1 a 5");

        } while (options < 0 || options > 5);

        switch(options){

            case 1: 
            // Metodo de realizar baixa       
            break;  
            
            case 2: 
            // Metodo de Criar Produtos
                
                System.out.println("----------------------------------------------------------------------");
                System.out.println(" - Criar Produtos - ");
                System.out.println("----------------------------------------------------------------------");
                
                funct.CreateProd();
                
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Deseja Visualizar todos os produtos ?");
                System.out.println("----------------------------------------------------------------------");
                System.out.println("1 - sim");
                System.out.println("2 - não");
                System.out.println("----------------------------------------------------------------------");
                    options = input.nextInt();
                System.out.println("----------------------------------------------------------------------");
                
                if (options == 1){
                    funct.consultarProd();
                }
                                
                moreOrClose();
                                
                if (moreOrclose == 1){
                    System.out.println("O que você gostaria de fazer agora ?");
                    System.out.println("----------------------------------------------------------------------");
                    almoxarife();
                }
            break;
    
            case 3: 
            // Metodo de Invetariar produtos 
                System.out.println("----------------------------------------------------------------------");
                System.out.println(" - Inventario - ");
                System.out.println("----------------------------------------------------------------------");
                
                funct.invProd();
                moreOrClose();
                
                if (moreOrclose == 1){
                    System.out.println("O que você gostaria de fazer agora ?");
                    System.out.println("----------------------------------------------------------------------");
                    almoxarife();
                }
            break;
    
            case 4: 
            // Metodo de Entrar com notas Ficais 
                System.out.println("----------------------------------------------------------------------");
                System.out.println(" - Entrar com Nota Fiscal - ");
                System.out.println("----------------------------------------------------------------------");
                funct.notaFiscal(); 
                
                moreOrClose();
                
                if (moreOrclose == 1){
                    System.out.println("O que você gostaria de fazer agora ?");
                    System.out.println("----------------------------------------------------------------------");
                        almoxarife();
                }
                break;
            
            case 5: 
            // Tirar Relatório 
                do{ System.out.println("Qual relatório você gostaria de visualizar: ");
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println("1 - Posição de Estoque");
                    System.out.println("2 - Consumo");
                    System.out.println("3 - Lista de compras Mensal");
                    System.out.println("----------------------------------------------------------------------");
                        options = input.nextInt();
                        input.nextLine(); // limpa no numero 
                    if (options < 1 || options > 3){
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("Código inválido, por gentileza selecione uma opção de 1 a 3 !");
                        System.out.println("----------------------------------------------------------------------");
                    }
                    
                } while (options < 1 || options > 3);
                
                switch (options) {
                    case 1 : 
                        // Metodo do Relatorio da posição de estoque
                        funct.pos_estoque();
                        
                        moreOrClose(); 
                    
                        if (moreOrclose == 1){
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("O que você gostaria de fazer ?");
                                almoxarife(); 
                        }
                        
                    break; 
                        
                    case 2 : 
                        // Metodo do Relatório de consumo 
                        moreOrClose(); 
                    
                        if (moreOrclose == 1){
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("O que você gostaria de fazer ?");
                                almoxarife();
                        }
                    break;
                    
                    case 3: 
                        // Metodo do Relátorio de Lista de compras mensal
                        
                        moreOrClose(); 
                    
                        if (moreOrclose == 1){
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("O que você gostaria de fazer ?");
                                almoxarife();
                        }
                    break;     
                }
            break; 
        }        
    }
        
    public void farmaceutico(){
        
    }
    
    public void admin(){
        
        String login,senha,name_user,cargo_user;
        
        senha = "0000";  
        cargo_user = null;
        
        System.out.println("  ");
        do{ System.out.println("1 - Criar usuário");
            System.out.println("2 - Alterar Senha");
            System.out.println("3 - Alterar Dados do usuário");
            System.out.println("  ");
            System.out.println("----------------------------------------------------------------------");
            options = input.nextInt();
            //Corrige o bug do enter pra não pular a linha no proximo scanner
            input.nextLine();
            System.out.println("----------------------------------------------------------------------");

            if (options < 1 || options > 3){
                    System.out.println("Opção inválida, por favor escolha uma opção de 1 a 3");
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println("O que Você gostaria de fazer ?");
                    System.out.println("----------------------------------------------------------------------"); 
            }
            
        } while (options < 1 || options > 3);
        
        switch (options){ 
            // CRIAR USUÁRIO 
            case 1:
                System.out.println(" - CRIAR USUÁRIO - ");
                System.out.println("----------------------------------------------------------------------");
                
                System.out.print("Digite qual o nome completo do usuário: ");
                    name_user = input.nextLine(); 
                
                do{ System.out.println("Digite qual cargo o usário irá exercer: ");
                    System.out.println("1 - Aux de almoxarife");
                    System.out.println("2 - Almoxarife");
                    System.out.println("3 - Farmacêutico");
                    System.out.println("4 - Admin");
                        options = input.nextInt();

                    if (options < 1 || options > 4){
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("Opção inválida, por favor escolha uma opção de 1 a 4");
                        System.out.println("----------------------------------------------------------------------");
                    }
                } while (options < 1 || options > 4); 
                
                // Padronizar para evitar erros no banco de dados 
                switch (options){
                    case 1:
                        cargo_user = "AuxAlmoxarife"; 
                    break;
                    case 2:
                        cargo_user = "Almoxarife";
                    break;
                    case 3:
                        cargo_user = "Farmacêutico";
                    break;
                    case 4:
                        cargo_user = "Admin"; 
                    break;
                }
                
                System.out.print("Digite qual vai ser o login desse usuário: ");
                    login = input.next();
                System.out.println("----------------------------------------------------------------------");
                
                // Cria o usuário 
                funct.createUser(name_user, login, senha, cargo_user);
                System.out.println("Senha padrão definida como: 0000");
                System.out.println("----------------------------------------------------------------------");
            
                moreOrClose(); 
                    
                if (moreOrclose == 1){
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println("O que você gostaria de fazer ?");
                        admin(); 
                }
            break;
            
                // Redefinir senha 
            case 2: 
                System.out.print("Digite o login do usuário: ");
                    login = input.next(); 
                funct.resetPassword(login);
                
                moreOrClose(); 
                
                if (moreOrclose == 1){
                    System.out.println("----------------------------------------------------------------------");
                    System.out.println("O que você gostaria de fazer ?");
                        admin(); 
                }
            break;
                
            case 3:
                System.out.println(" - Alterar dados do usuário - ");
                System.out.println("----------------------------------------------------------------------");
                do{ System.out.print("Digite o login do usuário: ");
                        login = input.next(); 
                    ResultSet rs = funct.verificaUsuario(login);
                    if (rs == null){
                        buscar = false; 
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("Digite novamente o login do usuário");
                        System.out.println("----------------------------------------------------------------------");
                    }
                } while(buscar = false);
                
                System.out.println("----------------------------------------------------------------------");
                do{ System.out.println("O que você deseja alterar no cadastro? ");    
                    System.out.println(" ");
                    System.out.println("1 - Nome do usuário ");
                    System.out.println("2 - Login do usuário ");
                    System.out.println("3 - Cargo do usuário"); 
                    System.out.println(" ");
                    System.out.println("----------------------------------------------------------------------");
                        options = input.nextInt(); 
                    System.out.println("----------------------------------------------------------------------");
                    if (options < 0 || options > 3) {
                        System.out.println("Opção inválida, por favor escolha uma opção de 1 a 4");
                        System.out.println("----------------------------------------------------------------------");
                    }
                } while(options < 0 || options > 3);
                
                switch (options){
                    
                    case 1:
                        funct.alterName(login);
                        
                        // Fechar ou continuar 
                        moreOrClose(); 
                        
                        if (moreOrclose == 1){
                            System.out.println("O que você gostaria de fazer agora ?");
                            System.out.println("----------------------------------------------------------------------");
                            admin();
                        }
                    break;
                    case 2:
                        funct.alterLogin(login);
                        
                        // Fechar ou continuar 
                        moreOrClose(); 
                
                        if (moreOrclose == 1){
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("O que você gostaria de fazer ?");
                                admin(); 
                        }
                    break;
                    case 3:
                        funct.alterCargo(login);
                        
                        // Fechar ou continuar 
                        moreOrClose(); 
                
                        if (moreOrclose == 1){
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("O que você gostaria de fazer ?");
                                admin(); 
                        }
                    break;
                } 
            break;        
        }    
    }
    
    public void moreOrClose(){
             
        System.out.println("Deseja fazer mais algo ou Deseja sair ?");
            System.out.println("1 - Voltar para o Menu");
            System.out.println("2 - Sair");
            System.out.println("----------------------------------------------------------------------");
                moreOrclose = input.nextInt();     
    } 



}
