import java.util.*;
/**
 * Клас, що представляє квартиру з електроприладами.
 */
public class Apartment {
    private MyLinkedList<Appliance> appliances;

    /**
     * Порожній конструктор для створення об'єкта Apartment.
     */
    public Apartment() {
        this.appliances = new MyLinkedList<>();
    }

    /**
     * Конструктор, що створює Apartment з одного приладу.
     *
     * @param appliance прилад для додавання
     */
    public Apartment(Appliance appliance) {
        this.appliances = new MyLinkedList<>(appliance);
    }

    /**
     * Конструктор, що створює Apartment з наданої колекції приладів.
     *
     * @param appliances колекція приладів
     */
    public Apartment(Collection<? extends Appliance> appliances) {
        this.appliances = new MyLinkedList<>(appliances);
    }

    /**
     * Додає прилад до списку приладів у квартирі.
     *
     * @param appliance прилад для додавання
     */
    public void addAppliance(Appliance appliance) {
        appliances.add(appliance);
    }
    /**
     * Увімкнення приладу в розетку за його назвою.
     *
     * @param name назва приладу
     * @throws NoSuchElementException якщо прилад з вказаною назвою не знайдено
     * @throws IllegalStateException якщо прилад вже увімкнений
     */
    public void plugInAppliance(String name) {
        if (appliances != null && !appliances.isEmpty()) {
            appliances.stream()
                    .filter(a -> a.getName().equalsIgnoreCase(name))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Прилад не знайдено: " + name))
                    .plugIn();
        } else {
            System.out.println("Список приладів порожній.");
        }
    }

    /**
     * Обчислює загальну споживану потужність увімкнених приладів.
     *
     * @return загальна потужність у ватах
     */
    public int calculateTotalPower() {
        return appliances.stream()
                .filter(Appliance::isPluggedIn)
                .mapToInt(Appliance::getPower)
                .sum();
    }

    /**
     * Сортує список приладів за потужністю.
     */
    public void sortByPower() {
        if (appliances != null && !appliances.isEmpty()) {
            appliances.sort(Comparator.comparingInt(Appliance::getPower));
        } else {
            System.out.println("Список приладів порожній або не ініціалізований.");
        }
    }

    /**
     * Знаходить прилади за вказаним діапазоном потужності.
     *
     * @param minRange мінімальна потужність
     * @param maxRange максимальна потужність
     * @return список приладів у вказаному діапазоні
     */
    public List<Appliance> findByRadiationRange(int minRange, int maxRange) {
        List<Appliance> result = new ArrayList<>();
        for (Appliance appliance : appliances) {
            if (appliance.getPower() >= minRange && appliance.getPower() <= maxRange) {
                result.add(appliance);
            }
        }
        return result;
    }

    /**
     * Виводить список всіх приладів у квартирі.
     */
    public void printAppliances() {
        appliances.forEach(System.out::println);
    }

    /**
     * Перевіряє, чи є прилади в квартирі.
     *
     * @return true, якщо список порожній
     */
    public boolean isEmpty() {
        return appliances.isEmpty();
    }

    /**
     * Повертає кількість приладів у квартирі.
     *
     * @return кількість приладів
     */
    public int size() {
        return appliances.size();
    }

    /**
     * Видаляє прилад з квартири за його назвою.
     *
     * @param name назва приладу
     * @return true, якщо прилад було видалено
     */
    public boolean removeApplianceByName(String name) {
        return appliances.removeIf(a -> a.getName().equalsIgnoreCase(name));
    }
}