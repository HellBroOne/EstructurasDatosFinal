package pruebas;

import entradasalida.SalidaPorDefecto;
import utilerias.matematicas.TipoNumero;

public class PruebasNumerosRecursivos {
    public static void main(String[] args) {
        SalidaPorDefecto.terminal("Verificando si el numero 7 es primo: "+ TipoNumero.esPrimo(7)+"\n");
        SalidaPorDefecto.terminal("Verificando si el numero 6 es primo: "+ TipoNumero.esPrimo(6)+"\n");
        SalidaPorDefecto.terminal("Verificando si el numero 3 es primo: "+ TipoNumero.esPrimo(3)+"\n");
        SalidaPorDefecto.terminal("\nVerificado si el numero 12311010 es binario: "+ TipoNumero.esBinario(12311010)+"");
        SalidaPorDefecto.terminal("\nVerificado si el numero 111010 es binario: "+ TipoNumero.esBinario(111010)+"\n");
        SalidaPorDefecto.terminal("\nObteniendo el MCD de 412 y 184: "+ TipoNumero.maximoComunDivisor(412, 184)+"");
        SalidaPorDefecto.terminal("\nObteniendo el MCD de 315 y 200: "+ TipoNumero.maximoComunDivisor(315, 200)+"\n");
        // ############# PRUEBAS DE RESIDUOS #################
        SalidaPorDefecto.terminal("\nObteniendo el valor del residuo de -1: "+ TipoNumero.obtenerDigitoPorResiduo(-1)+"");
        SalidaPorDefecto.terminal("\nObteniendo el valor del residuo de 1: "+ TipoNumero.obtenerDigitoPorResiduo(1)+"");
        // ############## ################# ##################
        SalidaPorDefecto.terminal("\nObteniendo la equivalencia de 65029 a Hexadecimal: "+TipoNumero.convertirAHexadecimal(65029)+"");
        SalidaPorDefecto.terminal("\nObteniendo la equivalencia de 5326 a Hexadecimal: "+TipoNumero.convertirAHexadecimal(32)+"\n");
        SalidaPorDefecto.terminal("\nObteniendo la equivalencia de 150 a Binario: "+TipoNumero.convertirABinario(150)+"");
        SalidaPorDefecto.terminal("\nObteniendo la equivalencia de 23 a Binario: "+TipoNumero.convertirABinario(23)+"");
        SalidaPorDefecto.terminal("\nObteniendo la equivalencia de 1512 a Binario: "+TipoNumero.convertirABinario(1512)+"\n");
    }
}
