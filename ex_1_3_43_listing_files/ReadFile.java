import edu.princeton.cs.algs4.*;
import java.io.File;

public class ReadFile
{
	//using queue to realize a BFS(Breadth-First-Search) tranverse
	//NOTICE: if we use a stack instead of a queue, we will realize a DFS(Depth-First-Search) tranverse, which is shown below within remarks /*...*/
	//In fact, a recursive implementation is also valid, which is also shown below within remarks /*...*/
	public static void readfile(String path)
	{
		Queue<String> queue=new Queue<String>();
		File file=new File(path);
		if (!file.exists())
			StdOut.println("Invalid path");
		else if(!file.isDirectory())//file
            StdOut.println(file.getName());
		else//folder
		{
			queue.enqueue(path);
			while(!queue.isEmpty())
			{
				String str=queue.dequeue();
				File folder=new File(str);
				StdOut.println("\nFolder:"+folder.getName());
				String[] filelist = folder.list();
				for(int i=0;i<filelist.length;i++) 
				{
					File subfile = new File(str + "/" + filelist[i]);
					if (!subfile.isDirectory()) 
						StdOut.println(subfile.getName());
					else
						queue.enqueue(str + "/" + filelist[i]);
				}
			}
		}
	}
	
	/*public static void readfile(String path)
	{
		Stack<String> stack=new Stack<String>();
		File file=new File(path);
		if (!file.exists())
			StdOut.println("Invalid path");
		else if(!file.isDirectory())//file
            StdOut.println(file.getName());
		else//folder
		{
			stack.push(path);
			while(!stack.isEmpty())
			{
				String str=stack.pop();
				File folder=new File(str);
				StdOut.println("\nFolder:"+folder.getName());
				String[] filelist = folder.list();
				for(int i=0;i<filelist.length;i++) 
				{
					File subfile = new File(str + "/" + filelist[i]);
					if (!subfile.isDirectory()) 
						StdOut.println(subfile.getName());
					else
						stack.push(str + "/" + filelist[i]);
				}
			}
		}
	}*/
	
	/*public static void readfile(String filepath)
	{
		File file = new File(filepath);
		if (!file.exists())
			StdOut.println("Invalid path");
		else if(!file.isDirectory())//file
            StdOut.println(file.getName());
		else//folder
		{
            String[] filelist = file.list();
            for(int i=0;i<filelist.length;i++) 
			{
				File subfile = new File(filepath + "/" + filelist[i]);
                if (subfile.isDirectory()) 
                    readfile(filepath + "/" + filelist[i]);
				else
					StdOut.println(subfile.getName());
			}
		} 
    }*/
        
	public static void main(String[] args) 
	{
		StdOut.println("Files contained in the path \""+args[0]+"\":\n");
        readfile(args[0]);
    }
}