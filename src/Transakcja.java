import Karty.KartaKlienta;

public class Transakcja {
    public static int numerTransakcji = 0;
    private double kwota;
    private KartaKlienta karta;

    public Transakcja(){
        kwota = 0;
        karta = null;
    }

    public Transakcja(double kwota, KartaKlienta karta) {
        this.kwota = kwota;
        this.karta = karta;
    }

    public static int getNumerTransakcji() {
        return numerTransakcji;
    }

    public static void setNumerTransakcji(int numerTransakcji) {
        Transakcja.numerTransakcji = numerTransakcji;
    }

    public double getKwota() {
        return kwota;
    }

    public void setKwota(double kwota) {
        this.kwota = kwota;
    }

    public KartaKlienta getKarta() {
        return karta;
    }

    public void setKarta(KartaKlienta karta) {
        this.karta = karta;
    }

    public double kwotaDoZaplaty(){
        return kwota * ((double)(100 - karta.rabat())/100);
    }

    @Override
    public String toString() {
        return "Transakcja{" +
                "kwota=" + kwota +
                ", karta=" + karta +
                '}';
    }
}
