import java.util.Scanner;

public class Maior {

    public static void main (String [] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.println("Escreve o numero 1:");
        int numero1 = teclado.nextInt();

        System.out.println("Escreve o numero 2:");
        int numero2 = teclado.nextInt();

        System.out.println("Escreve o numero 3:");
        int numero3 = teclado.nextInt();

        if (numero1 >= numero2 && numero1 >= numero3) {
            System.out.println("O maior e " + numero1);
        } else if (numero2 >= numero1 && numero2 >= numero3) {
            System.out.println("O maior e " + numero2);
        } else {
            System.out.println("O maior e " + numero3);
        }
    }
}