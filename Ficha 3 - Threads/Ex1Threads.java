// ============================================================================
// FICHA 3 - EXERCÍCIO 1
// ----------------------------------------------------------------------------
// O que pede: criar um certo número de threads (o número vem como argumento).
// Cada thread só escreve "Eu sou uma thread!".
//
// Como correr:
//     javac Ex1Threads.java
//     java Ex1Threads 5          <- cria 5 threads
//
// Ideias novas:
//   - THREAD: uma "linha de execução" dentro do mesmo programa. Um programa
//     normal faz uma coisa de cada vez; com threads pode fazer várias ao
//     mesmo tempo. Ao contrário dos processos (Ficha 2), as threads partilham
//     a mesma memória.
//   - Runnable: "algo que pode correr numa thread". Para o usar, a classe
//     escreve "implements Runnable" e põe dentro de run() o que a thread faz.
//   - start(): arranca a thread, que vai executar o run().
//     ATENÇÃO: chama-se sempre start(), nunca run() diretamente. Se chamar
//     run(), o código corre na thread atual e não se cria thread nenhuma.
// ============================================================================

public class Ex1Threads implements Runnable {

    // Isto é o que CADA thread vai fazer quando arrancar.
    public void run() {
        System.out.println("Eu sou uma thread!");
    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Modo de uso: java Ex1Threads <numero de threads>");
            return;
        }

        // Converter o argumento de texto para número inteiro.
        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("ERRO: o argumento tem de ser um numero inteiro.");
            return;
        }

        // Criamos e arrancamos n threads.
        // new Ex1Threads()           -> o "trabalho" (o Runnable)
        // new Thread(...)            -> a thread que vai fazer esse trabalho
        // .start()                   -> põe a thread a correr
        for (int i = 0; i < n; i++) {
            new Thread(new Ex1Threads()).start();
        }
        // O main acaba aqui, mas o programa só termina quando todas as threads acabarem.
    }
}
