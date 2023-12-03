package servicos;

public class Formatacao {

    public static String dataParaUI(String data) {

        data = data.replaceAll("-", "/");
        String[] s = data.split("/");
        String novaData = s[2] + "/" + s[1] + "/" + s[0];
        
        return novaData;

    }
}
