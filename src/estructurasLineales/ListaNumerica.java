package estructurasLineales;

import entradasalida.SalidaPorDefecto;

/**
 * Esta Clase contiene los metodos de un TDA ListaNumerica
 * @version 1.0
 * @author Gerardo Rivas Delgado
 */
public class ListaNumerica extends ListaEstatica{
    protected int tamanioNuevosArreglos;

    public ListaNumerica(int tamanio) {
        super(tamanio);
        tamanioNuevosArreglos = tamanio;
    }

    /**
     * Este metodo sobre-escribe al metodo de agregar, verificando que se
     * agreguen solo valores numericos.
     * @param info Es el nuevo valor a añadir.
     * @return regresa -1 si no se pudo agregar o si no es instancia de
     * una superclase Numero.
     */
    @Override
    public int agregar(Object info) {
        if (info instanceof Number){
            if (info instanceof Integer){
                double valor = (int) info;
                return super.agregar(valor);
            } else if (info instanceof Double){
                return super.agregar(info);
            } else {
                return -1;
            }
        } else {
            return -1; //No es instancia de un numero
        }
    }

    /**
     * Metodo que multiplica a el arreglo por el escalar especificado.
     * @param escalar Escalar a multiplicar en double.
     * @return Regresa true si se pudo multiplicar, falso si la lista no esta llena, ya que
     * no es posible multiplicar por nulos.
     */
    public boolean porEscalar(Number escalar){
        if (llena() == true){
            for (int posicionActual = 0; posicionActual<=tope; posicionActual++){
                informacion[posicionActual] = (double) (informacion[posicionActual]) * (double) escalar;
            }
            return true; //se pudo multiplicar
        } else {
            return false; //no se pudo multiplicar
        }
    }

    /**
     * Metodo que suma un escalar a todas las posiciones de un arreglo.
     * @param escalar Numero Escalar en double a sumar.
     * @return Regresa True si se pudo sumar,
     */
    public boolean sumarEscalar(Number escalar){
        if (llena() == true){
            for (int posicionActual = 0; posicionActual<=tope; posicionActual++){
                informacion[posicionActual] = (double) (informacion[posicionActual]) + (double) escalar;
            }
            return true; //se pudo sumar
        } else {
            return false; //no se pudo sumar
        }
    }

    /**
     * Metodo que suma los valores de las mismas posiciones entre dos listas.
     * @param lista2 Objeto tipo ListaNumerica que sumara a la lista acutal.
     * @return Regresa True si se sumaron ambos valores, false si
     * no fueron del mismo tamanio o alguna estaba vacia.
     */
    public boolean sumar(ListaNumerica lista2){
        if (((lista2.llena()) == true) && (llena() == true)){
            if (lista2.MAXIMO == MAXIMO){
                for (int posicionAmbas = 0; posicionAmbas<=lista2.tope; posicionAmbas++){
                    informacion[posicionAmbas] = (double) (informacion[posicionAmbas]) + (double) lista2.informacion[posicionAmbas];
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Metodo que multiplica los valores de las mismas posiciones entre dos listas.
     * @param lista2 Objeto tipo ListaNumerica que sumara a la lista acutal.
     * @return Regresa True si se multiplicaron todos los valores, false si
     * no fueron del mismo tamanio o alguna estaba vacia.
     */
    public boolean multiplicar(ListaNumerica lista2){
        if (((lista2.llena()) == true) && (llena() == true)){
            if (lista2.MAXIMO == MAXIMO){
                for (int posicionAmbas = 0; posicionAmbas<=lista2.tope; posicionAmbas++){
                    informacion[posicionAmbas] = (double) (informacion[posicionAmbas]) * (double) lista2.informacion[posicionAmbas];
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Aplica una potencia de un numero pasado por parametros al contenido del arreglo.
     * @param escalar Numero a potenciar, en double.
     * @return Regresa falso si la lista esta vacia, verdadero si se potencio el arreglo.
     */
    public boolean aplicarPotencia(Number escalar){
        if (llena() == true){
            for (int posicionActual = 0; posicionActual<=tope; posicionActual++){
                informacion[posicionActual] = Math.pow((double) (informacion[posicionActual]), (double) escalar);
            }
            return true; //se pudo sumar
        } else {
            return false; //no se pudo sumar
        }
    }

    /**
     * Aplica las potencias a los valores del arreglo actual de otro que se pase por parametros.
     * @param lista2 Lista con valores que se van a aplicar.
     * @return Regresa falso si la lista esta vacia, verdadero si se potencio el arreglo.
     */
    public boolean aplicarPotencia(ListaNumerica lista2){
        if (((lista2.llena()) == true) && (llena() == true)){
            if (lista2.MAXIMO == MAXIMO){
                for (int posicionAmbas = 0; posicionAmbas<=lista2.tope; posicionAmbas++){
                    informacion[posicionAmbas] = Math.pow((double) (informacion[posicionAmbas]), (double) lista2.informacion[posicionAmbas]);
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Devuelve el valor del producto escalar de la lista2.
     * @param lista2 Valor del segundo vector.
     * @return Valor del producto Escalar.
     */
    public double productoEscalar(ListaNumerica lista2){
        if (lista2.tope >= 0){
            double producto = 0.0;
            for (int posicion = 0; posicion <= lista2.tope; posicion++){
                double multiplicacion = (double) (informacion[posicion]) * (double) (lista2.informacion[posicion]);
                producto = producto + multiplicacion;
            }
            return producto;
        } else {
            return -1.0;
        }
    }

    /**
     * Devuelve la norma del arreglo.
     * @return Valor de la norma.
     */
    public double norma(){
        if (tope > 0){
            double norma = 0;
            double suma = 0;
            for (int posicion = 0; posicion <= tope; posicion++){
                double cuadrado = Math.pow((double)(informacion[posicion]), 2);
                suma = suma + cuadrado;
            }
            if (suma >= 0){
                norma = Math.sqrt(suma);
                return norma;
            }else{
                return -1.0;
            }
        } else {
            return -1.0;
        }
    }

    /**
     * Este metodo devuelve la norma Euclidiana entre dos arreglos.
     * @param arreglo2 Vector B para sumar.
     * @return Se regresa el valor de la norma Eucliadina o "-1" si no se pudo realizar la operacion.
     */
    public double normaEuclidiana(ListaNumerica arreglo2){
        if ((arreglo2.llena() == true) && (llena() == true)){
            double resta = 0.0;
            double suma = 0.0;
            double cuadrado = 0.0;
            double normaEuc = 0.0;
            for (int posicion = 0; posicion <= tope; posicion++){
                resta = ((double) arreglo2.informacion[posicion]) - ((double) informacion[posicion]);
                cuadrado = Math.pow(resta, 2);
                suma = suma + cuadrado;
            }
            if (suma >= 0){
                normaEuc = Math.sqrt(suma);
                return normaEuc;
            }else{
                return -1.0;
            }
        } else {
            return -1.0;
        }
    }

    /**
     * Se suman los escalares de una lista dada, al arreglo actual.
     * @param escalares Lista de escalares a sumar.
     * @return se regresa el resultado de la suma.
     */
    public double sumarEscalares(ListaNumerica escalares){
        double sumaEscalares = 0.0;
        double sumaTotal = 0.0;
        for (int posicionEscalares = 0; posicionEscalares<=escalares.tope; posicionEscalares++){
            sumaEscalares = sumaEscalares + ((double) escalares.informacion[posicionEscalares]);
        }
        for (int posicionSuma = 0; posicionSuma<=tope; posicionSuma++){
            sumaTotal = sumaTotal + ( ((double) informacion[posicionSuma]) + sumaEscalares);
        }
        return sumaTotal;
    }

    /**
     * Suma los indices de los valores que se tengan en una lista numerica.
     * @param indices Lista con los valores de los indices.
     * @return Se regresa la suma de los indices.
     */
    public double sumarIndices(ListaNumerica indices){
        double suma = 0.0;
        for (int posicionEnIndice = 0; posicionEnIndice<=indices.tope; posicionEnIndice++){
            int numeroPosicion = (int) ((double) (indices.informacion[posicionEnIndice]));
            suma = suma + ((double) informacion[numeroPosicion]);
        }
        return suma;
    }

    /**
     * Metodo que devuelve una lista con los objetos de las posiciones de los numeros de la lista del parametro.
     * @param listaIndices Lista con los indices del arreglo actual a agregar a la nueva lista.
     * @return Lista con los valores agregados
     */
    public ListaEstatica subLista(ListaNumerica listaIndices){
        int tamanioNuevoArreglo = listaIndices.MAXIMO;
        ListaEstatica listaNueva = new ListaEstatica(tamanioNuevoArreglo);
        for (int posicionIndices = 0; posicionIndices <= listaIndices.tope; posicionIndices++){
            int posicion = (int) ((double) (listaIndices.informacion[posicionIndices]));
            listaNueva.agregar(informacion[posicion]);
        }
        return listaNueva;
    }

    /**
     * Checa que el vector actual y el que se le pasa sean Ortogonales.
     * @param lista2 Vector "b" a comparar con el "a" (Arreglo actual).
     * @return Verdadero si son Ortogonales, Falso si no son.
     */
    public boolean esOrtogonal(ListaNumerica lista2){
        if (productoEscalar(lista2) == 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Se suman todas las listas que se tengan en una ListaEstatica
     * @param listas Se pasa una lista que contenga listas dentras, de lo contrario bota error.
     * @return Regresa la suma en double de lo que se resulte de las sumas.
     */
    public double sumarListaEstatica(ListaEstatica listas){
        double sumaRenglonListas = 0.0;
        double sumaTotal = 0.0;
        for (int columnas = 0; columnas<=listas.tope; columnas++){
            ListaEstatica listaColumna = (ListaEstatica) listas.informacion[columnas];
            for (int renglones = 0; renglones<=listaColumna.tope; renglones++){
                sumaRenglonListas = sumaRenglonListas + ((double) listaColumna.informacion[renglones]);
            }
            sumaTotal = sumaTotal + sumaRenglonListas + ((double)informacion[columnas]);
            sumaRenglonListas = 0.0;
            }
        return sumaTotal;
    }

    /**
     * Metodo que verifica que una lista de vectores sea linealmente dependiente a los escalares actuales.
     * @param listaVectores Lista estatica que guarde en cada posicion un arreglo numerico.
     * @return Regresa true si es Linealmente Dependiente, false si no lo es.
     */
    public boolean sonLinealmenteDependientes(ListaEstatica listaVectores){
        double sumaDeUnVector = 0.0;
        for (int numeroDeVector = 0; numeroDeVector <= listaVectores.tope; numeroDeVector++){
            ListaNumerica listaEjemplar = ((ListaNumerica) listaVectores.informacion[numeroDeVector]);
            for (int valorDeVectorConsultado = 0; valorDeVectorConsultado <= listaEjemplar.tope; valorDeVectorConsultado++){
                sumaDeUnVector = sumaDeUnVector + (((double) listaEjemplar.informacion[valorDeVectorConsultado])*((double) informacion[numeroDeVector]));
                SalidaPorDefecto.terminal("Suma del Vector "+numeroDeVector+" :"+sumaDeUnVector+"\n");
            }
        }
        if (sumaDeUnVector == 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Metodo que checa que los vectores de una lista de vectores sea linealmente independientes
     * respecto a los esclares del arreglo actual.
     * @param listaVectores Lista con los vectores a validar.
     * @return Se regresa true si es L.I., false si no lo son.
     */
    public boolean sonLinealmenteIndependientes(ListaEstatica listaVectores){
        int cantidadDeEscalaresNulos = contar(0.0);
        if (cantidadDeEscalaresNulos == cantidad()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Metodo que verifica que dos vectores sean paralelos.
     * @param lista2 Lista (Vector) que tiene otros valores.
     * @return Regresa True si si es paralelo, false si no.
     */
    public boolean esParalelo(ListaNumerica lista2){
        if (tope == lista2.tope) {
            double valorEscalar = 0.0;
            ListaNumerica listaValoresEsclares = new ListaNumerica(tope + 1);
            for (int posicionVector = 0; posicionVector <= tope; posicionVector++) {
                if (((double) lista2.informacion[posicionVector]) != 0.0) {
                    valorEscalar = ((double) informacion[posicionVector]) / ((double) lista2.informacion[posicionVector]);
                    listaValoresEsclares.agregar(valorEscalar);
                } else {
                    SalidaPorDefecto.terminal("No se pudo dividir ");
                    return false; //en caso que no se pueda dividir entre 0
                }
            }
            int totalEscalares = listaValoresEsclares.contar(valorEscalar);
            if (totalEscalares == listaValoresEsclares.tope+1){ //verifica que se tengan los mismos escalares
                int contadorIguales = 0;
                for (int posicion = 0; posicion<tope; posicion++){
                    if ( ((double) informacion[posicion]) == valorEscalar*((double) lista2.informacion[posicion]) ){ //verificar que el producto del escalar lista2 sea igual a todos los valores del tope actual
                        contadorIguales = contadorIguales + 1;
                    }
                }
                if (contadorIguales == tope){
                    return true;
                } else {
                    return false;
                }
            } else {
                SalidaPorDefecto.terminal("Algun escalar es diferente. ");
                return false;
            }
        }
        return false;
    }

    public Object verUltimo(){
        return null;
    }

    /**
     * Metodo que agrega los datos de un buffer de tramas a una lista Numerica.
     * @param buffer Arreglo tipo double del cual se van a copiar datos.
     */
    @Override
    public void guardarDatosAlArreglo(double[] buffer){
        super.guardarDatosAlArreglo(buffer);
    }

    /**
     * Metodo que hace una copia del arreglo actual en un arreglo de tipo Object
     * @return Regresa un arreglo tipo Object[] del mismo tamanio y elementos que
     * el actual arreglo de "informacion".
     */
    @Override
    public Object[] leerArreglo(){
        Object[] arregloCopia = new Object[tamanioNuevosArreglos]; //Inicializamos el arreglo a copiar.
        SalidaPorDefecto.terminal("Nuevo tamanio: "+tamanioNuevosArreglos);
        for (int posicionInformacion = 0; posicionInformacion < tamanioNuevosArreglos; posicionInformacion++){
            arregloCopia[posicionInformacion] = informacion[posicionInformacion];
        }
        return arregloCopia;
    }

    /**
     * Metodo que hace el promedio de los valores de una lista Numerica.
     * @return regresa el promedio en entero.
     */
    public int promediarValoresNumericos(){
        double suma = 0.0;
        for (int valor = 0; valor<=tope; valor++){
            suma = suma + ((double) obtener(valor));
        }
        int resultado = (int) (suma/(tope+1));
        return resultado;
    }
    /**
     * Metodo que obtiene el tamanio del arreglo a crear con leerArreglo
     * @return Se regresa un int del tamanio
     */
    public int obtenerTamanioNuevoArreglo(){return tamanioNuevosArreglos;}

    public void agregarTamanioNuevosArreglos(int valor){tamanioNuevosArreglos = valor;}


}


