import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.lang.*;

public class TestDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int ch=0;
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		
		Connection con = null;
		PreparedStatement ps_ins=null,ps_upd=null,ps_ser=null,ps_del=null,ps_sel=null;
		ResultSet rs = null;
		
		String name,reason;
		try
		{
			Class.forName(Constants.DRIVER);
			con = DriverManager.getConnection(Constants.URL, Constants.UID, Constants.PASS);
			System.out.println("established con "+con);
			
			ps_ins = con.prepareStatement("insert into enemies(name,reason) values(?,?)"); 
			ps_sel = con.prepareStatement("select * from enemies");
			ps_del = con.prepareStatement("delete from enemies where name = ?");
			ps_upd = con.prepareStatement("update enemies set reason=? where name=?");
			ps_ser = con.prepareStatement("select * from enemies where name LIKE ?");
					
			
			while(ch!=6)
			{
				System.out.println("");
				System.out.println("Press 1 to add enemy");
				System.out.println("Press 2 to list enemies");
				System.out.println("Press 3 to delete enemies");
				System.out.println("Press 4 to update");
				System.out.println("Press 5 to search");
				System.out.println("Press 6 to exit");
				
				ch = sc1.nextInt();
				
				switch(ch)
				{
					case 1:
							System.out.println("inserting...");
							System.out.println("Enter name of enemy");
							name = sc2.nextLine();
							System.out.println("Enter reason for hating enemy "+name);
							reason = sc2.nextLine();
							
							ps_ins.setString(1, name);
							ps_ins.setString(2, reason);
							ps_ins.execute();
							
							break;
					case 2:
							System.out.println("listing...");
							ps_sel.execute();
							
							rs = ps_sel.getResultSet();
							
							while(rs.next())
							{
								name = rs.getString("name");
								reason = rs.getString("reason");
								System.out.println("Name : "+name+" Reason : "+reason);
							}
							
							break;
					case 3:
							System.out.println("deleting...");
							System.out.println("enter name of enemy you want to delete");
							name = sc2.nextLine();
							ps_del.setString(1, name);
							ps_del.execute();
							
							break;
					case 4:
							System.out.println("updating...");
							System.out.println("enter name of enemy you want to update");
							name = sc2.nextLine();
							System.out.println("enter new reason for enemy that you hate");
							reason = sc2.nextLine();
						
							ps_upd.setString(1, reason);
							ps_upd.setString(2, name);
							ps_upd.execute();
							
							break;
					case 5:
							System.out.println("searching...");
							System.out.println("enter part name of enemy to search");
							name = sc2.nextLine();
							ps_ser.setString(1, "%"+name+"%");
							ps_ser.execute();
							
							rs = ps_ser.getResultSet();
							while(rs.next())
							{
								name = rs.getString("name");
								reason = rs.getString("reason");
								System.out.println("Name : "+name+" Reason : "+reason);
							}
							
							break;
					case 6:
							System.out.println("Tata, bye bye, get lost!");
							
							break;
					default:
							System.out.println("Oye, enter 1-6 only, illandre ushhar!");
							break;
						
				}
				
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(ps_ins!=null)
				try {
					ps_ins.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

}
