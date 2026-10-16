// ============================================================================
// FICHA 4 - LISTAGEM 4: Run (a "Printer thread")
// ----------------------------------------------------------------------------
// Esta thread fica num ciclo infinito: de segundo a segundo, espreita o
// objeto partilhado e escreve o número e o nome que lá estão.
// Só pára quando o main lhe pede (com interrupt()).
//
// Ideias desta classe:
//   - "extends Thread": outra forma de criar threads (na Ficha 3 usámos
//     "implements Runnable"). O resultado é igual: o trabalho vai no run().
//   - Thread.interrupted(): pergunta "alguém me pediu para parar?".
//     É a forma correta de uma thread terminar de forma ordenada.
//     (Existe um método stop(), mas está obsoleto e não se deve usar.)
// ============================================================================

public class Run extends Thread {
    public SharedObj share; // referência para o objeto partilhado

    // Construtor: recebe o objeto partilhado e guarda-o.
    public Run(SharedObj s) { share = s; }

    public void run() {
        String myname = Thread.currentThread().getName(); // "Printer thread"
        try {
            while (true) { // ciclo infinito...
                // ...mas se alguém chamou interrupt(), saímos.
                if (Thread.interrupted()) return;

                Thread.sleep(1000); // espera 1 segundo

                // Mostra o que está NESTE MOMENTO no objeto partilhado.
                System.out.println("[" + myname + "] Number:" + share.getNumber() + " (" + share.getName() + ")");
            }
        } catch (InterruptedException e) {
            // Se o interrupt() chegar enquanto a thread dorme, o sleep lança
            // esta exceção e saímos do ciclo. Aqui não fazemos mais nada.
        }
    }

    public void setShare(SharedObj s) { share = s; }
}
