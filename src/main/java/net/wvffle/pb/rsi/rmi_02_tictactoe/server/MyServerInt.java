package net.wvffle.pb.rsi.rmi_02_tictactoe.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MyServerInt extends Remote {
  public int join () throws RemoteException;
  public MoveResult move (int index) throws RemoteException;
  public boolean canStart () throws RemoteException;
  public List<BoardState> getState () throws RemoteException;
  public int whoWon () throws RemoteException;
  public int turnOf () throws RemoteException;
}
