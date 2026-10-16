// ============================================================================
// FICHA 2 - EXERCÍCIO 1
// ----------------------------------------------------------------------------
// O que pede: partir do exemplo "Shell" da ficha e permitir passar um ou mais
// argumentos ao programa externo. O nosso programa espera que o outro termine
// e mostra no ecrã tudo o que o outro escreveu.
//
// Como correr:
//     javac Ex1Shell.java
//     java Ex1Shell ls -l /tmp            (Linux: corre "ls -l /tmp")
//     java Ex1Shell cmd /c dir            (Windows: corre "dir")
//
// Ideias novas:
//   - PROCESSO: um programa em execução, com a sua própria memória.
//     O nosso programa Java é um processo; o "ls" que ele lança é OUTRO processo.
//   - ProcessBuilder: classe do Java que prepara e lança um processo externo.
//   - Process: representa o processo que foi lançado. Permite ler o que ele
//     escreve (getInputStream) e esperar que termine (waitFor).
//   - Código de saída: número que um programa devolve ao terminar.
//     Por convenção, 0 = correu bem, outro valor = houve erro.
// ============================================================================

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ex1Shell {

    // "throws IOException" avisa que o main pode lançar este erro
    // (por exemplo, se o comando não existir) sem o tratarmos aqui dentro.
    public static void main(String[] args) throws IOException {

        // Precisamos de pelo menos o nome do comando.
        if (args.length < 1) {
            System.err.println("Modo de uso: java Ex1Shell <comando> [argumentos...]");
            System.exit(0); // termina o programa
        }

        // Diferença para o exemplo da ficha: em vez de passar só args[0],
        // passamos a lista TODA. O ProcessBuilder trata o primeiro elemento como
        // o comando e os restantes como argumentos desse comando.
        // Ex.: ["ls", "-l", "/tmp"]  ->  ls -l /tmp
        ProcessBuilder pb = new ProcessBuilder(Arrays.asList(args));

        // Os programas têm duas "saídas": a normal e a de erros.
        // Esta linha junta as duas, para lermos tudo no mesmo sítio.
        pb.redirectErrorStream(true);

        // start() lança o processo. A partir daqui há DOIS programas a correr.
        Process process = pb.start();
        System.out.println("Inicio do comando \"" + String.join(" ", args) + "\".");

        // Para ler o que o outro processo escreve, ligamo-nos à saída dele.
        // InputStream lê bytes; InputStreamReader converte para letras;
        // BufferedReader permite ler linha a linha com readLine().
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        // readLine() devolve null quando o outro processo acabar de escrever.
        while ((line = br.readLine()) != null) {
            System.out.println(line); // reimprimimos cada linha no nosso ecrã
        }
        br.close(); // fechamos a ligação

        try {
            // waitFor() bloqueia o nosso programa até o processo externo terminar
            // e devolve o código de saída dele.
            int codigo = process.waitFor();
            System.out.println("Fim do comando \"" + String.join(" ", args) + "\" (codigo de saida " + codigo + ").");
        } catch (InterruptedException e) {
            // Só acontece se alguém interromper o nosso programa enquanto espera.
            System.out.println("Interrompido enquanto esperava pelo processo.");
        }
    }
}
