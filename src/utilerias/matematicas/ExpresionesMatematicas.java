package utilerias.matematicas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.PilaEstatica;
import estructurasnolineales.Matriz2;

public class ExpresionesMatematicas {

    /**
     * Metodo que convierte una expresion infija a una Postfija.
     * @param infija Cadena de expresiones infijas.
     * @return Regresa una cadena en postfija
     */
    public static String infijaAPostfija(String infija){
        PilaEstatica pila = new PilaEstatica(infija.length());
        String epos = "";
        //1. Tokenizar de izq a Der
        for (int cadaToken = 0; cadaToken<infija.length(); cadaToken++) {
            char token = infija.charAt(cadaToken);
            //2. Si es operando, mandarlo a EPOS
            if ((esOperando("" + token) == true) ) {
                epos += token;
            } else if (token == '('){ //si es ( ponerlo
                pila.poner(token);
            } else if (token == ')') { //si es ), vaciar
                char valor = (char) pila.quitar();
                while(valor != '('){
                    epos += valor;
                    valor = (char) pila.quitar();
                }
            } else { //si es operando
                if (pila.vacio() == false){
                    char ultimoValor = (char) pila.verTope();
                    if (obtenerPrioridad(ultimoValor) > obtenerPrioridad(token)){ //si en la pila hay de mayor prioridad..
                        if((ultimoValor != '(')) {
                            while (pila.vacio() == false) {
                                char valor = (char) pila.quitar();
                                epos += valor;
                            }
                            pila.poner(token);
                        }else {
                            pila.poner(token);
                        }
                    } else if (obtenerPrioridad(ultimoValor) < obtenerPrioridad(token)){ //si el de la pila es de menor prioridad
                        pila.poner(token);
                    } else { // si son de la misma prioridad, cambiar
                        char valor = (char) pila.quitar();
                        epos += valor;
                        pila.poner(token);
                    }
                } else {
                    pila.poner(token);
                }
            }

        }
        //6. cuando no hay operadores, sacalos de la pila y mandalos a epos
        while(pila.vacio() == false){
            char valor = (char) pila.quitar();
            epos += valor;
        }
        return epos;
    }

    public static Double evaluarPostfija(String postfija){
        PilaEstatica pila = new PilaEstatica(postfija.length());
        // a 8 + 3 x * 4 z ^ / -
        // 0 1 2 3 4 5 6 7 8 9 10 posiciones
        for (int cadaToken = 0; cadaToken<postfija.length(); cadaToken++){
            //1. Tokenizar de izq a der
            char token = postfija.charAt(cadaToken);
            //2. Si es operando, meter a la pila
            if (esOperando(""+token) == true){
                boolean resultadoPila = pila.poner(""+token);
                if (resultadoPila == false){
                    return null;
                }
            } else {
                //3. Si es operador, sacar dos operandos
                //(el primero es op2).
                String operando2 = (String)pila.quitar();
                String operando1 = (String)pila.quitar();

                if (operando1 == null || operando2 == null){
                    return null;
                } else {
                    //Aplicar operacion con ellos
                    Double resultadoParcial = operacion(Double.parseDouble(operando1), Double.parseDouble(operando2), token);
                    //Meter el resultado en la pila
                    if (resultadoParcial == null){
                        return null;
                    } else {
                        boolean resultadoPila = pila.poner(""+resultadoParcial);
                        if (resultadoPila == false){
                            return null;
                        }
                    }
                }
            }
        }
        //4. El resultado final esta en la pila
        String resultadoFinal = (String) pila.quitar();
        if (resultadoFinal != null){
            return Double.parseDouble(resultadoFinal);
        }else{
            return null;
        }
    }

    public static Double evaluarPrefija(String prefija){
        PilaEstatica pila = new PilaEstatica(prefija.length());
        // a 8 + 3 x * 4 z ^ / -
        // 0 1 2 3 4 5 6 7 8 9 10 posiciones
        for (int cadaToken = prefija.length()-1; cadaToken >= 0; cadaToken--){
            //1. Tokenizar de izq a der
            char token = prefija.charAt(cadaToken);
            //2. Si es operando, meter a la pila
            if (esOperando(""+token) == true){
                boolean resultadoPila = pila.poner(""+token);
                if (resultadoPila == false){
                    return null;
                }
            } else {
                //3. Si es operador, sacar dos operandos
                //(el primero es op1).
                String operando1 = (String)pila.quitar();
                String operando2 = (String)pila.quitar();

                if (operando1 == null || operando2 == null){
                    return null;
                } else {
                    //Aplicar operacion con ellos
                    Double resultadoParcial = operacion(Double.parseDouble(operando1), Double.parseDouble(operando2), token);
                    //Meter el resultado en la pila
                    if (resultadoParcial == null){
                        return null;
                    } else {
                        boolean resultadoPila = pila.poner(""+resultadoParcial);
                        if (resultadoPila == false){
                            return null;
                        }
                    }
                }
            }
        }
        //4. El resultado final esta en la pila
        String resultadoFinal = (String) pila.quitar();
        if (resultadoFinal != null){
            return Double.parseDouble(resultadoFinal);
        }else{
            return null;
        }
    }

    public static Double operacion(double operando1, double operadondo2, char operador){
        if (operador == '+'){
            return operando1+operadondo2;
        } else if (operador == '-'){
            return operando1-operadondo2;
        } else if (operador == '*'){
            return operando1*operadondo2;
        } else if (operador == '/'){
            if (operadondo2 == 0){
                return null;
            }
            return operando1/operadondo2;
        } else if (operador == '^'){
            return Math.pow(operando1,operadondo2);
        } else if (operador == '%'){
            return operando1%operadondo2;
        } else {
            return null;
        }
    }

    public static boolean esOperando(String token){
        if (token.equalsIgnoreCase("+") == true){
            return false;
        } else if (token.equalsIgnoreCase("-") == true){
            return false;
        } else if (token.equalsIgnoreCase("/") == true){
            return false;
        } else if (token.equalsIgnoreCase("^") == true){
            return false;
        } else if (token.equalsIgnoreCase("%") == true){
            return false;
        } else if (token.equalsIgnoreCase("(") == true){
            return false;
        } else if (token.equalsIgnoreCase(")") == true){
            return false;
        } else if (token.equalsIgnoreCase("*") == true){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Metodo que verifica que una String sea una variable valida<br/>
     * en el programa de la calculadora.
     * @param variable Cadena de texto que indique una variable.
     * @return Regresa true si una variable es valida, o false si no.
     */
    public static boolean esVariableValida(String variable){
        char primerToken;
        if(variable.length() > 0){
            primerToken = variable.charAt(0);
        } else {
            return false;
        }
        if ((Character.isLetter(primerToken) == true) || (primerToken == '$') || (primerToken == '_')){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que devuelve la prioridad de un operador.
     * @param operador1 Operador a obtener prioridad en char.
     * @return Regresa un número correspondiente a su prioridad.
     */
    public static int obtenerPrioridad(char operador1){
        if((operador1 == '(') || (operador1 == ')') || (operador1 == '[') || (operador1 == ']')){
            return 4;
        } else if (operador1 == '^'){
            return 3;
        } else if ((operador1 == '*') || (operador1 == '/')) {
            return 2;
        } else if ((operador1 == '+') || (operador1 == '-')) {
            return 1;
        } else {
            return -1;
        }
    }
}
