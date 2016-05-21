import java.net.*;
import java.io.*;

public class GreetingClient
{
	public static void main(String[] args)
	{
		try
		{
			System.out.println("connection to localhost on port 6066 ...");
			Socket client = new Socket("localhost",6066);
			System.out.println("just connect to " + client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			out.writeUTF("hello from "+ client.getLocalSocketAddress() + "\n");
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			System.out.println("server says " + in.readUTF());
			client.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}