// ============================================================================
// FICHA 3 - EXERCÍCIO 2
// ----------------------------------------------------------------------------
// O que pede: igual ao exercício 1, mas cada thread tem um nome diferente
// (Th0, Th1, Th2, ...) e usa-o no texto que escreve:
//     [Th0] Eu sou uma thread
//     [Th1] Eu sou uma thread
//
// Como correr:
//     javac Ex2ThreadsComNome.java
//     java Ex2ThreadsComNome 5
//
// Ideias novas:
//   - new Thread(trabalho, "nome"): o segundo parâmetro dá um nome à thread.
//   - Thread.currentThread(): devolve a thread que está a executar ESTA linha
//     de código. Com .getName() obtemos o nome dela.
//
// Repara: as linhas podem aparecer fora de ordem (ex.: Th2 antes de Th0).
// É normal! As threads correm ao mesmo tempo e é o sistema operativo que
// decide qual corre primeiro. Não há garantia de ordem.
// ============================================================================

public class Ex2ThreadsComNome implements Runnable {

    public void run() {
        // Descobrir o nome da thread que está a correr este run().
        String nome = Thread.currentThread().getName();
        System.out.println("[" + nome + "] Eu sou uma thread");
    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Modo de uso: java Ex2ThreadsComNome <numero de threads>");
            return;
        }

        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("ERRO: o argumento tem de ser um numero inteiro.");
            return;
        }

        for (int i = 0; i < n; i++) {
            // "Th" + i  ->  "Th0", "Th1", "Th2", ...
            new Thread(new Ex2ThreadsComNome(), "Th" + i).start();
        }
    }
}
