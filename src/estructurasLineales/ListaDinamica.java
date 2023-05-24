package estructurasLineales;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.auxiliares.Nodo;
import estructurasnolineales.Matriz2;
import utilerias.comunes.TipoTabla;

public class ListaDinamica implements Lista{
    protected Nodo primero; //que nodo es primero en la lista
    protected Nodo ultimo; //que nodo es el ultimo en la lista
    protected Nodo nodoActual; //nodo

    public ListaDinamica(){ //inicializar los nodos en nulo.
        primero=null;
        ultimo=null;
    }

    /**
     * Verifica que la lista dinamica este vacia.
     * @return Regresa True si esta vacia o False si no lo esta.
     */
    @Override
    public boolean vacia() {
        if (primero==null){ // si no hay primer nodo, no hay lista ni ultimo nodo.
            return true;
        }else{
            return false;
        }
    }

    /**
     * Agrega un valor a la información del nuevo nodo
     * @param info Es el nuevo valor a añadir.
     * @return Regresa 1 si se agrego, -1 si no se pudo.
     */
    @Override
    public int agregar(Object info) {
        Nodo nuevoNodo = new Nodo(info);
        if(nuevoNodo != null){ //no es nulo (hay espacio)
            if (vacia() == true){ //caso a: no hay nodos en la lista
                primero = nuevoNodo;
                ultimo = nuevoNodo;
            }else{ //caso b: hay algún nodo en la lista
                ultimo.setLigaDer(nuevoNodo);
                ultimo = nuevoNodo;
            }
            return 1;
        }else{
            return -1; //el nodo no se agrega
        }
    }

    public int agregarInicio(Object info){
        Nodo nuevoNodo = new Nodo(info);
        if(nuevoNodo != null){ //no es nulo
            if (vacia() == true){ //caso a: no hay nodos en la lista
                primero = nuevoNodo;
                ultimo = nuevoNodo;
            }else{ //caso b: hay algun nodo en la lista
                nuevoNodo.setLigaDer(primero);
                primero = nuevoNodo;
            }
            return 1;
        }else{
            return -1; //el nodo no se agrega
        }
    }

    @Override
    public void imprimir() {
        Nodo temporal = primero;
        while (temporal != null){
            SalidaPorDefecto.terminal(temporal.getInfo()+" -> ");
            temporal = temporal.getLigaDer();
        }
        SalidaPorDefecto.terminal("null");
    }

    @Override
    public void imprimirOI() {
        Nodo nodoActual = primero;
        PilaDinamica pilaTemporal = new PilaDinamica();
        while (nodoActual != null){
            pilaTemporal.poner(nodoActual.getInfo());
            nodoActual = nodoActual.getLigaDer();
        }
        pilaTemporal.poner("null");
        //pilaTemporal.imprimir(); // esta cosa imprime el nulo del final por naturaleza, lo cambiare por un while
        SalidaPorDefecto.terminal(""+pilaTemporal.quitar());
        while(pilaTemporal.vacio() != true){
            SalidaPorDefecto.terminal(" <- "+ pilaTemporal.quitar());
        }
    }

    @Override
    public Object buscar(Object info) {
        Nodo nodoBuscado = primero;
        while ((nodoBuscado != null)&&(info.toString().equalsIgnoreCase(nodoBuscado.toString()) == false)){
            nodoBuscado = nodoBuscado.getLigaDer();
        }
        if (nodoBuscado != null){ //se encontro
            return nodoBuscado.getInfo();
        }else{ //no esta
            return null;
        }
    }

    public ListaEstatica buscarAnterior(Object info) {
        ListaEstatica listaReturn = new ListaEstatica(2);
        Nodo nodoBuscado = primero;
        Nodo nodoAnterior = primero;
        while ((nodoBuscado != null)&&(info.toString().equalsIgnoreCase(nodoBuscado.toString()) == false)){
            nodoAnterior = nodoBuscado;
            nodoBuscado = nodoBuscado.getLigaDer();
        }
        if (nodoBuscado != null){ //se encontro
            listaReturn.agregar(nodoAnterior);
            listaReturn.agregar(nodoBuscado);
        }else{ //no esta
            listaReturn.agregar(null);
            listaReturn.agregar(null);
        }
        return listaReturn;
    }

    @Override
    public Object eliminar(Object info) {
        if (vacia() == false){
            ListaEstatica listaRetornoBusqueda = buscarAnterior(info);
            Nodo nodoAnterior = (Nodo) listaRetornoBusqueda.obtener(0);
            Nodo nodoBuscado = (Nodo) listaRetornoBusqueda.obtener(1);
            if (nodoBuscado == null){ //caso a
                return null;
            }else {
                Object respaldo = nodoBuscado.getInfo();
                if (primero == ultimo){ //caso c
                    primero = null;
                    ultimo = null;
                }else if (nodoBuscado == primero){ //caso d
                    primero = primero.getLigaDer();
                } else if (nodoBuscado == ultimo){ //caso e
                    nodoAnterior.setLigaDer(null);
                    ultimo = nodoAnterior;
                } else { //caso f
                    Nodo siguiente = nodoBuscado.getLigaDer();
                    nodoAnterior.setLigaDer(siguiente);
                }
                return respaldo;
            }
        }else{ //caso b
            return  null;
        }
    }

    /**
     * Metodo que verifica si dos listas son iguales.
     * @param lista2 recibe un parametro de objeto que debe ser un tipo ListaDinamica.
     * @return Regresa false si son diferentes, true si es igual.
     */
    @Override
    public boolean esIgual(Object lista2) {
        if (lista2 instanceof ListaDinamica){
            for (int cadaObjeto = 0; cadaObjeto<cantidad(); cadaObjeto++){
                Object obtenido = obtener(cadaObjeto);
                if (((ListaDinamica) lista2).buscar(obtenido) == null){
                    return false;
                }
                //if (! ( obtener(cadaObjeto) ).equals(((ListaDinamica) lista2).obtener(cadaObjeto))){
                    //return false;
                //}
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que elimina un objeto dependiendeo del numero de veces que se pasen.
     * @param infoViejo Se recibe un Elemento para buscar, el cual sera modificado.
     * @param infoNuevo Se modifica un Elemento viejo para modificar.
     * @param numVeces Numero de veces a eliminar.
     * @return Regresa false si ningun elemento se cambio.
     */
    @Override
    public boolean cambiar(Object infoViejo, Object infoNuevo, int numVeces) {
        nodoActual = primero;
        int numVez = 0;
        while((nodoActual != null) && (numVez != numVeces)){
            if (nodoActual.getInfo().equals(infoViejo)){
                nodoActual.setInfo(infoNuevo);
                numVez++;
            }
            nodoActual = nodoActual.getLigaDer();
        }
        if (numVez > 0 ){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ListaEstatica buscarValores(Object info) {
        return null;
    }

    @Override
    public Object eliminar() {
        if(vacia() == false){ //hay algo
            Object respaldo = ultimo.getInfo();
            if (primero == ultimo){ // caso b)
                primero = null; //2
                ultimo = null;
            }else{ // paso c)
                Nodo penultimo = primero;
                while(penultimo.getLigaDer() != ultimo){
                    penultimo = penultimo.getLigaDer();
                }
                penultimo.setLigaDer(null);
                ultimo = penultimo;
            }
            return respaldo;
        }else{ //paso a)
            return null;
        }
    }

    /**
     * Metodo que vacia el contenido de los nodos de una lista.
     */
    public void vaciarInfo() {
        if (vacia() == false){
            nodoActual = primero;
            while(nodoActual != null){
                nodoActual.setInfo(" ");
                nodoActual = nodoActual.getLigaDer();
            }
        }
    }

    /**
     * Metodo que vacia todos los nodos de toda la lista.
     */
    @Override
    public void vaciar(){
        if (vacia() == false){
            while (vacia() != true){
                eliminarInicio();
            }
        }
    }

    /**
     * Metodo que agrega al final de la lista dinamica actual la<br/>
     * lista pasada por parametro.
     * @param lista2 Ingresar un Objeto tipo lista, si no devolvera null.
     * @return Regresa true si se agrego bien, regresa false si no.
     */
    @Override
    public boolean agregarLista(Object lista2) {
        if (lista2 instanceof ListaEstatica){ //funciona con estaticas
            if (((ListaEstatica) lista2).vacia() == false){
                for (int cadaInfo = 0; cadaInfo< ((ListaEstatica) lista2).cantidad(); cadaInfo++){
                    agregar(((ListaEstatica) lista2).obtener(cadaInfo));
                }
                return true;
            } else {
                return false;
            }
        } else if (lista2 instanceof ListaDinamica){
            if (((ListaDinamica) lista2).vacia() == false){
                Nodo nodoActualLista2 = ((ListaDinamica) lista2).primero;
                Nodo nodoUltimoLista2 = (Nodo) ((ListaDinamica) lista2).verUltimo();
                while (nodoActualLista2 != nodoUltimoLista2){ //se ciclaba por que no podia obtener el nulo de esta lista.
                    agregar(nodoActualLista2.getInfo());
                    nodoActualLista2 = nodoActualLista2.getLigaDer();
                }
                agregar(nodoUltimoLista2);
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Metodo que invierte la lista dinamica.
     */
    @Override
    public void invertir() {
        PilaDinamica pilaTemporal = new PilaDinamica();
        nodoActual = primero;
        while (nodoActual != null){
            pilaTemporal.poner(nodoActual.getInfo());
            nodoActual = nodoActual.getLigaDer();
        }
        nodoActual = primero;
        while (pilaTemporal.vacio() == false){
            nodoActual.setInfo(pilaTemporal.quitar());
            nodoActual = nodoActual.getLigaDer();
        }
    }

    /**
     * Metodo que cuenta el numero de ocurrencias en la lista.
     * @param info Se pasa el objeto que se desea contar.
     * @return Regresa el numero de ocurrencias de la lista.
     */
    @Override
    public int contar(Object info) {
        int total = 0;
        nodoActual = primero;
        while (nodoActual != null){
            if ((nodoActual.getInfo()).equals(info.toString())){
                total++;
            }
            nodoActual = nodoActual.getLigaDer();
        }
        return total;
    }

    @Override
    public boolean eliminarLista(Object lista2) {
        if (lista2 instanceof ListaDinamica){
            Nodo actual = ((ListaDinamica) lista2).primero;
            while (actual != null){
                Object info = actual.getInfo();
                if (buscar(info) != null){
                    eliminar(info);
                }
                actual = actual.getLigaDer();
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que rellena una lista dinamica con la info que se pase<br/>
     * en el numero de veces que se desee.
     * @param info Objeto que se desea llenar en la lista.
     * @param cantidad Cantidad de veces que se desea llenar en la lista.
     */
    @Override
    public void rellenar(Object info, int cantidad) {
        nodoActual = primero;
        for (int vecesARellenar = 0; vecesARellenar<cantidad; vecesARellenar++){
            if (nodoActual == null){
                agregar(info);
            } else {
                nodoActual.setInfo(info);
                nodoActual = nodoActual.getLigaDer();
            }
        }
    }

    /**
     * Metodo que clona la matriz actual a otra.
     * @return regresa un objeto Lista Dinamica,<br/>
     * pero en tipo Lista, ya que el metodo se<br/>
     * implementa de la interface Lista.
     */
    @Override
    public Lista clonar() {
        ListaDinamica listaDinamicaRetorno = new ListaDinamica();
        nodoActual = primero;
        while (nodoActual != null){
            listaDinamicaRetorno.agregar(nodoActual.getInfo());
            nodoActual = nodoActual.getLigaDer();
        }
        return listaDinamicaRetorno;
    }

    @Override
    public Lista subLista(int indiceInicial, int indiceFinal) {
        return null;
    }

    /**
     * Metodo que redimensiona la lista dinamica.
     * @param maximo Tamanio nuevo de la lista.
     * @return Regresa True si se pudo redimensionar,<br/>
     * false si no.
     */
    @Override
    public boolean redimensionar(int maximo) {
        if ( maximo > cantidad()){
            int vecesSobrantes = maximo - cantidad();
            for (int cadaVez = 0; cadaVez<vecesSobrantes; cadaVez++){
                agregar(null);
            }
            return true;
        } else if ( maximo < cantidad() ) {
            int vecesSobrantes = cantidad() - maximo;
            for (int cadaVez = 0; cadaVez<vecesSobrantes; cadaVez++){
                eliminar();
            }
            return true;
        }
        return false;
    }

    @Override
    public int maximo() {
        return 0;
    }

    /**
     * Metodo que regresa la cantidad total de nodos<br/>
     * sin contar el nulo del final.
     * @return Regresa el numero de nodos.
     */
    @Override
    public int cantidad() {
        int total = 0;
        if (primero != null){
            Nodo actual = primero;
            while (actual != null){
                total++;
                actual = actual.getLigaDer();
            }
            return total;
        }else{
            return total;
        }
    }

    @Override
    public Object verUltimo() {
        Nodo referenciaAUltimo = ultimo;
        return referenciaAUltimo.getInfo();
    }

    public Object verPrimero() {
        if (vacia() == false){
            Nodo referenciaAPrimero = primero;
            return referenciaAPrimero.getInfo();
        }else{
            return null;
        }
    }

    @Override
    public Object eliminarInicio(){
        if (vacia() == false){
            Object respaldo = primero.getInfo();
            if (primero == ultimo){
                primero = null;
                ultimo = null;
            } else {
                primero = primero.getLigaDer(); //no es necesario eliminar el apuntador al antiguo primero, ya que nadie hace referencia a el
            }
            return respaldo;
        }else {
            return null;
        }
    }

    public boolean hayNodo(){
        if (nodoActual == null){
            return false;
        }else{
            return true;
        }
    }

    public Object obtenerNodo(){
        if (hayNodo() == true){
            Object respaldo = nodoActual.getInfo();
            nodoActual = nodoActual.getLigaDer();
            return respaldo;
        } else{
            return null;
        }
    }

    public Object inicializarIterador(){
        nodoActual = primero;
        return nodoActual;
    }

    /**
     * Metodo que regresa una Lista Estatica con los<br/>
     * valores de la lista dinamica actual.
     * @return Regresa la lista con valores, o false<br/>
     * si la lista dinamica actual estaba vacia.
     */
    public ListaEstatica aListaEstatica(){
        if (vacia() == false){
            int tamanioNuevaLista = cantidad();
            ListaEstatica listaRetorno = new ListaEstatica(tamanioNuevaLista);
            nodoActual = primero; //iniciamos el nodo
            while (nodoActual != null){
                listaRetorno.agregar(nodoActual.getInfo());
                nodoActual = nodoActual.getLigaDer();
            }
            return listaRetorno;
        }else{
            return null;
        }
    }

    /**
     * Metodo que obtiene los valores de una lista con elementos a descartar<br/>
     * en la lista dinamica actual y elimina los que seran descartados.
     * @param elementosADescartar Lista con los elementos a descartar.
     * @return Regresa una lista nueva con los elementos nuevos.
     */
    public ListaEstatica aListaEstatica(ListaEstatica elementosADescartar){
        if (vacia() == false){
            ListaEstatica listaRetorno = new ListaEstatica(cantidad());
            nodoActual = primero;
            while (nodoActual != null){
                int res = (int) elementosADescartar.buscar(nodoActual.getInfo());
                if (res == -1){
                    listaRetorno.agregar(nodoActual.getInfo());
                }
                nodoActual = nodoActual.getLigaDer();
            }
            return listaRetorno;
        }else{
            return null;
        }
    }

    /**
     * Metodo que convierte una lista dinamica a una matriz 2d
     * @param filas Filas de la nueva matriz
     * @param columnas Columnas de la nueva MAtriz
     * @return Regresa una matriz con la info nueva.
     */
    public Matriz2 aMatriz2d(int filas, int columnas){
        Matriz2 matrizRetorno = new Matriz2(filas, columnas);
        for (int cadaFila = 0; cadaFila<filas; cadaFila++){
            nodoActual = primero;
            for (int cadaColumna = 0; cadaColumna<columnas; cadaColumna++){
                if (nodoActual != null){
                    matrizRetorno.cambiar(cadaFila, cadaColumna, nodoActual.getInfo());
                    nodoActual = nodoActual.getLigaDer();
                } else {
                    matrizRetorno.cambiar(cadaFila, cadaColumna, null);
                }
            }
        }
        return matrizRetorno;
    }

    /**
     * Metodo que agrega una matriz a la lista dinamica actual,<br/>
     * de acuerdo al tipo de Tabla que se haya especificado.
     * @param tabla Matriz que se agregara.
     * @param enumTabla Tipo enumerado de Tabla, agregar por columnas<br/>
     *                  o por renglon.
     * @return Regresa true si se pudo agregar, false si no.
     */
    public boolean agregarMatriz2D(Matriz2 tabla, TipoTabla enumTabla){
        if (tabla.esVacia() == false){ //si la matriz de tabla no esta vacia
            if (enumTabla.getNumeroTabla() == 0){ //Por Columnas
                for (int cadaRenglon = 0; cadaRenglon<tabla.obtenerRenglones(); cadaRenglon++){
                    for (int cadaColumna = 0; cadaColumna< tabla.obtenerColumnas(); cadaColumna++){
                        Object infoAgregar = tabla.obtener(cadaRenglon, cadaColumna);
                        if ( infoAgregar != null ){
                            agregar(infoAgregar);
                        }
                    }
                }
                return true;
            } else if (enumTabla.getNumeroTabla() == 1) { //Por Renglones
                for (int cadaColumna = 0; cadaColumna<tabla.obtenerColumnas(); cadaColumna++){
                    for (int cadaRenglon = 0; cadaRenglon<tabla.obtenerRenglones(); cadaRenglon++){
                        Object infoAgregar = tabla.obtener(cadaRenglon, cadaColumna);
                        if ( infoAgregar != null ){
                            agregar(infoAgregar);
                        }
                    }
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
     * Metodo que cambia la informacion de un nodo en una posicion.
     * @param indice Numero del nodo en la lista.
     * @param info Info a cambiar.
     * @return Regresa true si se pudo cambiar, false si no.
     */
    public boolean cambiar(int indice, Object info){
        if ((indice >= 0) && (indice < cantidad() )){
            int posicion = 0;
            nodoActual = primero;
            while (posicion != indice){
                nodoActual = nodoActual.getLigaDer();
                posicion++;
            }
            nodoActual.setInfo(info);
            return true;
        }else{
            return false;}
    }

    /**
     * Metodo que obtiene un objeto en una posicion de ls lista.
     * @param indice Posicion del nodo.
     * @return Regresa el nodo objeto especificado.
     */
    public Object obtener(int indice){
        if ((indice >= 0) && (indice < cantidad() )) {
            int posicion = 0;
            nodoActual = primero;
            while (posicion != indice) {
                nodoActual = nodoActual.getLigaDer();
                posicion++;
            }
            Object respaldo = nodoActual.getInfo();
            return respaldo;
        } else {
            return null;
        }
    }

    /**
     * Eliminacion con indices.
     * @param indice Numero de la posicion a eliminar.
     * @return Regresa el objeto eliminado.
     */
    public Object eliminar(int indice){
        if ((indice >= 0) && (indice < cantidad() )) {
            int posicion = 0;
            nodoActual = primero;
            while (posicion != indice) {
                nodoActual = nodoActual.getLigaDer();
                posicion++;
            }
            Object respaldo = nodoActual.getInfo();
            eliminar(nodoActual);
            return respaldo;
        }else{
            return null;
        }
    }

}
