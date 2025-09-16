import java.util.ArrayList;
/*
Na implementação desse projeto foi necessária a manipulação 
de ArrayList. Foram utilizadas:
- add(ele) -> adiciona um elemento 'ele' ao final da lista
- get(int i) -> retorna o elemento que está na posição i da lista
- size() -> retorna o número de elementos atualmente na lista

Referência: https://www.devmedia.com.br/explorando-a-classe-arraylist-no-java/24298 
 */


public class Pessoa {
    String nome;
    Pessoa pai;
    ArrayList<Pessoa> filhos;

    public Pessoa(String nome) {
        this.nome = nome;
        this.filhos = new ArrayList<Pessoa>();
        this.pai = null;
    }

    public void adFilho(Pessoa filho) {
        if (filhos.size() >= 2) {
            System.out.println(nome + " ja tem 2 filhos");
        } else {
            filhos.add(filho);
            filho.pai = this;
        }
    }
}
