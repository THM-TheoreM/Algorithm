import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL{
	public static void main(String[] args)
	{
		String url;
		String sql;
		Connection conn;
		Statement st;
		ResultSet rs;
		
		String databasename="";
		String username="";
		String password="";
		String tablename="";
		
		url="jdbc:mysql://localhost:3306/"+databasename+"?user="+username+"&password="+password;
	
		try{
			conn=DriverManager.getConnection(url);
			st=conn.createStatement();
			sql="select * from"+tablename;
			rs=st.executeQuery(sql);
		
			while(rs.next()){
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
			}
		
			rs.close();
			conn.close();
		}catch(SQLException e){
			System.out.println("Error:"+e.toString()+e.getMessage());
		}
	}
}