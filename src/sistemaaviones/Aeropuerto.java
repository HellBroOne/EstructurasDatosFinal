package sistemaaviones;

public class Aeropuerto {
    protected String ciudad;
    protected String pais;

    public Aeropuerto(String ciudad, String pais){
        this.ciudad = ciudad;
        this.pais = pais;
    }

    public String toString(){
        return "Aeropuerto en "+ ciudad +" del pais "+ pais;
    };

    public String getCiudad() {
        return ciudad;
    }

}
