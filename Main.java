import java.util.List;

public class Main {
    public static void main(String[] args) {
        Apartment apartment = new Apartment();

        // Додавання приладів
        apartment.addAppliance(new KitchenAppliance("Мікрохвильовка", 800));
        apartment.addAppliance(new CleaningAppliance("Пилосос", 1400));
        apartment.addAppliance(new EntertainmentAppliance("Телевізор", 200));

        try {
            // Увімкнення приладів
            apartment.plugInAppliance("Мікрохвильовка");
            apartment.plugInAppliance("Телевізор");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // Обчислення загальної потужності
        System.out.println("Загальне споживання потужності: " + apartment.calculateTotalPower() + "Вт");


        // Пошук приладів у діапазоні
        System.out.println("\nПрилади у діапазоні 100-1000Вт:");
        List<Appliance> rangeAppliances = apartment.findByRadiationRange(100, 1000);
        rangeAppliances.forEach(System.out::println);
    }
}
