package net.wvffle.pb.rsi.rmi_02_tictactoe.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class MyServerImpl extends UnicastRemoteObject implements MyServerInt {

  List<BoardState> state = new ArrayList<>();
  int clients = 0;
  int turnOf = 0;

  public MyServerImpl() throws RemoteException {
    super();
  }

  private void reset () {
    state.clear();

    for (int i = 0; i < 9; i++) {
      state.add(BoardState.EMPTY);
    }

    turnOf = 0;
  }

  @Override
  public int join() throws RemoteException {
    if (clients >= 2) return -1;
    clients += 1;

    if (clients == 2) {
      reset();
    }

    return clients - 1;
  }

  @Override
  public boolean canStart() throws RemoteException {
    return clients == 2;
  }

  @Override
  public List<BoardState> getState() throws RemoteException {
    return state;
  }

  @Override
  public MoveResult move(int index) throws RemoteException {
    BoardState s = state.get(index);

    if (s == BoardState.EMPTY) {
      state.set(index, turnOf == 0 ? BoardState.O : BoardState.X);
      turnOf = Math.abs(turnOf - 1);

      if (whoWon() != -1) {
        clients = 0;
        return MoveResult.WIN;
      }

      return MoveResult.NOOP;
    }

    return MoveResult.INVALID_MOVE;
  }

  @Override
  public int whoWon() throws RemoteException {
    BoardState winningState = BoardState.EMPTY;
    for (int i = 0; i < 3; i++) {
      if (state.get(i * 3 + 0) == state.get(i * 3 + 1) && state.get(i * 3 + 0) == state.get(i * 3 + 2)) {
        winningState = state.get(i * 3 + 0);
        break;
      }

      if (state.get(i) == state.get(3 + i) && state.get(0 + i) == state.get(6 + i)) {
        winningState = state.get(i);
        break;
      }
    }

    if (winningState == BoardState.EMPTY) {
      if (state.get(0) == state.get(4) && state.get(4) == state.get(8) || state.get(2) == state.get(4) && state.get(4) == state.get(6)) {
        winningState = state.get(4);
      }
    }

    if (!state.contains(BoardState.EMPTY)) return -2;
    if (winningState == BoardState.EMPTY) return -1;
    return winningState == BoardState.O ? 0 : 1;
  }

  @Override
  public int turnOf() throws RemoteException {
    return turnOf;
  }
}
