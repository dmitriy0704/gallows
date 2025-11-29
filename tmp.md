# Альтернативный вариант

```java
package dev.folomkin;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String secretWord = "кот";  // Загаданное слово
        int wordLength = secretWord.length();

        // Массив для отслеживания угаданных букв (false = не угадана)

        boolean[] guessed = new boolean[wordLength];
        int guessedCount = 0;  // Сколько букв уже угадано
        System.out.println("Угадайте слово по буквам! Слово: " + getDisplayWord(secretWord, guessed));

        while (guessedCount < wordLength) {
            System.out.print("Введите букву: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Пожалуйста, введите букву.");
                continue;
            }

            char guessChar = input.charAt(0);  // Берём первую введённую букву

            boolean found = false;

            // Проходим по всем буквам слова
            for (int i = 0; i < wordLength; i++) {
                if (!guessed[i] && secretWord.charAt(i) == guessChar) {
                    guessed[i] = true;
                    guessedCount++;
                    found = true;
                }
            }

            if (found) {
                System.out.println("Есть такая буква!");
            } else {
                System.out.println("Такой буквы нет.");
            }

            // Выводим текущее состояние слова
            System.out.println("Слово: " + getDisplayWord(secretWord, guessed));
        }

        System.out.println("Поздравляю! Вы угадали слово: " + secretWord);
        scanner.close();
    }


    private static String getDisplayWord(String word, boolean[] guessed) {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (guessed[i]) {
                display.append(word.charAt(i));
            } else {
                display.append("_");
            }
        }
        return display.toString();
    }
}
```