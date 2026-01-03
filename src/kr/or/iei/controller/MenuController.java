package kr.or.iei.controller;

import kr.or.iei.common.Config;
import kr.or.iei.model.vo.Word;
import kr.or.iei.viewer.Viewer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuController {
    Scanner sc;

    Viewer viewer;
    SearchController searchCon;
    EditController editCon;
    TestController testCon;

    ArrayList<Word> wordList;
    ArrayList<Word> failList;

    public MenuController() {
        sc = new Scanner(System.in);

        viewer = new Viewer();
        searchCon = new SearchController(this);
        editCon = new EditController(this);
        testCon = new TestController(this);

        wordList = new ArrayList<>();
        failList = new ArrayList<>();
    }

    public void mainMenu() {
        while (true) {
            readWordFile();
            int menu = viewer.menu();
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
                    viewer.terminated();
                    return;
                default:
                    break;
            }
        }
    }

    public void readWordFile() {
        wordList = readFile(Config.WORD_FILE);
    }

    public void readFailedWordFile() {
        failList = readFile(Config.FAILED_WORD_FILE);
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

    public ArrayList<Word> getWordList() {
        return wordList;
    }

    public ArrayList<Word> getFailList() {
        return failList;
    }
}
