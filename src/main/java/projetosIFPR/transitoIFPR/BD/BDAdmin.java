package projetosIFPR.transitoIFPR.BD;
import java.time.LocalDateTime;

import projetosIFPR.transitoIFPR.utilidade.Hash;

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

    public BDAdmin(String hashSenha, LocalDateTime ultimaDataAtividade, String salt) {
        this.hashSenha = hashSenha;
        this.ultimaDataAtividade = ultimaDataAtividade;
        this.salt = salt;
    }

}