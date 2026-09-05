package com.unemotioned.englishtest.edit.viewer;

import com.unemotioned.englishtest.common.vo.Word;
import java.util.Scanner;

public class EditViewer {
    Scanner sc;

    public EditViewer() {
        sc = new Scanner(System.in);
    }

    public String newWord() {
        System.out.print("New Word: ");
        return sc.next();
    }

    public void dupWord() {
        System.out.println("Duplicated Word.");
    }

    public Word add() {
        Word newWord = new Word();

        System.out.println();
        System.out.print("Enter new word: ");
        newWord.setWord(sc.next());
        System.out.print("Definition (1/2): ");
        newWord.setDef1(sc.next());
        System.out.print("Definition (2/2): ");
        newWord.setDef2(sc.next());

        return newWord;
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
