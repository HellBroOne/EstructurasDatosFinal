package estructurasLineales;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.auxiliares.Nodo;
import estructurasLineales.auxiliares.NodoClave;
import estructurasnolineales.Matriz2;

public class ListaDinamicaClave {
    protected NodoClave primero;
    protected NodoClave ultimo;
    protected NodoClave nodoActual;

    public ListaDinamicaClave(){
        primero = null;
        ultimo = null;
    }

    /**
     * Metodo que verifica si la lista esta vacia.
     * @return Regresa True siendo que esta vacia<br/>
     * False si no esta.
     */
    public boolean vacia(){
        if (primero == null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Metodo que permite agregar un nodo clave a la lista.
     * @param clave Objeto clave
     * @param info
     * @return
     */
    public int agregar(Object clave, Object info){
        NodoClave nuevoNodoCl = new NodoClave(clave, info);
        if (nuevoNodoCl != null){ //si hay memoria
            if (vacia() == true){ //si la lista clave esta vacia, por ende no hay que verificar que se repita.
                primero = nuevoNodoCl;
                ultimo = nuevoNodoCl;
            }else{
                NodoClave verificado = obtenerNodoXClave(clave);
                if (verificado == null){//Si NO se repite.
                    ultimo.setLigaDer(nuevoNodoCl);
                    ultimo = nuevoNodoCl;
                } else { //si si se encontro hay que reemplazar la info.
                    verificado.setInfo(info);
                }
            }
            return 1;
        }else{
            return -1;
        }
    }

    /**
     * Metodo que permite eliminar un nodo mediante su clave.
     * @param clave Objeto clave del nodo a buscar.
     * @return
     */
    public Object eliminar(Object clave){
        if (vacia() == false){
            ListaEstatica nodos = buscarAnterior(clave);
            NodoClave anterior = (NodoClave) nodos.obtener(0);
            NodoClave buscado = (NodoClave) nodos.obtener(1);
            if (buscado != null){
                Object respaldo = buscado.getInfo();
                if (primero == ultimo){ //caso 1. Solo hay un nodo en la lista.
                    primero = null;
                    ultimo = null;
                } else if (buscado == primero){ //caso 2. El nodo solicitado es el inicial.
                    primero = primero.getLigaDer();
                } else if (buscado == ultimo){ //caso 3. El nodo solicitado a eliminar es el final
                    anterior.setLigaDer(null);
                    ultimo = anterior;
                } else { //caso 4. el nodo esta ENTRE otros nodos
                    NodoClave siguiente = buscado.getLigaDer(); //obtener el nodo siguiente
                    anterior.setLigaDer(siguiente); //enlazamos al nodo anterior con el de adelante.
                }
                return respaldo;
            } else {
                return null;
            }
        }else{
            return null; //no hay nada que eliminar, esta vacia
        }
    }

    /**
     * Metodo que elimina los nodos que coincidan<br/>
     * con la info pasada como parametro.
     * @param info Info del elemento a buscar.
     * @return Regresa True si se borraron uno o<br/>
     * mas elementos. False si ninguno se elimino.
     */
    public Object eliminarContenido(Object info){
        NodoClave temporal = primero;
        int eliminados = 0;
        Object respaldo = null;
        while (temporal != null){
            if (info == null){ //ya que la lista puede contener null, se verifica si la info que se solicita es null
                if (temporal.getInfo() == info == true){
                    respaldo = eliminar(temporal.getClave());
                    eliminados++;
                }
            } else if (temporal.getInfo().equals(info) == true){ //si no es null pues se compara con un equals
                respaldo = eliminar(temporal.getClave());
                eliminados++;
            }
            temporal = temporal.getLigaDer();
        }
        if (eliminados > 0){
            return respaldo;
        }else{
            return null;
        }
    }

    /**
     * Metodo que busca a un nodo y a su anterior nodo,<br/>
     * devuelve una lista con ambos nodos.
     * @param clave Clave del nodo a buscar.
     * @return Regresa una lista con el nodo buscado en .
     */
    public ListaEstatica buscarAnterior(Object clave){
        ListaEstatica ambosnodos = new ListaEstatica(2);
        NodoClave buscado = primero;
        NodoClave anterior = primero;
        while (buscado != null && (clave.equals(buscado.getClave()) == false) ){
            anterior = buscado;
            buscado = buscado.getLigaDer();
        }
        if (buscado != null){
            ambosnodos.agregar(anterior);
            ambosnodos.agregar(buscado);
        } else {
            ambosnodos.agregar(null);
            ambosnodos.agregar(null);
        }
        return ambosnodos;
    }

    /**
     * Metodo auxiliar que verifica y regresa un nodo completo.<br/>
     * Se implementa asi por que a diferencia del buscar, se requiere<br/>
     * obtener tod0 el nodo para poder realizar operaciones.
     * @param clave clave a buscar.
     * @return Regresa al nodo buscado o nulo si no esta.
     */
    private NodoClave obtenerNodoXClave(Object clave){
        NodoClave buscado = primero;
        while (buscado != null && (buscado.getClave().equals(clave) == false) ){
            buscado = buscado.getLigaDer();
        }
        if (buscado != null){ //se encontro, siendo diferente a nulo
            return buscado;
        }else{
            return null;
        }
    }

    /**
     * Metodo que busca y regresa el contenido de un objeto por su clave.
     * @param clave Objeto clave a buscar.
     * @return Regresa el contenido del nodo que coincida con esa clave.
     */
    public Object buscar(Object clave){
        NodoClave buscado = primero;
        while (buscado != null && (buscado.getClave().equals(clave) == false) ){
            buscado = buscado.getLigaDer();
        }
        if (buscado != null){ //se encontro, siendo diferente a nulo
            return buscado.getInfo();
        }else{
            return null;
        }
    }

    /**
     * Metodo que busca a un nodo por su contenido/info.
     * @param contenido Info a buscar, exactamente.
     * @return Regresa la info del nodo buscado, o null si no esta.
     */
    public Object buscarContenido(Object contenido){
        NodoClave buscado = primero;
        while (buscado != null && (buscado.getInfo().equals(contenido) == false) ){
            buscado = buscado.getLigaDer();
        }
        if (buscado != null){
            return buscado.getInfo();
        } else {
            return null;
        }
    }

    /**
     * Metodo que cambia la info de un nodo<br/>
     * especificado por una clave.
     * @param clave Clave del nodo.
     * @param info Informacion a cambiar.
     * @return Regresa True si pudo ser cambiado,<br/>
     * False si el nodo no existe.
     */
    public boolean cambiar(Object clave, Object info){
        NodoClave obtenido = obtenerNodoXClave(clave);
        if (obtenido != null){
            obtenido.setInfo(info);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que cambia a los nodos con una info<br/>
     * vieja por lo de una info nueva. Si hay duplicados<br/>
     * tambien los cambiara.
     * @param infoVieja Info vieja de un nodo en la lista.
     * @param infoNueva Info a la que se va a cambiar.
     * @return Regresa true si se cambio, si no<br/>
     * hubieron cambios se regresa false.
     */
    public boolean cambiarValor(Object infoVieja, Object infoNueva){
        NodoClave temporal = primero;
        int cambios = 0;
        while (temporal != null){
            if (temporal.getInfo().toString().equalsIgnoreCase(infoVieja.toString()) == true){ //Si el objeto de la infoVieja es igual al del nodo actual, se cambia.
                temporal.setInfo(infoNueva);
                cambios++;
            }
            temporal = temporal.getLigaDer();
        }
        if (cambios > 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Metodo que Imprime la lista completa.
     */
    public void imprimir(){
        NodoClave temporal = primero;
        while (temporal != null){
            SalidaPorDefecto.terminal(temporal+" -> ");
            temporal = temporal.getLigaDer();
        }
        SalidaPorDefecto.terminal("null");
    }

    /**
     * Metodo que Imprime las claves de la lista.
     */
    public void imprimirClaves(){
        NodoClave temporal = primero;
        while (temporal != null){
            SalidaPorDefecto.terminal(temporal.getClave()+" -> ");
            temporal = temporal.getLigaDer();
        }
        SalidaPorDefecto.terminal("null");
    }

    /**
     * Metodo que Imprime los valores de la lista.
     */
    public void imprimirValores(){
        NodoClave temporal = primero;
        while (temporal != null){
            SalidaPorDefecto.terminal(temporal.getInfo()+" -> ");
            temporal = temporal.getLigaDer();
        }
        SalidaPorDefecto.terminal("null");
    }

    /**
     * Metodo que convierte a la lista actual en una<br/>
     * lista estatica que contiene a su vez a dos listas<br/>
     * estaticas, en la posicion 0 las claves y en la <br/>
     * posicion 1 los valores.
     * @return Regresa una lista estatica.
     */
    public ListaEstatica aListasEstaticas(){
        ListaEstatica listaRetorno = new ListaEstatica(2);
        int tamanioListaActual = numeroElementos();
        ListaEstatica claves = new ListaEstatica(tamanioListaActual);
        ListaEstatica valores = new ListaEstatica(tamanioListaActual);
        NodoClave temporal = primero;
        while (temporal != null){
            claves.agregar(temporal.getClave());
            valores.agregar(temporal.getInfo());
            temporal = temporal.getLigaDer();
        }
        listaRetorno.agregar(claves);
        listaRetorno.agregar(valores);
        return listaRetorno;
    }

    /**
     * Metodo que convierte la lista actual en una<br/>
     * lista dinamica con listas dinamicas que contienen<br/>
     * la clave y valor (parecido al metodo anterior).
     * @return Regresa una lista dinamica con los valores.
     */
    public ListaDinamica aListasDinamicas(){
        ListaDinamica listaDynaRetorno = new ListaDinamica();
        ListaDinamica claves = new ListaDinamica();
        ListaDinamica valores = new ListaDinamica();
        NodoClave temporal = primero;
        while (temporal != null){
            claves.agregar(temporal.getClave());
            valores.agregar(temporal.getInfo());
            temporal = temporal.getLigaDer();
        }
        listaDynaRetorno.agregar(claves);
        listaDynaRetorno.agregar(valores);
        return listaDynaRetorno;
    }

    /**
     * Metodo que convierte a la lista actual <br/>
     * en una Matriz 2D. Con la primera columna de<br/>
     * claves y la segunda de valores.
     * @return Regresa una matriz con los datos especificos.
     */
    public Matriz2 aMatriz2(){
        int tamanioListaActual = numeroElementos();
        Matriz2 matrizRetorno = new Matriz2(tamanioListaActual ,2);
        int posicionRenglon = 0;
        NodoClave temporal = primero;
        while (temporal != null){
            matrizRetorno.cambiar(posicionRenglon, 0, temporal.getClave());
            matrizRetorno.cambiar(posicionRenglon, 1, temporal.getInfo());
            posicionRenglon++;
            temporal = temporal.getLigaDer();
        }
        return matrizRetorno;
    }



    /**
     * Metodo que vacia una lista.
     */
    public void vaciar(){
        primero = null;
        ultimo = null;
    }

    /**
     * Metodo que obtiene el contenido de un nodo por su clave.
     * @param clave Clave del Nodo a obtener.
     * @return Regresa La informacion o contenido del nodo.
     */
    public Object obtener(Object clave){
        NodoClave obtenido = obtenerNodoXClave(clave);
        return obtenido.getInfo();
    }

    /**
     * Metodo que agrega la informacion de una lista<br/>
     * clave pasada como argumento a la lista actual.<br/>
     * Si un elemento se repite en clave, se reemplazara.
     * @param lista2 Lista Dinamica Clave de la cual se va a obtener la info.
     * @return Regresa true si pudo ser agregada, false si no.
     */
    public boolean agregarLista(ListaDinamicaClave lista2){
        if (lista2.vacia() == false){
            NodoClave temporalLista2 = lista2.primero;
            while (temporalLista2 != null){
                agregar(temporalLista2.getClave(), temporalLista2.getInfo());
                temporalLista2 = temporalLista2.getLigaDer();
            }
            return true;
        }else{
            return false;
        }
    }

    /**
     * Metodo que permite agregar una lista como las claves,<br/>
     * y otra como los valores. Si hay repetidos se reemplazan.
     * @param claves Lista Estatica referente a claves.
     * @param valores Lista Estatica referente a valores.
     * @return Regresa true si se agregaron,<br/>
     * false si una lista esta vacia o son de diferente tamanio.
     */
    public boolean agregarListasEstaticas(ListaEstatica claves, ListaEstatica valores){
        if (claves.vacia() == false || valores.vacia() == false){ //si tanto valores y claves son falsos...
            if (claves.cantidad() >= valores.cantidad()){ //si el total de las claves son mayores a la de los valores
                for (int cadaClave = 0; cadaClave < claves.cantidad(); cadaClave++){
                    if (cadaClave <= valores.cantidad()){
                        agregar(claves.obtener(cadaClave), valores.obtener(cadaClave));
                    } else {
                        agregar(claves.obtener(cadaClave), null);
                    }
                }
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * Metodo que regresa el tamanio de la lista.
     * @return Regresa el tamanio de lista.
     */
    public int numeroElementos(){
        NodoClave temporal = primero;
        int numeroNodos = 0;
        while (temporal != null){ //mientras el nodo actual no sea nulo.
            numeroNodos++;
            temporal = temporal.getLigaDer();
        }
        return numeroNodos;
    }

    /**
     * Metodo que permite agregar una lista dinamica a la actual.<br/>
     * Al igual que el metodo que agrega Listas Estaticas, ambas<br/>
     * listas deben de ser de igual tamanio, para evitar problemas<br/>
     * con nulos.
     * @param claves Lista Dinamica de Claves.
     * @param valores Lista Dinamica de Valores.
     * @return Regresa true si se pudo agregar, false si no.
     */
    public boolean agregarListasDinamicas(ListaDinamica claves, ListaDinamica valores){
        if (claves.vacia() == false || valores.vacia() == false){
            if (claves.cantidad() >= valores.cantidad()){ //funciona siempre y cuando ambas sean iguales.
                Nodo temporalClaves = (Nodo) claves.inicializarIterador();
                Nodo temporalValores = (Nodo) valores.inicializarIterador();
                while (temporalClaves != null && temporalValores != null){
                    agregar(temporalClaves.getInfo(), temporalValores.getInfo());
                    temporalClaves = temporalClaves.getLigaDer();
                    temporalValores = temporalValores.getLigaDer();
                }
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * Metodo que permite agregar una matriz a la lista actual.<br/>
     * Se debe de tener en la columna 0 las claves y en la columna 1<br/>
     * los valores.
     * @param matriz Matriz a agregar.
     * @return Regresa True si se pudo agregar, <br/>
     * false si la matriz no tiene exactamente 2 columnas.
     */
    public boolean agregarMatriz2(Matriz2 matriz){
        if (matriz.obtenerColumnas() == 2){
            for (int cadaRegistro = 0; cadaRegistro<matriz.obtenerRenglones(); cadaRegistro++){
                agregar(matriz.obtener(cadaRegistro, 0), matriz.obtener(cadaRegistro, 1));
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo para obtener el primer nodo <br/>
     * de la lista, usado en iteraciones.
     * @return Regresa el primer nodo como objeto.
     */
    public NodoClave inicializarIterador(){
        NodoClave nodoActual = primero;
        return nodoActual;
    }

}
