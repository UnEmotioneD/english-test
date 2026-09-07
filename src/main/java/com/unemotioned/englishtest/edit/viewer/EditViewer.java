package com.unemotioned.englishtest.edit.viewer;

import com.unemotioned.englishtest.common.vo.Word;
import java.util.Scanner;

public class EditViewer {
    Scanner sc;

    public EditViewer() {
        sc = new Scanner(System.in);
    }

    public Word add() {
        Word newWord = new Word();

        System.out.println("\n===== Add new Word =====");
        System.out.println("Cancel (C)");

        System.out.print("\nEnter new word: ");
        String input = sc.next();
        if (input.equals("C")) {
            System.out.println("Cancel adding new word...\n");
            return null;
        }
        newWord.setWord(input);

        System.out.print("Definition (1/2): ");
        newWord.setDef1(sc.next());
        System.out.print("Definition (2/2): ");
        newWord.setDef2(sc.next());

        return newWord;
    }

    public void printDup(String word) {
        System.out.println("The word: " + word + " is already saved.");
    }

    public void addSuccess() {
        System.out.println("New word added successfully!");
    }

    public String editViewer() {
        System.out.println("Search word to edit / delete");
        System.out.println("Delete all / Cancel (a/c)");
        System.out.print("=> ");

        return sc.next();
    }
}
