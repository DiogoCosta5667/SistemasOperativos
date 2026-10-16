import java.util.Scanner;


public class Sinal {

public static void main (String [] args) {

Scanner teclado = new Scanner(System.in);

System.out.println("Escreve um numero:");

int numero = teclado.nextInt();

if (numero < 0) {

System.out.println ("O numero e negativo");

} else if (numero > 0) {

System.out.println ("O numero e positivo");

} else {

System.out.println ("O numero e 0");

}

}
}