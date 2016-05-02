public class ComparableWithIndex implements Comparable<ComparableWithIndex>
{
	private Comparable value;
	private int index;
		
	public ComparableWithIndex(Comparable value,int index)
	{
		this.value=value;
		this.index=index;
	}
	
	public int compareTo(ComparableWithIndex that)
	{
		if(value.compareTo(that.value)>0)		return 1;
		else if(value.compareTo(that.value)==0)	return 0;
		else									return -1;
	}
	
	public Comparable getValue()
	{ return value;}
	
	public int getIndex()
	{ return index;}
	
	public String toString()
	{ return value.toString()+" "+index; }
	
	public static void main(String[] args)
	{}
}