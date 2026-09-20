package com.logistics.transport;

public class Truck implements Transport {
    @Override
    public void deliver(String cargo, String destination) {
        System.out.println("truck delivers " + cargo + " to " + destination + " by road");
    }
}
