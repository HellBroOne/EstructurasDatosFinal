package registros.centros_computo;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaDinamica;

/**
 * Clase que representa un usuario en el programa.
 */
public class Usuario {
    protected String nombre;
    protected String diaUso;
    protected String horaInicio;
    protected String horaFin;
    protected ListaDinamica apps_abiertas;

    public Usuario(String nombre, int diaUso, int mesUso, int anioUso, int horaInicio, int horaFin, ListaDinamica apps){
        this.nombre = nombre;
        String fecha = diaUso+"/"+mesUso+"/"+anioUso;
        this.diaUso = fecha;
        String horaInicial = horaInicio+":00";
        String horaFinal = horaFin+":00";
        this.horaInicio = horaInicial;
        this.horaFin = horaFinal;
        this.apps_abiertas = apps;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDiaUso() {
        return diaUso;
    }

    public void setDiaUso(String diaUso) {
        this.diaUso = diaUso;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public ListaDinamica getApps_abiertas() {
        return apps_abiertas;
    }

    /**
     * Metodo que registra una Aplicacion abierta por el usuario
     * @param app Objeto Aplicacion
     * @return Regresa 1 si se pudo agregar la app.
     */
    public int agregarAplicacionesAbiertas(Aplicacion app){
        return apps_abiertas.agregar(app);
    }

    /**
     * Metodo que imprime las aplicaciones que un usuario uso
     */
    public void imprimirAplicaciones(){
        for (int cadaApp = 0; cadaApp < apps_abiertas.cantidad(); cadaApp++){
            SalidaPorDefecto.terminal("\n"+(apps_abiertas.obtener(cadaApp)));
        }
    }

    public String toString(){
        return nombre+", fecha: "+diaUso;
    }
}
