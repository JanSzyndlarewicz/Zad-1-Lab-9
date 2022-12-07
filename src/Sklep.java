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

    public static void initialisationArray(KartaKlienta[] u, int n){
        for(int j=0; j<n; j++){
            Random random = new Random();
            int wchichCard = random.nextInt(3)+1;
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

            u[ileKartKlienta] = choseninitialisation(u, wchichCard, numer, nazwisko);
            ileKartKlienta++;
        }

    }

    public static KartaKlienta choseninitialisation(KartaKlienta[] u, int wchichCard, int numer, String nazwisko){
        Random random = new Random();
        KartaKlienta tmp = null;
        boolean sole = true;
        int i=0;

        //czy istnieje podany numer
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

    public static void addOneTransaction(Transakcja[] t, double kwota, KartaKlienta u){
        t[ileTransakcji] = new Transakcja( kwota, u);
        ileTransakcji++;


    }

    public static KartaKlienta cardToAddTransaction(KartaKlienta[] u) throws IOException {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
            ileKartKlienta++;
        }
        else{
            int i=0;
            System.out.println("Podaj numer karty");
            int cardNumber = scan.nextInt();
            ifExist = false;
            do{
                if(u[i].getNumer() == cardNumber){
                    ifExist = true;
                }
                i++;
            }while(i<ileKartKlienta && !ifExist);

            if(ifExist){
                tmp = i-1;
            }
            else {
                tmp = ileKartKlienta+1;
            }
        }

        return u[tmp];
    }


    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Transakcja[] t = new Transakcja[100];
        KartaKlienta[] u = new KartaKlienta[200];

        initialisationArray(u, 100);
        for (int i=0; i<100; i++){
            System.out.println(u[i]);
        }

        //System.out.println(cardToAddTransaction(u));
        double kwota = 205.66;

        addOneTransaction(t, kwota, cardToAddTransaction(u));
        System.out.println(t[0]);
        //Add One Transaction
 /*       boolean ifExist;
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

*/








//addOneTransaction(t, 200, card, cardNumber, ifExist);



    }
}