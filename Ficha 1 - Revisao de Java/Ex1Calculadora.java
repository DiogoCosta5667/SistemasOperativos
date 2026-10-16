// ============================================================================
// FICHA 1 - EXERCÍCIO 1
// ----------------------------------------------------------------------------
// O que pede: ler dois valores escritos pelo utilizador e mostrar a soma,
// a subtração, a multiplicação e a divisão.
//
// Como correr (no terminal, dentro desta pasta):
//     javac Ex1Calculadora.java      <- compila: cria o ficheiro Ex1Calculadora.class
//     java Ex1Calculadora            <- executa o programa
//
// Ideias novas:
//   - Scanner: é a "ferramenta" do Java para ler o que o utilizador escreve.
//   - double: tipo de variável para números com casas decimais (ex.: 2.5).
//     Se usássemos int, 10 / 4 dava 2 em vez de 2.5.
// ============================================================================

// "import" diz ao Java que vamos usar uma classe que está noutra biblioteca.
// O Scanner vive no pacote java.util.
import java.util.Scanner;

// Em Java todo o código tem de estar dentro de uma classe.
// O nome da classe TEM de ser igual ao nome do ficheiro (Ex1Calculadora.java).
public class Ex1Calculadora {

    // O método main é o ponto de partida: é a primeira coisa que o Java executa.
    // "String[] args" são os argumentos da linha de comandos (aqui não usamos).
    public static void main(String[] args) {

        // Criamos um Scanner ligado ao teclado (System.in = entrada padrão).
        Scanner teclado = new Scanner(System.in);

        // System.out.println escreve uma linha no ecrã.
        System.out.println("Escreve o primeiro valor:");
        // nextDouble() espera que o utilizador escreva um número e carregue Enter.
        // Nota: com o computador em português, os decimais escrevem-se com vírgula (7,5).
        double a = teclado.nextDouble();

        System.out.println("Escreve o segundo valor:");
        double b = teclado.nextDouble();

        // O + entre texto e número junta os dois ("Soma: " + 14.0 -> "Soma: 14.0").
        // Os parênteses garantem que a conta é feita ANTES de juntar ao texto.
        System.out.println("Soma: " + (a + b));
        System.out.println("Subtracao: " + (a - b));
        System.out.println("Multiplicacao: " + (a * b));

        // Não se pode dividir por zero, por isso verificamos primeiro com um if.
        if (b == 0) {
            System.out.println("Divisao: impossivel dividir por 0");
        } else {
            System.out.println("Divisao: " + (a / b));
        }
    }
}
