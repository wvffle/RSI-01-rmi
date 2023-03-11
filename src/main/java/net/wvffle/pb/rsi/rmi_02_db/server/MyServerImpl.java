package net.wvffle.pb.rsi.rmi_02_db.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class MyServerImpl extends UnicastRemoteObject implements MyServerInt {

  protected MyServerImpl() throws RemoteException {
    super();
  }

  @Override
  public List<User> getUsers() throws RemoteException {
    List<User> users = new ArrayList<>();

    users.add(new User("John", 23));
    users.add(new User("Agatha", 27));
    users.add(new User("Tom", 30));
    users.add(new User("Jonas", 28));

    return users;
  }

}
