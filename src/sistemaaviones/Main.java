package sistemaaviones;

import java.util.ArrayList;
import java.util.Scanner;

public class Main implements DarAlta, Consultar{

    public ArrayList<Piloto> listapilotos = new ArrayList<>();
    public ArrayList<Aeropuerto> listaaeropuertos = new ArrayList<>();

    //Arraylists de aviones
    public ArrayList<Avion> avionescarga = new ArrayList<>();
    public ArrayList<Avion> avionespasajeros = new ArrayList<>();
    public ArrayList<Avion> avionesmilitar = new ArrayList<>();
    public ArrayList<Avion> avionesrecreacion = new ArrayList<>();
    public ArrayList<Avion> avionesexcursion = new ArrayList<>();
    public ArrayList<Avion> todoslosaviones = new ArrayList<>();
    public ArrayList<Vuelo> listavuelos = new ArrayList<>();

    //Consultas
    @Override
    public void consultarAvion() {
        System.out.println("Que tipo de avion desea consultar? \n(Escriba 'Carga', 'Pasajeros', 'Militar', 'Recreacion' o 'Excursion')");
        Scanner consultaavion = new Scanner (System.in);
        String tipo = consultaavion.nextLine();
        switch (tipo) {
            case "Carga" -> {
                //donde se consulta si es de carga
                if ( !avionescarga.isEmpty() ){
                    int numero_avcar = 0;
                    for (Avion avcar : avionescarga){
                        System.out.println(numero_avcar + ". {"+ avcar.toString()+"}");
                        numero_avcar = numero_avcar + 1;
                    }
                } else {
                    System.out.println("La lista de Aviones de Carga esta vacia.");
                }
            }

            case "Pasajeros" -> {
                //donde se consulta si es de pasajeros
                if ( !avionespasajeros.isEmpty() ){
                    int numero_avpas = 0;
                    for (Avion avpas : avionespasajeros){
                        System.out.println(numero_avpas + ". {"+ avpas.toString()+"}");
                        numero_avpas = numero_avpas + 1;
                    }
                } else {
                    System.out.println("La lista de Aviones de Pasajeros esta vacia.");
                }
            }
            case "Militar" -> {
                //donde se consulta si es militar
                if ( !avionesmilitar.isEmpty() ){
                    int numero_avmil = 0;
                    for (Avion avmil : avionesmilitar){
                        System.out.println(numero_avmil + ". {"+ avmil.toString()+"}");
                        numero_avmil = numero_avmil + 1;
                    }
                } else {
                    System.out.println("La lista de Aviones Militares esta vacia.");
                }
            }
            case "Recreacion" -> {
                //donde se consulta si es de recreacion
                if ( !avionesrecreacion.isEmpty() ){
                    int numero_avrec = 0;
                    for (Avion avrec : avionesrecreacion){
                        System.out.println(numero_avrec + ". {"+ avrec.toString()+"}");
                        numero_avrec = numero_avrec + 1;
                    }
                } else {
                    System.out.println("La lista de Aviones de Recreacion esta vacia.");
                }
            }
            case "Excursion" -> {
                //donde se consulta si es de excursion
                if ( !avionesexcursion.isEmpty() ){
                    int numero_avexc = 0;
                    for (Avion avexc : avionesexcursion){
                        System.out.println(numero_avexc + ". {"+ avexc.toString()+"}");
                        numero_avexc = numero_avexc + 1;
                    }
                } else {
                    System.out.println("La lista de Aviones de Excursion esta vacia.");
                }
            }
            default -> System.out.println("Tipo de Avion invalido.");
        }
    }

    @Override
    public void consultarTodos(){
        System.out.println("Usado?");
    }

    @Override
    public void consultarVuelo() {
        //pedir tipo de consulta (destino u origen)
        Scanner tipo_consulta = new Scanner(System.in);
        System.out.println("Como quiere consultar?\n1. Mediante destino\n2. Mediante origen\nElija el tipo de consulta (Escriba el numero): ");
        int numero_opcion_consulta = tipo_consulta.nextInt();
        if (numero_opcion_consulta == 1){
            //consultar por destino
            System.out.println("Ingrese el nombre de la ciudad de destino: ");
            String destino_search = tipo_consulta.nextLine();
            for (Vuelo vuelo_consulta: listavuelos) {
                Aeropuerto aeropuerto_consultado = vuelo_consulta.getAeropuertoDestino();
                if ( (aeropuerto_consultado.getCiudad() ) == destino_search){
                    System.out.println(vuelo_consulta);
                }
            }
        }
        if (numero_opcion_consulta == 2){
            System.out.println("Ingrese el nombre de la ciudad de origen: ");
            String origen_search = tipo_consulta.nextLine();
            for (Vuelo vuelo_consulta: listavuelos) {
                Aeropuerto aeropuerto_consultado = vuelo_consulta.getAeropuertoOrigen();
                if ( ( aeropuerto_consultado.getCiudad() ) == origen_search){
                    System.out.println(vuelo_consulta);
                }
            }
        }

    }

    @Override
    public void consultarAeropuertos() {
        //pedir ciudad
        Scanner aeropuerto_consulta = new Scanner(System.in);
        System.out.println("Ingrese la ciudad para buscar un Aeropuerto: ");
        String ciudad_consulta = aeropuerto_consulta.nextLine();
        for (Aeropuerto aeropuerto_enfor: listaaeropuertos) {
            if (aeropuerto_enfor.getCiudad() == ciudad_consulta){
                System.out.println(aeropuerto_consulta);
            }
        }
    }

    @Override
    public void consultarPilotos(){
        if ( !listapilotos.isEmpty() ){
            int numero_pilot = 0;
            for (Piloto p : listapilotos) {
                System.out.println(numero_pilot + ". {"+ p.toString()+"}");
                numero_pilot = numero_pilot + 1;
            }
        } else {
            System.out.println("Error: La lista de Pilotos esta vacia. \n Intente agregar primero un piloto.");
            System.exit(0);
        }
    }

    //Registrarvuelos
    @Override
    public void registrarVuelo() {
        Scanner vuelo_registro = new Scanner(System.in);
        int numero_de_avion = 0;
        //mostrar todos los aviones
        for (Avion avion_enfore: todoslosaviones) {
            System.out.println(numero_de_avion+". "+avion_enfore.toString());
            numero_de_avion = numero_de_avion + 1;
        }
        System.out.println("Ingrese el numero del avion para el vuelo: ");
        int numero_avion = vuelo_registro.nextInt();
        Avion avion_seleccionado;
        avion_seleccionado = todoslosaviones.get(numero_avion);

        //mostrar todos los aeropuertos
        int numero_aeropuerto = 0;
        for (Aeropuerto aero_for: listaaeropuertos) {
            System.out.println(numero_aeropuerto+". "+aero_for);
            numero_aeropuerto = numero_aeropuerto + 1;
        }
        System.out.println("Ingrese el numero de Aeropuerto de Origen: ");
        int numero_origen = vuelo_registro.nextInt();
        System.out.println("Ingrese el numero de Aeropuerto de Destino: ");
        int numero_destino = vuelo_registro.nextInt();
        Aeropuerto origen;
        Aeropuerto destino;
        origen = listaaeropuertos.get(numero_origen);
        destino = listaaeropuertos.get(numero_destino);

        consultarPilotos();
        System.out.println("Ingrese al numero del piloto del vuelo: ");
        int piloto_seleccionado = vuelo_registro.nextInt();
        Piloto piloto_vuelo;
        piloto_vuelo = listapilotos.get(piloto_seleccionado);

        System.out.println("Ingrese la fecha de hoy: ");
        String fecha_vuelo = vuelo_registro.nextLine();

        Vuelo vuelo_registrado;
        vuelo_registrado = new Vuelo(avion_seleccionado, origen, destino, piloto_vuelo, fecha_vuelo);
        listavuelos.add(vuelo_registrado);
    }

    @Override
    public void registrarAvion() {
        Scanner planeregister = new Scanner(System.in);
        System.out.println("Agregue la matricula del avion: ");
        String mat = planeregister.nextLine();
        System.out.println("Agregue el fabricante del avion: ");
        String fab = planeregister.nextLine();
        System.out.println("Agregue el modelo del avion: ");
        int model = planeregister.nextInt();
        System.out.println("Agregue la capacidad de vuelo/pasajeros del avion: ");
        int capacidadv = planeregister.nextInt();
        System.out.println("Agregue el piloto del avion (agregue el numero): ");
        consultarPilotos();
        int pilotnumber = planeregister.nextInt();
        Piloto added_to_airplane = listapilotos.get(pilotnumber); //Este piloto es el que se agregara al avion
        System.out.println("Agruegue el tipo del avion.\n"+"Escriba 'Carga', 'Pasajeros', 'Militar', 'Recreacion' o 'Excursion': ");
        String type = planeregister.nextLine();
        Avion avionadded;
        //elegir entre casos y tipos
        switch (type) {
            case "Carga" -> {
                //donde se guarda si es de carga
                avionadded = new Avion(mat, fab, model, capacidadv, type, added_to_airplane);
                avionescarga.add(avionadded);
                todoslosaviones.add(avionadded);
                System.out.println("Avion Guardado: "+ avionadded);
            }
            case "Pasajeros" -> {
                //donde se guarda si es de pasajeros
                avionadded = new Avion(mat, fab, model, capacidadv, type, added_to_airplane);
                avionespasajeros.add(avionadded);
                todoslosaviones.add(avionadded);
                System.out.println("Avion Guardado: "+ avionadded);
            }
            case "Militar" -> {
                //donde se guarda si es militar
                avionadded = new Avion(mat, fab, model, capacidadv, type, added_to_airplane);
                avionesmilitar.add(avionadded);
                todoslosaviones.add(avionadded);
                System.out.println("Avion Guardado: "+ avionadded);
            }
            case "Recreacion" -> {
                //donde se guarda si es de recreacion
                avionadded = new Avion(mat, fab, model, capacidadv, type, added_to_airplane);
                avionesrecreacion.add(avionadded);
                todoslosaviones.add(avionadded);
                System.out.println("Avion Guardado: "+ avionadded);
            }
            case "Excursion" -> {
                //donde se guarda si es de excursion
                avionadded = new Avion(mat, fab, model, capacidadv, type, added_to_airplane);
                avionesexcursion.add(avionadded);
                todoslosaviones.add(avionadded);
                System.out.println("Avion Guardado: "+ avionadded);
            }
            default -> System.out.println("Tipo de Avion invalido.");
        }
    }

    @Override
    public void registrarPiloto() {
        Scanner pilotregister = new Scanner(System.in);
        System.out.print("Agregue el nombre del Piloto: ");
        String nombre = pilotregister.nextLine();
        System.out.print("Agregue la licencia del Piloto: ");
        String licencia = pilotregister.nextLine();
        Piloto pilotoadded;
        pilotoadded = new Piloto(nombre, licencia);
        listapilotos.add(pilotoadded);
        System.out.println("Piloto Registrado: " + pilotoadded);
    }

    @Override
    public void registrarAeropuerto() {
        Scanner airportreg = new Scanner(System.in);
        System.out.println("Agregue la ciudad del Aeropuerto: ");
        String ciudad = airportreg.nextLine();
        System.out.println("Agregue el pais del Aeropuerto: ");
        String pais = airportreg.nextLine();
        Aeropuerto aeroadded;
        aeroadded = new Aeropuerto(ciudad, pais);
        listaaeropuertos.add(aeroadded);
        System.out.println("Aeropuerto Agregado: " + aeroadded);
    }
}
