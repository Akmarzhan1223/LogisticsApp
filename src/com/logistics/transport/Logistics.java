package com.logistics.transport;

public abstract class Logistics {
    public abstract Transport createtransport();
    public void planDelivery(String cargo, String destination) {
        Transport transport = createtransport();
        transport.deliver(cargo, destination);
    }
}
