import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
O método usado na main usa o BufferedReader junto do FileReader 
para ler os arquivos entrada.txt e consultas.txt linha por linha. 
Cada linha é processada separando os elementos com split("\\s+").
Após a leitura, os arquivos são fechados com close().

Referência utilizada: https://www.datacamp.com/pt/doc/java/read-files 
 */

public class Main {
    public static void main(String[] args) throws IOException {
        arvore arvore = new arvore();

        // leitura de arquivo entrada.txt
        BufferedReader br = new BufferedReader(new FileReader("entrada.txt"));
        String linha;
        Map<String, String> espera = new HashMap<>();
        while ((linha = br.readLine()) != null) {
            boolean inseriu = true;
            String[] partes = linha.split("\\s+"); // separa por espaços
            if (partes.length == 2) {
                inseriu = arvore.insert(partes[0], partes[1]);
            }
            if (inseriu == false) {
                espera.put(partes[0], partes[1]);
            }
        }

        int i = espera.size();
        ArrayList<String> remover = new ArrayList<>();
        while (i > 0) {
            int aux = i;
            for (String filho : espera.keySet()) {
                String pai = espera.get(filho);
                if (arvore.insert(filho, pai)) {
                    remover.add(filho);
                    i--;
                }
            }
            while (!remover.isEmpty()) {
                espera.remove(remover.remove(0));
            }
            if (aux == i) break;
        }

        br.close();

        BufferedReader consultas = new BufferedReader(new FileReader("consultas.txt"));
        while ((linha = consultas.readLine()) != null) {
            String[] partes = linha.split("\\s+");
            if (partes.length == 2) {
                arvore.consultaParentesco(partes[0], partes[1]);
            }
        }
        consultas.close();
    }
}