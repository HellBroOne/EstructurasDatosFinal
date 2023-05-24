package registros.competenciasatleticas;

public class Corredor {
    protected int numCorredor;
    protected String nombre;
    protected int edad;
    protected String nacionalidad;

    public Corredor(int numCorredor, String nombre, int edad, String nacionalidad) {
        this.numCorredor = numCorredor;
        this.nombre = nombre;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
    }

    public int getNumCorredor() {
        return numCorredor;
    }

    public void setNumCorredor(int numCorredor) {
        this.numCorredor = numCorredor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String toString(){
        return ""+numCorredor;
    }
}
