package Server;

import interfaces.Cajero;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

    public static void main(String[] args) {
        try {
            Cajero cajero = new CajeroImpl();
            Registry registro = LocateRegistry.createRegistry(1099);
            registro.rebind("CajeroService", cajero);
            System.out.println("Servidor listo.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
