package projetosIFPR.transitoIFPR;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import projetosIFPR.transitoIFPR.BD.BancoDeDados;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // System.out.println("Escreva o nome do usuário a autenticar: ");
        // String nome = sc.nextLine();
        // System.out.println("Escreva a senha: ");
        // String senha = sc.nextLine();
        Autenticador entrada = new Autenticador();
        IUsuario a = entrada.gerarUsuario("123456789", "senha");

        BancoDeDados bd = new BancoDeDados();

        if (a != null) {
            System.out.println("Olá, " + a.getNome() + "! Estes são os comandos que você pode executar.");
            ArrayList<String> comandos = new ArrayList<String>(a.getComandosAcessiveis());
            for (String comando: comandos) {
                System.out.print(comando);
                if (!Objects.equals(comando, comandos.getLast())) {
                    System.out.print(", ");
                }

            }
            System.out.println();
            return;
        }
        System.out.println("Usuário inexistente!");
        //sc.close();
    }
}
