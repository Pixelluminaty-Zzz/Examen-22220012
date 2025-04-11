package cliente;

import interfaces.Cajero;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            Cajero cajero = (Cajero) registro.lookup("CajeroService");
            Scanner sc = new Scanner(System.in);

            System.out.print("Ingrese NIP: ");
            String nip = sc.nextLine();

            if (!cajero.validarNIP(nip)) {
                System.out.println("NIP incorrecto. Acceso denegado.");
                return;
            }

            int opcion;
            do {
                System.out.println("\n1. Consultar saldo\n2. Depositar\n3. Retirar\n4. Salir");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Saldo: $" + cajero.consultarSaldo());
                        break;
                    case 2:
                        System.out.print("Cantidad a depositar: ");
                        double dep = sc.nextDouble();
                        if (cajero.depositar(dep)) {
                            System.out.println("Depósito exitoso.");
                        } else {
                            System.out.println("Depósito inválido.");
                        }
                        break;
                    case 3:
                        System.out.print("Cantidad a retirar: ");
                        double ret = sc.nextDouble();
                        if (cajero.retirar(ret)) {
                            System.out.println("Retiro exitoso.");
                        } else {
                            System.out.println("Retiro inválido.");
                        }
                        break;
                }
            } while (opcion != 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
