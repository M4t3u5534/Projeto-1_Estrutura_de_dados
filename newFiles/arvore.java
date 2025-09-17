import java.lang.Math;
import java.util.ArrayList;

public class arvore {
    private Node raiz;
    ArrayList<Node> pessoas;

    // construtor
    public arvore() {
        this.raiz = null;
        pessoas = new ArrayList<Node>();
    }

    public Node buscaPessoa(String nome) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.get(i).nome.equals(nome)) return pessoas.get(i);
        }
        return null;
    }

    public Node atualizaPessoas(String nome) {
        // primeiro procura para não adicionar duplicata
        Node p = buscaPessoa(nome);
        if (p == null) {
            p = new Node(nome);
            pessoas.add(p);
        }
        return p;
    }

    public void insert(String filhoNome, String paiNome) {
        Node filho = atualizaPessoas(filhoNome);
        Node pai = atualizaPessoas(paiNome);
        pai.setFilho(filho);
    }

    public void consultaParentesco(String nome1, String nome2){
        Node p1 = getNode(nome1);
        System.out.println("p1");
        Node p2 = getNode(nome2);
        System.out.println("p2");
        int d1 = getDescendencia(p1,p2);
        System.out.println("d1");
        int d2 = getDescendencia(p2,p1);
        System.out.println("d2");
        if (d1 > d2) {
            if (d1 == 0) System.out.println("filho");
            else if (d1 == 1) System.out.println("neto");
            else if (d1 == 2) System.out.println("bisneto");
            else {
                for (int i = 0; i < d1-1; i++) {
                    System.out.print("ta");
                }
                System.out.println("raneto");
            }
        } else if (d2 > d1) {
            if (d1 == 0) System.out.println("pai");
            else if (d1 == 1) System.out.println("avo");
            else if (d1 == 2) System.out.println("bisavo");
            else {
                for (int i = 0; i < d1-1; i++) {
                    System.out.print("ta");
                }
                System.out.println("ravo");
            }
        } else{
            if (p1.getPai() == p2.getPai()) System.out.println("irmao");
            else {
                int n;
                Node r = achaAncestralComum(p1, p2);
                if (getNivel(p1) < getNivel(p2)) {
                    n = getNivel(p1) - getNivel(r) - 1;
                } else {
                    n = getNivel(p2) - getNivel(r) - 1;
                }
                int grau = Math.abs(getNivel(p1)-getNivel(p2));
                System.out.printf("primo-%d em grau %d\n", n, grau);
            }
        }
    }

    private Node achaAncestralComum(Node m, Node n){
        int nivelm = getNivel(m);
        int niveln = getNivel(n);
        if (m != n) {
            if (nivelm > niveln) return achaAncestralComum(m.getPai(), n);
            else return achaAncestralComum(m, n.getPai());
        } else return m;
    }

    private int getNivel(Node n){
        if (n.getPai() == null) {
            return 0;
        } else {
            return getNivel(n.getPai()) + 1;
        }
    }

    private int getDescendencia(Node p, Node q){
        if (p.getPai() == null) {
            return -1;
        }else if (p.getPai() == q) {
            return 0;
        } else {
            return getDescendencia(p.getPai(),q) + 1;
        }
    }

    private Node getNode(String nome){
        Node base = raiz;
        System.out.println("raiz do get node" + raiz);
        System.out.println("nome do get node " + nome);
        Node aux = getNode(nome, base);
        System.out.println("get node aux: " + aux);
        return aux;
    }

    private Node getNode(String nome, Node base){
        System.out.println("get node absurdo printando tudojksasduifbgajk");
        if (base == null) {
            System.out.println("base se null: " + base);
            return null;
        }
        
        int i=0;
        System.out.println("debug getnode" + (i));
        i+=1;

        if (base.getNome().equals(nome)) {
            return base;
        }

        for (Node filho : base.getFilhos()) {
            Node achado = getNode(nome, filho);
            if (achado != null){
                return achado;
            }
        }
        System.out.println("comida");
        return base;
    }

}
