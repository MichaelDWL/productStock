/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygerenciamentoestoquea3;


import java.util.Scanner;

/**
 *
 * @author maiqu
 */

public class SyGerenciamentoEstoqueA3 {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        conexaodb.conectar();
        
        int cargo, options; 
        String nome; 
        cargo cargoF = new cargo(); 
     
        
    System.out.println("----------------------------------------------------------------------");
    System.out.println("Olá, Seja bem vindo !");
    System.out.println("Por gentileza, insira seu nome: ");
    System.out.println("----------------------------------------------------------------------");
        nome = input.nextLine();
        
        System.out.println("----------------------------------------------------------------------");
     
                                    //MENU seleção de cargo 
        do{
           System.out.println("Para prosseguir Selecione o Seu cargo: ");

           System.out.println("**********************************************************************");

           System.out.println(" 1 - ADMIN ");
           System.out.println(" 2 - Aux de Almoxarife/Suprimentos");
           System.out.println(" 3 - Almoxarife");
           System.out.println(" 4 - Farmacêutico");
           
           System.out.println("**********************************************************************");
               cargo = input.nextInt();
            
           if (cargo < 0 || cargo > 4){
               System.out.println("**********************************************************************");
               System.out.println("Opção inválida, por favor escolha uma opção de 1 a 4");
           }  

           System.out.println("**********************************************************************");

        } while (cargo < 0 || cargo > 4);

           System.out.println("Seja bem vindo "+nome+"! O que gostaria de fazer hoje? " );
           
           System.out.println("**********************************************************************");                             
           
           switch (cargo){

           // CARGO : ADMINISTRADOR 

                case 1: 
                    cargoF.admin();
                break; 
           
           // CARGO : AUX DE ALMOXARIFE     
                
                case 2: 
                   cargoF.auxAlmoxarife();    
                break;
                    
           // CARGO : ALMOXARIFE         
                    
                case 3:
                   cargoF.almoxarife(); 
                break; 

           // CARGO : FARMACÊUTICO         

               case 4: 
                   cargoF.farmaceutico();
               break; 
           }

        System.out.println("Até a proxima, tenha um bom trabalho !");



    









    }
    
}
