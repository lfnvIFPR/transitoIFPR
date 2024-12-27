package projetosIFPR.transitoIFPR.BD;

import projetosIFPR.transitoIFPR.cripto.Hash;

import java.time.LocalDateTime;

// registro da tabela de fiscalizadores
// poderia ser um public record, se não fosse pelo fato que geramos um salt para criptografar a senha;
public class BDFiscalizador {
    public final String hashSenha;
    public final String nome;
    public final LocalDateTime ultimaDataAtividade;
    public final String salt;

    public BDFiscalizador(String senha, String nome, LocalDateTime ultimaDataAtividade) {
        this.salt = Hash.randomString(8);
        this.hashSenha = Hash.hashString(senha + this.salt);
        this.nome = nome;
        this.ultimaDataAtividade = ultimaDataAtividade;
    }

    public BDFiscalizador(String hashSenha, String nome, LocalDateTime ultimaDataAtividade, String salt) {
        this.hashSenha = hashSenha;
        this.nome = nome;
        this.ultimaDataAtividade = ultimaDataAtividade;
        this.salt = salt;
    }
}
