package BaseDeDatos.BusquedaTablas;

import entradasalida.SalidaPorDefecto;
import estructurasnolineales.ArbolBinario;
import estructurasnolineales.ArbolBinarioBusqueda;
import estructurasnolineales.auxiliares.NodoBusquedaArbol;

import java.io.*;

public class BusquedaTablasBD {
    protected ArbolBinarioBusqueda arbol;
    protected long ultimaDireccionMemoria;

    public BusquedaTablasBD(){
        arbol = new ArbolBinarioBusqueda();
        ultimaDireccionMemoria = 0;
    }

    /**
     * Metodo que unicamente lee la tabla, es el ejemplo de la practica.
     */
    public void lectorTablas(){
        boolean finArchivo = false;
        RandomAccessFile archivo = null;
        try {
            archivo = new RandomAccessFile("src\\BaseDeDatos\\datos_ordenes\\customers.csv", "r");
            System.out.println("El tamaño es: " + archivo.length());
            String cad="";
            while(true) {
                System.out.print("Pos: "+archivo.getFilePointer());
                cad = archivo.readLine();
                if (cad==null)
                    break;
                System.out.println(" - " +cad);
            }
            //Obtiene una posicion especifica
            System.out.println("\nObteniendo posición 9071: ");
            archivo.seek(9071);
            System.out.println(archivo.readLine());
            System.out.println("\n");
            archivo.close();
        } catch (IOException fe) {
            System.err.println(fe.getMessage()+". Error: No se encontro el archivo");
        }
    }

    /**
     * Metodo que guarda las direcciones de memoria en<br/>
     * el arbol binario de busqueda.
     */
    public void guardar(){
        boolean finArchivo = false;
        RandomAccessFile archivo = null;
        try {
            archivo = new RandomAccessFile("src\\BaseDeDatos\\datos_ordenes\\customers.csv", "r");
            System.out.println("El tamaño es: " + archivo.length());
            String cad="";
            long identificador = 0;
            while(true) {
                //System.out.print("Pos: "+archivo.getFilePointer());
                if (archivo.getFilePointer() != 0){
                    NodoBusquedaArbol agregado = new NodoBusquedaArbol();
                    agregado.setDireccionMemoria(archivo.getFilePointer());
                    agregado.setIndice(obtenerIDRegistro(archivo.readLine()));
                    boolean resultado = arbol.agregar(agregado);
                    ultimaDireccionMemoria = archivo.getFilePointer();
                    //SalidaPorDefecto.terminal("\nAgregado: "+agregado+", Resultado: "+resultado);
                }
                cad = archivo.readLine();
                if (cad==null)
                    break;
                //System.out.println(" - " +cad);
            }
            /*Obtiene una posicion especifica
            System.out.println("\nObteniendo posición 2150: ");
            archivo.seek(2150);
            System.out.println(archivo.readLine());
            System.out.println("\n");
            archivo.close();
             */
            SalidaPorDefecto.terminal("Registros guardados.\n");
            archivo.close();
        } catch (IOException fe) {
            System.err.println(fe.getMessage()+". Error: No se encontro el archivo");
        }
    }

    /**
     * Metodo que permite buscar un registro por su identificador de nodo de busqueda.
     * @param identificador Identificador del nodo.
     */
    public void obtenerRegistroPorID(long identificador){
        NodoBusquedaArbol regresado = (NodoBusquedaArbol) arbol.buscar(identificador);
        RandomAccessFile archivo = null;
        if (regresado != null){
            try {
                archivo = new RandomAccessFile("src\\BaseDeDatos\\datos_ordenes\\customers.csv", "r");
                SalidaPorDefecto.terminal("Posicion de Memoria: "+regresado.getDireccionMemoria()+" - ");
                archivo.seek(regresado.getDireccionMemoria());
                SalidaPorDefecto.terminal(archivo.readLine());
            } catch (FileNotFoundException e) {
                SalidaPorDefecto.error("Error: Archivo no encontrado.");
            } catch (IOException e) {
                SalidaPorDefecto.error("Excepcion no controlada: "+e.getMessage());
            }
        }else{
            SalidaPorDefecto.terminal("\nEl identificador especificado no ha sido encontrado.");
        }
    }

    /**
     * Metodo que obtiene la ID de un registro.
     * @param consulta Registro a agregar
     * @return Regresa en long la ID
     */
    private long obtenerIDRegistro(String consulta){
        String identificador = "";
        for (int cadaCaracter = 0; cadaCaracter<consulta.length(); cadaCaracter++){
            char caracter = consulta.charAt(cadaCaracter);
            if (Character.isDigit(caracter) == true){
                identificador += caracter;
            } else if (caracter == ',') {
                break;
            }
        }
        if (identificador.equals("") == false){
            return Long.parseLong(identificador);
        } else {
            return 0;
        }
    }

    /**
     * Metodo que permite agregar un registro al archivo y al arbol.
     * @param identificador Identificador a asignarle
     * @param registro Registro (Contenido de los datos) a agregar.
     */
    public void agregarRegistro(long identificador, String registro){
        SalidaPorDefecto.terminal("Ultima direccion de memoria: "+ultimaDireccionMemoria);
        Object resultado = arbol.buscar(identificador);
        if (resultado == null){
            NodoBusquedaArbol nuevoNodoBusqueda = new NodoBusquedaArbol();
            nuevoNodoBusqueda.setIndice(identificador);
            ultimaDireccionMemoria = ultimaDireccionMemoria+1;
            nuevoNodoBusqueda.setDireccionMemoria(ultimaDireccionMemoria);
            arbol.agregar(nuevoNodoBusqueda);
            SalidaPorDefecto.terminal("\nRegistro Agregado.");
            try {
                BufferedWriter escritor = new BufferedWriter(new FileWriter("src\\BaseDeDatos\\datos_ordenes\\customers.csv", true));
                escritor.append(identificador+registro);
                escritor.newLine();
                escritor.close();
            } catch (IOException e) {
                SalidaPorDefecto.error("Error al escribir el registro. (Error: "+e.getMessage()+")");
            }
        } else {
            SalidaPorDefecto.terminal("\nEl identificador especificado ya existe.");
        }
    }

    /**
     * Metodo que permite eliminar un registro por medio de su llave primaria.
     * @param identificador Identificador del registro a eliminar.
     */
    public void eliminarRegistro(long identificador){
        arbol.eliminar(identificador);
    }

    /**
     * Metodo que permite buscar un registro por cierto valor de su columna.
     * @param nombreColumna Nombre de la columna a buscar.
     * @param dato Dato a Buscar en la columna.
     */
    public void obtenerPorValor(String nombreColumna, String dato){
        RandomAccessFile archivo = null;
        String cad = "";
        try{
            ArbolBinarioBusqueda repetidos = obtenerRepetidos(nombreColumna, dato);
            archivo = new RandomAccessFile("src\\BaseDeDatos\\datos_ordenes\\orders.csv", "r");
            while (true){
                cad = archivo.readLine();
                if (cad != null){
                    NodoBusquedaArbol valor = (NodoBusquedaArbol) repetidos.buscar(obtenerIDRegistro(cad));
                    if (valor != null){
                        archivo.seek(valor.getDireccionMemoria());
                        SalidaPorDefecto.terminal(archivo.readLine());
                        SalidaPorDefecto.terminal("\n");
                    }
                }
                if (cad==null)
                    break;
            }
        } catch (IOException fe){
            SalidaPorDefecto.error("Archivo no encontrado.");
        }
    }

    /**
     * Metodo que guarda todos los valores repetidos en un arbol.
     * @param nombreABuscar Nombre de la columna a buscar.
     * @param valorBuscado Valor a buscar del arbol.
     */
    private ArbolBinarioBusqueda obtenerRepetidos(String nombreABuscar, String valorBuscado){
        boolean finArchivo = false;
        RandomAccessFile archivo = null;
        String cad = "";
        try {
            archivo = new RandomAccessFile("src\\BaseDeDatos\\datos_ordenes\\orders.csv", "r");
            cad = archivo.readLine(); //leer los encabezados
            int posicion = obtenerPosicionColumna(cad, nombreABuscar);
            if (posicion >= 1){ //se encontro la posicion
                ArbolBinarioBusqueda repetidos = new ArbolBinarioBusqueda();
                while(true) {
                    cad = archivo.readLine();
                    if (cad != null){
                        long indice = obtenerIDRegistro(cad);
                        agregarLosRepetidos(repetidos, posicion, valorBuscado, cad, archivo.getFilePointer(), indice);
                    }
                    if (cad==null)
                        break;
                }
                return repetidos;
            } else {
                SalidaPorDefecto.terminal("No existe esa columna.");
            }
            archivo.close();
        } catch (IOException fe) {
            System.err.println(fe.getMessage()+". Error: No se encontro el archivo");
        }
        return null;
    }

    /**
     * Metodo privado que obtiene la posicion de la columna.
     * @param nombresColumnas Nombre de todas las columnas a verificar.
     * @param nombreBuscado Nombre buscado
     * @return Devuelve la posicion en la que esta la columna buscada.
     */
    private int obtenerPosicionColumna(String nombresColumnas, String nombreBuscado){
        if (nombresColumnas.equals("") == false){
            int contadorColumna = 1;
            String nombreColumna = "";
            for (int cadaCaracter = 0; cadaCaracter<nombresColumnas.length(); cadaCaracter++){
                char caracter = nombresColumnas.charAt(cadaCaracter);
                if (Character.isLetter(caracter) == true || caracter == '_'){
                    nombreColumna += caracter;
                }
                if (caracter == ','){
                    contadorColumna++;
                    nombreColumna = "";
                }
                if (nombreColumna.equals(nombreBuscado) == true){
                    break;
                }
            }
            return contadorColumna;
        }else{
            return -1;
        }
    }

    /**
     * Metodo que agrega a los elementos repetidos a un arbol.
     * @param arbol Arbol al cual agregar.
     * @param posicion Posicion de la columna a buscar
     * @param valorBuscado Valor a buscar.
     */
    private void agregarLosRepetidos(ArbolBinarioBusqueda arbol, int posicion, String valorBuscado, String registro, long direccionMem, long indice){
        int posicionActual = 1;
        String buscado = "";
        for (int cadaCaracter = 0; cadaCaracter<registro.length(); cadaCaracter++){
            char caracter = registro.charAt(cadaCaracter);
            if (posicionActual == posicion){ //buscar en esa columna
                if (Character.isLetter(caracter) || Character.isDigit(caracter) || caracter == '_'){
                    buscado += caracter;
                }
            }
            if (caracter == ','){ //incrementar la posicion actual
                buscado = "";
                posicionActual++;
            }
            if (buscado.equals(valorBuscado) == true){ //meter a repetidos
                NodoBusquedaArbol nuevoNodo = new NodoBusquedaArbol();
                nuevoNodo.setIndice(indice);
                nuevoNodo.setDireccionMemoria(direccionMem);
                arbol.agregar(nuevoNodo);
                break;
            }
        }
    }

}
