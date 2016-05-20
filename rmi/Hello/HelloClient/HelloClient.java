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

public class HelloClient
{
	public static void main(String[] args)
	{
		try
		{
			HelloInterface hello = (HelloInterface) Naming.lookup("Hello");
			//HelloInterface hello = (HelloInterface) Naming.lookup("//192.168.1.105:1099/Hello");
			System.out.println(hello.say());
		}catch(Exception e){System.out.println("HelloClient exception: "+e);}
	}
}