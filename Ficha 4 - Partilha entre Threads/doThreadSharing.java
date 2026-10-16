// ============================================================================
// FICHA 4 - LISTAGEM 2: doThreadSharing (o programa principal)
// ----------------------------------------------------------------------------
// O que pede o exercício: compilar as 4 classes (doThreadSharing, SharedObj,
// Run e Run2), correr e observar o output. As linhas vêm numeradas
// 0,1,2,...,9 ou aparecem sobretudo com 9? Porquê?
//
// Como correr (dentro desta pasta):
//     javac *.java            <- compila as 4 classes de uma vez
//     java doThreadSharing    <- corre (demora cerca de 10 segundos)
//
// O que este main faz:
//   1. Cria UM objeto SharedObj (o "quadro branco" partilhado).
//   2. Cria a "Printer thread" (classe Run), que mostra o quadro a cada segundo.
//   3. Cria 10 "Numbered threads" (classe Run2), numeradas de 0 a 9.
//      TODAS recebem o mesmo objeto share.
//   4. Espera 10 segundos e pede à Printer thread que pare.
//
// Resposta curta: aparecem quase só 9s, porque todas as threads escrevem no
// MESMO objeto e a última a escrever (a 9) apaga o número das outras.
// Isto chama-se "condição de corrida" (race condition).
// Resposta completa em Resposta.txt.
//
// Ideias desta ficha:
//   - As threads de um processo partilham a memória: se duas têm a
//     referência para o mesmo objeto, as alterações de uma veem-se na outra.
//   - Duas formas de dar nome a uma thread: no construtor (passo 2) ou com
//     setName() depois de criada (passo 3).
// ============================================================================

public class doThreadSharing {

    public static void main(String args[]) {

        // 1. O único objeto partilhado.
        SharedObj share = new SharedObj();

        // 2. Printer thread. O nome é dado logo no construtor (2.º parâmetro).
        Thread tarefa = new Thread(new Run(share), "Printer thread");
        tarefa.start();

        // 3. As 10 threads numeradas. Arrancam todas quase ao mesmo instante.
        for (int i = 0; i < 10; i++) {
            Thread tarefa2 = new Thread(new Run2(share, i)); // mesmo "share" para todas!
            tarefa2.setName("Numbered thread " + i);          // nome dado com setName()
            tarefa2.start();
        }

        // 4. O main espera 10 segundos e depois manda parar a Printer thread
        //    (senão ela ficava a correr para sempre, porque tem um ciclo infinito).
        try {
            Thread.sleep(10000);
            tarefa.interrupt();
        } catch (InterruptedException e) {}
    }
}
