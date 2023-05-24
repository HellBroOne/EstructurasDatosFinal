package utilerias.matematicas;

import entradasalida.SalidaPorDefecto;

/**
 * Clase utilitaria con metodos de verificacion<br/>
 * de numeros de manera recursiva.
 * @author Gerardo Rivas Delgado
 * @version 1.0
 */
public class TipoNumero {

    /**
     * Metodo que permite verificar si un numero es primo.
     * @param numero Numero a verificar.
     * @return Regresa true si es primo, false si no.
     */
    public static boolean esPrimo(int numero){
        return verificarPrimo(numero, numero-1);
    }

    /**
     * Metodo que es privado pero verifica<br/>
     * el primo de un numero respecto al anterior.
     * @param numero Numero del cual se saca el primo.
     * @param anterior Numero menos uno.
     * @return Regresa true si si funciono, false si no.
     */
    private static boolean verificarPrimo(int numero, int anterior){
        boolean primo = true;
        if (anterior!=1){
            int resultado = numero % anterior;
            if (resultado == 0){
                return false;
            } else {
                return verificarPrimo(numero, anterior-1);
            }
        }else{
            return primo;
        }
    }

    /**
     * Metodo que verifica si un numero es binario.
     * @param numero Numero binario en long.
     * @return Regresa true si si es binario, false si no.
     */
    public static boolean esBinario(long numero){
        boolean binario = true;
        String numeroBinario = String.valueOf(numero);
        char ultimodigito = numeroBinario.charAt(numeroBinario.length()-1);
        if ((ultimodigito == '0') || (ultimodigito == '1')){
            if (numeroBinario.length() != 1){
                numeroBinario = numeroBinario.substring(0, numeroBinario.length() - 1);
                long numeroNuevo = Long.parseLong(numeroBinario);
                return esBinario(numeroNuevo);
            } else {
                return binario;
            }
        } else {
            return false;
        }
    }

    /**
     * Metodo que obtiene el maximo comun<br/>
     * divisor de dos numeros.
     * @param numero1 Numero A.
     * @param numero2 Numero B.
     * @return Devuelve el maximo comun divisor de ambos.
     */
    public static int maximoComunDivisor(int numero1, int numero2){
        if (numero2 < numero1){ //Por regla del algoritmo de euclides
            if (numero2 != 0){
                int residuo = numero1%numero2;
                return maximoComunDivisor(numero2, residuo);
            }else{
                return numero1;
            }
        }else{
            return -1;
        }
    }

    /**
     * Metodo que mediante un numero se<br/>
     * obtiene el equivalente en Hexadecimal.
     * @param numero Numero a obtener hexadecimal.
     * @return Regresa la cadena de Hexadecimal.
     */
    public static String convertirAHexadecimal(int numero){
        return aHexadecimal(numero, "");
    }

    /**
     * Metodo privado que convierte un numero a Hexadecimal.<br/>
     * Es privado ya que se tiene que seguir almacenando el<br/>
     * numero hexadecimal que se va formando para poder<br/>
     * irle agregando el resto.
     * @param numero Numero a convertir que se pasa para dividirlo entre 16.
     * @param cadena Cadena que se va a ir formado cada vez.
     * @return Regresa una cadena al final.
     */
    private static String aHexadecimal(int numero, String cadena){
        if (numero != 0){
            int residuo = numero%16;
            numero = numero/16;
            char valorHex = obtenerDigitoPorResiduo(residuo);
            cadena = valorHex+cadena;
            return aHexadecimal(numero, cadena);
        } else {
            return cadena;
        }
    }

    /**
     * Metodo que permite obtener un valor<br/>
     * hexadecimal mediante el residuo.
     * @param numero Numero del residuo a verificar.
     * @return Regresa el valor del resto en hexadecimal.
     */
    public static char obtenerDigitoPorResiduo(int numero){
        if (numero >= 0 && numero < 16){
            if (numero >= 0 && numero <= 9){
                //char retorno = (char) (numero+'0');
                return (char) (numero+'0');
            } else if (numero == 10){
                return 'A';
            } else if (numero == 11){
                return 'B';
            } else if (numero == 12){
                return 'C';
            } else if (numero == 13){
                return 'D';
            } else if (numero == 14){
                return 'E';
            } else {
                return 'F';
            }
        } else {
            return '\0';
        }
    }

    /**
     * Metodo que permite convertir un numero a binario.
     * @param numero Numero a convertir.
     * @return Regresa el numero Convertido.
     */
    public static String convertirABinario(int numero){
        return aBinario(numero, "");
    }

    /**
     * Metodo privado que convierte un numero entero a un binario.<br/>
     * Igualmente es privado por que la cadena siempre se debe mandar vacia.
     * @param numero Numero a convertir.
     * @param cadena Cadena actual que se va pasando para concatenar.
     * @return Regresa el numero binario como cadena.
     */
    private static String aBinario(int numero, String cadena){
        if (numero != 1){
            int resto = numero%2;
            if (resto != 0){
                numero = numero - 1;
            }
            numero = numero/2;
            cadena = resto+cadena;
            return aBinario(numero, cadena);
        }else{
            return '1'+cadena;
        }
    }
}
