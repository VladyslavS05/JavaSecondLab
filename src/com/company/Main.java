package com.company;
import java.util.Scanner;
import java.io.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.print("\nВиберіть завдання \n1. В рядку порахувати кількість різних слів, що входять до заданого тексту, і вивести на екран кількість використаних символів." +
                    "\n2. Дано файл, який містить текст, що складається з окремих речень і в ньому використовуються розділові знаки <.>, <?>, <!>. Порахувати кількість речень в даному тексті. " +
                    "\n3. З файлу вивести на екран слова, що мають найменшу кількість літер, і записати їх в новий файл. \n\nВаш вибір: ");

            int chooseTask = scanner.nextInt();

            switch (chooseTask) {
                case 1 -> TaskOne();
                case 2 -> TaskTwo();
                case 3 -> TaskThree();
                default -> System.out.println("Ви обрали неправильне завдання!");
            }
        }
    }

    public static void TaskOne() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введіть текст: ");

        String inputString = sc.nextLine();

        WordCount(inputString);
        SymbolCount(inputString);
    }

    public static void WordCount(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println("Виявити кількість слів неможливо, оскільки рядок порожній!");
            return;
        } else if (text.length() == 1) {
            System.out.println("Виявити кількість слів неможливо, оскільки ви ввели тільки 1 символ!");
            return;
        }

        int wordCount = 0;

        boolean isWord = false;
        int endOfLine = text.length() - 1;
        char[] characters = text.toCharArray();

        for (int i = 0; i < characters.length; i++) {

            if (Character.isLetter(characters[i]) && i != endOfLine) {
                isWord = true;

            } else if (!Character.isLetter(characters[i]) && isWord) {
                wordCount++;
                isWord = false;

            } else if (Character.isLetter(characters[i]) && i == endOfLine) {
                wordCount++;
            }
        }

        System.out.println("Кількість слів, які ви ввели: " + wordCount);
    }

    public static void SymbolCount(String text) {
        if (text == null || text.isEmpty()) {
            System.out.println("Виявити кількість символів неможливо, оскільки рядок порожній!");
            return;
        }
        int symbolCount = 0;

        for (int i = 0; i < text.length(); i++) {
            symbolCount++;
        }

        System.out.println("Кількість використаних символів: " + symbolCount);
    }

    public static void TaskTwo() throws IOException {
        File file = new File("C:\\Users\\vland\\Desktop\\Education\\Java\\Lab2.txt");
        FileInputStream fileStream = new FileInputStream(file);
        InputStreamReader input = new InputStreamReader(fileStream);
        BufferedReader reader = new BufferedReader(input);

        String line;
        int countWord = 0;
        int sentenceCount = 0;
        int characterCount = 0;

        while ((line = reader.readLine()) != null) {
            characterCount += line.length();

            String[] wordList = line.split("\\s+");

            countWord += wordList.length;

            String[] sentenceList = line.split("[!?.:]+");

            sentenceCount += sentenceList.length;
        }
        reader.close();

        System.out.println("Кількість слів: " + countWord);
        System.out.println("Кількість речень: " + sentenceCount);
        System.out.println("Кількість символів: " + characterCount);
    }

    public static void TaskThree() throws IOException {
        File file = new File("C:\\Users\\vland\\Desktop\\Education\\Java\\input.txt");
        Scanner scanner = new Scanner(file);
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader input = new InputStreamReader(fileInputStream);

        File fileOutput = new File("C:\\Users\\vland\\Desktop\\Education\\Java\\output.txt");
        OutputStream out = new FileOutputStream(fileOutput);

        String smallestWord = "";
        int minLength = Integer.MAX_VALUE;

        System.out.print("Слова з найменшою кількістю букв: ");

        while (scanner.hasNextLine()) {
            Scanner wordScanner = new Scanner(scanner.nextLine());
            while (wordScanner.hasNext()) {
                String str = wordScanner.next();
                String[] arr = str.split(" ");

                for(int i = 0; i < arr.length; i++){

                    if(arr[i].length() <= minLength) {
                        smallestWord = arr[i];
                        minLength = arr[i].length();
                        System.out.print(smallestWord + " ");

                        StringBuilder sb = new StringBuilder();

                        for(String s : arr){
                            sb.append(s).append(System.lineSeparator());
                        }
                        byte[] b = sb.toString().getBytes();
                        out.write(b);
                    }
                }
            }
        }
        out.close();
        System.out.println("\n");
        System.out.println("Інформація додана в новий файл output.txt");
    }
}