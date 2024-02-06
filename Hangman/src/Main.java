import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final String victory = "Слово угадано!" + "\n"+ "Вы выиграли!";
    public static final String enterLetter = "Введите букву: ";
    public static final String lose = "Вы проиграли!" + "\n" + "Загаданное слово: ";
    public static int counter = 6;
    public static String path;
    public static File file;
    public static Scanner scanner;
    public static String word;
    public static StringBuilder wordWithMask;
    public static String answer;

    public static void setPath(String path) {
        Main.path = path;
    }
    public static void setFile(File file) {
        Main.file = file;
    }
    public static void setScanner(Scanner scanner) {
        Main.scanner = scanner;
    }
    public static void setWord(String word) { Main.word = word; }
    public static void setWordWithMask(StringBuilder wordWithMask) {
        Main.wordWithMask = wordWithMask;
    }

    public static void main(String[] args) throws FileNotFoundException {
        setPath("words.txt");
        setFile(new File(path));
        setScanner(new Scanner(file));
        setWord(choose(file));
        setWordWithMask(new StringBuilder());
        Scanner console = new Scanner(System.in);

        mask(wordWithMask, word);
        System.out.println(wordWithMask);

        while(counter > 0) {
            if (word.contains(wordWithMask)) {
                System.out.println(victory);
                break;
            } else {
                System.out.println(enterLetter);
                answer = console.next();
                answer = answer.toUpperCase();
                if(word.contains(answer)) {
                    for (int i = 0; i < wordWithMask.length(); i++) {
                        if (String.valueOf(word.charAt(i)).contains(answer)){
                            wordWithMask = wordWithMask.deleteCharAt(i);
                            wordWithMask = wordWithMask.insert(i, answer);
                        }
                    }
                    System.out.println(wordWithMask);
                } else {
                    Main.counter--;
                    System.out.println(counter);
                }
                switch (counter) {
                    case 5:
                        System.out.println(Hangmans.COUNTER_5 + "\n" + wordWithMask);
                        continue;
                    case 4:
                        System.out.println(Hangmans.COUNTER_4);
                        System.out.println(wordWithMask);
                        continue;
                    case 3:
                        System.out.println(Hangmans.COUNTER_3);
                        System.out.println(wordWithMask);
                        continue;
                    case 2:
                        System.out.println(Hangmans.COUNTER_2);
                        System.out.println(wordWithMask);
                        continue;
                    case 1:
                        System.out.println(Hangmans.COUNTER_1);
                        System.out.println(wordWithMask);
                        continue;
                    case 0:
                        System.out.println(Hangmans.COUNTER_0);
                        System.out.println(lose + word);
                }
            }

        }
        scanner.close();
    }
    public static String choose(File file) throws FileNotFoundException
    {
        String result = null;
        Random random = new Random();
        int n = 0;
        for(Scanner scanner = new Scanner(file);
            scanner.hasNext(); ) {
            ++n;
            String line = scanner.nextLine();
            if(random.nextInt(n) == 0)
                result = line;
        }
        return result;
    }
    public static StringBuilder mask(StringBuilder stringBuilder, String word) {
        for (int i = 0; i < word.length(); i++) {
            stringBuilder.append('*');
        }
        return stringBuilder;
    }
}
