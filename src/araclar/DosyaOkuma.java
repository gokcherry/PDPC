/**
*
* @author Gökçe Çiçek Yağmur - gokce.yagmur@ogr.sakarya.edu.tr
* @since Nisan 2025
* <p>
* dosya okuma sınıfı, main içerisinde çağırdım.
* </p> 
*/
package araclar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import modeller.Gezegen;
import modeller.Kisi;
import modeller.UzayGemisi;
import modeller.Zaman;

public class DosyaOkuma {

    public static List<Gezegen> gezegenOku(String dosyaYolu) {
        List<Gezegen> gezegenler = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] bilgiler = satir.split("#");
                if (bilgiler.length >= 3) {
                    String gezegenAdi = bilgiler[0];
                    int gunBasinaSaat = Integer.parseInt(bilgiler[1]);
                    String[] tarihBilgileri = bilgiler[2].split("\\.");

                    int gun = Integer.parseInt(tarihBilgileri[0]);
                    int ay = Integer.parseInt(tarihBilgileri[1]);
                    int yil = Integer.parseInt(tarihBilgileri[2]);

                    Zaman baslangicZamani = new Zaman(gun, ay, yil, gunBasinaSaat);
                    gezegenler.add(new Gezegen(gezegenAdi, gunBasinaSaat, baslangicZamani));
                }
            }
        } catch (IOException e) {
            System.err.println("Gezegen dosyası okunamadı: " + e.getMessage());
        }

        return gezegenler;
    }

    public static List<Kisi> kisiOku(String dosyaYolu) {
        List<Kisi> kisiler = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] bilgiler = satir.split("#");
                if (bilgiler.length >= 4) {
                    String isim = bilgiler[0];
                    int yas = Integer.parseInt(bilgiler[1]);
                    int kalanOmur = Integer.parseInt(bilgiler[2]);
                    String uzayAraciAdi = bilgiler[3];

                    kisiler.add(new Kisi(isim, yas, kalanOmur, uzayAraciAdi));
                }
            }
        } catch (IOException e) {
            System.err.println("Kişi dosyası okunamadı: " + e.getMessage());
        }

        return kisiler;
    }

    public static List<UzayGemisi> aracOku(String dosyaYolu, Map<String, Gezegen> gezegenMap) {
        List<UzayGemisi> araclar = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] bilgiler = satir.split("#");
                if (bilgiler.length >= 5) {
                    String aracAdi = bilgiler[0];
                    Gezegen cikisGezegen = gezegenMap.get(bilgiler[1]);
                    Gezegen varisGezegen = gezegenMap.get(bilgiler[2]);
                    
                    String[] tarihBilgileri = bilgiler[3].split("\\.");
                    int gun = Integer.parseInt(tarihBilgileri[0]);
                    int ay = Integer.parseInt(tarihBilgileri[1]);
                    int yil = Integer.parseInt(tarihBilgileri[2]);

                    int mesafe = Integer.parseInt(bilgiler[4]);

                    if (cikisGezegen != null && varisGezegen != null) {
                        Zaman cikisZamani = new Zaman(gun, ay, yil, cikisGezegen.getZaman().getGunBasinaSaat());
                        araclar.add(new UzayGemisi(aracAdi, cikisGezegen, varisGezegen, cikisZamani, mesafe));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Araç dosyası okunamadı: " + e.getMessage());
        }

        return araclar;
    }

    public static void kisileriAraclaraYerlestir(List<Kisi> kisiler, Map<String, UzayGemisi> aracMap) {
        for (Kisi kisi : kisiler) {
            UzayGemisi arac = aracMap.get(kisi.uzayAraciIsminiGetir());
            if (arac != null) {
                arac.yolcuEkle(kisi);
            }
        }
    }
}
