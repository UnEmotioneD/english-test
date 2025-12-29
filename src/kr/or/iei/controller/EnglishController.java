package kr.or.iei.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import kr.or.iei.model.vo.Word;
import kr.or.iei.viewer.EnglishViewer;

public class EnglishController {
  private final String fileWithWords = "allDb.txt";
  private final String fileWithFailedWords = "failDb.txt";

  Scanner sc;
  EnglishViewer engViewer;
  SearchController searchCon;
  EditController editCon;
  TestController testCon;

  ArrayList<Word> wordList;
  ArrayList<Word> failList;

  public EnglishController() {
    sc = new Scanner(System.in);
    engViewer = new EnglishViewer();
    searchCon = new SearchController();
    editCon = new EditController();
    testCon = new TestController();

    wordList = new ArrayList<>();
    failList = new ArrayList<>();
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
          editCon.add();
          break;
        case 3:
          editCon.edit();
          break;
        case 4:
          testCon.test();
          break;
        case 5:
          testCon.reTest();
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

  public String getFileWithWords() {
    return fileWithWords;
  }

  public String getFileWithFailedWords() {
    return fileWithFailedWords;
  }
}
