/**
 * Абстрактний клас, що представляє електричний прилад.
 */
public abstract class Appliance {
    private String name;
    private int power; // Потужність у ватах
    private boolean isPluggedIn;
    /**
     * Конструктор для створення приладу.
     *
     * @param name  назва приладу
     * @param power потужність приладу в ватах
     * @throws IllegalArgumentException якщо потужність є від'ємною
     */
    public Appliance(String name, int power) {
        if (power < 0) throw new IllegalArgumentException("Потужність не може бути від'ємною");
        this.name = name;
        this.power = power;
        this.isPluggedIn = false;
    }
    /**
     * Повертає назву приладу.
     *
     * @return назва приладу
     */
    public String getName() {
        return name;
    }
    /**
     * Повертає потужність приладу.
     *
     * @return потужність у ватах
     */
    public int getPower() {
        return power;
    }
    /**
     * Повертає статус, чи підключено прилад до розетки.
     *
     * @return true, якщо прилад підключений, інакше false
     */
    public boolean isPluggedIn() {
        return isPluggedIn;
    }
    /**
     * Підключає прилад до розетки.
     *
     * @throws IllegalStateException якщо прилад вже підключений
     */
    public void plugIn() {
        if (isPluggedIn) throw new IllegalStateException(name + " вже підключено");
        isPluggedIn = true;
    }
    /**
     * Відключає прилад від розетки.
     */
    public void unplug() {
        isPluggedIn = false;
    }
    /**
     * Повертає текстове представлення приладу.
     *
     * @return текстове представлення приладу
     */
    @Override
    public String toString() {
        return String.format("Прилад{name='%s', power=%dW, pluggedIn=%b}", name, power, isPluggedIn);
    }
}