/**
*
* @author Gökçe Çiçek Yağmur - gokce.yagmur@ogr.sakarya.edu.tr
* @since Nisan 2025
* <p>
* main sınıfı
* </p>
*/
package modeller;

import java.util.ArrayList;
import java.util.List;

public class Gezegen {
    private String ad;
    private Zaman gezegenZamani;
    private List<Kisi> gezegenSakinleri;
    
    public Gezegen(String ad, int gunBasinaSaat, Zaman baslangicZamani) {
        this.ad = ad;
        this.gezegenZamani = new Zaman(baslangicZamani.getGun(), baslangicZamani.getAy(), baslangicZamani.getYil(), gunBasinaSaat);
        this.gezegenSakinleri = new ArrayList<>();
    }
    
    public String getAd() {
        return ad;
    }
    
    public void zamanDongusu() {
        gezegenZamani.gunDongusu(1);
        gezegenSakinleri.forEach(k -> k.yas(1));
        gezegenSakinleri.removeIf(Kisi::olduMu);
    }
    
    public void gezegenSakiniEkle(Kisi k) {
        if(!k.olduMu()) gezegenSakinleri.add(k);
    }
    
    public int getGezegenSakinleriSayisi() {
        return gezegenSakinleri.size();
    }
    
    public Zaman getZaman() {
        return gezegenZamani;
    }
       
}