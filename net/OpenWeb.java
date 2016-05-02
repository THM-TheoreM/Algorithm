import java.io.*;

public class OpenWeb{
	public static void main(String[] args){

		String cmdStr = "cmd /c start iexplore http://www.baidu.com/";
		
		try{
				Runtime.getRuntime().exec(cmdStr);
			}catch(IOException e){
				e.printStackTrace();
			} 
	}
}
