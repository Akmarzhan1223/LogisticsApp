package com.logistics.app;
import com.logistics.transport.Logistics;
import com.logistics.ui.Button;
import com.logistics.ui.Checkbox;
import com.logistics.ui.GUIFactory;

public class DeliveryApplication {
    private final GUIFactory guiFactory;
    private final Logistics logistics;

    public DeliveryApplication(GUIFactory guifactory, Logistics logistics) {
        this.guiFactory = guifactory;
        this.logistics = logistics;
    }
    public void run(String cargo, String destination) {
        Button button = guiFactory.createButton();
        Checkbox checkbox = guiFactory.createCheckbox();
        button.paint();
        checkbox.paint();
        logistics.planDelivery(cargo, destination);
    }

}
