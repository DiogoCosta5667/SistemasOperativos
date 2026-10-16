import java.util.Scanner;

public class Triangulo {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Escreve o lado 1:");
        int lado1 = teclado.nextInt();

        System.out.println("Escreve o lado 2:");
        int lado2 = teclado.nextInt();

        System.out.println("Escreve o lado 3:");
        int lado3 = teclado.nextInt();

        if (lado1 == lado2 && lado2 == lado3) {
            System.out.println("Equilatero");
        } else if (lado1 == lado2 || lado2 == lado3 || lado3 == lado1) {
            System.out.println("Isosceles");
        } else {
            System.out.println("Escaleno");
        }
    }
}