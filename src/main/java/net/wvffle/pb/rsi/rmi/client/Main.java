package net.wvffle.pb.rsi.rmi.client;

import java.rmi.Naming;

import net.wvffle.pb.rsi.rmi.server.MyServerInt;

public class Main {
    public static void main(String[] args) {
        System.setProperty("java.security.policy", "security.policy");
        System.setSecurityManager(new SecurityManager());

        try {
            MyServerInt myRemoteObject = (MyServerInt) Naming.lookup("//localhost/ABC");
            String text = "Hallo :-)";
            String result = myRemoteObject.getDescription(text);
            System.out.println("Wysłano do servera: " + text);
            System.out.println("Otrzymana z serwera odpowiedź: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
