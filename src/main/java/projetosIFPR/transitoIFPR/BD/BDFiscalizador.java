package projetosIFPR.transitoIFPR.BD;

import projetosIFPR.transitoIFPR.cripto.Hash;

import java.time.LocalDate;

// registro da tabela de fiscalizadores
// poderia ser um public record, se não fosse pelo fato que geramos um salt para criptografar a senha;
public class BDFiscalizador {
    public final String hashSenha;
    public final String nome;
    public final LocalDate ultimaDataAtividade;
    public final String salt;

    public BDFiscalizador(String senha, String nome, LocalDate ultimaDataAtividade) {
        this.salt = Hash.randomString(8);
        this.hashSenha = Hash.hashString(senha + this.salt);
        this.nome = nome;
        this.ultimaDataAtividade = ultimaDataAtividade;
    }
}
