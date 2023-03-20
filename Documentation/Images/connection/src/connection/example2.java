package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.sql.Statement;
import connection.User;


public class example2{

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
					int fid = sc.nextInt();
					System.out.println("Enter Employee Name");
					String fname = sc.next();
					System.out.println("Enter Employee Age");
					int fage = sc.nextInt();
					
					String s1 = "INSERT INTO firstdemo (id,name,age) VALUES (?,?,?)";
					
					PreparedStatement ps1 = con.prepareStatement(s1);
					ps1.setInt(1, fid);
					ps1.setString(2,fname);
					ps1.setInt(3, fage);
					
					int rows = ps1.executeUpdate();
					if(rows>0) {
						System.out.println("Inserted Successfully!");
					}
					break;
				
				case 2:
					System.out.println("Enter records to update");
					System.out.println("Enter Employee ID for updation");
					int ufid = sc.nextInt();
					System.out.println("Enter updated Employee Name");
					String ufname = sc.next();
					System.out.println("Enter updated Employee Age");
					int ufage = sc.nextInt();
					
					String s2 = "UPDATE firstdemo SET name=?,age=? WHERE id=?";
					PreparedStatement ps2 = con.prepareStatement(s2);
					ps2.setString(1,ufname);
					ps2.setInt(2, ufage);
					ps2.setInt(3, ufid);
					
					int urows = ps2.executeUpdate();
					if(urows>0) {
						System.out.println("Updated Successfully!");
					}
					break;
				
				case 3:
					System.out.println("Enter Employee ID for deletion");
					int dfid = sc.nextInt();
				
					String s3 = "DELETE FROM firstdemo WHERE id=?";
					PreparedStatement ps3 = con.prepareStatement(s3);
					ps3.setInt(1,dfid);
					
					int drows = ps3.executeUpdate();
					if(drows>0) {
						System.out.println("Deleted Successfully!");
					}				
					break;
				case 4:
					String s4 = ("SELECT * FROM firstdemo LIMIT 1");
					Statement st = con.createStatement();
					ResultSet res = st.executeQuery(s4);
					while(res.next())
					{
						System.out.println(res.getInt(1)+"|"+res.getString(2)+"|"+res.getInt(3));
					}
					break;
			case 5:
				String s5 = "INSERT INTO firstdemo (id,name,age) VALUES (?,?,?)";
				  PreparedStatement ps4 = con.prepareStatement(s5);
				      List<User> user = new ArrayList<>();				      
				      user.add(new User(3,"abc",20));
				      user.add(new User(4,"def",20));
				      for (User i : user) {
				        ps4.setInt(1, i.getId());
				        ps4.setString(2, i.getName());
				        ps4.setInt(3, i.getAge());
				        ps4.executeUpdate();
				      }
				      break;
			case 6:
				 String s6 = "SELECT * FROM firstdemo";
				       Statement s = con.createStatement();
				       ResultSet rs = s.executeQuery(s6);
				      ArrayList<User> u = new ArrayList<>();
				      while (rs.next()) {
				        int id = rs.getInt("id");
				        String name = rs.getString("name");
				        int age = rs.getInt("age");
				        u.add(new User(id,name,age));
				      }
				      for (User i : u) {
				        System.out.println(i.getId() + " | " + i.getName() + " | " + i.getAge());
				      }
				      break;
				}
				System.out.println("enter choice: 1.Insert 2.Update 3.Delete 4.Display 5.InsertMultiple 6.DisplayAll\n");
				op = sc.nextInt();
			}while(op!=0);	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		}
}
		

