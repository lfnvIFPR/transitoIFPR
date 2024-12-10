package projetosIFPR.transitoIFPR;

import projetosIFPR.transitoIFPR.BD.*;
import projetosIFPR.transitoIFPR.cripto.Hash;

import java.util.Objects;

public class Autenticador{

    public IUsuario gerarUsuario(String ID, String senha) {
        if (!BancoDeDados.IDValido(ID)) return null;

        char diferenciador = ID.charAt(0);
        
        IUsuario usuario = null;
        switch (diferenciador) {
            case 'F':
                BDFiscalizador dadosF = BancoDeDados.obterRegistroF(ID);
                if (Objects.isNull(dadosF)) return null;
                if (!verificarSenha(senha, dadosF.salt, dadosF.hashSenha)) return null;
                usuario = new Fiscalizador(ID);
                break;
            case 'A':
                BDAdmin dadosA = BancoDeDados.obterRegistroA(ID);
                if (Objects.isNull(dadosA)) return null;
                if (!verificarSenha(senha, dadosA.salt, dadosA.hashSenha)) return null;
                usuario = new Administrador(ID);
                break;
            default:
                if (ID.length() == 9) {
                    BDCondutor dadosC = BancoDeDados.obterRegistroC(ID);
                    if (Objects.isNull(dadosC)) return null;
                    if (!verificarSenha(senha, dadosC.salt, dadosC.hashSenha)) return null;
                    usuario = new Condutor(ID);
                }
                return usuario;
        }


        return usuario;
    }

    private boolean verificarSenha(String senha, String salt, String hashSenha) {
        if (!Hash.hashCorrespondente(senha + salt, hashSenha)) {
            System.out.println("Senha inválida.");
            return false;
        }
        return true;
    }

}
