package projetosIFPR.transitoIFPR;

import projetosIFPR.transitoIFPR.GUI.TelaLogin;
import projetosIFPR.transitoIFPR.estado.GUIController;

public class App {
    public static void main(String[] args) {

        System.out.println(System.getProperty("java.class.path"));
        // Scanner sc = new Scanner(System.in);
        // System.out.println("Escreva o nome do usuário a autenticar: ");
        // String nome = sc.nextLine();
        // System.out.println("Escreva a senha: ");
        // String senha = sc.nextLine();


        GUIController.setup();
        GUIController.configurarAtivo(new TelaLogin());
        //sc.close();
    }
}
