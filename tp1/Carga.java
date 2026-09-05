package tp1.tp1;

import java.io.*;
import java.text.SimpleDateFormat;

public class Carga {
    public static void csv(String steamcsv, Arquivo arquivo) throws IOException {
        BufferedReader ler = new BufferedReader(new FileReader(steamcsv));

        ler.readLine(); // pular o cabeçalho do csv

        String linha;
        while ((linha = ler.readLine()) != null) {

            // dividir o csv
           String[] campos = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // utilizei isso por causa que o csv infelizmente tem nome com , dentro ai complicou tudo e bugou tudo tbm
            // nome
            String nome = campos[1];
            // data
            String data = campos[2];
            // genero
            String genero = campos[9];
            // donos
            String donos = campos[16];
            // preço
            float preco = Float.parseFloat(campos[17]);

            long dataLancamento = 0;
            // tratamento de data
            try {
                dataLancamento = new SimpleDateFormat("yyyy-MM-dd").parse(data).getTime();
            } catch (Exception e) {
                System.out.println("data deuy erro");
            }
            // criando objeto
            Jogo jogo = new Jogo(0, nome, donos, dataLancamento, preco, genero);
            // jogando objeto no crud
            arquivo.create(jogo);
        }
        ler.close();
    }
public static void main(String[] args) throws Exception {

    Arquivo arquivo = new Arquivo("jogos.db");

    csv("tp1/steam.csv", arquivo);

    System.out.println("Carga finalizada!");
}

}
