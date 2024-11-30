package projetosIFPR.transitoIFPR;

public class Autenticador{

    public IUsuario gerarUsuario(String IDUsuario, String senha) {
        char diferenciador = IDUsuario.charAt(0);
        
        IUsuario usuario = null;
        switch (diferenciador) {
            case 'C':
                usuario = new Condutor(IDUsuario);
            break;
            case 'F':
                usuario = new Fiscalizador(IDUsuario);
            break;
            case 'A':
                usuario = new Administrador(IDUsuario);
            break;
            default:
                return usuario;
        }
        
        return usuario;
    } 

}
