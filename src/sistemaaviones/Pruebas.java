package sistemaaviones;

import java.util.Scanner;

public class Pruebas {
    public static void main(String[] args) {
        Main programaprincipal;
        programaprincipal = new Main();
        Scanner scan_option = new Scanner(System.in);
        String respuesta_continuar = "si";
        while (respuesta_continuar == "si"){
            System.out.println("Bienvenido al sistema de Aviones.\n" +
                    "Que operacion desea realizar?\n " +
                    "a)Dar de Alta avion.\n" +
                    "b)Consultar un avion por tipo\n" +
                    "c)Dar de alta a un piloto\n" +
                    "d)Dar de alta un Aeropuerto\n" +
                    "e)Registrar vuelo\n" +
                    "f)Consultar vuelos\n" +
                    "g)Consultar aeropuertos\n" +
                    "Escriba la letra (en Minuscula) del inciso de la operacion a realizar: ");
            String inciso = scan_option.nextLine();
            switch (inciso){
                case "a":
                    programaprincipal.registrarAvion();
                    break;
                case "b":
                    programaprincipal.consultarAvion();
                    break;
                case "c":
                    programaprincipal.registrarPiloto();
                    break;
                case "d":
                    programaprincipal.registrarAeropuerto();
                    break;
                case "e":
                    programaprincipal.registrarVuelo();
                    break;
                case "f":
                    programaprincipal.consultarVuelo();
                    break;
                case "g":
                    programaprincipal.consultarAeropuertos();
                    break;
                default:
                    System.out.println("Ingrese una opcion valida. ");
                    break;
            }
            System.out.println("Desea continuar?\nEscriba 'si' para continuar: ");
            respuesta_continuar = scan_option.nextLine();
        }


    }
}
