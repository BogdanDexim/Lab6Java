/**
 * Клас, що представляє кухонний прилад.
 */
public class KitchenAppliance extends Appliance {
    /**
     * Конструктор для створення кухонного приладу.
     *
     * @param name  назва кухонного приладу
     * @param power потужність кухонного приладу у ватах
     */
    public KitchenAppliance(String name, int power) {
        super(name, power);
    }
}
