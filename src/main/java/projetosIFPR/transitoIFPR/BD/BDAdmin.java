package projetosIFPR.transitoIFPR.BD;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import projetosIFPR.transitoIFPR.cripto.Hash;

// registro da tabela de administradores
// poderia ser um public record, se não fosse pelo fato que geramos um salt para criptografar a senha;
public class BDAdmin {
    public final String hashSenha;
    public final LocalDateTime ultimaDataAtividade;
    public final String salt;

    public BDAdmin(String senha, LocalDateTime ultimaDataAtividade) {
        this.salt = Hash.randomString(8);
        this.hashSenha = Hash.hashString(senha + this.salt);
        this.ultimaDataAtividade = ultimaDataAtividade;
    }

    public ArrayList<Object> export() {
        Object[] elems = {this.hashSenha, this.ultimaDataAtividade, this.salt};
        return new ArrayList<Object>(Arrays.asList(elems));
    }

}