package com.renan.customMap;

import java.util.Map;
import java.util.Scanner;

public class Main {
    private final static Map<String, String> map = new CustomMap<>();

    public static void main(String[] args) {
        int option = -1;
        Scanner in = new Scanner(System.in);

        do {
            try {
                printMainMenu();
                option = Integer.parseInt(in.nextLine());
                callAction(option, in);
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
        System.out.println("4 - imprimir mapa");
        System.out.println("0 - Sair");
    }

    private static void callAction(int actionCode, Scanner in) {
        String chave;
        String valor;

        switch (actionCode) {
            case 1:
                System.out.println("Informe a chave do valor a ser adicionado:");
                chave = in.nextLine();
                System.out.println("Informe o valor:");
                valor = in.nextLine();
                map.put(chave, valor);
                break;
            case 2:
                System.out.println("Informe a chave do valor a ser buscado:");
                chave = in.nextLine();
                System.out.println(map.get(chave));
                break;
            case 3:
                System.out.println("Informe a chave do valor a ser removido:");
                chave = in.nextLine();
                System.out.println("Valor removido: " + map.remove(chave));
                break;
            case 4:
                System.out.println(map.toString());
                break;
            case 0:
                System.out.println("Até mais!");
                break;
        }
    }
}
