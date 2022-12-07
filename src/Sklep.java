import Karty.KartaKlienta;
import Karty.KartaPodstawowa;
import Karty.KartaSeniora;
import Karty.KartaStudenta;

import java.util.Random;
import java.util.Scanner;

public class Sklep {
    static int ileTransakcji = 0;
    static int ileKartKlienta = 0;



    public static KartaKlienta initialisation(KartaKlienta[] u){
        Random random = new Random();
        int card = random.nextInt(3)+1;
        int numer;
        String nazwisko;
        KartaKlienta tmp = null;
        boolean sole = true;

        do{
            numer = random.nextInt(1000000);
            int i = 0;

            do{
                if(u[i]!=null){
                    if(u[i].getNumer() == numer){
                        sole = false;
                    }
                }
                i++;

            }while(i<ileTransakcji && sole);

        }while(!sole);

        nazwisko = String.valueOf(numer);

        switch (card){
            case 1:{
                tmp = new KartaPodstawowa(numer, nazwisko);
                break;
            }

            case 2:{
                tmp = new KartaStudenta(numer, nazwisko);
                break;
            }

            case 3:{
                tmp = new KartaSeniora(numer, nazwisko);
                break;
            }

            default:{
                break;
            }
        }

        return tmp;
    }

    public static void initialisationArray(KartaKlienta[] u, int n){
        for(int i=0; i<n; i++){
            u[ileKartKlienta] = initialisation(u);
            ileKartKlienta++;
        }

    }

    public static void addOneTransaction(Transakcja[] t, double kwota, int card){
        if(ileTransakcji<t.length){
            t[ileTransakcji]  = new Transakcja(kwota, new KartaSeniora(2000, "dafa"));
            ileTransakcji++;
        }
        else{
            System.out.println("Nie można dodać więcej transakcji");
        }

    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Transakcja[] t = new Transakcja[100];
        KartaKlienta[] u = new KartaKlienta[200];
        initialisationArray(u, 100);


        //Add One Transaction
        int card;
        do{
            System.out.println("1 - Karta podstawowa");
            System.out.println("2 - Karta studenta");
            System.out.println("3 - Karta seniora");
            card = scan.nextInt();
        }while(card<1 || card>3);





        //addOneTransaction(t, 200, card);


    }
}