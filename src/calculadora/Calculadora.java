package calculadora;

import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;
import estructurasnolineales.Matriz2;
import utilerias.matematicas.ExpresionesMatematicas;

public class Calculadora {
    protected String expresion;
    protected Matriz2 valoresVariables;
    public Calculadora(){
        pedirExpresion();
    }

    /**
     * Metodo en la calculadora que obtiene la expresion matematica
     */
    protected void pedirExpresion(){
        SalidaPorDefecto.terminal("Ingrese una expresion para resolver:\n");
        expresion = EntradaPorDefecto.consolaCadenas();
        valoresVariables = crearMatrizDeVariables(expresion);
        if (verificarMatriz() == true){ //si tiene variables validas
            ponerVariablesEnMatriz(expresion);
            pedirValoresDeVariables(valoresVariables);
            expresion = reemplazarVariables(expresion);
            expresion = ExpresionesMatematicas.infijaAPostfija(expresion);
            SalidaPorDefecto.terminal(expresion+"\n");
            SalidaPorDefecto.terminal("El resultado es: "+ExpresionesMatematicas.evaluarPostfija(expresion)+".\n");
        }else if (esExpresionSoloNumeros(expresion) == true){ //si solo es una expresion de numeros
            expresion = ExpresionesMatematicas.infijaAPostfija(expresion);
            SalidaPorDefecto.terminal("El resultado es: "+ExpresionesMatematicas.evaluarPostfija(expresion)+".\n");
        } else { //si tiene variables invalidas
            SalidaPorDefecto.terminal("Tienes variables invalidas, verifica por favor.\n");
        }
    }

    /**
     * Metodo que crea una matriz del tamanio de las variables<br/>
     * validas en la expresion.
     * @param expresion Cadena de expresion.
     * @return Regresa matriz si hay variables validas. Si no, regresa null.
     */
    public Matriz2 crearMatrizDeVariables(String expresion){
        int contador = 0;
        String variableTemporal = "";
        for (int cadaToken = 0; cadaToken<expresion.length(); cadaToken++){
            char token = expresion.charAt(cadaToken);
            if ((Character.isDigit(token)) || (Character.isLetter(token)) || (token == '$') || (token == '_')){ //analizamos si "Cada Palabra" es variable valida
                variableTemporal += token;
            }
            if ( ((ExpresionesMatematicas.esOperando(""+token)) == false) || (token == ' ')){
                if (ExpresionesMatematicas.esVariableValida(variableTemporal) == true){
                    contador++;
                }
                variableTemporal = "";
            }
        }
        if (ExpresionesMatematicas.esVariableValida(variableTemporal) == true){
            contador++;
        }
        if (contador > 0){
            Matriz2 matrizDeValores = new Matriz2(contador, 2, 0.0);
            return matrizDeValores;
        }else{
            return null;
        }
    }

    /**
     * Metodo que verifica si hay variables.
     */
    protected boolean verificarMatriz(){
        if (valoresVariables != null){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que identifica las variables y las guarda en una matriz.
     * @param expresion Expresion aritmetica a obtener variables.
     */
    public void ponerVariablesEnMatriz(String expresion){
        int contador = 0;
        String variableTemporal = "";
        for (int cadaToken = 0; cadaToken<expresion.length(); cadaToken++){
            char token = expresion.charAt(cadaToken);
            if ((Character.isDigit(token)) || (Character.isLetter(token)) || (token == '$') || (token == '_')){ //analizamos si "Cada Palabra" es variable valida
                variableTemporal += token;
            }
            if ( ((ExpresionesMatematicas.esOperando(""+token)) == false) || (token == ' ')){
                if (ExpresionesMatematicas.esVariableValida(variableTemporal) == true){
                    valoresVariables.cambiar(contador, 0, variableTemporal);
                    contador++;
                }
                variableTemporal = "";
            }
        }
        if (ExpresionesMatematicas.esVariableValida(variableTemporal) == true){
            valoresVariables.cambiar(contador, 0, variableTemporal);
        }
    }

    /**
     * Metodo interno que pide los valores de las variables en una matriz,<br/>
     * para guardarlos posteriormente.
     * @param matriz Matriz de valores después de haber sido llenada.
     */
    protected void pedirValoresDeVariables(Matriz2 matriz){
        for (int variable = 0; variable < matriz.obtenerRenglones(); variable++){
            SalidaPorDefecto.terminal("Ingrese el valor de la variable '"+matriz.obtener(variable, 0)+"': ");
            double valor = EntradaPorDefecto.consolaDouble();
            matriz.cambiar(variable, 1, valor);
        }
    }

    /**
     * Metodo que verifica si una expresion esta compuesta por solo numeros.
     * @param expresion Expresion a validar.
     * @return Si al menos tiene 1 letra, se regresa false.
     */
    protected boolean esExpresionSoloNumeros(String expresion){
        boolean numeros = false;
        for (int cadaToken = 0; cadaToken<expresion.length(); cadaToken++){
            char token = expresion.charAt(cadaToken);
            if (Character.isDigit(token) || (ExpresionesMatematicas.esOperando(""+token)) == false){
                numeros = true;
            } else {
                numeros = false;
                return numeros;
            }
        }
        return numeros;
    }

    /**
     * Metodo que reemplaza las variables de una ecuacion
     * @param expresion Expresion ya dada al principio
     * @return Regresa una string con valores reemplazados
     */
    protected String reemplazarVariables(String expresion){
        String nuevaExpresion = "";
        String variableAComparar = "";
        int numeroDeVariable = 0;
        boolean esVariable = false;
        for (int cadaToken = 0; cadaToken<expresion.length(); cadaToken++){
            char token = expresion.charAt(cadaToken);
            if ((Character.isLetter(token)) || (token == '$') || (token == '_') || ((Character.isDigit(token) == true) && (esVariable == true))){ //analizamos si "Cada Palabra" es variable valida
                esVariable = true;
                variableAComparar += token;
            }else if ((ExpresionesMatematicas.esOperando(""+token) == false) || (token == ' ')){ //si ya hay un operador o espacio, verificamos que la variable esta en la matriz
                if(esVariable == true){
                    esVariable = false;
                    String variableEnMatriz = (String) valoresVariables.obtener(numeroDeVariable ,0);
                    if (variableAComparar.equals(variableEnMatriz)){ //si ambas son iguales..
                        double valorDeVariable = (double) valoresVariables.obtener(numeroDeVariable,1);
                        if(valorDeVariable/1 == (int) valorDeVariable){
                            int valor = (int) valorDeVariable;
                            nuevaExpresion += String.valueOf(valor);
                            numeroDeVariable++;
                        } else { //esto es para evitar que haya numeros en la forma
                            nuevaExpresion += "(";
                            int valorEntero = (int) valorDeVariable;
                            nuevaExpresion += String.valueOf(valorEntero); //agregamos el valor entero
                            nuevaExpresion += "+(";
                            double resto = valorDeVariable-valorEntero;
                            String decimas = String.valueOf(resto); //obtnenemos las decimas
                            String nuevoNumero = "";
                            String entre = "1";
                            for (int cadaDecimal = 0; cadaDecimal<decimas.length(); cadaDecimal++){
                                char numero = decimas.charAt(cadaDecimal);
                                if ((numero != '0')){
                                    if (numero !='.'){
                                        nuevoNumero += numero;
                                        entre += "0";
                                    }
                                }
                            }
                            nuevaExpresion += nuevoNumero+"/"+entre;
                            nuevaExpresion += "))";
                            numeroDeVariable++;
                        }
                    }
                }
                nuevaExpresion += token;
                variableAComparar = "";
            } else {
                if(esVariable == false){
                    nuevaExpresion += token;
                }
            }
        }
        if (variableAComparar.length() > 0){
            String variableEnMatriz = (String) valoresVariables.obtener(numeroDeVariable ,0);
            if (variableAComparar.equals(variableEnMatriz)){ //si ambas son iguales..
                double valorDeVariable = (double) valoresVariables.obtener(numeroDeVariable,1);
                nuevaExpresion += String.valueOf(valorDeVariable);
            }
        }
        return nuevaExpresion;
    }

}
