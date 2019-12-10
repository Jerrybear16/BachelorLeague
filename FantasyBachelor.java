


import java.sql.*;





public class FantasyBachelor
{

	public static Connection connect() throws ClassNotFoundException, SQLException
	{
		Connection con=null;
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:FantasyBachelor.db.sqbpro");
		System.out.println("Connected");

		return con;
	}




	public static void main(String[] args)
	{
		try
		{
			connect();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			System.out.println(e+"Could not connect to Database");
		}
		
	}
}