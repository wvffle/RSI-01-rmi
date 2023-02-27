package net.wvffle.pb.rsi.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Main {
  public static void main(String[] args) {
    System.setProperty("java.security.policy", "security.policy");

    if (System.getSecurityManager() == null) {
      System.setSecurityManager(new SecurityManager());
    }

    // System.setProperty("java.rmi.server.codebase","file:/C:/Users/Jacek/NetBeansProjects/RMIServer/build/classes/");
    System.out.println("Codebase: " + System.getProperty("java.rmi.server.codebase"));

    try {
      LocateRegistry.createRegistry(1099);
      
      MyServerImpl server = new MyServerImpl();
      Naming.rebind("//localhost/ABC", server);
      System.out.println("Serwer oczekuje ...");
    } catch (RemoteException | MalformedURLException e) {
      e.printStackTrace();
    }
  }
}
