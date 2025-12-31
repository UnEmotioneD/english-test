package kr.or.iei.controller;

import java.util.ArrayList;
import kr.or.iei.model.vo.Word;
import kr.or.iei.viewer.EnglishViewer;

public class SearchController {
  EnglishController engCon;
  EnglishViewer engViewer;

  ArrayList<Word> wordsWithIndex;
  ArrayList<Word> wordList;

  public SearchController() {
    engCon = new EnglishController();
    engViewer = new EnglishViewer();

    wordsWithIndex = new ArrayList<>();
    wordList = engCon.getWordList();
  }

  // BUG: stack over flow
  // must have no exit statement somewhere
  public void search() {
    String searchWord;

    boolean found = false;
    while (!found) {
      searchWord = engViewer.searchViewer();

      final String cancel = "c";
      if (searchWord.equalsIgnoreCase(cancel)) {
        System.out.println("Canceling Search");
        break;
      }

      int chosenIndex = -1;
      switch (searchWord.length()) {
        case 1:
          chosenIndex = engViewer.searchView(oneChar(searchWord));
          break;
        case 2:
          chosenIndex = engViewer.searchView(twoChar(searchWord.charAt(0), searchWord.charAt(1)));
          break;
        case 3:
          chosenIndex =
              engViewer.searchView(
                  threeChar(searchWord.charAt(0), searchWord.charAt(1), searchWord.charAt(2)));
          break;
        default:
          for (Word word : wordList) {
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
        engViewer.showChosenIndex(chosenIndex - 1, wordsWithIndex);
      }
    }
  }

  public ArrayList<Word> oneChar(String searchWord) {
    for (Word word : wordList) {
      if (word.getWord().charAt(0) == searchWord.charAt(0)) {
        wordsWithIndex.add(
            new Word(word.getWord(), word.getDef1(), word.getDef2(), word.getIndex()));
      }
    }
    return wordsWithIndex;
  }

  public ArrayList<Word> twoChar(char firstChar, char secondChar) {
    wordsWithIndex.clear();
    int j = 0;
    for (Word word : wordList) {
      for (int k = 0; k < word.getWord().length() - 1; k++) {
        if (word.getWord().charAt(k) == firstChar && word.getWord().charAt(k + 1) == secondChar) {
          j++;
          wordsWithIndex.add(new Word(word.getWord(), word.getDef1(), word.getDef2(), j));
        }
      }
    }
    return wordsWithIndex;
  }

  public ArrayList<Word> threeChar(char firstChar, char secondChar, char thirdChar) {
    wordsWithIndex.clear();
    int j = 0;
    for (Word word : wordList) {
      for (int k = 0; k < word.getWord().length() - 2; k++) {
        if (word.getWord().charAt(k) == firstChar
            && word.getWord().charAt(k + 1) == secondChar
            && word.getWord().charAt(k + 2) == thirdChar) {
          j++;
          wordsWithIndex.add(new Word(word.getWord(), word.getDef1(), word.getDef2(), j));
        }
      }
    }
    return wordsWithIndex;
  }
}
