import java.io.*;
import java.util.Scanner;

/*
O método usado na main usa o BufferedReader junto do FileReader 
para ler os arquivos entrada.txt e consultas.txt linha por linha. 
Cada linha é processada separando os elementos com split("\\s+").
Após a leitura, os arquivos são fechados com close().

Referência utilizada: https://www.datacamp.com/pt/doc/java/read-files 
 */

public class Main {
    public static void main(String[] args) throws IOException {
        ArvoreGenealogica arvore = new ArvoreGenealogica();

        // leitura de arquivo entrada.txt
        BufferedReader br = new BufferedReader(new FileReader("entrada.txt"));
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split("\\s+"); // separa por espaços
            if (partes.length == 2) {
                arvore.defRelacao(partes[0], partes[1]);
            }
        }
        br.close();

        /* leitura de arquivo consultas.txt  
        BufferedReader consultas = new BufferedReader(new FileReader("consultas.txt"));
        while ((linha = consultas.readLine()) != null) {
            String[] partes = linha.split("\\s+");
            if (partes.length == 2) {
                String resultado = arvore.parentesco(partes[0], partes[1]);
                System.out.println(resultado);
            }
        }
        consultas.close();
        */

        /* via código:*/

        String[][] consultas = {
            {"carlos.souza", "bob.constable"},
            {"heitor.maciel", "joao.silva"},
            {"heitor.maciel", "carlos.souza"},
            {"les.valiant", "joao.silva"},
            {"les.valiant", "dennis.ritchie"},
            {"dennis.ritchie", "les.valiant"},
            {"patricia.fischer", "michael.rabin"},
            {"les.valiant", "oswaldo.veblen"}
        };

        for (String[] consulta : consultas) {
            String resultado = arvore.parentesco(consulta[0], consulta[1]);
            System.out.println(consulta[0] + "  " + consulta[1] + ": " + resultado);
        }
        
    }
}