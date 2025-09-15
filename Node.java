import java.util.ArrayList;

public class Node {
    private String nome;
    private ArrayList<Node> filhos;  // lista dinâmica

    public Node(String nome) {
        this.nome = nome;
        this.filhos = new ArrayList<>();  // inicializa a lista
    }

    public void setFilho(String filho) {
        this.filhos.add(new Node(filho));  // adiciona dinamicamente
    }

    public int getQuant_filhos() {
        return filhos.size(); // tamanho da lista
    }

    public String getNome() {
        return nome;
    }

    public Node getFilho(int i) {
        return filhos.get(i);
    }
}
