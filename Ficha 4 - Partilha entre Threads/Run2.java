// ============================================================================
// FICHA 4 - LISTAGEM 3: Run2 (as "Numbered threads" 0 a 9)
// ----------------------------------------------------------------------------
// O main cria 10 destas, cada uma com um número n diferente (0, 1, ..., 9).
// Cada uma faz 3 passos:
//     1. escreve o SEU número n no objeto partilhado
//     2. dorme n segundos (a 0 dorme 0 s, a 1 dorme 1 s, ..., a 9 dorme 9 s)
//     3. lê o número do objeto partilhado e imprime-o
//
// À primeira vista, cada thread devia imprimir o seu próprio número.
// Mas entre o passo 1 e o passo 3 as OUTRAS threads também escreveram no
// mesmo objeto, por isso o que se lê no passo 3 já não é o nosso número.
// É esta a pergunta do exercício (resposta completa em Resposta.txt).
// ============================================================================

public class Run2 extends Thread {
    public SharedObj share; // o objeto partilhado (o mesmo para todas)
    public int n;           // o número desta thread (cada uma tem o seu)

    // Construtor: recebe o objeto partilhado e o número desta thread.
    public Run2(SharedObj s, int i) { share = s; n = i; }

    public void run() {
        String myname = Thread.currentThread().getName(); // ex.: "Numbered thread 3"
        try {
            share.setNumber(n);    // PASSO 1: escreve o seu número no objeto partilhado
            Thread.sleep(n * 1000); // PASSO 2: dorme n segundos
            //share.setNumber(n);  // <- se esta linha estivesse ativa, cada thread voltava
            //                          a escrever o seu número antes de o ler, e a
            //                          numeração já saía certa. Experimenta!

            // PASSO 3: lê o número que está AGORA no objeto partilhado.
            System.out.println("[" + myname + "] Number:" + share.getNumber());

            // Deixa a sua "assinatura" no objeto partilhado (a Printer thread mostra-a).
            share.setName("share.name definido por: " + myname);
        } catch (InterruptedException e) {}
    }

    public void setShare(SharedObj s) { share = s; }
}
