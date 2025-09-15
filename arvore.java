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
    public boolean insert(String pai, String filho, Node base) {
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
            if (insert(pai, filho, base.getFilho(i))) {
                return true;
            }
        }

        return false; // não encontrou
    }

    public String consultaParentesco(String nome1, String nome2){
        
    }
}
