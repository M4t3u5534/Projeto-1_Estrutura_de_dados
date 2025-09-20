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
        if (filhos.size() >= 2) {
            System.out.println(nome + " ja tem 2 filhos");
        } else {
            Node aux = new Node(filho);
            aux.setPai(this);
            this.filhos.add(aux);
        }
    }

    public int getQuant_filhos() {
        return filhos.size(); // tamanho da lista
    }

    public String getNome() {
        return nome;
    }

    private void setPai(Node pai) {
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
