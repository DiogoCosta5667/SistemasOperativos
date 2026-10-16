// ============================================================================
// FICHA 1 - EXERCÍCIO 3
// ----------------------------------------------------------------------------
// O que pede: o programa recebe UM só argumento, que é o nome de um ficheiro.
// Esse ficheiro tem lá dentro os dois valores. O programa lê-os e faz as contas.
//
// Como correr:
//     javac Ex3CalculadoraFicheiro.java
//     java Ex3CalculadoraFicheiro valores.txt
//
// O ficheiro valores.txt (que está nesta pasta) tem:   10 4
// Também funciona com um valor em cada linha.
//
// Ideias novas:
//   - Path / Paths.get(...): representa o caminho de um ficheiro no disco.
//   - Files.readAllLines(...): lê o ficheiro todo e devolve uma lista de linhas.
//   - List<String>: uma lista de textos que pode crescer (ao contrário do array).
//   - IOException: o erro que acontece quando há problemas a ler o ficheiro
//     (por exemplo, o ficheiro não existe).
// ============================================================================

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Ex3CalculadoraFicheiro {
    public static void main(String[] args) {

        // Tem de haver exatamente 1 argumento (o nome do ficheiro).
        if (args.length != 1) {
            System.out.println("Modo de uso: java Ex3CalculadoraFicheiro <ficheiro>");
            return;
        }

        // Transformamos o nome do ficheiro num "caminho" que o Java entende.
        Path path = Paths.get(args[0]);

        // Aqui vamos guardar cada número encontrado no ficheiro (ainda como texto).
        List<String> numeros = new ArrayList<>();

        try {
            // Lê todas as linhas do ficheiro. UTF_8 é a codificação do texto
            // (a forma como as letras estão guardadas no ficheiro).
            List<String> linhas = Files.readAllLines(path, StandardCharsets.UTF_8);

            // Percorremos cada linha...
            for (String linha : linhas) {
                // ...tiramos espaços no início/fim (trim) e partimos a linha
                // nos espaços (split("\\s+") = "parte onde houver 1 ou mais espaços").
                // Assim "10 4" fica ["10", "4"].
                for (String parte : linha.trim().split("\\s+")) {
                    if (!parte.isEmpty()) numeros.add(parte);
                }
            }
        } catch (IOException ex) {
            // Se o ficheiro não existir ou não der para ler, chegamos aqui.
            System.out.println("Erro no acesso ao ficheiro " + args[0]);
            return;
        }

        // Precisamos de pelo menos 2 valores.
        if (numeros.size() < 2) {
            System.out.println("ERRO: o ficheiro tem de ter dois valores.");
            return;
        }

        // Converter de texto para número (igual ao exercício 2).
        double a, b;
        try {
            a = Double.parseDouble(numeros.get(0)); // get(0) = primeiro elemento
            b = Double.parseDouble(numeros.get(1)); // get(1) = segundo elemento
        } catch (NumberFormatException e) {
            System.out.println("ERRO: os valores do ficheiro tem de ser numeros.");
            return;
        }

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
