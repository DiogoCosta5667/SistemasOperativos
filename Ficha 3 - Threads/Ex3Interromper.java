// ============================================================================
// FICHA 3 - EXERCÍCIO 3
// ----------------------------------------------------------------------------
// O que pede: criar UMA thread que escreve 10 linhas, uma por segundo.
// O programa principal interrompe a thread ao fim de X segundos
// (X vem como argumento).
//
// Como correr:
//     javac Ex3Interromper.java
//     java Ex3Interromper 4      <- a thread só chega a escrever 4 linhas
//     java Ex3Interromper 15     <- dá tempo para as 10 linhas
//
// Ideias novas:
//   - Thread.sleep(1000): põe a thread a "dormir" 1000 milissegundos (= 1 s).
//   - t.interrupt(): pede à thread t que pare. Não a mata à força: se ela
//     estiver a dormir (sleep), o sleep é acordado com uma InterruptedException
//     e a thread decide o que fazer (aqui, escreve uma mensagem e termina).
//   - t.join(): o programa principal fica à espera que a thread t termine.
//     É o equivalente ao waitFor() dos processos (Ficha 2).
//
// Há DUAS threads neste programa: a do main (programa principal) e a que
// criamos. Ambas usam sleep, cada uma por razões diferentes.
// ============================================================================

public class Ex3Interromper implements Runnable {

    // O trabalho da thread criada: 10 linhas, uma por segundo.
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Linha " + i);
                Thread.sleep(1000); // espera 1 segundo antes da próxima linha
            }
            // Só chega aqui se ninguém a interromper antes das 10 linhas.
            System.out.println("A thread imprimiu as 10 linhas.");
        } catch (InterruptedException e) {
            // Se o main chamar interrupt() enquanto a thread dorme,
            // o sleep "rebenta" com esta exceção e viemos parar aqui.
            System.out.println("Fui interrompida!");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        if (args.length != 1) {
            System.out.println("Modo de uso: java Ex3Interromper <segundos>");
            return;
        }

        int segundos;
        try {
            segundos = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("ERRO: o argumento tem de ser um numero inteiro.");
            return;
        }

        // Criar e arrancar a thread. Guardamos a referência em "t" para
        // depois a podermos interromper e esperar por ela.
        Thread t = new Thread(new Ex3Interromper());
        t.start();

        // O main dorme os segundos pedidos, enquanto a thread vai escrevendo.
        // "1000L" (com L) é para a conta ser feita em long e não haver
        // problemas com números grandes.
        Thread.sleep(segundos * 1000L);

        t.interrupt(); // pede à thread que pare
        t.join();      // espera que ela termine mesmo
        System.out.println("Programa principal terminou.");
    }
}
