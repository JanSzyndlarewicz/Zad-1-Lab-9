import Karty.KartaKlienta;
import Karty.KartaPodstawowa;
import Karty.KartaSeniora;
import Karty.KartaStudenta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static KartaKlienta choseninitialisation(KartaKlienta[] u, int wchichCard, int numer, String nazwisko){
        Random random = new Random();
        KartaKlienta tmp = null;
        boolean sole = true;
        int i=0;

        do{
            if(u[i]!=null){
                if(u[i].getNumer() == numer){
                    sole = false;
                }
            }
            i++;

        }while(i<ileTransakcji && sole);

        if(!sole){
            do{
                i = 0;

                numer = random.nextInt(1000000);

                do{
                    if(u[i]!=null){
                        if(u[i].getNumer() == numer){
                            sole = false;
                        }
                    }
                    i++;

                }while(i<ileTransakcji && sole);

            }while(!sole);
        }

        switch (wchichCard){
            case 1:{
                tmp = new KartaPodstawowa(numer, nazwisko);
                ileKartKlienta++;
                break;
            }

            case 2:{
                tmp = new KartaStudenta(numer, nazwisko);
                ileKartKlienta++;
                break;
            }

            case 3:{
                tmp = new KartaSeniora(numer, nazwisko);
                ileKartKlienta++;
                break;
            }

            default:{
                break;
            }
        }

        return tmp;
    }

    public static void addOneTransaction(Transakcja[] t, double kwota, int cardNumber, int card, boolean ifExist){
        switch (card){
            case 1:{
                if(ifExist){
                    if(ileTransakcji<t.length){
                       // t[ileTransakcji]  = new Transakcja(kwota, );
                        ileTransakcji++;
                    }
                }
                break;
            }

            case 2:{

                break;
            }

            case 3:{

                break;
            }
        }


    }


    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Transakcja[] t = new Transakcja[100];
        KartaKlienta[] u = new KartaKlienta[200];
        initialisationArray(u, 100);


        //Add One Transaction
        boolean ifExist;
        int wchichCard;
        int tmp;

        do{
            System.out.println("Czy masz już kartę?");
            System.out.println("1 - Tak");
            System.out.println("2 - Nie");
            tmp = scan.nextInt();
        }while(tmp<1 || tmp>2);

        if(tmp == 1){
            ifExist = true;
        }
        else{
            ifExist = false;
        }

        if(!ifExist){
            do{
                System.out.println("1 - Karta podstawowa");
                System.out.println("2 - Karta studenta");
                System.out.println("3 - Karta seniora");
                wchichCard = scan.nextInt();
            }while(wchichCard<1 || wchichCard>3);

            System.out.println("Podaj nazwisko");


            String nazwisko = reader.readLine();
            
            boolean sole = true;
            int numer;

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

            tmp = ileKartKlienta;
            u[tmp] = choseninitialisation(u, wchichCard, numer, nazwisko);
            System.out.println(u[tmp]);
            ileKartKlienta++;
        }
        else{
            int i=0;
            System.out.println("Podaj numer karty");
            int cardNumber = scan.nextInt();
            tmp = ileKartKlienta;
            u[tmp] = choseninitialisation(u, 2, cardNumber, "fasfa");
            ileKartKlienta++;

            System.out.println(u[tmp]);
        }










//addOneTransaction(t, 200, card, cardNumber, ifExist);



    }
}