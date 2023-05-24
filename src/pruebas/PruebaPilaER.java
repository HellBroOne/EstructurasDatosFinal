package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasnolineales.Matriz2;
import utilerias.matematicas.ExpresionesMatematicas;

public class PruebaPilaER {
    public static void main(String[] args) {
        SalidaPorDefecto.terminal("Ejecutando la operacion con ");
        //SalidaPorDefecto.terminal("a 8 + 3 x * 4 z ^ / -, donde a=1, x=2, z=2, Resultado = "+ ExpresionesMatematicas.evaluarPostfija("18+32*42^/-")+"\n");
        //SalidaPorDefecto.terminal("a 8 + 3 x * 4 z ^ / -, donde a=1, x=2, z=2, Resultado = "+ ExpresionesMatematicas.evaluarPrefija("-+18/*32^42")+"\n");
        // Metodo solo para probar variables validas: SalidaPorDefecto.terminal("Probando si las siguientes son variables validas.\n1variable: "+ExpresionesMatematicas.esVariableValida("1variable")+"\nvariable: "+ExpresionesMatematicas.esVariableValida("variable")+"\n_variable: "+ExpresionesMatematicas.esVariableValida("_variable")+"\n$variable: "+ExpresionesMatematicas.esVariableValida("$variable")+"\nvariable1: "+ExpresionesMatematicas.esVariableValida("variable1")+"\n");
        String resultado = ExpresionesMatematicas.infijaAPostfija("3+(3+(5/10))");
        SalidaPorDefecto.terminal("\n(6+2)*3/2^2-4 Ejecutando infija a postfija: "+resultado+"\n");
        SalidaPorDefecto.terminal("Evaluando: "+ExpresionesMatematicas.evaluarPostfija("33510/++")+"\n");
    }
}
