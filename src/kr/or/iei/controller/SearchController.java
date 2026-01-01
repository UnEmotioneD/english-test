package kr.or.iei.controller;

import kr.or.iei.model.vo.Word;
import kr.or.iei.viewer.Viewer;

public class SearchController {
  MenuController menuCon;
  Viewer viewer;

  public SearchController(MenuController menuCon) {
    this.menuCon = menuCon;
    this.viewer = new Viewer();
  }

  public void search() {
    String searchWord;
    String cancelSearch = "c";

    boolean searching = true;
    while (searching) {
      searchWord = viewer.searchViewer();

      if (searchWord.equals(cancelSearch)) {
        searching = false;
        continue;
      }

      Word searchResults = searchWord(searchWord);

      if (searchResults != null) {
        viewer.showSearchResults(searchResults);
      } else {
        viewer.noSearchResults(searchWord);
      }
    }
  }

  // TODO: sliding search window
  public Word searchWord(String searchWord) {
    Word searchResults = new Word();

    for (Word word : menuCon.getWordList()) {
      String textFromFile = word.getWord();

      if (searchWord.equalsIgnoreCase(textFromFile)) {
        searchResults.setWord(word.getWord());
        searchResults.setDef1(word.getDef1());
        searchResults.setDef2(word.getDef2());

        return searchResults;
      }
    }
    return null;
  }

  public void searchDef() {}
}
