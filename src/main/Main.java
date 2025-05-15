/**
*
* @author Gökçe Çiçek Yağmur - gokce.yagmur@ogr.sakarya.edu.tr
* @since Nisan 2025
* <p>
* main sınıfı
* </p>
*/
package main;

import araclar.DosyaOkuma;
import modeller.Gezegen;
import modeller.Kisi;
import modeller.UzayGemisi;
import simulasyon.Simulasyon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<Gezegen> gezegenler = DosyaOkuma.gezegenOku("C:\\Users\\gokce\\eclipse-workspace\\PDProject\\data\\Gezegenler.txt");
        Map<String, Gezegen> gezegenMap = new HashMap<>();
        for (Gezegen gezegen : gezegenler) {
            gezegenMap.put(gezegen.getAd(), gezegen);
        }

        List<UzayGemisi> uzayGemileri = DosyaOkuma.aracOku("C:\\Users\\gokce\\eclipse-workspace\\PDProject\\data\\Araclar.txt", gezegenMap);
        Map<String, UzayGemisi> aracMap = new HashMap<>();
        for (UzayGemisi arac : uzayGemileri) {
            aracMap.put(arac.getAd(), arac);
        }

        List<Kisi> kisiler = DosyaOkuma.kisiOku("C:\\Users\\gokce\\eclipse-workspace\\PDProject\\data\\Kisiler.txt");
        DosyaOkuma.kisileriAraclaraYerlestir(kisiler, aracMap);

        Simulasyon sim = new Simulasyon(gezegenler, uzayGemileri);
        konsoluTemizle();
        sim.run();
    }

    public static void konsoluTemizle() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("\n".repeat(50));
        }
    }
}
