public class Connection
{
	private int p;
	private int q;
	
	public Connection(int p,int q)
	{this.p=p;this.q=q;}
	
	public int getp()	{return p;}
	public int getq()	{return q;}
	
	public static void main(String[] args)
	{
		Connection a=new Connection(1,2);
	}
}