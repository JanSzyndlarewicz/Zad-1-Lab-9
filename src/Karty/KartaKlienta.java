package Karty;

public abstract class KartaKlienta {
    private int numer;
    private String nazwisko;

    public KartaKlienta(){
        numer = 0;
        nazwisko = null;
    }

    public KartaKlienta(int numer, String nazwisko) {
        this.numer = numer;
        this.nazwisko = nazwisko;
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public abstract int rabat();

    @Override
    public String toString() {
        return "Karty.KartaKlienta{" +
                "numer=" + numer +
                ", nazwisko=" + nazwisko + '\'' +
                '}';
    }
}
