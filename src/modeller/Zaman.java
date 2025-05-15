/**
*
* @author Gökçe Çiçek Yağmur - gokce.yagmur@ogr.sakarya.edu.tr
* @since Nisan 2025
* <p>
* zaman sınıfı
* </p>
*/
package modeller;

public class Zaman {
    private int gun;
    private int ay;
    private int yil;
    private int saat;
    private int gunBasinaSaat;
    
    public Zaman(int gun, int ay, int yil, int gunBasinaSaat) {
        this.gun = gun;
        this.ay = ay;
        this.yil = yil;
        this.saat = 0;
        this.gunBasinaSaat = gunBasinaSaat;
    }
    
    public int getGun() {
        return gun;
    }

    public int getAy() {
        return ay;
    }
    
    public int getYil() {
        return yil;
    }
    
    public int getGunBasinaSaat() {
        return gunBasinaSaat;
    }
    
    public void gunDongusu(int saatler) {
        this.saat += saatler;
        while (this.saat >= gunBasinaSaat) {
            this.saat -= gunBasinaSaat;
            this.gun++;
            if (this.gun > 30) {
                this.gun = 1;
                this.ay++;
                if (this.ay > 12) {
                    this.ay = 1;
                    this.yil++;
                }
            }
        }
    }
    
    public void saatEkle(int saatler) {
        gunDongusu(saatler);
    }
    
    public String toString() {
        return String.format("%02d.%02d.%d %02d:00", gun, ay, yil, saat);
    }
    
    public boolean esitlik(Zaman baska) {
        return this.gun == baska.gun && this.ay == baska.ay && this.yil == baska.yil;
    }
}