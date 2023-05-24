package registros.centros_computo;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaDinamica;
import estructurasLineales.ListaEstatica;

/**
 * Clase que contiene los datos del centro de computo
 */
public class CentroComputo {
    protected int numero_cc;
    protected ListaDinamica computadoras;
    protected ListaDinamica usuarios;

    public CentroComputo(int numero_cc){
        this.numero_cc = numero_cc;
        computadoras = new ListaDinamica();
        usuarios = new ListaDinamica();
    }

    /**
     * Metodo que agrega una computadora a la lista de las que tiene.
     * @param computadora Objeto computadora
     * @return Regresa 1 si se pudo agregar. -1 si no.
     */
    public int agregarComputadora(Computadora computadora){
        if (verificarComputadoraExistente(computadora.getNumero_computadora()) == null){ //verificar si no existe
            //3. Si no esta, agregamos
            return computadoras.agregar(computadora);
        } else {
            //4. Si si esta, ps no
            return -1;
        }
    }

    /**
     * Metodo que permite eliminar una computadora dado su numero
     * @param numeroComputadora Numero de la Compu a eliminar
     * @return Regresa un objeto de la PC eliminada.
     */
    public Computadora eliminarComputadora(int numeroComputadora){
        Computadora obtenida = verificarComputadoraExistente(numeroComputadora);
        if (obtenida != null){
            return (Computadora) computadoras.eliminar(obtenida);
        } else { //si se puede eliminar
            return null;
        }
    }

    /**
     * Metodo que imprime toda la info de las PCs
     */
    public void imprimirTodasCompus(){
        for (int cadaPC = 0; cadaPC<computadoras.cantidad(); cadaPC++){
            Computadora computadora = (Computadora) computadoras.obtener(cadaPC);
            SalidaPorDefecto.terminal("\n"+computadora+"\n");
            computadora.imprimirApps();
            SalidaPorDefecto.terminal("\n");
            computadora.imprimirUsuarios();
        }
    }

    /**
     * Metodo que imprime los datos de una sola computadora
     * @param numeroComputadora Numero de la computadora.
     */
    public void imprimirComputadora(int numeroComputadora){
        Computadora encontrada = (Computadora) verificarComputadoraExistente(numeroComputadora);
        if (encontrada != null){ //si existe
            SalidaPorDefecto.terminal(encontrada+"\n");
            encontrada.imprimirApps();
            SalidaPorDefecto.terminal("\n");
            encontrada.imprimirUsuarios();
        }else{
            SalidaPorDefecto.terminal("No existe esa Computadora.");
        }
    }

    /**
     * Metodo protegido que verifica si una computadora existe.
     * @param numeroComputadora Numero de la computadora a verificar
     * @return Regresa True si si existe, false si no.
     */
    public Computadora verificarComputadoraExistente(int numeroComputadora){
        for (int cadaPc = 0; cadaPc<computadoras.cantidad(); cadaPc++){
            Computadora temporal = (Computadora) computadoras.obtener(cadaPc);
            if (numeroComputadora == temporal.getNumero_computadora()){
                return temporal;
            }
        }
        return null;
    }

    /**
     * Metodo que permite agregar una app a una computadora.
     * @param numeroComputadora numero de computadora a la cual se agregara.
     * @param app Aplicacion a agregar.
     * @return Regresa 1 si se pudo, -1 si no.
     */
    public int agregarApps(int numeroComputadora, Aplicacion app){
        Computadora obtenida = verificarComputadoraExistente(numeroComputadora);
        if (obtenida != null){
            return obtenida.agregarAplicaciones(app);
        } else {
            return -1;
        }
    }

    /**
     * Metodo que permite eliminar una app dado el numero de computadora.
     * @param numeroComputadora Numero de Computadora.
     * @param nombre Nombre de la Aplicacion a quitar.
     * @return Regresa la Aplicacion quitada, o null si no.
     */
    public Aplicacion eliminarApps(int numeroComputadora, String nombre){
        Computadora obtenida = verificarComputadoraExistente(numeroComputadora);
        if (obtenida != null){
            return obtenida.eliminarAplicaciones(nombre);
        } else {
            return null;
        }
    }

    /**
     * Metodo que agrega a un usuario a la compu
     * @param numeroComputadora Numero de la Computadora a agregar.
     * @param usuario Objeto Usuario a agregar.
     * @return Regresea 1 si se agrego, -1 si no.
     */
    public int agregarUsuarios(int numeroComputadora, Usuario usuario){
        Computadora obtenida = verificarComputadoraExistente(numeroComputadora);
        if (obtenida != null){
            usuarios.agregar(usuario);
            return obtenida.agregarUsuario(usuario);
        }else{
            return -1;
        }
    }

    /**
     * Metodo que verifica si una PC tiene la Aplicacion que se paso por parametros
     * @param nombreApp Nombre de la App
     * @return Lista Dinamica con la app.
     */
    public ListaDinamica aplicacionABuscar(String nombreApp){
        ListaDinamica computadorasConLaApp = new ListaDinamica();
        for (int cadaCompu = 0; cadaCompu<computadoras.cantidad(); cadaCompu++){
            Computadora temporal = (Computadora) computadoras.obtener(cadaCompu);
            if (temporal.obtenerAplicacionXNombre(nombreApp) != null){ //si la computadora actual tiene la app solicitada
                computadorasConLaApp.agregar(temporal.obtenerDatosAbreviados());
            }
        }
        return computadorasConLaApp;
    }

    /**
     * Metodo que obtiene a todos los usuarios con el mismo nombre en la lista.
     * @param nombreUsuario Nombre del usuario.
     * @return Regresa una lista con usuarios.
     */
    public ListaDinamica obtenerUsuario(String nombreUsuario){
        ListaDinamica usuariosConElMismoNombre = new ListaDinamica();
        for (int cadaComputadora = 0; cadaComputadora<computadoras.cantidad(); cadaComputadora++){
            Computadora computadora_obtenida = (Computadora) computadoras.obtener(cadaComputadora);
            for (int cadaUsuario = 0; cadaUsuario<computadora_obtenida.usuarios.cantidad(); cadaUsuario++){
                Usuario usuario_obtenido = (Usuario) computadora_obtenida.usuarios.obtener(cadaUsuario);
                if((usuario_obtenido != null)&&(nombreUsuario.equals(usuario_obtenido.getNombre()))){
                    String resultado = "Usuario "+usuario_obtenido.getNombre()+": Computadora "+computadora_obtenida.getNumero_computadora()+", del CC "+numero_cc+", Fecha: "+usuario_obtenido.getDiaUso()+", Hora Inicio: "+usuario_obtenido.getHoraInicio()+", Hora Fin: "+usuario_obtenido.getHoraFin();
                    usuariosConElMismoNombre.agregar(resultado);
                }
            }
        }
        if (usuariosConElMismoNombre.vacia() == false){
            return usuariosConElMismoNombre;
        } else {
            return null;
        }
    }
    public int getNumero_cc() {
        return numero_cc;
    }

    public String toString(){
        return "Centro Computo "+numero_cc;
    }

    public int getCantidadPCs(){
        return computadoras.cantidad();
    }

    /**
     * Metodo protegido que puede obtener una PC por un indice en la lista de computadoras.
     * @param indice Indice de la computadora.
     * @return Regresa a la computadora Solicitada.
     */
    protected Computadora obtenerComputadoraXIndice(int indice){
        return (Computadora) computadoras.obtener(indice);
    }

    /**
     * Metodo que busca a un usuario de este CC por su nombre.
     * @param nombre Nombre del Usuario.
     * @return Regresa un usuario si se encontro.
     */
    private Usuario buscarUsuario(String nombre){
        for (int cadaUsuario = 0; cadaUsuario<usuarios.cantidad(); cadaUsuario++){
            Usuario usuario_obtenido = (Usuario) usuarios.obtener(cadaUsuario);
            if (usuario_obtenido.getNombre().equals(nombre)){
                return usuario_obtenido;
            }
        }
        return null;
    }
}
