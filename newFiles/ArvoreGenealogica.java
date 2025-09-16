import java.util.ArrayList;

public class ArvoreGenealogica {
    ArrayList<Pessoa> pessoas;

    public ArvoreGenealogica() {
        pessoas = new ArrayList<Pessoa>();
    }

    public Pessoa acharPessoa(String nome) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.get(i).nome.equals(nome)) return pessoas.get(i);
        }
        return null;
    }

    public Pessoa criarPessoa(String nome) {
        // primeiro procura para não adicionar duplicata
        Pessoa p = acharPessoa(nome);
        if (p == null) {
            p = new Pessoa(nome);
            pessoas.add(p);
        }
        return p;
    }

    public void defRelacao(String filhoNome, String paiNome) {
        Pessoa filho = criarPessoa(filhoNome);
        Pessoa pai = criarPessoa(paiNome);
        pai.adFilho(filho);
    }

    private int distanciaParente(Pessoa filho, Pessoa ascendente) {
        int distancia = 0;
        Pessoa atual = filho.pai;
        while (atual != null) {
            distancia++;
            if (atual == ascendente) return distancia;
            atual = atual.pai;
        }
        return -1;
    }
    private String descendente(int n) {
        if (n == 1) return "filho";
        if (n == 2) return "neto";
        if (n == 3) return "bisneto";
        if (n == 4) return "tataraneto";
        return "descendente-" + n;
    }

    private String ascendente(int n) {
        if (n == 1) return "pai";
        if (n == 2) return "avô";
        if (n == 3) return "bisavô";
        if (n == 4) return "tataravô";
        return "ascendente-" + n;
    }

    public String parentesco(String nome1, String nome2) {
        Pessoa p1 = acharPessoa(nome1);
        Pessoa p2 = acharPessoa(nome2);
        if (p1 == null || p2 == null) return "sem relacao";

        // descendentes e ascendentes
        int d1 = distanciaParente(p1, p2);
        int d2 = distanciaParente(p2, p1);
        if (d1 > 0) return descendente(d1);
        if (d2 > 0) return ascendente(d2);

        // irmaos
        if (p1.pai != null && p1.pai == p2.pai) return "irmao";


        // primos

        //TODO

        // qlqr outro
        return "sem relacao";
    }

}
