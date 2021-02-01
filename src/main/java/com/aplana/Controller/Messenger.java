package com.aplana.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Messenger{

    private static void messageForClient() {
        System.out.println(
                "Введите команду для генератора" + "\n" +
                "1 - Массовая генерация абонов" + "\n" +
                "2 - Одиночная генерация абона" + "\n" +
                "3 - Получение вакантных симок из билинга" + "\n" +
                "4 - Текущий статус по выполняемой операции на генераторе" + "\n" +
                "5 - Остановка всех операций на генераторе" + "\n" +
                "6 - Получение набора ключей из Redis perf-ytank1" + "\n" +
                "7 - Выход"
        );
    }

    static String inputMessage() {
        String command = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            command = bufferedReader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return command;
    }

   public static String messageAmountUsers() {
        System.out.println("Введите количество абонентов");
        String amountUsers = inputMessage();
        return amountUsers;
    }

    public static String messageNameKeyRedis() {
        System.out.println("Введите имя ключа в Redis");
        String nameKeyRedis = inputMessage();
        return nameKeyRedis;
    }

    public static String messageAmountSimCards() {
        System.out.println("Введите количество вакантных симок");
        return inputMessage();
    }

    public static String firstMessage() {
        messageForClient();
        return inputMessage();
    }
}
