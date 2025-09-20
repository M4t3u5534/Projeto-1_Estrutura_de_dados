import java.util.ArrayList;

public class arvore {
    ArrayList<Pessoa> familia;


    //array list de membros da familia (evita duplicatas)
    public arvore() {
        familia = new ArrayList<Pessoa>();
    }


    // busca no array da familia
    public Pessoa buscaMembro(String nome) {
        for (int i = 0; i < familia.size(); i++) {
            if (familia.get(i).nome.equals(nome)) return familia.get(i);
        }
        return null;
    }

    public Pessoa atualizaFamilia(String nome) {
        // primeiro procura para não adicionar duplicata
        Pessoa p = buscaMembro(nome);
        if (p == null) {
            p = new Pessoa(nome);
            familia.add(p);
        }
        return p;
    }

    // define relação pai e filho, cria membros e atualiza array da familia
    public void insert(String filhoNome, String paiNome) {
        Pessoa filho = atualizaFamilia(filhoNome);
        Pessoa pai = atualizaFamilia(paiNome);
        pai.adFilho(filho);
    }


    // pega todos os ancestrais do membro da familia
    private ArrayList<Pessoa> getAncestrais(Pessoa p) {
        ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
        Pessoa atual = p;
        while (atual != null) {
            lista.add(atual);
            atual = atual.pai;
        }
        return lista;
    }

    // retorna n (nível do ascendente em comum) e m (diferença de nivel entre membros)
    private int[] defPrimo(Pessoa p1, Pessoa p2) {
        ArrayList<Pessoa> ancestrais1 = getAncestrais(p1);
        ArrayList<Pessoa> ancestrais2 = getAncestrais(p2);

        int i = ancestrais1.size() - 1;
        int j = ancestrais2.size() - 1;

        // achar o ascendente em comum mais próximo (atualiza i e j -> menor valor entre eles é o nível do ascendente comum)
        Pessoa ancComum = null;
        while (i >= 0 && j >= 0 && ancestrais1.get(i) == ancestrais2.get(j)) {
            ancComum = ancestrais1.get(i);
            i--;
            j--;
        }

        if (ancComum == null || ancComum == p1 || ancComum == p2) return null; // não são primos

        int m = Math.abs(i - j);   // diferença de níveis
        int n = Math.min(i, j); // nivel do ascendente comum
        return new int[]{n, m};
    }

    // distancia em níveisentre
    private int distanciaParente(Pessoa filho, Pessoa ascendente) {
        int distancia = 0;
        Pessoa atual = filho.pai;
        // enquanto o pai do membro atual não for null
        while (atual != null) {
            distancia++;
            if (atual == ascendente) return distancia;
            atual = atual.pai; // vai subindo
        }
        return -1;
    }
    private String descendente(int n) {
        if (n == 1) return "filho";
        if (n == 2) return "neto";
        if (n == 3) return "bisneto";
        if (n == 4) return "tataraneto";
        // TODO tatatatatatatatatataat
        return "tatatatatatatata";
    }

    private String ascendente(int n) {
        if (n == 1) return "pai";
        if (n == 2) return "avô";
        if (n == 3) return "bisavô";
        if (n == 4) return "tataravô";
        // TODO tatatatatatatatatataat
        return "tattatatatataatatat";
    }

    public String consultParentesco(String nome1, String nome2) {
        // caso 0: membros fora da família
        Pessoa p1 = buscaMembro(nome1);
        Pessoa p2 = buscaMembro(nome2);
        if (p1 == null || p2 == null) return "sem relacao";

        // caso 1: descendentes ou ascendentes (filho - pai - neto ...)
        int d1 = distanciaParente(p1, p2);
        int d2 = distanciaParente(p2, p1);
        if (d1 > 0) return descendente(d1);
        if (d2 > 0) return ascendente(d2);

        // caso 2: irmãos
        if (p1.pai != null && p1.pai == p2.pai) return "irmao";

        // caso 3: primos
        int[] primo = defPrimo(p1, p2);
        if (primo != null) {
            if (primo[1] == 0) return "primo-" + primo[0];
            return "primo-" + primo[0] + " em grau " + primo[1];
        }

        return "sem relacao";
    }

}
