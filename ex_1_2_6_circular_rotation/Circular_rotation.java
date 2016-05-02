public class Circular_rotation
{
	public static boolean IsCircularRotation1(String s, String t)
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
	
	public static boolean IsCircularRotation2(String s, String t)
	{
        String u=s+s;
		if(s.length()==t.length() && u.indexOf(t)!=-1) return true; 
		return false;
	}
	
	
	public static void main(String[] args)
	{
		String s1="CACG";
		String t1="CGCA";
		System.out.println(IsCircularRotation1(s1,t1));
		
		String s2="ACTGACG";
		String t2="TGACGAC";
		System.out.println(IsCircularRotation2(s2,t2));
	}
}