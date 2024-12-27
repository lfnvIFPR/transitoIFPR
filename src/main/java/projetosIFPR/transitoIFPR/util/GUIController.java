package projetosIFPR.transitoIFPR.util;

import com.formdev.flatlaf.FlatDarculaLaf;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class GUIController {

    private ArrayList<String> fontes = new ArrayList<>(Arrays.asList(
            "ttf/JetBrainsMono-Bold.ttf",
            "ttf/JetBrainsMono-BoldItalic.ttf",
            "ttf/JetBrainsMono-ExtraBold.ttf",
            "ttf/JetBrainsMono-ExtraBoldItalic.ttf",
            "ttf/JetBrainsMono-ExtraLight.ttf",
            "ttf/JetBrainsMono-ExtraLightItalic.ttf",
            "ttf/JetBrainsMono-Italic.ttf",
            "ttf/JetBrainsMono-Light.ttf",
            "ttf/JetBrainsMono-LightItalic.ttf",
            "ttf/JetBrainsMono-Medium.ttf",
            "ttf/JetBrainsMono-MediumItalic.ttf"));

    public GUIController() {
        FlatDarculaLaf.setup();
        carregar();
    }

    private void carregar() {
        for (String fonte: fontes) {
            try {
                InputStream fontStream = getClass().getClassLoader().getResourceAsStream(fonte);
                if (fontStream != null) {
                    Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    ge.registerFont(customFont);
                    //System.out.println(Arrays.toString(ge.getAvailableFontFamilyNames()));
                }
            } catch (IOException | FontFormatException e) {
                System.out.println("Não foi possível ler a fonte " + fonte);
            }
        }
    }
}
