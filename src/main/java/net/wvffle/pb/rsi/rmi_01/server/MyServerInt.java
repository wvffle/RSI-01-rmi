package net.wvffle.pb.rsi.rmi_01.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyServerInt extends Remote {
    String getDescription (String text) throws RemoteException;
    int calc (int a, CalculationOperator operator, int b) throws RemoteException;
}
