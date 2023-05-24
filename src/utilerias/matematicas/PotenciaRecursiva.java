package utilerias.matematicas;

public class PotenciaRecursiva {
    public static long potencia(long base, long exponente){
        if (exponente == 1){
            return base;
        } else {
            return base*potencia(base, exponente-1);
        }
    }
}
