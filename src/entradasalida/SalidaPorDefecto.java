package entradasalida;

public class SalidaPorDefecto {

    public static void terminal(String cadena){
        System.out.print(cadena);
    }

    public static void error(String cadena){
        System.err.print(cadena);
    }
}
