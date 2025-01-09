package projetosIFPR.transitoIFPR;

import projetosIFPR.transitoIFPR.BD.*;
import projetosIFPR.transitoIFPR.utilidade.Hash;

import java.util.Objects;

public class Autenticador{

    public IUsuario gerarUsuario(String ID, String senha, BancoDeDados BD) {
        if (!BD.IDValido(ID)) return null;

        char diferenciador = ID.charAt(0);
        
        IUsuario usuario = null;
        switch (diferenciador) {
            case 'F':
                BDFiscalizador dadosF = BD.obterRegistroF(ID);
                if (Objects.isNull(dadosF)) return null;
                if (!verificarSenha(senha, dadosF.salt, dadosF.hashSenha)) return null;
                usuario = new Fiscalizador(ID, dadosF.ultimaDataAtividade);
                break;
            case 'A':
                BDAdmin dadosA = BD.obterRegistroA(ID);
                if (Objects.isNull(dadosA)) return null;
                if (!verificarSenha(senha, dadosA.salt, dadosA.hashSenha)) return null;
                usuario = new Administrador(ID, dadosA.ultimaDataAtividade);
                break;
            default:
                if (ID.length() == 9) {
                    BDCondutor dadosC = BD.obterRegistroC(ID);
                    if (Objects.isNull(dadosC)) return null;
                    if (!verificarSenha(senha, dadosC.salt, dadosC.hashSenha)) return null;
                    usuario = new Condutor(ID, dadosC.ultimaDataAtividade);
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
