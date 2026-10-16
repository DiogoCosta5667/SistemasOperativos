import java.util.Scanner;

public class Mes {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Escreve a idade:");
        int numero = teclado.nextInt();

        if (numero < 12) {
            System.out.println("Preco: 4 euros");
        } else if (numero <= 64) {
            System.out.println("Preco: 8 euros");
        } else {
            System.out.println("Preco: 5 euros");
        }
    }
}