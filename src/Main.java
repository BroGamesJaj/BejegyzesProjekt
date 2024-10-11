import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static List<Bejegyzes> bejegyzes_lista = new ArrayList<Bejegyzes>();
    public static void main(String[] args) {
        //2.a
        bejegyzes_lista.add(new Bejegyzes("Pásztor Botond","Ez az első bejegyzés!"));
        bejegyzes_lista.add(new Bejegyzes("Nem Én","Huge második bejegyzés!"));
        //2.b
        Scanner sc = new Scanner(System.in);
        System.out.println("Adja meg, hogy hány bejegyzést szeretne hozzáadni:");
        int ujBejSzam = -1;
        if(sc.hasNextInt() && (ujBejSzam = sc.nextInt()) < 0) {
            System.out.println("Természetes számot kell megadni!");
        }else{
            sc.nextLine();
            for (int i = 1; i <= ujBejSzam; i++) {
                System.out.println("Adja meg a szerző nevét:");
                String szerzo = sc.nextLine().strip();
                System.out.println("Adja meg a bejegyzés tartalmát: ");
                String tartalom = sc.nextLine().strip();
                bejegyzes_lista.add(new Bejegyzes(szerzo,tartalom));
            }
        }
        // 2.c
        try(BufferedReader br = new BufferedReader(new FileReader("src/bejegyzesek.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                bejegyzes_lista.add(new Bejegyzes(data[0], data[1]));
            }
        }catch (IOException e){
            System.out.println("Nem sikerült beolvasni a fájlt!");
        }
        // 2.d
        Random rand = new Random();
        for (int i = 0; i < 20*bejegyzes_lista.size(); i++) {
            bejegyzes_lista.get(rand.nextInt(bejegyzes_lista.size())).like();
        }
        // 2.e
        System.out.println("Adja meg az új tartalmát a második bejegyzésnek: ");
        bejegyzes_lista.get(1).setTartalom(sc.nextLine().strip());
        // 2.f
        bejegyzes_lista.forEach(System.out::println);
    }
}