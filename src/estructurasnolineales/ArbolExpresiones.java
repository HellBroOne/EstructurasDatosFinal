package estructurasnolineales;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.PilaDinamica;
import estructurasLineales.PilaEstatica;
import estructurasLineales.auxiliares.Nodo;
import estructurasLineales.auxiliares.NodoDoble;
import utilerias.matematicas.ExpresionesMatematicas;

public class ArbolExpresiones extends ArbolBinario{

    public void crearArbolInfija(String infija){
        //1. Convertir la expresion infija en postfija.
        //llamar al metodo que convierte infija a postfija
        //llamar a creararbolPostfija
    }

    public boolean crearArbolPostfija(String postfija){
        PilaEstatica pila = new PilaEstatica(postfija.length());
        //1. Convertir la expresión infija a postfija.
        //postfija = ExpresionesMatematicas.infijaAPostfija(postfija);
        //2. Tokenizar la expresión i -> d.
        for (int posToken = 0; posToken < postfija.length(); posToken++){
            char token = postfija.charAt(posToken);
            //3. Si es operando, crear nodo (con el token) y meterlo en la pila.
            if (ExpresionesMatematicas.esOperando(token+"") == true){
                NodoDoble nuevoNodo = new NodoDoble(token);
                if (nuevoNodo != null){
                    pila.poner(nuevoNodo);
                } else {
                    return false;
                }
            } else {
                //4. Si es operador, sacar dos operandos de la pila (el primero es operando 2).
                // Crear nodo con token enlazados de los 2 operandos metiendo despues en pila
                NodoDoble nuevoNodo = new NodoDoble(token);
                NodoDoble operando2 = (NodoDoble) pila.quitar();
                NodoDoble operando1 = (NodoDoble) pila.quitar();

                if (nuevoNodo != null && (operando1 != null && operando2 != null)){
                    nuevoNodo.setLigaIzq(operando1);
                    nuevoNodo.setLigaDer(operando2);
                    pila.poner(nuevoNodo);
                } else {
                    return false;
                }
            } //operando y operador
        } //for
        //5. La raiz del arbol esta en la pila.
        NodoDoble nodoRaiz = (NodoDoble) pila.quitar();
        if (nodoRaiz != null){
            raiz = nodoRaiz;
            return true;
        }else{
            return false;
        }
    }

    public boolean crearArbolPrefija(String prefija){
        PilaEstatica pila = new PilaEstatica(prefija.length());
        //1. Convertir la expresión infija a postfija.
        //postfija = ExpresionesMatematicas.infijaAPostfija(postfija);
        //2. Tokenizar la expresión i -> d.
        for (int posToken = prefija.length()-1; posToken >= 0; posToken--){
            char token = prefija.charAt(posToken);
            //3. Si es operando, crear nodo (con el token) y meterlo en la pila.
            if (ExpresionesMatematicas.esOperando(token+"") == true){
                NodoDoble nuevoNodo = new NodoDoble(token);
                if (nuevoNodo != null){
                    pila.poner(nuevoNodo);
                } else {
                    return false;
                }
            } else {
                //4. Si es operador, sacar dos operandos de la pila (el primero es operando 2).
                // Crear nodo con token enlazados de los 2 operandos metiendo despues en pila
                NodoDoble nuevoNodo = new NodoDoble(token);
                NodoDoble operando1 = (NodoDoble) pila.quitar();
                NodoDoble operando2 = (NodoDoble) pila.quitar();

                if (nuevoNodo != null && (operando1 != null && operando2 != null)){
                    nuevoNodo.setLigaIzq(operando1);
                    nuevoNodo.setLigaDer(operando2);
                    pila.poner(nuevoNodo);
                } else {
                    return false;
                }
            } //operando y operador
        } //for
        //5. La raiz del arbol esta en la pila.
        NodoDoble nodoRaiz = (NodoDoble) pila.quitar();
        if (nodoRaiz != null){
            raiz = nodoRaiz;
            return true;
        }else{
            return false;
        }
    }
}
