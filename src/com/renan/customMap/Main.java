package com.renan.customMap;

import java.util.Scanner;

public class Main {
    private final static CustomMap<String, String> map = new CustomMap<>();

    public static void main(String[] args) {
        int option = -1;
        Scanner in = new Scanner(System.in);

        do {
            try {
                printMainMenu();
                option = Integer.parseInt(in.nextLine());
                callAction(option);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida");
            }
        } while (option != 0);
    }

    private static void printMainMenu() {
        System.out.println("Escolha uma opção abaixo: ");
        System.out.println("1 - adicionar item");
        System.out.println("2 - buscar item");
        System.out.println("3 - remover item");
        System.out.println("0 - Sair");
    }

    private static void callAction(int actionCode) {
        switch (actionCode) {
            case 1:
                System.out.println("Adição");
                break;
            case 2:
                System.out.println("Busca");
                break;
            case 3:
                System.out.println("Remoção");
                break;
            case 0:
                System.out.println("Até mais!");
                break;
        }
    }
}
