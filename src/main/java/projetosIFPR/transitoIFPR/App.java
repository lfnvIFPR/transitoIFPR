package projetosIFPR.transitoIFPR;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import projetosIFPR.transitoIFPR.BD.BancoDeDados;
import projetosIFPR.transitoIFPR.GUI.TelaLogin;
import projetosIFPR.transitoIFPR.util.GUIController;

import java.util.List;

public class App {
    public static void main(String[] args) {

        System.out.println(System.getProperty("java.class.path"));
        // Scanner sc = new Scanner(System.in);
        // System.out.println("Escreva o nome do usuário a autenticar: ");
        // String nome = sc.nextLine();
        // System.out.println("Escreva a senha: ");
        // String senha = sc.nextLine();


        new GUIController();
        TelaLogin login = new TelaLogin();
        //sc.close();
    }
}
