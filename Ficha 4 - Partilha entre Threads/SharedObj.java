// ============================================================================
// FICHA 4 - LISTAGEM 5: SharedObj (o objeto partilhado)
// ----------------------------------------------------------------------------
// Este é o "quadro branco" que todas as threads usam. Só existe UM objeto
// destes, criado no main (doThreadSharing). Todas as threads recebem uma
// referência para ESSE MESMO objeto.
//
// Imagina um quadro branco numa sala com 10 pessoas: se uma escreve lá o
// número 3 e logo a seguir outra apaga e escreve 7, quem olhar depois vê 7,
// não 3. É exatamente o que acontece neste exercício.
//
// Esta classe é simples: guarda um número e um nome, com métodos para os
// ler (get) e alterar (set). Ver as explicações em doThreadSharing.java.
// ============================================================================

public class SharedObj {
    // "private": só esta classe mexe diretamente nestas variáveis.
    // As outras têm de usar os métodos get/set abaixo.
    private int number;
    private String name;

    public String getName() { return name; }   // ler o nome
    public int getNumber() { return number; }  // ler o número

    public void setName(String n) { name = n; }  // alterar o nome
    public void setNumber(int x) { number = x; } // alterar o número
}
