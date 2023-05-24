package DiccionarioPalabra;

import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaDinamicaClave;
import estructurasLineales.ListaEstatica;
import estructurasLineales.auxiliares.NodoClave;
import utilerias.comunes.Menu;

/**
 * Clase que simula a un diccionario.
 * @author Gerardo Rivas Delgado.
 * @version 1.0
 */
public class Diccionario {
    protected ListaDinamicaClave conceptos;

    /**
     * Constructor de la clase del Diccionario,<br/>
     * no requiere argumentos.
     */
    public Diccionario(){
        conceptos = new ListaDinamicaClave();
    }

    /**
     * Metodo que muestra al menu. Simplemente.
     */
    public void muestraMenu(){
        int resultado = Menu.muestraMenuCuatroOpciones("Dar de alta un concepto", "Buscar conceptos", "Buscar Palabras clave");
        switch (resultado){
            case 1: darAlta(); muestraMenu(); break;
            case 2: buscarConcepto(); muestraMenu(); break;
            case 3: buscarPalabraClave(); muestraMenu(); break;
            case 0: SalidaPorDefecto.terminal("\nSaliendo..."); break;
            default: SalidaPorDefecto.terminal("\nIngresa una opcion valida.\n"); muestraMenu(); break;
        }
    }

    /**
     * Metodo que da de alta a una definicion
     */
    public void darAlta(){
        SalidaPorDefecto.terminal("Dame el Concepto: ");
        String concepto = EntradaPorDefecto.consolaCadenas();
        SalidaPorDefecto.terminal("Dame la Definicion: ");
        String definicion = EntradaPorDefecto.consolaCadenas();
        SalidaPorDefecto.terminal("Dame el Sinonimo: ");
        String sinonimo = EntradaPorDefecto.consolaCadenas();
        SalidaPorDefecto.terminal("Ingresa un Uso: ");
        String uso = EntradaPorDefecto.consolaCadenas();
        int resultado = altaConceptos(concepto, definicion, sinonimo, uso);
        if (resultado == 1){
            SalidaPorDefecto.terminal("\n"+concepto+" agregado.\n\n");
        }else{
            SalidaPorDefecto.terminal("No se ha podido agruegar a "+concepto+".\n");
        }
    }

    /**
     * Permite dar de alta un concepto al diccionario.
     * @param concepto Nombre del concepto a agregar.
     * @param definicion Definicion del concepto.
     * @return regresa true si pudo ser agregado.
     */
    private int altaConceptos(String concepto, String definicion, String sinonimo, String uso){
        ListaEstatica registro = new ListaEstatica(5);
        registro.agregar(definicion);
        registro.agregar(sinonimo);
        registro.agregar(uso);
        return conceptos.agregar(concepto, registro);
    }

    /**
     * Metodo que le permite al usuario buscar un concepto ingresado.
     */
    public void buscarConcepto(){
        SalidaPorDefecto.terminal("\nDame el concepto a buscar: ");
        String concepto = EntradaPorDefecto.consolaCadenas();
        buscarConceptos(concepto);
    }

    /**
     * Permite buscar un concepto al diccionario.
     * @param concepto Nombre del concepto a agregar.
     * @return regresa true si pudo ser agregado.
     */
    private void buscarConceptos(String concepto){
        ListaEstatica conceptoObtenido = (ListaEstatica) conceptos.buscar(concepto);
        String definicion = ": "+(String) conceptoObtenido.obtener(0);
        String deftotal = concepto+definicion;
        String sinonimo = (String) conceptoObtenido.obtener(1);
        String uso = (String) conceptoObtenido.obtener(2);
        if ((sinonimo.equals("") == false)){
            String sin = ". Sin: "+(String) conceptoObtenido.obtener(1);
            deftotal += sin;
        }
        if (uso.equals("") == false){
            String use = ". Uso: "+(String) conceptoObtenido.obtener(2);
            deftotal += use;
        }
        deftotal += ".\n";
        SalidaPorDefecto.terminal("\n"+deftotal);
    }


    /**
     * Metodo que invoca al metodo recursivo que busca<br/>
     * si hay una palabra clave en las definiciones.
     */
    public void buscarPalabraClave(){
        SalidaPorDefecto.terminal("\nDame la palabra a buscar: ");
        String palabraClave = EntradaPorDefecto.consolaCadenas();
        verificarSiHayPalabraClaveEnNodo(palabraClave, conceptos.inicializarIterador());
    }

    /**
     * Metodo RECURSIVO que verifica si en el<br/>
     * nodo actual hay alguna palabra clave.
     * @param palabraClave Palabra a buscar.
     * @param nodoAVerificar Nodo en el que se va a verificar si esta la palabra.
     */
    private void verificarSiHayPalabraClaveEnNodo(String palabraClave, NodoClave nodoAVerificar){
        if (nodoAVerificar != null){
            String concepto = (String) nodoAVerificar.getClave();
            ListaEstatica registro = (ListaEstatica) nodoAVerificar.getInfo();
            String definicion = (String) registro.obtener(0);
            String sinonimo = (String) registro.obtener(1);
            String uso = (String) registro.obtener(2);
            if ((concepto.contains(palabraClave) == true) || (definicion.contains(palabraClave) == true) || (sinonimo.contains(palabraClave) == true) || (uso.contains(palabraClave) == true)){
                buscarConceptos(concepto);
                SalidaPorDefecto.terminal("\n");
            }
            verificarSiHayPalabraClaveEnNodo(palabraClave, nodoAVerificar.getLigaDer());
        }
    }
}
