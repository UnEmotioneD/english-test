package com.unemotioned.englishtest.exam.viewer;

import com.unemotioned.englishtest.common.vo.Word;
import java.util.ArrayList;
import java.util.Scanner;

public class ExamViewer {

    Scanner sc;

    public ExamViewer() {
        sc = new Scanner(System.in);
    }

    public String startTest() {
        System.out.print("Select to English or Korean (e/k): ");
        return sc.next();
    }

    public int random() {
        System.out.print("Please enter the number of tests : ");
        return sc.nextInt();
    }

    public String randomTest() {
        System.out.print("Answer : ");
        return sc.next();
    }

    public char editOrDelete() {
        return sc.next().charAt(0);
    }

    public int searchView(ArrayList<Word> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%2d %-13s\t", i + 1, list.get(i).getWord());
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }
        System.out.print("\nChoose index : ");
        return sc.nextInt();
    }

    public void showChosenIndex(int chosenIndex, ArrayList<Word> list) {
        System.out.print(list.get(chosenIndex).toString());
        System.out.println();
    }
}
