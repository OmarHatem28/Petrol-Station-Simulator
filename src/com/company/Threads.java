package com.company;

public class Threads extends Thread {

    String clientName="";
    PetrolStation petrolStation;
    private int index;

    public Threads ( String name, PetrolStation petrolStation ){
        clientName = name;
        this.petrolStation = petrolStation;
    }

    @Override
    public void run(){

        petrolStation.arrived(this);
        petrolStation.semaphore.acquire(clientName, this);
        index = petrolStation.occupyPump(this);
        petrolStation.served(this,index);
        petrolStation.paying(this,index);
        petrolStation.leaving(this,index);
        petrolStation.semaphore.release();

    }


}
