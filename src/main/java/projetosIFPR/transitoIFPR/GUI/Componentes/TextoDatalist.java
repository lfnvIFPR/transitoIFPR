package projetosIFPR.transitoIFPR.GUI.Componentes;

import javax.swing.*;

public class TextoDatalist extends JComboBox<String> {

    public TextoDatalist(String[] nomes) {
        super(nomes);
        setEditable(true);
    }

}
