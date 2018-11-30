package com.company;

import java.util.Random;

public class PetrolStation {

    private int pumpNumb;
    public static Semaphore semaphore;
    private Random rand;

    public PetrolStation( int pumpNumb ){
        this.pumpNumb = pumpNumb;
        semaphore = new Semaphore(pumpNumb);
        rand = new Random();
    }

    public synchronized void arrived( Threads customer ){
        System.out.println(customer.clientName+" Arrived");
        Main.gui.setColorBlue(0,customer.clientName+" Arrived");
    }

    public synchronized void served( Threads customer ){
        System.out.println(customer.clientName+" is Being Served");
        Main.gui.setColorYellow(0, customer.clientName+" is Being Served");
        try {
            customer.sleep(rand.nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void paying( Threads customer ){
        System.out.println(customer.clientName+" is paying");
        Main.gui.setColorGreen(1,customer.clientName+" is paying");
        try {
            customer.sleep(rand.nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void leaving( Threads customer ){
        System.out.println(customer.clientName+" is leaving");
        Main.gui.setColorRed(1,customer.clientName+" is leaving");
        semaphore.release();
    }

}
