
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Ejemplo de uso
        F1 polinomio1 = (F1) Polinomio.ingresarPolinomio(2, "F1");
        F3 polinomio2 = (F3) Polinomio.ingresarPolinomio(2, "F3");

        System.out.println("Suma de Polinomios:");
        F1 suma = polinomio1.sumar(polinomio2);
        suma.mostrarBonito();

        System.out.println("Producto de Polinomios:");
        F3 producto = polinomio1.multiplicar(new F2(polinomio2.coeficientes));
        producto.mostrarBonito();
    }
}

class Polinomio {
    protected int[] coeficientes;

    public Polinomio(int[] coeficientes) {
        this.coeficientes = coeficientes;
    }

    // Método para mostrar el polinomio de manera legible
    public void mostrarBonito() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < coeficientes.length; i++) {
            if (coeficientes[i] != 0) {
                if (i > 0 && coeficientes[i] > 0) {
                    sb.append("+");
                }
                sb.append(coeficientes[i]).append("x^").append(i).append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    // Método para ingresar un polinomio
    public static Polinomio ingresarPolinomio(int grado, String tipo) {
        int[] coeficientes = new int[grado + 1];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i <= grado; i++) {
            System.out.print("Ingrese el coeficiente para x^" + i + ": ");
            coeficientes[i] = scanner.nextInt();
        }
        return switch (tipo) {
            case "F1" -> new F1(coeficientes);
            case "F2" -> new F2(coeficientes);
            case "F3" -> new F3(coeficientes);
            default -> null;
        };
    }
}

class F1 extends Polinomio {
    public F1(int[] coeficientes) {
        super(coeficientes);
    }

    // Suma un polinomio representado en F1 con uno en F3
    public F1 sumar(F3 b) {
        int maxGrado = Math.max(this.coeficientes.length, b.coeficientes.length);
        int[] resultado = new int[maxGrado];
        for (int i = 0; i < maxGrado; i++) {
            int coefA = (i < this.coeficientes.length) ? this.coeficientes[i] : 0;
            int coefB = (i < b.coeficientes.length) ? b.coeficientes[i] : 0;
            resultado[i] = coefA + coefB;
        }
        return new F1(resultado);
    }

    // Multiplicar un polinomio representado en F1 con uno en F2
    public F3 multiplicar(F2 b) {
        int gradoA = this.coeficientes.length - 1;
        int gradoB = b.coeficientes.length - 1;
        int[] resultado = new int[gradoA + gradoB + 1];

        for (int i = 0; i <= gradoA; i++) {
            for (int j = 0; j <= gradoB; j++) {
                resultado[i + j] += this.coeficientes[i] * b.coeficientes[j];
            }
        }
        return new F3(resultado);
    }

    // Copiar un polinomio F1
    public F3 copia() {
        return new F3(this.coeficientes.clone());
    }
}

class F2 extends Polinomio {
    public F2(int[] coeficientes) {
        super(coeficientes);
    }

    // Multiplicar un polinomio representado en F2 con uno en F3
    public F2 multiplicar(F3 b) {
        int gradoA = this.coeficientes.length - 1;
        int gradoB = b.coeficientes.length - 1;
        int[] resultado = new int[gradoA + gradoB + 1];

        for (int i = 0; i <= gradoA; i++) {
            for (int j = 0; j <= gradoB; j++) {
                resultado[i + j] += this.coeficientes[i] * b.coeficientes[j];
            }
        }
        return new F2(resultado);
    }

    // Copiar un polinomio F2
    public F1 copia() {
        return new F1(this.coeficientes.clone());
    }
}

class F3 extends Polinomio {
    public F3(int[] coeficientes) {
        super(coeficientes);
    }

    // Restar un polinomio en F3 con uno en F2
    public F3 restar(F2 b) {
        int maxGrado = Math.max(this.coeficientes.length, b.coeficientes.length);
        int[] resultado = new int[maxGrado];
        for (int i = 0; i < maxGrado; i++) {
            int coefA = (i < this.coeficientes.length) ? this.coeficientes[i] : 0;
            int coefB = (i < b.coeficientes.length) ? b.coeficientes[i] : 0;
            resultado[i] = coefA - coefB;
        }
        return new F3(resultado);
    }

    // Comparar si dos polinomios son iguales
    public boolean sonIguales(F2 b) {
        if (this.coeficientes.length != b.coeficientes.length) {
            return false;
        }
        for (int i = 0; i < this.coeficientes.length; i++) {
            if (this.coeficientes[i] != b.coeficientes[i]) {
                return false;
            }
        }
        return true;
    }

    // Copiar un polinomio F3
    public F2 copia() {
        return new F2(this.coeficientes.clone());
    }
}
