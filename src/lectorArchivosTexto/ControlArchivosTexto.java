package lectorArchivosTexto;

import entradasalida.EntradaPorDefecto;
import entradasalida.SalidaPorDefecto;
import entradasalida.archivos.ArchivoTexto;
import estructurasLineales.ColaEstatica;
import estructurasLineales.ListaEstatica;
import estructurasLineales.PilaEstatica;

import java.util.Objects;

/**
 * Clase que implementa un Controlador de Archivos de Texto,
 */
public class ControlArchivosTexto {

    protected ListaEstatica caracteres; /** Lista con los renglones del archivo */
    protected String rutaArchivo; /** Ruta del archivo a leer */
    protected String cadena; /** Cadena con lo que se va a leer */
    protected ColaEstatica errores; /** Cola de Errores */
    protected ListaEstatica varDeclaradas; /** Lista con todas las variables que hayan sido declaradas */

    public ControlArchivosTexto(){
        mostrarMenu();
    }

    /**
     * Metodo que muestra el menu para que el usuario ingrese que quiere leer.
     */
    public void mostrarMenu(){
        String respuesta = "";
        do{
            SalidaPorDefecto.terminal("Bienvenido, que desea hacer?\na) Leer archivo\nb) Leer cadena\nSu respuesta: ");
            String accion = EntradaPorDefecto.consolaCadenas();
            switch (accion){
                case "a": //Caso 1. Se lee el archivo
                    SalidaPorDefecto.terminal("\nIngresa la ruta del archivo: ");
                    rutaArchivo = EntradaPorDefecto.consolaCadenas();
                    caracteres = ArchivoTexto.leer(rutaArchivo);
                    if (verBalanceArchivos(caracteres) == false){
                        imprimirErrores(errores);
                    } else {
                        SalidaPorDefecto.terminal("Archivo balanceado.\n");
                    }
                    //if(checarVariablesDeclaradas(caracteres) == false){
                        //imprimirErrores(errores);
                    //} else {
                        //SalidaPorDefecto.terminal("Variables correctas.\n");
                    //}
                    break;
                case "b": //Caso 2. Se lee la cadena
                    SalidaPorDefecto.terminal("\nIngresa la cadena: ");
                    cadena = EntradaPorDefecto.consolaCadenas();
                    if (verBalanceCadena(cadena) == false){
                        imprimirErrores(errores);
                    } else {
                        SalidaPorDefecto.terminal("Esta balanceada.\n");
                    }
                    break;
                default: SalidaPorDefecto.terminal("\nIngrese una letra valida."); break;
            }
            SalidaPorDefecto.terminal("\nDeseas continuar? Escribe 'si' o 'no': ");
            respuesta = EntradaPorDefecto.consolaCadenas();
        }while (! Objects.equals(respuesta, "no") );
    }

    /**
     * Metodo que crea una cola con errores en caso que haya algun error.
     * @param cadena Cadena a checar el balance.
     * @return Regresa una cola con errores.
     */
    private boolean verBalanceCadena(String cadena){ //actualmente funciona sin /* o /**
        ColaEstatica errores = new ColaEstatica(cadena.length());
        PilaEstatica balanceos = new PilaEstatica(cadena.length());
        String simbolo = "";
        for (int cadaCaracter = 0; cadaCaracter < cadena.length(); cadaCaracter++){
            char caracter = cadena.charAt(cadaCaracter);
            //Si es de apertura
            if ((caracter == '(') || (caracter == '{') || (caracter == '[')){
                simbolo += caracter;
                balanceos.poner(simbolo);
                simbolo = "";
            }else if ((caracter == ')') || (caracter == '}') || (caracter == ']')){ //si es de cierre
                String anterior = (String) balanceos.verTope();
                if ((caracter == ')') && (anterior.equals("(")) ){
                    balanceos.quitar();
                } else if ((caracter == '}') && (anterior.equals("{")) ){
                    balanceos.quitar();
                } else if ((caracter == ']') && (anterior.equals("[")) ) {
                    balanceos.quitar();
                } else {
                    String error = "Error, te falto cerrar el '"+anterior+"'.";
                    errores.poner(error);
                    break;
                }
            } else if ((caracter == '/') || (caracter == '*')) { //si es uno de estos simbolos, se va a concatenar
                simbolo += caracter;
            } else if ((simbolo.equals("/**")) || (simbolo.equals("/*"))) { //si la cadena es un simbolo de ap
                balanceos.poner(simbolo);
                simbolo = "";
            } else if ((simbolo.equals("**/")) || (simbolo.equals("*/"))) { //si la cadena es un simbolo de cierre
                String anterior = (String) balanceos.verTope();
                if ((simbolo.equals("**/")) && (anterior.equals("/**"))){
                    balanceos.quitar();
                } else if ((simbolo.equals("*/")) && (anterior.equals("/*"))){
                    balanceos.quitar();
                } else {
                    String error = cadena+"\n^ Error, te falto cerrar el '"+anterior+"'.";
                    errores.poner(error);
                    break;
                }
            } else {
                simbolo = "";
            }
        }
        if ((simbolo.equals("/**")) || (simbolo.equals("/*"))) { //si la cadena es un simbolo de ap
            balanceos.poner(simbolo);
        } else if ((simbolo.equals("**/")) || (simbolo.equals("*/"))) { //si la cadena es un simbolo de cierre
            String anterior = (String) balanceos.verTope();
            if ((simbolo.equals("**/")) && (anterior.equals("/**"))){
                balanceos.quitar();
            } else if ((simbolo.equals("*/")) && (anterior.equals("/*"))){
                balanceos.quitar();
            } else {
                String error = cadena+"\n^ Error, te falto cerrar el '"+anterior+"'.";
                errores.poner(error);
            }
        }
        this.errores = errores;
        if (balanceos.vacio() == true){ //si no hay operadores ya en la pila, significa que se quitaron todos
            return true;
        } else { //si hay alguno, entonces no esta balanceada
            return false;
        }
    }

    /**
     * Metodo que cuenta el numero total de caracteres en el archivo
     * @param archivo Lista con el archivo.
     * @return Regresa el numero de caracteres del archivo
     */
    private int numeroTotalArchivo(ListaEstatica archivo){
        int total = 0;
        for (int cadaRenglonArchivo = 0; cadaRenglonArchivo<archivo.cantidad(); cadaRenglonArchivo++){
            String cadena = (String) archivo.obtener(cadaRenglonArchivo);
            total += cadena.length();
        }
        return total;
    }

    /**
     * Metodo que verifica el balance de un archivo
     * @param archivo Lista con los renglones del archivo
     * @return Regresa True si esta bien balanceada.
     */
    private boolean verBalanceArchivos(ListaEstatica archivo){
        //necesidades
        ColaEstatica erroresGuardados = new ColaEstatica(numeroTotalArchivo(archivo));
        PilaEstatica balances = new PilaEstatica(numeroTotalArchivo(archivo));
        for (int cadaRenglonArchivo = 0; cadaRenglonArchivo<archivo.cantidad(); cadaRenglonArchivo++){
            String simbolo = "";
            String cadenaLeida = (String) archivo.obtener(cadaRenglonArchivo); //obtener cada cadena y verificarla
            for (int cadaLetraRenglon = 0; cadaLetraRenglon<cadenaLeida.length(); cadaLetraRenglon++){ //leer la cadena
                ListaEstatica valorConLinea = new ListaEstatica(2);
                char unaLetra = cadenaLeida.charAt(cadaLetraRenglon);
                if ((unaLetra == '(') || (unaLetra == '{') || (unaLetra == '[')){ //si la letra actual es de apertura
                    valorConLinea.agregar(""+unaLetra); //agregamos la info en la pos 0
                    valorConLinea.agregar(cadaRenglonArchivo); //agregamos la linea en la pos 1
                    balances.poner(valorConLinea);
                    //unicamente se pone el parentesis
                } else if ((unaLetra == ')') || (unaLetra == '}') || (unaLetra == ']')) { //si la letra actual es de cierre
                    ListaEstatica comparado = (ListaEstatica) balances.verTope(); //aqui si hay demas un signo de cierre, bota un error.
                    String anterior = (String) comparado.obtener(0); //obtenemos el simbolo del anterior
                    if ((unaLetra == ')') && (anterior.equals("("))){ //si esta el parentesis de cierre, se quita el anterior
                        balances.quitar();
                    } else if ((unaLetra == '}') && (anterior.equals("{"))){ //si esta la llave de cierre, se quita el anterior
                        balances.quitar();
                    } else if ((unaLetra == ']') && (anterior.equals("["))){ //si esta el corchete de cierre, se quita el anterior
                        balances.quitar();
                    } else { //un error
                        int numeroLinea = (int) comparado.obtener(1);
                        String linea = (String) archivo.obtener(numeroLinea);
                        String error = "Linea "+(numeroLinea+1)+": "+linea+"\n^ Falto cerrar el '"+anterior+"'.";
                        erroresGuardados.poner(error);
                        balances.quitar();
                    }
                } else if ((unaLetra == '/') || (unaLetra == '*')){ //si la letra actual es un '/' o '*'
                    simbolo += unaLetra; //solo se concatena
                }  else { //si no es ninguno de los anteriores..
                    simbolo = "";
                }

                //Verificar la cadena "simbolo"
                if ((simbolo.equals("/**")) || (simbolo.equals("/*"))){ //si el simbolo es "/**" o "/*"
                    valorConLinea.agregar(simbolo); //agregamos la info en la pos 0
                    valorConLinea.agregar(cadaRenglonArchivo); //agregamos la linea en la pos 1
                    balances.poner(valorConLinea); //ponemos los valores
                    simbolo = ""; //reseteamos la cadena
                } else if ((simbolo.equals("**/")) || (simbolo.equals("*/"))) { //si es simbolo de cierre
                    ListaEstatica comparado = (ListaEstatica) balances.verTope(); //obtenemos la anterior
                    String anterior = (String) comparado.obtener(0); //obtenemos el simbolo del anterior
                    if ((simbolo.equals("**/") && (anterior.equals("/**")))) { //si son de cierre y apertura en anterior
                        balances.quitar();
                    } else if (((simbolo.equals("*/")) && (anterior.equals("/*")))) {
                        balances.quitar();
                    } else {
                        int numeroLinea = (int) comparado.obtener(1);
                        String linea = (String) archivo.obtener(numeroLinea);
                        String error = "Linea " +(numeroLinea+1)+ ": " + linea + "\n^ Falto cerrar el '" + anterior + "'.";
                        erroresGuardados.poner(error);
                        balances.quitar();
                    }
                }
            }
        }
        errores = erroresGuardados;
        if (balances.vacio() == true){ //si no hay simbolos
            return true;
        }else{
            return false;
        }
    }

    /**
     * Metodo que guarda una variable si esta declarada en el arreglo de variables declaradas
     * @param archivo Lista con los renglones del archivo
     */
    private void guardarVariablesDeclaradas(ListaEstatica archivo){
        varDeclaradas = new ListaEstatica(numeroTotalArchivo(archivo)); //creamos el tamanio del arreglo var declaradas
        for (int cadaRenglonArchivo = 0; cadaRenglonArchivo<archivo.cantidad();  cadaRenglonArchivo++){ //recorremos las lineas
           String cadenaActual = (String) archivo.obtener(cadaRenglonArchivo);
           String tipoDato = "";
           String nombreVariable = "";
           boolean esVariable = false;
           for (int cadaLetra = 0; cadaLetra<cadenaActual.length(); cadaLetra++){
               char letraActual = cadenaActual.charAt(cadaLetra);
               if (letraActual != ' '){
                   if (esVariable == false){ //si no es una variable
                       tipoDato += letraActual; //se agrega al tipo de dato
                   } else { //si no
                       if ( letraActual != ';'){
                           nombreVariable += letraActual; // se agrega al nombre de variable
                       }
                   }
                   if ((tipoDato.equals("int")) || (tipoDato.equals("boolean")) || (tipoDato.equals("String")) || (tipoDato.equals("double")) ){ //si se detecta un tipo de dato, significa que es variable
                       esVariable = true; //es variable
                   }
                   if (letraActual == ';'){ //si es punto y coma, se acaba la variable
                       if (! nombreVariable.equals("")){
                           varDeclaradas.agregar(nombreVariable); //y se agrega al arreglo de las declaradas
                       }
                   }
               } else {
                   tipoDato = ""; //se reinicia el tipo de dato
                   nombreVariable = ""; // y nombre de la var
               }
           }
        }
    }

    /**
     * Metodo que checa si las variables que se usan estan declaradas previamente
     * @return regresa true si se verifico que todas las variables usadas
     * estan declaradas.
     */
    private boolean checarVariablesDeclaradas(ListaEstatica archivo){
        guardarVariablesDeclaradas(archivo);
        ColaEstatica erroresGuardados = new ColaEstatica(numeroTotalArchivo(archivo));
        for (int cadaRenglonArchivo = 0; cadaRenglonArchivo<archivo.cantidad(); cadaRenglonArchivo++){ //recorrer todos los renglones
            String renglonObtenido = (String) archivo.obtener(cadaRenglonArchivo);
            String variableABuscar = "";
            boolean enUso = false;
            for (int cadaLetra = 0; cadaLetra<renglonObtenido.length(); cadaLetra++){
                char letra = renglonObtenido.charAt(cadaLetra);
                if (Character.isLetter(letra)){ //verificamos que se tenga que usar la variable en
                    variableABuscar += letra;
                    enUso = true;
                } else if ((letra == '=') || (letra == ' ') || (letra == ';')){ // si ya se acaba la sentencia.
                    enUso = false;
                }
                if (enUso == false){
                    //buscamos la variable
                    int posicion = (int) varDeclaradas.buscar(variableABuscar);
                    if (posicion < 0){
                        String error = "Linea "+cadaRenglonArchivo+": "+renglonObtenido+"\n^ Falto declarar la variable "+variableABuscar;
                        erroresGuardados.poner(error);
                    }
                    variableABuscar = "";
                }
            }
        }
        errores = erroresGuardados;
        if (erroresGuardados.vacio() == true){ //si no hubo error.
            return  true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que imprime los errores en la cola.
     * @param errores Cola con errores
     */
    private void imprimirErrores(ColaEstatica errores){
        SalidaPorDefecto.terminal("\nError\n");
        while (errores.vacio() == false){
            SalidaPorDefecto.terminal(errores.quitar()+"\n");
        }
    }

}

