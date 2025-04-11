package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cajero extends Remote {

    boolean validarNIP(String nip) throws RemoteException;

    double consultarSaldo() throws RemoteException;

    boolean depositar(double cantidad) throws RemoteException;

    boolean retirar(double cantidad) throws RemoteException;
}
