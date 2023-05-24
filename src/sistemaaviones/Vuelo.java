package sistemaaviones;

public class Vuelo {
    protected Avion avion_vuelo;
    protected Aeropuerto aeropuertoOrigen;
    protected Aeropuerto aeropuertoDestino;
    protected Piloto piloto_vuelo;
    protected String fecha;

    public Vuelo (Avion av, Aeropuerto origen, Aeropuerto destino, Piloto piloto_vuelo, String fecha){
        avion_vuelo = av;
        aeropuertoOrigen = origen;
        aeropuertoDestino = destino;
        this.piloto_vuelo = piloto_vuelo;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Vuelo - {" +
                "Avion: " + avion_vuelo + ", Origen: " + aeropuertoOrigen + ", Destino: " + aeropuertoDestino + ", Piloto: " + piloto_vuelo + ", Fecha: " + fecha + '}';
    }

    public Aeropuerto getAeropuertoOrigen() {
        return aeropuertoOrigen;
    }

    public Aeropuerto getAeropuertoDestino() {
        return aeropuertoDestino;
    }
}
