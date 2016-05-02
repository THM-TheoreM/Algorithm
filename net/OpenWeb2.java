import java.net.*;
import java.io.*;
import java.awt.*;

public class OpenWeb2{
	public static void main(String[] args){

		try{
				URI uri=new URI("http://www.baidu.com/");
				Desktop.getDesktop().browse(uri); 
			}catch(IOException e){
				e.printStackTrace();
			}catch(URISyntaxException e){
				e.printStackTrace();
			}
	}
}