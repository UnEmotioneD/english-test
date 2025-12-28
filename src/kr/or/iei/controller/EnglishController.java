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
import kr.or.iei.model.vo.WordWithIndex;
import kr.or.iei.viewer.EnglishViewer;

public class EnglishController {
  Scanner sc;
  EnglishViewer viewer;

  ArrayList<Word> wordList;
  ArrayList<Word> failList;
  ArrayList<Word> testList;
  ArrayList<WordWithIndex> wordsWithIndex;

  public EnglishController() {
    sc = new Scanner(System.in);
    viewer = new EnglishViewer();

    wordList = new ArrayList<>();
    failList = new ArrayList<>();
    testList = new ArrayList<>();
    wordsWithIndex = new ArrayList<>();
  }

  public void mainMethod() {
    readWordFile();

    while (true) {
      int menu = viewer.menu();

      switch (menu) {
        case 1:
          search();
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
          System.out.println("Terminated");
          return;
        default:
          break;
      }
    }
  }

  public void readWordFile() {
    String fileWithWords = "allDb.txt";
    wordList = readFile(fileWithWords);
  }

  public void readFailedWord() {
    String fileWithFailedWords = "failDb.txt";
    failList = readFile(fileWithFailedWords);
  }

  public ArrayList<Word> readFile(String fileName) {
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

  public void search() {
    ArrayList<Word> list = new ArrayList<>();

    boolean found = false;
    String searchWord;
    while (!found) {
      searchWord = viewer.searchViewer();
      // Cancel when typed "c"
      if (searchWord.equalsIgnoreCase("c")) {
        System.out.println("Canceling Search");
        break;
      }

      int chosenIndex = -1;
      switch (searchWord.length()) {
        case 1:
          chosenIndex = viewer.searchView(oneChar(searchWord));
          break;
        case 2:
          chosenIndex = viewer.searchView(twoChar(searchWord.charAt(0), searchWord.charAt(1)));
          break;
        case 3:
          chosenIndex =
              viewer.searchView(
                  threeChar(searchWord.charAt(0), searchWord.charAt(1), searchWord.charAt(2)));
          break;
        default:
          for (Word word : list) {
            if (word.getWord().equalsIgnoreCase(searchWord)
                || word.getDef1().equalsIgnoreCase(searchWord)
                || word.getDef2().equalsIgnoreCase(searchWord)) {
              System.out.println(word);
              found = true;
            } else {
              System.out.println("No such word");
            }
            break;
          }
      } // switch
      if (chosenIndex != -1) {
        viewer.showChosenIndex(chosenIndex - 1, wordsWithIndex);
      }
    }
  }

  public ArrayList<WordWithIndex> oneChar(String searchWord) {
    ArrayList<Word> list = new ArrayList<>();

    int j = 0;
    for (Word word : list) {
      if (word.getWord().charAt(0) == searchWord.charAt(0)) {
        j++;
        wordsWithIndex.add(new WordWithIndex(word.getWord(), word.getDef1(), word.getDef2(), j));
      }
    }
    return wordsWithIndex;
  }

  public ArrayList<WordWithIndex> twoChar(char firstChar, char secondChar) {
    ArrayList<Word> list = new ArrayList<>();

    wordsWithIndex.clear();
    int j = 0;
    for (Word word : list) {
      for (int k = 0; k < word.getWord().length() - 1; k++) {
        if (word.getWord().charAt(k) == firstChar && word.getWord().charAt(k + 1) == secondChar) {
          j++;
          wordsWithIndex.add(new WordWithIndex(word.getWord(), word.getDef1(), word.getDef2(), j));
        }
      }
    }
    return wordsWithIndex;
  }

  public ArrayList<WordWithIndex> threeChar(char firstChar, char secondChar, char thirdChar) {
    ArrayList<Word> list = new ArrayList<>();

    wordsWithIndex.clear();
    int j = 0;
    for (Word word : list) {
      for (int i = 0; i < word.getWord().length() - 2; i++) {
        if (word.getWord().charAt(i) == firstChar
            && word.getWord().charAt(i + 1) == secondChar
            && word.getWord().charAt(i + 2) == thirdChar) {
          j++;
          wordsWithIndex.add(new WordWithIndex(word.getWord(), word.getDef1(), word.getDef2(), j));
        }
      }
    }
    return wordsWithIndex;
  }

  public void add() {
    BufferedWriter bw = null;
    try {
      bw = new BufferedWriter(new FileWriter("allDB.txt", true));

      bw.newLine();
      bw.write(viewer.addViewer("word") + "/");
      bw.write(viewer.addViewer("definition (1/2)") + "/");
      bw.write(viewer.addViewer("definition (2/2)"));

      System.out.println("New word and definitions successfully added");

    } catch (IOException e) {
      System.out.println("I/O Error");
    } finally {
      try {
        assert bw != null;
        bw.close();
      } catch (IOException e) {
        System.out.println("I/O Error");
      }
    }
  }

  public void test() {
    ArrayList<Word> list = new ArrayList<>();

    System.out.println(failList);

    Random random = new Random();
    String selWord = viewer.startTest();
    int ranNum = viewer.random();
    int[] ran = new int[ranNum];

    for (int i = 0; i < ran.length; i++) {
      ran[i] = random.nextInt(list.size());
      for (int j = 0; j < i; j++) {
        if (ran[i] == ran[j]) {
          i--;
        }
      }
    }

    for (int i = 0; i < ranNum; i++) {
      if (selWord.equals("e")) {
        System.out.println(list.get(ran[i]).getDef1() + "\t" + list.get(ran[i]).getDef2());
        String answer = viewer.randomTest();

        if (!answer.equals(list.get(ran[i]).getWord())) {
          testList.add(list.get(ran[i]));
        }

      } else if (selWord.equals("k")) {
        System.out.println(list.get(ran[i]).getWord());
        String answer = viewer.randomTest();

        if (!answer.equals(list.get(ran[i]).getDef1())
            || !answer.equals(list.get(ran[i]).getDef2())) {
          testList.add(list.get(ran[i]));
        }
      } else {
        System.out.println("Error");
      }
    }
    BufferedWriter bw = null;

    try {
      FileWriter fw = new FileWriter("failDB.txt", true);
      bw = new BufferedWriter(fw);

      for (int i = 0; i < testList.size(); i++) {
        for (int j = 0; j < failList.size(); j++) {
          if (!failList.get(i).getWord().equals(testList.get(i).getWord())) {
            bw.write(testList.get(i).getWord() + "/");
            bw.write(testList.get(i).getDef1() + "/");
            bw.write(testList.get(i).getDef2());
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

  public void edit() {
    ArrayList<Word> list = new ArrayList<>();

    String editWord = viewer.editViewer();
    boolean found = false;

    if (editWord.equalsIgnoreCase("c")) {
      System.out.println("Canceling Search");
    } else if (editWord.equalsIgnoreCase("a")) {
      System.out.println("Are you sure you want to delete all?");
      System.out.println("y / s");
      char yesOrNo = sc.next().charAt(0);

      if (yesOrNo == 'y') {

      } else {
        System.out.println("Canceling it ...");
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
