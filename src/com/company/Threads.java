package com.company;

public class Threads extends Thread {

    String clientName="";
    PetrolStation petrolStation;

    public Threads ( String name, PetrolStation petrolStation ){
        clientName = name;
        this.petrolStation = petrolStation;
    }

    @Override
    public void run(){

        petrolStation.arrived(this);
        petrolStation.semaphore.acquire(clientName, this);
        petrolStation.served(this);
        petrolStation.paying(this);
        petrolStation.leaving(this);
        petrolStation.semaphore.release();

    }


}
