package conjuntistas;

public class Funciones {
    /**
     * Ejemplo: Dada la clave 163456789, se separa la clave en tres grupos: 16, 3456 y 789. Luego se suman
     * dichos grupos y se obtiene 4261, que es el resultado de la función. Si este valor es mayor que M, se
     * toma el resto del resultado dividido por M (valor MOD M).
     *
     * @param valor
     * @return suma
     */
    public static int fDoblamiento(int valor, int tamanio) {
        int suma = 0;
        if (valor <= 999999999) {
            String grupos = "" + valor;

            int primerGrupo = Integer.parseInt(grupos.substring(0, 2));
            int segundoGrupo = Integer.parseInt(grupos.substring(2, 6));
            int tercerGrupo = Integer.parseInt(grupos.substring(6, 9));

            suma = primerGrupo + segundoGrupo + tercerGrupo;
            if (suma > tamanio) {
                suma = suma % tamanio;
            }
        }
        return suma;
    }

    public static int fCuadrado(int valor) {
        int cuadrado = 0, resultado = -1;
        if (valor >= 0) {
            cuadrado = valor * valor;
            String sCuadrado = cuadrado + "";
            if (sCuadrado.length() % 2 == 0) {
                int mitad = sCuadrado.length();
                resultado = Integer.parseInt("" + sCuadrado.charAt(mitad) + "" + sCuadrado.charAt(mitad + 1));
            } else {
                int mitad = sCuadrado.length();
                resultado = Integer.parseInt("" + sCuadrado.charAt(mitad));
            }

        }
        return resultado;
    }

    public static int f1(int valor) {
        return valor * 3;
    }
    public static int f7(int valor) {
        return valor * 7;
    }
    public static int h(int valor) {
        return valor * 3;
    }
    public static int h2(int valor) {
        return valor % 7;
    }
}
