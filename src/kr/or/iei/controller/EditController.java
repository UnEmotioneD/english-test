package kr.or.iei.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import kr.or.iei.model.vo.Word;
import kr.or.iei.viewer.EnglishViewer;

public class EditController {
  Scanner sc;
  EnglishViewer engViewer;

  EnglishController engCon;
  final String fileWithWords;

  public EditController() {
    sc = new Scanner(System.in);
    engViewer = new EnglishViewer();

    engCon = new EnglishController();
    fileWithWords = engCon.getFileWithWords();
  }

  public void add() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileWithWords, true))) {
      bw.newLine();
      bw.write(engViewer.addViewer("word") + "/");
      bw.write(engViewer.addViewer("definition (1/2)") + "/");
      bw.write(engViewer.addViewer("definition (2/2)"));

      // TODO: add logic

      engViewer.addSuccess();

    } catch (IOException e) {
      System.out.println("I/O Error");
    }
  }

  public void edit() {
    ArrayList<Word> list = new ArrayList<>();

    String editWord = engViewer.editViewer();
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
      for (Word word : list) {
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
