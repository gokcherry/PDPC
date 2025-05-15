/**
*
* @author Gökçe Çiçek Yağmur - gokce.yagmur@ogr.sakarya.edu.tr
* @since Nisan 2025
* <p>
* sim sınıfı
* </p>
*/
package simulasyon;

import java.util.List;
import modeller.Gezegen;
import modeller.Kisi;
import modeller.UzayGemisi;
import main.Main;

public class Simulasyon {
    private List<Gezegen> gezegenler;
    private List<UzayGemisi> uzayGemileri;
    private int aktifGemiler;
    
    public Simulasyon(List<Gezegen> gezegenler, List<UzayGemisi> uzayGemileri) {
        this.gezegenler = gezegenler;
        this.uzayGemileri = uzayGemileri;
        this.aktifGemiler = uzayGemileri.size();
    }
    
    public void run() {
        durumGoster();
        bekle(500);
        
        while(aktifGemiler > 0) {
            for(Gezegen gezegen : gezegenler) {
                gezegen.zamanDongusu();
            }
            
            for(UzayGemisi gemi : uzayGemileri) {
                gemi.kalkisKontrol(gemi.getCikis().getZaman());
                gemi.dongu();
                
                if (gemi.hedefeVardi()) {
                    for (Kisi k : gemi.yolcuYukleme()) {
                        gemi.getVaris().gezegenSakiniEkle(k);
                    }
                    gemi.varisGerceklesti(); 
                }

                if ((gemi.hedefeVardi() || gemi.yokOlduMu()) && !gemi.isBitisIsaretlendi()) {
                    aktifGemiler--;
                    gemi.setBitisIsaretlendi(true);
                }

            }
            Main.konsoluTemizle();
            durumGoster();
            bekle(500);
        }
        
        Main.konsoluTemizle();
        System.out.println("\nSİMÜLASYON TAMAMLANDI - TÜM ARAÇLAR HEDEFLERİNE ULAŞTI VEYA İMHA OLDU");
        durumGoster();
    }
    
    private void durumGoster() {
    	
        System.out.print("\nGezegenler\t\t");
        for (Gezegen gezegen : gezegenler) {
            System.out.print("--" + gezegen.getAd() + "--\t");
        }
        System.out.println();

        System.out.print("Tarih\t\t\t");
        for (Gezegen g : gezegenler) {
            String tarih = String.format("%02d.%02d.%d", g.getZaman().getGun(), g.getZaman().getAy(), g.getZaman().getYil());
            System.out.print(tarih + "\t");
        }
        System.out.println();
        
        System.out.print("Nüfus\t\t\t");
        for (Gezegen g : gezegenler) {
            String nufus = String.format("%d", g.getGezegenSakinleriSayisi());
            System.out.print(String.format("%6s", nufus) + "\t\t");
        }
        System.out.println("\n");
        
        System.out.println("Uzay Araçları:");
        System.out.println("Araç Adı\t\tDurum\t\tÇıkış\t\tVarış\t\tHedefe Kalan Saat\t\tHedefe Varacağı Tarih");
        
        for (UzayGemisi u : uzayGemileri) {
        	String durum = u.hedefeVardi() ? "Vardı" :
                		u.yokOlduMu() ? "İmha" :
                		u.yolaCiktiMi() ? "Yolda" : "Bekliyor";

            
            String cikisGezegen = u.getCikis().getAd();
            String varisGezegen = u.getVaris().getAd();
            String kalanSaat = u.hedefeVardi() ? "0" : String.valueOf(u.getKalanZaman());

            
            String varisTarihi;
            if (u.yokOlduMu()) {
                varisTarihi = "--";
            } else if (u.hedefeVardi()) {
                varisTarihi = String.format("%02d.%02d.%d", 
                               u.getVaris().getZaman().getGun(), 
                               u.getVaris().getZaman().getAy(), 
                               u.getVaris().getZaman().getYil());
            } else {
                varisTarihi = u.hesaplaVarisTarihi().toString();
            }
            
            System.out.println(String.format("%-8s\t\t%-10s\t%-8s\t%-8s\t%-8s\t\t\t%-10s", 
                               u.getAd(), durum, cikisGezegen, varisGezegen, kalanSaat, varisTarihi));
        }
    }
    
    private void bekle(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}