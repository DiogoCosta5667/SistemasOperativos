// ============================================================================
// FICHA 1 - EXERCÍCIO 4
// ----------------------------------------------------------------------------
// O que pede: mostrar o conteúdo de um ficheiro de texto, pondo o número de
// cada linha à frente. O nome do ficheiro vem como argumento.
//   - Sem argumento   -> explicar ao utilizador como se usa o programa.
//   - Ficheiro não existe -> "ERRO: O ficheiro <nome> não existe!"
//
// Como correr:
//     javac Ex4NumerarLinhas.java
//     java Ex4NumerarLinhas texto.txt
//
// Resultado com o texto.txt desta pasta:
//     1: Primeira linha do ficheiro
//     2: Segunda linha
//     3: Terceira e ultima linha
//
// Ideias novas:
//   - Files.isRegularFile(path): verifica se o ficheiro existe (e se é mesmo
//     um ficheiro e não uma pasta) ANTES de o tentarmos abrir.
//   - Ciclo for com contador (int i = 0; ...): útil quando precisamos do
//     número da posição, que aqui é o número da linha.
// ============================================================================

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Ex4NumerarLinhas {
    public static void main(String[] args) {

        // Caso 1: o utilizador não escreveu nenhum argumento.
        if (args.length == 0) {
            System.out.println("Este programa mostra um ficheiro de texto com as linhas numeradas.");
            System.out.println("Modo de uso: java Ex4NumerarLinhas <ficheiro>");
            return;
        }

        Path path = Paths.get(args[0]);

        // Caso 2: escreveu um nome, mas esse ficheiro não existe.
        if (!Files.isRegularFile(path)) {
            System.out.println("ERRO: O ficheiro " + args[0] + " não existe!");
            return;
        }

        // Caso 3: o ficheiro existe -> lemos e mostramos.
        try {
            List<String> linhas = Files.readAllLines(path, StandardCharsets.UTF_8);

            // linhas.size() = número de linhas do ficheiro.
            // i começa em 0 (é assim que as listas funcionam), mas para o
            // utilizador a primeira linha é a 1, por isso escrevemos (i + 1).
            for (int i = 0; i < linhas.size(); i++) {
                System.out.println((i + 1) + ": " + linhas.get(i));
            }
        } catch (IOException ex) {
            // Pode acontecer se o ficheiro existir mas não tivermos permissão para o ler.
            System.out.println("Erro no acesso ao ficheiro " + args[0]);
        }
    }
}
