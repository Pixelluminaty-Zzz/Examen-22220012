package Server;

import interfaces.Cajero;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class CajeroImpl extends UnicastRemoteObject implements Cajero {

    private boolean autenticado = false;
    private final String nipCorrecto = "1234";
    private double saldo = 1000.0;

    public CajeroImpl() throws RemoteException {
    }

    public boolean validarNIP(String nip) throws RemoteException {
        autenticado = nip.equals(nipCorrecto);
        return autenticado;
    }

    public double consultarSaldo() throws RemoteException {
        if (!autenticado) {
            throw new RemoteException("Acceso denegado. NIP no validado.");
        }
        return saldo;
    }

    public boolean depositar(double cantidad) throws RemoteException {
        if (!autenticado || cantidad <= 0) {
            return false;
        }
        saldo += cantidad;
        return true;
    }

    public boolean retirar(double cantidad) throws RemoteException {
        if (!autenticado || cantidad <= 0 || cantidad > saldo) {
            return false;
        }
        saldo -= cantidad;
        return true;
    }
}
