package projetosIFPR.transitoIFPR.GUI.Componentes;

import projetosIFPR.transitoIFPR.comandos.Comando;

import java.awt.*;

public class BotaoComando implements OpcaoLista, BotaoExecutor {

    Comando comandoAtivo;
    String nome;
    String descricao;
    Boolean ehImportante;

    public BotaoComando(Comando comandoAtivo, String nome, String desc, boolean importante) {
        this.comandoAtivo = comandoAtivo;
        this.nome = nome;
        this.descricao = desc;
        this.ehImportante = importante;
    }

    @Override
    public void configurarComando(Comando comandoAtivo) {

    }

    @Override
    public void executarComando() {
        comandoAtivo.executar();
    }

    @Override
    public boolean botaoImportante() {
        return ehImportante;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean possuiComponente() {
        return false;
    }

    @Override
    public Component getComponente() {
        return null;
    }
}
