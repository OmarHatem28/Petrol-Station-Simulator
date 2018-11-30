package com.company;

public class Threads extends Thread {

    String customerName="";
    static Semaphore semaphore = new Semaphore(2);

    public Threads ( String name ){
        customerName = name;
    }

    @Override
    public void run(){

        arrived();
        semaphore.acquire(customerName, this);
        served();
        paying();
        leaving();

    }

    public synchronized void arrived(){
        System.out.println(customerName+" Arrived");
    }

    public synchronized void served(){
        System.out.println(customerName+" is Being Served");
    }

    public synchronized void paying(){
        System.out.println(customerName+" is paying");
    }

    public synchronized void leaving(){
        System.out.println(customerName+" is leaving");
        semaphore.release();
    }
}
