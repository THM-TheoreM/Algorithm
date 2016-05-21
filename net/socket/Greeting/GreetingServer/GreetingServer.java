import java.net.*;
import java.io.*;

public class GreetingServer extends Thread
{
	private ServerSocket serverSocket;
	
	public GreetingServer(int port) throws IOException
	{
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(1000000);
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				System.out.println("waiting for client on port " + serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();
				System.out.println("just connected to "+ server.getRemoteSocketAddress());
				DataInputStream in = new DataInputStream(server.getInputStream());
				System.out.println(in.readUTF());
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				out.writeUTF("thank you for connection to " + server.getLocalSocketAddress() + "\ngoodbye!");
				server.close();
			}catch(SocketTimeoutException s){
				System.out.println("socket time out");
				break;
			}catch(IOException e){
				System.out.println("io");
				break;
			}
		}
	}
	
	public static void main(String[] args)
	{
		try
		{
			Thread t = new GreetingServer(6066);
			t.start();
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
}