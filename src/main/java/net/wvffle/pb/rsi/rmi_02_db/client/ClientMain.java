package net.wvffle.pb.rsi.rmi_02_db.client;

import java.rmi.Naming;
import java.util.Scanner;
import java.util.stream.Collectors;

import net.wvffle.pb.rsi.rmi_02_db.server.MyServerInt;

public class ClientMain {
    public static void main(String[] args) {
        try {
            MyServerInt obj = (MyServerInt) Naming.lookup(String.format("//%s/ABC", args.length > 0 ? args[0] : "127.0.0.1"));

            System.out.println("Szukaj userow po imieniu: ");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.next();

            System.out.println("Znalezieni userzy:\n" + obj.getUsers()
              .stream()
              .filter(user -> user.getName().toLowerCase().startsWith(name.toLowerCase()))
              .map(user -> String.format("%s (%d)\n", user.getName(), user.getAge()))
              .collect(Collectors.joining())
            );

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
