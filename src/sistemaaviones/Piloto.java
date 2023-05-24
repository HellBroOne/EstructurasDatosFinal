package sistemaaviones;

public class Piloto {
    protected String nombre;
    protected String licencia;
    //protected String tipoavion;

    public Piloto(String nombre, String licencia){
        this.nombre = nombre;
        this.licencia = licencia;
    }

    public String getNombre(){ return nombre; }
    public String toString(){
        return "Nombre Piloto: " + nombre + "Licencia de Vuelo: " + licencia;
    }
}
