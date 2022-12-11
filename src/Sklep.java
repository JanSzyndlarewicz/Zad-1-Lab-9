import Karty.KartaKlienta;
import Karty.KartaPodstawowa;
import Karty.KartaSeniora;
import Karty.KartaStudenta;

import java.io.*;
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
        Transakcja.numerTransakcji++;
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
                System.out.println("Brak podanej karty");
                tmp = ileKartKlienta+1;
            }
        }

        return u[tmp];
    }

    public static void writeToFile(Transakcja[] t) throws IOException {
        FileWriter fw = new FileWriter("KartaStudenta.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        int i=0;

        do{
            if(t[i].getKarta() instanceof KartaStudenta){
                try {

                    bw.write(t[i].toString());
                    bw.newLine();

                } catch (IOException e) {

                    System.out.println("Błąd wejścia-wyjścia: " + e.getMessage());

                }
            }
            i++;
        }while(t[i]!=null && i<t.length);

        bw.close();
    }

    public static void menu(){
        System.out.println('\n');
        System.out.println("-----Menu-----");
        System.out.println("0 - Zakończenie działania");
        System.out.println("1 - Dodanie jednej transakcji");
        System.out.println("2 - Dodanie wielu transakcji");
        System.out.println("3 - Wyświetlenie wszystkich transakcji");
        System.out.println("4 - Policzenie transakcji z kartą seniora");
        System.out.println("5 - Znaleznie osoby z kartą student, która zapłaciła najwięcej");
        System.out.println("6 - wpisz transakcje za pomocą karty studenta do pliku");
    }



    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        Transakcja[] t = new Transakcja[100];
        KartaKlienta[] u = new KartaKlienta[200];

        initialisationArray(u, 100);

        for (int i=0; i<100; i++){
            System.out.println(u[i]);
        }

        int choice;

        do{

            menu();

            choice = scan.nextInt();

            switch (choice){
                case 0:{
                    System.out.println("Do zobaczenia");
                    break;
                }
                case 1:{
                    System.out.print("-----Dodanie nowej transakcji----- '\n");
                    System.out.print("Podaj kwotę transakcji: ");

                    double kwota = scan.nextDouble();

                    addOneTransaction(t, kwota, cardToAddTransaction(u));

                    break;
                }

                case 2: {
                    System.out.print("-----Dodanie wielu transakcji----- '\n");
                    System.out.print("Podaj ilość transakcji, które chcesz wpisać: ");
                    int n;
                    double kwota;

                    do{
                        n = scan.nextInt();
                    }while(n<1 || n>t.length-ileTransakcji);

                    for(int i=0; i<n; i++){
                        System.out.print("Podaj kwotę transakcji nr " + (i+1) + ": ");
                        kwota = scan.nextDouble();
                        addOneTransaction(t, kwota, cardToAddTransaction(u));
                    }
                    break;
                }

                case 3:{
                    System.out.print("-----Wyświetlenie wszystkich transakcji----- '\n");

                    for(int i=0; i<ileTransakcji; i++){
                        System.out.println(t[i]);
                    }
                    break;
                }

                case 4:{
                    System.out.println("-----Policzenie transakcji z kartą seniora-----");
                    int count = 0;

                    for(int i=0; i<ileTransakcji; i++){
                        if(t[i].getKarta() instanceof KartaSeniora){
                            count++;
                        }
                    }

                    System.out.println("Ilość transakcji z kartą seniora: " + count);
                    break;
                }

                case 5:{
                    int indexMax = -1;
                    do{
                        indexMax++;
                    }while (!(t[indexMax].getKarta() instanceof KartaStudenta));

                    for(int i=0; i<ileTransakcji; i++){
                        if(t[i].getKarta() instanceof KartaStudenta){
                            if(t[i].kwotaDoZaplaty()>t[indexMax].kwotaDoZaplaty()){
                                indexMax = i;
                            }
                        }
                    }

                    System.out.println("Danie klienta który zapłacił najwięcej: ");
                    System.out.println(t[indexMax]);
                    break;
                }

                case 6:{
                    writeToFile(t);
                    break;
                }
            }
        }while (choice!=0);
    }
}