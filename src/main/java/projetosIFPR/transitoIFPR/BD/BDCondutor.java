package projetosIFPR.transitoIFPR.BD;
import projetosIFPR.transitoIFPR.cripto.Hash;

import java.time.LocalDate;

// registro da tabela de administradores
// poderia ser um public record, se não fosse pelo fato que geramos um salt para criptografar a senha;
public class BDCondutor {
    public final String hashSenha;
    public final LocalDate dataCNH;
    public final LocalDate dataNascimento;
    public final int pontuacao;
    public final LocalDate ultimaDataAtividade;
    public final String salt;

    public BDCondutor(String senha,
                      LocalDate dataCNH, LocalDate dataNascimento,
                      int pontuacao, LocalDate ultimaDataAtividade) {
        this.salt = Hash.randomString(8);
        this.hashSenha = Hash.hashString(senha + this.salt);
        this.dataCNH = dataCNH;
        this.dataNascimento = dataNascimento;
        this.pontuacao = pontuacao;
        this.ultimaDataAtividade = ultimaDataAtividade;
    }
}