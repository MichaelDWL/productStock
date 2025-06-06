
package sygerenciamentoestoquea3;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author MichaelDWL
 */
public class cargo {
    
    Scanner input = new Scanner(System.in);
    int options,moreOrclose;
    boolean buscar;
    
    
    funcoes funct = new funcoes(); 
               
    public void auxAlmoxarife(){
            
        do {buscar = true; 
            System.out.println("1 - Realizar baixa");
            System.out.println("2 - Listar Produtos");
            System.out.println("----------------------------------------------------------------------");
            try{    
                options = input.nextInt(); 
              
                switch(options){
                    default:
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("Opção inválida, por favor escolha uma opção de 1 a 2");
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("O que Você gostaria de fazer ?");
                        System.out.println("----------------------------------------------------------------------");
                        buscar = false; 
                    break;    

                    case 1:

                        System.out.println("----------------------------------------------------------------------");
                        System.out.println(" ");
                        System.out.println("-------------------------+ BAIXA DE PRODUTOS +------------------------");
                        System.out.println(" ");
                        System.out.println("----------------------------------------------------------------------");
                        funct.baixaDprod();

                        moreOrClose();

                        if (moreOrclose == 1){
                                System.out.println("----------------------------------------------------------------------");
                                System.out.println("O que você gostaria de fazer ?");
                                    auxAlmoxarife(); 
                            }

                    break; 

                    case 2: 
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println(" ");
                        System.out.println("------------------+ POSIÇÃO DE ESTOQUE SIMPLIFICADO +-----------------");
                        System.out.println(" ");
                        System.out.println("----------------------------------------------------------------------");
                            funct.consultarProd();
                            System.out.println(" ");

                            // Continuar ou finalizar programa

                            moreOrClose(); 

                            if (moreOrclose == 1){
                                System.out.println("----------------------------------------------------------------------");
                                System.out.println("O que você gostaria de fazer ?");
                                    auxAlmoxarife(); 
                            }
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite apenas números.");
                input.nextLine(); // Limpa o buffer do scanner (remove o texto inválido)
                buscar = false; 
            }   
            
        } while(buscar == false);       
    }
    
    public void almoxarife(){
            
    do {    buscar = true;
            System.out.println(" ");
            System.out.println("1 - Realizar baixa"); // ok 
            System.out.println("2 - Criar Produto"); // ok 
            System.out.println("3 - Inventario de Produtos "); // ok 
            System.out.println("4 - Entrar com notas ficais"); // ok 
            System.out.println("5 - Relatórios"); // semi-estruturado 
            System.out.println("6 - Redefinir senha"); // semi-estruturado 
            System.out.println(" ");
            System.out.println("----------------------------------------------------------------------");
            try{
                options = input.nextInt();
                System.out.println("----------------------------------------------------------------------");
                System.out.println(" ");
                System.out.println(" ");
                input.nextLine();
                
                switch(options){

                    default:
                        System.out.println("Opção inválida, por favor escolha uma opção de 1 a 5");
                        System.out.println("----------------------------------------------------------------------");    
                        System.out.println(" ");
                        System.out.println("O que você gostaria de fazer hoje ? ");
                        System.out.println(" ");
                        buscar = false; 
                        break; 

                    case 1: 
                    // Metodo de realizar baixa       


                        System.out.println("----------------------------------------------------------------------");
                        System.out.println(" ");
                        System.out.println("-------------------------+ BAIXA DE PRODUTOS +------------------------");
                        System.out.println(" ");
                        System.out.println("----------------------------------------------------------------------");
                        funct.baixaDprod();
                        System.out.println(" ");

                        moreOrClose();

                        if (moreOrclose == 1){
                                System.out.println("----------------------------------------------------------------------");
                                System.out.println("O que você gostaria de fazer ?");
                                    almoxarife(); 
                            }
                    break;  

                    case 2: 
                    // Metodo de Criar Produtos
                        
                        System.out.println(" ");
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println(" ");
                        System.out.println("--------------------------+ CRIAR PRODUTOS +--------------------------");
                        System.out.println(" ");
                        System.out.println("----------------------------------------------------------------------");

                        funct.CreateProd();

                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("Deseja Visualizar todos os produtos ?");
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("Digite 1 para visualizar os produtos, ou qualquer tecla para continuar");
                        System.out.println("----------------------------------------------------------------------");
                            options = input.nextInt();
                        System.out.println("----------------------------------------------------------------------");

                        if (options == 1){
                            funct.consultarProd();
                        }

                        moreOrClose();

                        if (moreOrclose == 1){
                            
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("O que você gostaria de fazer agora ?");
                            System.out.println("----------------------------------------------------------------------");
                            almoxarife();
                        }
                    break;

                    case 3: 
                    // Metodo de Invetariar produtos 
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println(" ");
                        System.out.println("----------------------------+ INVENTÁRIO +----------------------------");
                        System.out.println(" ");
                        System.out.println("----------------------------------------------------------------------");

                        funct.invProd();
                        moreOrClose();

                        if (moreOrclose == 1){
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("O que você gostaria de fazer agora ?");
                            System.out.println("----------------------------------------------------------------------");
                            almoxarife();
                        }
                    break;

                    case 4: 
                    // Metodo de Entrar com notas Ficais 
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println(" ");
                        System.out.println("---------------------+ ENTRAR COM NOTAS FISCAIS +---------------------");
                        System.out.println(" ");
                        System.out.println("----------------------------------------------------------------------");
                        funct.notaFiscal(); 

                        moreOrClose();

                        if (moreOrclose == 1){
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("O que você gostaria de fazer agora ?");
                            System.out.println("----------------------------------------------------------------------");
                                almoxarife();
                        }
                        break;

                    case 5: 
                        // Metodo de Tirar Relatório 
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println(" ");
                        System.out.println("----------------------------+ RELATÓRIOS +----------------------------");
                        System.out.println(" ");
                        System.out.println("----------------------------------------------------------------------");                   

                        do{ System.out.println(" ");
                            System.out.println("Qual relatório você gostaria de visualizar: ");
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println(" ");
                            System.out.println("1 - Posição de Estoque");
                            System.out.println("2 - Consumo");
                            System.out.println("3 - Entrada de produtos");
                            System.out.println(" ");
                            System.out.println("----------------------------------------------------------------------");
                            try{    
                                options = input.nextInt();
                                input.nextLine(); // limpa no numero 

                                switch (options) {

                                    default: 
                                        System.out.println("----------------------------------------------------------------------");
                                        System.out.println("Código inválido, por gentileza selecione uma opção de 1 a 3 !");
                                        System.out.println("----------------------------------------------------------------------");
                                        buscar = false; 
                                    break; 

                                    case 1 : 
                                        // Metodo do Relatorio da posição de estoque
                                        funct.pos_estoque();
                                        
                                        System.out.println(" ");
                                        System.out.println("----------------------------------------------------------------------");
                                        System.out.println(" ");
                                        System.out.println("----------------------------------------------------------------------");
                                        moreOrClose(); 

                                        if (moreOrclose == 1){
                                            System.out.println("----------------------------------------------------------------------");
                                            System.out.println("O que você gostaria de fazer ?");
                                                almoxarife(); 
                                        }

                                    break; 

                                    case 2 : 
                                        // Metodo do Relatório de consumo 
                                        funct.consProd();
                                        
                                        System.out.println(" ");
                                        System.out.println("----------------------------------------------------------------------");
                                        moreOrClose(); 

                                        if (moreOrclose == 1){
                                            System.out.println("----------------------------------------------------------------------");
                                            System.out.println("O que você gostaria de fazer ?");
                                                almoxarife();
                                        }
                                    break;

                                    case 3: 
                                        // Metodo do Relatório de Entrada de produtos 
                                        funct.relatorioNotas();
                                        
                                        System.out.println(" ");
                                        moreOrClose(); 

                                        if (moreOrclose == 1){
                                            System.out.println("----------------------------------------------------------------------");
                                            System.out.println("O que você gostaria de fazer ?");
                                                almoxarife();
                                        }
                                    break;
                                }
                            } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida! Por favor, digite apenas números.");
                            input.nextLine(); // Limpa o buffer do scanner (remove o texto inválido)
                            buscar = false; 
                            }    
                        // Return menu de relatórios 
                        } while (buscar == false); 
                    break;
                    case 6:
                    // Metodo de redefinir senha 
                        
                    break;    
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite apenas números.");
                input.nextLine();
                buscar = false; // Limpa o buffer do scanner (remove o texto inválido)
            } 
        // Return menu de Almoxarife     
        } while (buscar == false);   
    }
        
    public void farmaceutico(){
                    
    do {    buscar = true;
            System.out.println(" ");
            System.out.println("1 - Realizar baixa"); // ok 
            System.out.println("2 - Criar Produto"); // ok 
            System.out.println("3 - Inventario de Produtos "); // ok 
            System.out.println("4 - Entrar com notas ficais"); // ok 
            System.out.println("5 - Relatórios"); // semi-estruturado 
            System.out.println("6 - Redefinir senha"); // semi-estruturado 
            System.out.println(" ");
            System.out.println("----------------------------------------------------------------------");
            try{
                options = input.nextInt();
                System.out.println("----------------------------------------------------------------------");
                System.out.println(" ");
                System.out.println(" ");
                input.nextLine();
                
                switch(options){

                    default:
                        System.out.println("Opção inválida, por favor escolha uma opção de 1 a 5");
                        System.out.println("----------------------------------------------------------------------");    
                        System.out.println(" ");
                        System.out.println("O que você gostaria de fazer hoje ? ");
                        System.out.println(" ");
                        buscar = false; 
                        break; 

                    case 1: 
                    // Metodo de realizar baixa       


                        System.out.println("----------------------------------------------------------------------");
                        System.out.println(" ");
                        System.out.println("-------------------------+ BAIXA DE PRODUTOS +------------------------");
                        System.out.println(" ");
                        System.out.println("----------------------------------------------------------------------");
                        funct.baixaDprod();
                        System.out.println(" ");

                        moreOrClose();

                        if (moreOrclose == 1){
                                System.out.println("----------------------------------------------------------------------");
                                System.out.println("O que você gostaria de fazer ?");
                                    farmaceutico(); 
                            }
                    break;  

                    case 2: 
                    // Metodo de Criar Produtos
                        
                        System.out.println(" ");
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println(" ");
                        System.out.println("--------------------------+ CRIAR PRODUTOS +--------------------------");
                        System.out.println(" ");
                        System.out.println("----------------------------------------------------------------------");

                        funct.CreateProd();

                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("Deseja Visualizar todos os produtos ?");
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("Digite 1 para visualizar os produtos, ou qualquer tecla para continuar");
                        System.out.println("----------------------------------------------------------------------");
                            options = input.nextInt();
                        System.out.println("----------------------------------------------------------------------");

                        if (options == 1){
                            funct.consultarProd();
                        }

                        moreOrClose();

                        if (moreOrclose == 1){
                            
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("O que você gostaria de fazer agora ?");
                            System.out.println("----------------------------------------------------------------------");
                            farmaceutico();
                        }
                    break;

                    case 3: 
                    // Metodo de Invetariar produtos 
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println(" ");
                        System.out.println("----------------------------+ INVENTÁRIO +----------------------------");
                        System.out.println(" ");
                        System.out.println("----------------------------------------------------------------------");

                        funct.invProd();
                        moreOrClose();

                        if (moreOrclose == 1){
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("O que você gostaria de fazer agora ?");
                            System.out.println("----------------------------------------------------------------------");
                            farmaceutico();
                        }
                    break;

                    case 4: 
                    // Metodo de Entrar com notas Ficais 
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println(" ");
                        System.out.println("---------------------+ ENTRAR COM NOTAS FISCAIS +---------------------");
                        System.out.println(" ");
                        System.out.println("----------------------------------------------------------------------");
                        funct.notaFiscal(); 

                        moreOrClose();

                        if (moreOrclose == 1){
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println("O que você gostaria de fazer agora ?");
                            System.out.println("----------------------------------------------------------------------");
                                farmaceutico();
                        }
                        break;

                    case 5: 
                        // Metodo de Tirar Relatório 
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println(" ");
                        System.out.println("----------------------------+ RELATÓRIOS +----------------------------");
                        System.out.println(" ");
                        System.out.println("----------------------------------------------------------------------");                   

                        do{ System.out.println(" ");
                            System.out.println("Qual relatório você gostaria de visualizar: ");
                            System.out.println("----------------------------------------------------------------------");
                            System.out.println(" ");
                            System.out.println("1 - Posição de Estoque");
                            System.out.println("2 - Consumo");
                            System.out.println("3 - Entrada de produtos");
                            System.out.println(" ");
                            System.out.println("----------------------------------------------------------------------");
                            try{    
                                options = input.nextInt();
                                input.nextLine(); // limpa o espaço vazio

                                switch (options) {

                                    default: 
                                        System.out.println("----------------------------------------------------------------------");
                                        System.out.println("Código inválido, por gentileza selecione uma opção de 1 a 3 !");
                                        System.out.println("----------------------------------------------------------------------");
                                        buscar = false; 
                                    break; 

                                    case 1 : 
                                        // Metodo do Relatorio da posição de estoque
                                        funct.pos_estoque();
                                        
                                        System.out.println(" ");
                                        System.out.println("----------------------------------------------------------------------");
                                        System.out.println(" ");
                                        System.out.println("----------------------------------------------------------------------");
                                        moreOrClose(); 

                                        if (moreOrclose == 1){
                                            System.out.println("----------------------------------------------------------------------");
                                            System.out.println("O que você gostaria de fazer ?");
                                                farmaceutico(); 
                                        }

                                    break; 

                                    case 2 : 
                                        // Metodo do Relatório de consumo 
                                        funct.consProd();
                                        
                                        System.out.println(" ");
                                        System.out.println("----------------------------------------------------------------------");
                                        moreOrClose(); 

                                        if (moreOrclose == 1){
                                            System.out.println("----------------------------------------------------------------------");
                                            System.out.println("O que você gostaria de fazer ?");
                                                farmaceutico();
                                        }
                                    break;

                                    case 3: 
                                        // Metodo do Relatório de Entrada de produtos 
                                        
                                        moreOrClose(); 

                                        if (moreOrclose == 1){
                                            System.out.println("----------------------------------------------------------------------");
                                            System.out.println("O que você gostaria de fazer ?");
                                                farmaceutico();
                                        }
                                    break;
                                }
                            } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida! Por favor, digite apenas números.");
                            input.nextLine(); // Limpa o buffer do scanner (remove o texto inválido)
                            buscar = false; 
                            }    
                        // Return menu de relatórios 
                        } while (buscar == false); 
                    break;
                    case 6:
                    // Metodo de redefinir senha 
                        
                    break;    
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite apenas números.");
                input.nextLine();
                buscar = false; // Limpa o buffer do scanner (remove o texto inválido)
            } 
        // Return menu de Farmacêutico     
        } while (buscar == false);   
    
    }
        
    
    public void admin(){
        
        String login,senha,name_user,cargo_user;
        
        senha = "0000";  
        cargo_user = null;
        
        
        do{ buscar = true; 
            System.out.println("  ");
            System.out.println("1 - Criar usuário");
            System.out.println("2 - Alterar Senha");
            System.out.println("3 - Alterar Dados do usuário");
            System.out.println("  ");
            System.out.println("----------------------------------------------------------------------");
            try{
                options = input.nextInt();
                input.nextLine(); //Corrige o bug do enter pra não pular a linha no proximo scanner
                System.out.println("----------------------------------------------------------------------");

                switch (options){ 
                    
                    default:
                        System.out.println("Opção inválida, por favor escolha uma opção de 1 a 3");
                        System.out.println("----------------------------------------------------------------------");
                        System.out.println("O que Você gostaria de fazer ?");
                        System.out.println("----------------------------------------------------------------------"); 
                        buscar = false; 
                    break;
                    // CRIAR USUÁRIO 
                    case 1:
                        System.out.println(" - CRIAR USUÁRIO - ");
                        System.out.println("----------------------------------------------------------------------");

                        System.out.print("Digite qual o nome completo do usuário: ");
                            name_user = input.nextLine(); 

                        do{ buscar = true;
                            System.out.println("Digite qual cargo o usário irá exercer: ");
                            System.out.println("1 - Aux de almoxarife");
                            System.out.println("2 - Almoxarife");
                            System.out.println("3 - Farmacêutico");
                            System.out.println("4 - Admin");
                            try{
                                options = input.nextInt();
                            
                                // Padronizar para evitar erros no banco de dados 
                                switch (options){

                                    default:
                                       System.out.println("----------------------------------------------------------------------");
                                       System.out.println("Opção inválida, por favor escolha uma opção de 1 a 4");
                                       System.out.println("----------------------------------------------------------------------");
                                       buscar = false;
                                    break; 

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
                            } catch (InputMismatchException e) {
                                System.out.println("Entrada inválida! Por favor, digite apenas números.");
                                input.nextLine();
                                buscar = false; // Limpa o buffer do scanner (remove o texto inválido)
                            }                                
                        } while (buscar = false);        

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
                        
                        do{ buscar = true;
                            System.out.print("Digite o login do usuário: ");
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
                        do{ buscar = true; 
                            System.out.println("O que você deseja alterar no cadastro? ");    
                            System.out.println(" ");
                            System.out.println("1 - Nome do usuário ");
                            System.out.println("2 - Login do usuário ");
                            System.out.println("3 - Cargo do usuário"); 
                            System.out.println(" ");
                            System.out.println("----------------------------------------------------------------------");
                            try{    
                                options = input.nextInt(); 
                                System.out.println("----------------------------------------------------------------------");

                                switch (options){

                                    default: 
                                        System.out.println("Opção inválida, por favor escolha uma opção de 1 a 4");
                                        System.out.println("----------------------------------------------------------------------");
                                        buscar = false;
                                    break;
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
                            } catch (InputMismatchException e) {
                                System.out.println("Entrada inválida! Por favor, digite apenas números.");
                                input.nextLine();
                                buscar = false; // Limpa o buffer do scanner (remove o texto inválido)
                            }    
                        // Return para menu de alterar cadastro 
                        } while(buscar == false);    
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, digite apenas números.");
                input.nextLine();
                buscar = false; // Limpa o buffer do scanner (remove o texto inválido)
            }    
        // Return Menu Admin
        } while (buscar == false);    
    }
    
    public void moreOrClose(){
             
        System.out.println("Deseja fazer mais algo ou Deseja sair ?");
            System.out.println("1 - Voltar para o Menu");
            System.out.println("2 - Sair");
            System.out.println("----------------------------------------------------------------------");
                moreOrclose = input.nextInt();     
    } 

}
