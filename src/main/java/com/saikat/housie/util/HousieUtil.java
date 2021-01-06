package com.saikat.housie.util;

import java.util.*;

/**
 * @author Saikat Gupta
 * This utility class will help us generate random Housie Tickets and Random Housie numbers.
 */

public class HousieUtil {
    private static final int MIN_HOUSIE = 1;
    private static final int MAX_HOUSIE = 90;

    public static List<Integer> populateHousieList() {
        List<Integer> numberList = new ArrayList<>();
        for(int i = MIN_HOUSIE; i <= MAX_HOUSIE; i++) {
            numberList.add(i);
        }
        return numberList;
    }

    public static Integer[] generateRandomPosition() {
        List<Integer> indexList = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
        shuffleListFrequently(indexList, 10);
        return indexList.subList(0, 5).toArray(Integer[]::new);
    }

    public static void shuffleListFrequently(List<Integer> list, int frequency) {
        for (int occurrence = 1; occurrence <= frequency; occurrence++) {
            Collections.shuffle(list);
        }
    }

    public static List<List<Integer>> populateSubHousieRandomList(List<Integer> parent) {
        List<List<Integer>> subHousieRandomList = new ArrayList<>();
        shuffleListFrequently(parent, 10);
        for (int count = 0; count < 15; count+=5) {
            subHousieRandomList.add(parent.subList(count, count + 5));
        }
        return subHousieRandomList;
    }

    public static List<List<Integer>> generateHousieTicket() {
        Integer[][] housieTicket = new Integer[3][9];
        List<Integer> parent = populateHousieList();
        List<List<Integer>> subHousieRandomList = populateSubHousieRandomList(parent);
        List<List<Integer>> lstHousieTicket = new ArrayList<>();
        for(int row = 0; row < 3; row++) {
            Integer[] ticketPos = generateRandomPosition();
            for(int column = 0; column < 9; column++) {
                if(column < 5) {
                    housieTicket[row][ticketPos[column]] = subHousieRandomList.get(row).get(column);
                }
            }
            lstHousieTicket.add(Arrays.asList(housieTicket[row]));
        }
        return lstHousieTicket;
    }

    public static int shuffleAndPopUp(List<Integer> housieList) {
        shuffleListFrequently(housieList, 10);
        return housieList.remove(0);
    }

    public static void generateRandomHoisieNumbers() {
        System.out.println("==================================");
        System.out.println("|Generating random housie numbers|");
        System.out.println("==================================");
        List<Integer> housieList = populateHousieList();
        System.out.print("Generate next random number (Yy/[Except Y]? ");

        Scanner scanner = new Scanner(System.in);
        String response = scanner.next();

        while (response.equalsIgnoreCase("Y") && !housieList.isEmpty()) {
            System.out.println(shuffleAndPopUp(housieList));

            if(!housieList.isEmpty()) {
                System.out.print("Generate next random number (Yy/[Except Y]? ");
                response = new Scanner(System.in).next();
                if (!response.equalsIgnoreCase("Y")) {
                    System.out.print("Do you want to exit the process (Yy)? ");
                    String exit = scanner.next();
                    if (!exit.equalsIgnoreCase("Y")) {
                        System.out.print("Generate next random number (Yy/[Except Y]? ");
                        response = scanner.next();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter total no. of players: " );
        try {
            int totalPlayers = new Scanner(System.in).nextInt();

            for(int playerCount = 1; playerCount <= totalPlayers; playerCount++) {
                System.out.println();
                System.out.println("Ticket for player " + playerCount);
                System.out.println("====================");
                generateHousieTicket().forEach(System.out::println);
            }
        } catch (InputMismatchException e) {
            System.out.println("Input type mismatch. Expecting: int");
            System.out.println("Run again to generate the housie tickets.");
        }
        System.out.println();
        generateRandomHoisieNumbers();
    }
}
