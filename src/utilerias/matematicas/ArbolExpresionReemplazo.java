package utilerias.matematicas;

import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaDinamica;
import estructurasLineales.ListaDinamicaClave;
import estructurasLineales.auxiliares.NodoDoble;
import estructurasnolineales.ArbolBinario;

public class ArbolExpresionReemplazo {
    protected ArbolBinario arbol;
    protected ArbolBinario arbolcopia;
    protected ListaDinamicaClave operandos;
    protected ListaDinamica operadores;

    /**
     * Constructor de la clase Arbol Binario con expresiones.
     */
    public ArbolExpresionReemplazo(){
        arbol = new ArbolBinario();
        operandos = new ListaDinamicaClave();
        operadores = new ListaDinamica();
        arbolcopia = new ArbolBinario();
    }

    /**
     * Metodo que crea el arbol binario
     */
    public void crearArbol(){
        arbol.crearArbol();
        extraerOperador();
        SalidaPorDefecto.terminal("\nOperandos: ");
        operandos.imprimir();
        SalidaPorDefecto.terminal("\nOperadores: ");
        operadores.imprimir();
    }

    /**
     * Metodo que es llamado por crear Arbol<br/>
     * para extraer el operador automaticamente.
     */
    private void extraerOperador(){
        NodoDoble nodo = arbol.inicializarNodo();
        extraerOperador(arbol.inicializarNodo());
    }

    /**
     * Metodo recursivo y privado llamado por todos los nodos.
     * @param subraiz Subraiz actual.
     */
    private void extraerOperador(NodoDoble subraiz){
        if (subraiz != null){
            if (ExpresionesMatematicas.esOperando(subraiz.getInfo().toString()) == false){ //significa que es operador
                operadores.agregar(subraiz.getInfo());
            } else {
                SalidaPorDefecto.terminal("\nAgregue el valor de "+subraiz.getInfo()+": ");
                double valor = EntradaPorDefecto.consolaDouble();
                operandos.agregar(subraiz.getInfo(), valor);
            }
            extraerOperador(subraiz.getLigaIzq());
            extraerOperador(subraiz.getLigaDer());
        }
    }

    /**
     * Metodo que sustituye al arbol binario,<br/>
     * pidiendo valores si hay variables, o si no <br/>
     * solo los deja igual.
     * @return Regresa el arbol ya modificado.
     */
    public ArbolBinario generarArbol(){
        ArbolBinario retorno = arbol;
        generarArbolSustituido(retorno.inicializarNodo(), retorno);
        SalidaPorDefecto.terminal("\nOrignal: ");
        arbol.innorden();
        SalidaPorDefecto.terminal("\nRetorno: ");
        retorno.innorden();
        return retorno;
    }

    /**
     * Metodo que genera las sustituciones del arbol recursivamente.
     * @param subRaiz Subraiz actual.
     * @param retorno arbol a retornar (no funciona por referencias a memoria).
     */
    private void generarArbolSustituido(NodoDoble subRaiz, ArbolBinario retorno){
        if (subRaiz != null){
            if (ExpresionesMatematicas.esOperando(subRaiz.getInfo().toString()) == true){
                double valor = (double) operandos.buscar(subRaiz.getInfo());
                subRaiz.setInfo(valor);
            }
            generarArbolSustituido(subRaiz.getLigaIzq(), retorno);
            generarArbolSustituido(subRaiz.getLigaDer(), retorno);
        }
    }

    /**
     * Impresiones de recorridos del arbol post
     */
    public void postorden(){
        arbol.postorden();
    }

    public void inorden(){
        arbol.innorden();
    }

    public void preorden(){
        arbol.preorden();
    }

    private ArbolBinario getArbol() {
        return arbol;
    }

    private void setArbol(ArbolBinario arbol){
        this.arbol = arbol;
    }
}
