import java.util.ArrayList;

public class Node {
    private String nome;
    private Node pai;
    private ArrayList<Node> filhos;  // lista dinâmica

    public Node(String nome) {
        this.nome = nome;
        this.filhos = new ArrayList<>();  // inicializa a lista
    }

    public void setFilho(String filho) {
        Node aux = new Node(filho);
        aux.setPai(this);
        this.filhos.add(aux);  // adiciona dinamicamente
    }

    public void setFilho(Node filhos) {
        this.filhos.add(filhos);
    }

    public int getQuant_filhos() {
        return filhos.size(); // tamanho da lista
    }

    public String getNome() {
        return nome;
    }

    public void setPai(Node pai) {
        this.pai = pai;
}

    public Node getFilho(int i) {
        return filhos.get(i);
    }

    public Node getPai() {
        return pai;
    }
    public ArrayList<Node> getFilhos() {
        return filhos;
    }
}