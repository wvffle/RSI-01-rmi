package net.wvffle.pb.rsi.rmi_02_db.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MyServerInt extends Remote {
  List<User> getUsers() throws RemoteException;
}
