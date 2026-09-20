# Logistics Application — Factory Method & Abstract Factory

## Purpose

A Java console application demonstrating two design patterns working together:

- **Factory Method** — creates transport objects (`Truck`, `Ship`) through creator subclasses.
- **Abstract Factory** — creates matching UI component families (`Windows`, `macOS`) with `Button` and `Checkbox`.

The user chooses a delivery mode and a UI platform at runtime. Both patterns cooperate in a single program.

## Prerequisites

- **JDK 17** (required by the assignment)
- Terminal or IntelliJ IDEA

## Package Structure

```
com.logistics
├── Main.java                       — startup, input validation, runtime selection
├── transport/                      — Part A: Factory Method
│   ├── Transport.java              — product interface
│   ├── Truck.java                  — concrete product (road)
│   ├── Ship.java                   — concrete product (sea)
│   ├── Logistics.java              — abstract creator (factory method + planDelivery)
│   ├── RoadLogistics.java          — concrete creator (creates Truck)
│   └── SeaLogistics.java           — concrete creator (creates Ship)
├── ui/                             — Part B: Abstract Factory
│   ├── Button.java                 — abstract product
│   ├── Checkbox.java               — abstract product
│   ├── WindowsButton.java          — concrete product (Windows family)
│   ├── WindowsCheckbox.java        — concrete product (Windows family)
│   ├── MacOSButton.java            — concrete product (macOS family)
│   ├── MacOSCheckbox.java          — concrete product (macOS family)
│   ├── GUIFactory.java             — abstract factory
│   ├── WindowsFactory.java         — concrete factory (Windows family)
│   └── MacOSFactory.java           — concrete factory (macOS family)
└── app/
    └── DeliveryApplication.java    — client, uses both patterns via abstractions
```

## Build & Run

### Option 1 — Terminal (macOS / Linux)

```bash
cd LogisticsApp
javac -d out $(find src -name "*.java")
java -cp out com.logistics.Main
```

### Option 2 — Terminal (Windows PowerShell)

```powershell
cd LogisticsApp
javac -d out (Get-ChildItem -Recurse -Filter *.java | % { $_.FullName })
java -cp out com.logistics.Main
```

### Option 3 — IntelliJ IDEA

1. Open the project in IntelliJ IDEA.
2. Make sure **Project SDK** is set to **JDK 17** (`File → Project Structure → Project`).
3. Open `src/com/logistics/Main.java`.
4. Click the green ▶ next to `public static void main`.
5. Run `Main.main()`.

## Supported Input

| Choice | Valid values | Notes |
|--------|-------------|-------|
| Delivery mode | `ROAD`, `SEA` | case-insensitive (e.g., `road`, `Sea` work too) |
| UI platform | `WINDOWS`, `MACOS` | case-insensitive |

**Invalid input** → the app prints a clear message and asks again.  
**Empty input** → the app prints "Input is required. Please try again." and asks again.  
The app never silently falls back to a default value.

## Sample Run

```
Delivery mode (ROAD/SEA): ROAD
UI platform (WINDOWS/MACOS): WINDOWS
Rendering Windows button
Rendering Windows checkbox
Truck delivers laboratory equipment to Aktau warehouse by road
```

## Design Overview

### Factory Method

`Logistics` declares `createTransport()` (abstract) and provides `planDelivery(cargo, destination)` (shared workflow). Subclasses `RoadLogistics` and `SeaLogistics` override `createTransport()` to return `Truck` and `Ship` respectively. The workflow calls the factory method — never `new Truck()` / `new Ship()` directly.

### Abstract Factory

`GUIFactory` declares `createButton()` and `createCheckbox()`. `WindowsFactory` produces a matching Windows pair; `MacOSFactory` produces a matching macOS pair. `DeliveryApplication` receives a `GUIFactory` and a `Logistics` through its constructor, so it depends only on interfaces and never on concrete classes.


## References

- Freeman, E., Robson, E. *Head First Design Patterns*. Chapter 4.
- Martin, R. C. *Clean Code*. Chapter 6: Objects and Data Structures.
- Course lecture notes: *Lecture 2 — Factory Method and Abstract Factory*.