package projetosIFPR.transitoIFPR.utilidade;

public class Palavras {
    public static String capitalizar(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        str = str.toLowerCase();

        char primeiraLetra = Character.toUpperCase(str.charAt(0));
        return primeiraLetra + str.substring(1);
    }

    public static String capitalizarTodas(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        StringBuilder sb = new StringBuilder();
        boolean palavraNova = true;
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                if (palavraNova) {
                    sb.append(Character.toUpperCase(c));
                    palavraNova = false;
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            } else {
                sb.append(c);
                palavraNova = true;
            }
        }
        return sb.toString();
    }
}
