import java.rmi.*;
import java.rmi.server.*;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

interface HelloInterface extends Remote
{public String say() throws RemoteException;}

class Hello extends UnicastRemoteObject implements HelloInterface
{
	private String message;
	
	public Hello(String msg) throws RemoteException
	{message = msg;}
	
	public String say() throws RemoteException
	{
		System.out.println("Called by HelloClient");
		return message;
	}
}

public class HelloServer
{
	public static void main(String[] args)
	{
		try
		{
			LocateRegistry.createRegistry(1099);
			HelloInterface hello = new Hello("Hello, World!");
			Naming.rebind("Hello",hello);
			//Naming.rebind("//192.168.1.105:1099/Hello",hello);
			System.out.println("Hello Server is ready.");
		}catch(Exception e){System.out.println("HelloSercer failed: "+e);}
	}
}