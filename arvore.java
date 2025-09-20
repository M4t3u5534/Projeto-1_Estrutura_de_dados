import java.lang.Math;

public class arvore {
    private Node raiz;

    // construtor
    public arvore() {
        this.raiz = null;
    }

    // versão SEM o parâmetro extra (chama a versão completa)
    public boolean insert(String filho, String pai) {
        return insert(filho, pai, raiz);
    }

    // versão COM o parâmetro extra
    public boolean insert(String filho, String pai, Node base) {
        if (base == null) {
            this.raiz = new Node(pai);
            this.raiz.setFilho(filho);
            return true;
        }

        // achou o pai
        if (pai.equalsIgnoreCase(base.getNome())) {
            base.setFilho(filho);
            return true;
        }

        // senão, procura recursivamente nos filhos
        for (int i = 0; i < base.getQuant_filhos(); i++) {
            if (insert(filho, pai, base.getFilho(i))) {
                return true;
            }
        }

        return false; // não encontrou
    }

    public void consultaParentesco(String nome1, String nome2){
        Node p1 = getNode(nome1);
        Node p2 = getNode(nome2);

        if (p1 == null || p2 == null) {
            System.out.println("sem relacao");
            return;
        }

        int d1 = getDescendencia(p1,p2);
        int d2 = getDescendencia(p2,p1);

        if (d1 != -1) {
            if (d1 == 0) System.out.println("filho");
            else if (d1 == 1) System.out.println("neto");
            else if (d1 == 2) System.out.println("bisneto");
            else {
                for (int i = 0; i < d1-1; i++) {
                    System.out.print("ta");
                }
                System.out.println("raneto");
            }
        } else if (d2 != -1) {
            if (d2 == 0) System.out.println("pai");
            else if (d2 == 1) System.out.println("avo");
            else if (d2 == 2) System.out.println("bisavo");
            else {
                for (int i = 0; i < d2-1; i++) {
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
                System.out.println("primo-" + n + " em grau " + grau);
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
        int d = 0;
        Node aux = p;
        while (aux.getPai() != null) {
            if (aux.getPai() == q) return d;
            aux = aux.getPai();
            d++;
        } return -1;
    }

    private Node getNode(String nome){
        Node base = raiz;
        return getNode(nome, base);
    }

    private Node getNode(String nome, Node base){
        if (base == null) return null;
        if (base.getNome().equals(nome)) return base;
        for (Node filho : base.getFilhos()) {
            Node achado = getNode(nome, filho);
            if (achado != null) return achado;
        }
        return null;
    }

}
