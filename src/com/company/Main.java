package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static int numPumps;
    public static int numClients;
    public static ArrayList<String> clients = new ArrayList<>();
    public static PetrolStation petrolStation;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of Pumps: ");

        numPumps = sc.nextInt();
        petrolStation = new PetrolStation(numPumps);

        System.out.print("Enter number of Clients: ");
        numClients = sc.nextInt();

        System.out.print("Enter clients’ names: ");
        for (int i = 0; i < numClients; i++) {
            clients.add(sc.next());
        }

        for ( int i=0;i<numClients;i++){
            Threads client = new Threads(clients.get(i),petrolStation);
            client.start();
        }
    }
}
