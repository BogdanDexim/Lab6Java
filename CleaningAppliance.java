/**
 * Клас, що представляє прилад для прибирання.
 */
public class CleaningAppliance extends Appliance {
    /**
     * Конструктор для створення приладу для прибирання.
     *
     * @param name  назва приладу для прибирання
     * @param power потужність приладу для прибирання у ватах
     */
    public CleaningAppliance(String name, int power) {
        super(name, power);
    }
}
