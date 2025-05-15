/**
*
* @author Gökçe Çiçek Yağmur - gokce.yagmur@ogr.sakarya.edu.tr
* @since Nisan 2025
* <p>
* kisi sınıfı
* </p>
*/
package modeller;

public class Kisi {
    private String ad;
    private int yas;
    private int kalanYasamSuresi;
    private String uzayGemisi;
    
    public Kisi(String ad, int yas, int kalanYasamSuresi, String uzayGemisi){
        this.ad = ad;
        this.yas = yas;
        this.kalanYasamSuresi = kalanYasamSuresi;
        this.uzayGemisi = uzayGemisi;
    }   
    
    public void yas(int saatler) {
        kalanYasamSuresi -= saatler;
    }
    
    public boolean olduMu() {
        return kalanYasamSuresi <= 0;
    }
    
    public String uzayAraciIsminiGetir() {
        return uzayGemisi;
    }
}