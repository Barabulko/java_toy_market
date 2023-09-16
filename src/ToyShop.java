import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class ToyShop {
    private List<Toy> toys;
    private File file;

    public ToyShop(File file) {
        this.file = file;
        toys = new ArrayList<>();
        loadToysFromFile();
    }

    public void addNewToy(int id, String name, int quantity, int frequency) {
        toys.add(new Toy(id, name, quantity, frequency));
    }

    public void changeToyFrequency(int toyId, int newFrequency) {
        Toy toy = findToyById(toyId);
        if (toy != null) {
            toy.setFrequency(newFrequency);
        } else {
            System.out.println("Игрушка с указанным ID не найдена");
        }
    }

    public void startToyGiveaway() {
        int totalFrequency = 0;
        for (Toy toy : toys) {
            totalFrequency += toy.getFrequency();
        }

        Random random = new Random();
        int randomNum = random.nextInt(totalFrequency);

        int count = 0;
        for (Toy toy : toys) {
            count += toy.getFrequency();
            if (randomNum < count) {
                if (toy.getQuantity() > 0) {
                    System.out.println("Выиграна игрушка: " + toy.getName());
                    toy.setQuantity(toy.getQuantity() - 1);
                } else {
                    System.out.println("К сожалению, игрушка "+ toy.getName() + " закончилась");
                }
                break;
            }
        }
    }

    private Toy findToyById(int toyId) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                return toy;
            }
        }
        return null;
    }

    private void loadToysFromFile() {
        try {
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int quantity = Integer.parseInt(parts[2]);
                    int frequency = Integer.parseInt(parts[3]);
                    toys.add(new Toy(id, name, quantity, frequency));
                }
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToysToFile() {
        try {
            FileWriter writer = new FileWriter(file);
            for (Toy toy : toys) {
                writer.write(toy.getId() + "," + toy.getName() + "," + toy.getQuantity() + "," + toy.getFrequency() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}