package demojava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import demojava.Customer;

public class CustomerDemo {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		int op=0;
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CustomerDB","root","Deepsi@4");
			do {
			switch(op) {
			case 1:insert(con);
					break;
			case 2:insertMultiple(con);
					break;
			case 3:Display(con);
					break;
			case 4:DisplayAll(con);
					break;
			default:System.out.println("Enter valid Input");
			}
			System.out.println("Enter Your Choice");
			System.out.println("1.Insert \n 2.Insert Multiple \n 3.Display \n 4.DisplayAll");
			op = sc.nextInt();
		}while(op<=4);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	private static void insert(Connection con) throws SQLException {
		
		
		System.out.println("Enter Customer ID:");
		int c_id = sc.nextInt();
		System.out.println("Enter Customer Name:");
		String c_name = sc.next();
		System.out.println("Enter Customer Mobile NUmber:");
		String c_mobile_no = sc.next();
		System.out.println("Enter Customer EMailID:");
		String c_email = sc.next();
		System.out.println("Enter No.of items purchased by Customer:");
		int c_itemspurchased = sc.nextInt();
		String s1 = "INSERT INTO Customer (id,name,mobile,email,itemspurchased) VALUES (?,?,?,?,?)";
	
		PreparedStatement ps1 = con.prepareStatement(s1);
		ps1.setInt(1, c_id);
		ps1.setString(2,c_name);
		ps1.setString(3, c_mobile_no);
		ps1.setString(4,c_email);
		ps1.setInt(5, c_itemspurchased);
		int rows = ps1.executeUpdate();
		if(rows>0) {
			System.out.println("Inserted Successfully!");
		}	
	}
	
	private static void insertMultiple(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		String s5 = "INSERT INTO Customer(id,name,mobile,email,itemspurchased) VALUES (?,?,?,?,?)";
		  PreparedStatement ps4 = con.prepareStatement(s5);
		      List<Customer> list1 = new ArrayList<>();
		      System.out.println("Enter Number:");
		      int n=sc.nextInt();
		      while(n!=0) {
		  		System.out.println("Enter Customer ID:");
		    	 int c_id = sc.nextInt();
		  		System.out.println("Enter Customer Name:");
		  		String c_name = sc.next();
		  		System.out.println("Enter Customer Mobile NUmber:");
		  		String c_mobile_no = sc.next();
		  		System.out.println("Enter Customer EMailID:");
		  		String c_email = sc.next();
		  		System.out.println("Enter No.of items purchased by Customer:");
		  		int c_itemspurchased = sc.nextInt();  
		      list1.add(new Customer(c_id,c_name,c_mobile_no,c_email,c_itemspurchased));
		      n-=1;}
		      for (Customer i : list1) { System.out.println("Enter Customer ID:");
		        ps4.setInt(1, i.getId());
		        ps4.setString(2, i.getName());
		        ps4.setString(3, i.getPhn());
		        ps4.setString(4, i.getMail());
		        ps4.setInt(5, i.getItems());
		        ps4.executeUpdate();
		      }
		System.out.println("Insertion Successful");
	}

	private static void Display(Connection con)throws SQLException{
		// TODO Auto-generated method stub
		System.out.println("Enter Employee ID to retrieve");
		int c_id= sc.nextInt();
		String sql4="SELECT * FROM Customer WHERE id=?";
		PreparedStatement st = con.prepareStatement(sql4);
		st.setInt(1,c_id);
		ResultSet res = st.executeQuery();
		while(res.next()) 	
			System.out.println(res.getInt(1)+"|"+res.getString(2)+"|"+res.getString(3)+"|"+res.getString(4)+"|"+res.getInt(5));	
	}

	private static void DisplayAll(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		 String s6 = "SELECT * FROM Customer";
	       Statement s = con.createStatement();
	       ResultSet rs = s.executeQuery(s6);
	      ArrayList<Customer> list = new ArrayList<>();
	      while (rs.next()) {
	        int c_id = rs.getInt("id");
	        String c_name = rs.getString("name");
	        String c_mobile_no = rs.getString("mobile");
	        String c_email = rs.getString("email");
	        int c_itemspurchased = rs.getInt("itemspurchased");
	        list.add(new Customer(c_id,c_name,c_mobile_no,c_email,c_itemspurchased));
	      }
	      for (Customer i : list) {
	        System.out.println(i.getId() + " | " + i.getName() + " | " + i.getPhn()+" | "+i.getMail()+" | "+i.getItems());
	      }
		
	}
	
}
