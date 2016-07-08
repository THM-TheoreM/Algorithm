import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Iterator;

public class Json
{		
	private static int Big(String text)
	{
		int index = 1;
		
		for(int i=1;i<text.length();i++)
		{
			if(text.charAt(i) == '{') index++;
			if(text.charAt(i) == '}') index--;
			if(index == 0)            return i;
		}
		
		return -1;
	}

	private static int Med(String text)
	{
		int index = 1;
		
		for(int i=1;i<text.length();i++)
		{
			if(text.charAt(i) == '[') index++;
			if(text.charAt(i) == ']') index--;
			if(index == 0)            return i;
		}
		
		return -1;
	}

	public static Hashtable<String, Object> Big2(String text)
	{
		text = text.substring(1,text.length()-1);
		Hashtable<String, Object> result = new Hashtable<String, Object>();

		while(!text.equals(""))
		{
			String key = text.substring(1,text.indexOf("\"",1));
			text = text.substring(text.indexOf("\"",1)+2,text.length());
			
			if(text.charAt(0) == '"')
			{
				String value = text.substring(1,text.indexOf("\"",1));
				result.put(key,value);
				if(text.indexOf("\"",1)+2 >= text.length()) text = text.substring(text.length(),text.length());
				else                                        text = text.substring(text.indexOf("\"",1)+2,text.length());
				continue;
			}
			
			if(text.charAt(0) == '0' | text.charAt(0) == '1' | text.charAt(0) == '2' | text.charAt(0) == '3' | text.charAt(0) == '4' | text.charAt(0) == '5' | text.charAt(0) == '6' | text.charAt(0) == '7' | text.charAt(0) == '8' | text.charAt(0) == '9')
			{
				int value;
				if(text.indexOf(",") == -1) value = Integer.parseInt(text.substring(0,text.length())); 
				else                        value = Integer.parseInt(text.substring(0,text.indexOf(",")));
				result.put(key,value);
				if(text.indexOf(",") == -1) text = text.substring(text.length(),text.length());
				else                        text = text.substring(text.indexOf(",")+1,text.length());
				continue;
			}

			if(text.charAt(0) == '{')
			{
				Hashtable<String, Object> value = Big2(text.substring(0,Big(text)+1));
				result.put(key,value);
				if(Big(text)+2 >= text.length()) text = text.substring(text.length(),text.length());
				else                             text = text.substring(Big(text)+2,text.length());
				continue;
			}
			
			if(text.charAt(0) == '[')
			{
				Hashtable<Integer, Object> value = Med2(text.substring(0,Med(text)+1));
				result.put(key,value);
				if(Med(text)+2 >= text.length()) text = text.substring(text.length(),text.length());
				else                             text = text.substring(Med(text)+2,text.length());
				continue;				
			}
		}
		
		return result;
	}
	
	private static Hashtable<Integer, Object> Med2(String text)
	{
		text = text.substring(1,text.length()-1);
		Hashtable<Integer, Object> result = new Hashtable<Integer, Object>();
		
		if(text.charAt(0) == '"')
		{
			text = text.substring(1,text.length()-1);
			String[] tmp = text.split("\",\"");
			for(int i=0;i<tmp.length;i++) result.put(i,tmp[i]);		
		}
		
		if(text.charAt(0) == '0' | text.charAt(0) == '1' | text.charAt(0) == '2' | text.charAt(0) == '3' | text.charAt(0) == '4' | text.charAt(0) == '5' | text.charAt(0) == '6' | text.charAt(0) == '7' | text.charAt(0) == '8' | text.charAt(0) == '9')
		{
			String[] tmp = text.split(",");
			for(int i=0;i<tmp.length;i++) result.put(i,Integer.parseInt(tmp[i]));
		}

		if(text.charAt(0) == '{')
		{
			int index = 0;
			while(true)
			{
				if(Big(text)+1 < text.length())
				{
					result.put(index,Big2(text.substring(0,Big(text)+1)));
					text = text.substring(Big(text)+2,text.length());
					index++;
				}
				else
				{
					result.put(index,Big2(text));
					break;
				}
			}
		}

		if(text.charAt(0) == '[')
		{
			int index = 0;
			while(true)
			{
				if(Med(text)+1 < text.length())
				{
					result.put(index,Med2(text.substring(0,Med(text)+1)));
					text = text.substring(Med(text)+2,text.length());
					index++;
				}
				else
				{
					result.put(index,Med2(text));
					break;
				}
			}

		}
		
		return result;
	}
	
	public static void main(String[] args)
	{
		System.out.print(Big("{q{we}r}")+" ");
		System.out.println(Med("[q[we]r]")+"\n");
		
		System.out.println("{\"a\":\"1\",\"b\":\"2\",\"c\":\"3\"}");
		Hashtable<String, Object> result1 = Big2("{\"a\":\"1\",\"b\":\"2\",\"c\":\"3\"}");
		for(Iterator it = result1.keySet().iterator(); it.hasNext();)
		{
			String key = (String) it.next();
			Object value = result1.get(key);
			System.out.print(value + " ");
		}
		System.out.println("\n");
		
		System.out.println("{\"a\":1,\"b\":2,\"c\":3}");
		result1 = Big2("{\"a\":1,\"b\":2,\"c\":3}");
		for(Iterator it = result1.keySet().iterator(); it.hasNext();)
		{
			String key = (String) it.next();
			Object value = result1.get(key);
			System.out.print(value + " ");
		}
		System.out.println("\n");
		
		System.out.println("{\"a\":{\"x\":1},\"b\":{\"y\":2},\"c\":{\"z\":3}}");
		result1 = Big2("{\"a\":{\"x\":1},\"b\":{\"y\":2},\"c\":{\"z\":3}}");
		System.out.print(((Hashtable) result1.get("a")).get("x") + " ");
		System.out.print(((Hashtable) result1.get("b")).get("y") + " ");
		System.out.print(((Hashtable) result1.get("c")).get("z"));
		System.out.println("\n");
		
		System.out.println("{\"a\":[\"x\",\"y\"],\"b\":[1,2]}");
		result1 = Big2("{\"a\":[\"x\",\"y\"],\"b\":[1,2]}");
		System.out.print(((Hashtable) result1.get("a")).get(0) + " ");
		System.out.print(((Hashtable) result1.get("a")).get(1) + " ");
		System.out.print(((Hashtable) result1.get("b")).get(0) + " ");
		System.out.print(((Hashtable) result1.get("b")).get(1));
		System.out.println("\n");
		
		System.out.println("[\"qwer\",\"qwe\",\"qw\"]");
		Hashtable<Integer, Object> result2 = Med2("[\"qwer\",\"qwe\",\"qw\"]");
		for(Iterator it = result2.keySet().iterator(); it.hasNext();)
		{
			int key = (int) it.next();
			Object value = result2.get(key);
			System.out.print(value + " ");
		}
		System.out.println("\n");

		System.out.println("[123,12,1]");
		result2 = Med2("[123,12,1]");
		for(Iterator it = result2.keySet().iterator(); it.hasNext();)
		{
			int key = (int) it.next();
			Object value = result2.get(key);
			System.out.print(value + " ");
		}
		System.out.println("\n");

		System.out.println("[{\"qwer\":\"123\"},{\"qwe\":\"12\"},{\"qw\":\"1\"}]");
		result2 = Med2("[{\"qwer\":\"123\"},{\"qwe\":\"12\"},{\"qw\":\"1\"}]");
		System.out.print(((Hashtable) result2.get(0)).get("qwer") + " ");
		System.out.print(((Hashtable) result2.get(1)).get("qwe") + " ");
		System.out.print(((Hashtable) result2.get(2)).get("qw"));
		System.out.println("\n");
	
		System.out.println("[[\"qwer\"],[\"qwe\"],[\"qw\"]]");
		result2 = Med2("[[\"qwer\"],[\"qwe\"],[\"qw\"]]");
		System.out.print(((Hashtable) result2.get(0)).get(0) + " ");
		System.out.print(((Hashtable) result2.get(1)).get(0) + " ");
		System.out.print(((Hashtable) result2.get(2)).get(0));
	}
}