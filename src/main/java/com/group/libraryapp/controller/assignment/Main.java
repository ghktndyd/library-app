package com.group.libraryapp.controller.assignment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.print("숫자를 입력하세요: ");
        Scanner scanner = new Scanner(System.in);
        int repeatCount = scanner.nextInt();

        Dice dice = new Dice();

        int[] results = dice.rollDice(repeatCount);
        dice.printResults(results);
    }
}

class Dice {
    private final int diceFace = 6; // 주사위면이 6개라고 가정 만약 더 커진다면 변경
    private final int[] results = new int[diceFace];

    public int[] rollDice(int repeatCount) {
        for (int i = 0; i < repeatCount; i++) {
            int roll = (int)(Math.random() * diceFace); // 주사위면만큼만 나올 수 있도록
            results[roll]++; // 생성된 난수에 해당하는 주사위 눈금의 카운트 증가
        }
        return results;
    }

    public void printResults(int[] results) {
        for (int i = 0; i < results.length; i++) {
            System.out.printf("%d는 %d번 나왔습니다. \n", i + 1, results[i]);
        }
    }
}
