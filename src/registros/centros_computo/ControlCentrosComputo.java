package registros.centros_computo;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaDinamica;

/**
 * Clase controladora que permite <br/>
 * llevar un registro de Centros de Computo.
 */
public class ControlCentrosComputo {
    protected ListaDinamica centros_computo;
    protected ListaDinamica usuariosTotales;
    public ControlCentrosComputo(int cantidad_centros_computo){
        centros_computo = new ListaDinamica();
        for (int centro = 1; centro <= cantidad_centros_computo; centro++){
            CentroComputo centroNuevo = new CentroComputo(centro);
            centros_computo.agregar(centroNuevo);
        }
        usuariosTotales = new ListaDinamica();
    }

    /**
     * Metodo que agrega un centro de computo<br/>
     * a la lista de centros de computo.
     * @param numero_cc Numero de centro a agregar.
     * @return
     */
    public int agregarCentroComputo(int numero_cc){
        for (int cadaCentro = 0; cadaCentro<centros_computo.cantidad(); cadaCentro++){
            CentroComputo cc_temporal = (CentroComputo) centros_computo.obtener(cadaCentro);
            if (cc_temporal.getNumero_cc() == numero_cc){ //si hay un cc con el mismo numero al que se quiere crear, no se podra
                return -1;
            }
        }
        //si se recorrieron todos los cc y nunca se encontro uno igual, no esta y se crea el nuevo
        CentroComputo centroNuevo = new CentroComputo(numero_cc);
        return centros_computo.agregar(centroNuevo);
    }

    /**
     * Elimina el cc dado por un numero especifico
     * @param numero_cc numero del centro computo a encontrar.
     * @return Regresa el objeto eliminado.
     */
    public Object eliminarCentroComputo(int numero_cc){
        for (int cadaCentro = 0; cadaCentro<centros_computo.cantidad(); cadaCentro++){
            CentroComputo cc_temporal = (CentroComputo) centros_computo.obtener(cadaCentro);
            if (cc_temporal.getNumero_cc() == numero_cc){ //si hay un cc con el mismo numero entonces se encontro y es posible eliminarlo.
                return centros_computo.eliminar(cc_temporal);
            }
        }
        return null;
    }

    /**
     * Metodo que permite agregar una computadora, pero dandola de alta con sus datos
     * @param numeroComputadora Numero de la computadora (no debe repetirse).
     * @param numeroCentroComputo Numero del centro de computo al que se va a agregar la Computadora.
     * @param ram Ram de la computadora.
     * @param espacio_disco Espacio del Disco Duro de la Computadora.
     * @param procesador Nombre del procesador de la Computadora.
     * @param marca Nombre de la marca de la Computadora.
     * @return Regresa -1 si:<br/>
     *  - No se pudo encontrar el Centro de Computo especificado<br/>
     *  - No se pudo agregar la computadora (esta repetida).
     */
    public int agregarComputadora(int numeroComputadora, int numeroCentroComputo, double ram, double espacio_disco, String procesador, String marca){
        CentroComputo obtenido = obtenerCentroComputo(numeroCentroComputo); //obtener el cc por su numero
        if (obtenido != null){
            Computadora computadora = new Computadora(numeroComputadora, numeroCentroComputo, ram, espacio_disco, procesador, marca);
            return obtenido.agregarComputadora(computadora);
        }else{
            return -1;
        }
    }

    /**
     * Metodo que da de baja a una computadora, por su numero
     * @param numeroCentroComputo Numero del CC del cual se va a quitar la computadora.
     * @param numeroComputadora Numero de la computadora a quitar.
     * @return Regrea null si no se encontro el CC o si no se encontro la computadora<br/>
     * especificada. Regresa la computadora eliminada que se quito.
     */
    public Computadora eliminarComputadora(int numeroCentroComputo, int numeroComputadora){
        CentroComputo obtenido = obtenerCentroComputo(numeroCentroComputo);
        if (obtenido!=null){
            return obtenido.eliminarComputadora(numeroComputadora);
        } else {
            return null;
        }
    }

    /**
     * Metodo que imprime a todas las computadoras de un Centro de Computo.
     * @param numeroCentroComputo Numero del Centro de Computo
     */
    public void imprimirComputadorasXCentroComputo(int numeroCentroComputo){
        CentroComputo obtenido = obtenerCentroComputo(numeroCentroComputo);
        if (obtenido != null){
            obtenido.imprimirTodasCompus();
        }else{
            SalidaPorDefecto.terminal("No existe ese Centro de Computo.");
        }
    }

    /**
     * Metodo que imprime a una computadora de un Centro de Computo.
     * @param numeroCentroComputo Numero del Centro de Computo
     */
    public void imprimirComputadorasXNumero(int numeroCentroComputo, int numeroComputadora){
        CentroComputo obtenido = obtenerCentroComputo(numeroCentroComputo);
        if (obtenido != null){
            obtenido.imprimirComputadora(numeroComputadora);
        }else{
            SalidaPorDefecto.terminal("No existe ese Centro de Computo.");
        }
    }

    /**
     * Metodo que permite ver los Centros de Computo agregados
     */
    public void imprimirListaCC(){
        if (centros_computo.vacia() == true){
            SalidaPorDefecto.terminal("No hay ningun Centro de Computo.\nPuedes agregar un centro de computo con el metodo de\nagregarCentroComputo.");
        }else{
            centros_computo.imprimir();
        }
    }

    /**
     * Metodo que permite agregar una Aplicacion a una computadora.
     * @param numeroCentroComputo Numero del Centro de computo de la Computadora a Agregar.
     * @param numeroComputadora Numero de la Computadora a Agregar.
     * @param nombre Nombre de la Aplicacion.
     * @param logo Logo de la Aplicacion.
     * @param version Version de la Aplicacion.
     * @param ram_requerida Memoria RAM de la Aplicacion.
     * @param autores Cadena con autores de la Aplicacion.
     * @return Regresa 1 si se pudo agregar, -1 si no.
     */
    public int agregarAplicacion(int numeroCentroComputo, int numeroComputadora, String nombre, String logo, double version, double ram_requerida, String autores){
        CentroComputo obtenido = obtenerCentroComputo(numeroCentroComputo);
        if (obtenido != null){
            Aplicacion appNueva = new Aplicacion(nombre, logo, version, ram_requerida, autores);
            return obtenido.agregarApps(numeroComputadora, appNueva);
        }else{
            return -1;
        }
    }

    /**
     * Metodo que registra un usuario.
     * @param numeroCentroComputo Numero del Centro de Computo agregado.
     * @param numeroComputadora Numero de Computadora.
     * @param nombre Nombre del usuario.
     * @param diaUso Numero de dia de uso. (Entre 1 y 31)
     * @param mesUso Numero de mes de uso. (Entre 1 y 12)
     * @param anioUso Numero de anio de uso. (Entre 1971 y 2023)
     * @param horaInicio Hora de Inicio. (Solo la hora del dia 0 a 23)
     * @param horaFin Hora de Fin. (Solo la hora del dia 0 a 23)
     * @param apps_abiertas Lista con las apps abiertas.
     * @return Regresa 1 cuando se agrega.
     */
    public int agregarUsuario(int numeroCentroComputo, int numeroComputadora, String nombre, int diaUso, int mesUso, int anioUso, int horaInicio, int horaFin, ListaDinamica apps_abiertas){
        if (! ((horaInicio >= 0) && (horaInicio <= 23)) ){
            return -1;
        }
        if (! ((horaFin >= 0) && (horaFin <= 23)) ){
            return -1;
        }
        if (! ((diaUso >= 1) && (diaUso <= 31)) ){
            return -1;
        }
        if (! ((mesUso >= 1) && (mesUso <= 12)) ){
            return -1;
        }
        if (! ((anioUso >= 1971) && (anioUso <= 2023)) ){
            return -1;
        }
        if (apps_abiertas.vacia() == true){
            return -1;
        }
        CentroComputo obtenida = obtenerCentroComputo(numeroCentroComputo);
        if (obtenida != null){
            Usuario usuario = new Usuario(nombre, diaUso, mesUso, anioUso, horaInicio, horaFin, apps_abiertas);
            usuariosTotales.agregar(usuario);
            return obtenida.agregarUsuarios(numeroComputadora, usuario);
        }else{
            return -1;
        }
    }

    /**
     * Metodo que imprime que dias ha usado un usuario cierta computadora.
     * @param nombre Nombre del usuario a buscar.
     */
    public void imprimirDiasUso(String nombre){
        ListaDinamica usuarios = new ListaDinamica();
        for (int cadaCC = 0; cadaCC<centros_computo.cantidad(); cadaCC++){
            CentroComputo cc_obtenido = (CentroComputo) centros_computo.obtener(cadaCC);
            usuarios.agregarLista(cc_obtenido.obtenerUsuario(nombre));
        }
        if (usuarios.vacia() == true){
            SalidaPorDefecto.terminal("\nNo hay ningun usuario "+nombre+".");
        } else {
            SalidaPorDefecto.terminal("\nUsada en: ");
            for (int cadaObtenido = 0; cadaObtenido<usuarios.cantidad(); cadaObtenido++){
                SalidaPorDefecto.terminal("\n"+usuarios.obtener(cadaObtenido));
            }
        }
    }

    /**
     * Metodo que imprime las aplicaciones que un usuario uso por fecha.
     * @param fecha Fecha dada como argumento.
     * @param nombreUsuario Nombre del usuario a buscar.
     */
    public void imprimirAplicacionesDeUsuarioPorfecha(String fecha, String nombreUsuario){
        for (int cadaCC = 0; cadaCC<centros_computo.cantidad(); cadaCC++){
            CentroComputo cc_obtenido = (CentroComputo) centros_computo.obtener(cadaCC);
            for (int cadaComputadora = 0; cadaComputadora<cc_obtenido.computadoras.cantidad(); cadaComputadora++){
                Computadora computadora = (Computadora) cc_obtenido.computadoras.obtener(cadaComputadora);
                for (int cadaUsuario = 0; cadaUsuario<computadora.usuarios.cantidad(); cadaUsuario++){
                    Usuario usuario = (Usuario) computadora.usuarios.obtener(cadaUsuario);
                    if ((usuario.getDiaUso().equals(fecha))&&(usuario.getNombre().equals(nombreUsuario))){
                        SalidaPorDefecto.terminal("\nAplicaciones abiertas el "+fecha+": ");
                        usuario.imprimirAplicaciones();
                    }
                }
            }
        }
    }

    /**
     * Metodo que imprime que usuarios NO han usado un CC especifico.
     * @param numeroDeCentroComputo Numero del CC a verificar.
     */
    public void imprimirUsuariosQueNoUsanelCC(int numeroDeCentroComputo){
        CentroComputo obtenido = obtenerCentroComputo(numeroDeCentroComputo);
        if (usuariosTotales.vacia() == true){
            SalidaPorDefecto.terminal("Curiosamente, no tienes aun ningun usuario registrado.");
        } else {
            for (int cadaUsuario = 0; cadaUsuario<usuariosTotales.cantidad(); cadaUsuario++){
                Usuario usuarioTemp = (Usuario) usuariosTotales.obtener(cadaUsuario);
                if (obtenido.obtenerUsuario(usuarioTemp.getNombre()) == null){
                    SalidaPorDefecto.terminal("\nEl usuario "+usuarioTemp.getNombre()+" no ha usado nunca el CC "+numeroDeCentroComputo);
                }
            }
        }
    }

    /**
     * Metodo que permite agregar una Aplicacion a una computadora.
     * @param numeroCentroComputo Numero del Centro de computo de la Computadora a Agregar.
     * @param numeroComputadora Numero de la Computadora a Agregar.
     * @param nombre Nombre de la Aplicacion.
     * @return Regresa 1 si se pudo agregar, -1 si no.
     */
    public Aplicacion eliminarAplicacion(int numeroCentroComputo, int numeroComputadora, String nombre){
        CentroComputo obtenido = obtenerCentroComputo(numeroCentroComputo);
        if (obtenido != null){
            return obtenido.eliminarApps(numeroComputadora, nombre);
        }else{
            return null;
        }
    }

    /**
     * Imprime todas las Computadoras que tengan instalada una ap especifica.
     * @param nombreApp Nombre de la app a buscar en las PCs
     */
    public void imprimirComputadorasConAppInstalada(String nombreApp){
        ListaDinamica listaComp = obtenerComputadorasConAppInstalada(nombreApp);
        if (listaComp.vacia() == true){
            SalidaPorDefecto.terminal("\nNo hay ninguna Computadora con "+nombreApp+".\n");
        }else{
            SalidaPorDefecto.terminal("\nComputadoras con la app "+nombreApp+":\n");
            listaComp.imprimir();
            SalidaPorDefecto.terminal("\n");
        }
    }

    /**
     * Metodo privado que obtiene las computadoras con una cierta app y las regresa en forma de Lista Dinamica.
     * @param nombreApp Nombre de la app a buscar.
     * @return Regresa las PCs con la app instalada
     */
    private ListaDinamica obtenerComputadorasConAppInstalada(String nombreApp){
        ListaDinamica listaComp = new ListaDinamica();
        for (int cadaCC = 0; cadaCC<centros_computo.cantidad(); cadaCC++){
            CentroComputo actual = (CentroComputo) centros_computo.obtener(cadaCC);
            listaComp.agregarLista(actual.aplicacionABuscar(nombreApp));
        }
        return listaComp;
    }

    /**
     * Metodo que recibe una app y verifica en cuales pcs se pueden correr
     * @param app Objeto Aplicacion a verificar.
     */
    public void imprimirCualesCorrenLaApp(Aplicacion app){
        ListaDinamica comprobacion = obtenerComputadorasConAppInstalada(app.getNombre());
        if (comprobacion.vacia() == false){ //si no esta vacia es pq la app es una app instalada
            double ram = app.getRam_requerida();
            for (int cadaCC = 0; cadaCC<centros_computo.cantidad(); cadaCC++){
                CentroComputo cc_obtenido = (CentroComputo) centros_computo.obtener(cadaCC);
                for (int cadaPC = 0; cadaPC<cc_obtenido.getCantidadPCs(); cadaPC++){
                    Computadora pc_obtenida = cc_obtenido.obtenerComputadoraXIndice(cadaPC);
                    if (pc_obtenida.getRam() >= ram){
                        SalidaPorDefecto.terminal(" "+pc_obtenida.obtenerDatosAbreviados()+"\n");
                    }
                }
            }
        } else { //si esta vacia es pq la app no esta instalada
            SalidaPorDefecto.terminal("La aplicacion "+app.getNombre()+" no esta instalada.");
        }
    }

    /**
     * Metodo que imprime a los usuarios que han
     * @param numeroCentroComputo Numero del Centro de Computo de la PC a consultar.
     * @param numeroComputadora Numero de la computadora a Consultar sus usuarios.
     */
    public void usuariosDeComputadora(int numeroCentroComputo, int numeroComputadora){
        CentroComputo cc_obtenido = obtenerCentroComputo(numeroCentroComputo);
        if (cc_obtenido != null){
            SalidaPorDefecto.terminal("\nLos usuarios de la Computadora "+numeroComputadora+" son:\n");
            cc_obtenido.verificarComputadoraExistente(numeroComputadora).imprimirUsuarios();
            SalidaPorDefecto.terminal("\n");
        }else{
            SalidaPorDefecto.terminal("\nEl Centro de Computo especificado no existe.\n");
        }
    }

    /**
     * Metodo protegido que solo se usa para poder obtener un CC.
     * @param numeroCentroComputo Numero del CC a obtener.
     * @return Regresa el CC obtenido.
     */
    protected CentroComputo obtenerCentroComputo(int numeroCentroComputo){
        for (int cadaCC = 0; cadaCC<centros_computo.cantidad(); cadaCC++){
            CentroComputo obtenido = (CentroComputo) centros_computo.obtener(cadaCC);
            if (numeroCentroComputo == obtenido.getNumero_cc()){
                return obtenido;
            }
        }
        return null;
    }

}
