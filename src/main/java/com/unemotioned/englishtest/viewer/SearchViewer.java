package com.unemotioned.englishtest.viewer;

import com.unemotioned.englishtest.model.vo.Word;
import java.util.Scanner;

public class SearchViewer {
    Scanner sc;

    public SearchViewer() {
        sc = new Scanner(System.in);
    }

    public String searchViewer() {
        System.out.println("Search Word / Cancel(C)");
        System.out.print("=> ");
        return sc.next();
    }

    public void cancelSearch() {
        System.out.println("Canceling Search");
    }

    public void searchResultsHeader() {
        System.out.println("=== Search Results ===");
    }

    public void showSearchResults(Word word) {
        System.out.println("Word: " + word.getWord());
        System.out.println("Definitions: " + word.getDef1() + ", " + word.getDef2());
    }

    public void noSearchResults(String searchedWord) {
        System.out.println("No such words: " + searchedWord);
    }
}
