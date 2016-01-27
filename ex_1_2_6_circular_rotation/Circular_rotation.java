public class Circular_rotation
{
	public static boolean IsCircularRotation(String s,String t)
	{
		if(s.length()!=t.length()) return false;
		if(s.equals(t)) return true;
		int i=s.indexOf(t.substring(0,1));
		if(i==-1) return false;
		while(i<s.length())
		{
			if(t.equals(s.substring(i,s.length())+s.substring(0,i)))//substring(0,-1)="" is tested
				return true;
			i=s.indexOf(t.substring(0,1),i+1);
			if(i==-1) return false;
		}
		return false;
	}
	
	public static void main(String[] args)
	{
		String s="CACG";
		String t="CGCA";
		System.out.println(IsCircularRotation(s,t));
	}
}