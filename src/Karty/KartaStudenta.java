package Karty;

public class KartaStudenta extends KartaKlienta {

    public KartaStudenta() {
        super();
    }

    public KartaStudenta(int numer, String nazwisko) {
        super(numer, nazwisko);
    }

    @Override
    public int rabat() {
        return 10;
    }
}
