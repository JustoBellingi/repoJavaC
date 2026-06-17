package com.techlab.util;

import java.util.Scanner;

public class InputUtil {
    public static Scanner sc = new Scanner(System.in);

    public static int leerInt(String msg) {
        System.out.print(msg);
        return sc.nextInt();
    }

    public static double leerDouble(String msg) {
        System.out.print(msg);
        return sc.nextDouble();
    }

    public static String leerString(String msg) {
        System.out.print(msg);
        sc.nextLine();
        return sc.nextLine();
    }
}