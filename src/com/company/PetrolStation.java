package com.company;

import java.util.Random;

public class PetrolStation {

    private int pumpNumb;
    private Threads[] pumps;
    public static Semaphore semaphore;
    private Random rand;

    public PetrolStation( int pumpNumb ){
        this.pumpNumb = pumpNumb;
        semaphore = new Semaphore(pumpNumb);
        pumps = new Threads[pumpNumb];
        rand = new Random();
    }

    public synchronized void arrived( Threads client ){
        System.out.println(client.clientName+" Arrived");
    }

    public synchronized int occupyPump( Threads client ){
        int i;
        for ( i=0;i<pumpNumb;i++){
            if ( pumps[i] == null ){
                System.out.println(client.clientName + " occupied pump number "+ (i+1));
                pumps[i]=client;
                break;
            }
        }
        return i;
    }

    public synchronized void served( Threads client , int index){
        System.out.println(client.clientName+" is Being Served");
        Main.gui.setColorYellow(index, client.clientName+" is Being Served");
        try {
            client.sleep(rand.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void paying( Threads client , int index ){
        System.out.println(client.clientName+" is paying");
        Main.gui.setColorGreen(index,client.clientName+" is paying");
        try {
            client.sleep(rand.nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void leaving( Threads client , int index ){
        System.out.println(client.clientName+" is leaving");
        for ( int i=0;i<pumpNumb;i++){
            if ( pumps[i] != null && pumps[i].clientName == client.clientName ){
                pumps[i]=null;
            }
        }
        Main.gui.setColorRed(index,client.clientName+" is leaving");
        semaphore.release();
    }

}
