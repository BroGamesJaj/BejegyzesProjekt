import java.io.*;
import java.util.*;

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

        // 3.a
        int maxl = 0;
        for (Bejegyzes e : bejegyzes_lista) {
            if (e.getLikeok() > maxl) {
                maxl = e.getLikeok();
            }
        }
        System.out.printf("A legnépszerűbb bejegyzésnek %d likeja van!\n",maxl);

        // 3.b
        if(maxl> 35){
            System.out.println("Van olyan bejegyzés, aminek több mint 35 likeja van!");
        }else{
            System.out.println("Nincs olyan bejegyzés, aminek több mint 35 likeja van!");
        }

        // 3.c
        int count = 0;
        for (Bejegyzes e : bejegyzes_lista) {
            if (e.getLikeok() < 15) {
                count++;
            }
        }

        System.out.printf((count == 0 ? "Nincs " : "%d ")+"olyan bejegyzés, aminek kevesebb mint 15 likeja van!\n",count);

        // 3.d
        List<Bejegyzes> sorted = bejegyzes_lista;

        sorted.sort((o1, o2) -> o2.getLikeok() - o1.getLikeok());
        sorted.forEach(System.out::println);
        // 3.d+
        try {
            File myObj = new File("bejegyzesek_rendezett.txt");
            myObj.createNewFile();
            FileWriter fw = new FileWriter("bejegyzesek_rendezett.txt");
            sorted.forEach(e -> {
                try {
                    fw.write(e.getSzerzo()+";"+e.getTartalom()+'\n');
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        } catch (IOException e) {
            System.out.println("Hiba a fájl létrehozásában");
        }

    }
}