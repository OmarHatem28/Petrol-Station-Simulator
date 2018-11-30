package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class Semaphore {

    private int counter;
    private Queue<Threads> queue = new LinkedList<>();

    public Semaphore( int permits ){
        counter = permits;
    }

    public void acquire(String name, Threads obj){
        counter--;

        if ( counter < 0  ){
            System.out.println(name + " Arrived and waiting");

            try {
                obj.join();
                queue.add(obj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void release(){
        counter++;

        if ( !queue.isEmpty() ){
            Threads customer = queue.remove();

            customer.interrupt();
        }

    }

}
