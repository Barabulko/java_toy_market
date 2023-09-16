import java.io.File;

public class ToyShopDemo {
    public static void main(String[] args) {
        File file = new File("toys.txt");
        ToyShop toyShop = new ToyShop(file);
        if (file.length()==0) {
            toyShop.addNewToy(1, "Мяч", 10, 20);
            toyShop.addNewToy(2, "Кукла", 5, 30);
            toyShop.addNewToy(3, "Солдатики", 8, 25);
            toyShop.addNewToy(4, "Паззлы", 12, 25);
        }

        toyShop.changeToyFrequency(1, 15);

        toyShop.startToyGiveaway();

        toyShop.saveToysToFile();
    }
}