package net.wvffle.pb.rsi.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyServerImpl extends UnicastRemoteObject implements MyServerInt {

  protected MyServerImpl() throws RemoteException {
    super();
  }

  private int i = 0;

  @Override
  public String getDescription(String text) throws RemoteException {
    i += 1;

    String res = String.format("MyServerImpl.getDescription: %s, %d", text, i);
    System.out.println(res);
    return res;
  }
    
}
