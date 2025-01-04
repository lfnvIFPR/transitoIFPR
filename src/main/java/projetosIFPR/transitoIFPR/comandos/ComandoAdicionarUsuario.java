package projetosIFPR.transitoIFPR.comandos;

import projetosIFPR.transitoIFPR.IUsuario;
import projetosIFPR.transitoIFPR.BD.BancoDeDados;

public class ComandoAdicionarUsuario implements Comando {
    
    IUsuario usuario;


    public ComandoAdicionarUsuario(IUsuario usuario) {
        this.usuario = usuario;
    }




    @Override
    public Resultados executar() {
        // forcomando in comandos
        // listar todos os comandos de comando 0 a comando numeroDeComandos

        BancoDeDados BD = new BancoDeDados();
        String tabela = usuario.getNome();

        return null;
    }

}
