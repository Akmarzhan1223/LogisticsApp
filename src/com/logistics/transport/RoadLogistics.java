package com.logistics.transport;

public class RoadLogistics extends Logistics {

    @Override
    public Transport createtransport() {
        return new Truck();
    }
}