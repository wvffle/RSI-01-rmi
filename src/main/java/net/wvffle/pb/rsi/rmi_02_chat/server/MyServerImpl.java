package net.wvffle.pb.rsi.rmi_02_chat.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyServerImpl extends UnicastRemoteObject implements MyServerInt {

  List<ChatMessage> messages = new ArrayList<>();
  List<ChatUser> users = new ArrayList<>();

  protected MyServerImpl() throws RemoteException {
    super();
  }

  @Override
  public boolean join(String name) throws RemoteException {
    for (ChatUser user : users) {
      if (user.getName().equals(name)) {
        long delta = (new Date().getTime() - user.getHb().getTime()) / 1000;
        if (delta < 5) {
          System.out.println("User " + user.getName() + " is already in the chat (" + delta + " seconds left)");
          return false;
        }

        System.out.println("Overriding user " + user.getName() + " (" + delta + " seconds past)");
      }
    }

    System.out.println("Registering user " + name);
    users.add(new ChatUser(name));
    return true;
  }

  @Override
  public void send(String name, String message) throws RemoteException {
    messages.add(new ChatMessage(new Date(), name, message));
    System.out.println(name + ": " + message);
  }

  @Override
  public List<ChatMessage> getMessages() throws RemoteException {
    return messages;
  }

  @Override
  public boolean hb(String name) throws RemoteException {
    for (ChatUser user : users) {
      if (user.getName().equals(name)) {
        long delta = (new Date().getTime() - user.getHb().getTime()) / 1000;
        if (delta >= 5) {
          // System.out.println("Releasing user " + user.getName());
          return true;
        }

        // System.out.println("Received heartbeat from " + user.getName());
        user.hb();
        return true;
      }
    }

    return false;
  }
}
