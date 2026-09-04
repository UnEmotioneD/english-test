package com.unemotioned.englishtest.edit.controller;

import com.unemotioned.englishtest.common.Config;
import com.unemotioned.englishtest.menu.controller.MenuController;
import com.unemotioned.englishtest.common.vo.Word;
import com.unemotioned.englishtest.edit.viewer.EditViewer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EditController {
    Scanner sc;
    MenuController menuCon;
    EditViewer editViewer;

    public EditController(MenuController menuCon) {
        sc = new Scanner(System.in);
        this.menuCon = menuCon;
        editViewer = new EditViewer();
    }

    public void newWord() {
        String newWord = editViewer.newWord();

        for (Word word : menuCon.getWordList()) {
            if (word.getWord().equalsIgnoreCase(newWord)) {
                editViewer.dupWord();
                return;
            } else {
                // TODO: get two different definitions
            }
        }
    }

    // TODO: check duplication before adding
    public void add() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Config.WORD_FILE, true))) {
            Word word = editViewer.add();
            bw.newLine();
            bw.write(word.getWord() + "/" + word.getDef1() + "/" + word.getDef2());
            editViewer.addSuccess();
        } catch (IOException e) {
            System.out.println("I/O Error");
        }
    }

    public void edit() {
        String editWord = editViewer.editViewer();
        boolean found = false;

        if (editWord.equalsIgnoreCase("c")) {
            System.out.println("Canceling Search");
        } else if (editWord.equalsIgnoreCase("a")) {
            System.out.println("Are you sure you want to delete all?");
            System.out.println("y / s");
            char yesOrNo = sc.next().charAt(0);

            final char yesSelected = 'y';
            if (yesOrNo == yesSelected) {
                // TODO: confirm editing word
            } else {
                System.out.println("Canceling ...");
            }

        } else {
            for (Word word : menuCon.getWordList()) {
                if (word.getWord().equalsIgnoreCase(editWord)) {
                    System.out.println(word);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("No such word");
            } else {
                // char editOrDelete = view.editOrDelete();
            }
        }
    }
}
