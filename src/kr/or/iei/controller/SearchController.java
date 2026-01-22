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
        String cancelSearch = "C";

        boolean searching = true;
        while (searching) {
            searchWord = viewer.searchViewer();

            if (searchWord.equals(cancelSearch)) {
                searching = false;
                viewer.cancelSearch();
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

    // TODO: return more then one word
    public Word searchWord(String searchWord) {
        Word searchResults = new Word();
        int searchWordLength = searchWord.length();

        for (Word word : menuCon.getWordList()) {
            String wordFromFile = word.getWord();
            int wordFromFileLength = wordFromFile.length();

            if (searchWordLength >= wordFromFileLength) {
                continue;

                // BUG: if length is equal won't compare
            } else if (searchWordLength == wordFromFileLength) {
                if (searchWord.equalsIgnoreCase(wordFromFile)) {
                    searchResults.setWord(word.getWord());
                    searchResults.setDef1(word.getDef1());
                    searchResults.setDef2(word.getDef2());

                    return searchResults;
                }
            } else if (searchWordLength <= wordFromFileLength) {
                for (int i = 0; i < wordFromFileLength - searchWordLength + 1; i++) {
                    String subStr = wordFromFile.substring(i, i + searchWordLength);
                    if (searchWord.equalsIgnoreCase(subStr)) {
                        searchResults.setWord(word.getWord());
                        searchResults.setDef1(word.getDef1());
                        searchResults.setDef2(word.getDef2());

                        return searchResults;
                    }
                }
            }
        }
        return null;
    }

    public void searchDef() {}
}
