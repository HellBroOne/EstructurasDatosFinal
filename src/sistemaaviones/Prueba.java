package sistemaaviones;

import java.util.Scanner;

public class Prueba {
    public static void main(String[] args) {
        Main programaPrincipal;
        programaPrincipal = new Main();
        Scanner scan_respuesta = new Scanner(System.in);
        int respuesta = 0;
        while(! (respuesta == 1)){
            System.out.println("Bienvenido al sistema de Aviones.\n" +
                    "Que operacion desea realizar?\n " +
                    "a)Dar de Alta avion.\n" +
                    "b)Consultar un avion por tipo\n" +
                    "c)Dar de alta a un piloto\n" +
                    "d)Dar de alta un Aeropuerto\n" +
                    "e)Registrar vuelo\n" +
                    "f)Consultar vuelos\n" +
                    "g)Consultar aeropuertos\n" +
                    "h)Salir\n" +
                    "Escriba la letra (en Minuscula) del inciso de la operacion a realizar: ");
            String inciso = scan_respuesta.nextLine();
            if (inciso == "a"){
                programaPrincipal.registrarAvion();
            }
            if (inciso == "b"){
                programaPrincipal.consultarAvion();
            }
            if (inciso == "c"){
                programaPrincipal.registrarPiloto();
            }
            if (inciso == "d"){
                programaPrincipal.registrarAeropuerto();
            }
            if (inciso == "e"){
                programaPrincipal.registrarVuelo();
            }
            if (inciso == "f"){
                programaPrincipal.consultarVuelo();
            }
            if (inciso == "g"){
                programaPrincipal.consultarAeropuertos();
            }
            if (inciso == "h"){
                respuesta = 1;
            }
        }
    }
}
