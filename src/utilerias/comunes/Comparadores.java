package utilerias.comunes;

/**
 * Clase utilitaria que compara a un elemento A (info 1),
 * respecto a un elemento B (info 2). Si A es mayor a B, se
 * regresa un positivo, si A es menor a B se regresa un negativo
 */
public class Comparadores {
    public static Integer comparar(Object info1, Object info2){//checamos que ambos objetos sean de una superclase
        if ((info1 == null) && (info2 == null)){
            return 0;
        } else if ((info1 == null) && (info2 != null)) {
            return -1;
        } else if ((info1 != null) && (info2 == null)) {
            return 1;
        }
        if ((info1 instanceof Number) && (info2 instanceof Number)){ // ambas son instancias de numero
            double valor1 = ((Number) info1).doubleValue();
            double valor2 = ((Number) info2).doubleValue();
            if (valor1 < valor2){ //si el valor 1 es menor
                return -1;
            } else if (valor1 > valor2){ //si el valor 1 es mayor
                return 1;
            } else if (valor1 == valor2){
                return 0;
            }
        } else {
            return (info1.toString()).compareToIgnoreCase(info2.toString());
        }
        return null;
    }

}
