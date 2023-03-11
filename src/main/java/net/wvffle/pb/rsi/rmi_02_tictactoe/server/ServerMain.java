package net.wvffle.pb.rsi.rmi_02_tictactoe.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerMain {
  public static void main(String[] args) {
    try {
      LocateRegistry.createRegistry(1099);

      MyServerImpl server = new MyServerImpl();
      Naming.rebind("//0.0.0.0/ABC", server);
      System.out.println("Serwer oczekuje ...");
    } catch (RemoteException | MalformedURLException e) {
      e.printStackTrace();
    }
  }
}
