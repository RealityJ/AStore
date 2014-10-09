/**
 * @ author: Rong Ge
 * The driver for HW6
 * Do not change this file
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TopTenPlayersRunner {
  
  // TODO implement the method
  public static int parseNumGames(String s) {
    int index = s.indexOf('\t');
    return Integer.parseInt(s.substring(0, index));
  }

  // TODO implement the method
  public static String parseName(String s) {
    int index1, index2;
    index1 = s.indexOf('\t');
    index2 = s.lastIndexOf('\t');
    return s.substring(index1, index2);
  }

  // TODO implement the method
  public static int parseScore(String s) {
    int index = s.lastIndexOf('\t');
    return Integer.parseInt(s.substring(index + 1, s.length()).trim());

  }
  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stub
    if (args.length != 1) {
      System.out.println("usage: " + TopTenPlayersRunner.class.getName()
          + " input_file_name");
      System.exit(-1);
    }
    BufferedReader in = new BufferedReader(new FileReader(args[0]));
    TopTenPlayersList myList = new TopTenPlayersList();
    String line;
    int numGame = 0;
    String pName;
    int pScore;
    in.readLine(); // disreagard the first line in the input file
    while ((line = in.readLine()) != null) {
      int i = parseNumGames(line); // You need to implement parseNumGames
                      // yourself
      if (i == -1)
        break;
      numGame = (parseNumGames(line) > 0 ? parseNumGames(line) : numGame);
      pName = parseName(line); // You need to implement parseName yourself
      pScore = parseScore(line); // You need to implement parseScore
                    // yourself

      /*
       * System.out.println(String.valueOf(numGame) + '\t' + pName + '\t'
       * + String.valueOf(pScore));
       */

      myList.addEntry(pName, pScore);

      if (numGame % 5 == 0) {
        System.out.println("After game " + String.valueOf(numGame)
            + ", the records are:");
        myList.printList();
      }
    }

    System.out.println("\nThe last game is game " + String.valueOf(numGame)
        + ", the final records are:");
    myList.printList();

    myList.removeFirst();
    System.out
        .println("\nHere are the records after we remove the highest score:");
    myList.printList();

    myList.removeLast();
    System.out
        .println("\nHere are the records after we remove the highest and lowest score:");
    myList.printList();

    in.close();

  }

}