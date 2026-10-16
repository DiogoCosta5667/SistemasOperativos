// ============================================================================
// FICHA 2 - EXERCÍCIO 3
// ----------------------------------------------------------------------------
// O que pede: o mesmo que o exercício 2, mas agora os programas correm UM DE
// CADA VEZ (sequencialmente), em vez de todos ao mesmo tempo.
//
// Como correr:
//     javac Ex3Sequencial.java
//     java Ex3Sequencial programas.txt
//
// A diferença para o exercício 2 está toda numa coisa: aqui chamamos
// waitFor() LOGO a seguir a cada start(), dentro do mesmo ciclo. Assim o
// programa seguinte só arranca quando o anterior já terminou.
//
//   Exercício 2 (paralelo):    start A, start B, start C ... espera A, B, C
//   Exercício 3 (sequencial):  start A, espera A, start B, espera B, ...
// ============================================================================

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Ex3Sequencial {
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Modo de uso: java Ex3Sequencial <ficheiro com programas>");
            System.exit(0);
        }

        // Um só ciclo: para cada linha do ficheiro, lança, lê e espera.
        for (String linha : Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8)) {
            String prog = linha.trim();
            if (prog.isEmpty()) continue; // salta linhas vazias

            ProcessBuilder pb = new ProcessBuilder(prog);
            pb.redirectErrorStream(true);

            Process p;
            try {
                p = pb.start(); // lança o processo
            } catch (IOException e) {
                System.out.println("Nao foi possivel executar: " + prog);
                continue; // passa ao próximo programa
            }

            System.out.println("----- Inicio de " + prog + " -----");

            // Ler tudo o que este processo escreve.
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();

            try {
                // AQUI está a diferença: esperamos que ESTE termine
                // antes de o ciclo avançar para o próximo programa.
                p.waitFor();
            } catch (InterruptedException e) {
                System.out.println("Interrompido enquanto esperava por " + prog);
            }
            System.out.println("----- Fim de " + prog + " -----");
        }
        System.out.println("Todos os programas terminaram.");
    }
}
