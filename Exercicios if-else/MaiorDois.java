import java.util.Scanner;

public class MaiorDois {
public static void main (String [] args) {
     Scanner teclado = new Scanner(System.in);
    
    System.out.println("Escreve um numero:");
    int numero1 = teclado.nextInt();
    System.out.println("Escreve outro numero:");
    int numero2 = teclado.nextInt();

    if (numero1 > numero2) {
        System.out.println("O maior é: " + numero1);
    } else {
        System.out.println("O maior é: " + numero2);
    }
}
}



