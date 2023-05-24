package pruebas;

import entradasalida.SalidaPorDefecto;
import estructurasLineales.ListaNumerica;
import estructurasnolineales.Matriz2Numerica;
import utilerias.matematicas.TipoLogaritmo;

public class PruebaMatriz2 {
    public static void main(String[] args) {
        Matriz2Numerica matriz = new Matriz2Numerica(2, 2);
        matriz.cambiar(0, 0, "A");
        matriz.cambiar(0, 0, 1);
        matriz.cambiar(0, 1, 2);
        matriz.cambiar(1, 0, 3.0);
        matriz.cambiar(1, 1, 4.0);
        matriz.imprimirPorRenglones();
        matriz.porEscalar(2);
        SalidaPorDefecto.terminal("\n");
        matriz.imprimirPorRenglones();

        ListaNumerica escalares = new ListaNumerica(2);
        escalares.agregar(2);
        escalares.agregar(3);
        SalidaPorDefecto.terminal("\n");
        escalares.imprimir();
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal("Probando un caso en porEscalares: "+matriz.porEscalares(escalares)+"\n");
        matriz.imprimirPorRenglones();

        //Reinicio los valores
        matriz.cambiar(0, 0, 1);
        matriz.cambiar(0, 1, 2);
        matriz.cambiar(1, 0, 3.0);
        matriz.cambiar(1, 1, 4.0);

        SalidaPorDefecto.terminal("Probando sumarEscalar: "+matriz.sumarEscalar(4)+"\n");
        matriz.imprimirPorRenglones();
        SalidaPorDefecto.terminal("\n");
        escalares.imprimir();
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal("Probando sumarEscalares: "+matriz.sumarEscalares(escalares)+"\n");
        matriz.imprimirPorRenglones();
        SalidaPorDefecto.terminal("\n");
        Matriz2Numerica matrizA = new Matriz2Numerica(2, 4);
        matrizA.cambiar(0,0, 5);
        matrizA.cambiar(0,1, 3);
        matrizA.cambiar(0,2, -4);
        matrizA.cambiar(0,3, -2);
        matrizA.cambiar(1,0, 8);
        matrizA.cambiar(1,1, -1);
        matrizA.cambiar(1,2, 0);
        matrizA.cambiar(1,3, -3);
        Matriz2Numerica matrizB = new Matriz2Numerica(4, 3);
        matrizB.cambiar(0,0, 1);
        matrizB.cambiar(0,1, 4);
        matrizB.cambiar(0,2, 0);
        matrizB.cambiar(1,0, -5);
        matrizB.cambiar(1,1, 3);
        matrizB.cambiar(1,2, 7);
        matrizB.cambiar(2,0, 0);
        matrizB.cambiar(2,1, -9);
        matrizB.cambiar(2,2, 5);
        matrizB.cambiar(3,0, 5);
        matrizB.cambiar(3,1, 1);
        matrizB.cambiar(3,2, 4);
        SalidaPorDefecto.terminal("\nProbando multiplicarMatriz: "+matrizA.multiplicar(matrizB)+"\n");
        matrizA.imprimirPorRenglones();
        Matriz2Numerica matrizNo = new Matriz2Numerica(2, 3);
        Matriz2Numerica matrizD = new Matriz2Numerica(3,3);
        SalidaPorDefecto.terminal("\nProbando sumarMatriz (cuando no se puede): "+matrizNo.sumar(matrizD)+"\n");
        Matriz2Numerica matrizC = new Matriz2Numerica(3,3);
        matrizC.cambiar(0,0, 2);
        matrizC.cambiar(0,1, 0);
        matrizC.cambiar(0,2, 1);
        matrizC.cambiar(1,0, 3);
        matrizC.cambiar(1,1, 0);
        matrizC.cambiar(1,2, 0);
        matrizC.cambiar(2,0, 5);
        matrizC.cambiar(2,1, 1);
        matrizC.cambiar(2,2, 1);

        matrizD.cambiar(0,0, 1);
        matrizD.cambiar(0,1, 0);
        matrizD.cambiar(0,2, 1);
        matrizD.cambiar(1,0, 1);
        matrizD.cambiar(1,1, 2);
        matrizD.cambiar(1,2, 1);
        matrizD.cambiar(2,0, 1);
        matrizD.cambiar(2,1, 1);
        matrizD.cambiar(2,2, 0);
        SalidaPorDefecto.terminal("\nProbando sumarMatriz (cuando si se puede): "+matrizC.sumar(matrizD)+"\n");
        matrizC.imprimirPorRenglones();
        SalidaPorDefecto.terminal("\n");
        SalidaPorDefecto.terminal("\nProbando aplicarPotencia: "+matrizC.aplicarPotencia(2)+"\n");
        matrizC.imprimirPorRenglones();
        Matriz2Numerica logaritmoNatural = new Matriz2Numerica(2, 2, 10);
        SalidaPorDefecto.terminal("\n");
        logaritmoNatural.imprimirPorRenglones();
        SalidaPorDefecto.terminal("\nProbando aplicarLogaritmo (Base natural): "+logaritmoNatural.aplicarLogaritmo(TipoLogaritmo.NATURAL)+"\n");
        logaritmoNatural.imprimirPorRenglones();

        Matriz2Numerica logaritmoBinario = new Matriz2Numerica(2, 2, 27);
        SalidaPorDefecto.terminal("\n");
        logaritmoBinario.imprimirPorRenglones();
        SalidaPorDefecto.terminal("\nProbando aplicarLogaritmo (Base 2): "+logaritmoBinario.aplicarLogaritmo(TipoLogaritmo.BASE2)+"\n");
        logaritmoBinario.imprimirPorRenglones();

        Matriz2Numerica logaritmoBase10 = new Matriz2Numerica(2, 2, 2.5);
        SalidaPorDefecto.terminal("\n");
        logaritmoBase10.imprimirPorRenglones();
        SalidaPorDefecto.terminal("\nProbando aplicarLogaritmo (Base 10): "+logaritmoBase10.aplicarLogaritmo(TipoLogaritmo.BASE10)+"\n");
        logaritmoBase10.imprimirPorRenglones();

        Matriz2Numerica logaritmo0 = new Matriz2Numerica(2, 2);
        SalidaPorDefecto.terminal("\nProbando aplicarLogaritmo (Con 0s en matriz): "+logaritmo0.aplicarLogaritmo(TipoLogaritmo.BASE10)+"\n");
        logaritmo0.imprimirPorRenglones();

        Matriz2Numerica matrixDiagonal = new Matriz2Numerica(2, 2, 9.0);
        Matriz2Numerica matrixDiagonal2 = new Matriz2Numerica(3, 3, 5.0);
        Matriz2Numerica matrixDiagonal3 = new Matriz2Numerica(2, 3, 0.0);
        Matriz2Numerica matrixDiagonal4 = new Matriz2Numerica(4, 4, 1.0);
        SalidaPorDefecto.terminal("\nProbando matrizDiagonal (2x2): "+matrixDiagonal.matrizDiagonal(1)+"\n");
        SalidaPorDefecto.terminal("\n");
        matrixDiagonal.imprimirPorRenglones();
        SalidaPorDefecto.terminal("\nProbando matrizDiagonal (3x3): "+matrixDiagonal2.matrizDiagonal(2)+"\n");
        SalidaPorDefecto.terminal("\n");
        matrixDiagonal2.imprimirPorRenglones();
        SalidaPorDefecto.terminal("\nProbando matrizDiagonal (2x3): "+matrixDiagonal3.matrizDiagonal(9)+"\n");
        SalidaPorDefecto.terminal("\n");
        matrixDiagonal3.imprimirPorRenglones();
        SalidaPorDefecto.terminal("\nProbando matrizDiagonal (4x4): "+matrixDiagonal4.matrizDiagonal(4)+"\n");
        matrixDiagonal4.imprimirPorRenglones();
        SalidaPorDefecto.terminal("\n");
        Matriz2Numerica matriz2x2Diagonal = new Matriz2Numerica(2,2);
        matriz2x2Diagonal.cambiar(0, 0, 1);
        matriz2x2Diagonal.cambiar(0, 1, 0);
        matriz2x2Diagonal.cambiar(1, 0, 2);
        matriz2x2Diagonal.cambiar(1, 1, 3);
        matriz2x2Diagonal.imprimirPorRenglones();
        SalidaPorDefecto.terminal("\n");

        SalidaPorDefecto.terminal("\nProbando esDiagonalSuperior (2x2): "+matrixDiagonal.esDiagonalSuperior()+"\n");
        SalidaPorDefecto.terminal("\nProbando esDiagonalSuperior (3x3): "+matrixDiagonal2.esDiagonalSuperior()+"\n");
        SalidaPorDefecto.terminal("\nProbando esDiagonalSuperior (2x3): "+matrixDiagonal3.esDiagonalSuperior()+"\n");
        SalidaPorDefecto.terminal("\nProbando esDiagonalSuperior (4x4): "+matrixDiagonal4.esDiagonalSuperior()+"\n");
        SalidaPorDefecto.terminal("\nProbando esDiagonalInferior (2x2): "+matriz2x2Diagonal.esDiagonalInferior()+"\n");
        SalidaPorDefecto.terminal("\nProbando esDiagonalInferior (4x4): "+matrixDiagonal4.esDiagonalInferior()+"\n");
        Matriz2Numerica matrizA1 = new Matriz2Numerica(3, 3);
        matrizA1.cambiar(0, 0, 0);
        matrizA1.cambiar(0, 1, 1);
        matrizA1.cambiar(0, 2, 0);
        matrizA1.cambiar(1, 0, 1);
        matrizA1.cambiar(1, 1, 0);
        matrizA1.cambiar(1, 2, 1);
        matrizA1.cambiar(2, 0, 0);
        matrizA1.cambiar(2, 1, 1);
        matrizA1.cambiar(2, 2, 0);
        matrizA1.imprimirPorRenglones();
        SalidaPorDefecto.terminal("\nProbando potencia: "+matrizA1.potencia(3)+"\n");
        matrizA1.imprimirPorRenglones();
        SalidaPorDefecto.terminal("\n");
        matrixDiagonal4.imprimirPorRenglones();
        SalidaPorDefecto.terminal("\nProbando doblarColumnas: "+matrixDiagonal4.doblarColumnas()+"\n"); //Use este metodo si quiere probar doblar columnas
        //SalidaPorDefecto.terminal("\nProbando doblarRenglones: "+matrixDiagonal4.doblarRenglones()+"\n"); //Use este metodo si quiere probar doblar renglones
        matrixDiagonal4.imprimirPorRenglones();

        //Prueba caso impar
        Matriz2Numerica matrizImpar = new Matriz2Numerica(3,5, 2);
        SalidaPorDefecto.terminal("\nProbando doblarRenglones: "+matrizImpar.doblarRenglones()+"\n"); //El metodo impar funciona, aunque el codigo es extenso.
        //SalidaPorDefecto.terminal("\nProbando doblarColumnas: "+matrizImpar.doblarColumnas()+"\n"); //<-- Este metodo no verifica pares, arroja false.
        matrizImpar.imprimirPorRenglones();

    }
}
