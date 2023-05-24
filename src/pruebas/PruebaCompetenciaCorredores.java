package pruebas;

import registros.competenciasatleticas.ControlCompetenciasCorredores;
import registros.competenciasatleticas.Corredor;
import registros.competenciasatleticas.EventoCompetencia;

public class PruebaCompetenciaCorredores {
    public static void main(String[] args) {
        ControlCompetenciasCorredores competencias = new ControlCompetenciasCorredores(4, 3, 5);
        competencias.agregarAnio(1980);
        competencias.agregarAnio(2000);
        competencias.agregarAnio(2005);
        competencias.agregarAnio(2020);

        Corredor corredor1 = new Corredor(101, "Pepe", 20, "Mexicano");
        Corredor corredor2 = new Corredor(201, "Lencho", 29, "Mexicano");
        Corredor corredor3 = new Corredor(301, "Jelipe", 39, "Salvadorenio");

        competencias.agregarCorredor(corredor1);
        competencias.agregarCorredor(corredor2);
        competencias.agregarCorredor(corredor3);

        EventoCompetencia evento1 = new EventoCompetencia("ZACATON 1980", "Zacatecas");
        EventoCompetencia evento2 = new EventoCompetencia("Carrera del Milenio", "Durango");
        EventoCompetencia evento3 = new EventoCompetencia("Runners from the Edge", "California");

        competencias.agregarEvento(evento1);
        competencias.agregarEvento(evento2);
        competencias.agregarEvento(evento3);

        competencias.agregarKilometrosRecorridos(1980, 301, "ZACATON 1980", 9.0);
        competencias.agregarKilometrosRecorridos(2000, 101, "Carrera del Milenio", 3.7);
        competencias.agregarKilometrosRecorridos(2020, 201, "Runners from the Edge", 7.13);

        competencias.imprimirDatosCompetencias();
    }
}
