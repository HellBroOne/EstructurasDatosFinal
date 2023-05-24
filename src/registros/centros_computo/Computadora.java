package registros.centros_computo;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaDinamica;

/**
 * Clase que representa los datos requeridos de una Computadora.
 */
public class Computadora {
    protected int numero_computadora;
    protected int numero_cc;
    protected double ram;
    protected double espacio_disco;
    protected String procesador;
    protected String marca;
    protected ListaDinamica aplicaciones;
    protected ListaDinamica usuarios;

    public Computadora(int numero_computadora, int numero_cc, double ram, double espacio_disco, String procesador, String marca){
        this.numero_computadora = numero_computadora;
        this.numero_cc = numero_cc;
        this.ram = ram;
        this.espacio_disco = espacio_disco;
        this.procesador = procesador;
        this.marca = marca;
        aplicaciones = new ListaDinamica();
        usuarios = new ListaDinamica();
    }

    public int getNumero_computadora(){
        return numero_computadora;
    }

    public double getRam() {
        return ram;
    }

    public String obtenerDatosAbreviados(){
        return "Computadora "+numero_computadora+" del CC "+numero_cc;
    }

    public String toString(){
        return "Computadora "+numero_computadora+":\n Del CC "
                +numero_cc+",\n RAM: "+ram+"\n HDD: "+espacio_disco+
                "\n Procesador: "+procesador+"\n Marca: "+marca;
    }

    /**
     * Metodo que imprime las aplicaciones instaladas en esta Computadora.
     */
    public void imprimirApps(){
        SalidaPorDefecto.terminal(" Apps instaladas: ");
        if (aplicaciones.vacia() == true){
            SalidaPorDefecto.terminal("Sin apps.");
        } else {
            aplicaciones.imprimir();
        }
    }

    /**
     * Metodo que imprime las usuarios de esta Computadora.
     */
    public void imprimirUsuarios(){
        SalidaPorDefecto.terminal(" Usuarios: ");
        if (usuarios.vacia() == true){
            SalidaPorDefecto.terminal("Sin usuarios.");
        }else{
            usuarios.imprimir();
        }
    }

    /**
     * Metodo que agrega una aplicacion a la lista de apps de la pc
     * @param app Objeto Aplicacion.
     * @return Regresa 1 si se pudo agregar, -1 si no.
     */
    public int agregarAplicaciones(Aplicacion app){
        return aplicaciones.agregar(app);
    }

    /**
     * Metodo que registra a un usuario de una PC
     * @param usuario Objeto Usuario agregado a los usuarios.
     * @return Regresa 1 si se agrego el usuario.
     */
    public int agregarUsuario(Usuario usuario){
        for (int cadaApp = 0; cadaApp<usuario.getApps_abiertas().cantidad(); cadaApp++){
            Object app = usuario.getApps_abiertas().obtener(cadaApp); //obtenemos cada app en las abiertas por el usuario
            if (aplicaciones.buscar(app) == null){ //si una app no esta instalada en esta pc
                return -2;
            }
        }
        if ((aplicaciones.vacia() == false) && (obtenerUsuarioXFecha(usuario.getDiaUso()) == null)){ //si el usuario no esta o si la lista esta vacia
            return usuarios.agregar(usuario);
        }else{
            return -1;
        }
    }

    /**
     * Metodo que elimina una aplicacion por su nombre
     * @param nombre Nombre de la app
     * @return Regresa la app Eliminada
     */
    public Aplicacion eliminarAplicaciones(String nombre){
        Aplicacion obtenida = obtenerAplicacionXNombre(nombre);
        if (obtenida != null){
            return (Aplicacion) aplicaciones.eliminar(nombre);
        } else {
            return null;
        }
    }

    /**
     * Metodo que obtiene una Aplicacion por su nombre.
     * @param nombre Nombre de la App buscada.
     * @return Regresa la app Obtenida.
     */
    public Aplicacion obtenerAplicacionXNombre(String nombre){
        for (int cadaApp = 0; cadaApp<aplicaciones.cantidad(); cadaApp++){
            Aplicacion appObtenida = (Aplicacion) aplicaciones.obtener(cadaApp);
            if (appObtenida.getNombre() == nombre){
                return appObtenida;
            }
        }
        return null;
    }

    /**
     * Metodo que obtiene a un usuario por fecha.
     * @param fecha Fecha que se va a buscar.
     * @return Regresa el Usuario buscado. O null si no.
     */
    public Usuario obtenerUsuarioXFecha(String fecha){
        for (int cadaApp = 0; cadaApp<usuarios.cantidad(); cadaApp++){
            Usuario usuarioObtenido = (Usuario) usuarios.obtener(cadaApp);
            if (usuarioObtenido.getDiaUso().equals(fecha)){
                return usuarioObtenido;
            }
        }
        return null;
    }

}
