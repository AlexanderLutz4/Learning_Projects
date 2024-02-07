import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

    public class Launcher {
        public static String path;
        public static File file;
        public static boolean game = true;

        public static void setPath(String path) { Launcher.path = path; }
        public static void setFile(File file) { Launcher.file = file; }
        public static void setGame(boolean game) { Launcher.game = game; }

        public static void main(String[] args) throws FileNotFoundException {
            setPath("russian_nouns.txt");
            setFile(new File(path));
            Scanner scanner = new Scanner(file);

            while(game != false) {
                Scanner console = new Scanner(System.in);
                System.out.println(Phrases.START_GAME);

                String symbol = console.next();
                symbol = symbol.toLowerCase();

                if(symbol.equals("д")) {
                    String word = choose(file).toUpperCase();
                    StringBuilder wordWithMask = new StringBuilder();
                    mask(wordWithMask, word);
                    int counter = 6;

                    while(counter > 0) {
                        System.out.println(wordWithMask);
                        System.out.println(Phrases.ATTEMPTS_LEFT + counter);
                        if (word.contains(wordWithMask)) {
                            System.out.println(Phrases.VICTORY);
                            counter = 0;
                        } else {

                            System.out.println(Phrases.ENTER_LETTER);
                            String answer = console.next();
                            answer = answer.toUpperCase();
                            if(word.contains(answer)) {
                                for (int i = 0; i < wordWithMask.length(); i++) {
                                    if (String.valueOf(word.charAt(i)).contains(answer)){
                                        wordWithMask = wordWithMask.deleteCharAt(i);
                                        wordWithMask = wordWithMask.insert(i, answer);
                                    }
                                }
                            } else {
                                counter--;
                            }
                            switch (counter) {
                                case 5:
                                    System.out.println(HagmansImages.COUNTER_5 + "\n" + wordWithMask);
                                    continue;
                                case 4:
                                    System.out.println(HagmansImages.COUNTER_4);
                                    continue;
                                case 3:
                                    System.out.println(HagmansImages.COUNTER_3);
                                    continue;
                                case 2:
                                    System.out.println(HagmansImages.COUNTER_2);
                                    continue;
                                case 1:
                                    System.out.println(HagmansImages.COUNTER_1);
                                    continue;
                                case 0:
                                    System.out.println(HagmansImages.COUNTER_0);
                                    System.out.println(Phrases.LOSE + word);
                            }
                        }

                    }
                } else if (symbol.equals("н")) {
                    System.out.println(Phrases.END_GAME);
                    setGame(false);
                } else {
                    System.out.println(Phrases.WRONG_SYMBOL);
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
