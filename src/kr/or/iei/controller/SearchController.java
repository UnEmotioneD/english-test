package kr.or.iei.controller;

import kr.or.iei.model.vo.Word;
import kr.or.iei.viewer.Viewer;

import java.util.ArrayList;

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

            ArrayList<Word> wordList = searchWord(searchWord);

            if (!wordList.isEmpty()) {
                viewer.searchResultsHeader();
                for (Word word : wordList) {
                    viewer.showSearchResults(word);
                }
            } else {
                viewer.noSearchResults(searchWord);
            }
        }
    }

    public ArrayList<Word> searchWord(String searchWord) {
        ArrayList<Word> searchResults = new ArrayList<>();
        Word searchResult;
        int searchWordLength = searchWord.length();

        for (Word word : menuCon.getWordList()) {
            String wordFromFile = word.getWord();
            int wordFromFileLength = wordFromFile.length();

            if (searchWordLength > wordFromFileLength) {
                continue;
            } else if (searchWordLength == wordFromFileLength) {
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

    public void searchDef() {}
}
