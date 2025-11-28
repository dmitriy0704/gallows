package dev.folomkin;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static final String[] HANGMAN_IMAGES = {
            """
          _______
         |/      |
         |
         |
         |
         |
        _|___
        """,
            """
          _______
         |/      |
         |      (_)
         |
         |
         |
        _|___
        """,
            """
          _______
         |/      |
         |      (_)
         |       |
         |       |
         |
        _|___
        """,
            """
          _______
         |/      |
         |      (_)
         |      \\|
         |       |
         |
        _|___
        """,
            """
          _______
         |/      |
         |      (_)
         |      \\|/
         |       |
         |
        _|___
        """,
            """
          _______
         |/      |
         |      (_)
         |      \\|/
         |       |
         |      /
        _|___
        """,
            """
          _______
         |/      |
         |      (_)
         |      \\|/
         |       |
         |      / \\
        _|___
        """,
            """
          _______
         |/      |
         |      (_)
         |     --|--
         |       |
         |      / \\
        _|___
        """,
            """
          _______
         |/      |
         |      (_)
         |     --|--
         |       |
         |    _/ \\
        _|___
        """,
            """
          _______
         |/      |
         |      (_)
         |     --|--
         |       |
         |    _/ \\_
        _|___  RIP
        """
    };


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String word = getWord();
        int maxAttempts = 10;
        int attempts = 0;

        // Массив для отображения текущего состояния слова (скрытые буквы — '_')
        char[] guessedWord = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            guessedWord[i] = '_';
        }

        // Набор для хранения уже введённых букв
        Set<Character> usedLetters = new HashSet<>();

        System.out.println("Добро пожаловать в Виселицу!");
        System.out.println("У вас есть " + maxAttempts + " попыток.");

        // Основной игровой цикл
        while (attempts < maxAttempts) {
            // Выводим текущую графику виселицы
            System.out.println(HANGMAN_IMAGES[attempts]);

            // Выводим текущее состояние слова
            System.out.print("Слово: ");
            for (char c : guessedWord) {
                System.out.print(c + " ");
            }
            System.out.println();

            // Выводим уже использованные буквы
            if (!usedLetters.isEmpty()) {
                System.out.print("Использованные буквы: ");
                for (char letter : usedLetters) {
                    System.out.print(letter + " ");
                }
                System.out.println();
            }

            System.out.println("Попыток осталось: " + (maxAttempts - attempts));

            System.out.print("Введите букву: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() != 1) {
                System.out.println("Пожалуйста, введите одну букву.");
                continue;
            }

            char guess = input.charAt(0);

            // Проверка на повторную букву
            if (usedLetters.contains(guess)) {
                System.out.println("Вы уже называли эту букву! Попробуйте другую.");
                continue;
            }

            // Добавляем букву в набор использованных
            usedLetters.add(guess);

            boolean found = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    guessedWord[i] = guess;  // Открываем букву
                    found = true;
                }
            }

            if (found) {
                System.out.println("Есть такая буква!");
            } else {
                attempts++;
                System.out.println("Нет такой буквы. Осталось попыток: " + (maxAttempts - attempts));
            }

            // Проверяем, угадано ли слово целиком
            if (new String(guessedWord).equals(word)) {
                System.out.println("Поздравляю! Вы угадали слово: " + word);
                System.out.println(HANGMAN_IMAGES[attempts]);  // финальная картинка
                break;
            }
        }

        // Если попытки исчерпаны
        if (attempts >= maxAttempts) {
            System.out.println("Вы проиграли! Загаданное слово: " + word);
            System.out.println(HANGMAN_IMAGES[maxAttempts - 1]);  // последняя картинка
        }
        scanner.close();
    }

    private static String getWord() {
        Random random = new Random();
        String[] wordsList = {
                "программирование",
                "эффективность",
                "калейдоскоп",
                "барсучонок",
                "апельсиновый",
                "заколдованный",
                "термометр",
                "водопад",
                "бабочка",
                "кристалл",
                "фантазия",
                "радуга"
        };

        var wordIndex = random.nextInt(wordsList.length);
        return wordsList[wordIndex].toLowerCase();
    }
}