import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;

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

public class CalculatorClient
{
	public static void main(String[] args)
	{
		try
		{
			Calculator c = (Calculator) Naming.lookup("//localhost:1099/Calculator");
			System.out.println(c.sub(4,5));
			System.out.println(c.add(4,5));
			System.out.println(c.mul(3,6));
			System.out.println(c.div(9,3));
		}catch(Exception e){System.out.println(e);}
	}
}