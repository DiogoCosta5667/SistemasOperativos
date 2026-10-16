// ============================================================================
// FICHA 2 - EXERCÍCIO 2
// ----------------------------------------------------------------------------
// O que pede: ler um ficheiro de texto com um programa por linha e executar
// TODOS ao mesmo tempo (em paralelo).
//
// Como correr:
//     javac Ex2Paralelo.java
//     java Ex2Paralelo programas.txt
//
// O programas.txt desta pasta tem "whoami" e "hostname", dois comandos que
// existem tanto no Windows como no Linux.
//
// Ideia principal - PARALELO vs SEQUENCIAL:
//   - Paralelo (este exercício): arrancamos o 1.º, o 2.º, o 3.º... sem esperar
//     por nenhum. Ficam todos a correr ao mesmo tempo. Só DEPOIS esperamos.
//   - Sequencial (exercício 3): arrancamos o 1.º, esperamos que acabe,
//     só então arrancamos o 2.º, e assim por diante.
//   Com programas demorados, o paralelo acaba muito mais depressa.
// ============================================================================

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Ex2Paralelo {
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Modo de uso: java Ex2Paralelo <ficheiro com programas>");
            System.exit(0);
        }

        // Ler o ficheiro e guardar o nome de cada programa (ignorando linhas vazias).
        List<String> programas = new ArrayList<>();
        for (String linha : Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8)) {
            if (!linha.trim().isEmpty()) programas.add(linha.trim());
        }

        // ---------- PASSO 1: arrancar todos, sem esperar por nenhum ----------
        // Guardamos cada Process numa lista para depois podermos falar com ele.
        List<Process> processos = new ArrayList<>();
        for (String prog : programas) {
            ProcessBuilder pb = new ProcessBuilder(prog);
            pb.redirectErrorStream(true);
            try {
                processos.add(pb.start()); // start() lança e continua logo
                System.out.println("Arrancou: " + prog);
            } catch (IOException e) {
                // Se o programa não existir, avisamos e seguimos para o próximo.
                System.out.println("Nao foi possivel executar: " + prog);
                processos.add(null); // "null" marca que este não arrancou
            }
        }

        // Neste momento todos os processos estão a correr ao mesmo tempo.

        // ---------- PASSO 2: recolher o output de cada um e esperar ----------
        for (int i = 0; i < processos.size(); i++) {
            Process p = processos.get(i);
            if (p == null) continue; // saltar os que não arrancaram

            System.out.println("----- Output de " + programas.get(i) + " -----");
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();

            try {
                p.waitFor(); // garante que este processo já terminou
            } catch (InterruptedException e) {
                System.out.println("Interrompido enquanto esperava por " + programas.get(i));
            }
        }
        System.out.println("Todos os programas terminaram.");
    }
}
