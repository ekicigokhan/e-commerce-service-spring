import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random(); // Random sınıfından yararlanmak için bir instance oluşturdum.
        List<Integer> randomNumbers = new ArrayList<>(); //Diziyi List ile tutmaya karar verdim.
        for (int i=1; i<=100 ; i++){ // 0'dan 100'e kadar biribirnden farklı 100 sayı oluşturdum.
            int randomNumber = random.nextInt(101);
            if (!randomNumbers.contains(randomNumber)){
                randomNumbers.add(randomNumber);
            } else {
                i--;
            }
        }
        List<Integer> copyRandomNumbers = new ArrayList<>(randomNumbers); // Mevcut diziyi referansını almadan bağımsız kopyaladım.
        int randomNumber = (int)(Math.random() * 100); // Bu sefer Math sınıfı ile bir rastgele sayı oluşturdum.

        for (int n : copyRandomNumbers) {
            if (n == randomNumber){
                System.out.println("Generated random number : " + randomNumber);
                System.out.println("Number of items in the copied list : " + copyRandomNumbers.size());
                copyRandomNumbers.remove(Integer.valueOf(randomNumber));
                System.out.println("Number of items in the original list : " + randomNumbers.size());
                System.out.println("Number of items in the copied list after removed: " + copyRandomNumbers.size());
                break;
            } //Gerekli kontrolleri sağlayarak işlemi takip ettim
        }

        int notExistNumber = getNotExistNumber(randomNumbers,copyRandomNumbers); // Geriye int tipinde aradığım değeri dönen bir metod yazdım.
        if (notExistNumber != -1) {
            System.out.println("Searched number : " + notExistNumber);
        } else {
            System.out.println("All numbers matched. !");
        }
    }
    static int getNotExistNumber(List<Integer> originalList, List<Integer> copyList) {
        for (int number : originalList){
            if (!copyList.contains(number)){
                return number;
            }
        }
        return -1;
    }
}