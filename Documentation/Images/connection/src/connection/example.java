package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Statement;


public class example {

	public static void main(String[] args) {
		int ch=0;
		int op=0;
		Scanner sc = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testDB","root","Deepsi@4");
			do {
				switch(op) {
				case 1:
					System.out.println("Enter Employee ID");
					int pid = sc.nextInt();
					System.out.println("Enter Employee Name");
					String pname = sc.next();
					System.out.println("Enter Employee Age");
					int page = sc.nextInt();
					
					String sql = "INSERT INTO firstdemo (id,name,age) VALUES (?,?,?)";
					
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setInt(1, pid);
					pst.setString(2,pname);
					pst.setInt(3, page);
					
					int rows = pst.executeUpdate();
					if(rows>0) {
						System.out.println("Inserted Successfully!");
					}
					System.out.println("Do you want to continue?");
					ch = sc.nextInt();
					break;
				
				case 2:
					System.out.println("Enter records to update");
					System.out.println("Enter Employee ID for updation");
					int uid = sc.nextInt();
					System.out.println("Enter updated Employee Name");
					String uname = sc.next();
					System.out.println("Enter updated Employee Age");
					int uage = sc.nextInt();
					
					String sql1 = "UPDATE firstdemo SET name=?,age=? WHERE id=?";
					PreparedStatement pst1 = con.prepareStatement(sql1);
					pst1.setString(1,uname);
					pst1.setInt(2, uage);
					pst1.setInt(3, uid);
					
					int urows = pst1.executeUpdate();
					if(urows>0) {
						System.out.println("Updated Successfully!");
					}
					break;
				
				case 3:
					System.out.println("Enter records to delete");
					System.out.println("Enter Employee ID for deletion");
					int did = sc.nextInt();
				
					String sql2 = "DELETE FROM firstdemo WHERE id=?";
					PreparedStatement pst2 = con.prepareStatement(sql2);
					pst2.setInt(1,did);
					
					int drows = pst2.executeUpdate();
					if(drows>0) {
						System.out.println("Deleted Successfully!");
					}				
					break;
			
			case 4:
				String sql4="SELECT * FROM firstdemo";
				
				Statement st = con.createStatement();
				ResultSet res = st.executeQuery(sql4);
				
				int c=0;
				while(res.next()) {
					int eid = res.getInt(1);
					String ename = res.getString(2);
					int eage = res.getInt("age");
					
					String ans = "EMPLOYEE %d)   %d   %s   %d";
					System.out.println(String.format(ans, ++c,eid,ename,eage));	
				}
				break;
				}
				System.out.println("enter choice: 1.Insert 2.Update 3.Delete 4.Display\n");
				op = sc.nextInt();
	
			}while(op!=0);	
		}catch(Exception e) {
				e.printStackTrace();
			}
		}

}
