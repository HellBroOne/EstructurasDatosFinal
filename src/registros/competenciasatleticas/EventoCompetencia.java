package registros.competenciasatleticas;

public class EventoCompetencia {
    protected String nombre;
    protected String lugar;

    public EventoCompetencia(String nombre, String lugar) {
        this.nombre = nombre;
        this.lugar = lugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String toString(){
        return nombre;
    }
}
