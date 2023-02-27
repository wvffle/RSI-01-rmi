package net.wvffle.pb.rsi.rmi.client;

import java.rmi.Naming;

import net.wvffle.pb.rsi.rmi.server.CalculationOperator;
import net.wvffle.pb.rsi.rmi.server.MyServerInt;

public class ClientMain {
    public static void main(String[] args) {
        try {
            MyServerInt obj = (MyServerInt) Naming.lookup(String.format("//%s/ABC", args.length > 0 ? args[0] : "127.0.0.1"));
            String text = "Hallo :-)";
            String result = obj.getDescription(text);
            System.out.println("Wysłano do servera: " + text);
            System.out.println("Otrzymana z serwera odpowiedź: " + result);

            System.out.print("21 * 2 = ");
            System.out.println(obj.calc(21, CalculationOperator.MULT, 2));

            System.out.print("40 + 2 = ");
            System.out.println(obj.calc(40, CalculationOperator.ADD, 2));

            System.out.print("84 / 2 = ");
            System.out.println(obj.calc(84, CalculationOperator.DIV, 2));

            System.out.print("44 - 2 = ");
            System.out.println(obj.calc(44, CalculationOperator.SUB, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
