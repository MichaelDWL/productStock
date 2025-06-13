/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sygerenciamentoestoquea3;

import java.util.Scanner;
import java.sql.*; 
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author MichaelDWL
 */

public class SyGerenciamentoEstoqueA3 {
    
    public static void main(String[] args) {
          
        conexaodb.conectar();
        Scanner input = new Scanner(System.in);
        cargo cargoF = new cargo(); 
        funcoes funct = new funcoes();
        int tentativas = 0, MAX_TENTATIVAS = 5; 
        ResultSet rs; 
        
        String loginMain = null, senhaMain = null, nomeCurto, cargoMain = null;        
        int cargo, options; 
       
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println(" ");
        System.out.println("----------------------------------------------+ LOGIN +---------------------------------------------");
        System.out.println(" ");
        System.out.println("----------------------------------------------------------------------------------------------------");                   
        System.out.println(" ");
        System.out.println("Olá, Seja bem vindo !");

        while (tentativas < MAX_TENTATIVAS) {

            System.out.println(" ");
            System.out.print("Por gentileza, insira seu Login: ");
            loginMain = input.nextLine();

            System.out.print("Por gentileza, insira sua Senha: ");
            senhaMain = input.nextLine();
            System.out.println(" ");

            rs = funct.verificaAcesso(loginMain, senhaMain);

            if (rs != null) {
                break; // Sucesso!
            }

            tentativas++;
            int restantes = MAX_TENTATIVAS - tentativas;

            if (restantes > 0) {
                System.out.println("----------------------------------------------------------------------------------------------------");
                System.out.println("Tentativa inválida. Restam " + restantes + " tentativas.");
            } else {
                System.out.println("----------------------------------------------------------------------------------------------------");
                System.out.println("Número máximo de tentativas excedido. Encerrando.");
                break;
            }
        }


        // Pega nome e sobre nome do Usuário 
        nomeCurto = funct.nomeCurto(loginMain);

        // Passa o login como parametro para o cargo
        cargoF.getLoginMain(loginMain);

        // Verifica acesso do usuário 
        rs = funct.verificaAcesso(loginMain, senhaMain); 

            try {        
                if (rs.next()){
                   cargoMain = rs.getString("cargo_user");
                }
                } catch (SQLException ex) {
                Logger.getLogger(SyGerenciamentoEstoqueA3.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("----------------------------------------------------------------------------------------------------");
                System.out.println(" ");
                System.out.println(nomeCurto + " -+- " + cargoMain);
                System.out.println(" ");
                System.out.println("----------------------------------------------------------------------------------------------------");
                System.out.println(" ");
                System.out.println("Olá " + nomeCurto+",");
                System.out.println(" ");
                System.out.println("O que você gostaria de fazer hoje? ");

               // Vai pegar o cargo e retornar um numero int
                int optCargo = funct.optCargo(cargoMain);


               //MENU seleção de cargo 

                switch (optCargo){

               // CARGO : AuxAlmoxarife

                    case 1: 
                       cargoF.auxAlmoxarife();    
                    break; 

               // CARGO : Almoxarife     

                    case 2: 
                       cargoF.almoxarife(); 
                    break;

               // CARGO : Farmacêutico          

                    case 3:
                       cargoF.farmaceutico();
                    break; 

               // CARGO : Admin 

                   case 4: 
                       cargoF.admin();
                   break; 
               }

            System.out.println(" ");    
            System.out.println(" ");    
            System.out.println(" ");    
            System.out.println("Até a proxima, tenha um bom trabalho !");

        }
    
}
