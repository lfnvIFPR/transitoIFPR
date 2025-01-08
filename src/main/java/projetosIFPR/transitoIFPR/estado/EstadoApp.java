package projetosIFPR.transitoIFPR.estado;

import projetosIFPR.transitoIFPR.IUsuario;

public class EstadoApp {

    static private IUsuario usuarioLogado;

    public static IUsuario getUsuarioLogado() {
        if (usuarioLogado == null)
            System.err.println("Foi acessado o valor do usuário logado, " +
                    "porém não havia usuário ativo.");
        return usuarioLogado;
    }

    public static void setUsuarioLogado(IUsuario usuarioLogado) {
        if (usuarioLogado != null) {
            EstadoApp.usuarioLogado = usuarioLogado;
            return;
        }
        System.err.println("Usuário nulo foi configurado como logado. Ignorando...");
    }

    public static void resetUsuarioLogado() {
        usuarioLogado = null;
    }
}
