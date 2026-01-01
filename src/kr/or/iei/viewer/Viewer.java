package kr.or.iei.viewer;

import java.util.ArrayList;
import java.util.Scanner;
import kr.or.iei.model.vo.Word;

public class Viewer {
  Scanner sc;

  public Viewer() {
    sc = new Scanner(System.in);
  }

  public int menu() {
    System.out.println("===== Study English =====");
    System.out.println("1 Search");
    System.out.println("2 Add new");
    System.out.println("3 Start test");
    System.out.println("4 Make-up exam");
    System.out.println("5 Edit / Delete");
    System.out.println("0 Terminate");
    System.out.print("=> ");
    return sc.nextInt();
  }

  public void terminated() {
    System.out.println("Terminated");
  }

  public String searchViewer() {
    String input = "";

    System.out.println("Search Word / Cancel (w/c): ");
    input = sc.next();
    if (input.equalsIgnoreCase("c")) {
      System.out.println("Canceling Search");
    }
    return input;
  }

  public void showSearchResults(Word word) {
    System.out.println("=== Search Results ===");
    System.out.println("Word: " + word.getWord());
    System.out.println("Definitions: " + word.getDef1() + ", " + word.getDef2());
  }

  public String newWord() {
    System.out.println("New Word: ");
    return sc.next();
  }

  public void dupWord() {
    System.out.println("Duplicated Word.");
  }

  public String addViewer(String text) {
    System.out.println("Enter new " + text + " : ");
    return sc.next();
  }

  public void addSuccess() {
    System.out.println("New word added successfully!");
  }

  public String editViewer() {
    System.out.println("Search word to edit / delete");
    System.out.println("Delete all / Cancel (a/c)");
    System.out.print("=> ");
    return sc.next();
  }

  public String startTest() {
    System.out.print("Select to English or Korean (e/k): ");
    return sc.next();
  }

  public int random() {
    System.out.print("Please enter the number of tests : ");
    return sc.nextInt();
  }

  public String randomTest() {
    System.out.print("Answer : ");
    return sc.next();
  }

  public char editOrDelete() {
    return sc.next().charAt(0);
  }

  public int searchView(ArrayList<Word> list) {
    for (int i = 0; i < list.size(); i++) {
      System.out.printf("%2d %-13s\t", i + 1, list.get(i).getWord());
      if ((i + 1) % 3 == 0) {
        System.out.println();
      }
    }
    System.out.print("\nChoose index : ");
    return sc.nextInt();
  }

  public void showChosenIndex(int chosenIndex, ArrayList<Word> list) {
    System.out.print(list.get(chosenIndex).toString());
    System.out.println();
  }
}
