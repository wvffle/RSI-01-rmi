package net.wvffle.pb.rsi.rmi_02_chat.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MyServerInt extends Remote {
  public boolean join (String name) throws RemoteException;
  public void send (String name, String message) throws RemoteException;
  public List<ChatMessage> getMessages () throws RemoteException;
  public boolean hb (String name) throws RemoteException;
}
