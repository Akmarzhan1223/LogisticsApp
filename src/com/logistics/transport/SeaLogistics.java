package com.logistics.transport;

public class SeaLogistics extends Logistics {

    @Override
    public Transport createtransport() {
        return new Ship();
    }
}