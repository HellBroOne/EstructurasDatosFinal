package utilerias.matematicas.polinomios;

import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaDinamica;
import estructurasLineales.auxiliares.Nodo;
import utilerias.matematicas.ExpresionesMatematicas;

/**
 * Clase que permite realizar diferentes<br/>
 * operaciones de un polinomio dado.
 */
public class OperacionesPolinomio {

    protected ListaDinamica polinomio;
    protected String expresion;
    protected double valor;
    public OperacionesPolinomio(){
        this.polinomio = new ListaDinamica();
        this.expresion = "";
        this.valor = 0.0;
    }

    /**
     * Metodo que permite agregar una expresion en cadena y valor de x.
     * @return Regresa int si se agrego correctamente.
     */
    public int agregarExpresion(){
        SalidaPorDefecto.terminal("Ingrese una expresion: ");
        expresion = EntradaPorDefecto.consolaCadenas();
        SalidaPorDefecto.terminal("Cual es el valor de x? ");
        valor = EntradaPorDefecto.consolaDouble();
        return dividirYAgregar("", 0);
    }

    /**
     * Metodo que guarda a un polinomio en la lista dinamica.
     * @param cadena Cadena a ir construyendo.
     * @param posicion Posicion actual del numero
     * @return Regresa 1 si se pudo agregar.
     */
    private int dividirYAgregar(String cadena, int posicion){
        if (posicion < expresion.length()){
            char caracter = expresion.charAt(posicion);
            if (ExpresionesMatematicas.esOperando(caracter+"") == false && (caracter != '^')){
                polinomio.agregar(cadena);
                cadena = caracter+"";
            } else if (caracter == 'X' || caracter == 'x' || caracter == '^' || Character.isDigit(caracter)){
                cadena += caracter;
            }
            return dividirYAgregar(cadena, posicion+1);
        }
        return polinomio.agregar(cadena);
    }

    //x^4+x^2-1
    //x^5+x^3+x-1

    /**
     * Metodo que permite imprimir un polinomio dado.
     */
    public void imprimirPolinomio(){
        Nodo temporal = (Nodo) polinomio.inicializarIterador();
        imprimirNodo(temporal);
    }

    /**
     * Metodo auxiliar que imprime al nodo de la lista de manera recursiva.
     * @param temporal Nodo actual.
     */
    private void imprimirNodo(Nodo temporal){
        if (temporal != null){
            SalidaPorDefecto.terminal(temporal.getInfo()+"");
            imprimirNodo(temporal.getLigaDer());
        }
    }

    /**
     * Metodo que se encarga de buscar un monomio.
     * @param monomio Cadena del monomio.
     * @return Regresa la cadena del especificado.
     */
    public String obtenerMonomio(String monomio){
        return identificarMonomio((Nodo)polinomio.inicializarIterador(), monomio);
    }

    /**
     * Metodo privado que identifica la info de un <br/>
     * monomio contenida en otra.
     * @param actual Nodo Actual a revisar.
     * @param monomio Monomio a buscar.
     * @return Regresa la cadena si se encuentra.<br/>
     * Si no, una cadena vacia.
     */
    private String identificarMonomio(Nodo actual, String monomio){
        String resultado = "";
        if (actual != null){
            if (actual.getInfo().toString().contains(monomio) == true){
                return actual.getInfo().toString();
            }
            resultado = identificarMonomio(actual.getLigaDer(), monomio);
        }
        return resultado;
    }

    /**
     * Metodo que convierte el polinomio a binario.
     * @return Regresa una cadena con el binario.
     */
    public String aBinario(){
        int exponente = identififcarMayorExponente(0, 0, false, "");
        return identificarSiEsExponente((Nodo) polinomio.inicializarIterador(), exponente, "");
    }

    /**
     * Metodo que verifica si un monomio esta en su posicion.
     * @param actual Nodo actual.
     * @param mayorExp Numero mayor del exponente.
     * @param cadenaRetorno Cadena que se va construyendo como retorno.
     * @return Regresa la cadena en binario.
     */
    private String identificarSiEsExponente(Nodo actual, int mayorExp, String cadenaRetorno){
        if (actual.getLigaDer() != null){
            if (mayorExp != 0){
                String cadenaAComparar = actual.getInfo().toString();
                String mayor = String.valueOf(mayorExp);
                if (mayorExp != 1){
                    if (cadenaAComparar.contains(mayor)){
                        cadenaRetorno += "1";
                        actual = actual.getLigaDer();
                    } else {
                        cadenaRetorno += "0";
                    }
                } else {
                    if (cadenaAComparar.contains("x") || cadenaAComparar.contains("X")){
                        cadenaRetorno += "1";
                        actual = actual.getLigaDer();
                    } else {
                        cadenaRetorno += "0";
                    }
                }
            } else {
                actual = actual.getLigaDer();
            }
            return identificarSiEsExponente(actual, mayorExp-1, cadenaRetorno);
        } else {
            return cadenaRetorno+"1";
        }
    }

    /**
     * Metodo privado que identifica al exponente mayor en la cadena.
     * @param posicion Posicion del caracter.
     * @param mayor Numero mayor actual.
     * @param esExponente Variable que identifica si el numero es parte de un exponente.
     * @param numero Cadena del numero del exponente mayor.
     * @return Regresa el numero mayor.
     */
    private int identififcarMayorExponente(int posicion, int mayor, boolean esExponente, String numero){
        if (posicion < expresion.length()){
            char caracter = expresion.charAt(posicion);
            if (caracter == '^'){
                esExponente = true;
            } else if (ExpresionesMatematicas.esOperando(caracter+"") == false) {
                esExponente = false;
                if (numero.equals("") == false){
                    int acomparar = Integer.parseInt(numero);
                    numero = "";
                    if (acomparar > mayor){
                        mayor = acomparar;
                    }
                }
            } else if (esExponente == true && Character.isDigit(caracter) == true){
                numero += caracter;
            }
            return identififcarMayorExponente(posicion+1, mayor, esExponente, numero);
        }
        return mayor;
    }

    /**
     * Metodo que resuelve al polinomio.
     * @return Regresa el valor del resultado.
     */
    public double resolverPolinomio(){
        ListaDinamica exponentes = new ListaDinamica();
        exponentes = obtenerExponentes(0, false, "", exponentes);
        return obtenerResultado(0, 0.0,  exponentes, 0);
    }

    /**
     * Metodo privado que resuelve al polinomio.
     * @param posicion Posicion del caracter.
     * @param resultado Resultado del polinomio.
     * @param exponentes Lista de Exponentes.
     * @param posicionExponente Posicion del exponente.
     * @return Regresa un double con el valor especifico
     */
    private double obtenerResultado(int posicion, double resultado, ListaDinamica exponentes, int posicionExponente){
        if (posicion < expresion.length() && posicion < polinomio.cantidad()){
            String monomio = (String) polinomio.obtener(posicion);
            if (expresion.contains(monomio) == true){ //esta en la expresion
                if (posicionExponente < exponentes.cantidad()){
                    int valorExp = (int) exponentes.obtener(posicionExponente);
                    if (monomio.contains("-") ){
                        resultado = resultado - Math.pow(valor, valorExp);
                    } else {
                        resultado = resultado + Math.pow(valor, valorExp);
                    }
                    posicionExponente++;
                } else {
                    if (monomio.contains("1") == true){
                        if (monomio.contains("-") ){
                            resultado = resultado - 1;
                        } else {
                            resultado = resultado + 1;
                        }
                    } else {
                        if (monomio.contains("-") ){
                            resultado = resultado - valor;
                        } else {
                            resultado = resultado + valor;
                        }
                    }
                }
            }
            return obtenerResultado(posicion+1, resultado, exponentes, posicionExponente);
        }
        return resultado;
    }

    /**
     * Metodo recursivo privado que obtiene los exponentes <br/>
     * de la expresion y los devuelve en lista dinamica.
     * @param posicion Posicion del caracter actual.
     * @param esExponente Variable que identifica si es exponente.
     * @param numero Numero a construir del exponente
     * @param retorno Lista a retornar.
     * @return Regresa una lista dinamica con los exponentes.
     */
    private ListaDinamica obtenerExponentes(int posicion, boolean esExponente, String numero, ListaDinamica retorno){
        if (posicion < expresion.length()){
            char caracter = expresion.charAt(posicion);
            if (caracter == '^'){
                esExponente = true;
            } else if (esExponente == true && Character.isDigit(caracter) == true) {
                numero += caracter;
            } else if (ExpresionesMatematicas.esOperando(caracter+"") == false){
                if (numero.equals("") == false){
                    int valor = Integer.valueOf(numero);
                    retorno.agregar(valor);
                }
                numero = "";
            }
            return obtenerExponentes(posicion+1, esExponente, numero, retorno);
        }
        return retorno;
    }

}
