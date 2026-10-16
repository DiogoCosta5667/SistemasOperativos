import java.util.Scanner;


public class Quadrado {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Escreve um numero:");
        int numero = teclado.nextInt();      

        if (numero % 2 == 0) {
            System.out.println("E par");
        } else {
            System.out.println("E impar");
        }
    }
}

