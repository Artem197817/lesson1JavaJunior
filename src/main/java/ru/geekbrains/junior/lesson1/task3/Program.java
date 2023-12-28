package ru.geekbrains.junior.lesson1.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Program {
    /**
     *Напишите программу, которая использует Stream API для обработки списка чисел.
     * Программа должна вывести на экран среднее значение всех четных чисел в списке.
     */
    public static void main(String[] args) {

        List<Integer> integerList= new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20 ; i++)
            integerList.add(random.nextInt(0,100));

        int average = (int) integerList.stream()
                .filter(number->number%2 == 0)
                .mapToInt(Integer::intValue)
                .summaryStatistics()
                .getAverage();

        System.out.println("Среднее арифметическое четных чисел - " + average);
    }
}
