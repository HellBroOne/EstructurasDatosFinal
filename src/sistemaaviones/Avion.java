package sistemaaviones;

public class Avion {
    protected String matricula;
    protected String fabricante;
    protected int modelo;
    protected int capacidadvuelo;
    protected String tipo;

    public Piloto pilotoavion;

    public Avion(String matricula, String fabricante, int modelo, int capacidadvuelo, String tipo, Piloto pilotoavion){
        this.matricula = matricula;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.capacidadvuelo = capacidadvuelo;
        this.tipo = tipo;
        this.pilotoavion = pilotoavion;
    };

    @Override
    public String toString() {
        return "Avion - { matricula: " + matricula + ", fabricante: " + fabricante + ", modelo: " + modelo + ", capacidadvuelo: " + capacidadvuelo + ", tipo: " + tipo + ", con el Piloto: "+pilotoavion.getNombre()+'}';
    }
}
