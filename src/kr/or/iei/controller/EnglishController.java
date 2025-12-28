package kr.or.iei.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import kr.or.iei.model.vo.Word;
import kr.or.iei.viewer.EnglishViewer;

public class EnglishController {
  Scanner sc;
  EnglishViewer engViewer;
  SearchController searchCon;

  ArrayList<Word> wordList;
  ArrayList<Word> failList;
  ArrayList<Word> testList;

  final String fileWithWords = "allDb.txt";
  final String fileWithFailedWords = "failDb.txt";

  public EnglishController() {
    sc = new Scanner(System.in);
    engViewer = new EnglishViewer();
    searchCon = new SearchController();

    wordList = new ArrayList<>();
    failList = new ArrayList<>();
    testList = new ArrayList<>();
  }

  public void mainMethod() {
    readWordFile();

    while (true) {
      int menu = engViewer.menu();

      switch (menu) {
        case 1:
          searchCon.search();
          break;
        case 2:
          add();
          break;
        case 3:
          test();
          break;
        case 4:
          reTest();
          break;
        case 5:
          edit();
          break;
        case 0:
          engViewer.terminated();
          return;
        default:
          break;
      }
    }
  }

  public void readWordFile() {
    wordList = readFile(fileWithWords);
  }

  public void readFailedWordFile() {
    failList = readFile(fileWithFailedWords);
  }

  private ArrayList<Word> readFile(String fileName) {
    ArrayList<Word> list = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;

      while ((line = br.readLine()) != null) {
        // split line by slash
        // put it into String arr
        // construct Word object with it
        // put Word obj into ArrayList
        String[] wordArr = line.split("/");
        list.add(new Word(wordArr[0], wordArr[1], wordArr[2]));
      }

    } catch (FileNotFoundException e) {
      System.out.println(fileName + " not found.");
    } catch (IOException e) {
      System.out.println("I/O Error");
    }
    return list;
  }

  public void test() {
    ArrayList<Word> list = new ArrayList<>();

    System.out.println(failList);

    Random random = new Random();
    String selWord = engViewer.startTest();
    int ranNum = engViewer.random();
    int[] ran = new int[ranNum];

    for (int j = 0; j < ran.length; j++) {
      ran[j] = random.nextInt(list.size());
      for (int k = 0; k < j; k++) {
        if (ran[j] == ran[k]) {
          j--;
        }
      }
    }

    for (int j = 0; j < ranNum; j++) {
      final String engSelected = "e";
      final String korSelected = "k";

      if (selWord.equals(engSelected)) {
        System.out.println(list.get(ran[j]).getDef1() + "\t" + list.get(ran[j]).getDef2());
        String answer = engViewer.randomTest();

        if (!answer.equals(list.get(ran[j]).getWord())) {
          testList.add(list.get(ran[j]));
        }

      } else if (selWord.equals(korSelected)) {
        System.out.println(list.get(ran[j]).getWord());
        String answer = engViewer.randomTest();

        if (!answer.equals(list.get(ran[j]).getDef1())
            || !answer.equals(list.get(ran[j]).getDef2())) {
          testList.add(list.get(ran[j]));
        }
      } else {
        System.out.println("Error");
      }
    }
    BufferedWriter bw = null;

    try {
      FileWriter fw = new FileWriter(fileWithFailedWords, true);
      bw = new BufferedWriter(fw);

      for (int k = 0; k < testList.size(); k++) {
        for (int l = 0; l < failList.size(); l++) {
          if (!failList.get(k).getWord().equals(testList.get(k).getWord())) {
            bw.write(testList.get(k).getWord() + "/");
            bw.write(testList.get(k).getDef1() + "/");
            bw.write(testList.get(k).getDef2());
            bw.newLine();
          }
        }
      }
      failList.clear();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        bw.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void reTest() {}

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
