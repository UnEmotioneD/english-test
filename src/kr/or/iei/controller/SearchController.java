package kr.or.iei.controller;

import java.util.ArrayList;
import kr.or.iei.model.vo.Word;
import kr.or.iei.viewer.Viewer;

public class SearchController {
  MenuController menuCon;
  Viewer viewer;

  ArrayList<Word> wordsWithIndex;
  ArrayList<Word> wordList;

  public SearchController() {
    menuCon = new MenuController();
    viewer = new Viewer();

    wordsWithIndex = new ArrayList<>();
    wordList = menuCon.getWordList();
  }

  public void search() {
    String searchWord;
    String cancelSearch = "c";

    boolean isFound = false;
    while (isFound) {
      searchWord = viewer.searchViewer();

      if (searchWord.equals(cancelSearch)) {
        isFound = !isFound;
        continue;
      }

      Word searchResults = searchWord(searchWord);

      if (searchResults != null) {
        viewer.showSearchResults(searchResults);
      }
    }
  }

  // TODO: sliding search window
  public Word searchWord(String searchWord) {
    Word searchResults = new Word();

    for (Word word : wordList) {
      String textFromFile = word.getWord();

      if (searchWord.equalsIgnoreCase(textFromFile)) {
        searchResults.setWord(word.getWord());
        searchResults.setDef1(word.getDef1());
        searchResults.setDef2(word.getDef2());
      }
    }

    return searchResults;
  }

  public void searchDef() {}
}
