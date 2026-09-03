package com.unemotioned.englishtest.controller;

import com.unemotioned.englishtest.model.vo.Word;
import com.unemotioned.englishtest.viewer.SearchViewer;
import java.util.ArrayList;

public class SearchController {
    MenuController menuCon;
    SearchViewer searchViewer;

    public SearchController(MenuController menuCon) {
        this.menuCon = menuCon;
        searchViewer = new SearchViewer();
    }

    public void search() {
        String searchWord;

        while (true) {
            searchWord = searchViewer.searchViewer();

            if (searchWord.equals("C")) {
                searchViewer.cancelSearch();
                break;
            }

            ArrayList<Word> wordList = searchWord(searchWord);

            if (!wordList.isEmpty()) {
                searchViewer.searchResultsHeader();
                for (Word word : wordList) {
                    searchViewer.showSearchResults(word);
                }
            } else {
                searchViewer.noSearchResults(searchWord);
            }
        }
    }

    private ArrayList<Word> searchWord(String searchWord) {
        ArrayList<Word> searchResults = new ArrayList<>();

        for (Word word : menuCon.getWordList()) {
            String wordFromFile = word.getWord();

            if (wordFromFile.toLowerCase().contains(searchWord.toLowerCase())) {
                searchResults.add(word);
            }
        }

        return searchResults;
    }

    private void searchDef() {}
}
