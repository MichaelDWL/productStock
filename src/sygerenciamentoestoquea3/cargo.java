
package sygerenciamentoestoquea3;
import java.util.Scanner;
/**
 *
 * @author MDWL
 */
public class cargo {
    
    Scanner input = new Scanner(System.in);
    int options,moreOrclose;
    
    funcoes funct = new funcoes(); 
    
    public void auxAlmoxarife(){
            
        do {
            System.out.println("1 - Realizar baixa");
            System.out.println("2 - Listar Produtos");
            System.out.println("**********************************************************************");
                options = input.nextInt(); 
            
            if (options > 2 || options < 1){
                
                System.out.println("**********************************************************************");
                System.out.println("Opção inválida, por favor escolha uma opção de 1 a 2");
                System.out.println("**********************************************************************");
                System.out.println("O que Você gostaria de fazer ?");
                System.out.println("**********************************************************************");
            }
            
        } while (options > 2 || options < 1);
              
        switch(options){
            
            case 1:
                // Metodo de realizar baixa    
                System.out.println("Baixar produtos");
            break; 
                
            case 2: 
                System.out.println("**********************************************************************");
                System.out.println(" - Posição de estoque Simplificado - ");
                System.out.println("**********************************************************************");
                    funct.consultarProd();
            
                    // Continuar ou finalizar programa
                    
                    moreOrClose(); 
                    
                    if (moreOrclose == 1){
                        System.out.println("**********************************************************************");
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
            System.out.println("3 - Inventario de Produtos ");
            System.out.println("4 - Entrar com notas ficais");
            System.out.println("5 - Relatórios");
            System.out.println("**********************************************************************");
                options = input.nextInt(); 

            if (options < 0 || options > 5)
                System.out.println("Opção inválida, por favor escolha uma opção de 1 a 5");

        } while (options < 0 || options > 5);

        switch(options){

            case 1: 
            // Metodo de realizar baixa       
            break;  
            
            case 2: 
            // Metodo de Criar Produtos
                
                System.out.println("**********************************************************************");
                System.out.println(" - Criar Produtos - ");
                System.out.println("**********************************************************************");
                
                funct.CreateProd();
                
                System.out.println("**********************************************************************");
                System.out.println("Deseja Visualizar todos os produtos ?");
                System.out.println("**********************************************************************");
                System.out.println("1 - sim");
                System.out.println("2 - não");
                System.out.println("**********************************************************************");
                    options = input.nextInt();
                System.out.println("**********************************************************************");
                
                if (options == 1){
                    funct.consultarProd();
                }
                                
                moreOrClose();
                                
                if (moreOrclose == 1){
                    System.out.println("O que você gostaria de fazer agora ?");
                    System.out.println("**********************************************************************");
                    almoxarife();
                }
            break;
    
            case 3: 
            // Metodo de Invetariar produtos 
                System.out.println("**********************************************************************");
                System.out.println(" - Inventario - ");
                System.out.println("**********************************************************************");
                
                funct.invProd();
                moreOrClose();
                
                if (moreOrclose == 1){
                    System.out.println("O que você gostaria de fazer agora ?");
                    System.out.println("**********************************************************************");
                    almoxarife();
                }
            break;
    
            case 4: 
            // Metodo de Entrar com notas Ficais 
            break;
            
            case 5: 
            // Tirar Relatório 
                do{ System.out.println("Qual relatório você gostaria de visualizar: ");
                    System.out.println("**********************************************************************");
                    System.out.println("1 - Posição de Estoque");
                    System.out.println("2 - Consumo");
                    System.out.println("3 - Lista de compras Mensal");
                    System.out.println("**********************************************************************");
                        options = input.nextInt();

                    if (options < 1 || options > 3){
                        System.out.println("**********************************************************************");
                        System.out.println("Código inválido, por gentileza selecione uma opção de 1 a 3 !");
                        System.out.println("**********************************************************************");
                    }
                    
                } while (options < 1 || options > 3);
                
                switch (options) {
                    case 1 : 
                        // Metodo do Relatorio da posição de estoque
                        funct.consultarProd();
                    break; 
                        
                    case 2 : 
                        // Metodo do Relatório de consumo 
                    break;
                    
                    case 3: 
                        // Metodo do Relátorio de Lista de compras mensal
                    break;     
                }
            break; 
        }        
    }
        
    public void farmaceutico(){
        
    }
    
    public void moreOrClose(){
             
        System.out.println("Deseja fazer mais algo ou Deseja sair ?");
            System.out.println("1 - Voltar para o Menu");
            System.out.println("2 - Sair");
            System.out.println("**********************************************************************");
                moreOrclose = input.nextInt();     
    } 



}
