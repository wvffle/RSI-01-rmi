package net.wvffle.pb.rsi.rmi_02_tictactoe.client;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

import net.wvffle.pb.rsi.rmi_02_tictactoe.server.BoardState;
import net.wvffle.pb.rsi.rmi_02_tictactoe.server.MoveResult;
import net.wvffle.pb.rsi.rmi_02_tictactoe.server.MyServerInt;

public class ClientMain {
  final static Scanner scanner = new Scanner(System.in);
  static int id = -1;

  public static void main(String[] args) {
    try {
      MyServerInt obj = (MyServerInt) Naming.lookup(String.format("//%s/ABC", args.length > 0 ? args[0] : "127.0.0.1"));

      id = obj.join();
      if (id == -1) {
        System.out.println("Nie można dołączyć. Gra aktualnie w toku.");
        return;
      }

      System.out.println("Oczekiwanie na przeciwnika...");
      while (!obj.canStart());

      System.out.println(obj.turnOf() == id ? "Rozpoczynasz Ty!" : "Rozpoczyna przeciwnik.");

      int won = -1;
      while ((won = obj.whoWon()) == -1) {
        while (obj.turnOf() != id);
        printBoardState(obj.getState());
        if ((won = obj.whoWon()) != -1) break;

        while (obj.move(getMove()) == MoveResult.INVALID_MOVE);
        printBoardState(obj.getState());
      }

      System.out.println(won == id ? "Wygrałeś!" : won == -2 ? "Remis..." : "Nie wygrałeś!");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static int getMove () {
    while (true) {
      System.out.print("> ");
      String move = scanner.next();
      char a = move.toLowerCase().charAt(0);
      char b = move.charAt(1);

      if (move.length() == 2 && Character.isDigit(b) && Character.isAlphabetic(a)) {
        if (a == 'a' || a == 'b' || a == 'c') {
          if (b == '1' || b == '2' || b == '3') {
            return (a - 'a') + (b - '1') * 3;
          }
        }
      }
    }
  }

  private static void printBoardState (List<BoardState> state) {
    System.out.println(String.format("  ┏━━━┳━━━┳━━━┓"));
    System.out.println(String.format("1 ┃ %s ┃ %s ┃ %s ┃", sign(state.get(0)), sign(state.get(1)), sign(state.get(2))));
    System.out.println(String.format("  ┣━━━╋━━━╋━━━┫"));
    System.out.println(String.format("2 ┃ %s ┃ %s ┃ %s ┃", sign(state.get(3)), sign(state.get(4)), sign(state.get(5))));
    System.out.println(String.format("  ┣━━━╋━━━╋━━━┫"));
    System.out.println(String.format("3 ┃ %s ┃ %s ┃ %s ┃", sign(state.get(6)), sign(state.get(7)), sign(state.get(8))));
    System.out.println(String.format("  ┗━━━┻━━━┻━━━┛"));
    System.out.println(String.format("    A   B   C  "));
  }

  private static String sign (BoardState state) {
    switch (state) {
      case X: return (id == 1 ? "\u001B[32m" : "\u001B[31m") + "X\u001B[0m";
      case O: return (id == 0 ? "\u001B[32m" : "\u001B[31m") + "O\u001B[0m";
      default: return " ";
    }
  }
}
