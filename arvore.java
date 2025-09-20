import java.lang.Math;

public class arvore {
    private Node raiz;

    // construtor
    public arvore() {
        this.raiz = null;
    }

    // versão SEM o parâmetro extra (chama a versão completa)
    public boolean insert(String pai, String filho) {
        return insert(pai, filho, raiz);
    }

    // versão COM o parâmetro extra
     public boolean insert(String filho, String pai, Node base) {
        // Se a árvore está vazia, cria o pai como raiz
        if (this.raiz == null) {
            this.raiz = new Node(pai);
            this.raiz.setFilho(filho);
            return true;
        }
        
        // Verifica se o filho já existe e se é a raiz atual
        Node filhoNode = getNode(filho);
        if (filhoNode != null && filhoNode == this.raiz) {
            Node novoPai = new Node(pai);
            novoPai.getFilhos().add(this.raiz);
            this.raiz.setPai(novoPai);
            this.raiz = novoPai;
            return true;
        }
        
        // Procura o pai na árvore
        Node paiNode = getNode(pai);
        if (paiNode != null) {
            paiNode.setFilho(filho);
            return true;
        }
        
        return false;
    }

    public void consultaParentesco(String nome1, String nome2){
        Node p1 = getNode(nome1);
        Node p2 = getNode(nome2);
        
        // Verifica se ambos existem
        if (p1 == null || p2 == null) {
            System.out.println("sem relacao");
            return;
        }
        
        int d1 = getDescendencia(p1, p2);
        int d2 = getDescendencia(p2, p1);
        
        if (d1 >= 0) { // p1 é descendente de p2
            if (d1 == 0) System.out.println("filho");
            else if (d1 == 1) System.out.println("neto");
            else if (d1 == 2) System.out.println("bisneto");
            else {
                for (int i = 0; i < d1 - 2; i++) {
                    System.out.print("ta");
                }
                System.out.println("taraneto");
            }
        } else if (d2 >= 0) { // p2 é descendente de p1
            if (d2 == 0) System.out.println("pai");
            else if (d2 == 1) System.out.println("avo");
            else if (d2 == 2) System.out.println("bisavo");
            else {
                for (int i = 0; i < d2 - 2; i++) {
                    System.out.print("ta");
                }
                System.out.println("taravo");
            }
        } else {
            // Verifica se são irmãos
            if (p1.getPai() != null && p1.getPai() == p2.getPai()) {
                System.out.println("irmao");
            } else {
                Node ancestralComum = achaAncestralComum(p1, p2);
                if (ancestralComum == null) {
                    System.out.println("sem relacao");
                } else {
                    int nivelP1 = getNivel(p1);
                    int nivelP2 = getNivel(p2);
                    int nivelAncestral = getNivel(ancestralComum);
                    
                    int m = nivelP1 - nivelAncestral - 1;
                    int n = nivelP2 - nivelAncestral - 1;
                    
                    int k = Math.min(m, n);
                    int grau = Math.abs(m - n);
                    
                    if (grau == 0) {
                        System.out.println("primo-" + k);
                    } else {
                        System.out.println("primo-" + k + " em grau " + grau);
                    }
                }
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