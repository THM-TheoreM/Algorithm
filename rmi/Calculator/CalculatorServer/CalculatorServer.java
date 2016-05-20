import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

interface Calculator extends Remote
{
	public long add(long a, long b) throws RemoteException;
	public long sub(long a, long b) throws RemoteException;
	public long mul(long a, long b) throws RemoteException;
	public long div(long a, long b) throws RemoteException;
}

class CalculatorImpl extends UnicastRemoteObject implements Calculator
{
	public CalculatorImpl() throws RemoteException
	{super();}
	
	public long add(long a, long b) throws RemoteException
	{System.out.println("plus");return a+b;}

	public long sub(long a, long b) throws RemoteException
	{System.out.println("minius");return a-b;}

	public long mul(long a, long b) throws RemoteException
	{System.out.println("time");return a*b;}

	public long div(long a, long b) throws RemoteException
	{System.out.println("divide");return a/b;}	
}

public class CalculatorServer
{
	public static void main(String[] args)
	{
		try
		{
			LocateRegistry.createRegistry(1099);
			Calculator c = new CalculatorImpl();
			Naming.rebind("//localhost:1099/Calculator",c);
			System.out.println("loading...");
		}catch(Exception e){System.out.println(e);}
	}
}
