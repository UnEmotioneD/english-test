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
        Word searchResult;
        int searchWordLength = searchWord.length();

        for (Word word : menuCon.getWordList()) {
            String wordFromFile = word.getWord();
            int wordFromFileLength = wordFromFile.length();

            if (searchWordLength == wordFromFileLength) {
                if (searchWord.equalsIgnoreCase(wordFromFile)) {
                    searchResult = new Word();
                    searchResult.setWord(word.getWord());
                    searchResult.setDef1(word.getDef1());
                    searchResult.setDef2(word.getDef2());
                    searchResults.add(searchResult);
                }
            } else if (searchWordLength < wordFromFileLength) {
                for (int i = 0; i < wordFromFileLength - searchWordLength + 1; i++) {
                    String subStr = wordFromFile.substring(i, i + searchWordLength);
                    if (searchWord.equalsIgnoreCase(subStr)) {
                        searchResult = new Word();
                        searchResult.setWord(word.getWord());
                        searchResult.setDef1(word.getDef1());
                        searchResult.setDef2(word.getDef2());
                        searchResults.add(searchResult);
                        break;
                    }
                }
            }
        }
        return searchResults;
    }

    private void searchDef() {}
}
