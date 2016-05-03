import edu.princeton.cs.algs4.*;
import java.io.File;

public class ReadFile
{	
	private Queue<String> file=new Queue<String>();
	
	public void file(String pathname)
	{
		File a=new File(pathname);
		String[] b=a.list();
		
		for(int i=0;i<b.length;i++)
		{
			File c=new File(pathname+"\\"+b[i]);
			if(!c.isDirectory()) file.enqueue(pathname+"\\"+b[i]);
			else                 file(pathname+"\\"+b[i]);
		}
	}
	
	public void fileName()
	{
		for(String x: file)
			StdOut.println(x);
	}
	
	public int fileNumber()
	{return file.size();}
	
	public static void main(String[] args) 
	{
		ReadFile f=new ReadFile();
		f.file(args[0]);
		f.fileName();
		StdOut.println(f.fileNumber());
    }
}