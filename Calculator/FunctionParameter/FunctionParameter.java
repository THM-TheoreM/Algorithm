import java.util.function.*;

public class FunctionParameter
{
	public static int max(int a,int b)
	{ return a>b?a:b; }
	
	public static int min(int a,int b)
	{ return a<b?a:b; }
	
	public static int negative(int a)
	{ return -a; }
	
	public static int abs(int a)
	{ return Math.abs(a); }
	
	public static int[] operArray(BiFunction<Integer,Integer,Integer> operator,int[] arr)
	{
        int[] ans=new int[arr.length-1];
        for(int i=0;i<arr.length-1; i++) 
			ans[i]=operator.apply(arr[i],arr[i+1]);
        return ans;
    }
	
	public static int[] operArray(Function<Integer,Integer> operator,int[] arr)
	{
		int[] ans=new int[arr.length];
		for(int i=0;i<arr.length;i++)
			ans[i]=operator.apply(arr[i]);
		return ans;
	}
	
	
	public static void main(String[] args)
	{
		int c=1;
		System.out.println(-c);
		int[] a={1,-2,3,-4};
		System.out.println("Original array:");
		for(int i=0;i<a.length;i++)
			System.out.print(a[i]+"\t");
		System.out.println("\n----------------------");
		
		System.out.println("After abs:");
		int[] b=operArray(FunctionParameter::abs,a);
		for(int i=0;i<b.length;i++)
			System.out.print(b[i]+"\t");
		System.out.println("\n----------------------");
		
		System.out.println("After negative:");
		b=operArray(FunctionParameter::negative,a);
		for(int i=0;i<b.length;i++)
			System.out.print(b[i]+"\t");
		System.out.println("\n----------------------");
		
		System.out.println("After max:");
		b=operArray(FunctionParameter::max,a);
		for(int i=0;i<b.length;i++)
			System.out.print(b[i]+"\t");
		System.out.println("\n----------------------");
		
		System.out.println("After min:");
		b=operArray(FunctionParameter::min,a);
		for(int i=0;i<b.length;i++)
			System.out.print(b[i]+"\t");
		System.out.println("\n----------------------");
	}
}