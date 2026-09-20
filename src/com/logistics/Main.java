package com.logistics;

import com.logistics.app.DeliveryApplication;
import com.logistics.transport.Logistics;
import com.logistics.transport.RoadLogistics;
import com.logistics.transport.SeaLogistics;
import com.logistics.ui.GUIFactory;
import com.logistics.ui.MacOSFactory;
import com.logistics.ui.WindowsFactory;

import java.util.Scanner;

public class Main {

    private static final String CARGO = "laboratory equipment";
    private static final String DESTINATION = "Aktau warehouse";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String deliveryMode = promptChoice(scanner,
                "Delivery mode (ROAD/SEA): ",
                "ROAD", "SEA");
        if (deliveryMode == null) {
            System.out.println("No input provided. Exiting.");
            scanner.close();
            return;
        }

        String platform = promptChoice(scanner,
                "UI platform (WINDOWS/MACOS): ",
                "WINDOWS", "MACOS");
        if (platform == null) {
            System.out.println("No input provided. Exiting.");
            scanner.close();
            return;
        }

        Logistics logistics = createLogistics(deliveryMode);
        GUIFactory guiFactory = createGuiFactory(platform);

        DeliveryApplication app = new DeliveryApplication(guiFactory, logistics);
        app.run(CARGO, DESTINATION);

        scanner.close();
    }

    private static String promptChoice(Scanner scanner, String prompt, String... valid) {
        while (true) {
            System.out.print(prompt);

            if (!scanner.hasNextLine()) {
                return null;
            }

            String input = scanner.nextLine().trim().toUpperCase();

            if (input.isEmpty()) {
                System.out.println("Input is required. Please try again.");
                continue;
            }

            for (String option : valid) {
                if (option.equals(input)) {
                    return input;
                }
            }

            System.out.println("Unsupported choice: '" + input
                    + "'. Valid options: " + String.join(", ", valid));
        }
    }

    private static Logistics createLogistics(String mode) {
        return switch (mode) {
            case "ROAD" -> new RoadLogistics();
            case "SEA" -> new SeaLogistics();
            default -> throw new IllegalArgumentException("Unknown mode: " + mode);
        };
    }

    private static GUIFactory createGuiFactory(String platform) {
        return switch (platform) {
            case "WINDOWS" -> new WindowsFactory();
            case "MACOS" -> new MacOSFactory();
            default -> throw new IllegalArgumentException("Unknown platform: " + platform);
        };
    }
}