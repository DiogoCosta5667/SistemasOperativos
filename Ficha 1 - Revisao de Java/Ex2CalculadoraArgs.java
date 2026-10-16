// ============================================================================
// FICHA 1 - EXERCÍCIO 2
// ----------------------------------------------------------------------------
// O que pede: o mesmo que o exercício 1, mas os dois valores já não são
// pedidos com o teclado. Vêm escritos logo a seguir ao nome do programa.
//
// Como correr:
//     javac Ex2CalculadoraArgs.java
//     java Ex2CalculadoraArgs 10 4      <- 10 e 4 são os "argumentos"
//
// Ideias novas:
//   - args: é uma lista (array) com as palavras escritas depois do nome do
//     programa. No exemplo acima: args[0] = "10" e args[1] = "4".
//     Atenção: os índices começam em 0, não em 1!
//   - Os argumentos chegam sempre como TEXTO (String). Para fazer contas temos
//     de os converter para número com Double.parseDouble(...).
// ============================================================================

public class Ex2CalculadoraArgs {
    public static void main(String[] args) {

        // args.length diz quantos argumentos foram escritos.
        // Se não forem exatamente 2, explicamos como se usa o programa e saímos.
        if (args.length != 2) {
            System.out.println("Modo de uso: java Ex2CalculadoraArgs <valor1> <valor2>");
            return; // "return" no main termina o programa aqui.
        }

        double a, b;

        // try/catch: "tenta fazer isto; se der erro, faz aquilo em vez de rebentar".
        // Se alguém escrever "java Ex2CalculadoraArgs ola 4", o parseDouble("ola")
        // falha com NumberFormatException e caímos no catch.
        try {
            a = Double.parseDouble(args[0]); // texto "10" -> número 10.0
            b = Double.parseDouble(args[1]); // texto "4"  -> número 4.0
        } catch (NumberFormatException e) {
            System.out.println("ERRO: os dois argumentos tem de ser numeros.");
            return;
        }

        // A partir daqui é igual ao exercício 1.
        System.out.println("Soma: " + (a + b));
        System.out.println("Subtracao: " + (a - b));
        System.out.println("Multiplicacao: " + (a * b));
        if (b == 0) {
            System.out.println("Divisao: impossivel dividir por 0");
        } else {
            System.out.println("Divisao: " + (a / b));
        }
    }
}
