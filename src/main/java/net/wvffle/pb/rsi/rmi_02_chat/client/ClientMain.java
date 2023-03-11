package net.wvffle.pb.rsi.rmi_02_chat.client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import net.wvffle.pb.rsi.rmi_02_chat.server.ChatMessage;
import net.wvffle.pb.rsi.rmi_02_chat.server.MyServerInt;

public class ClientMain {
    public static void main(String[] args) {
        try {
            MyServerInt obj = (MyServerInt) Naming.lookup(String.format("//%s/ABC", args.length > 0 ? args[0] : "127.0.0.1"));

            final Scanner scanner = new Scanner(System.in);
            String name;

            while (true) {
              System.out.print("Podaj nazwę użytkownika: ");
              name = scanner.next();

              if (obj.join(name)) {
                break;
              }

              System.out.println("Nie udało się zalogować");
            }

            final String NAME = name;
            Thread scannerThread = new Thread(() -> {
              System.out.println("Podaj wiadomość: ");
              while (true) {
                if (scanner.hasNext()) {
                  String message = scanner.next();

                  if (message.equals("/exit")) {
                    scanner.close();
                    break;
                  }

                  try {
                    obj.send(NAME, message);
                  } catch (RemoteException e) {
                    e.printStackTrace();
                    break;
                  }
                }
              }
            });

            Thread messageThread = new Thread(() -> {
              System.out.println("Waiting for messages...");
              Date date = new Date();

              while (true) {
                try {
                  if (!scannerThread.isAlive() || !obj.hb(NAME)) {
                    System.out.println("Rozłączono");
                    scannerThread.stop();
                    scanner.close();
                    break;
                  }

                  List<ChatMessage> messages = obj.getMessages();
                  for (ChatMessage message : messages) {
                    if (message.getDate().after(date)) {
                      System.out.println(String.format("%s: %s", message.getUser(), message.getContent()));
                      date = message.getDate();
                    }
                  }
                } catch (RemoteException e) {
                  // e.printStackTrace();
                }
              }

            });


            scannerThread.start();
            messageThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
