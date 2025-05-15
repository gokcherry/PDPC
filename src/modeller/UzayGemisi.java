/**
*
* @author Gökçe Çiçek Yağmur - gokce.yagmur@ogr.sakarya.edu.tr
* @since Nisan 2025
* <p>
* uzay gemisi sınıfı
* </p>
*/
package modeller;

import java.util.ArrayList;
import java.util.List;

public class UzayGemisi {
    private String ad;
    private Gezegen cikis;
    private Gezegen varis;
    private Zaman cikisZamani;
    private int mesafe;
    private List<Kisi> yolcular;
    private boolean yokOlduMu;
    private int kalanZaman;
    private boolean yolaCikti;
    private boolean varisYapildi = false;
    private boolean bitisIsaretlendi = false;
    
    public UzayGemisi(String ad, Gezegen cikis, Gezegen varis, Zaman cikisZamani, int mesafe) {
        this.ad = ad;
        this.cikis = cikis;
        this.varis = varis;
        this.cikisZamani = cikisZamani;
        this.mesafe = mesafe;
        this.yolcular = new ArrayList<>();
        this.yokOlduMu = false;
        this.yolaCikti = false;
        this.kalanZaman = mesafe;
    }

    public void varisGerceklesti() {
        this.varisYapildi = true;
    }

    public String getAd() {
        return ad;
    }
    
    public Gezegen getCikis() {
        return cikis;
    }

    public Gezegen getVaris() {
        return varis;
    }
    
    public boolean yolaCiktiMi() {
        return yolaCikti;
    }
    
    public List<Kisi> getYolcuListesi() {
        return new ArrayList<>(yolcular);
    }

    public boolean isBitisIsaretlendi() {
        return bitisIsaretlendi;
    }

    public void setBitisIsaretlendi(boolean b) {
        bitisIsaretlendi = b;
    }

    public void kalkisKontrol(Zaman gezegenZamani) {
        if(!yolaCikti && gezegenZamani.esitlik(cikisZamani)) {
            yolaCikti = true;
        }		
    }
    
    public void dongu() {
    	if (yolaCikti && !yokOlduMu) {
    	    if (kalanZaman > 0) {
    	        kalanZaman--;
    	    }
    	    yolcular.forEach(y -> y.yas(1));
    	    yolcular.removeIf(Kisi::olduMu);
    	    if (yolcular.isEmpty() && !varisYapildi) yokOlduMu = true; 	
    	}

    }
    
    public boolean hedefeVardi() {
        return yolaCikti && kalanZaman <= 0 && !yokOlduMu;
    }
    
    public boolean yokOlduMu() {
    	return !varisYapildi && yokOlduMu;
    }
    
    public void yolcuEkle(Kisi k) {
        yolcular.add(k);
    }
    
    public List<Kisi> yolcuYukleme() {
        List<Kisi> vardi = new ArrayList<>(yolcular);
        yolcular.clear();
        return vardi;
    }
    
    public int getKalanZaman() {
        if(yokOlduMu) return 0;
        return kalanZaman;
    }
    
    public Zaman hesaplaVarisTarihi() {
        Zaman varisZamani = new Zaman(
            varis.getZaman().getGun(),
            varis.getZaman().getAy(),
            varis.getZaman().getYil(),
            varis.getZaman().getGunBasinaSaat()
        );
        varisZamani.saatEkle(kalanZaman);
        return varisZamani;
    }
}