public class PhoneDir
{
	private static String[] format(String str)
	{
		String[] ans=new String[3];
		
		//phone
		ans[0]=str.substring(str.indexOf("+")+1,str.indexOf("+")+13+str.indexOf("-",str.indexOf("+"))-str.indexOf("+"));
		
		//name
		ans[1]=str.substring(str.indexOf("<")+1,str.indexOf(">"));
		
		//address
		ans[2]=str.replace(ans[0],"");
		ans[2]=ans[2].replace(ans[1],"");
		ans[2]=ans[2].replaceAll("[+<>\\/,:;\\?!$\\*]","");
		ans[2]=ans[2].replaceAll("_"," ");
		ans[2]=ans[2].replaceAll("\\s+"," ");//let two or more whitespaces to be one
		//delete extra whitespaces at the beginning and the end of the string
		int lo=0,hi=ans[2].length()-1;
		while(ans[2].charAt(lo)==' ')	lo++;
		while(ans[2].charAt(hi)==' ')	hi--;
		ans[2]=ans[2].substring(lo,hi+1);
		
		return ans;
	}
	
	public static String phone(String strng, String num) 
	{
		String[] s=strng.split("\n");
		String ans="";
		int cnt=0;
		for(int i=0;i<s.length;i++)
		{
			String[] tmp=format(s[i]);
			if(num.equals(tmp[0]))
			{
				ans="Phone => "+tmp[0]+", Name => "+tmp[1]+", Address => "+tmp[2];
				cnt++;
			}
		}
		if(cnt==1)
			return ans;
		else if(cnt==0)
			return "Error => Not found: "+num;
		else
			return "Error => Too many people: "+num;
    }
	
	public static void main(String[] args)
	{   //test a=new test(); a.main(null);
		String dr = "/+1-541-754-3010 156 Alphand_St. <J Steeve>\n 133, Green, Rd. <E Kustur> NY-56423 ;+1-541-914-3010\n"
				+ "+1-541-984-3012 <P Reed> /PO Box 530; Pollocksville, NC-28573\n :+1-321-512-2222 <Paul Dive> Sequoia Alley PQ-67209\n"
				+ "+1-741-984-3090 <Peter Reedgrave> _Chicago\n :+1-921-333-2222 <Anna Stevens> Haramburu_Street AA-67209\n"
				+ "+1-111-544-8973 <Peter Pan> LA\n +1-921-512-2222 <Wilfrid Stevens> Wild Street AA-67209\n"
				+ "<Peter Gone> LA ?+1-121-544-8974 \n <R Steell> Quora Street AB-47209 +1-481-512-2222\n"
				+ "<Arthur Clarke> San Antonio $+1-121-504-8974 TT-45120\n <Ray Chandler> Teliman Pk. !+1-681-512-2222! AB-47209,\n"
				+ "<Sophia Loren> +1-421-674-8974 Bern TP-46017\n <Peter O'Brien> High Street +1-908-512-2222; CC-47209\n"
				+ "<Anastasia> +48-421-674-8974 Via Quirinal Roma\n <P Salinger> Main Street, +1-098-512-2222, Denver\n"
				+ "<C Powel> *+19-421-674-8974 Chateau des Fosses Strasbourg F-68000\n <Bernard Deltheil> +1-498-512-2222; Mount Av.  Eldorado\n"
				+ "+1-099-500-8000 <Peter Crush> Labrador Bd.\n +1-931-512-4855 <William Saurin> Bison Street CQ-23071\n"
				+ "<P Salinge> Main Street, +1-098-512-2222, Denve\n"+ "<P Salinge> Main Street, +1-098-512-2222, Denve\n";
		System.out.println(phone(dr, "1-908-512-2222"));
	}
}